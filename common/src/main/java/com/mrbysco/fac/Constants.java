package com.mrbysco.fac;

import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {

	public static final String MOD_ID = "foreverachild";
	public static final String MOD_NAME = "Forever A Child";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	public static final Identifier AGELOCKED = Identifier.fromNamespaceAndPath(Constants.MOD_ID, "capability.agelocked");

}