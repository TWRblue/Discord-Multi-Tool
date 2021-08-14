package q2474727683.badgecollect;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 玩家数据管理
 *
 * @author: Dragon
 * @data: 2021/8/2 - 16:21
 */
public class PlayerDataManager {
    private File file;
    private final YamlConfiguration playerData = new YamlConfiguration();

    /**
     * @param playerUUID 玩家的UUID.toString()
     */
    public PlayerDataManager(String playerUUID) {
        initPlayerData(playerUUID);
    }

    /**
     * 获取玩家所拥有的勋章材质
     * @return materialName
     */
    public List<String> getBadgeMaterialNameList() {
        List<String> materialNameList = playerData.getStringList("badgeMaterialNameList");
        return materialNameList != null ?
                materialNameList : new ArrayList<>();
    }

    /**
     * 设置目前拥有勋章
     * @param badgeList 勋章Material列表
     */
    public void setBadgeList(List<String> badgeList) {
        playerData.set("badgeMaterialNameList", badgeList);
        try {
            playerData.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取勋章数量
     * @return number
     */
    public int getBadgeNumber() {
        int badgeNumber = getBadgeMaterialNameList().size();
        return badgeNumber;
    }

    /**
     * 获取已经领取的序号
     * @return list
     */
    public List<String> getAlreadyReceiveList() {
        return playerData.getStringList("alreadyReceiveList");
    }

    /**
     * 设置已经领取的序号
     * @param alreadyReceiveList list
     */
    public void setAlreadyReceive(List<String> alreadyReceiveList) {
        playerData.set("alreadyReceiveList", alreadyReceiveList);
        try {
            playerData.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化玩家数据
     * @param playerUUID 玩家的UUID.toString()
     */
    private void initPlayerData(String playerUUID) {
        file = new File(BadgeCollect.INSTANCE.getDataFolder() +
                "\\PlayerData\\" + playerUUID + ".yml");
        try {
            file.createNewFile();
            playerData.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
