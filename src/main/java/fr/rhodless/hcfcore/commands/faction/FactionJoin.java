package fr.rhodless.hcfcore.commands.faction;

import fr.rhodless.hcfcore.CC;
import fr.rhodless.hcfcore.faction.Faction;
import fr.rhodless.hcfcore.file.LangValues;
import fr.rhodless.hcfcore.player.FPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class FactionJoin {
    public FactionJoin(String[] args, Player player) {

        if(Faction.getFaction(player) != null) {
            player.sendMessage(CC.translate(LangValues.FACTION_ALREADYINFACTION.getMessage()));
            return;
        }

        Player target = Bukkit.getPlayer(args[1]);
        if(target != null) {
            Faction faction = new Faction(Faction.getFaction(target));

            if(!faction.getInvited().contains(player.getUniqueId().toString())) {
                player.sendMessage(CC.translate(LangValues.FACTION_INVITE_NOTINVITED.getMessage()));
                return;
            }

            faction.setInvited(player.getUniqueId().toString(), false);
            faction.setMember(player.getUniqueId().toString(), true);
            for(String string : faction.getMembers()) {
                if(!new FPlayer(string).isOnline()) {
                    return;
                }
                Bukkit.getPlayer(UUID.fromString(string)).sendMessage(CC.translate(LangValues.FACTION_INVITE_JOIN.getMessage()
                        .replace("<player>", player.getName())));
            }
            player.sendMessage(CC.translate(LangValues.FACTION_INVITE_JOIN.getMessage()
                    .replace("<player>", player.getName())));
            return;
        }

        Faction faction = new Faction(Faction.getFaction(args[1]));
        if(Faction.getFaction(args[1]) == null) {
            player.sendMessage(CC.translate(LangValues.FACTION_INVITE_NOTINVITED.getMessage()));
            return;
        }

        faction.setInvited(player.getUniqueId().toString(), false);
        faction.setMember(player.getUniqueId().toString(), true);
        for(String string : faction.getMembers()) {
            if(!new FPlayer(string).isOnline()) {
                return;
            }
            Bukkit.getPlayer(UUID.fromString(string)).sendMessage(CC.translate(LangValues.FACTION_INVITE_JOIN.getMessage()
                    .replace("<player>", player.getCustomName())));
        }
        player.sendMessage(CC.translate(LangValues.FACTION_INVITE_JOIN.getMessage()
                .replace("<player>", player.getName())));

    }
}
