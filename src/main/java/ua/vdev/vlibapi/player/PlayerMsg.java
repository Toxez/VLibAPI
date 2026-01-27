package ua.vdev.vlibapi.player;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;
import ua.vdev.vlibapi.util.TextColor;

import java.util.Map;

@UtilityClass
public class PlayerMsg {

    public void send(Player player, String msg) {
        player.sendMessage(TextColor.parse(msg));
    }

    public void send(Player player, String msg, Map<String, String> replacements) {
        player.sendMessage(TextColor.parse(msg, replacements));
    }

    public void action(Player player, String msg) {
        player.sendActionBar(TextColor.parse(msg));
    }

    public void title(Player player, String title, String subtitle) {
        var t = TextColor.parse(title);
        var s = TextColor.parse(subtitle);
        player.showTitle(Title.title(t, s));
    }
}