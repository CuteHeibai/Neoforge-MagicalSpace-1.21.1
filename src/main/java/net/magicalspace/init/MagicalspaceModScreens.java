package net.magicalspace.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.magicalspace.client.gui.GuiChestObsidianScreen;
import net.magicalspace.client.gui.ChestBigObsidianScreen;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MagicalspaceModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(MagicalspaceModMenus.GUI_CHEST_OBSIDIAN.get(), GuiChestObsidianScreen::new);
		event.register(MagicalspaceModMenus.CHEST_BIG_OBSIDIAN.get(), ChestBigObsidianScreen::new);
	}
}
