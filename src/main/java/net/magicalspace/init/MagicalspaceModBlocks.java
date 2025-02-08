package net.magicalspace.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;

import net.minecraft.world.level.block.Block;

import net.magicalspace.block.TrapDoorStarlightBlock;
import net.magicalspace.block.StarslightBlock;
import net.magicalspace.block.StarlightFlowerBlock;
import net.magicalspace.block.StairsPlankStarlightBlock;
import net.magicalspace.block.SpawnerStarlightVillagerBlock;
import net.magicalspace.block.SlabStarlightBlock;
import net.magicalspace.block.SaplingStarlightBlock;
import net.magicalspace.block.OreStarlightBlock;
import net.magicalspace.block.ObsidianStarlightBlock;
import net.magicalspace.block.OakPlanksStarlightBlock;
import net.magicalspace.block.OakLogStarlightBlock;
import net.magicalspace.block.LeavesStarlightBlock;
import net.magicalspace.block.GrassStarlightBlock;
import net.magicalspace.block.DoorStarlightBlock;
import net.magicalspace.block.DimensionStarlightPortalBlock;
import net.magicalspace.block.ChestObsidianChestBlock;
import net.magicalspace.block.ChestObsidianBigBlock;
import net.magicalspace.block.BlockStarlightBlock;
import net.magicalspace.block.BlockMagicalStarlightBlock;
import net.magicalspace.MagicalspaceMod;

public class MagicalspaceModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(MagicalspaceMod.MODID);
	public static final DeferredBlock<Block> BLOCK_MAGICAL_STARLIGHT = REGISTRY.register("block_magical_starlight", BlockMagicalStarlightBlock::new);
	public static final DeferredBlock<Block> ORE_STARLIGHT = REGISTRY.register("ore_starlight", OreStarlightBlock::new);
	public static final DeferredBlock<Block> BLOCK_STARLIGHT = REGISTRY.register("block_starlight", BlockStarlightBlock::new);
	public static final DeferredBlock<Block> GRASS_STARLIGHT = REGISTRY.register("grass_starlight", GrassStarlightBlock::new);
	public static final DeferredBlock<Block> OAK_LOG_STARLIGHT = REGISTRY.register("oak_log_starlight", OakLogStarlightBlock::new);
	public static final DeferredBlock<Block> LEAVES_STARLIGHT = REGISTRY.register("leaves_starlight", LeavesStarlightBlock::new);
	public static final DeferredBlock<Block> OBSIDIAN_STARLIGHT = REGISTRY.register("obsidian_starlight", ObsidianStarlightBlock::new);
	public static final DeferredBlock<Block> CHEST_OBSIDIAN_CHEST = REGISTRY.register("chest_obsidian_chest", ChestObsidianChestBlock::new);
	public static final DeferredBlock<Block> STARLIGHT_FLOWER = REGISTRY.register("starlight_flower", StarlightFlowerBlock::new);
	public static final DeferredBlock<Block> CHEST_OBSIDIAN_BIG = REGISTRY.register("chest_obsidian_big", ChestObsidianBigBlock::new);
	public static final DeferredBlock<Block> STARSLIGHT = REGISTRY.register("starslight", StarslightBlock::new);
	public static final DeferredBlock<Block> DIMENSION_STARLIGHT_PORTAL = REGISTRY.register("dimension_starlight_portal", DimensionStarlightPortalBlock::new);
	public static final DeferredBlock<Block> OAK_PLANKS_STARLIGHT = REGISTRY.register("oak_planks_starlight", OakPlanksStarlightBlock::new);
	public static final DeferredBlock<Block> STAIRS_PLANK_STARLIGHT = REGISTRY.register("stairs_plank_starlight", StairsPlankStarlightBlock::new);
	public static final DeferredBlock<Block> DOOR_STARLIGHT = REGISTRY.register("door_starlight", DoorStarlightBlock::new);
	public static final DeferredBlock<Block> SAPLING_STARLIGHT = REGISTRY.register("sapling_starlight", SaplingStarlightBlock::new);
	public static final DeferredBlock<Block> SLAB_STARLIGHT = REGISTRY.register("slab_starlight", SlabStarlightBlock::new);
	public static final DeferredBlock<Block> TRAP_DOOR_STARLIGHT = REGISTRY.register("trap_door_starlight", TrapDoorStarlightBlock::new);
	public static final DeferredBlock<Block> SPAWNER_STARLIGHT_VILLAGER = REGISTRY.register("spawner_starlight_villager", SpawnerStarlightVillagerBlock::new);
	// Start of user code block custom blocks
	// End of user code block custom blocks
}
