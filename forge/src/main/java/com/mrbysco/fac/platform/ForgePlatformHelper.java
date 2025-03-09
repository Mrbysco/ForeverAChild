package com.mrbysco.fac.platform;

import com.mrbysco.fac.capability.CapabilityHandler;
import com.mrbysco.fac.platform.services.IPlatformHelper;
import net.minecraft.world.entity.AgeableMob;

public class ForgePlatformHelper implements IPlatformHelper {

	@Override
	public boolean isLocked(AgeableMob ageableMob) {
		if (ageableMob.isBaby()) {
			var lockCap = ageableMob.getCapability(CapabilityHandler.LOCKED_CAPABILITY).orElse(null);
			if (lockCap != null) {
				return lockCap.isLocked();
			}
		}
		return false;
	}
}
