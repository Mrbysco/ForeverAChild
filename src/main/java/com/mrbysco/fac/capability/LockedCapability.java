package com.mrbysco.fac.capability;

import com.mrbysco.fac.CapabilityHandler;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LockedCapability implements ILocked, ICapabilitySerializable<CompoundTag> {

	private boolean locked;

	public LockedCapability() {
		this.locked = false;
	}

	@Override
	public boolean isLocked() {
		return this.locked;
	}

	@Override
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	@Override
	public CompoundTag serializeNBT() {
		return writeNBT(this);
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		readNBT(this, nbt);
	}

	public CompoundTag writeNBT(LockedCapability instance) {
		CompoundTag tag = new CompoundTag();
		tag.putBoolean("ageLocked", instance.isLocked());
		return tag;
	}

	public void readNBT(LockedCapability instance, CompoundTag tag) {
		instance.setLocked(tag.getBoolean("ageLocked"));
	}

	@NotNull
	@Override
	public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		return CapabilityHandler.LOCKED_CAPABILITY.orEmpty(cap, LazyOptional.of(() -> this));
	}
}
