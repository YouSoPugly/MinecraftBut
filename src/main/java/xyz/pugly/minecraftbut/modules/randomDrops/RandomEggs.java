package xyz.pugly.minecraftbut.modules.randomDrops;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDropItemEvent;
import xyz.pugly.minecraftbut.modules.EventModule;

public class RandomEggs extends EventModule {
    public RandomEggs() {
        super("random_eggs");
    }

    @EventHandler
    public void onEggLay(EntityDropItemEvent event) {
        if (!isEnabled) return;
        if (event.getEntity().getType() != EntityType.CHICKEN) return;
        if (event.getItemDrop().getItemStack().getType() != Material.EGG) return;

        event.setCancelled(true);
        Location l = event.getEntity().getLocation();
        l.getWorld().dropItemNaturally(l, new org.bukkit.inventory.ItemStack(Material.values()[(int) (Math.random()*Material.values().length)]));
    }
}
