package q2474727683.badgecollect.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**
 * @author: Dragon
 * @data: 2021/8/2 - 21:18
 */
public class InventoryClickListener implements Listener {
    @EventHandler
    public void onInventory(InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();
        final Inventory inventory = event.getInventory();

        if (inventory.getTitle().contains(player.getName() + " 的勋章仓库") ||
                inventory.getTitle().contains("勋章收集奖励")) {
            event.setCancelled(true);
        }

    }
}
