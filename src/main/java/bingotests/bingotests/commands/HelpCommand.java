package bingotests.bingotests.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HelpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            Bukkit.getLogger().info("Only players can use this command!");
            return false;
        }

        // Convert sender to player
        Player player = (Player) sender;

        player.sendMessage(ChatColor.BLUE + "[Bingo Help]\n" +
                "---------------\n" +
                ChatColor.GOLD + "[+] " + "/team | " + ChatColor.GRAY + "Let´s the player interact / manipulate teams\n" +
                ChatColor.GOLD + "[+] " + "/bingoboard | " + ChatColor.GRAY + "Shows the current board\n" +
                ChatColor.GOLD + "[+] " + "/bingoboard random | " + ChatColor.GRAY + "Fills the board with random items\n" +
                ChatColor.GOLD + "[+] " + "/bingoboard add ITEM1 ITEM2 ... ITEM9 | " + ChatColor.GRAY + "Fills the board with the 9 specified items, the names have to be in this style: BLUE_DYE\n" +
                ChatColor.GOLD + "[+] " + "/bingo | " + ChatColor.GRAY + "Checks if the player / team has collected all the items & ends the game\n" +
                ChatColor.GOLD + "[+] " + "/top | " + ChatColor.GRAY + "Teleports the player to the surface / overworld if in the nether\n" +
                ChatColor.BLUE + "---------------");

        return false;
    }
}
