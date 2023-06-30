package net.pulga22.pulgacore;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.pulga22.pulgacore.networking.Packets;

public class PulgaCoreClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Packets.registerS2CPackets();

        ClientPlayConnectionEvents.JOIN.register((handler, sender, client)->{
            if (client.player != null){
                ClientPlayNetworking.send(Packets.SYNC_C2S, PacketByteBufs.empty());
            }
        });
    }
}
