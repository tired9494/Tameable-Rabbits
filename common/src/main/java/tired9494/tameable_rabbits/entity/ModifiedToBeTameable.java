package tired9494.tameable_rabbits.entity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface ModifiedToBeTameable extends OwnableEntity {
    // CREDITS to AlexModGuy's DomesticationInnovation
    boolean isTame();
    void setTame(boolean value);
    @Nullable
    UUID getTameOwnerUUID();
    void setTameOwnerUUID(@Nullable UUID uuid);
    @Nullable
    LivingEntity getTameOwner();

    boolean isSitting();

    boolean isValidAttackTarget(LivingEntity target);

    @Nullable
    default UUID getOwnerUUID() {
        return getTameOwnerUUID();
    }
}
