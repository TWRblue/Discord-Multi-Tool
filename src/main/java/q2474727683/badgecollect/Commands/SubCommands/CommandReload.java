package q2474727683.badgecollect.Commands.SubCommands;

import org.bukkit.command.CommandSender;
import q2474727683.badgecollect.Commands.BaseCommand;
import q2474727683.badgecollect.Configuration.Config;

/**
 * 重载指令
 *
 * @author: Dragon
 */
public class CommandReload implements BaseCommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        Config.reloadConfig();
        sender.sendMessage("重载完毕");
    }
}
