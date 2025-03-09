package com.mrbysco.fac.capability;

import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface ILocked {
	boolean isLocked();

	void setLocked(boolean locked);
}
