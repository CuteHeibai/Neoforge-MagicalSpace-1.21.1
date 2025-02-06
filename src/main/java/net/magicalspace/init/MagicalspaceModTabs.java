
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.magicalspace.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.magicalspace.MagicalspaceMod;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class MagicalspaceModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MagicalspaceMod.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAGIC_SPACE = REGISTRY.register("magic_space",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.magicalspace.magic_space")).icon(() -> new ItemStack(MagicalspaceModItems.GEM_STARLIGHT.get())).displayItems((parameters, tabData) -> {
				tabData.accept(MagicalspaceModItems.GEM_STARLIGHT.get());
				tabData.accept(MagicalspaceModItems.GEM_MAGICAL_STARLIGHT.get());
				tabData.accept(MagicalspaceModBlocks.BLOCK_MAGICAL_STARLIGHT.get().asItem());
				tabData.accept(MagicalspaceModBlocks.ORE_STARLIGHT.get().asItem());
				tabData.accept(MagicalspaceModBlocks.BLOCK_STARLIGHT.get().asItem());
				tabData.accept(MagicalspaceModItems.STARLIGHT.get());
				tabData.accept(MagicalspaceModItems.CRIPSY_STARLIGHT.get());
				tabData.accept(MagicalspaceModItems.SWORD_GEM_STARLIGHT.get());
				tabData.accept(MagicalspaceModItems.EMBRYO_SWORD_GEM_STARLIGHT.get());
				tabData.accept(MagicalspaceModBlocks.GRASS_STARLIGHT.get().asItem());
				tabData.accept(MagicalspaceModBlocks.OAK_LOG_STARLIGHT.get().asItem());
				tabData.accept(MagicalspaceModBlocks.LEAVES_STARLIGHT.get().asItem());
				tabData.accept(MagicalspaceModItems.APPLE_STARLIGHT.get());
				tabData.accept(MagicalspaceModBlocks.OBSIDIAN_STARLIGHT.get().asItem());
				tabData.accept(MagicalspaceModBlocks.CHEST_OBSIDIAN_CHEST.get().asItem());
				tabData.accept(MagicalspaceModBlocks.STARLIGHT_FLOWER.get().asItem());
				tabData.accept(MagicalspaceModItems.DISC_STARLIGHT.get());
				tabData.accept(MagicalspaceModBlocks.CHEST_OBSIDIAN_BIG.get().asItem());
				tabData.accept(MagicalspaceModItems.STARSLIGHT_BUCKET.get());
				tabData.accept(MagicalspaceModItems.DIMENSION_STARLIGHT.get());
				tabData.accept(MagicalspaceModBlocks.OAK_PLANKS_STARLIGHT.get().asItem());
				tabData.accept(MagicalspaceModBlocks.STAIRS_PLANK_STARLIGHT.get().asItem());
				tabData.accept(MagicalspaceModBlocks.DOOR_STARLIGHT.get().asItem());
				tabData.accept(MagicalspaceModBlocks.SAPLING_STARLIGHT.get().asItem());
				tabData.accept(MagicalspaceModItems.MOB_DARK_STARLIGHT_MAGE_SPAWN_EGG.get());
				tabData.accept(MagicalspaceModBlocks.SLAB_STARLIGHT.get().asItem());
				tabData.accept(MagicalspaceModBlocks.TRAP_DOOR_STARLIGHT.get().asItem());
				tabData.accept(MagicalspaceModItems.VILLAGER_STARLIGHT_SPAWN_EGG.get());
			}).build());

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.INGREDIENTS) {
			tabData.accept(MagicalspaceModBlocks.ORE_STARLIGHT.get().asItem());
			tabData.accept(MagicalspaceModItems.STARLIGHT.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.COMBAT) {
			tabData.accept(MagicalspaceModItems.SWORD_GEM_STARLIGHT.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
			tabData.accept(MagicalspaceModBlocks.GRASS_STARLIGHT.get().asItem());
			tabData.accept(MagicalspaceModBlocks.OAK_LOG_STARLIGHT.get().asItem());
			tabData.accept(MagicalspaceModBlocks.STARLIGHT_FLOWER.get().asItem());
			tabData.accept(MagicalspaceModBlocks.SAPLING_STARLIGHT.get().asItem());
		} else if (tabData.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			tabData.accept(MagicalspaceModItems.DIMENSION_STARLIGHT.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
			tabData.accept(MagicalspaceModBlocks.OAK_PLANKS_STARLIGHT.get().asItem());
			tabData.accept(MagicalspaceModBlocks.STAIRS_PLANK_STARLIGHT.get().asItem());
		} else if (tabData.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
			tabData.accept(MagicalspaceModItems.MOB_DARK_STARLIGHT_MAGE_SPAWN_EGG.get());
			tabData.accept(MagicalspaceModItems.VILLAGER_STARLIGHT_SPAWN_EGG.get());
		}
	}
}
