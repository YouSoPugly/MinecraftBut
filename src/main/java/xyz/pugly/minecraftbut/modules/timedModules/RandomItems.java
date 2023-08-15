package xyz.pugly.minecraftbut.modules.timedModules;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import xyz.pugly.minecraftbut.modules.TimedModule;

public class RandomItems extends TimedModule {
    public RandomItems() {
        super("random_items");
    }

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(p -> {
            p.getInventory().addItem(new ItemStack(Material.values()[((int) (Math.random()*Material.values().length))]));
        });
    }
}
