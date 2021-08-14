package q2474727683.badgecollect.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import q2474727683.badgecollect.Commands.SubCommands.CommandMaterial;
import q2474727683.badgecollect.Commands.SubCommands.CommandOpen;
import q2474727683.badgecollect.Commands.SubCommands.CommandReload;
import q2474727683.badgecollect.Commands.SubCommands.CommandReward;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 指令管理
 *
 * @author: Dragon
 */
public final class CommandHandler implements CommandExecutor, TabCompleter {
    private static final HashMap<String, BaseCommand> commands = new HashMap<>();
    private static final CommandExecutor INSTANCE = new CommandHandler();

    public CommandHandler() {
        commands.put("reload", new CommandReload());
        commands.put("open", new CommandOpen());
        commands.put("material", new CommandMaterial());
        commands.put("reward", new CommandReward());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length < 1) {
            sender.sendMessage("§6/xzz reward - 打开领取奖励界面" +
                    "\n/xzz reload - 重载插件" +
                    "\n/xzz open - 打开自己的勋章仓库" +
                    "\n/xzz material - 服主专用，查询手上物品的material值，方便填材质");
        } else if (commands.containsKey(args[0])) {
            commands.get(args[0]).execute(sender, args);
        } else {
            sender.sendMessage("§4未知命令");
        }
        return true;
    }

    public static CommandExecutor getInstance() {
        return INSTANCE;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!(sender instanceof Player)) {
            return null;
        }
        if (args.length > 2) {
            return null;
        }

        if (args.length <= 1) {
            return new ArrayList<>(commands.keySet());
        }
        return null;
    }
}
