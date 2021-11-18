package fr.rhodless.hcfcore.commands.faction;

import fr.rhodless.hcfcore.CC;
import fr.rhodless.hcfcore.faction.Faction;
import fr.rhodless.hcfcore.file.LangValues;
import fr.rhodless.hcfcore.player.FPlayer;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class FactionInvites {
    public FactionInvites(String[] args, Player player) {

        StringBuilder string = new StringBuilder();
        int i = 1;
        for(String strings : new FPlayer(player.getUniqueId().toString()).getInvites()) {
            if(string.length() < 2) {
                string = new StringBuilder(new FPlayer(player.getUniqueId().toString()).getInvites().get(0));
            } else {
                string.append("&f, ").append(new FPlayer(player.getUniqueId().toString()).getInvites().get(i));
                i++;
            }
        }

        player.sendMessage(CC.translate(LangValues.FACTION_FACTIONINVITES_MYINVITES.getMessage())
                .replace("<size>", "" + new FPlayer(player.getUniqueId().toString()).getInvites().size())
                .replace("<invites>", string.toString()));

        if(Faction.getFaction(player) == null) {
            return;
        }

        StringBuilder string2 = new StringBuilder();
        int i2 = 1;
        for(String strings : new Faction(Faction.getFaction(player)).getInvited()) {
            if(string2.length() < 2) {
                string2 = new StringBuilder(new Faction(Faction.getFaction(player)).getInvited().get(0));
            } else {
                if(new Faction(Faction.getFaction(player)).getInvited().get(i2) == null) return;
                string2.append("&f, ").append(new Faction(Faction.getFaction(player)).getInvited().get(i2));
                i2++;
            }
        }

        player.sendMessage(CC.translate(LangValues.FACTION_FACTIONINVITES_MYFACTION.getMessage()
                .replace("<size>", "" + new FPlayer(player.getUniqueId().toString()).getInvites().size())
                .replace("<invites>", string2.toString())
                .replace("<faction>", Faction.getFaction(player))));


    }
}
