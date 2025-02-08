package net.magicalspace.init;

import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.fluids.FluidType;

import net.magicalspace.fluid.types.StarslightFluidType;
import net.magicalspace.MagicalspaceMod;

public class MagicalspaceModFluidTypes {
	public static final DeferredRegister<FluidType> REGISTRY = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, MagicalspaceMod.MODID);
	public static final DeferredHolder<FluidType, FluidType> STARSLIGHT_TYPE = REGISTRY.register("starslight", () -> new StarslightFluidType());
}
