package net.pulga22.pulgacore.networking;

import net.minecraft.client.MinecraftClient;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.pulga22.pulgacore.PulgaCore;
import net.pulga22.pulgacore.datasaver.DataSaver;
import net.pulga22.pulgacore.datasaver.SaveTypes;

import java.util.UUID;

public abstract class SaveData {

    public static void saveDataFromClient(PacketByteBuf buf){

        String modId = buf.readString();
        Identifier key = buf.readIdentifier();
        SaveTypes saveType = buf.readEnumConstant(SaveTypes.class);
        DataSaver dataSaver = PulgaCore.getDataSaver(modId);
        UUID uuid = buf.readUuid();
        MinecraftClient client = MinecraftClient.getInstance();

        switch (saveType){
            case INTEGER -> {
                int i = buf.readInt();
                client.execute(() -> dataSaver.setInt(uuid, key, i));
            }
            case FLOAT -> {
                float f = buf.readFloat();
                client.execute(() -> dataSaver.setFloat(uuid, key, f));
            }
            case BOOLEAN -> {
                boolean b = buf.readBoolean();
                client.execute(() -> dataSaver.setBoolean(uuid, key, b));
            }
            case BYTE -> {
                byte b = buf.readByte();
                client.execute(() -> dataSaver.setByte(uuid, key, b));
            }
            case DOUBLE -> {
                double d = buf.readDouble();
                client.execute(() -> dataSaver.setDouble(uuid, key, d));
            }
            case LIST -> {
                NbtCompound list = buf.readNbt();
                if (list != null){
                    client.execute(() -> dataSaver.setList(uuid, key, list));
                }
            }
        }
    }
    
    public static void saveData(PacketByteBuf buf){

        String modId = buf.readString();
        Identifier key = buf.readIdentifier();
        SaveTypes saveType = buf.readEnumConstant(SaveTypes.class);
        DataSaver dataSaver = PulgaCore.getDataSaver(modId);
        UUID uuid = buf.readUuid();

        switch (saveType){
            case INTEGER -> {
                int i = buf.readInt();
                dataSaver.setInt(uuid, key, i);
            }
            case FLOAT -> {
                float f = buf.readFloat();
                dataSaver.setFloat(uuid, key, f);
            }
            case BOOLEAN -> {
                boolean b = buf.readBoolean();
                dataSaver.setBoolean(uuid, key, b);
            }
            case BYTE -> {
                byte b = buf.readByte();
                dataSaver.setByte(uuid, key, b);
            }
            case DOUBLE -> {
                double d = buf.readDouble();
                dataSaver.setDouble(uuid, key, d);
            }
            case LIST -> {
                NbtCompound list = buf.readNbt();
                if (list != null){
                    dataSaver.setList(uuid, key, list);
                }
            }
        }

    }

}
