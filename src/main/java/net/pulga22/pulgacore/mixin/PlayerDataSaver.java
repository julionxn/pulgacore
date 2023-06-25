package net.pulga22.pulgacore.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.pulga22.pulgacore.PulgaCore;
import net.pulga22.pulgacore.datasaver.DataSaver;
import net.pulga22.pulgacore.datasaver.SaveTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerDataSaver extends LivingEntity {

    @Shadow public abstract boolean damage(DamageSource source, float amount);

    protected PlayerDataSaver(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    public void read(NbtCompound nbt, CallbackInfo ci){
        PulgaCore.dataSaverKeys().forEach(modId -> {
            DataSaver saver = PulgaCore.getDataSaver(modId);
            saver.getIntegerKeys().forEach(intKey -> {
                if (nbt.contains(parseIdentifier(intKey))){
                    saver.setInt(getUuid(), intKey, nbt.getInt(parseIdentifier(intKey)));
                }
            });
            saver.getBooleanKeys().forEach(booleanKey -> {
                if (nbt.contains(parseIdentifier(booleanKey))){
                    saver.setBoolean(getUuid(), booleanKey, nbt.getBoolean(parseIdentifier(booleanKey)));
                }
            });
            saver.getFloatKeys().forEach(floatKey -> {
                if (nbt.contains(parseIdentifier(floatKey))){
                    saver.setFloat(getUuid(), floatKey, nbt.getFloat(parseIdentifier(floatKey)));
                }
            });
            saver.getByteKeys().forEach(byteKey -> {
                if (nbt.contains(parseIdentifier(byteKey))){
                    saver.setByte(getUuid(), byteKey, nbt.getByte(parseIdentifier(byteKey)));
                }
            });
            saver.getDoubleKeys().forEach(doubleKey -> {
                if (nbt.contains(parseIdentifier(doubleKey))){
                    saver.setDouble(getUuid(), doubleKey, nbt.getDouble(parseIdentifier(doubleKey)));
                }
            });
            saver.getListKeys().forEach(listKey -> {
                if (nbt.contains(parseIdentifier(listKey))){
                    saver.setList(getUuid(), listKey, nbt.getCompound(parseIdentifier(listKey)));
                }
            });
        });
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    public void write(NbtCompound nbt, CallbackInfo ci){
        PulgaCore.dataSaverKeys().forEach(modId -> {
            DataSaver saver = PulgaCore.getDataSaver(modId);
            saver.getIntegerKeys().forEach(intKey -> {
                if (saver.playerActiveIn(SaveTypes.INTEGER, intKey, getUuid())){
                    nbt.putInt(parseIdentifier(intKey), saver.getInt(getUuid(), intKey));
                }
            });
            saver.getBooleanKeys().forEach(booleanKey -> {
                if (saver.playerActiveIn(SaveTypes.BOOLEAN, booleanKey, getUuid())) {
                    nbt.putBoolean(parseIdentifier(booleanKey), saver.getBoolean(getUuid(), booleanKey));
                }
            });
            saver.getFloatKeys().forEach(floatKey -> {
                if (saver.playerActiveIn(SaveTypes.FLOAT, floatKey, getUuid())){
                    nbt.putFloat(parseIdentifier(floatKey), saver.getFloat(getUuid(), floatKey));
                }
            });
            saver.getByteKeys().forEach(byteKey -> {
                if (saver.playerActiveIn(SaveTypes.BYTE, byteKey, getUuid())){
                    nbt.putByte(parseIdentifier(byteKey), saver.getByte(getUuid(), byteKey));
                }
            });
            saver.getDoubleKeys().forEach(doubleKey -> {
                if (saver.playerActiveIn(SaveTypes.DOUBLE, doubleKey, getUuid())){
                    nbt.putDouble(parseIdentifier(doubleKey), saver.getDouble(getUuid(), doubleKey));
                }
            });
            saver.getListKeys().forEach(listKey -> {
                if (saver.playerActiveIn(SaveTypes.LIST, listKey, getUuid())){
                    nbt.put(parseIdentifier(listKey), saver.getList(getUuid(), listKey));
                }
            });
        });
    }

    private static String parseIdentifier(Identifier identifier){
        return identifier.getNamespace() + "_" + identifier.getPath();
    }

}