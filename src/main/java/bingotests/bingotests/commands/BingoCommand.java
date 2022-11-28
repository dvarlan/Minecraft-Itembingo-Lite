package bingotests.bingotests.commands;

import bingotests.bingotests.BingoTests;
import bingotests.bingotests.team.Team;
import bingotests.bingotests.team.TeamHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class BingoCommand implements CommandExecutor {

    TeamHandler handler = BingoTests.getMain().getHandler();
    ArrayList<ItemStack> teaminv = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // Checks if the user (or maybe the team) has all the Bingoboard items
        if(!(sender instanceof Player)) {
            Bukkit.getLogger().info("Only players can use this command!");
            return false;
        }

        Player player = (Player) sender;

        // TODO: This feature still requires testing!
        // Creates a teaminv out of all the team members inventories & checks if all items were obtained
        if(!(handler.getTeamlist().isEmpty())) {
            for(Team t : handler.getTeamlist()) {
                if (t.getMembers().contains(player)) {
                    for (Player member : t.getMembers()) {
                        for(int i = 0; i <= member.getInventory().getSize(); ++i) {
                            teaminv.add(member.getInventory().getItem(i));
                        }
                   }
                }
            }
            for(ItemStack i : BingoTests.getMain().getBingo()) {
                if(!(teaminv.contains(i))) {
                    player.sendMessage("Your team did not obtain every item!");
                    teaminv.clear();
                    return false;
                }
            }
            player.sendMessage("Your team obtained every item!");
            return false;
        }


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
