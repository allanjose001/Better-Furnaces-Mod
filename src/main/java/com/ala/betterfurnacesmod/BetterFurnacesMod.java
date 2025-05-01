package com.ala.betterfurnacesmod;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BetterFurnacesMod implements ModInitializer {
	public static final String MOD_ID = "betterfurnacesmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.initialize();
		ModBlocks.initialize();

		LOGGER.info("Better Furnaces Mod is initializing...");
	}
}