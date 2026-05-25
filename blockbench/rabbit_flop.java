// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class rabbit_flop<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "rabbit_flop"), "main");
	private final ModelPart bb_main;

	public rabbit_flop(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition nose_r1 = bb_main.addOrReplaceChild("nose_r1", CubeListBuilder.create().texOffs(32, 9).mirror().addBox(-0.5F, -10.5F, -6.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(32, 0).mirror().addBox(-2.5F, -12.0F, -6.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5361F, 6.2107F, -1.2916F, -0.0338F, -0.2411F, -0.4776F));

		PartDefinition tail_r1 = bb_main.addOrReplaceChild("tail_r1", CubeListBuilder.create().texOffs(52, 6).mirror().addBox(-1.5F, -5.5F, 7.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.5933F, -3.1652F, -0.2153F, -0.0572F, -0.0232F, -1.5702F));

		PartDefinition earLeft_r1 = bb_main.addOrReplaceChild("earLeft_r1", CubeListBuilder.create().texOffs(52, 0).mirror().addBox(0.5F, -17.0F, -2.0F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.766F, 6.0904F, -1.2629F, -0.0328F, 0.0205F, -0.4864F));

		PartDefinition earRight_r1 = bb_main.addOrReplaceChild("earRight_r1", CubeListBuilder.create().texOffs(58, 0).mirror().addBox(-2.5F, -17.0F, -2.0F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.3196F, 6.3215F, -1.3865F, -0.0374F, -0.5027F, -0.4677F));

		PartDefinition frontLegRight_r1 = bb_main.addOrReplaceChild("frontLegRight_r1", CubeListBuilder.create().texOffs(0, 15).mirror().addBox(-4.0F, -7.0F, -2.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5946F, -3.9528F, -6.401F, -1.0172F, -0.0092F, -1.5495F));

		PartDefinition frontLegLeft_r1 = bb_main.addOrReplaceChild("frontLegLeft_r1", CubeListBuilder.create().texOffs(8, 15).mirror().addBox(2.0F, -7.0F, -2.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.7747F, 0.818F, -4.2143F, -1.4181F, 0.7696F, -2.0936F));

		PartDefinition body_r1 = bb_main.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.998F, -7.02F, -1.9151F, 6.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.6369F, -3.1891F, -1.056F, -0.2754F, -0.0225F, -1.5652F));

		PartDefinition haunchRight_r1 = bb_main.addOrReplaceChild("haunchRight_r1", CubeListBuilder.create().texOffs(30, 15).mirror().addBox(-3.5F, -7.0F, 3.7F, 2.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.2707F, -3.5934F, -0.6164F, -0.0572F, -0.0232F, -1.5702F));

		PartDefinition haunchLeft_r1 = bb_main.addOrReplaceChild("haunchLeft_r1", CubeListBuilder.create().texOffs(16, 15).mirror().addBox(2.0F, -6.5F, 3.7F, 2.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(8.209F, 0.5891F, 3.0007F, 0.5198F, -0.2411F, -1.2561F));

		PartDefinition rearFootRight_r1 = bb_main.addOrReplaceChild("rearFootRight_r1", CubeListBuilder.create().texOffs(26, 24).mirror().addBox(-3.5F, -1.0F, 0.0F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(9.1298F, -3.4173F, 6.8898F, 1.2955F, -0.013F, -1.59F));

		PartDefinition rearFootLeft_r1 = bb_main.addOrReplaceChild("rearFootLeft_r1", CubeListBuilder.create().texOffs(8, 24).mirror().addBox(2.5F, -1.0F, 0.0F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(8.0398F, 2.4787F, 2.5345F, 0.9984F, -0.2796F, -1.0277F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}