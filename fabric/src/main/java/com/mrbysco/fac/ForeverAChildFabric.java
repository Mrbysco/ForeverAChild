package com.mrbysco.fac;

import com.mojang.serialization.Codec;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ForeverAChildFabric implements ModInitializer {
	public static final AttachmentType<Boolean> LOCKED = AttachmentRegistry.createPersistent(
			Constants.AGELOCKED,
			Codec.BOOL
	);

	@Override
	public void onInitialize() {

	}

	public static InteractionResult interactEvent(Player player, Entity target, ItemStack stack) {
		if (target instanceof AgeableMob ageableMob && !ageableMob.level().isClientSide()) {
			if (ageableMob.isBaby()) {
				if (stack.is(CommonClass.AGE_LOCKING_TAG)) {
					setLocked(ageableMob, player, stack, true);
					return InteractionResult.SUCCESS;
				}
				if (stack.is(CommonClass.AGE_UNLOCKING_TAG)) {
					setLocked(ageableMob, player, stack, false);
					return InteractionResult.SUCCESS;
				}
			}
		}
		return InteractionResult.PASS;
	}

	private static void setLocked(AgeableMob entity, Player playerIn, ItemStack stack, boolean value) {
		boolean locked = entity.getAttachedOrElse(LOCKED, false);
		if (locked != value) {
			entity.setAttached(LOCKED, value);
			CommonClass.shrinkItem(stack, playerIn);
		}
	}
}
