package net.magicalspace.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;

import net.magicalspace.MagicalspaceMod;

public class MagicalspaceModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(Registries.PARTICLE_TYPE, MagicalspaceMod.MODID);
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLES_STARLIGHT = REGISTRY.register("particles_starlight", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARTICLES_DARK_STARLIGHT = REGISTRY.register("particles_dark_starlight", () -> new SimpleParticleType(false));
}
