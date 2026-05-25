package tired9494.tameable_rabbits.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import tired9494.tameable_rabbits.entity.ModifiedToBeTameable;

import java.util.EnumSet;

public class ModifiedOwnerHurtTargetGoal extends TargetGoal {
    private final Animal tameableAnimal;
    private LivingEntity lastTarget;
    private int timestamp;

    public ModifiedOwnerHurtTargetGoal(Animal tameableAnimal) {
        super(tameableAnimal, false);
        this.tameableAnimal = tameableAnimal;
        this.setFlags(EnumSet.of(Flag.TARGET));
    }

    public boolean canUse() {
        if (((ModifiedToBeTameable)this.tameableAnimal).isTame() && !((ModifiedToBeTameable)this.tameableAnimal).isSitting()) {
            LivingEntity owner = ((ModifiedToBeTameable)this.tameableAnimal).getTameOwner();
            if (owner == null) {
                return false;
            } else {
                this.lastTarget = owner.getLastHurtMob();
                int lastHurtTargetTimestamp = owner.getLastHurtMobTimestamp();
                return lastHurtTargetTimestamp != this.timestamp && this.canAttack(this.lastTarget, TargetingConditions.DEFAULT)
                        && ((ModifiedToBeTameable)this.tameableAnimal).isValidAttackTarget(this.lastTarget);
            }
        } else {
            return false;
        }
    }

    public void start() {
        this.tameableAnimal.setTarget(this.lastTarget);
        LivingEntity owner = ((ModifiedToBeTameable)this.tameableAnimal).getTameOwner();
        if (owner != null) {
            this.timestamp = owner.getLastHurtMobTimestamp();
        }

        super.start();
    }
}
