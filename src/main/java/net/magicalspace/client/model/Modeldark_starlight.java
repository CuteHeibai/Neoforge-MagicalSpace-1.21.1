package net.magicalspace.client.model;

import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class Modeldark_starlight<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("magicalspace", "modeldark_starlight"), "main");
	public final ModelPart all;
	public final ModelPart leg_right;
	public final ModelPart leg_left;
	public final ModelPart body;
	public final ModelPart head;
	public final ModelPart arm_left;
	public final ModelPart arm_right;

	public Modeldark_starlight(ModelPart root) {
		this.all = root.getChild("all");
		this.leg_right = this.all.getChild("leg_right");
		this.leg_left = this.all.getChild("leg_left");
		this.body = this.all.getChild("body");
		this.head = this.all.getChild("head");
		this.arm_left = this.all.getChild("arm_left");
		this.arm_right = this.all.getChild("arm_right");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition leg_right = all.addOrReplaceChild("leg_right", CubeListBuilder.create().texOffs(20, 52).addBox(-4.75F, -1.0029F, -2.6309F, 4.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));
		PartDefinition leg_left = all.addOrReplaceChild("leg_left", CubeListBuilder.create().texOffs(52, 40).addBox(0.0F, -2.0F, -2.5F, 4.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));
		PartDefinition body = all.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -19.0F, -3.0F, 9.0F, 16.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition head = all.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 22).addBox(-4.5F, -27.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition arm_left = all.addOrReplaceChild("arm_left",
				CubeListBuilder.create().texOffs(30, 9).addBox(-2.75F, 1.0F, -3.0F, 5.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(32, 31).addBox(-3.5F, 8.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(0, 47)
						.addBox(-2.75F, 11.05F, -2.75F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(30, 0).addBox(-3.25F, -2.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(7.0F, -16.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition arm_right = all.addOrReplaceChild("arm_right", CubeListBuilder.create().texOffs(0, 38).addBox(-3.75F, 8.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(50, 9)
				.addBox(-3.75F, 11.0F, -2.25F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(32, 22).addBox(-3.75F, -2.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -16.0F, 0.0F));
		PartDefinition staff_head_r1 = arm_right.addOrReplaceChild("staff_head_r1", CubeListBuilder.create().texOffs(52, 48).addBox(-1.25F, -4.0F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.25F, 0.4326F, -18.2387F, -0.6148F, 0.0542F, -0.0172F));
		PartDefinition staff_r1 = arm_right.addOrReplaceChild("staff_r1", CubeListBuilder.create().texOffs(44, 40).addBox(-1.0F, -21.2915F, 0.0752F, 2.0F, 20.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.75F, 12.9326F, 0.0113F, 0.9163F, 0.0F, 0.0F));
		PartDefinition arm_r1 = arm_right.addOrReplaceChild("arm_r1", CubeListBuilder.create().texOffs(24, 40).addBox(-3.0F, 2.0F, -2.0F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.75F, -1.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int rgb) {
		all.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.leg_right.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);
		this.arm_right.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
		this.leg_left.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		this.arm_left.xRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
	}
}
