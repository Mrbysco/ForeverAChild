package com.mrbysco.fac;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent.EntityInteract;

public class CapabilityHandler {

	@SubscribeEvent
	public void interactEvent(EntityInteract event) {
		if (event.getTarget() instanceof AgeableMob ageableMob && !ageableMob.level().isClientSide) {
			final Player playerIn = event.getEntity();
			if (ageableMob.isBaby()) {
				ItemStack stack = event.getItemStack();
				if (stack.is(ForeverAChild.AGE_LOCKING_TAG) && setLocked(ageableMob, playerIn, stack, true)) {
					event.setCancellationResult(InteractionResult.SUCCESS);
					event.setCanceled(true);
				} else if (stack.is(ForeverAChild.AGE_UNLOCKING_TAG) && setLocked(ageableMob, playerIn, stack, false)) {
					event.setCancellationResult(InteractionResult.SUCCESS);
					event.setCanceled(true);
				}
			}
		}
	}

	public boolean setLocked(AgeableMob entity, Player playerIn, ItemStack stack, boolean value) {
		boolean locked = entity.getData(ForeverAChild.LOCKED);
		if(locked != value) {
			entity.setData(ForeverAChild.LOCKED, value);
			shrinkItem(stack, playerIn);
			return true;
		}
		return false;
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
