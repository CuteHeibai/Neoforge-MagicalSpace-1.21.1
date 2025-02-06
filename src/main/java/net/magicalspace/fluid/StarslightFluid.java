
package net.magicalspace.fluid;

import net.neoforged.neoforge.fluids.BaseFlowingFluid;

import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.LiquidBlock;

import net.magicalspace.init.MagicalspaceModItems;
import net.magicalspace.init.MagicalspaceModFluids;
import net.magicalspace.init.MagicalspaceModFluidTypes;
import net.magicalspace.init.MagicalspaceModBlocks;

public abstract class StarslightFluid extends BaseFlowingFluid {
	public static final BaseFlowingFluid.Properties PROPERTIES = new BaseFlowingFluid.Properties(() -> MagicalspaceModFluidTypes.STARSLIGHT_TYPE.get(), () -> MagicalspaceModFluids.STARSLIGHT.get(),
			() -> MagicalspaceModFluids.FLOWING_STARSLIGHT.get()).explosionResistance(100f).bucket(() -> MagicalspaceModItems.STARSLIGHT_BUCKET.get()).block(() -> (LiquidBlock) MagicalspaceModBlocks.STARSLIGHT.get());

	private StarslightFluid() {
		super(PROPERTIES);
	}

	public static class Source extends StarslightFluid {
		public int getAmount(FluidState state) {
			return 8;
		}

		public boolean isSource(FluidState state) {
			return true;
		}
	}

	public static class Flowing extends StarslightFluid {
		protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
			super.createFluidStateDefinition(builder);
			builder.add(LEVEL);
		}

		public int getAmount(FluidState state) {
			return state.getValue(LEVEL);
		}

		public boolean isSource(FluidState state) {
			return false;
		}
	}
}
