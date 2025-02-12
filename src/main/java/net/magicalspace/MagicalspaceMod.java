package net.magicalspace;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.fml.util.thread.SidedThreadGroups;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.IEventBus;

import net.minecraft.util.Tuple;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.FriendlyByteBuf;

import net.magicalspace.init.MagicalspaceModTabs;
import net.magicalspace.init.MagicalspaceModSounds;
import net.magicalspace.init.MagicalspaceModParticleTypes;
import net.magicalspace.init.MagicalspaceModMenus;
import net.magicalspace.init.MagicalspaceModItems;
import net.magicalspace.init.MagicalspaceModFluids;
import net.magicalspace.init.MagicalspaceModFluidTypes;
import net.magicalspace.init.MagicalspaceModEntities;
import net.magicalspace.init.MagicalspaceModBlocks;
import net.magicalspace.init.MagicalspaceModBlockEntities;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Collection;
import java.util.ArrayList;

// Mod主文件
//用来传入基本信息和注册元素类型
@Mod("magicalspace")
public class MagicalspaceMod {
	public static final Logger LOGGER = LogManager.getLogger(MagicalspaceMod.class);
	public static final String MODID = "magicalspace";

	public static boolean chatBoxOpened = false; // 控制对话框显示状态

	//元素类型注册
	public MagicalspaceMod(IEventBus modEventBus) {
		NeoForge.EVENT_BUS.register(this);
		modEventBus.addListener(this::registerNetworking);
		MagicalspaceModSounds.REGISTRY.register(modEventBus);
		MagicalspaceModBlocks.REGISTRY.register(modEventBus);
		MagicalspaceModBlockEntities.REGISTRY.register(modEventBus);
		MagicalspaceModItems.REGISTRY.register(modEventBus);
		MagicalspaceModEntities.REGISTRY.register(modEventBus);
		MagicalspaceModTabs.REGISTRY.register(modEventBus);

		MagicalspaceModMenus.REGISTRY.register(modEventBus);
		MagicalspaceModParticleTypes.REGISTRY.register(modEventBus);

		MagicalspaceModFluids.REGISTRY.register(modEventBus);
		MagicalspaceModFluidTypes.REGISTRY.register(modEventBus);

	}

	@SubscribeEvent
	public void onServerTick(ServerTickEvent.Post event) {
		if (chatBoxOpened) {
			// 在这里可以添加客户端代码来绘制对话框
			// 例如：VillagerStarlightClient.renderChatBox();
		}
	}

	private static boolean networkingRegistered = false;
	private static final Map<CustomPacketPayload.Type<?>, NetworkMessage<?>> MESSAGES = new HashMap<>();

	private record NetworkMessage<T extends CustomPacketPayload>(StreamCodec<? extends FriendlyByteBuf, T> reader, IPayloadHandler<T> handler) {}

	public static <T extends CustomPacketPayload> void addNetworkMessage(CustomPacketPayload.Type<T> id, StreamCodec<? extends FriendlyByteBuf, T> reader, IPayloadHandler<T> handler) {
		if (networkingRegistered)
			throw new IllegalStateException("Cannot register new network messages after networking has been registered");
		MESSAGES.put(id, new NetworkMessage<>(reader, handler));
	}

	private void registerNetworking(final RegisterPayloadHandlersEvent event) {
		final PayloadRegistrar registrar = event.registrar(MODID);
		MESSAGES.forEach((id, networkMessage) -> registrar.playBidirectional(id, ((NetworkMessage) networkMessage).reader(), ((NetworkMessage) networkMessage).handler()));
		networkingRegistered = true;
	}

	private static final Collection<Tuple<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

	public static void queueServerWork(int tick, Runnable action) {
		if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER)
			workQueue.add(new Tuple<>(action, tick));
	}

	@SubscribeEvent
	public void tick(ServerTickEvent.Post event) {
		List<Tuple<Runnable, Integer>> actions = new ArrayList<>();
		workQueue.forEach(work -> {
			work.setB(work.getB() - 1);
			if (work.getB() == 0)
				actions.add(work);
		});
		actions.forEach(e -> e.getA().run());
		workQueue.removeAll(actions);
	}
}