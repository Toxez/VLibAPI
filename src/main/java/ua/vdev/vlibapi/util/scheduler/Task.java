package ua.vdev.vlibapi.util.scheduler;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;
import ua.vdev.vlibapi.VLibAPI;

import java.util.function.Consumer;

@UtilityClass
public class Task {

    public BukkitTask sync(Runnable runnable) {
        return Bukkit.getScheduler().runTask(VLibAPI.getInstance(), runnable);
    }

    public BukkitTask later(long delay, Runnable runnable) {
        return Bukkit.getScheduler().runTaskLater(VLibAPI.getInstance(), runnable, delay);
    }

    public BukkitTask timer(long delay, long period, Runnable runnable) {
        return Bukkit.getScheduler().runTaskTimer(VLibAPI.getInstance(), runnable, delay, period);
    }

    public BukkitTask async(Runnable runnable) {
        return Bukkit.getScheduler().runTaskAsynchronously(VLibAPI.getInstance(), runnable);
    }

    public BukkitTask laterAsync(long delay, Runnable runnable) {
        return Bukkit.getScheduler().runTaskLaterAsynchronously(VLibAPI.getInstance(), runnable, delay);
    }

    public BukkitTask timerAsync(long delay, long period, Runnable runnable) {
        return Bukkit.getScheduler().runTaskTimerAsynchronously(VLibAPI.getInstance(), runnable, delay, period);
    }

    public void timerSelf(long delay, long period, Consumer<BukkitTask> consumer) {
        Bukkit.getScheduler().runTaskTimer(VLibAPI.getInstance(), task -> consumer.accept(task), delay, period);
    }

    public void timerAsyncSelf(long delay, long period, Consumer<BukkitTask> consumer) {
        Bukkit.getScheduler().runTaskTimerAsynchronously(VLibAPI.getInstance(), task -> consumer.accept(task), delay, period);
    }
}