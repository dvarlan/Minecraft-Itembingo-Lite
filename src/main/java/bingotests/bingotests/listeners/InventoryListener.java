package bingotests.bingotests.listeners;

import bingotests.bingotests.BingoTests;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Objects;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) return;
        if (Objects.equals(event.getClickedInventory(), BingoTests.getMain().getInventory())) {
            // Player can´t take the items in the Bingoboard
            event.setCancelled(true);
        }
    }

}
