package fr.rhodless.hcfcore.commands;

import fr.rhodless.hcfcore.CC;
import fr.rhodless.hcfcore.Main;
import fr.rhodless.hcfcore.faction.Faction;
import fr.rhodless.hcfcore.file.LangValues;
import fr.rhodless.hcfcore.file.ScoreboardValues;
import fr.rhodless.hcfcore.file.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import fr.rhodless.hcfcore.commands.faction.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandFaction implements TabExecutor {

    public static void sendHelpMessage() {
        for(String string : LangValues.FACTIONHELP_ONE.getList()) {
            player.sendMessage(CC.translate(string));
        }
    }

    static Player player;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(!(commandSender instanceof Player)) return true;

        Player player = (Player) commandSender;
        CommandFaction.player = player;

        if(args.length == 0) {
            sendHelpMessage();
            return true;
        }

        if(args[0].equalsIgnoreCase("create")) {
            new FactionCreate(args, player);
        } if(args[0].equalsIgnoreCase("disband") || args[0].equalsIgnoreCase("delete")) {
            new FactionDisband(args, player);
        } else if(args[0].equalsIgnoreCase("info") || args[0].equalsIgnoreCase("show")) {
            new FactionShow(args, player);
        } if(args[0].equalsIgnoreCase("rename")) {
            new FactionRename(args, player);
        } if(args[0].equalsIgnoreCase("list")) {
            new FactionList(args, player);
        } if(args[0].equalsIgnoreCase("top")) {
            new FactionTop(args, player);
        } if(args[0].equalsIgnoreCase("invite")) {
            new FactionInvite(args, player);
        } if(args[0].equalsIgnoreCase("accept") || args[0].equalsIgnoreCase("join")) {
            new FactionJoin(args, player);
        } if(args[0].equalsIgnoreCase("uninvite")) {
            new FactionUnInvite(args, player);
        } if(args[0].equalsIgnoreCase("invites")) {
            new FactionInvites(args, player);
        }

        return false;
    }

    public static List<String> getFactionInfo(Faction faction) {
        List<String> factionInfo = LangValues.FACTION_INFO.getList();
        List<String> finalMessage = new ArrayList<>();

        for(String string : factionInfo) {

            if(string.contains("<members_format>"))
                if(faction.getMembersWithoutRanked().size() == 0) string = null;
            if (string != null && string.contains("<captains_format>"))
                if (faction.getCaptains().size() == 0) string = null;
            if (string != null && string.contains("co_leaders_format"))
                if (faction.getCoLeaders().size() == 0) string = null;

            if(string != null)
                finalMessage.add(string
                    .replace("<faction_name>", faction.getName())
                    .replace("<online>", "" + faction.getOnline())
                    .replace("<total_members>", "" + faction.getFactionSize())
                    .replace("<members_format>", Utils.getFormat(faction.getMembersWithoutRanked()))
                    .replace("<captains_format>", Utils.getFormat(faction.getCaptains()))
                    .replace("<home>", (faction.getHome().getZ() == 0 ? "Not set" : faction.getHome().getBlockX() + ", " + faction.getHome().getBlockY() + ", " + faction.getHome().getBlockY()))
                    .replace("<z>", "" + (faction.getHome().getBlockY() == 0 ? "" : faction.getHome().getBlockZ()))
                    .replace("<dtr>", (faction.getDTR() < 0 ? "&c" + faction.getDTR() : "&a" + faction.getDTR()))
                    .replace("<kills>", "" + faction.getKills())
                    .replace("<points>", "" + faction.getPoints())
                    .replace("<dtr_status>", "" + faction.getDTRStatus())
                    .replace("<leader_format>", Utils.getFormat(Collections.singletonList(faction.getLeader())))
                    .replace("<balance>", "" + faction.getBalance())
                    .replace("<co_leaders_format>", Utils.getFormat(faction.getCoLeaders())));
        }

        return finalMessage;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }

}
