package xyz.pugly.minecraftbut.modules;

import org.bukkit.Bukkit;
import xyz.pugly.minecraftbut.MinecraftBut;

import java.util.ArrayList;

public class ModuleHandler {

    static ArrayList<Module> modules = new ArrayList<Module>();

    public static void registerModule(Module module) {
        modules.add(module);
    }

    public static ArrayList<Module> getModules() {
        return modules;
    }

    public static void reload() {
        MinecraftBut.get().getLogger().info("Reloading " + modules.size() + " modules");
        for (Module module : modules) {
            module.reload();
        }
    }

}
