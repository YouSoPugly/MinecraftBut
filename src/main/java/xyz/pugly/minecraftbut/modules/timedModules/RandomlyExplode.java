package xyz.pugly.minecraftbut.modules.timedModules;

import org.bukkit.Bukkit;
import xyz.pugly.minecraftbut.modules.TimedModule;

public class RandomlyExplode extends TimedModule {

    double chance;

    public RandomlyExplode() {
        super("randomly_explode");
        chance = section.getDouble("chance");
    }

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(p -> {
            if (Math.random() < chance) {
                p.getWorld().createExplosion(p.getLocation(), 4);
            }
        });
    }
}
