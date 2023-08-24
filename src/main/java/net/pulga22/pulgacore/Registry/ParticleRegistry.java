package net.pulga22.pulgacore.Registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.pulga22.pulgacore.PulgaCore;

import java.util.HashMap;
import java.util.Map;

public abstract class ParticleRegistry extends AbstractRegistry{

    private static final Map<Identifier, Map<DefaultParticleType, ParticleFactoryRegistry.PendingParticleFactory<DefaultParticleType>>> ENTRIES = new HashMap<>();

    protected static DefaultParticleType registerSimple(String id, ParticleFactoryRegistry.PendingParticleFactory<DefaultParticleType> factory){
        DefaultParticleType particleType = FabricParticleTypes.simple();
        Identifier identifier = new Identifier(PulgaCore.MOD_ID, id);
        Registry.register(Registries.PARTICLE_TYPE, identifier, particleType);
        ENTRIES.put(identifier, new HashMap<>(){{
            put(particleType, factory);
        }});
        return particleType;
    }

    @Environment(EnvType.CLIENT)
    protected static void registerParticlesOnClient(){
        ENTRIES.forEach((id, data) -> {
            data.forEach( (particle, factory) -> {
                ParticleFactoryRegistry.getInstance().register(particle, factory);
            });
        });
    }
    @Environment(EnvType.SERVER)
    protected static  void registerParticlesOnServer(){
        register("particles");
    }


}
