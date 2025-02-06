
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.magicalspace.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

import net.magicalspace.MagicalspaceMod;

public class MagicalspaceModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(Registries.SOUND_EVENT, MagicalspaceMod.MODID);
	public static final DeferredHolder<SoundEvent, SoundEvent> ANNIES_WONDERLAND = REGISTRY.register("annies_wonderland", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("magicalspace", "annies_wonderland")));
	public static final DeferredHolder<SoundEvent, SoundEvent> ON_THIS_EVE_OF_PARTING_PT3 = REGISTRY.register("on_this_eve_of_parting_pt3",
			() -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("magicalspace", "on_this_eve_of_parting_pt3")));
	public static final DeferredHolder<SoundEvent, SoundEvent> CITY_OF_TEARS = REGISTRY.register("city_of_tears", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("magicalspace", "city_of_tears")));
}
