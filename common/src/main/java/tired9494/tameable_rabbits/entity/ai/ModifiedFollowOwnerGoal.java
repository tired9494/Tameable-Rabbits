package tired9494.tameable_rabbits.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import tired9494.tameable_rabbits.entity.ModifiedToBeTameable;

import java.util.EnumSet;

public class ModifiedFollowOwnerGoal extends Goal {
    private final Animal tameableAnimal;
    private final LevelReader level;
    private final double speedModifier;
    private final PathNavigation navigation;
    private final float stopDistance;
    private final float startDistance;
    private final boolean canFly;
    private LivingEntity owner;
    private int timeToRecalcPath;
    private float oldWaterMalus;

    public ModifiedFollowOwnerGoal(Animal animal, double speedModifier, float startDistance, float stopDistance, boolean flies) {
        this.tameableAnimal = animal;
        this.level = animal.level();
        this.speedModifier = speedModifier;
        this.navigation = animal.getNavigation();
        this.startDistance = startDistance;
        this.stopDistance = stopDistance;
        this.canFly = flies;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    public boolean canUse() {
        LivingEntity owner = ((ModifiedToBeTameable)this.tameableAnimal).getTameOwner();
        if (owner == null) {
            return false;
        } else if (owner.isSpectator()) {
            return false;
        } else if (((ModifiedToBeTameable)this.tameableAnimal).isSitting()) {
            return false;
        } else if (this.tameableAnimal.distanceToSqr(owner) < this.startDistance*this.startDistance) {
            return false;
        } else {
            this.owner = owner;
            return true;
        }
    }

    public void start() {
        this.timeToRecalcPath = 0;
        this.oldWaterMalus = this.tameableAnimal.getPathfindingMalus(PathType.WATER);
        this.tameableAnimal.setPathfindingMalus(PathType.WATER, 0.0F);
    }

    public void stop() {
        this.owner = null;
        this.navigation.stop();
        this.tameableAnimal.setPathfindingMalus(PathType.WATER, this.oldWaterMalus);
    }

    public void tick() {
        this.tameableAnimal.getLookControl().setLookAt(this.owner, 10.0F, this.tameableAnimal.getMaxHeadXRot());
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = this.adjustedTickDelay(10);
            if (!this.tameableAnimal.isLeashed() && !this.tameableAnimal.isPassenger()) {
                if (this.tameableAnimal.distanceToSqr(this.owner) >= 144.0D) {
                    this.teleportToOwner();
                } else {
                    this.navigation.moveTo(this.owner, this.speedModifier);
                }
            }
        }
    }

    private void teleportToOwner() {
        BlockPos ownerBlockPos = this.owner.blockPosition();

        for (int i = 0; i < 10; ++i) {
            int xOffset = this.randomIntInclusive(-3, 3);
            int yOffset = this.randomIntInclusive(-1, 1);
            int zOffset = this.randomIntInclusive(-3, 3);
            boolean teleported = this.maybeTeleportTo(ownerBlockPos.getX()+xOffset, ownerBlockPos.getY()+yOffset, ownerBlockPos.getZ()+zOffset);
            if (teleported) {
                return;
            }
        }
    }

    private boolean maybeTeleportTo(int x, int y, int z) {
        if (Math.abs(x - this.owner.getX()) < 2.0D && Math.abs(z - this.owner.getZ()) < 2.0D) {
            return false;
        } else if (!this.canTeleportTo(new BlockPos(x, y, z))) {
            return false;
        } else {
            this.tameableAnimal.moveTo(x+0.5D, y, z+0.5D, this.tameableAnimal.getYRot(), this.tameableAnimal.getXRot());
            this.navigation.stop();
            return true;
        }
    }

    private boolean canTeleportTo(BlockPos pos) {
        PathType blockPathType = WalkNodeEvaluator.getPathTypeStatic(this.tameableAnimal, pos.mutable());
        if (blockPathType != PathType.WALKABLE) {
            return false;
        } else {
            BlockState blockState = this.level.getBlockState(pos.below());
            return this.level.noCollision(this.tameableAnimal, this.tameableAnimal.getBoundingBox().move(pos));
        }
    }

    private int randomIntInclusive(int min, int max) {
        return this.tameableAnimal.getRandom().nextInt(max-min+1) + min;
    }
}
