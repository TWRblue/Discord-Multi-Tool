package q2474727683.badgecollect.Commands;

import org.bukkit.command.CommandSender;

/**
 * 抽象类，所有子命令应使用此接口
 *
 * @author: Dragon
 */
public interface BaseCommand {
    /**
     * 抽象方法
     * 所有子命令 args >= 1
     */
    void execute(CommandSender sender, String[] args);
}
