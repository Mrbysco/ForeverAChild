package com.mrbysco.fac;

import com.mrbysco.fac.capability.CapabilityHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class ForeverAChildForge {

    public ForeverAChildForge() {
        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
    }
}