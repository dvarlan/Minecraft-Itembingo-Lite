package bingotests.bingotests.commands;

import bingotests.bingotests.BingoTests;
import bingotests.bingotests.team.Team;
import bingotests.bingotests.team.TeamHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TeamCommand implements CommandExecutor{

    TeamHandler handler = BingoTests.getMain().getHandler();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // Check if a player used the command
        if(!(sender instanceof Player)) {
            Bukkit.getLogger().info("Only players can use this command!");
            return false;
        }

        // Convert sender to player
        Player player = (Player) sender;

        if(args.length == 0) {
            sendUsage(player);
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "create":
                if (args.length != 2 || args[1].isEmpty()) {
                    player.sendMessage(ChatColor.BLUE + "[Team] "
                            + ChatColor.GRAY + "Team name can´t be empty!");
                    return false;
                }
                handler.addTeam(new Team(args[1]));
                player.sendMessage(ChatColor.BLUE + "[Team] "
                        + ChatColor.GRAY + "The team " + args[1] + " was created successfully!");
                return false;
            case "delete":
                if (args.length != 2 || args[1].isEmpty()) {
                    player.sendMessage(ChatColor.BLUE + "[Team] "
                            + ChatColor.GRAY + "Team name can´t be empty!");
                    return false;
                }

                for (int i = 0; i < handler.getTeamlist().size(); ++i) {
                    if (handler.getTeamlist().get(i).getTeamname().equals(args[1])) {
                        handler.removeTeam(handler.getTeamlist().get(i));
                        player.sendMessage(ChatColor.BLUE + "[Team] "
                                + ChatColor.GRAY + "The team: " + args[1] + " was removed successfully!");
                        return false;
                    }
                }
                player.sendMessage(ChatColor.BLUE + "[Team] "
                        + ChatColor.GRAY + "No team with the name " + args[1] + " was found!");
                return false;
            case "list":
                player.sendMessage(ChatColor.GOLD + "Teams: ");
                for(int i = 0; i < handler.getTeamlist().size(); i++) {
                    player.sendMessage(ChatColor.AQUA + "[+] " + handler.getTeamlist().get(i).getTeamname());
                    for (int j = 0; j < handler.getTeamlist().get(i).getMembers().size(); j++) {
                        player.sendMessage(ChatColor.GRAY + "    [-] " + handler.getTeamlist().get(i).getMembers().get(j).getName());
                    }
                }
                return false;
            case "join":
                if (args.length != 2 || args[1].isEmpty()) {
                    player.sendMessage(ChatColor.BLUE + "[Team] "
                            + ChatColor.GRAY + "Team name can´t be empty!");
                    return false;
                }

                for (int i = 0; i < handler.getTeamlist().size(); ++i) {
                    if (handler.getTeamlist().get(i).getTeamname().equals(args[1])) {
                        handler.getTeamlist().get(i).addMember(player);
                        player.sendMessage(ChatColor.BLUE + "[Team] "
                                + ChatColor.GRAY + "You joined team: " + args[1] + "!");
                        return false;
                    }
                }
                player.sendMessage(ChatColor.BLUE + "[Team] "
                        + ChatColor.GRAY + "No team with the name " + args[1] + " was found!");
                return false;
            case "leave":
                if (args.length != 2 || args[1].isEmpty()) {
                    player.sendMessage(ChatColor.BLUE + "[Team] "
                            + ChatColor.GRAY + "Team name can´t be empty!");
                    return false;
                }

                for (int i = 0; i < handler.getTeamlist().size(); ++i) {
                    if (handler.getTeamlist().get(i).getTeamname().equals(args[1])) {
                        handler.getTeamlist().get(i).removeMember(player);
                        player.sendMessage(ChatColor.BLUE + "[Team] "
                                + ChatColor.GRAY + "You left team: " + args[1] + "!");
                        return false;
                    }
                }
                player.sendMessage(ChatColor.BLUE + "[Team] "
                        + ChatColor.GRAY + "No team with the name " + args[1] + " was found!");
                return false;
            default:
                sendUsage(player);
        }
        return false;
    }
    private void sendUsage(Player p) {
        p.sendMessage(ChatColor.BLUE + "[Team] "
                + ChatColor.GRAY + "Usage: "
                + ChatColor.BLUE
                + "/team create <name>, /team delete <name>, /team join <name>, /team leave <name>, /team list");
    }
}
