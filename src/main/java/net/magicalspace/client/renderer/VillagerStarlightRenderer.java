
package net.magicalspace.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.magicalspace.entity.VillagerStarlightEntity;
import net.magicalspace.client.model.animations.starlight_villagerAnimation;
import net.magicalspace.client.model.Modelstarlight_villager;

public class VillagerStarlightRenderer extends MobRenderer<VillagerStarlightEntity, Modelstarlight_villager<VillagerStarlightEntity>> {
	public VillagerStarlightRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelstarlight_villager.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(VillagerStarlightEntity entity) {
		return ResourceLocation.parse("magicalspace:textures/entities/texture_starlight_villager.png");
	}

	private static final class AnimatedModel extends Modelstarlight_villager<VillagerStarlightEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<VillagerStarlightEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(VillagerStarlightEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animateWalk(starlight_villagerAnimation.walking, limbSwing, limbSwingAmount, 1f, 2f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(VillagerStarlightEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}