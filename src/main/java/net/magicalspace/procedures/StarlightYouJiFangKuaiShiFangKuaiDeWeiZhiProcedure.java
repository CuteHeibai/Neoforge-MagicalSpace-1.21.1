package net.magicalspace.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

import net.magicalspace.init.MagicalspaceModParticleTypes;
import net.magicalspace.init.MagicalspaceModBlocks;

public class StarlightYouJiFangKuaiShiFangKuaiDeWeiZhiProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if ((entity instanceof Player _plr ? _plr.experienceLevel : 0) < 1) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal((Component.translatable("info.notenoughexp").getString())), false);
		} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == MagicalspaceModBlocks.BLOCK_STARLIGHT.get()) {
			{
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockState _bs = MagicalspaceModBlocks.BLOCK_MAGICAL_STARLIGHT.get().defaultBlockState();
				BlockState _bso = world.getBlockState(_bp);
				for (Property<?> _propertyOld : _bso.getProperties()) {
					Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
					if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
						try {
							_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
						} catch (Exception e) {
						}
				}
				world.setBlock(_bp, _bs, 3);
			}
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (MagicalspaceModParticleTypes.PARTICLES_STARLIGHT.get()), x, y, z, 20, 0.1, 0.1, 0.1, 0.2);
			if (world instanceof ServerLevel _level) {
				itemstack.hurtAndBreak(1, _level, null, _stkprov -> {
				});
			}
			if (entity instanceof Player _player)
				_player.getCooldowns().addCooldown(itemstack.getItem(), 300);
			if (entity instanceof Player _player)
				_player.giveExperienceLevels(-(1));
		}
	}
}
