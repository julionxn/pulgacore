package net.pulga22.pulgacore.Registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.pulga22.pulgacore.PulgaCore;


public abstract class BlockEntitiesRegistry extends AbstractRegistry{

    /**
     * @param id The unique id of the block entity.
     * @param block The block that is attached to the block entity.
     * @param blockEntity The block entity lambda. (BlockEntity::new)
     * @param <T> Extends of BlockEntity.
     * @return The block entity itself.
     */
    protected static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(String id, Block block, FabricBlockEntityTypeBuilder.Factory<T> blockEntity){
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(PulgaCore.MOD_ID, id), FabricBlockEntityTypeBuilder.create(blockEntity, block).build());
    }

    /**
     * Call this method to register all the block entities.
     * @see net.pulga22.pulgacore.PulgaCore#setModId(String)
     */
    protected static void registerBlockEntities(){
        register("blockentities");
    }

}
