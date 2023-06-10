package com.mrbysco.fac.util;

import com.mrbysco.fac.CapabilityHandler;
import net.minecraft.world.entity.AgeableMob;

public class LockUtil {
	public static boolean isLocked(AgeableMob entity) {
		if (entity.isBaby()) {
			var lockCap = entity.getCapability(CapabilityHandler.LOCKED_CAPABILITY).orElse(null);
			if (lockCap != null) {
				if (lockCap.isLocked()) {
					return true;
				}
			}
		}
		return false;
	}
}
