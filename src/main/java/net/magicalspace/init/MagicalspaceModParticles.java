
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.magicalspace.init;

import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.magicalspace.client.particle.ParticlesStarlightParticle;
import net.magicalspace.client.particle.ParticlesDarkStarlightParticle;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MagicalspaceModParticles {
	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(MagicalspaceModParticleTypes.PARTICLES_STARLIGHT.get(), ParticlesStarlightParticle::provider);
		event.registerSpriteSet(MagicalspaceModParticleTypes.PARTICLES_DARK_STARLIGHT.get(), ParticlesDarkStarlightParticle::provider);
	}
}
