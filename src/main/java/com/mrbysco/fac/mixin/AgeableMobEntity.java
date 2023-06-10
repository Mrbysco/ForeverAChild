package com.mrbysco.fac.mixin;

import com.mrbysco.fac.util.LockUtil;
import net.minecraft.world.entity.AgeableMob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AgeableMob.class)
public class AgeableMobEntity {
	@Inject(at = @At("HEAD"), method = "setAge(I)V", cancellable = true)
	public void setGrowingAge(int age, CallbackInfo info) {
		AgeableMob entity = (AgeableMob) (Object) this;
		if (LockUtil.isLocked(entity)) {
			info.cancel();
		}
	}
}
