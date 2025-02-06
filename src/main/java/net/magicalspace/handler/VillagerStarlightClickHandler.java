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

        if (target instanceof VillagerStarlightEntity) {
            event.setCanceled(true);
            showPopup(player);
            ((VillagerStarlightEntity) target).setInDialog(true);
            ((VillagerStarlightEntity) target).stopMoving(); // 停止走动
            ((VillagerStarlightEntity) target).facePlayer(player); // 面向玩家
        }
    }

    @SubscribeEvent
    public static void onAttackEntity(AttackEntityEvent event) {
        Player player = event.getEntity();
        Entity target = event.getTarget();

        if (target instanceof VillagerStarlightEntity) {
            event.setCanceled(true);
        }
    }

    private static void showPopup(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            VillagerStarlightDialogScreen.open(serverPlayer);
        }
    }
}