package com.mrbysco.fac.capability;

import com.mrbysco.fac.CommonClass;
import com.mrbysco.fac.Constants;
import com.mrbysco.fac.capability.ILocked;
import com.mrbysco.fac.capability.LockedCapability;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CapabilityHandler {
	public static final Capability<ILocked> LOCKED_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
	});

	@SubscribeEvent
	public void onEntityConstructing(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof AgeableMob) {
			event.addCapability(Constants.AGELOCKED, new LockedCapability());
		}
	}

	@SubscribeEvent
	public void interactEvent(EntityInteract event) {
		if (event.getTarget() instanceof AgeableMob ageableMob && !ageableMob.level().isClientSide) {
			final Player playerIn = event.getEntity();
			if (ageableMob.isBaby()) {
				ItemStack stack = event.getItemStack();
				if (stack.is(CommonClass.AGE_LOCKING_TAG)) {
					setLocked(ageableMob, playerIn, stack, true);
					event.setCancellationResult(InteractionResult.SUCCESS);
					event.setCanceled(true);
				}
				if (stack.is(CommonClass.AGE_UNLOCKING_TAG)) {
					setLocked(ageableMob, playerIn, stack, false);
					event.setCancellationResult(InteractionResult.SUCCESS);
					event.setCanceled(true);
				}
			}
		}
	}

	public void setLocked(AgeableMob entity, Player playerIn, ItemStack stack, boolean value) {
		entity.getCapability(CapabilityHandler.LOCKED_CAPABILITY).ifPresent(c -> {
			if (c.isLocked() != value) {
				c.setLocked(value);
				CommonClass.shrinkItem(stack, playerIn);
			}
		});
	}
}
