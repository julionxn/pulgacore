package net.pulga22.pulgacore.networking;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public abstract class SetValueC2SPacket {
    public static void onServer(MinecraftServer server, ServerPlayerEntity playerEntity,
                                ServerPlayNetworkHandler networkHandler, PacketByteBuf buf, PacketSender packetSender) {
        SaveData.saveData(buf);
    }
}
