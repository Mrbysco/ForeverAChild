package com.mrbysco.fac;

import com.mrbysco.fac.capability.ILocked;
import com.mrbysco.fac.capability.LockedCapabilityProvider;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CapabilityHandler {
    @SubscribeEvent
    public void onEntityConstructing(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof AgeableEntity) {
            event.addCapability(ForeverAChild.AGELOCKED_CAP, new LockedCapabilityProvider());
        }
    }

    @SubscribeEvent
    public void interactEvent(EntityInteract event) {
        if(event.getTarget() instanceof AgeableEntity) {
            AgeableEntity ageableEntity = (AgeableEntity) event.getTarget();
            PlayerEntity playerIn = event.getPlayer();

            if(ageableEntity.isChild()) {
                ItemStack stack = event.getItemStack();
                if(stack.getItem().isIn(ForeverAChild.AGE_LOCKING_TAG)) {
                    setLocked(ageableEntity, playerIn, stack,true);
                }
                if(stack.getItem().isIn(ForeverAChild.AGE_UNLOCKING_TAG)) {
                    setLocked(ageableEntity, playerIn, stack,false);
                }
            }
        }
    }

    public void setLocked(AgeableEntity entity, PlayerEntity playerIn, ItemStack stack, boolean value) {
        LazyOptional<ILocked> lockedCap = entity.getCapability(LockedCapabilityProvider.LOCKED_CAPABILITY, null);
        lockedCap.ifPresent(c -> {
            if(c.isLocked() != value) {
                c.setLocked(value);
                shrinkItem(stack, playerIn);
            }
        });
    }

    public void shrinkItem(ItemStack stack, PlayerEntity playerIn) {
        if(!playerIn.abilities.isCreativeMode) {
            if(stack.getItem() == Items.MILK_BUCKET) {
                stack.shrink(1);
                playerIn.addItemStackToInventory(new ItemStack(Items.BUCKET));
            } else {
                stack.shrink(1);
            }
        }
    }
}
