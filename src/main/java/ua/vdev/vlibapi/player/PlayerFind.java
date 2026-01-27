package ua.vdev.vlibapi.player;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

@UtilityClass
public class PlayerFind {

    public Optional<Player> name(String name) {
        return Optional.ofNullable(Bukkit.getPlayer(name));
    }

    public Optional<Player> uuid(UUID uuid) {
        return Optional.ofNullable(Bukkit.getPlayer(uuid));
    }

    public boolean isOnline(String name) {
        return Bukkit.getPlayerExact(name) != null;
    }

    public Iterable<? extends Player> all() {
        return Bukkit.getOnlinePlayers();
    }
}