package fr.rhodless.hcfcore.file;

import fr.rhodless.hcfcore.player.FPlayer;

import java.util.List;

public class Utils {

    public static String getPlayerColor(FPlayer player) {
        String a = "§7";
        if(player.isOnline()) {
            a = "§a";
        } else if(player.getDeathBanned()) {
            a = "§c";
        }
        return a;
    }

    public static String getFormat(List<String> players) {
        String format = LangValues.PLAYERFORMAT.getMessage();
        StringBuilder string = new StringBuilder();
        for(String string2 : players) {
            FPlayer player = new FPlayer(string2);
            if(string.length() < 2) {
                string = new StringBuilder(format
                        .replace("<player>", getPlayerColor(player) + player.getUsername())
                        .replace("<player_kills>", "" + new FPlayer(string2).getKills()));
            } else {
                string.append("&f, ").append(format
                        .replace("<player>", getPlayerColor(player) + player.getUsername())
                        .replace("<player_kills>", "" + new FPlayer(string2).getKills()));
            }
        }

        return string.toString();
    }

}
