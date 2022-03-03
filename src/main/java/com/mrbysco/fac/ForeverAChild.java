package com.mrbysco.fac;

import com.mrbysco.fac.capability.ILocked;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ForeverAChild.MOD_ID)
public class ForeverAChild {
	public static final String MOD_ID = "foreverachild";
	public static final ResourceLocation AGELOCKED_CAP = new ResourceLocation(MOD_ID, "capability.agelocked");
	public static final TagKey<Item> AGE_LOCKING_TAG = ItemTags.create(new ResourceLocation(MOD_ID, "age_locking"));
	public static final TagKey<Item> AGE_UNLOCKING_TAG = ItemTags.create(new ResourceLocation(MOD_ID, "age_unlocking"));

	public static final Logger LOGGER = LogManager.getLogger();

	public ForeverAChild() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		eventBus.addListener(this::registerCapabilities);

		MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
	}

	public void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.register(ILocked.class);
	}
}
