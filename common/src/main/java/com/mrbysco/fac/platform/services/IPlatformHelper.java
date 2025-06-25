package com.mrbysco.fac.platform.services;

import net.minecraft.world.entity.AgeableMob;

public interface IPlatformHelper {

	/**
	 * Check if the entity is age locked.
	 *
	 * @param ageableMob The entity to check.
	 * @return True if the entity is age locked, false otherwise.
	 */
	boolean isLocked(AgeableMob ageableMob);

}
