package net.pulga22.pulgacore.datasaver;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AbstractSaver<T> {

    private final Map<UUID, T> players = new HashMap<>();

    public void addPlayerIfNull(UUID uuid, NullHandler<T> handler) {
        if (!players.containsKey(uuid)){
            players.put(uuid, handler.run());
        }
    }

    public T getPlayer(UUID uuid){
        return players.get(uuid);
    }

    public void setPlayer(UUID uuid, T t){
        players.put(uuid, t);
    }

    public boolean playerActive(UUID uuid){
        return players.containsKey(uuid);
    }

}
