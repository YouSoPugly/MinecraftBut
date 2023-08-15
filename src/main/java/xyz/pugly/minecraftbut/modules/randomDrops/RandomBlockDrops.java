package xyz.pugly.minecraftbut.modules.randomDrops;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import xyz.pugly.minecraftbut.modules.EventModule;
import xyz.pugly.minecraftbut.modules.Module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class RandomBlockDrops extends EventModule {

    private HashMap<Material, Material> mapping;

    public RandomBlockDrops() {
        super("random_block_drops");
    }

    public void reload() {
        super.reload();

        ArrayList<Material> materials = new ArrayList<Material>(Arrays.asList(Material.values()));

        mapping = new HashMap<Material, Material>();

        for (Material m : Material.values()) {
            mapping.put(m, materials.get((int) (Math.random()*materials.size())));
            materials.remove(mapping.get(m));
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!isEnabled) return;

        event.setDropItems(false);
        Location l = event.getBlock().getLocation();

        l.getWorld().dropItemNaturally(l, new org.bukkit.inventory.ItemStack(mapping.get(event.getBlock().getType())));
    }
}
