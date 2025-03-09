package com.mrbysco.fac.platform;

import com.mrbysco.fac.ForeverAChildFabric;
import com.mrbysco.fac.platform.services.IPlatformHelper;
import net.minecraft.world.entity.AgeableMob;

public class FabricPlatformHelper implements IPlatformHelper {


    @Override
    public boolean isLocked(AgeableMob ageableMob) {
        if (ageableMob.isBaby()) {
	        return ageableMob.getAttachedOrElse(ForeverAChildFabric.LOCKED, false);
        }
        return false;
    }
}
