package ua.vdev.vlibapi.util;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.Map;

@UtilityClass
public class TextColor {
    private final MiniMessage MM = MiniMessage.miniMessage();
    private final LegacyComponentSerializer LEGACY = LegacyComponentSerializer.builder()
            .hexColors()
            .character('&')
            .hexCharacter('#')
            .build();

    public Component parse(String msg) {
        if (msg == null || msg.isEmpty()) return Component.empty();
        return parseWithMixed(msg);
    }

    public Component parse(String msg, Map<String, String> placeholders) {
        if (msg == null || msg.isEmpty()) return Component.empty();

        if (placeholders != null) {
            for (var entry : placeholders.entrySet()) {
                msg = msg.replace("{" + entry.getKey() + "}", entry.getValue());
            }
        }
        return parseWithMixed(msg);
    }

    private Component parseWithMixed(String msg) {
        if (msg.contains("&") || msg.contains("§")) {
            Component fromLegacy = LEGACY.deserialize(msg);
            String asMini = MM.serialize(fromLegacy);
            return MM.deserialize(asMini);
        }
        return MM.deserialize(msg);
    }
}