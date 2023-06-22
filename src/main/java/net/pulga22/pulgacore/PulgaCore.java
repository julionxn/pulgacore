package net.pulga22.pulgacore;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PulgaCore implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("pulgacore");
	public static String MOD_ID = "";

	/**
	 * Always this method first inside onInitialize() method
	 * @param id The id of the mod
	 */
	public static void setModId(String id){
		MOD_ID = id;
	}

	@Override
	public void onInitialize() {
	}
}