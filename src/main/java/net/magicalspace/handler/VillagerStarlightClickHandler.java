package net.magicalspace.handler;

import net.magicalspace.entity.VillagerStarlightEntity;
import net.magicalspace.screen.VillagerStarlightDialogScreen;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class VillagerStarlightClickHandler {
    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        Player player = event.getEntity();
        Entity target = event.getTarget();

        // 检查是否点击了 VillagerStarlightEntity
        if (target instanceof VillagerStarlightEntity) {
            event.setCanceled(true); // 取消默认交互行为
            showPopup(player);
        }
    }

    @SubscribeEvent
    public static void onAttackEntity(AttackEntityEvent event) {
        Player player = event.getEntity();
        Entity target = event.getTarget();

        // 检查是否攻击了 VillagerStarlightEntity
        if (target instanceof VillagerStarlightEntity) {
            event.setCanceled(true); // 取消默认攻击行为
        }
    }

    private static void showPopup(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            // 使用本地化键调用 CustomDialogScreen
            VillagerStarlightDialogScreen.open(serverPlayer);
        }
    }
}