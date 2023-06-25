package net.pulga22.pulgacore.datasaver;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.pulga22.pulgacore.networking.Packets;
import net.pulga22.pulgacore.networking.PulgaCoreBufs;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * DataSaver is a handler that read and store customNbt of the player.
 * It automatically syncs all the data between server and client.
 * To add one, register it inside the ModInitializer class of your mod.
 * @see net.pulga22.pulgacore.PulgaCore#registerDataSaver(String) 
 */
public class DataSaver {

    private final String MOD_ID;
    private final Map<Identifier, AbstractSaver<Integer>> INTEGERS = new HashMap<>();
    private final Map<Identifier, AbstractSaver<Float>> FLOATS = new HashMap<>();
    private final Map<Identifier, AbstractSaver<Boolean>> BOOLEANS = new HashMap<>();
    private final Map<Identifier, AbstractSaver<Byte>> BYTES = new HashMap<>();
    private final Map<Identifier, AbstractSaver<Double>> DOUBLES = new HashMap<>();
    private final Map<Identifier, AbstractSaver<NbtList>> LISTS = new HashMap<>();

    public DataSaver(String modId){
        MOD_ID = modId;
    }

    public String getModId(){
        return MOD_ID;
    }

    /**
     * @param saveType The type of value.
     * @param dataKey The unique id of the value;
     * @return The key identifier of the value.
     * @see SaveTypes
     */
    public Identifier register(SaveTypes saveType, String dataKey){
        Identifier key = registerId(dataKey);
        switch (saveType){
            case INTEGER -> {
                INTEGERS.put(key, new AbstractSaver<>());
            }
            case FLOAT -> {
                FLOATS.put(key, new AbstractSaver<>());
            }
            case BOOLEAN -> {
                BOOLEANS.put(key, new AbstractSaver<>());
            }
            case BYTE -> {
                BYTES.put(key, new AbstractSaver<>());
            }
            case DOUBLE -> {
                DOUBLES.put(key, new AbstractSaver<>());
            }
            case LIST -> {
                LISTS.put(key, new AbstractSaver<>());
            }
        }
        return key;
    }

    /**
     * @param uuid The uuid of the player.
     * @param key The identifier of the value.
     * @return The int value of the player.
     */
    public int getInt(UUID uuid, Identifier key){
        INTEGERS.get(key).addPlayerIfNull(uuid, () -> 0);
        return INTEGERS.get(key).getPlayer(uuid);
    }

    /**
     * @param player The player.
     * @param key The identifier of the value.
     * @param <P> Extends PlayerEntity
     * @return The int value of the player.
     */
    public <P extends PlayerEntity> int getInt(P player, Identifier key){
        return getInt(player.getUuid(), key);
    }

    /**
     * This method doesn't sync between sides. Use with precaution.
     * @param uuid The uuid of the player.
     * @param key The identifier of the value.
     * @param i The new int value.
     */
    public void setInt(UUID uuid, Identifier key, int i){
        getInt(uuid, key);
        INTEGERS.get(key).setPlayer(uuid, i);
    }

    /**
     * @param from The side the method is called.
     * @param player The player.
     * @param key The identifier of the value.
     * @param i The new int value.
     * @param <P> Extends PlayerEntity
     */
    public <P extends PlayerEntity> void setIntFrom(CalledFrom from, P player, Identifier key, int i){
        setInt(player.getUuid(), key, i);
        switch (from){
            case SERVER -> {
                PacketByteBuf buf = PulgaCoreBufs.createBuf(MOD_ID, player.getUuid(), key, i);
                ServerPlayNetworking.send((ServerPlayerEntity) player, Packets.SET_VALUE_S2C, buf);
            }
            case CLIENT -> {
                PacketByteBuf buf = PulgaCoreBufs.createBuf(MOD_ID, player.getUuid(), key, i);
                ClientPlayNetworking.send(Packets.SET_VALUE_C2S, buf);
            }
        }
    }

    /**
     * @param uuid The uuid of the player.
     * @param key The identifier of the value.
     * @return The float value of the player.
     */
    public float getFloat(UUID uuid, Identifier key){
        FLOATS.get(key).addPlayerIfNull(uuid, () -> 0f);
        return FLOATS.get(key).getPlayer(uuid);
    }

    /**
     * @param player The player.
     * @param key The identifier of the value.
     * @param <P> Extends PlayerEntity
     * @return The float value of the player.
     */
    public <P extends PlayerEntity> float getFloat(P player, Identifier key){
        return getFloat(player.getUuid(), key);
    }

    /**
     * This method doesn't sync between sides. Use with precaution.
     * @param uuid The uuid of the player.
     * @param key The identifier of the value.
     * @param i The new float value.
     */
    public void setFloat(UUID uuid, Identifier key, float i){
        getFloat(uuid, key);
        FLOATS.get(key).setPlayer(uuid, i);
    }

    /**
     * @param from The side the method is called.
     * @param player The player.
     * @param key The identifier of the value.
     * @param i The new float value.
     * @param <P> Extends PlayerEntity
     */
    public <P extends PlayerEntity> void setFloatFrom(CalledFrom from, P player, Identifier key, float i){
        setFloat(player.getUuid(), key, i);
        switch (from){
            case SERVER -> {
                PacketByteBuf buf = PulgaCoreBufs.createBuf(MOD_ID, player.getUuid(), key, i);
                ServerPlayNetworking.send((ServerPlayerEntity) player, Packets.SET_VALUE_S2C, buf);
            }
            case CLIENT -> {
                PacketByteBuf buf = PulgaCoreBufs.createBuf(MOD_ID, player.getUuid(), key, i);
                ClientPlayNetworking.send(Packets.SET_VALUE_C2S, buf);
            }
        }
    }

    /**
     * @param uuid The uuid of the player.
     * @param key The identifier of the value.
     * @return The boolean value of the player.
     */
    public boolean getBoolean(UUID uuid, Identifier key){
        BOOLEANS.get(key).addPlayerIfNull(uuid, () -> false);
        return BOOLEANS.get(key).getPlayer(uuid);
    }

    /**
     * @param player The player.
     * @param key The identifier of the value.
     * @param <P> Extends PlayerEntity
     * @return The boolean value of the player.
     */
    public <P extends PlayerEntity> boolean getBoolean(P player, Identifier key){
        return getBoolean(player.getUuid(), key);
    }

    /**
     * This method doesn't sync between sides. Use with precaution.
     * @param uuid The uuid of the player.
     * @param key The identifier of the value.
     * @param i The new boolean value.
     */
    public void setBoolean(UUID uuid, Identifier key, boolean i){
        getBoolean(uuid, key);
        BOOLEANS.get(key).setPlayer(uuid, i);
    }

    /**
     * @param from The side the method is called.
     * @param player The player.
     * @param key The identifier of the value.
     * @param i The new boolean value.
     * @param <P> Extends PlayerEntity
     */
    public <P extends PlayerEntity> void setBooleanFrom(CalledFrom from, P player, Identifier key, boolean i){
        setBoolean(player.getUuid(), key, i);
        switch (from){
            case SERVER -> {
                PacketByteBuf buf = PulgaCoreBufs.createBuf(MOD_ID, player.getUuid(), key, i);
                ServerPlayNetworking.send((ServerPlayerEntity) player, Packets.SET_VALUE_S2C, buf);
            }
            case CLIENT -> {
                PacketByteBuf buf = PulgaCoreBufs.createBuf(MOD_ID, player.getUuid(), key, i);
                ClientPlayNetworking.send(Packets.SET_VALUE_C2S, buf);
            }
        }
    }

    /**
     * @param uuid The uuid of the player.
     * @param key The identifier of the value.
     * @return The byte value of the player.
     */
    public byte getByte(UUID uuid, Identifier key){
        BYTES.get(key).addPlayerIfNull(uuid, () -> (byte) 0);
        return BYTES.get(key).getPlayer(uuid);
    }

    /**
     * @param player The player.
     * @param key The identifier of the value.
     * @param <P> Extends PlayerEntity
     * @return The byte value of the player.
     */
    public <P extends PlayerEntity> byte getByte(P player, Identifier key){
        return getByte(player.getUuid(), key);
    }

    /**
     * This method doesn't sync between sides. Use with precaution.
     * @param uuid The uuid of the player.
     * @param key The identifier of the value.
     * @param i The new byte value.
     */
    public void setByte(UUID uuid, Identifier key, byte i){
        getByte(uuid, key);
        BYTES.get(key).setPlayer(uuid, i);
    }

    /**
     * @param from The side the method is called.
     * @param player The player.
     * @param key The identifier of the value.
     * @param i The new byte value.
     * @param <P> Extends PlayerEntity
     */
    public <P extends PlayerEntity> void setByteFrom(CalledFrom from, P player, Identifier key, byte i){
        setByte(player.getUuid(), key, i);
        switch (from){
            case SERVER -> {
                PacketByteBuf buf = PulgaCoreBufs.createBuf(MOD_ID, player.getUuid(), key, i);
                ServerPlayNetworking.send((ServerPlayerEntity) player, Packets.SET_VALUE_S2C, buf);
            }
            case CLIENT -> {
                PacketByteBuf buf = PulgaCoreBufs.createBuf(MOD_ID, player.getUuid(), key, i);
                ClientPlayNetworking.send(Packets.SET_VALUE_C2S, buf);
            }
        }
    }

    /**
     * @param uuid The uuid of the player.
     * @param key The identifier of the value.
     * @return The double value of the player.
     */
    public double getDouble(UUID uuid, Identifier key){
        DOUBLES.get(key).addPlayerIfNull(uuid, () -> (double) 0);
        return DOUBLES.get(key).getPlayer(uuid);
    }

    /**
     * @param player The player.
     * @param key The identifier of the value.
     * @param <P> Extends PlayerEntity
     * @return The double value of the player.
     */
    public <P extends PlayerEntity> double getDouble(P player, Identifier key){
        return getDouble(player.getUuid(), key);
    }

    /**
     * This method doesn't sync between sides. Use with precaution.
     * @param uuid The uuid of the player.
     * @param key The identifier of the value.
     * @param i The new double value.
     */
    public void setDouble(UUID uuid, Identifier key, double i){
        getDouble(uuid, key);
        DOUBLES.get(key).setPlayer(uuid, i);
    }

    /**
     * @param from The side the method is called.
     * @param player The player.
     * @param key The identifier of the value.
     * @param i The new double value.
     * @param <P> Extends PlayerEntity
     */
    public <P extends PlayerEntity> void setDoubleFrom(CalledFrom from, P player, Identifier key, double i){
        setDouble(player.getUuid(), key, i);
        switch (from){
            case SERVER -> {
                PacketByteBuf buf = PulgaCoreBufs.createBuf(MOD_ID, player.getUuid(), key, i);
                ServerPlayNetworking.send((ServerPlayerEntity) player, Packets.SET_VALUE_S2C, buf);
            }
            case CLIENT -> {
                PacketByteBuf buf = PulgaCoreBufs.createBuf(MOD_ID, player.getUuid(), key, i);
                ClientPlayNetworking.send(Packets.SET_VALUE_C2S, buf);
            }
        }
    }

    /**
     * @param uuid The uuid of the player.
     * @param key The identifier of the value.
     * @return The list value of the player.
     */
    public NbtList getList(UUID uuid, Identifier key){
        LISTS.get(key).addPlayerIfNull(uuid, NbtList::new);
        return LISTS.get(key).getPlayer(uuid);
    }

    /**
     * @param player The player.
     * @param key The identifier of the value.
     * @param <P> Extends PlayerEntity
     * @return The list value of the player.
     */
    public <P extends PlayerEntity> NbtList getList(P player, Identifier key){
        return getList(player.getUuid(), key);
    }

    /**
     * This method doesn't sync between sides. Use with precaution.
     * @param uuid The uuid of the player.
     * @param key The identifier of the value.
     * @param list The new list value.
     */
    public void setList(UUID uuid, Identifier key, NbtList list){
        getList(uuid, key);
        LISTS.get(key).setPlayer(uuid, list);
    }

    /**
     * This method doesn't sync between sides. Use with precaution.
     * @param uuid The uuid of the player.
     * @param key The identifier of the value.
     * @param compound The new list value.
     */
    public void setList(UUID uuid, Identifier key, NbtCompound compound){
        NbtList nbtList = new NbtList();
        for (int i = 0; i < compound.getSize(); i++) {
            NbtElement element = compound.get(String.valueOf(i));
            nbtList.add(element);
        }
        getList(uuid, key);
        LISTS.get(key).setPlayer(uuid, nbtList);
    }

    /**
     * @param from The side the method is called.
     * @param player The player.
     * @param key The identifier of the value.
     * @param list The new list value.
     * @param <P> Extends PlayerEntity
     */
    public <P extends PlayerEntity> void setListFrom(CalledFrom from, P player, Identifier key, NbtList list){
        NbtCompound nbtCompound = new NbtCompound();
        int i = 0;
        for (NbtElement element : list) {
            nbtCompound.put(String.valueOf(i), element);
            i++;
        }
        setList(player.getUuid(), key, list);
        switch (from){
            case SERVER -> {
                PacketByteBuf buf = PulgaCoreBufs.createBuf(MOD_ID, player.getUuid(), key, i);
                ServerPlayNetworking.send((ServerPlayerEntity) player, Packets.SET_VALUE_S2C, buf);
            }
            case CLIENT -> {
                PacketByteBuf buf = PulgaCoreBufs.createBuf(MOD_ID, player.getUuid(), key, i);
                ClientPlayNetworking.send(Packets.SET_VALUE_C2S, buf);
            }
        }
    }

    /**
     * @param type The type of value.
     * @param key The real id of the value.
     * @param uuid The UUID of the player.
     * @return If the player is actually tracked inside the id of the value.
     */
    public boolean playerActiveIn(SaveTypes type, Identifier key, UUID uuid){
        switch (type){
            case INTEGER -> {
                return INTEGERS.get(key).playerActive(uuid);
            }
            case FLOAT -> {
                return FLOATS.get(key).playerActive(uuid);
            }
            case BOOLEAN -> {
                return BOOLEANS.get(key).playerActive(uuid);
            }
            case BYTE -> {
                return BYTES.get(key).playerActive(uuid);
            }
            case DOUBLE -> {
                return DOUBLES.get(key).playerActive(uuid);
            }
            case LIST -> {
                return LISTS.get(key).playerActive(uuid);
            }
        }
        return false;
    }

    public Set<Identifier> getIntegerKeys(){
        return INTEGERS.keySet();
    }

    public Set<Identifier> getFloatKeys(){
        return FLOATS.keySet();
    }

    public Set<Identifier> getBooleanKeys(){
        return BOOLEANS.keySet();
    }

    public Set<Identifier> getByteKeys(){
        return BYTES.keySet();
    }

    public Set<Identifier> getDoubleKeys(){
        return DOUBLES.keySet();
    }

    public Set<Identifier> getListKeys(){
        return LISTS.keySet();
    }
    
    private Identifier registerId(String key){
        return new Identifier(getModId(), key);
    }

}
