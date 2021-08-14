package q2474727683.badgecollect.Configuration;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import q2474727683.badgecollect.BadgeCollect;

import java.util.Set;

/**
 * 初始化配置文件
 *
 * @author: Dragon
 * @data: 2021/8/2 - 17:39
 */
public class Config {
    /**
     * 玩家右击勋章后的信息
     */
    public static String message;
    /**
     * 获取GUI界面的序号
     */
    public static Set<String> serialNumber;

    public static void loadConfig() {
        FileConfiguration config = BadgeCollect.INSTANCE.getConfig();
        message = ChatColor.translateAlternateColorCodes('&', config.getString("message"));
        serialNumber = config.getConfigurationSection("reward").getKeys(false);
    }

    public static void reloadConfig() {
        BadgeCollect.INSTANCE.reloadConfig();
        loadConfig();
    }
}
