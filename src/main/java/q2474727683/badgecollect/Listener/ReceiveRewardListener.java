package q2474727683.badgecollect.Listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import q2474727683.badgecollect.BadgeCollect;
import q2474727683.badgecollect.Configuration.Config;
import q2474727683.badgecollect.PlayerDataManager;

import java.util.List;

/**
 * 领奖监听
 *
 * @author: Dragon
 * @data: 2021/8/2 - 23:16
 */
public class ReceiveRewardListener implements Listener {
    @EventHandler
    public void onReceiveReward(InventoryClickEvent event) {
        final Inventory inventory = event.getInventory();
        String rawSlot = String.valueOf(event.getRawSlot());
        if (inventory.getTitle().contains("勋章收集奖励") &&
                Config.serialNumber.contains(rawSlot)) {
            final Player player = (Player) event.getWhoClicked();
            PlayerDataManager playerDataManager = new PlayerDataManager(player.getUniqueId().toString());
            if (!checkBadgeNumber(playerDataManager, rawSlot)) {
                player.sendMessage("§4抱歉，您没有这么多勋章");
                return;
            }
            List<String> alreadyReceiveList = playerDataManager.getAlreadyReceiveList();
            if (alreadyReceiveList.contains(rawSlot)) {
                player.sendMessage("§6该奖励已被领取");
                return;
            }
            List<String> commands = BadgeCollect.INSTANCE.getConfig().getStringList("reward." + rawSlot + ".commands");
            for (int i = 0; i < commands.size(); i++) {
                commands.set(i, commands.get(i).replace("%player%", player.getName()));
                Bukkit.dispatchCommand(BadgeCollect.INSTANCE.getServer().getConsoleSender(), commands.get(i));
            }
            alreadyReceiveList.add(rawSlot);
            playerDataManager.setAlreadyReceive(alreadyReceiveList);
            player.sendMessage("领取成功");
        }
    }

    /**
     * 检查所拥有的勋章数量是否达到条件
     * @param playerDataManager 玩家数据管理
     * @param rawSlot 点击的槽位
     * @return boolean
     */
    private boolean checkBadgeNumber(PlayerDataManager playerDataManager, String rawSlot) {
        int number = BadgeCollect.INSTANCE.getConfig().getInt("reward." + rawSlot + ".number");
        return playerDataManager.getBadgeNumber() >= number;
    }
}
