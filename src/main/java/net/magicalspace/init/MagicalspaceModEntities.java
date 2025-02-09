package net.magicalspace.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.registries.Registries;

import net.magicalspace.entity.VillagerStarlightEntity;
import net.magicalspace.entity.MobDarkStarlightMageEntity;
import net.magicalspace.MagicalspaceMod;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class MagicalspaceModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(Registries.ENTITY_TYPE, MagicalspaceMod.MODID);
	public static final DeferredHolder<EntityType<?>, EntityType<MobDarkStarlightMageEntity>> MOB_DARK_STARLIGHT_MAGE = register("mob_dark_starlight_mage",
			EntityType.Builder.<MobDarkStarlightMageEntity>of(MobDarkStarlightMageEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(1.1f, 1.7f));
	public static final DeferredHolder<EntityType<?>, EntityType<VillagerStarlightEntity>> VILLAGER_STARLIGHT = register("villager_starlight",
			EntityType.Builder.<VillagerStarlightEntity>of(VillagerStarlightEntity::new, MobCategory.AMBIENT).setShouldReceiveVelocityUpdates(true).setTrackingRange(16).setUpdateInterval(3)

					.sized(0.75f, 1.9f));

	// Start of user code block custom entities
	// End of user code block custom entities
	private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(RegisterSpawnPlacementsEvent event) {
		MobDarkStarlightMageEntity.init(event);
		VillagerStarlightEntity.init(event);
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(MOB_DARK_STARLIGHT_MAGE.get(), MobDarkStarlightMageEntity.createAttributes().build());
		event.put(VILLAGER_STARLIGHT.get(), VillagerStarlightEntity.createAttributes().build());
	}
}
