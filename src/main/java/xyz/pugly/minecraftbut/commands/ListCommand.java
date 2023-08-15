package xyz.pugly.minecraftbut.commands;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import xyz.pugly.minecraftbut.modules.ModuleHandler;
import xyz.pugly.minecraftbut.utils.Command;

import java.util.Arrays;
import java.util.List;

import static xyz.pugly.minecraftbut.MinecraftBut.title;

public class ListCommand extends Command {

    public ListCommand() {
        super(Arrays.asList("list"), "List all modules", "minecraftbut.list", false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        MiniMessage mm = MiniMessage.miniMessage();
        sender.sendMessage(mm.deserialize(title + " <gray>Modules:"));
        ModuleHandler.getModules().forEach(module -> {
            sender.sendMessage(mm.deserialize("<gray> - <white>" + module.name + " : " + (module.isEnabled ? "<green>Enabled" : "<red>Disabled")));
        });
    }

    @Override
    public List<String> TabComplete(CommandSender cs, org.bukkit.command.Command cmd, String s, String[] args) {
        return null;
    }
}
