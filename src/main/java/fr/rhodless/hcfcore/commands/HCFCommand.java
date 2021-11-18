package fr.rhodless.hcfcore.commands;

import fr.rhodless.hcfcore.CC;
import fr.rhodless.hcfcore.commands.hcf.HCFCornerOne;
import fr.rhodless.hcfcore.commands.hcf.HCFCornerTwo;
import fr.rhodless.hcfcore.file.FileManagement;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

public class HCFCommand implements TabExecutor {

    public static void sendHelpMessage() {
        player.sendMessage(CC.translate("&6/zhcf setcorner1 <spawn/warzone/east-road/west-road/north-road/south-road>"));
        player.sendMessage(CC.translate("&6/zhcf setcorner2 <spawn/warzone/east-road/west-road/north-road/south-road>"));
    }

    private static Player player;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        player = (Player) commandSender;

        if(args.length == 0) {
            sendHelpMessage();
            return true;
        }

        if(args[0].equalsIgnoreCase("setcorner1")) {
            new HCFCornerOne(args, player);
        } else if(args[0].equalsIgnoreCase("setcorner2")) {
            new HCFCornerTwo(args, player);
        } else if(args[0].equalsIgnoreCase("help")) {
            sendHelpMessage();
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
