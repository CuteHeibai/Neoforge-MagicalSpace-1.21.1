package net.magicalspace.client.model;

import net.minecraft.world.entity.Entity;
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
public class Modelstarlight_villager<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("magicalspace", "modelstarlight_villager"), "main");
	public final ModelPart all;
	public final ModelPart leg_right;
	public final ModelPart leg_left;
	public final ModelPart body;
	public final ModelPart head;
	public final ModelPart arm_right;
	public final ModelPart arm_left;

	public Modelstarlight_villager(ModelPart root) {
		this.all = root.getChild("all");
		this.leg_right = this.all.getChild("leg_right");
		this.leg_left = this.all.getChild("leg_left");
		this.body = this.all.getChild("body");
		this.head = this.all.getChild("head");
		this.arm_right = this.all.getChild("arm_right");
		this.arm_left = this.all.getChild("arm_left");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 1.5708F, 0.0F));
		PartDefinition leg_right = all.addOrReplaceChild("leg_right", CubeListBuilder.create().texOffs(0, 50).addBox(-2.0F, 1.0F, -2.0F, 5.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -12.0F, -3.0F));
		PartDefinition leg_left = all.addOrReplaceChild("leg_left", CubeListBuilder.create().texOffs(54, 0).addBox(-3.0F, 1.0F, -2.0F, 5.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 1.0F));
		PartDefinition body = all.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 28).addBox(-3.0F, -7.0F, -4.0F, 5.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -18.0F, -1.0F));
		PartDefinition head = all.addOrReplaceChild("head", CubeListBuilder.create().texOffs(26, 28).addBox(-4.0F, -9.0F, -4.0F, 7.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
				.addBox(-7.25F, -9.25F, -6.5F, 14.0F, 2.0F, 13.0F, new CubeDeformation(0.0F)).texOffs(0, 15).addBox(-5.25F, -13.5F, -4.5F, 10.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, -1.0F));
		PartDefinition arm_right = all.addOrReplaceChild("arm_right", CubeListBuilder.create().texOffs(26, 44).addBox(-3.0F, -1.0F, -2.0F, 5.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, -7.0F));
		PartDefinition arm_left = all.addOrReplaceChild("arm_left", CubeListBuilder.create().texOffs(44, 44).addBox(-3.0F, -1.0F, -2.0F, 5.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 5.0F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int rgb) {
		all.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
	}
}
