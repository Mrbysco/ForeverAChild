package com.mrbysco.fac.capability;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class LockedCapabilityProvider implements ICapabilitySerializable<INBT> {
    @CapabilityInject(ILocked.class)
    public static final Capability<ILocked> LOCKED_CAPABILITY = null;

    private LazyOptional<ILocked> instance;
    private ILocked sanity;

    public LockedCapabilityProvider() {
        this.sanity = LOCKED_CAPABILITY.getDefaultInstance();
        this.instance = LazyOptional.of(() -> sanity);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return LOCKED_CAPABILITY.orEmpty(cap, instance);
    }

    @Override
    public INBT serializeNBT() {
        return LOCKED_CAPABILITY.writeNBT(sanity, null);
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        LOCKED_CAPABILITY.readNBT(sanity, null, nbt);
    }
}
