package bingotests.bingotests.commands;

import bingotests.bingotests.BingoTests;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;


public class BingoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // Checks if the user (or maybe the team) has all the Bingoboard items
        if(!(sender instanceof Player)) {
            Bukkit.getLogger().info("Only players can use this command!");
            return false;
        }

        Player player = (Player) sender;
        for(ItemStack i : BingoTests.getMain().getBingo()) {
            //DEBUG player.sendMessage(i.toString());
            if(!(player.getInventory().contains(i))) {
                player.sendMessage("You did not obtain every item!");
                return false;
            }
        }
        player.sendMessage("You obtained every item!");
        return false;
    }
}
