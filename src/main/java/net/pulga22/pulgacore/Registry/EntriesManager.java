package net.pulga22.pulgacore.Registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public abstract class EntriesManager {

    private static final List<Block> BLOCK_ENTRIES = new ArrayList<>();
    private static final List<Item> ITEM_ENTRIES = new ArrayList<>();

    protected static void addBlockEntry(Block block){
        BLOCK_ENTRIES.add(block);
    }

    protected static void addItemEntry(Item item){
        ITEM_ENTRIES.add(item);
    }

    public static List<Block> getBlockEntries(){
        return BLOCK_ENTRIES;
    }

    public static List<Item> getItemEntries(){
        return ITEM_ENTRIES;
    }

}
