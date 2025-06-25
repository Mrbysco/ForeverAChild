package com.mrbysco.fac;

import com.mrbysco.fac.attachment.Locked;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

@Mod(Constants.MOD_ID)
public class ForeverAChildNeoForge {

	private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, Constants.MOD_ID);
	public static final Supplier<AttachmentType<Boolean>> LOCKED = ATTACHMENT_TYPES.register("locked", () -> AttachmentType.builder(() -> false).serialize(Locked.INSTANCE).build());


	public ForeverAChildNeoForge(IEventBus eventBus) {
		ATTACHMENT_TYPES.register(eventBus);

		NeoForge.EVENT_BUS.register(new AttachmentHandler());
	}
}