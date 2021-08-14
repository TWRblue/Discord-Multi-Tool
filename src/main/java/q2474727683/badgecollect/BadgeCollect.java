package q2474727683.badgecollect;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import q2474727683.badgecollect.Commands.CommandHandler;
import q2474727683.badgecollect.Configuration.Config;
import q2474727683.badgecollect.Listener.BadgeRightClickListener;
import q2474727683.badgecollect.Listener.InventoryClickListener;
import q2474727683.badgecollect.Listener.ReceiveRewardListener;
import q2474727683.badgecollect.PlaceHolderApi.Hook;

import java.io.File;

public final class BadgeCollect extends JavaPlugin {
    public static BadgeCollect INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        saveDefaultConfig();
        Config.loadConfig();
        Bukkit.getPluginCommand("BadgeCollect").setExecutor(CommandHandler.getInstance());
        Bukkit.getPluginManager().registerEvents(new BadgeRightClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new ReceiveRewardListener(), this);
        if (new File(getDataFolder(), "\\PlayerData").mkdirs()) {
            getLogger().info("创建玩家数据目录成功！");
        } else {
            getLogger().info("已创建玩家数据目录！");
        }

        new Hook();
    }
}
