package xyz.pugly.minecraftbut.modules.multiplayerModules;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import xyz.pugly.minecraftbut.modules.EventModule;
import xyz.pugly.minecraftbut.modules.Module;

public class SharedDeath extends EventModule {
    public SharedDeath() {
        super("shared_death");
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        if (!isEnabled) return;

        if (e.getEntity().getType() != EntityType.PLAYER) return;

        if (e.getEntity().getLastDamageCause() == null) return;
        if (e.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.CUSTOM) return;

        for (org.bukkit.entity.Player p : e.getEntity().getWorld().getPlayers()) {
            if (p != e.getEntity()) {
                p.setLastDamageCause(new EntityDamageEvent(p, EntityDamageEvent.DamageCause.CUSTOM, p.getHealth()*10));
            }
        }
    }
}
