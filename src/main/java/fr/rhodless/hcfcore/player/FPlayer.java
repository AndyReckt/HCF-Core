package fr.rhodless.hcfcore.player;

import fr.rhodless.hcfcore.faction.Faction;
import fr.rhodless.hcfcore.file.FileManagement;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FPlayer {

    String uuid;
    FileConfiguration config;

    public FPlayer(String uuid) {
        config = FileManagement.CONFIG.getConfig();
        this.uuid = uuid;
    }

    public static List<String> allPlayers() {
        return FileManagement.CONFIG.getConfig().getStringList("datas.list");
    }

    public static String getByName(String playerName) {
        String string3 = null;
        List<String> allPlayers = FileManagement.CONFIG.getConfig().getStringList("datas.list");
        for(String string : allPlayers) {
            if(playerName.equals(FileManagement.CONFIG.getConfig().getString("data." + string + ".username"))) {
                string3 = string;
            }
        }
        return string3;
    }

    public int getKills() {
        return config.getInt("data." + uuid + ".kills");
    }

    public String getUuid() {
        return uuid;
    }

    public boolean isOnline() {
        boolean yes = false;

        for(Player pls : Bukkit.getOnlinePlayers()) {
            UUID id = pls.getUniqueId();
            if(id.toString().equals(uuid)) {
                yes = true;
            }
        }
        return yes;
    }

    public String getUsername() {
        return config.getString("data." + uuid + ".username");
    }

    public boolean getDeathBanned() {
        return config.getBoolean("data." + uuid + ".death-banned");
    }

    public int getDeaths() {
        return config.getInt("data." + uuid + ".deaths");
    }

    public int getMoney() {
        return config.getInt("data." + uuid + ".money");
    }

    public List<String> getInvites() {
        List<String> strings = new ArrayList<>();
        for(String string : Faction.getFactions()) {
            Faction faction = new Faction(Faction.getFaction(string));
            if(faction.getInvited().contains(uuid)) {
                strings.add(string);
            }
        }
        return strings;
    }
}
