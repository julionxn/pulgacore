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

import java.util.HashMap;
import java.util.Map;

public abstract class ItemGroupRegistry extends AbstractRegistry {

    private static final Map<String, ItemGroup> MOD_GROUPS = new HashMap<>();

    /**
     * Call this method to register the item group of the mod.
     * ALWAYS CALL THIS AFTER REGISTERING THE BLOCKS AND ITEMS.
     * After this call the getGroup the reference it from a variable.
     * @param modId The unique id of the mod.
     * @param groupId The unique id of the item group.
     * @param icon Item displayed in the tab.
     */
    protected static ItemGroup registerItemGroup(String modId, String groupId, Item icon){
        ItemGroup MOD_GROUP = Registry.register(Registries.ITEM_GROUP, new Identifier(modId, groupId),
                FabricItemGroup.builder()
                        .displayName(Text.translatable("group." + modId + "." + groupId))
                        .icon(() -> new ItemStack(icon))
                        .entries(((displayContext, entries) -> {
                            for(Block block : EntriesManager.getBlockEntries(modId)){
                                entries.add(block);
                            }
                            for(Item item : EntriesManager.getItemEntries(modId)){
                                entries.add(item);
                            }
                        }))
                        .build());
        MOD_GROUPS.put(modId, MOD_GROUP);
        return MOD_GROUP;
    }


    /**
     * Call this method to register all the blocks.
     * @see net.pulga22.pulgacore.PulgaCore#setModId(String)
     */
    public static void registerItemGroups() {
        register("itemgroups");
    }

}
