package fr.rhodless.hcfcore.commands.faction;

import fr.rhodless.hcfcore.CC;
import fr.rhodless.hcfcore.Main;
import fr.rhodless.hcfcore.commands.CommandFaction;
import fr.rhodless.hcfcore.faction.Faction;
import fr.rhodless.hcfcore.file.LangValues;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class FactionRename {
    public FactionRename(String[] args, Player player) {

        if(args.length != 2) {
            CommandFaction.sendHelpMessage();
        }

        if(!args[1].matches("[a-zA-Z0-9]+")) {
            player.sendMessage(CC.translate(LangValues.ALPHANUMERIC.getMessage()));
            return;
        }

        if(args[1].length() < 3) {
            player.sendMessage(CC.translate(LangValues.ATLEASTTHREECARACTERS.getMessage()));
            return;
        }

        if(args[1].length() > 16) {
            player.sendMessage(CC.translate(LangValues.ATMOSTSIXTEENCARACTERS.getMessage()));
            return;
        }

        if(Faction.getFaction(player) != null) {
            player.sendMessage(CC.translate(LangValues.FACTION_NOTINFACTION.getMessage()));
            return;
        }

        if(Faction.getFaction(args[1]) != null) {
            player.sendMessage(CC.translate(LangValues.FACTION_ALREADYEXISTING.getMessage()));
            return;
        }

        Faction faction = new Faction(Faction.getFaction(player));

        if(!faction.getLeader().equals(player.getUniqueId().toString())) {
            player.sendMessage(CC.translate(LangValues.FACTION_NOTLEADER.getMessage()));
            return;
        }

        faction.delete();
        List<String> members = faction.getMembers();
        double dtr = faction.getDTR();
        double maxdtr = faction.getMaxDTR();
        int points = faction.getPoints();
        int kills = faction.getKills();
        Location loc = faction.getHome();
        int balance = faction.getBalance();
        List<String> invited = faction.getInvited();
        List<String> coLeaders = faction.getCoLeaders();
        List<String> captains = faction.getCaptains();

        Faction theFaction = new Faction().createFaction(args[1], player.getUniqueId().toString());
        theFaction.setMembers(members);
        theFaction.setDTR(dtr);
        theFaction.setMaxDTR(maxdtr);
        theFaction.setPoints(points);
        theFaction.setBalance(balance);
        theFaction.setKills(kills);
        theFaction.setHome(loc);
        theFaction.setInvites(invited);
        theFaction.setCoLeaders(coLeaders);
        theFaction.setCaptains(captains);
        for(String string : faction.getMembers()) {
            Bukkit.getPlayer(UUID.fromString(string)).sendMessage(CC.translate(LangValues.FACTION_RENAMESUCCESS.getMessage()
                    .replace("<name>", args[1])));
        }

    }
}
