
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.magicalspace.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.BlockItem;

import net.magicalspace.item.SwordGemStarlightItem;
import net.magicalspace.item.StarslightItem;
import net.magicalspace.item.StarlightItem;
import net.magicalspace.item.GemStarlightItem;
import net.magicalspace.item.GemMagicalStarlightItem;
import net.magicalspace.item.EmbryoSwordGemStarlightItem;
import net.magicalspace.item.DiscStarlightItem;
import net.magicalspace.item.DimensionStarlightItem;
import net.magicalspace.item.CripsyStarlightItem;
import net.magicalspace.item.AppleStarlightItem;
import net.magicalspace.MagicalspaceMod;

public class MagicalspaceModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(MagicalspaceMod.MODID);
	public static final DeferredItem<Item> GEM_STARLIGHT = REGISTRY.register("gem_starlight", GemStarlightItem::new);
	public static final DeferredItem<Item> GEM_MAGICAL_STARLIGHT = REGISTRY.register("gem_magical_starlight", GemMagicalStarlightItem::new);
	public static final DeferredItem<Item> BLOCK_MAGICAL_STARLIGHT = block(MagicalspaceModBlocks.BLOCK_MAGICAL_STARLIGHT);
	public static final DeferredItem<Item> ORE_STARLIGHT = block(MagicalspaceModBlocks.ORE_STARLIGHT);
	public static final DeferredItem<Item> BLOCK_STARLIGHT = block(MagicalspaceModBlocks.BLOCK_STARLIGHT);
	public static final DeferredItem<Item> STARLIGHT = REGISTRY.register("starlight", StarlightItem::new);
	public static final DeferredItem<Item> CRIPSY_STARLIGHT = REGISTRY.register("cripsy_starlight", CripsyStarlightItem::new);
	public static final DeferredItem<Item> SWORD_GEM_STARLIGHT = REGISTRY.register("sword_gem_starlight", SwordGemStarlightItem::new);
	public static final DeferredItem<Item> EMBRYO_SWORD_GEM_STARLIGHT = REGISTRY.register("embryo_sword_gem_starlight", EmbryoSwordGemStarlightItem::new);
	public static final DeferredItem<Item> GRASS_STARLIGHT = block(MagicalspaceModBlocks.GRASS_STARLIGHT);
	public static final DeferredItem<Item> OAK_LOG_STARLIGHT = block(MagicalspaceModBlocks.OAK_LOG_STARLIGHT);
	public static final DeferredItem<Item> LEAVES_STARLIGHT = block(MagicalspaceModBlocks.LEAVES_STARLIGHT);
	public static final DeferredItem<Item> APPLE_STARLIGHT = REGISTRY.register("apple_starlight", AppleStarlightItem::new);
	public static final DeferredItem<Item> OBSIDIAN_STARLIGHT = block(MagicalspaceModBlocks.OBSIDIAN_STARLIGHT);
	public static final DeferredItem<Item> CHEST_OBSIDIAN_CHEST = block(MagicalspaceModBlocks.CHEST_OBSIDIAN_CHEST);
	public static final DeferredItem<Item> STARLIGHT_FLOWER = doubleBlock(MagicalspaceModBlocks.STARLIGHT_FLOWER);
	public static final DeferredItem<Item> DISC_STARLIGHT = REGISTRY.register("disc_starlight", DiscStarlightItem::new);
	public static final DeferredItem<Item> CHEST_OBSIDIAN_BIG = block(MagicalspaceModBlocks.CHEST_OBSIDIAN_BIG);
	public static final DeferredItem<Item> STARSLIGHT_BUCKET = REGISTRY.register("starslight_bucket", StarslightItem::new);
	public static final DeferredItem<Item> DIMENSION_STARLIGHT = REGISTRY.register("dimension_starlight", DimensionStarlightItem::new);
	public static final DeferredItem<Item> OAK_PLANKS_STARLIGHT = block(MagicalspaceModBlocks.OAK_PLANKS_STARLIGHT);
	public static final DeferredItem<Item> STAIRS_PLANK_STARLIGHT = block(MagicalspaceModBlocks.STAIRS_PLANK_STARLIGHT);
	public static final DeferredItem<Item> DOOR_STARLIGHT = doubleBlock(MagicalspaceModBlocks.DOOR_STARLIGHT);
	public static final DeferredItem<Item> SAPLING_STARLIGHT = block(MagicalspaceModBlocks.SAPLING_STARLIGHT);
	public static final DeferredItem<Item> MOB_DARK_STARLIGHT_MAGE_SPAWN_EGG = REGISTRY.register("mob_dark_starlight_mage_spawn_egg",
			() -> new DeferredSpawnEggItem(MagicalspaceModEntities.MOB_DARK_STARLIGHT_MAGE, -16763956, -16750849, new Item.Properties()));
	public static final DeferredItem<Item> SLAB_STARLIGHT = block(MagicalspaceModBlocks.SLAB_STARLIGHT);
	public static final DeferredItem<Item> TRAP_DOOR_STARLIGHT = block(MagicalspaceModBlocks.TRAP_DOOR_STARLIGHT);
	public static final DeferredItem<Item> VILLAGER_STARLIGHT_SPAWN_EGG = REGISTRY.register("villager_starlight_spawn_egg", () -> new DeferredSpawnEggItem(MagicalspaceModEntities.VILLAGER_STARLIGHT, -10027009, -10027009, new Item.Properties()));
	public static final DeferredItem<Item> SPAWNER_STARLIGHT_VILLAGER = block(MagicalspaceModBlocks.SPAWNER_STARLIGHT_VILLAGER);

	// Start of user code block custom items
	// End of user code block custom items
	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}

	private static DeferredItem<Item> doubleBlock(DeferredHolder<Block, Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new DoubleHighBlockItem(block.get(), new Item.Properties()));
	}
}
