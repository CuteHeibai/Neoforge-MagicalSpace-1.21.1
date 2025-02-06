
/*
 * MCreator note: This file will be REGENERATED on each build.
 */
package net.magicalspace.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;

import net.magicalspace.fluid.StarslightFluid;
import net.magicalspace.MagicalspaceMod;

public class MagicalspaceModFluids {
	public static final DeferredRegister<Fluid> REGISTRY = DeferredRegister.create(BuiltInRegistries.FLUID, MagicalspaceMod.MODID);
	public static final DeferredHolder<Fluid, FlowingFluid> STARSLIGHT = REGISTRY.register("starslight", () -> new StarslightFluid.Source());
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_STARSLIGHT = REGISTRY.register("flowing_starslight", () -> new StarslightFluid.Flowing());

	@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class FluidsClientSideHandler {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {
			ItemBlockRenderTypes.setRenderLayer(STARSLIGHT.get(), RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(FLOWING_STARSLIGHT.get(), RenderType.translucent());
		}
	}
}
