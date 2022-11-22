package bingotests.bingotests.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


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
        Location Top = player.getLocation().toHighestLocation();

        // Check if the player is currently in the nether -> teleports them to the overworld
        if(player.getWorld().getName().contains("nether")) {
            Top = calculateOverworldCoordinates(player);
        } else {
            // To stop the glitch where the player would spawn in the block
            Top.setY(Top.getY() + 1);
        }
        player.teleport(Top);
        player.sendMessage("You used the top command!");

    }

    private Location calculateOverworldCoordinates(Player player) {
        World world = Bukkit.getWorld("world");
        Location l = new Location(world, player.getLocation().getX() * 8, 0, player.getLocation().getZ() * 8);
        l.setY(world.getHighestBlockAt(l).getY() + 1);
        return l;
    }
}
