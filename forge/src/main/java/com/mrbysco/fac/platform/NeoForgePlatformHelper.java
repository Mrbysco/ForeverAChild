package com.mrbysco.fac.platform;

import com.mrbysco.fac.ForeverAChildNeoForge;
import com.mrbysco.fac.platform.services.IPlatformHelper;
import net.minecraft.world.entity.AgeableMob;

public class NeoForgePlatformHelper implements IPlatformHelper {

	@Override
	public boolean isLocked(AgeableMob ageableMob) {
		if (ageableMob.isBaby() && ageableMob.hasData(ForeverAChildNeoForge.LOCKED)) {
			return ageableMob.getData(ForeverAChildNeoForge.LOCKED);
		}
		return false;
	}
}
