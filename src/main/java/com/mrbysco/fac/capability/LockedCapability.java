package com.mrbysco.fac.capability;

public class LockedCapability implements ILocked {
    private boolean locked;

    public LockedCapability() {
        this.locked = false;
    }

    @Override
    public boolean isLocked() {
        return locked;
    }

    @Override
    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
