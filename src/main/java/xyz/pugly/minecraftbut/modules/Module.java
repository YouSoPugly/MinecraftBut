package xyz.pugly.minecraftbut.modules;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.pugly.minecraftbut.MinecraftBut;

public abstract class Module {

    protected final MinecraftBut plugin = MinecraftBut.get();
    protected String sectionName;
    public boolean isEnabled = false;
    protected ConfigurationSection section;
    public String name = "<red>default module name ";
    public String description = "<red>default module description";

    public Module(String sectionName) {
        this.sectionName = sectionName;
        ModuleHandler.registerModule(this);
        reload();
    }

    public void reload() {
        plugin.getLogger().info("Loading module " + sectionName);
        section = plugin.getModuleConfig(sectionName);
        if (section == null) {
            plugin.getLogger().warning("Module " + sectionName + " has no config section!");
            return;
        }
        isEnabled = section.getBoolean("enabled");
        name = section.getString("name");
        description = section.getString("description");
    }
}
