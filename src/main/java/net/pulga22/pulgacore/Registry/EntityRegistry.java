package net.pulga22.pulgacore.Registry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.pulga22.pulgacore.PulgaCore;

public abstract class EntityRegistry extends AbstractRegistry{

    /**
     * @param id The unique id of the entity.
     * @param entity The entity type which is being registered.
     * @param <T> Extends of Entity.
     * @return The entity type registered.
     */
    protected static <T extends Entity> EntityType<T> registerEntity(String id, EntityType<T> entity){
        return Registry.register(Registries.ENTITY_TYPE, new Identifier(PulgaCore.MOD_ID, id), entity);
    }

    /**
     * Call this method to register all the entities.
     * @see net.pulga22.pulgacore.PulgaCore#setModId(String)
     */
    public static void register(){
        register("entities");
    }

}
