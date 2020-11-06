package com.mrbysco.fac.mixin;

import com.mrbysco.fac.capability.ILocked;
import com.mrbysco.fac.capability.LockedCapabilityProvider;
import net.minecraft.entity.AgeableEntity;
import net.minecraftforge.common.util.LazyOptional;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AgeableEntity.class)
public class AgeableEntityMixin {
    @Inject(at = @At("HEAD"), method = "setGrowingAge(I)V", cancellable = true)
    public void setGrowingAge(int age, CallbackInfo info) {
        AgeableEntity entity = (AgeableEntity) (Object) this;
        if(entity.isChild()) {
            LazyOptional<ILocked> lockedCap = entity.getCapability(LockedCapabilityProvider.LOCKED_CAPABILITY, null);
            lockedCap.ifPresent(c -> {
                if(c.isLocked()) {
                    info.cancel();
                }
            });
        }
    }
}
