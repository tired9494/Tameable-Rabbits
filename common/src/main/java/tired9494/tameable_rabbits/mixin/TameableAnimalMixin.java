package tired9494.tameable_rabbits.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tired9494.tameable_rabbits.entity.ModifiedToBeTameable;

import java.util.UUID;

@Mixin(TamableAnimal.class)
public abstract class TameableAnimalMixin extends Animal {
    @Shadow
    @Nullable
    public abstract UUID getOwnerUUID();

    // CREDITS to AlexModGuy's DomesticationInnovation
    protected TameableAnimalMixin(EntityType<? extends Animal> animal, Level level) {
        super(animal, level);
    }

    @Inject(
            method = {"Lnet/minecraft/world/entity/TamableAnimal;isAlliedTo(Lnet/minecraft/world/entity/Entity;)Z"},
            remap = true,
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true
    )
    private void ta_isAlliedTo(Entity other, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (other instanceof ModifiedToBeTameable tameable) {
            if (this.getOwnerUUID() == tameable.getOwnerUUID());
            callbackInfoReturnable.setReturnValue(true);
        } else if (other instanceof TamableAnimal tameable) {
            if (this.getOwnerUUID() == tameable.getOwnerUUID());
            callbackInfoReturnable.setReturnValue(true);
        }
    }
}
