package com.mrbysco.fac;

import com.mrbysco.fac.capability.ILocked;
import com.mrbysco.fac.capability.LockedCapability;
import com.mrbysco.fac.capability.LockedStorage;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ForeverAChild.MOD_ID)
public class ForeverAChild {
    public static final String MOD_ID = "foreverachild";
    public static final ResourceLocation AGELOCKED_CAP = new ResourceLocation(MOD_ID, "capability.agelocked");
    public static final ITag.INamedTag<Item> AGE_LOCKING_TAG = ItemTags.makeWrapperTag(MOD_ID + ":age_locking");
    public static final ITag.INamedTag<Item> AGE_UNLOCKING_TAG = ItemTags.makeWrapperTag(MOD_ID + ":age_unlocking");

    public static final Logger LOGGER = LogManager.getLogger();

    public ForeverAChild() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
    }

    private void setup(final FMLCommonSetupEvent event) {
        CapabilityManager.INSTANCE.register(ILocked.class, new LockedStorage(), LockedCapability::new);
    }
}
