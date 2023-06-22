package net.pulga22.pulgacore.Registry;

import net.pulga22.pulgacore.PulgaCore;

/**
 * Abstract class to all registry clases.
 * ALWAYS ADD A REGISTER METHOD WHO CALLS THIS REGISTER METHOD.
 */
public abstract class AbstractRegistry {

    protected static void register(String type){
        PulgaCore.LOGGER.info("[" + PulgaCore.MOD_ID.toUpperCase() + "] " + type.toUpperCase() + " registering...");
    }

}
