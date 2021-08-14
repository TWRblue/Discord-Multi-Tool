package q2474727683.badgecollect.PlaceHolderApi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import q2474727683.badgecollect.BadgeCollect;
import q2474727683.badgecollect.PlayerDataManager;

/**
 * @author: Dragon
 * @data: 2021/8/2 - 18:31
 */
public class Hook extends PlaceholderExpansion {
    public Hook() {
        register();
    }

    @Override
    public String getIdentifier() {
        return "Badge";
    }

    @Override
    public String getAuthor() {
        return "Dragon";
    }

    @Override
    public String getVersion() {
        return BadgeCollect.INSTANCE.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (identifier.equalsIgnoreCase("number")) {
            String uuid = player.getUniqueId().toString();
            return String.valueOf(new PlayerDataManager(uuid).getBadgeNumber());
        }
        return "";
    }
}
