package com.mrbysco.fac.util;

import com.mrbysco.fac.ForeverAChild;
import net.minecraft.world.entity.AgeableMob;

public class LockUtil {
	public static boolean isLocked(AgeableMob entity) {
		if (entity.isBaby()) {
			boolean locked = entity.getData(ForeverAChild.LOCKED.get());
			return locked;
		}
		return false;
	}
}
