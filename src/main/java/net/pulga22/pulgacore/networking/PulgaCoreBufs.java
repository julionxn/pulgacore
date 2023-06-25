package net.pulga22.pulgacore.networking;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.pulga22.pulgacore.datasaver.SaveTypes;

import java.util.UUID;


public abstract class PulgaCoreBufs {

    public static PacketByteBuf createBuf(String modId, UUID uuid, Identifier key, int i){
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(modId);
        buf.writeIdentifier(key);
        buf.writeEnumConstant(SaveTypes.INTEGER);
        buf.writeUuid(uuid);
        buf.writeInt(i);
        return buf;
    }

    public static PacketByteBuf createBuf(String modId, UUID uuid, Identifier key, float i){
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(modId);
        buf.writeIdentifier(key);
        buf.writeEnumConstant(SaveTypes.FLOAT);
        buf.writeUuid(uuid);
        buf.writeFloat(i);
        return buf;
    }

    public static PacketByteBuf createBuf(String modId, UUID uuid, Identifier key, boolean i){
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(modId);
        buf.writeIdentifier(key);
        buf.writeEnumConstant(SaveTypes.BOOLEAN);
        buf.writeUuid(uuid);
        buf.writeBoolean(i);
        return buf;
    }

    public static PacketByteBuf createBuf(String modId, UUID uuid, Identifier key, byte i){
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(modId);
        buf.writeIdentifier(key);
        buf.writeEnumConstant(SaveTypes.BYTE);
        buf.writeUuid(uuid);
        buf.writeByte(i);
        return buf;
    }

    public static PacketByteBuf createBuf(String modId, UUID uuid, Identifier key, double i){
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(modId);
        buf.writeIdentifier(key);
        buf.writeEnumConstant(SaveTypes.DOUBLE);
        buf.writeUuid(uuid);
        buf.writeDouble(i);
        return buf;
    }

}
