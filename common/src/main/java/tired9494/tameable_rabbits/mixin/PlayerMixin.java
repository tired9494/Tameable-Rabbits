package tired9494.tameable_rabbits.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import tired9494.tameable_rabbits.entity.ModifiedToBeTameable;

@Mixin(Player.class)
public class PlayerMixin {
    @Redirect(
            method = {"attack"},
            remap = true,
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;isAlliedTo(Lnet/minecraft/world/entity/Entity;)Z"
            )
    )
    private boolean ta_onSweepAttack_isAlliedTo(Player player, Entity entity) {
        if (player.isAlliedTo(entity)) {
            return true;
        } else if (entity instanceof ModifiedToBeTameable modifiedToBeTameable) {
            return modifiedToBeTameable.getTameOwner() == player;
        }
        return false;
    }
}
