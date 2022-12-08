package bingotests.bingotests.commands;

import bingotests.bingotests.BingoTests;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Random;

public class BingoboardCommand implements CommandExecutor {

    Inventory inventory = BingoTests.getMain().getInventory();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            Bukkit.getLogger().info("Only players can use this command!");
            return false;
        }

        // Convert sender to player
        Player player = (Player) sender;

        // Fills board with random items and stores them in a list for checking later
        if (args.length != 0 && args[0].equalsIgnoreCase("random")) {

            // Clears the list if the random command is used more than once
            if (!(BingoTests.getMain().getBingo().isEmpty())) {
                BingoTests.getMain().getBingo().clear();
            }

            for (int i = 0; i < 9; ++i) {
               ItemStack item = new ItemStack(getRandom());
               inventory.setItem(i, item);
               BingoTests.getMain().getBingo().add(item);
            }
            player.openInventory(inventory);
            return false;
        } else if (args.length != 0 && args[0].equalsIgnoreCase("add")) {
            // Lets the user add his own items (need to follow the Material syntax)
            if (args.length != 10) {
                player.sendMessage(ChatColor.BLUE + "[Bingo] "
                        + ChatColor.GRAY + "Please specify all 9 Items at once! Like this: \n" +
                        "/bingoboard add ITEM_1 ITEM_2 ...");
                return false;
            }

            try {
                for (int i = 1; i < args.length; ++i) {
                    ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(args[i])));
                    player.sendMessage(ChatColor.BLUE + "[Bingo] "
                            + ChatColor.GRAY + "You requested the Item: " + item);
                    inventory.setItem(i - 1, item);
                    BingoTests.getMain().getBingo().add(item);
                }
                player.openInventory(inventory);
            } catch (NullPointerException e) {
                inventory.clear();
                BingoTests.getMain().getBingo().clear();
                player.sendMessage(ChatColor.BLUE + "[Bingo] "
                        + ChatColor.GRAY + "Could not find the specified items! List the items like this: ACACIA_PLANKS BLACK_DYE ...");
            }
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
