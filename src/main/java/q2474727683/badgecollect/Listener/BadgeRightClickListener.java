package q2474727683.badgecollect.Listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import q2474727683.badgecollect.Configuration.Config;
import q2474727683.badgecollect.PlayerDataManager;

import java.util.List;

/**
 * 监听勋章右键
 *
 * @author: Dragon
 * @data: 2021/8/2 - 17:09
 */
public class BadgeRightClickListener implements Listener {
    @EventHandler
    public void onBadgeRightClick(PlayerInteractEvent event) {
        final Action action = event.getAction();
        final Player player = event.getPlayer();
        final ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        /*
        不是右击
        不是勋章
        是勋章箱时，返回。
         */
        if (!action.equals(Action.RIGHT_CLICK_AIR) &&
                !action.equals(Action.RIGHT_CLICK_BLOCK) ||
                itemInMainHand.getType() == Material.AIR ||
                itemInMainHand.toString().contains("BADGECASE") ||
                !itemInMainHand.toString().contains("BADGE")) {
            return;
        }

        String name = itemInMainHand.getType().name();
        PlayerDataManager playerDataManager = new PlayerDataManager(player.getUniqueId().toString());
        if (!checkBadge(playerDataManager, name)) {
            List<String> badgeMaterialNameList = playerDataManager.getBadgeMaterialNameList();
            badgeMaterialNameList.add(name);
            playerDataManager.setBadgeList(badgeMaterialNameList);

            //手上勋章 - 1
            int remain = itemInMainHand.getAmount() - 1;
            itemInMainHand.setAmount(remain);
            player.sendMessage(Config.message);
        } else {
            player.sendMessage("勋章已被收集，请勿重复收集");
        }
    }

    private boolean checkBadge(PlayerDataManager playerDataManager, String name) {
        List<String> badgeMaterialNameList = playerDataManager.getBadgeMaterialNameList();
        return badgeMaterialNameList.contains(name);
    }
}
