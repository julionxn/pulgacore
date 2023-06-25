package net.pulga22.pulgacore;

import net.fabricmc.api.ClientModInitializer;
import net.pulga22.pulgacore.networking.Packets;

public class PulgaCoreClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Packets.registerS2CPackets();
    }
}
