package com.mrbysco.fac.capability;

import net.neoforged.neoforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface ILocked {
	boolean isLocked();

	void setLocked(boolean locked);
}
