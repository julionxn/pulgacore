package net.pulga22.pulgacore.Registry;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import net.pulga22.pulgacore.PulgaCore;

import java.util.HashMap;
import java.util.Map;

public abstract class PacketsRegistry extends AbstractRegistry{

    private static final Map<Identifier, ClientPlayNetworking.PlayChannelHandler> S2C_ENTRIES = new HashMap<>();
    private static final Map<Identifier, ServerPlayNetworking.PlayChannelHandler> C2S_ENTRIES = new HashMap<>();


    /**
     * @param id The id of the packet.
     * @param channelHandler The onClient method call of the packet.
     * @return The identifier of the packet.
     */
    protected static Identifier registerS2C(String id, ClientPlayNetworking.PlayChannelHandler channelHandler){
        Identifier identifier = new Identifier(PulgaCore.MOD_ID, id);
        S2C_ENTRIES.put(identifier, channelHandler);
        return identifier;
    }

    /**
     * @param id The id of the packet.
     * @param channelHandler The onServer method call of the packet.
     * @return The identifier of the packet.
     */
    protected static Identifier registerC2S(String id, ServerPlayNetworking.PlayChannelHandler channelHandler){
        Identifier identifier = new Identifier(PulgaCore.MOD_ID, id);
        C2S_ENTRIES.put(identifier, channelHandler);
        return identifier;
    }

    /**
     * Call this method in the Client Mod Class to register all the packets.
     * @see net.pulga22.pulgacore.PulgaCore#setModId(String)
     */
    public static void registerS2CPacketsCall(){
        for (Identifier identifier : S2C_ENTRIES.keySet()){
            ClientPlayNetworking.registerGlobalReceiver(identifier, S2C_ENTRIES.get(identifier));
        }
        register("S2Cpackets");
    }

    /**
     * Call this method in the Common Mod Class to register all the packets.
     * @see net.pulga22.pulgacore.PulgaCore#setModId(String)
     */
    public static void registerC2SPacketsCall(){
        for (Identifier identifier : C2S_ENTRIES.keySet()){
            ServerPlayNetworking.registerGlobalReceiver(identifier, C2S_ENTRIES.get(identifier));
        }
        register("C2Spackets");
    }

}
