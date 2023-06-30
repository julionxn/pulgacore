package net.pulga22.pulgacore.networking;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.pulga22.pulgacore.PulgaCore;
import net.pulga22.pulgacore.datasaver.CalledFrom;
import net.pulga22.pulgacore.datasaver.DataSaver;
import net.pulga22.pulgacore.datasaver.SaveTypes;

import java.util.UUID;

public class SyncC2SPacket {
    public static void onServer(MinecraftServer minecraftServer, ServerPlayerEntity player,
                                ServerPlayNetworkHandler networkHandler, PacketByteBuf buf, PacketSender packetSender) {
        UUID uuid = player.getUuid();
        PulgaCore.dataSaverKeys().forEach(modId -> {
            DataSaver saver = PulgaCore.getDataSaver(modId);
            saver.getIntegerKeys().forEach(intKey -> {
                if (saver.playerActiveIn(SaveTypes.INTEGER, intKey, uuid)){
                    saver.setIntFrom(CalledFrom.SERVER, player, intKey, saver.getInt(uuid, intKey));
                }
            });
            saver.getBooleanKeys().forEach(booleanKey -> {
                if (saver.playerActiveIn(SaveTypes.BOOLEAN, booleanKey, uuid)){
                    saver.setBooleanFrom(CalledFrom.SERVER, player, booleanKey, saver.getBoolean(uuid, booleanKey));
                }
            });
            saver.getFloatKeys().forEach(floatKey -> {
                if (saver.playerActiveIn(SaveTypes.FLOAT, floatKey, uuid)){
                    saver.setFloatFrom(CalledFrom.SERVER, player, floatKey, saver.getFloat(uuid, floatKey));
                }
            });
            saver.getByteKeys().forEach(byteKey -> {
                if (saver.playerActiveIn(SaveTypes.BYTE, byteKey, uuid)){
                    saver.setByteFrom(CalledFrom.SERVER, player, byteKey, saver.getByte(uuid, byteKey));
                }
            });
            saver.getDoubleKeys().forEach(doubleKey -> {
                if (saver.playerActiveIn(SaveTypes.DOUBLE, doubleKey, uuid)){
                    saver.setDoubleFrom(CalledFrom.SERVER, player, doubleKey, saver.getDouble(uuid, doubleKey));
                }
            });
            saver.getListKeys().forEach(listKey -> {
                if (saver.playerActiveIn(SaveTypes.LIST, listKey, uuid)){
                    saver.setListFrom(CalledFrom.SERVER, player, listKey, saver.getList(uuid, listKey));
                }
            });
        });

    }
}
