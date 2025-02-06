
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.magicalspace.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.registries.BuiltInRegistries;

import net.magicalspace.block.entity.ChestObsidianChestBlockEntity;
import net.magicalspace.block.entity.ChestObsidianBigBlockEntity;
import net.magicalspace.MagicalspaceMod;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class MagicalspaceModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, MagicalspaceMod.MODID);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> CHEST_OBSIDIAN_CHEST = register("chest_obsidian_chest", MagicalspaceModBlocks.CHEST_OBSIDIAN_CHEST, ChestObsidianChestBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> CHEST_OBSIDIAN_BIG = register("chest_obsidian_big", MagicalspaceModBlocks.CHEST_OBSIDIAN_BIG, ChestObsidianBigBlockEntity::new);

	// Start of user code block custom block entities
	// End of user code block custom block entities
	private static DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> register(String registryname, DeferredHolder<Block, Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}

	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, CHEST_OBSIDIAN_CHEST.get(), (blockEntity, side) -> ((ChestObsidianChestBlockEntity) blockEntity).getItemHandler());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, CHEST_OBSIDIAN_BIG.get(), (blockEntity, side) -> ((ChestObsidianBigBlockEntity) blockEntity).getItemHandler());
	}
}
