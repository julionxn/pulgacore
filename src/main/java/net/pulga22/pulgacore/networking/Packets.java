package net.pulga22.pulgacore.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public abstract class Packets {

    public static Identifier SET_VALUE_C2S;
    public static Identifier SET_VALUE_S2C;

    public static void registerS2CPackets(){
        SET_VALUE_C2S = registerS2C(SetValueS2CPacket::onClient);
    }

    public static void registerC2SPackets(){
        SET_VALUE_S2C = registerC2S(SetValueC2SPacket::onServer);
    }

    private static Identifier registerS2C(ClientPlayNetworking.PlayChannelHandler channelHandler){
        Identifier identifier = new Identifier("pulga_core", "setvalue_c_s");
        ClientPlayNetworking.registerGlobalReceiver(identifier, channelHandler);
        return identifier;
    }

    protected static Identifier registerC2S(ServerPlayNetworking.PlayChannelHandler channelHandler){
        Identifier identifier = new Identifier("pulga_core", "setvalue_s_c");
        ServerPlayNetworking.registerGlobalReceiver(identifier, channelHandler);
        return identifier;
    }

}
