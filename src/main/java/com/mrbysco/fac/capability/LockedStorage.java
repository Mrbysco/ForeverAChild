package com.mrbysco.fac.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class LockedStorage implements Capability.IStorage<ILocked> {
    @Nullable
    @Override
    public INBT writeNBT(Capability<ILocked> capability, ILocked instance, Direction side) {
        CompoundNBT tag = new CompoundNBT();
        tag.putBoolean("ageLocked", instance.isLocked());
        return tag;
    }

    @Override
    public void readNBT(Capability<ILocked> capability, ILocked instance, Direction side, INBT nbt) {
        instance.setLocked(((CompoundNBT) nbt).getBoolean("ageLocked"));
    }
}