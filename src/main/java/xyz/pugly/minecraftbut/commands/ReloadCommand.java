package xyz.pugly.minecraftbut.commands;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import xyz.pugly.minecraftbut.MinecraftBut;
import xyz.pugly.minecraftbut.modules.ModuleHandler;
import xyz.pugly.minecraftbut.utils.Command;

import java.util.Arrays;
import java.util.List;

public class ReloadCommand extends Command {
    public ReloadCommand() {
        super(Arrays.asList("reload"), "Reload the config", "minecraftbut.reload", false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        MinecraftBut.reload();
        ModuleHandler.reload();
        MiniMessage mm = MiniMessage.miniMessage();
        sender.sendMessage(mm.deserialize("<gray>Reloaded config"));
    }

    @Override
    public List<String> TabComplete(CommandSender cs, org.bukkit.command.Command cmd, String s, String[] args) {
        return null;
    }
}
