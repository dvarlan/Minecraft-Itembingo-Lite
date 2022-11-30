package bingotests.bingotests.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.BLUE + "[Bingo] " +
                ChatColor.GRAY + "Welcome to the itembingo " + ChatColor.GOLD + player.getName() + ChatColor.GRAY + "!\n" +
                ChatColor.GRAY + "Use " + ChatColor.GOLD + "/bingohelp" + ChatColor.GRAY + " for help!");
    }
}
