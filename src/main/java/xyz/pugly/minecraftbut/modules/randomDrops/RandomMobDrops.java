package xyz.pugly.minecraftbut.modules.randomDrops;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import xyz.pugly.minecraftbut.modules.EventModule;
import xyz.pugly.minecraftbut.modules.Module;

import java.util.ArrayList;

public class RandomMobDrops extends EventModule {

    public RandomMobDrops() {
        super("random_mob_drops");
    }

    @EventHandler
    public void onMobDeath(EntityDeathEvent event) {
        if (!isEnabled) return;

        if (event.getEntity().getType() == EntityType.PLAYER) return;

        event.getDrops().clear();

        int itemCount = section.getInt("min_items") + (int) (Math.random() * (section.getInt("max_items") - section.getInt("min_items")));

        for (int i = 0; i < itemCount; i++) {
            Location l = event.getEntity().getLocation();
            l.getWorld().dropItem(l, new ItemStack(Material.values()[(int) (Math.random()*Material.values().length)]));
        }
    }

}
