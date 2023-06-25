package net.pulga22.pulgacore.Registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.pulga22.pulgacore.PulgaCore;

public abstract class BlockRegistry extends AbstractRegistry {

    /**
     * @param id The unique id of the block.
     * @return A generic block without anything special.
     */
    protected static Block registerGeneric(String id){
        return registerBlock(id, new Block(settingsOf(Blocks.STONE)));
    }

    /**
     * @param id The unique id of the block item.
     * @param block The block which item is being registered.
     * @return The registered block item.
     */
    protected static Item registerBlockItem(String id, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(PulgaCore.MOD_ID, id), new BlockItem(block, new FabricItemSettings()));
    }

    /**
     * Registers block and block item.
     * @param id The unique id of the block.
     * @param block The block which is being registered.
     * @return The registered block.
     */
    protected static Block registerBlock(String id, Block block) {
        Item item = registerBlockItem(id, block);
        return registerOnlyBlock(id, block);
    }

    /**
     * @param id The unique id of the block.
     * @param block The block which is being registered.
     * @return The registered block.
     */
    protected static Block registerOnlyBlock(String id, Block block){
        Block block1 = Registry.register(Registries.BLOCK, new Identifier(PulgaCore.MOD_ID, id), block);
        EntriesManager.addBlockEntry(block1);
        return block1;
    }

    /**
     * Shortcut.
     * @param block The block to copy the settings.
     * @return The setting of that block.
     */
    protected static FabricBlockSettings settingsOf(Block block){
        return FabricBlockSettings.copyOf(block);
    }

    /**
     * Shortcut for block predicates.
     * @param state The block state.
     * @param world Thw world.
     * @param pos The block pos.
     * @return True
     */
    protected static boolean always(BlockState state, BlockView world, BlockPos pos) {return true;}

    /**
     * Call this method to register all the blocks.
     * @see net.pulga22.pulgacore.PulgaCore#setModId(String)
     */
    public static void registerBlocks() {
        register("blocks");
    }
}
