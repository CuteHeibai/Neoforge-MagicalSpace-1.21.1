
package net.magicalspace.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.magicalspace.entity.MobDarkStarlightMageEntity;
import net.magicalspace.client.model.Modeldark_starlight;

public class MobDarkStarlightMageRenderer extends MobRenderer<MobDarkStarlightMageEntity, Modeldark_starlight<MobDarkStarlightMageEntity>> {
	public MobDarkStarlightMageRenderer(EntityRendererProvider.Context context) {
		super(context, new Modeldark_starlight<MobDarkStarlightMageEntity>(context.bakeLayer(Modeldark_starlight.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(MobDarkStarlightMageEntity entity) {
		return ResourceLocation.parse("magicalspace:textures/entities/texture_dark_starlight_mage.png");
	}
}
