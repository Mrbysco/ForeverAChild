package com.mrbysco.fac.mixin;

import com.mrbysco.fac.CommonClass;
import net.minecraft.world.entity.AgeableMob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("UnusedMixin") // It is used in the Fabric and NeoForge platform implementations
@Mixin(AgeableMob.class)
public class AgeableMobMixin {
	@Inject(at = @At("HEAD"), method = "setAge(I)V", cancellable = true)
	public void foreverachild$setAge(int age, CallbackInfo info) {
		AgeableMob entity = (AgeableMob) (Object) this;
		if (CommonClass.isLocked(entity)) {
			info.cancel();
		}
	}
}
