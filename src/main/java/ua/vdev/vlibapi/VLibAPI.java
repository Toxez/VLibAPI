package ua.vdev.vlibapi;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import ua.vdev.vlibapi.util.LogUtil;

public final class VLibAPI extends JavaPlugin {
    
    @Getter private static VLibAPI instance;
    private LogUtil log;

    @Override
    public void onEnable() {
        instance = this;
        log = LogUtil.of(this);
        log.info("Версия библиотеки - 1.0.1-RELEASE");
    }

    @Override
    public void onDisable() {
        instance = null;
    }
}
