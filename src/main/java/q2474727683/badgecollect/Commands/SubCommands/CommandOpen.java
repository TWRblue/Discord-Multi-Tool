package q2474727683.badgecollect.Commands.SubCommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import q2474727683.badgecollect.BadgeGui;
import q2474727683.badgecollect.Commands.BaseCommand;

/**
 * 打开已存储的勋章列表
 *
 * @author: Dragon
 * @data: 2021/8/2 - 17:20
 */
public class CommandOpen implements BaseCommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            return;
        }
        final Player player = (Player) sender;
        player.openInventory(BadgeGui.getPlayerBadge(player));
    }
}
