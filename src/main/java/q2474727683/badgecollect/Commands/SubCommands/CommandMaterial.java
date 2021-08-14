package q2474727683.badgecollect.Commands.SubCommands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import q2474727683.badgecollect.Commands.BaseCommand;

/**
 * 获取手上的材质名
 *
 * @author: Dragon
 * @data: 2021/8/2 - 17:33
 */
public class CommandMaterial implements BaseCommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player) ||
                !sender.isOp()) {
            return;
        }
        final Player player = (Player) sender;
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        if (itemInMainHand.getType().equals(Material.AIR)) {
            return;
        }

        String materialName = itemInMainHand.getType().name();
        player.sendMessage(ChatColor.BLUE + "手上物品材质名称：" + materialName);
    }
}
