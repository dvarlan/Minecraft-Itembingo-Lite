package bingotests.bingotests;

import bingotests.bingotests.commands.*;
import bingotests.bingotests.listeners.InventoryListener;
import bingotests.bingotests.listeners.JoinListener;
import bingotests.bingotests.team.TeamHandler;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Objects;

public final class BingoTests extends JavaPlugin {

    private static BingoTests main;
    private TeamHandler handler;
    private Inventory inventory;
    private final ArrayList<ItemStack> bingo = new ArrayList<>();
    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("Bingotests enabled!");

        main = this;
        handler = new TeamHandler();
        inventory = Bukkit.createInventory(null, InventoryType.DROPPER, Component.text(ChatColor.BLUE + "Bingoboard"));

        Objects.requireNonNull(getCommand("top")).setExecutor(new TopCommand());
        Objects.requireNonNull(getCommand("team")).setExecutor(new TeamCommand());
        Objects.requireNonNull(getCommand("bingoboard")).setExecutor(new BingoboardCommand());
        Objects.requireNonNull(getCommand("bingo")).setExecutor(new BingoCommand());
        Objects.requireNonNull(getCommand("bingohelp")).setExecutor(new HelpCommand());

        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("Bingotests disabled!");
    }

    public static BingoTests getMain() {
        return main;
    }

    public TeamHandler getHandler() {
        return handler;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public ArrayList<ItemStack> getBingo() {
        return bingo;
    }
}
