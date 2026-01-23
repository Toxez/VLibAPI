package ua.vdev.vlibapi.util;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import java.util.Map;

@UtilityClass
public class TextColor {
    private final MiniMessage MM = MiniMessage.miniMessage();

    public Component parse(String msg) {
        return (msg == null || msg.isEmpty()) ? Component.empty() : MM.deserialize(msg);
    }

    public Component parse(String msg, Map<String, String> placeholders) {
        if (msg == null || msg.isEmpty()) return Component.empty();

        String result = msg;
        if (placeholders != null) {
            for (var entry : placeholders.entrySet()) {
                result = result.replace("{" + entry.getKey() + "}", entry.getValue());
            }
        }
        return MM.deserialize(result);
    }
}