package net.pulga22.pulgacore.Registry;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.pulga22.pulgacore.PulgaCore;

public abstract class SoundRegistry extends AbstractRegistry{

    /**
     * A sound that volume doesn't decrease with the distance were is generated.
     * @param id The unique id of the sound.
     * @return The registered SoundEvent.
     */
    protected static SoundEvent registerOnmipresentSound(String id){
        Identifier identifier = new Identifier(PulgaCore.MOD_ID, id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    /**
     * A sound that volume decrease with the distance were is generated.
     * @param id The unique id of the sound.
     * @return The registered SoundEvent.
     */
    protected static SoundEvent registerLocalizedSound(String id, int distance){
        Identifier identifier = new Identifier(PulgaCore.MOD_ID, id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier, distance));
    }

    /**
     * Call this method to register all the sounds.
     * @see net.pulga22.pulgacore.PulgaCore#setModId(String)
     */
    protected static void registerSounds(){
        register("sounds");
    }

}
