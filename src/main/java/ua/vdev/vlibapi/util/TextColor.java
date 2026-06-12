package ua.vdev.vlibapi.util;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class TextColor {

    private final MiniMessage MM = MiniMessage.miniMessage();
    private final Pattern SECTION_HEX_PATTERN = Pattern.compile("§x§([0-9A-Fa-f])§([0-9A-Fa-f])§([0-9A-Fa-f])§([0-9A-Fa-f])§([0-9A-Fa-f])§([0-9A-Fa-f])");
    private final Pattern HEX_PATTERN = Pattern.compile("[&§]#([A-Fa-f0-9]{6})");
    private final Pattern LEGACY_PATTERN = Pattern.compile("[&§]([0-9a-fk-orA-FK-OR])");

    public Component parse(String msg) {
        return parse(msg, null);
    }

    public Component parse(String msg, Map<String, String> placeholders) {
        return Optional.ofNullable(msg)
                .filter(s -> !s.isEmpty())
                .map(s -> applyPlaceholders(s, placeholders))
                .map(TextColor::toMiniMessage)
                .map(MM::deserialize)
                .orElse(Component.empty());
    }

    private String applyPlaceholders(String msg, Map<String, String> placeholders) {
        if (placeholders == null || placeholders.isEmpty()) return msg;
        for (var entry : placeholders.entrySet()) {
            msg = msg.replace("{" + entry.getKey() + "}", entry.getValue());
        }
        return msg;
    }

    private String toMiniMessage(String msg) {
        if (msg.contains("§x") || msg.contains("§X")) {
            Matcher sectionHexMatcher = SECTION_HEX_PATTERN.matcher(msg);
            StringBuilder sb = new StringBuilder();
            while (sectionHexMatcher.find()) {
                sectionHexMatcher.appendReplacement(sb, "<reset><#" +
                        sectionHexMatcher.group(1) + sectionHexMatcher.group(2) + sectionHexMatcher.group(3) +
                        sectionHexMatcher.group(4) + sectionHexMatcher.group(5) + sectionHexMatcher.group(6) + ">");
            }
            sectionHexMatcher.appendTail(sb);
            msg = sb.toString();
        }

        if (msg.contains("#")) {
            Matcher hexMatcher = HEX_PATTERN.matcher(msg);
            StringBuilder sb = new StringBuilder();
            while (hexMatcher.find()) {
                hexMatcher.appendReplacement(sb, "<reset><#" + hexMatcher.group(1) + ">");
            }
            hexMatcher.appendTail(sb);
            msg = sb.toString();
        }

        Matcher legacyMatcher = LEGACY_PATTERN.matcher(msg);
        StringBuilder sb = new StringBuilder();
        while (legacyMatcher.find()) {
            char code = Character.toLowerCase(legacyMatcher.group(1).charAt(0));
            String replacement = switch (code) {
                case '0' -> "<reset><black>";
                case '1' -> "<reset><dark_blue>";
                case '2' -> "<reset><dark_green>";
                case '3' -> "<reset><dark_aqua>";
                case '4' -> "<reset><dark_red>";
                case '5' -> "<reset><dark_purple>";
                case '6' -> "<reset><gold>";
                case '7' -> "<reset><gray>";
                case '8' -> "<reset><dark_gray>";
                case '9' -> "<reset><blue>";
                case 'a' -> "<reset><green>";
                case 'b' -> "<reset><aqua>";
                case 'c' -> "<reset><red>";
                case 'd' -> "<reset><light_purple>";
                case 'e' -> "<reset><yellow>";
                case 'f' -> "<reset><white>";
                case 'k' -> "<obfuscated>";
                case 'l' -> "<bold>";
                case 'm' -> "<strikethrough>";
                case 'n' -> "<underlined>";
                case 'o' -> "<italic>";
                case 'r' -> "<reset>";
                default -> legacyMatcher.group(0);
            };
            legacyMatcher.appendReplacement(sb, replacement);
        }
        legacyMatcher.appendTail(sb);

        return sb.toString();
    }
}