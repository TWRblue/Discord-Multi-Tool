package q2474727683.badgecollect;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import q2474727683.badgecollect.Configuration.Config;

import java.util.List;
import java.util.Set;

/**
 * 勋章的GUI界面
 *
 * @author: Dragon
 * @data: 2021/8/2 - 17:44
 */
public class BadgeGui {
    public static Inventory getPlayerBadge(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 54,
                ChatColor.BLUE + player.getName() + " 的勋章仓库");
        PlayerDataManager playerData = new PlayerDataManager(player.getUniqueId().toString());
        List<String> materialNameList = playerData.getBadgeMaterialNameList();
        for (String materialName : materialNameList) {
            Material material = Material.getMaterial(materialName);
            ItemStack itemStack = new ItemStack(material);
            inventory.addItem(itemStack);
        }

        return inventory;
    }

    public static Inventory getRewardInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 54,
                ChatColor.BLUE + "勋章收集奖励");
        Set<String> serialNumber = Config.serialNumber;
        for (String serial : serialNumber) {
            int number = BadgeCollect.INSTANCE.getConfig().getInt("reward." + serial + ".number");
            List<String> lore = BadgeCollect.INSTANCE.getConfig().getStringList("reward." + serial + ".lore");
            for (int i = 0; i < lore.size(); i++) {
                lore.set(i, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
                if (lore.get(i).contains("%number%")) {
                    lore.set(i, lore.get(i).replace("%number%", String.valueOf(number)));
                }
            }
            int num = Integer.parseInt(serial);
            String materialName = BadgeCollect.INSTANCE.getConfig().getString("reward." + serial + ".material");
            Material material = Material.getMaterial(materialName);
            ItemStack itemStack = new ItemStack(material);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);

            inventory.setItem(num, itemStack);
        }
        return inventory;
    }
}
