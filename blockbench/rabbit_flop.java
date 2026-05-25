// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class rabbit_flop<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "rabbit_flop"), "main");
	private final ModelPart rearFootLeft;
	private final ModelPart rearFootRight;
	private final ModelPart haunchLeft;
	private final ModelPart haunchRight;
	private final ModelPart body;
	private final ModelPart frontLegLeft;
	private final ModelPart frontLegRight;
	private final ModelPart head;
	private final ModelPart earRight;
	private final ModelPart earLeft;
	private final ModelPart tail;
	private final ModelPart nose;
	private final ModelPart bb_main;

	public rabbit_flop(ModelPart root) {
		this.rearFootLeft = root.getChild("rearFootLeft");
		this.rearFootRight = root.getChild("rearFootRight");
		this.haunchLeft = root.getChild("haunchLeft");
		this.haunchRight = root.getChild("haunchRight");
		this.body = root.getChild("body");
		this.frontLegLeft = root.getChild("frontLegLeft");
		this.frontLegRight = root.getChild("frontLegRight");
		this.head = root.getChild("head");
		this.earRight = root.getChild("earRight");
		this.earLeft = root.getChild("earLeft");
		this.tail = root.getChild("tail");
		this.nose = root.getChild("nose");
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition rearFootLeft = partdefinition.addOrReplaceChild("rearFootLeft", CubeListBuilder.create().texOffs(8, 24).mirror().addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.8965F, 22.3219F, 5.0591F, 0.9813F, -0.2388F, -1.0195F));

		PartDefinition rearFootRight = partdefinition.addOrReplaceChild("rearFootRight", CubeListBuilder.create().texOffs(26, 24).mirror().addBox(0.0F, 3.5F, -3.7F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.5F, 24.25F, 3.45F, 1.2955F, -0.013F, -1.59F));

		PartDefinition haunchLeft = partdefinition.addOrReplaceChild("haunchLeft", CubeListBuilder.create().texOffs(16, 15).mirror().addBox(-0.8303F, -0.0804F, 0.0264F, 2.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, 19.5F, 3.7F, 0.5198F, -0.2411F, -1.2561F));

		PartDefinition haunchRight = partdefinition.addOrReplaceChild("haunchRight", CubeListBuilder.create().texOffs(30, 15).mirror().addBox(0.0F, -2.0F, 0.0F, 2.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.5F, 24.0F, 3.2F, -0.0572F, -0.0232F, -1.5702F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.998F, -2.02F, -9.9151F, 6.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 21.0F, 8.0F, -0.2754F, -0.0225F, -1.5651F));

		PartDefinition frontLegLeft = partdefinition.addOrReplaceChild("frontLegLeft", CubeListBuilder.create().texOffs(8, 15).mirror().addBox(2.0F, -2.0F, 0.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.25F, 23.5F, -1.25F, -1.3428F, 0.7304F, -2.0451F));

		PartDefinition frontLegRight = partdefinition.addOrReplaceChild("frontLegRight", CubeListBuilder.create().texOffs(0, 15).mirror().addBox(-0.979F, 0.1779F, -0.9733F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 23.0F, -1.0F, -1.0172F, -0.0092F, -1.5495F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(-2.5F, -4.0F, -5.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 23.0F, -2.0F, -0.0338F, -0.2411F, -0.4776F));

		PartDefinition earRight = partdefinition.addOrReplaceChild("earRight", CubeListBuilder.create().texOffs(58, 0).mirror().addBox(-2.5F, -9.0F, -1.0F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 23.0F, -2.0F, -0.0374F, -0.5027F, -0.4677F));

		PartDefinition earLeft = partdefinition.addOrReplaceChild("earLeft", CubeListBuilder.create().texOffs(52, 0).mirror().addBox(0.5F, -9.0F, -1.0F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 23.0F, -2.0F, -0.0328F, 0.0205F, -0.4864F));

		PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(52, 6).mirror().addBox(-0.5F, -2.5F, 0.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 22.0F, 7.0F, -0.0572F, -0.0232F, -1.5702F));

		PartDefinition nose = partdefinition.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(32, 9).mirror().addBox(-0.5F, -2.5F, -5.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 23.0F, -2.0F, -0.0338F, -0.2411F, -0.4776F));

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition tailBroken_r1 = bb_main.addOrReplaceChild("tailBroken_r1", CubeListBuilder.create().texOffs(52, 6).mirror().addBox(-1.5F, -5.5F, 7.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.5933F, -3.1652F, -0.2153F, -0.0572F, -0.0232F, -1.5702F));

		PartDefinition frontLegRightBroken_r1 = bb_main.addOrReplaceChild("frontLegRightBroken_r1", CubeListBuilder.create().texOffs(0, 15).mirror().addBox(-4.0F, -7.0F, -2.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5946F, -3.9528F, -6.401F, -1.0172F, -0.0092F, -1.5495F));

		PartDefinition frontLegLeftBroken_r1 = bb_main.addOrReplaceChild("frontLegLeftBroken_r1", CubeListBuilder.create().texOffs(8, 15).mirror().addBox(2.0F, -7.0F, -2.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.7747F, 0.818F, -4.2143F, -1.4181F, 0.7696F, -2.0936F));

		PartDefinition haunchRightBroken_r1 = bb_main.addOrReplaceChild("haunchRightBroken_r1", CubeListBuilder.create().texOffs(30, 15).mirror().addBox(-3.5F, -7.0F, 3.7F, 2.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.2707F, -3.5934F, -0.6164F, -0.0572F, -0.0232F, -1.5702F));

		PartDefinition haunchLeftBroken_r1 = bb_main.addOrReplaceChild("haunchLeftBroken_r1", CubeListBuilder.create().texOffs(16, 15).mirror().addBox(2.0F, -6.5F, 3.7F, 2.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(8.209F, 0.5891F, 3.0007F, 0.5198F, -0.2411F, -1.2561F));

		PartDefinition rearFootRightBroken_r1 = bb_main.addOrReplaceChild("rearFootRightBroken_r1", CubeListBuilder.create().texOffs(26, 24).mirror().addBox(-3.5F, -1.0F, 0.0F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(9.1298F, -3.4173F, 6.8898F, 1.2955F, -0.013F, -1.59F));

		PartDefinition rearFootLeftBroken_r1 = bb_main.addOrReplaceChild("rearFootLeftBroken_r1", CubeListBuilder.create().texOffs(8, 24).mirror().addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.8173F, -1.7132F, 4.9186F, 0.9984F, -0.2796F, -1.0277F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		rearFootLeft.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rearFootRight.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		haunchLeft.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		haunchRight.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		frontLegLeft.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		frontLegRight.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		earRight.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		earLeft.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		nose.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}