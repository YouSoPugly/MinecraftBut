package xyz.pugly.minecraftbut.modules;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public abstract class TimedModule extends Module {

    BukkitTask task;
    protected long delay = 1L;

    public TimedModule(String sectionName) {
        super(sectionName);
    }

    @Override
    public void reload() {
        super.reload();
        delay = section.getLong("delay");
        startTimer();
    }

    public void startTimer() {
        if (task != null) {
            task.cancel();
        }
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        task = scheduler.runTaskTimer(plugin, () -> {
            if (isEnabled) {
                run();
            }
        }, 0L, 1L);
    }

    public void stopTimer() {
        task.cancel();
    }

    public abstract void run();
}
