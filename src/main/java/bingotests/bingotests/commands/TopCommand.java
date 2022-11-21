package bingotests.bingotests.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.potion.PotionEffectType.REGENERATION;

public class TopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // Check if a player used the command
        if(!(sender instanceof Player)) {
            Bukkit.getLogger().info("Only players can use this command!");
            return false;
        }

        // Convert sender to player
        Player player = (Player) sender;

        if(args.length != 0) {
            player.sendMessage("Usage: /top");
            return false;
        }

        teleportPlayer(player);
        return false;
    }

    private void teleportPlayer(Player player) {
        Location Current = player.getLocation();
        Location Top = player.getLocation().toHighestLocation();
        // To stop the glitch where the player would spawn in the block
        Top.setY(Top.getY() + 1);

        if(Current.getY() >= -60 && Current.getY() <= 60) {
            player.teleport(Top);
            player.sendMessage("You used the top command!");
            // To heal the player in case they somehow suffocate:
            // player.addPotionEffect(new PotionEffect(REGENERATION, 20, 5));
        } else {
            player.sendMessage("You cannot use this command right now, try changing your y-Position!");
        }
    }
}
