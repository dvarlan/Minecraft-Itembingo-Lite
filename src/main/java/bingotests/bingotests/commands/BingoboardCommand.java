package bingotests.bingotests.commands;

import bingotests.bingotests.BingoTests;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class BingoboardCommand implements CommandExecutor {

    Inventory inventory = BingoTests.getMain().getInventory();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // This Command is only for testing right now
        if(!(sender instanceof Player)) {
            Bukkit.getLogger().info("Only players can use this command!");
            return false;
        }

        // Convert sender to player
        Player player = (Player) sender;

        // Fills board with random items and stores them in a list for checking later
        if (args.length != 0 && args[0].equalsIgnoreCase("random")) {
            for (int i = 0; i < 9; ++i) {
               ItemStack item = new ItemStack(getRandom());
               player.sendMessage(item.toString());
               inventory.setItem(i, item);
               BingoTests.getMain().getBingo().add(item);
            }
            player.openInventory(inventory);
            return false;
        }

        player.openInventory(inventory);
        return false;
    }

    private Material getRandom() {
        Material m = Material.values()[new Random().nextInt(Material.values().length)];
        /* Very basic filter, still returns a lot of unobtainable / null items though
        For now you just have to reuse the command until you get a working board */
        if (m.isAir() || m.isLegacy() || !m.isSolid() || m.toString().endsWith("SPAWN_EGG") || m.toString().contains("ENCHANTED") || m.toString().contains("WALL") || m.toString().contains("AIR")) {
            return getRandom();
        }
        return m;
    }

}
