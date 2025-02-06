package net.magicalspace.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;

public class SwordGemStarlightShengWuBeiGongJuJiZhongShiProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) >= 30) {
			entity.hurt(new DamageSource(world.holderOrThrow(DamageTypes.IN_WALL)), 20);
		}
	}
}
