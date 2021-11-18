package fr.rhodless.hcfcore.player;

import java.util.*;

public class PlayerData
{
    private static Map<UUID, PlayerData> playerDataMap;
    private final UUID uuid;
    private long lastLaunch;
    
    public PlayerData(final UUID uuid) {
        this.uuid = uuid;
        PlayerData.playerDataMap.put(uuid, this);
    }
    
    public static void removePlayerData(final UUID uuid) {
        PlayerData.playerDataMap.remove(uuid);
    }
    
    public static PlayerData getByUUID(final UUID uuid) {
        return PlayerData.playerDataMap.get(uuid);
    }
    
    public long getLastLaunch() {
        return this.lastLaunch;
    }
    
    public void setLastLaunch(final long lastLaunch) {
        this.lastLaunch = lastLaunch;
    }
    
    static {
        PlayerData.playerDataMap = new HashMap<UUID, PlayerData>();
    }
}
