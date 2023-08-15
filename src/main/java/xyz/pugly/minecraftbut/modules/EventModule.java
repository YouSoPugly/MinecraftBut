package xyz.pugly.minecraftbut.modules;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import xyz.pugly.minecraftbut.MinecraftBut;

public abstract class EventModule extends Module implements Listener {
    public EventModule(String sectionName) {
        super(sectionName);
        Bukkit.getPluginManager().registerEvents(this, MinecraftBut.get());
    }
}
