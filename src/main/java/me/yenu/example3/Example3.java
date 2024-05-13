package me.yenu.example3;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.C;

public final class Example3 extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getCommandMap().register("", new Command("random"));

    }

    @Override
    public void onDisable() {

    }
}
