package fr.rhodless.hcfcore.commands.faction;

import fr.rhodless.hcfcore.CC;
import fr.rhodless.hcfcore.commands.CommandFaction;
import fr.rhodless.hcfcore.faction.Faction;
import fr.rhodless.hcfcore.file.LangValues;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class FactionDisband {
    public FactionDisband(String[] args, Player player) {
        if(args.length != 1) {
            CommandFaction.sendHelpMessage();
            return;
        }

        if(Faction.getFaction(player) == null) {
            player.sendMessage(CC.translate(LangValues.FACTION_NOTEXISTING.getMessage()));
            return;
        }

        Faction faction = new Faction(Faction.getFaction(player));

        if(!faction.getLeader().equals(player.getUniqueId().toString())) {
            player.sendMessage(CC.translate(LangValues.FACTION_NOTLEADER.getMessage()));
            return;
        }

        if(LangValues.FACTION_DISBAND_BROADCAST.isValue()) {
            Bukkit.broadcastMessage(CC.translate(LangValues.FACTION_DISBAND_MESSAGE.getMessage()
                    .replace("<faction>", faction.getName())
                    .replace("<player>", player.getName())));
        } else {
            player.sendMessage(CC.translate(LangValues.FACTION_DISBAND_MESSAGE.getMessage()
                    .replace("<faction>", faction.getName())
                    .replace("<player>", player.getName())));
        }

        faction.delete();


    }
}
