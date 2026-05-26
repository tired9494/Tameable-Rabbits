package tired9494.tameable_rabbits.entity.ai;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Animal;
import tired9494.tameable_rabbits.entity.ModifiedToBeTameable;

import java.util.EnumSet;

public class ModifiedSitWhenOrderedGoal extends Goal {
    private final Animal animal;

    public ModifiedSitWhenOrderedGoal(Animal animal) {
        this.animal = animal;
        this.setFlags(EnumSet.of(Flag.JUMP, Flag.MOVE));
    }

    public boolean canContinueToUse() {
        return ((ModifiedToBeTameable)this.animal).isTame() && ((ModifiedToBeTameable)this.animal).isSitting();
    }

    public boolean canUse() {
        if (!((ModifiedToBeTameable)this.animal).isTame()) {
            return false;
        } else if (this.animal.isInWaterOrBubble()) {
            return false;
        } else if (!this.animal.onGround()) {
            return false;
        } else {
            return ((ModifiedToBeTameable)this.animal).isSitting();
        }
    }

    public void start() {
        this.animal.getNavigation().stop();
        this.animal.setJumping(false);
    }

    public void stop() {
    }
}
