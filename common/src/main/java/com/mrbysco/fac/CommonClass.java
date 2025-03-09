package com.mrbysco.fac;

import com.mrbysco.fac.platform.Services;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class CommonClass {
    public static final TagKey<Item> AGE_LOCKING_TAG = createItemTag("age_locking");
    public static final TagKey<Item> AGE_UNLOCKING_TAG = createItemTag("age_unlocking");

    public static void shrinkItem(ItemStack stack, Player playerIn) {
        if (!playerIn.getAbilities().instabuild) {
            if (stack.getItem() == Items.MILK_BUCKET) {
                stack.shrink(1);
                playerIn.addItem(new ItemStack(Items.BUCKET));
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
        return TagKey.create(Registries.ITEM, new ResourceLocation(Constants.MOD_ID, path));
    }
}