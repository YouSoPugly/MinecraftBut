package xyz.pugly.minecraftbut.modules.soloModules;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import xyz.pugly.minecraftbut.MinecraftBut;
import xyz.pugly.minecraftbut.modules.EventModule;

import java.util.HashMap;

public class MovementEnchants extends EventModule {

    static HashMap<Player, Integer> traveled = new HashMap<Player, Integer>();

    public MovementEnchants() {
        super("movement_enchants");
    }

    @EventHandler
    public void onMove(org.bukkit.event.player.PlayerMoveEvent event) {
        if (!isEnabled)
            return;

        if (event.getTo().getBlock().getX() == event.getFrom().getBlock().getX() && event.getTo().getBlock().getZ() == event.getFrom().getBlock().getZ())
            return;

        traveled.put(event.getPlayer(), traveled.getOrDefault(event.getPlayer(), 0) + 1);

        if (traveled.get(event.getPlayer()) >= section.getInt("distance")) {
            randomEnchant(event.getPlayer());
            traveled.put(event.getPlayer(), 0);
        }
    }

    private void randomEnchant(Player player) {
        if (player.getInventory().isEmpty()) return;

        Enchantment enchantment = Enchantment.values()[(int) (Math.random() * Enchantment.values().length)];
        int slot = (int) (Math.random() * player.getInventory().getContents().length);

        while (player.getInventory().getContents()[slot] == null) {
            slot = (int) (Math.random() * player.getInventory().getContents().length);
        }

        player.getInventory().getContents()[slot].addUnsafeEnchantment(enchantment, player.getInventory().getContents()[slot].getEnchantmentLevel(enchantment) + 1);

        if (!section.getBoolean("messages")) return;
        MiniMessage mm = MiniMessage.miniMessage();
        Component parsed = mm.deserialize(name + " <green>Enchanted <white>" + player.getInventory().getContents()[slot].getType().name() + " <green>with <white>" + enchantment.getKey().getKey() + " " + player.getInventory().getContents()[slot].getEnchantmentLevel(enchantment));
        player.sendMessage(parsed);
    }

}
