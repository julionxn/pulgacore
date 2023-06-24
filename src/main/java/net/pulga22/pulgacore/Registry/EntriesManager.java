package net.pulga22.pulgacore.Registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class EntriesManager {

    public EntriesManager(){

    }

    private final List<Block> BLOCK_ENTRIES = new ArrayList<>();
    private final List<Item> ITEM_ENTRIES = new ArrayList<>();

    protected void addBlockEntry(Block block){
        BLOCK_ENTRIES.add(block);
    }

    protected void addItemEntry(Item item){
        ITEM_ENTRIES.add(item);
    }

    public List<Block> getBlockEntries(){
        return BLOCK_ENTRIES;
    }

    public List<Item> getItemEntries(){
        return ITEM_ENTRIES;
    }

}
