package ua.vdev.vlibapi.util;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;

import java.util.Map;

@UtilityClass
public class TextColor {

    private final MiniMessage MM = MiniMessage.builder()
            .strict(false)
            .build();

    private final LegacyComponentSerializer LEGACY = LegacyComponentSerializer.builder()
            .character(ChatColor.COLOR_CHAR)
            .hexColors()
            .useUnusualXRepeatedCharacterHexFormat()
            .build();

    public Component parse(String msg) {
        if (msg == null || msg.isEmpty()) return Component.empty();
        return formatMixed(msg);
    }

    public Component parse(String msg, Map<String, String> placeholders) {
        if (msg == null || msg.isEmpty()) return Component.empty();

        if (placeholders != null) {
            for (var entry : placeholders.entrySet()) {
                msg = msg.replace("{" + entry.getKey() + "}", entry.getValue());
            }
        }
        return formatMixed(msg);
    }

    private Component formatMixed(String msg) {
        boolean hasLegacy = msg.indexOf('&') != -1 || msg.indexOf(ChatColor.COLOR_CHAR) != -1;
        boolean hasMini = msg.indexOf('<') != -1 && msg.indexOf('>') != -1;

        if (!hasLegacy && !hasMini) return Component.text(msg);

        if (!hasLegacy) return MM.deserialize(msg);

        if (!hasMini) return LEGACY.deserialize(msg.replace('&', ChatColor.COLOR_CHAR));

        String converted = convertLegacyToMini(msg);
        return MM.deserialize(converted);
    }

    private String convertLegacyToMini(String msg) {
        Component legacy = LEGACY.deserialize(msg.replace('&', ChatColor.COLOR_CHAR));
        return MM.serialize(legacy);
    }
}