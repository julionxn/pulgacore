package net.pulga22.pulgacore.Registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.pulga22.pulgacore.PulgaCore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class EntriesManager {

    private static final Map<String, List<Block>> BLOCK_ENTRIES = new HashMap<>();
    private static final Map<String, List<Item>> ITEM_ENTRIES = new HashMap<>();

    protected static void addBlockEntry(Block block){
        BLOCK_ENTRIES.get(PulgaCore.MOD_ID).add(block);
    }

    public static void addKeys(String id){
        BLOCK_ENTRIES.put(id, new ArrayList<>());
        ITEM_ENTRIES.put(id, new ArrayList<>());
    }

    protected static void addItemEntry(Item item){
        ITEM_ENTRIES.get(PulgaCore.MOD_ID).add(item);
    }

    protected static List<Block> getBlockEntries(String id){
        return BLOCK_ENTRIES.get(id);
    }

    protected static List<Item> getItemEntries(String id){
        return ITEM_ENTRIES.get(id);
    }

}
