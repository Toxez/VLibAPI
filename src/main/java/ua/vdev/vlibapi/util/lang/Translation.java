package ua.vdev.vlibapi.util.lang;

import net.kyori.adventure.text.Component;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import ua.vdev.vlibapi.util.LogUtil;
import ua.vdev.vlibapi.util.TextColor;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Translation {

    private final Plugin plugin;
    private final LogUtil log;
    private final Map<String, String> messages = new ConcurrentHashMap<>();

    public Translation(Plugin plugin) {
        this.plugin = plugin;
        this.log = LogUtil.of(plugin);
    }

    public void load(String langName) {
        messages.clear();
        File folder = new File(plugin.getDataFolder(), "lang");
        File file = new File(folder, langName + ".yml");

        if (!file.exists()) {
            Optional.ofNullable(plugin.getResource("lang/" + langName + ".yml"))
                    .ifPresentOrElse(
                            in -> plugin.saveResource("lang/" + langName + ".yml", false),
                            () -> {
                                folder.mkdirs();
                                try { file.createNewFile(); } catch (Exception ignored) {}
                                log.warn("языковой файл {}.yml не найден в джарнике, на всякий будет создан такой пустой", langName);
                            }
                    );
        }

        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        config.getValues(true).forEach((key, val) -> {
            if (val instanceof String || val instanceof List) {
                messages.put(key, parseValue(val));
            }
        });

        log.info("загружена локализация: {} ({} ключей)", langName, messages.size());
    }

    private String parseValue(Object v) {
        return (v instanceof List<?> list)
                ? list.stream().map(Object::toString).collect(Collectors.joining("\n"))
                : String.valueOf(v);
    }

    public Optional<String> find(String key) {
        return Optional.ofNullable(messages.get(key));
    }

    public Component get(String key) {
        return TextColor.parse(messages.getOrDefault(key, "<missing:" + key + ">"));
    }

    public Component get(String key, Map<String, String> placeholders) {
        return find(key)
                .map(raw -> TextColor.parse(raw, placeholders))
                .orElseGet(() -> TextColor.parse("<missing:" + key + ">"));
    }
}