
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.magicalspace.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.magicalspace.client.renderer.VillagerStarlightRenderer;
import net.magicalspace.client.renderer.MobDarkStarlightMageRenderer;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MagicalspaceModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(MagicalspaceModEntities.MOB_DARK_STARLIGHT_MAGE.get(), MobDarkStarlightMageRenderer::new);
		event.registerEntityRenderer(MagicalspaceModEntities.VILLAGER_STARLIGHT.get(), VillagerStarlightRenderer::new);
	}
}
