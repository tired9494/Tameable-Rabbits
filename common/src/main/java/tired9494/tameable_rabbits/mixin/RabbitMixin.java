package tired9494.tameable_rabbits.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.Util;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tired9494.tameable_rabbits.ModConfig;
import tired9494.tameable_rabbits.TameableRabbits;
import tired9494.tameable_rabbits.entity.ModifiedToBeTameable;
import tired9494.tameable_rabbits.entity.ai.*;

import java.util.Optional;
import java.util.UUID;

@Mixin(Rabbit.class)
public abstract class RabbitMixin extends Animal implements ModifiedToBeTameable {

    @Shadow @Final private static EntityDataAccessor<Integer> DATA_TYPE_ID;

    @Shadow public abstract Rabbit.Variant getVariant();

    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(Rabbit.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Optional<UUID>> OWNER_UUID = SynchedEntityData.defineId(Rabbit.class, EntityDataSerializers.OPTIONAL_UUID);
    private static final EntityDataAccessor<Boolean> TAMED = SynchedEntityData.defineId(Rabbit.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> ORDERED_TO_SIT = SynchedEntityData.defineId(Rabbit.class, EntityDataSerializers.BOOLEAN);

    Item tameItem = BuiltInRegistries.ITEM.get(ModConfig.tameItem);

    protected RabbitMixin(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }

    @Inject(
            at = {@At("TAIL")},
            remap = true,
            method = {"registerGoals()V"}
    )
    private void ta_registerGoals(CallbackInfo callbackInfo) {
        this.goalSelector.addGoal(1, new ModifiedSitWhenOrderedGoal(this));
        this.goalSelector.addGoal(2, new ModifiedFollowOwnerGoal(this, 2.0D, 10.0F, 3.0F, false));
        this.targetSelector.addGoal(2, new ModifiedOwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, new ModifiedOwnerHurtByTargetGoal(this));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.5D, Ingredient.of(Items.HAY_BLOCK), false));
        if(isTame()){
            removeUntamedGoals();
        }
    }

    @Inject(
            at = {@At("TAIL")},
            remap = true,
            method = {"defineSynchedData"}
    )
    private void ta_registerData(SynchedEntityData.Builder builder, CallbackInfo callbackInfo) {
        builder.define(DATA_FLAGS_ID, (byte)0);
        builder.define(OWNER_UUID, Optional.empty());
        builder.define(TAMED, false);
        builder.define(ORDERED_TO_SIT, false);
    }

    @Inject(
            at = {@At("TAIL")},
            remap = true,
            method = {"addAdditionalSaveData"}
    )
    private void ta_writeAdditional(CompoundTag compoundTag, CallbackInfo callbackInfo) {
        compoundTag.putBoolean("Tamed", this.isTame());
        if (this.getTameOwnerUUID() != null) {
            compoundTag.putUUID("Owner", this.getTameOwnerUUID());
        }
    }

    @Inject(
            at = {@At("TAIL")},
            remap = true,
            method = {"readAdditionalSaveData"}
    )
    private void ta_readAdditional(CompoundTag compoundTag, CallbackInfo callbackInfo) {
        this.setTame(compoundTag.getBoolean("Tamed"));
        UUID ownerUUID;
        if (compoundTag.hasUUID("Owner")) {
            ownerUUID = compoundTag.getUUID("Owner");
            if (ownerUUID != null) {
                try {
                    this.setTameOwnerUUID(ownerUUID);
                    this.setTame(true);
                } catch (Throwable throwable) {
                    this.setTame(false);
                }
            }
        }
    }

    @Inject(
            at = {@At("HEAD")},
            remap = true,
            method = {"setVariant(Lnet/minecraft/world/entity/animal/Rabbit$Variant;)V"},
            cancellable = true
    )
    private void ta_setRabbitType(Rabbit.Variant type, CallbackInfo callbackInfo) {
        callbackInfo.cancel();
        if(type == Rabbit.Variant.EVIL){
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(30.0D);
            this.getAttribute(Attributes.ARMOR).setBaseValue(8.0D);
            this.heal(22.0F);
            this.goalSelector.addGoal(4, new RabbitMeleeGoal(this));
            this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
            if(!this.isTame()){
                this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
                this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Wolf.class, true));
            }else{
                this.targetSelector.addGoal(2, new ModifiedOwnerHurtTargetGoal(this));
                this.targetSelector.addGoal(3, new ModifiedOwnerHurtByTargetGoal(this));
                removeUntamedGoals();
            }
            if (!this.hasCustomName()) {
                this.setCustomName(Component.translatable(Util.makeDescriptionId("entity", ResourceLocation.withDefaultNamespace("killer_bunny"))));
            }
        }
        this.entityData.set(DATA_TYPE_ID, type.id());
    }

    @Inject(
            method = "getBreedOffspring(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/AgeableMob;)Lnet/minecraft/world/entity/animal/Rabbit;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/animal/Rabbit;getRandomRabbitVariant(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/entity/animal/Rabbit$Variant;")
    )
    private void checkIfTame(ServerLevel serverLevel, AgeableMob ageableMob, CallbackInfoReturnable<Rabbit> cir, @Local Rabbit rabbit) {
        if (ageableMob instanceof ModifiedToBeTameable parentRabbit && rabbit instanceof ModifiedToBeTameable babyRabbit) {
            if (parentRabbit.isTame()) {
                babyRabbit.setTame(true);
                babyRabbit.setTameOwnerUUID(parentRabbit.getTameOwnerUUID());
            }
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        if (!this.level().isClientSide) {
            if (this.isFood(itemStack) || itemStack.is(tameItem)) {
                if (this.isTame()) {
                    if (this.getHealth() < this.getMaxHealth()) {
                        return healRabbitWithFood(player, itemStack);
                    }
                } else if (itemStack.is(tameItem)) {
                    itemStack.consume(1, player);
                    this.tryToTame(player);
                    return InteractionResult.SUCCESS;
                }
                else {
                    return super.mobInteract(player, interactionHand);
                }
            }
            else if (this.isTame()) {
                InteractionResult interactionResult = super.mobInteract(player, interactionHand);
                if (!interactionResult.consumesAction() && this.getTameOwner() == player) {
                    this.setOrderedToSit(!this.isSitting());
                    this.setJumping(false);
                    this.navigation.stop();
                    this.setTarget(null);
                    return InteractionResult.SUCCESS_NO_ITEM_USED;
                } else {
                    return interactionResult;
                }
            }
            else {
                return super.mobInteract(player, interactionHand);
            }
        } else {
            return this.isTame() || (itemStack.is(tameItem) && !this.isTame()) ?
                    InteractionResult.CONSUME : InteractionResult.PASS;
        }
        return super.mobInteract(player, interactionHand);
    }

    private @NotNull InteractionResult healRabbitWithFood(Player player, ItemStack itemStack) {
        itemStack.consume(1, player);
        FoodProperties foodProperties = itemStack.get(DataComponents.FOOD);
        float f = foodProperties != null ? (float)foodProperties.nutrition() : 1.0F;
        this.heal(2.0F * f);
        return InteractionResult.sidedSuccess(this.level().isClientSide());
    }

    private void tryToTame(Player player) {
        ServerLevel serverLevel = (ServerLevel) this.level();
        if (this.random.nextInt(3) == 0) {
            this.tame(player);
            this.setJumping(false);
            this.navigation.stop();
            this.setTarget(null);
            this.setOrderedToSit(true);
            serverLevel.broadcastEntityEvent(this, EntityEvent.TAMING_SUCCEEDED);
            sendParticles(ParticleTypes.HEART);
        } else {
            serverLevel.broadcastEntityEvent(this, EntityEvent.TAMING_FAILED);
            sendParticles(ParticleTypes.SMOKE);
        }
    }

    private void sendParticles(SimpleParticleType particleType) {
        for (int i = 0; i < 3; ++i) {
            double d0 = this.getRandom().nextGaussian() * 0.02D;
            double d1 = this.getRandom().nextGaussian() * 0.02D;
            double d2 = this.getRandom().nextGaussian() * 0.02D;
            ((ServerLevel) this.level()).sendParticles(particleType, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), 3, d0, d1, d2, 0.02F);
        }
    }

    public void tame(Player player) {
        this.setTame(true);
        this.setTameOwnerUUID(player.getUUID());
        if (player instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.TAME_ANIMAL.trigger(serverPlayer, this);
        }

    }

    public void removeUntamedGoals(){
        try {
            this.goalSelector.getAvailableGoals().stream().filter((wrapped) -> {
                return wrapped.getGoal() instanceof AvoidEntityGoal;
            }).filter(WrappedGoal::isRunning).forEach(WrappedGoal::stop);
            this.goalSelector.getAvailableGoals().removeIf((wrapped) -> {
                return wrapped.getGoal() instanceof AvoidEntityGoal;
            });
            this.targetSelector.getAvailableGoals().removeIf((wrapped) -> {
                return wrapped.getGoal() instanceof NearestAttackableTargetGoal;
            });
        } catch (Exception e){
            TameableRabbits.LOGGER.warn("Encountered error removing untamed goals from rabbit AI:", e);
        }
    }

    public boolean isTame() {
        return this.entityData.get(TAMED);
    }

    public void setTame(boolean tamed) {
        this.entityData.set(TAMED, tamed);
        if (tamed){
            removeUntamedGoals();
        }
    }
    @Nullable
    public UUID getTameOwnerUUID() {
        return this.entityData.get(OWNER_UUID).orElse(null);
    }

    public void setTameOwnerUUID(@Nullable UUID ownerUUID) {
        this.entityData.set(OWNER_UUID, Optional.ofNullable(ownerUUID));
    }

    @Nullable
    public LivingEntity getTameOwner() {
        try {
            UUID ownerUUID = this.getTameOwnerUUID();
            return ownerUUID == null ? null : this.level().getPlayerByUUID(ownerUUID);
        } catch (IllegalArgumentException illegalArgumentException) {
            return null;
        }
    }

    public boolean isSitting() {
        return this.entityData.get(ORDERED_TO_SIT);
    }

    public void setOrderedToSit(boolean value) {
        this.entityData.set(ORDERED_TO_SIT, value);
        Rabbit.RabbitJumpControl rabbitJumpControl = (Rabbit.RabbitJumpControl)this.jumpControl;
        rabbitJumpControl.setCanJump(value);
    }

    public boolean isValidAttackTarget(LivingEntity target) {
        return this.getVariant() == Rabbit.Variant.EVIL && (!this.isTame() || !shareOwner(target));
    }

    private boolean shareOwner(LivingEntity target) {
        if (target instanceof ModifiedToBeTameable tameable) {
            if (this.getOwnerUUID() == tameable.getOwnerUUID());
            return true;
        } else if (target instanceof TamableAnimal tameable) {
            if (this.getOwnerUUID() == tameable.getOwnerUUID());
            return true;
        } else {
            return false;
        }
    }
}
