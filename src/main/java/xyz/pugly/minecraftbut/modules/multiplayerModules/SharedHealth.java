package xyz.pugly.minecraftbut.modules.multiplayerModules;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import xyz.pugly.minecraftbut.modules.EventModule;
import xyz.pugly.minecraftbut.modules.Module;

public class SharedHealth extends EventModule {

    public SharedHealth() {
        super("shared_health");
    }

    @EventHandler
    public void onTakeDamage(EntityDamageEvent e) {
        if (!isEnabled) return;

        if (e.getEntity().getType() != EntityType.PLAYER) return;

        for (org.bukkit.entity.Player p : e.getEntity().getWorld().getPlayers()) {
            if (p != e.getEntity()) {
                p.setHealth(((Player)e.getEntity()).getHealth());
            }
        }
    }
}
