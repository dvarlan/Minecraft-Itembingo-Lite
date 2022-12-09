package bingotests.bingotests.commands;

import bingotests.bingotests.BingoTests;
import bingotests.bingotests.team.Team;
import bingotests.bingotests.team.TeamHandler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class BingoCommand implements CommandExecutor {

    TeamHandler handler = BingoTests.getMain().getHandler();
    ArrayList<ItemStack> teaminv = new ArrayList<>();
    ArrayList<ItemStack> bingoboard = BingoTests.getMain().getBingo();
    int score = 0;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // Checks if the user (or the team) has all the Bingoboard items
        if(!(sender instanceof Player)) {
            Bukkit.getLogger().info("Only players can use this command!");
            return false;
        }

        Player player = (Player) sender;

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

            // Method for checking items & ignoring the item count

            for (ItemStack itemStack : bingoboard) {
                for (ItemStack stack : teaminv) {
                    if (itemStack.isSimilar(stack)) {
                        score++;
                        break;
                    }
                }
            }
            if(score != 9) {
                player.sendMessage(ChatColor.BLUE + "[Bingo] "
                        + ChatColor.GRAY + "Your team did NOT obtain every item!");
                teaminv.clear();
                score = 0;
                return false;
            }

            player.sendMessage(ChatColor.BLUE + "[Bingo] "
                    + ChatColor.GRAY + "Your team obtained every item!");
            teaminv.clear();
            score = 0;
            bingoboard.clear();
            showWinner(player);
            return false;
        }

        for (ItemStack itemStack : bingoboard) {
            for (ItemStack stack : player.getInventory().getContents()) {
                if (itemStack.isSimilar(stack)) {
                    score++;
                    break;
                }
            }
        }

        if(score != 9) {
            player.sendMessage(ChatColor.BLUE + "[Bingo] "
                    + ChatColor.GRAY + "You did NOT obtain every item!");
            score = 0;
            return false;
        }

        player.sendMessage(ChatColor.BLUE + "[Bingo] "
                + ChatColor.GRAY + "You obtained every item!");
        showWinner(player);
        score = 0;
        bingoboard.clear();
        return false;
    }

    public void showWinner(@NotNull Player winner) {

        String winnerteam = winner.getName();

        if(!(handler.getTeamlist().isEmpty())) {
            for (Team t : handler.getTeamlist()) {
                for(Player p : t.getMembers()) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 99999, 1));
                }
                if (t.getMembers().contains(winner)) {
                    winnerteam = t.getTeamname();
                }
            }
        }

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.teleport(winner.getLocation());
            p.showTitle(Title.title(Component.text(ChatColor.GOLD + "TEAM "+ winnerteam + " WON!"), Component.text("GG!")));
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        }

        winner.getWorld().spawnEntity(winner.getLocation().add(1,1,1), EntityType.FIREWORK);
        winner.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 500, 1));
    }
}
