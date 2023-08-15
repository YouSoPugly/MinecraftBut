package xyz.pugly.minecraftbut.commands;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import xyz.pugly.minecraftbut.MinecraftBut;
import xyz.pugly.minecraftbut.modules.ModuleHandler;
import xyz.pugly.minecraftbut.utils.Command;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InfoCommand extends Command {
    public InfoCommand() {
        super(Arrays.asList("info", "i"), "Get module info", "minecraftbut.info", false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        MiniMessage mm = MiniMessage.miniMessage();

        ModuleHandler.getModules().forEach(module -> {
            if (mm.stripTags(module.name).equalsIgnoreCase(String.join(" ", Arrays.copyOfRange(args, 1, args.length)))) {
                sender.sendMessage(mm.deserialize("<gray>Module: <white>" + module.name));
                sender.sendMessage(mm.deserialize("<gray>Description: <white>" + module.description));
                sender.sendMessage(mm.deserialize("<gray>Enabled: <white>" + module.isEnabled));
                return;
            }
        });

        sender.sendMessage(mm.deserialize(MinecraftBut.title + " <gray>Module not found"));
    }

    @Override
    public List<String> TabComplete(CommandSender cs, org.bukkit.command.Command cmd, String s, String[] args) {
        MiniMessage mm = MiniMessage.miniMessage();
        if (args.length == 2)
            return ModuleHandler.getModules().stream().map(module -> mm.stripTags(module.name)).collect(Collectors.toList());

        return null;
    }
}
