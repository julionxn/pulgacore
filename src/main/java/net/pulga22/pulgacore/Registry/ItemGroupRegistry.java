package net.pulga22.pulgacore.Registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.pulga22.pulgacore.PulgaCore;

public abstract class ItemGroupRegistry extends AbstractRegistry {

    public static ItemGroup MOD_GROUP;

    /**
     * Call this method to register the item group of the mod.
     * ALWAYS CALL THIS AFTER REGISTERING THE BLOCKS AND ITEMS.
     * @param id The unique id of the item group.
     * @param icon Item that appear in the tab.
     * @see net.pulga22.pulgacore.PulgaCore#setModId(String)
     */
    public static void register(String id, Item icon){
        MOD_GROUP = Registry.register(Registries.ITEM_GROUP, new Identifier(PulgaCore.MOD_ID, id),
                FabricItemGroup.builder()
                        .displayName(Text.translatable(PulgaCore.MOD_ID + id))
                        .icon(() -> new ItemStack(icon))
                        .entries(((displayContext, entries) -> {
                            for(Block block : EntriesManager.getBlockEntries()){
                                entries.add(block);
                            }
                            for(Item item : EntriesManager.getItemEntries()){
                                entries.add(item);
                            }
                        }))
                        .build());
        register("itemgroup");
    }

}
