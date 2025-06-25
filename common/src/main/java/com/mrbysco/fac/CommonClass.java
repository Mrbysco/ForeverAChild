package com.mrbysco.fac;

import com.mrbysco.fac.platform.Services;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class CommonClass {
	public static final TagKey<Item> AGE_LOCKING_TAG = createItemTag("age_locking");
	public static final TagKey<Item> AGE_UNLOCKING_TAG = createItemTag("age_unlocking");

	public static void shrinkItem(ItemStack stack, Player playerIn) {
		if (!playerIn.getAbilities().instabuild) {
			ItemStack remainder = stack.getItem().getCraftingRemainder();
			if (!remainder.isEmpty()) {
				stack.shrink(1);
				playerIn.addItem(remainder);
			} else {
				stack.shrink(1);
			}
		}
	}

	public static boolean isLocked(AgeableMob entity) {
		if (entity.isBaby()) {
			return Services.PLATFORM.isLocked(entity);
		}
		return false;
	}

	private static TagKey<Item> createItemTag(String path) {
		return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, path));
	}
}