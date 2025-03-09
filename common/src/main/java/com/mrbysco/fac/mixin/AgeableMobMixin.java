package com.mrbysco.fac.mixin;

import com.mrbysco.fac.CommonClass;
import net.minecraft.world.entity.AgeableMob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AgeableMob.class)
public class AgeableMobMixin {
	@Inject(at = @At("HEAD"), method = "setAge(I)V", cancellable = true)
	public void setGrowingAge(int age, CallbackInfo info) {
		AgeableMob entity = (AgeableMob) (Object) this;
		if (CommonClass.isLocked(entity)) {
			info.cancel();
		}
	}
}
