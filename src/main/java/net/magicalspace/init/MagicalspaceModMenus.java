
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.magicalspace.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.core.registries.Registries;

import net.magicalspace.world.inventory.GuiChestObsidianMenu;
import net.magicalspace.world.inventory.ChestBigObsidianMenu;
import net.magicalspace.MagicalspaceMod;

public class MagicalspaceModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, MagicalspaceMod.MODID);
	public static final DeferredHolder<MenuType<?>, MenuType<GuiChestObsidianMenu>> GUI_CHEST_OBSIDIAN = REGISTRY.register("gui_chest_obsidian", () -> IMenuTypeExtension.create(GuiChestObsidianMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<ChestBigObsidianMenu>> CHEST_BIG_OBSIDIAN = REGISTRY.register("chest_big_obsidian", () -> IMenuTypeExtension.create(ChestBigObsidianMenu::new));
}
