package net.magicalspace.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;

import net.magicalspace.init.MagicalspaceModParticleTypes;

public class ParticlesDanSheWuFeiXingShiMeiKeFaShengProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (MagicalspaceModParticleTypes.PARTICLES_DARK_STARLIGHT.get()), x, y, z, 50, 0, 0, 0, 0.5);
	}
}
