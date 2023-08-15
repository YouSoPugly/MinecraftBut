package xyz.pugly.minecraftbut;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.pugly.minecraftbut.modules.randomDrops.RandomEggs;
import xyz.pugly.minecraftbut.modules.soloModules.MovementEnchants;
import xyz.pugly.minecraftbut.modules.multiplayerModules.SharedDeath;
import xyz.pugly.minecraftbut.modules.multiplayerModules.SharedHealth;
import xyz.pugly.minecraftbut.modules.randomDrops.RandomBlockDrops;
import xyz.pugly.minecraftbut.modules.randomDrops.RandomMobDrops;
import xyz.pugly.minecraftbut.modules.timedModules.RandomEffects;
import xyz.pugly.minecraftbut.modules.timedModules.RandomItems;
import xyz.pugly.minecraftbut.utils.CommandHandler;

public final class MinecraftBut extends JavaPlugin {

    private CommandHandler commandHandler;
    public static String title = "";

    @Override
    public void onEnable() {
        // Plugin startup logic
        setupConfig();
        title = MinecraftBut.get().getConfig().getString("title");

        commandHandler = new CommandHandler("minecraftbut", this);
        commandHandler.registerCommand(new xyz.pugly.minecraftbut.commands.ReloadCommand());
        commandHandler.registerCommand(new xyz.pugly.minecraftbut.commands.InfoCommand());
        commandHandler.registerCommand(new xyz.pugly.minecraftbut.commands.ListCommand());

        new MovementEnchants();
        new RandomMobDrops();
        new RandomBlockDrops();
        new SharedDeath();
        new SharedHealth();
        new RandomItems();
        new RandomEffects();
        new RandomEggs();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static MinecraftBut get() {
        return MinecraftBut.getPlugin(MinecraftBut.class);
    }

    public void setupConfig()
    {
        // Initializes the config with default values
        this.getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public ConfigurationSection getModuleConfig(String module)
    {
        // Gets the config for a module
        return this.getConfig().getConfigurationSection("modules").getConfigurationSection(module);
    }

    public static void reload()
    {
        // Reloads the config
        MinecraftBut.get().reloadConfig();
        title = MinecraftBut.get().getConfig().getString("title");
    }
}
