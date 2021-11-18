package fr.rhodless.hcfcore.commands.faction;

import fr.rhodless.hcfcore.CC;
import fr.rhodless.hcfcore.commands.CommandFaction;
import fr.rhodless.hcfcore.faction.Faction;
import fr.rhodless.hcfcore.file.LangValues;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class FactionCreate {

    public FactionCreate(String[] args, Player player) {

        if(args.length != 2) {
            CommandFaction.sendHelpMessage();
            return;
        }

        if(Faction.getFaction(player) != null) {
            player.sendMessage(CC.translate(LangValues.FACTION_ALREADYINFACTION.getMessage()));
            return;
        }

        if(Faction.getFaction(args[1]) != null) {
            player.sendMessage(CC.translate(LangValues.FACTION_ALREADYEXISTING.getMessage()));
            return;
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

        new Faction().createFaction(args[1], player.getUniqueId().toString());

        if(LangValues.FACTION_CREATE_BROADCAST.isValue()) {
            Bukkit.broadcastMessage(CC.translate(LangValues.FACTION_CREATE_MESSAGE.getMessage()
                    .replace("<faction>", args[1])
                    .replace("<player>", player.getName())));
        } else {
            player.sendMessage(CC.translate(LangValues.FACTION_CREATE_MESSAGE.getMessage()
                    .replace("<faction>", args[1])
                    .replace("<player>", player.getName())));
        }

    }

}
