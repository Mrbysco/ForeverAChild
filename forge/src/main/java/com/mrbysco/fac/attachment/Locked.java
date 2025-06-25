package com.mrbysco.fac.attachment;

import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;

public class Locked implements IAttachmentSerializer<Boolean> {
	public static final Locked INSTANCE = new Locked();

	@Override
	public Boolean read(IAttachmentHolder holder, ValueInput input) {
		return input.getBooleanOr("locked", false);
	}

	@Override
	public boolean write(Boolean locked, ValueOutput output) {
		if (locked != null) {
			output.putBoolean("locked", locked);
			return true;
		}
		return false;
	}
}
