package fr.rhodless.hcfcore.faction;

import fr.rhodless.hcfcore.Main;
import fr.rhodless.hcfcore.file.FileManagement;
import fr.rhodless.hcfcore.register.Cuboid;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.*;

public class Faction {

    private String path = "";
    private final FileConfiguration config;

    public Faction(String factionName) {
        this.config = FileManagement.CONFIG.getConfig();
        this.path = factionName;
    }

    public Faction() {
        this.config = FileManagement.CONFIG.getConfig();
    }

    public static List<String> getFactions() {
        return FileManagement.CONFIG.getConfig().getStringList("faction.list");
    }

    public static String getFaction(Player player) {
        FileConfiguration config = FileManagement.CONFIG.getConfig();
        List<String> factionList = config.getStringList("faction.list");
        String theFaction = null;
        for(String faction : factionList) {
            List<String> members = config.getStringList("factions." + faction + ".members");
            if(members.contains(player.getUniqueId().toString())) {
                theFaction = faction;
            }
        }

        return theFaction;

    }

    public static String getFaction(String string) {
        FileConfiguration config = FileManagement.CONFIG.getConfig();
        List<String> factionList = config.getStringList("faction.list");
        String theFaction = null;
        for (String faction : factionList) {
            if (faction.equalsIgnoreCase(string)) {
                theFaction = string;
                break;
            }
        }

        return theFaction;
    }

    public static void save() {
        Main.getInstance().saveConfig();
    }

    public Faction createFaction(String name, String uniqueId) {

        /*
         * Adds a faction to the list
         */
        List<String> list = config.getStringList("faction.list");
        list.add(name);
        Main.getInstance().getConfig().set("faction.list", list);
        save();

        /*
         * Sets the faction stats
         */
        Faction faction = new Faction(name);
        faction.setDTR(1.5);
        faction.setPoints(0);
        faction.setKills(0);
        faction.setBalance(0);
        faction.setMaxDTR(1.5);
        faction.setHome(new Location(Bukkit.getWorld("world"), 0, 0, 0));
        faction.setMembers(new ArrayList<>(Collections.singletonList(uniqueId)));
        faction.setInvites(new ArrayList<>());
        faction.setLeader(uniqueId);
        faction.setCoLeaders(new ArrayList<>());
        faction.setCaptains(new ArrayList<>());
        faction.setClaim(null);
        return faction;
    }

    public void delete() {

        /*
         * Removes a faction to the list
         */
        List<String> list = config.getStringList("faction.list");
        list.remove(path);
        Main.getInstance().getConfig().set("faction.list", list);

        Main.getInstance().getConfig().set("factions." + path, null);
        save();
    }

    public String getName() {
        return path;
    }
    public int getOnline() {
        int i = 0;
        for(String string : getMembers()) {
            for(Player pls : Bukkit.getOnlinePlayers()) {
                if(pls.getUniqueId().equals(UUID.fromString(string))) {
                    i++;
                }
            }
        }
        return i;
    }

    public void setInvited(String uuid, boolean b) {
        if(b) {
            List<String> invitedPlayers = config.getStringList("factions." + path + ".invites");
            invitedPlayers.add(uuid);
            Main.getInstance().getConfig().set("factions." + path + ".invites", invitedPlayers);
        } else {
            List<String> invitedPlayers = config.getStringList("factions." + path + ".invites");
            invitedPlayers.remove(uuid);
            Main.getInstance().getConfig().set("factions." + path + ".invites", invitedPlayers);
        }
        save();
    }
    public void setInvites(List<String> invited) {
        Main.getInstance().getConfig().set("factions." + path + ".invited", invited);
        save();
    }
    public List<String> getInvited() {
        return config.getStringList("factions." + path + ".invites");
    }

    public void setMember(String uuid, boolean b) {
        if(b) {
            List<String> invitedPlayers = config.getStringList("factions." + path + ".members");
            invitedPlayers.add(uuid);
            Main.getInstance().getConfig().set("factions." + path + ".members", invitedPlayers);
        } else {
            List<String> invitedPlayers = config.getStringList("factions." + path + ".members");
            invitedPlayers.remove(uuid);
            Main.getInstance().getConfig().set("factions." + path + ".members", invitedPlayers);
        }
        save();
    }
    public List<String> getMembers() {
        return config.getStringList("factions." + path + ".members");
    }
    public void setMembers(List<String> members) {
        Main.getInstance().getConfig().set("factions." + path + ".members", members);
        save();
    }
    public List<String> getMembersWithoutRanked() {
        List<String> allStrings = new ArrayList<>();
        for(String string : getMembers()) {
            if(!getLeader().equals(string) && !getCaptains().contains(string) && !getCoLeaders().contains(string)) {
                allStrings.add(string);
            }
        }
        return allStrings;
    }
    public int getFactionSize() {
        return getMembers().size();
    }

    public List<String> getCoLeaders() {
        return config.getStringList("factions." + path + ".co-leaders");
    }
    public void setCoLeaders(List<String> members) {
        Main.getInstance().getConfig().set("factions." + path + ".co-leaders", members);
        save();
    }
    public void setCoLeader(String uuid, boolean b) {
        if(b) {
            List<String> invitedPlayers = config.getStringList("factions." + path + ".co-leaders");
            invitedPlayers.add(uuid);
            Main.getInstance().getConfig().set("factions." + path + ".co-leaders", invitedPlayers);
        } else {
            List<String> invitedPlayers = config.getStringList("factions." + path + ".co-leaders");
            invitedPlayers.remove(uuid);
            Main.getInstance().getConfig().set("factions." + path + ".co-leaders", invitedPlayers);
        }
        save();
    }

    public List<String> getCaptains() {
        return config.getStringList("factions." + path + ".captains");
    }
    public void setCaptains(List<String> members) {
        Main.getInstance().getConfig().set("factions." + path + ".captains", members);
        save();
    }
    public void setCaptain(String uuid, boolean b) {
        if(b) {
            List<String> invitedPlayers = config.getStringList("factions." + path + ".captains");
            invitedPlayers.add(uuid);
            Main.getInstance().getConfig().set("factions." + path + ".captains", invitedPlayers);
        } else {
            List<String> invitedPlayers = config.getStringList("factions." + path + ".captains");
            invitedPlayers.remove(uuid);
            Main.getInstance().getConfig().set("factions." + path + ".captains", invitedPlayers);
        }
        save();
    }

    public void setLeader(String uuid) {
        Main.getInstance().getConfig().set("factions." + path + ".leader", uuid);
        save();
    }
    public String getLeader() {
        return config.getString("factions." + path + ".leader");
    }

    public void setDTR(double dtr) {
        Main.getInstance().getConfig().set("factions." + path + ".dtr", dtr);
        save();
    }
    public double getDTR() {
        return config.getDouble("factions." + path + ".dtr");
    }
    public void setMaxDTR(double maxdtr) {
        Main.getInstance().getConfig().set("factions." + path + ".max-dtr", maxdtr);
        save();
    }
    public double getMaxDTR() {
        return config.getDouble("factions." + path + ".dtr");
    }

    public int getPoints() {
        return config.getInt("factions." + path + ".points");
    }
    public void setPoints(int points) {
        Main.getInstance().getConfig().set("factions." + path + ".points", points);
        save();
    }

    public void setBalance(int balance) {
        Main.getInstance().getConfig().set("factions." + path + ".balance", balance);
        save();
    }
    public int getBalance() {
        return config.getInt("factions." + path + ".balance");
    }

    public void setKills(int kills) {
        Main.getInstance().getConfig().set("factions." + path + ".kills", kills);
        save();
    }
    public int getKills() {
        return config.getInt("factions." + path + ".kills");
    }

    public void setHome(Location location) {
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        Main.getInstance().getConfig().set("factions." + path + ".home.x", x);
        Main.getInstance().getConfig().set("factions." + path + ".home.y", y);
        Main.getInstance().getConfig().set("factions." + path + ".home.z", z);
        save();
    }
    public Location getHome() {
        return new Location(Bukkit.getWorld("world"), config.getInt("factions." + path + ".home.x"), config.getInt("factions." + path + ".home.y"), config.getInt("factions." + path + ".home.z"));
    }

    public boolean isRaidable() {
        return getDTR() < 0;
    }
    public String getDTRStatus() {
        return "";
    }

    public Cuboid getClaim() {
        return (Cuboid) Main.getInstance().getConfig().get("factions." + path + ".claim");
    }

    public void setClaim(Cuboid cuboid) {
        Main.getInstance().getConfig().set("factions." + path + ".claim", cuboid);
    }

}
