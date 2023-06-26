package net.pulga22.pulgacore.Registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.pulga22.pulgacore.PulgaCore;

public abstract class ItemRegistry extends AbstractRegistry{

    /**
     * @param id The unique id of the item.
     * @return A generic item without anything special.
     */
    protected static Item registerGeneric(String id) {
        return registerItem(id, new Item(new FabricItemSettings()));
    }

    /**
     * @param id The unique id of the item.
     * @return A generic item of max count 1 with anything special.
     */
    protected static Item registerGenericMaxCountOne(String id) {
        return registerItem(id, new Item(new FabricItemSettings().maxCount(1)));
    }

    /**
     * @param id The unique id of the item.
     * @param item The item which is being registered.
     * @return The registered item.
     */
    protected static Item registerItem(String id, Item item) {
        Item item1 = Registry.register(Registries.ITEM, new Identifier(PulgaCore.MOD_ID, id), item);
        EntriesManager.addItemEntry(item1);
        return item1;
    }
    /**
     * Call this method to register all the items.
     * @see net.pulga22.pulgacore.PulgaCore#setModId(String)
     */
    protected static void registerItems(){
        register("items");
    }

}
