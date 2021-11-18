package fr.rhodless.hcfcore.commands.faction;

import fr.rhodless.hcfcore.CC;
import fr.rhodless.hcfcore.commands.CommandFaction;
import fr.rhodless.hcfcore.faction.Faction;
import fr.rhodless.hcfcore.file.LangValues;
import fr.rhodless.hcfcore.player.FPlayer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

@SuppressWarnings("all")
public class FactionInvite {
    public FactionInvite(String[] args, Player player) {

        if(args.length != 2) {
            CommandFaction.sendHelpMessage();
            return;
        }

        String target = args[1];
        if(FPlayer.getByName(target) == null) {
            CommandFaction.sendHelpMessage();
            return;
        }

        String uuid = FPlayer.getByName(target);

        if(Faction.getFaction(target) != null) {
            player.sendMessage(CC.translate(LangValues.FACTION_TARGETINFACTION.getMessage()
                    .replace("<player>", target)));
            return;
        }

        if(Faction.getFaction(player) == null) {
            player.sendMessage(CC.translate(LangValues.FACTION_NOTINFACTION.getMessage()));
            return;
        }

        Faction faction = new Faction(Faction.getFaction(player));

        if(faction.getInvited().contains(uuid)) {
            player.sendMessage(CC.translate(LangValues.FACTION_INVITE_ALREADYINVITED.getMessage()
                    .replace("<player>", player.getName())));
            return;
        }



        faction.setInvited(uuid, true);
        for(String string : faction.getMembers()) {
            if(new FPlayer(string).isOnline()) {
                Bukkit.getPlayer(UUID.fromString(string)).sendMessage(CC.translate(LangValues.FACTION_INVITE_SUCCESS.getMessage()
                        .replace("<player>", target)));
            }
        }

        Player targetPlayer = Bukkit.getPlayer(target);
        if(targetPlayer == null) {
            return;
        }

        targetPlayer.sendMessage(CC.translate(LangValues.FACTION_INVITE_RECEIVE.getMessage()
                .replace(
                        "<player>",
                        player.getName())
                .replace("<faction_name>", faction.getName())));
        TextComponent accept = new TextComponent(CC.translate(LangValues.FACTION_INVITE_ACCEPT.getMessage()));
        accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/f join " + player.getName()));
        targetPlayer.spigot().sendMessage(accept);

    }
}
