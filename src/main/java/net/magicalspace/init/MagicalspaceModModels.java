package net.magicalspace.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.magicalspace.client.model.Modelstarlight_villager;
import net.magicalspace.client.model.Modeldark_starlight;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class MagicalspaceModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(Modeldark_starlight.LAYER_LOCATION, Modeldark_starlight::createBodyLayer);
		event.registerLayerDefinition(Modelstarlight_villager.LAYER_LOCATION, Modelstarlight_villager::createBodyLayer);
	}
}
