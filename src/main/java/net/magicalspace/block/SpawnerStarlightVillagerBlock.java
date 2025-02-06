
package net.magicalspace.block;

import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import net.magicalspace.procedures.SpawnerStarlightVillagerZaiKeGengXinShiProcedure;

public class SpawnerStarlightVillagerBlock extends Block {
	public SpawnerStarlightVillagerBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(-1, 3600000).randomTicks().pushReaction(PushReaction.BLOCK));
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}

	@Override
	public void randomTick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.randomTick(blockstate, world, pos, random);
		SpawnerStarlightVillagerZaiKeGengXinShiProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}
}
