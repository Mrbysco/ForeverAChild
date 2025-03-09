package com.mrbysco.fac.mixin;

import com.mrbysco.fac.ForeverAChildFabric;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerMixin {
	@Inject(method = "interactOn", at = @At(value = "INVOKE",
			target = "Lnet/minecraft/world/entity/player/Player;getItemInHand(Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/item/ItemStack;",
			ordinal = 0),
			cancellable = true)
	private void entityInteract(Entity entity, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResult> cir) {
		Player player = (Player) (Object) this;
		var result = ForeverAChildFabric.interactEvent(player, entity, player.getItemInHand(interactionHand));
		if (result.consumesAction()) {
			cir.setReturnValue(result);
		}
	}
}
