package com.mrbysco.fac.mixin.client;

import com.mrbysco.fac.ForeverAChildFabric;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MultiPlayerGameMode.class)
public class MultiPlayerGameModeMixin {
	@Inject(method = "interact",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientPacketListener;send(Lnet/minecraft/network/protocol/Packet;)V",
					shift = At.Shift.AFTER),
			cancellable = true)
	private void foreverachild$interact(Player player, Entity entity, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResult> cir) {
		var result = ForeverAChildFabric.interactEvent(player, entity, player.getItemInHand(interactionHand));
		if (result.consumesAction()) {
			cir.setReturnValue(result);
		}
	}
}
