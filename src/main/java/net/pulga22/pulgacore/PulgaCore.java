package net.pulga22.pulgacore;

import net.fabricmc.api.ModInitializer;

import net.pulga22.pulgacore.Registry.EntriesManager;
import net.pulga22.pulgacore.datasaver.DataSaver;
import net.pulga22.pulgacore.datasaver.SaveTypes;
import net.pulga22.pulgacore.networking.Packets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PulgaCore implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("pulgacore");
	private static final Map<String, DataSaver> DATA_SAVERS = new HashMap<>();
	public static String MOD_ID = "";

	/**
	 * Always this method first inside onInitialize() method
	 * @param id The id of the mod
	 */
	public static void setModId(String id){
		MOD_ID = id;
		EntriesManager.addKeys(id);
	}

	/**
	 * @param modId The unique id of your mod.
	 * @return The registered DataSaver. Make sure to add a static field of it.
	 * @see DataSaver
	 * @see DataSaver#register(SaveTypes, String)
	 */
	public static DataSaver registerDataSaver(String modId){
		DataSaver dataSaver = new DataSaver(modId);
		DATA_SAVERS.put(modId, dataSaver);
		return dataSaver;
	}

	public static DataSaver getDataSaver(String modId){
		return DATA_SAVERS.get(modId);
	}

	public static Set<String> dataSaverKeys(){
		return DATA_SAVERS.keySet();
	}

	@Override
	public void onInitialize() {
		Packets.registerC2SPackets();
	}
}