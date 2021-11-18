package fr.rhodless.hcfcore.commands.faction;

import fr.rhodless.hcfcore.CC;
import fr.rhodless.hcfcore.commands.CommandFaction;
import fr.rhodless.hcfcore.faction.Faction;
import fr.rhodless.hcfcore.file.LangValues;
import org.bukkit.entity.Player;

public class FactionShow {

    public FactionShow(String[] args, Player player) {
        if(args.length == 1) {
            if(Faction.getFaction(player) == null) {
                player.sendMessage(CC.translate(LangValues.FACTION_NOTINFACTION.getMessage()));
                return;
            }

            Faction faction = new Faction(Faction.getFaction(player));

            for(String string : CommandFaction.getFactionInfo(faction)) {
                player.sendMessage(CC.translate(string));
            }

        } else if(args.length == 2) {
            if(Faction.getFaction(args[1]) == null) {
                player.sendMessage(CC.translate(LangValues.FACTION_NOTEXISTING.getMessage()));
                return;
            }

            Faction faction = new Faction(args[1]);

            for(String string : CommandFaction.getFactionInfo(faction)) {
                player.sendMessage(CC.translate(string));
            }

        }
    }
}
