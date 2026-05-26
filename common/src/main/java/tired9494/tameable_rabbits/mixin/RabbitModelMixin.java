package tired9494.tameable_rabbits.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.RabbitModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.animal.Rabbit;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tired9494.tameable_rabbits.entity.ModifiedToBeTameable;

@Environment(EnvType.CLIENT)
@Mixin(RabbitModel.class)
public class RabbitModelMixin {
    @Shadow
    @Final
    private ModelPart leftRearFoot;

    @Shadow
    @Final
    private ModelPart rightRearFoot;

    @Shadow
    @Final
    private ModelPart leftHaunch;

    @Shadow
    @Final
    private ModelPart rightHaunch;

    @Shadow
    @Final
    private ModelPart body;

    @Shadow
    @Final
    private ModelPart leftFrontLeg;

    @Shadow
    @Final
    private ModelPart rightFrontLeg;

    @Shadow
    @Final
    private ModelPart head;

    @Shadow
    @Final
    private ModelPart leftEar;

    @Shadow
    @Final
    private ModelPart rightEar;

    @Shadow
    @Final
    private ModelPart tail;

    @Shadow
    @Final
    private ModelPart nose;

    @Shadow
    private float jumpRotation;

    @Inject(
            at = {@At("TAIL")},
            remap = true,
            method = {"prepareMobModel(Lnet/minecraft/world/entity/animal/Rabbit;FFF)V"}
    )
    private void ta_rabbitFlopPose(Rabbit rabbit, float f, float g, float h, CallbackInfo ci) {
        if (rabbit instanceof ModifiedToBeTameable tameableRabbit) {
            if (tameableRabbit.isSitting()) {
                this.jumpRotation = -1f;
                this.leftRearFoot.setPos(4.0F, 20.75F, 0.05F);
                this.leftRearFoot.setRotation(0.8828F, 0.0288F, -1.0855F);
                this.rightRearFoot.setPos(7.0F, 23.01F, 1.7F);
                this.rightRearFoot.setRotation(1.4835F, 0.0F, -1.5708F);
                this.leftHaunch.setPos(1.0F, 19.0F, 3.7F);
                this.leftHaunch.setRotation(-0.48F, 0.0F, -1.1781F);
                this.rightHaunch.setPos(1.0F, 23.0F, 3.7F);
                this.rightHaunch.setRotation(-0.3491F, 0.0F, -1.5708F);
                this.body.setPos(0.0F, 21.0F, 7.0F);
                this.body.setRotation(-0.3491F, 0.0F, -1.5708F);
                this.leftFrontLeg.setPos(-1.0F, 20.0F, -3.0F);
                this.leftFrontLeg.setRotation(-1.2217F, 0.4363F, -1.5708F);
                this.rightFrontLeg.setPos(-3.0F, 23.0F, -2.0F);
                this.rightFrontLeg.setRotation (-1.2654F, 0.0F, -1.5708F);
                this.head.setPos(-3.0F, 22.0F, -2.0F);
                this.head.setRotation(0.0F, 0.0F, -0.3927F);
                this.leftEar.setPos(-3.0F, 22.0F, -2.0F);
                this.leftEar.setRotation(0.0F, 0.2618F, -0.3927F);
                this.rightEar.setPos(-3.0F, 22.0F, -2.0F);
                this.rightEar.setRotation(0.0F, -0.2618F, -0.3927F);
                this.tail.setPos(0.5F, 21.0F, 7.0F);
                this.tail.setRotation(-0.3491F, 0.0F, -1.5708F);
                this.nose.setPos(-3.0F, 22.0F, -2.0F);
                this.nose.setRotation(0.0F, 0.0F, -0.3927F);
                if (rabbit.getAge() < 0) {
                    Vector3f babyHeadOffset =  new Vector3f(1.0F, -3.0F, -2.0F);
                    this.head.offsetPos(babyHeadOffset);
                    this.nose.offsetPos(babyHeadOffset);
                    this.leftEar.offsetPos(babyHeadOffset);
                    this.rightEar.offsetPos(babyHeadOffset);
                }
            }
            else {
                this.leftRearFoot.setPos(3.0F, 17.5F, 3.7F);
                this.leftRearFoot.setRotation(0F, 0F, 0F);
                this.rightRearFoot.setPos(-3.0F, 17.5F, 3.7F);
                this.rightRearFoot.setRotation(0F, 0F, 0F);
                this.leftHaunch.setPos(3.0F, 17.5F, 3.7F);
                this.leftHaunch.setRotation(-0.34906584F, 0.0F, 0.0F);
                this.rightHaunch.setPos(-3.0F, 17.5F, 3.7F);
                this.rightHaunch.setRotation(-0.34906584F, 0.0F, 0.0F);
                this.body.setPos(0.0F, 19.0F, 8.0F);
                this.body.setRotation(-0.34906584F, 0.0F, 0.0F);
                this.leftFrontLeg.setPos(3.0F, 17.0F, -1.0F);
                this.leftFrontLeg.setRotation(-0.17453292F, 0.0F, 0.0F);
                this.rightFrontLeg.setPos(-3.0F, 17.0F, -1.0F);
                this.rightFrontLeg.setRotation(-0.17453292F, 0.0F, 0.0F);
                this.head.setPos(0.0F, 16.0F, -1.0F);
                this.head.setRotation(0F, 0F, 0F);
                this.leftEar.setPos(0.0F, 16.0F, -1.0F);
                this.leftEar.setRotation(0.0F, 0.2617994F, 0.0F);
                this.rightEar.setPos(0.0F, 16.0F, -1.0F);
                this.rightEar.setRotation(0.0F, -0.2617994F, 0.0F);
                this.tail.setPos(0.0F, 20.0F, 7.0F);
                this.tail.setRotation(-0.3490659F, 0.0F, 0.0F);
                this.nose.setPos(0.0F, 16.0F, -1.0F);
                this.nose.setRotation(0F, 0F, 0F);
            }
        }
    }
    @Inject(
            at = {@At("TAIL")},
            remap = true,
            method = {"setupAnim(Lnet/minecraft/world/entity/animal/Rabbit;FFFFF)V"}
    )
    private void ta_fixRabbitLegs(Rabbit rabbit, float f, float g, float h, float i, float j, CallbackInfo ci) {
        if (rabbit instanceof ModifiedToBeTameable tameableRabbit) {
            if (tameableRabbit.isSitting()) {
                this.leftRearFoot.xRot = 0.8828F;

                this.rightRearFoot.xRot = 1.4835F;

                this.leftHaunch.xRot = -0.48F;

                this.rightHaunch.xRot = -0.0572F;

                this.leftFrontLeg.xRot = -1.2217F;

                this.rightFrontLeg.xRot = -1.2654F;
            }
        }
    }
}
