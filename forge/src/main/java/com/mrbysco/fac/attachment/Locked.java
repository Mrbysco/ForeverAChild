package com.mrbysco.fac.attachment;

import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;
import org.jetbrains.annotations.NotNull;

public class Locked implements IAttachmentSerializer<Boolean> {
	public static final Locked INSTANCE = new Locked();

	@NotNull
	@Override
	public Boolean read(@NotNull IAttachmentHolder holder, ValueInput input) {
		return input.getBooleanOr("locked", false);
	}

	@Override
	public boolean write(@NotNull Boolean locked, @NotNull ValueOutput output) {
		output.putBoolean("locked", locked);
		return true;
	}
}
