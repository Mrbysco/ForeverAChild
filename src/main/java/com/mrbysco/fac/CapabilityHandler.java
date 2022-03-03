package com.mrbysco.fac;

import com.mrbysco.fac.capability.ILocked;
import com.mrbysco.fac.capability.LockedCapability;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
			event.addCapability(ForeverAChild.AGELOCKED_CAP, new LockedCapability());
		}
	}

	@SubscribeEvent
	public void interactEvent(EntityInteract event) {
		if (event.getTarget() instanceof AgeableMob ageableMob && !ageableMob.level.isClientSide) {
			Player playerIn = event.getPlayer();
			if (ageableMob.isBaby()) {
				ItemStack stack = event.getItemStack();
				if (stack.is(ForeverAChild.AGE_LOCKING_TAG)) {
					setLocked(ageableMob, playerIn, stack, true);
				}
				if (stack.is(ForeverAChild.AGE_UNLOCKING_TAG)) {
					setLocked(ageableMob, playerIn, stack, false);
				}
			}
		}
	}

	public void setLocked(AgeableMob entity, Player playerIn, ItemStack stack, boolean value) {
		entity.getCapability(CapabilityHandler.LOCKED_CAPABILITY).ifPresent(c -> {
			if (c.isLocked() != value) {
				c.setLocked(value);
				shrinkItem(stack, playerIn);
			}
		});
	}

	public void shrinkItem(ItemStack stack, Player playerIn) {
		if (!playerIn.getAbilities().instabuild) {
			if (stack.getItem() == Items.MILK_BUCKET) {
				stack.shrink(1);
				playerIn.addItem(new ItemStack(Items.BUCKET));
			} else {
				stack.shrink(1);
			}
		}
	}
}
