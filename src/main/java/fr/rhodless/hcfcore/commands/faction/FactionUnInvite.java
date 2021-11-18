package fr.rhodless.hcfcore.commands.faction;

import fr.rhodless.hcfcore.CC;
import fr.rhodless.hcfcore.Main;
import fr.rhodless.hcfcore.commands.CommandFaction;
import fr.rhodless.hcfcore.faction.Faction;
import fr.rhodless.hcfcore.file.LangValues;
import fr.rhodless.hcfcore.player.FPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class FactionUnInvite {
    public FactionUnInvite(String[] args, Player player) {

        if(Faction.getFaction(player) == null) {
            player.sendMessage(CC.translate(LangValues.FACTION_NOTINFACTION.getMessage()));
            return;
        }

        FPlayer target = new FPlayer(FPlayer.getByName(args[1]));
        if(FPlayer.getByName(args[1]) == null) {
            CommandFaction.sendHelpMessage();
            return;
        }

        Faction faction = new Faction(Faction.getFaction(player));

        if(!faction.getInvited().contains(FPlayer.getByName(args[1]))) {
            player.sendMessage(CC.translate(LangValues.FACTION_UNINVITE_NOTINVITED.getMessage()
                    .replace("<player>", args[1])));
            return;
        }

        for(String string : faction.getMembers()) {
            if(new FPlayer(string).isOnline()) {
                Bukkit.getPlayer(UUID.fromString(string)).sendMessage(CC.translate(LangValues.FACTION_UNINVITE_MESSAGE.getMessage()
                        .replace("<player>", args[1])));
            }
        }
        faction.setInvited(target.getUuid(), false);


    }
}
