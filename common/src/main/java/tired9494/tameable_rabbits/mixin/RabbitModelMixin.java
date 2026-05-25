package tired9494.tameable_rabbits.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.RabbitModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.animal.Rabbit;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tired9494.tameable_rabbits.entity.ModifiedToBeTameable;

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
                this.leftRearFoot.setPos(4.75F, 21.25F, 1.7F);
                this.leftRearFoot.setRotation(0.9813F, -0.2388F, -1.0195F);
                this.rightRearFoot.setPos(4.5F, 24.25F, 3.45F);
                this.rightRearFoot.setRotation(1.2955F, -0.013F, -1.59F);
                this.leftHaunch.setPos(2.0F, 19.5F, 3.7F);
                this.leftHaunch.setRotation(0.5198F, -0.2411F, -1.2561F);
                this.rightHaunch.setPos(1.5F, 24.0F, 3.2F);
                this.rightHaunch.setRotation(-0.0572F, -0.0232F, -1.5702F);
                this.body.setPos(-1.0F, 21.0F, 8.0F);
                this.body.setRotation(-0.2754F, -0.0225F, -1.5651F);
                this.leftFrontLeg.setPos(-0.25F, 23.5F, -1.25F);
                this.leftFrontLeg.setRotation(-1.3428F, 0.7304F, -2.0451F);
                this.rightFrontLeg.setPos(-3.0F, 23.0F, -2.0F);
                this.rightFrontLeg.setRotation (-1.2654F, 0.0F, -1.5708F);
                this.head.setPos(-3.0F, 23.0F, -2.0F);
                this.head.setRotation(-0.0338F, -0.2411F, -0.4776F);
                this.leftEar.setPos(-3.0F, 23.0F, -2.0F);
                this.leftEar.setRotation(-0.0328F, 0.0205F, -0.4864F);
                this.rightEar.setPos(-3.0F, 23.0F, -2.0F);
                this.rightEar.setRotation(-0.0374F, -0.5027F, -0.4677F);
                this.tail.setPos(0.0F, 22.0F, 7.0F);
                this.tail.setRotation(-0.0572F, -0.0232F, -1.5702F);
                this.nose.setPos(-3.0F, 23.0F, -2.0F);
                this.nose.setRotation(-0.0338F, -0.2411F, -0.4776F);
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
                this.leftRearFoot.xRot = 0.9813F;

                this.rightRearFoot.xRot = 1.2955F;

                this.leftHaunch.xRot = 0.5198F;

                this.rightHaunch.xRot = -0.0572F;

                this.leftFrontLeg.xRot = -1.3428F;

                this.rightFrontLeg.xRot = -1.2654F;
            }
        }
    }
}
