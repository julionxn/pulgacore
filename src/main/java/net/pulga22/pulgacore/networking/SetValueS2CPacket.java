package net.pulga22.pulgacore.networking;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;

public abstract class SetValueS2CPacket {
    public static void onClient(MinecraftClient client, ClientPlayNetworkHandler clientPlayNetworkHandler,
                                PacketByteBuf buf, PacketSender packetSender) {
        SaveData.saveDataFromClient(buf);
    }
}
