package xyz.pugly.minecraftbut.modules.timedModules;

import org.bukkit.Bukkit;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.pugly.minecraftbut.modules.TimedModule;

public class RandomEffects extends TimedModule {
    public RandomEffects() {
        super("random_effects");
    }

    @Override
    public void run() {
        int duration = section.getInt("duration");

        if (section.getBoolean("individual_effects")) {
            PotionEffect effect = new PotionEffect(PotionEffectType.values()[(int) (Math.random() * PotionEffectType.values().length)], duration, (int) Math.floor((Math.random()*2)));
            Bukkit.getOnlinePlayers().forEach(p -> {
                p.addPotionEffect(effect);
            });
            return;
        }
        Bukkit.getOnlinePlayers().forEach(p -> {
            PotionEffect effect = new PotionEffect(PotionEffectType.values()[(int) (Math.random() * PotionEffectType.values().length)], duration, (int) Math.floor((Math.random()*2)));
            p.addPotionEffect(effect);
        });
    }
}
