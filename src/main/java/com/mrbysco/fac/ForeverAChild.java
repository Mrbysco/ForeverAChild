package com.mrbysco.fac;

import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

@Mod(ForeverAChild.MOD_ID)
public class ForeverAChild {
	public static final String MOD_ID = "foreverachild";
	public static final TagKey<Item> AGE_LOCKING_TAG = ItemTags.create(new ResourceLocation(MOD_ID, "age_locking"));
	public static final TagKey<Item> AGE_UNLOCKING_TAG = ItemTags.create(new ResourceLocation(MOD_ID, "age_unlocking"));

	private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, MOD_ID);
	public static final Supplier<AttachmentType<Boolean>> LOCKED = ATTACHMENT_TYPES.register(
			"locked", () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL).build());

	public ForeverAChild(IEventBus eventBus) {
		ATTACHMENT_TYPES.register(eventBus);

		NeoForge.EVENT_BUS.register(new CapabilityHandler());
	}
}
