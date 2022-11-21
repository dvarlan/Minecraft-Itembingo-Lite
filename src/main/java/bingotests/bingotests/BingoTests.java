package bingotests.bingotests;

import bingotests.bingotests.commands.TeamCommand;
import bingotests.bingotests.commands.TopCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class BingoTests extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("Bingotests enabled!");

        Objects.requireNonNull(getCommand("top")).setExecutor(new TopCommand());
        Objects.requireNonNull(getCommand("team")).setExecutor(new TeamCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("Bingotests disabled!");
    }
}
