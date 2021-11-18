package fr.rhodless.hcfcore.commands.faction;

import fr.rhodless.hcfcore.CC;
import fr.rhodless.hcfcore.Main;
import fr.rhodless.hcfcore.commands.CommandFaction;
import fr.rhodless.hcfcore.faction.Faction;
import fr.rhodless.hcfcore.file.LangValues;
import org.bukkit.entity.Player;

import java.util.*;

@SuppressWarnings({"unchecked cast", "unused"})
public class FactionList {
    public FactionList(String[] args, Player player) {

        if(args.length != 2) {
            player.performCommand("f list 1");
            return;
        }

        List<String> factions = Faction.getFactions();
        HashMap<String, Integer> map = new HashMap<>();
        factions.forEach(faction -> {
            if(new Faction(faction).getOnline() != 0) {
                map.put(faction, new Faction(faction).getOnline());
            }
        });
        Object[] a = map.entrySet().toArray();

        Arrays.sort(a, (o1, o2) -> ((Map.Entry<String, Integer>) o2).getValue().compareTo(((Map.Entry<String, Integer>) o1).getValue()));

        List<String> oFactions = new ArrayList<>();

        for (Object e : a) {
            oFactions.add(((Map.Entry<String, Integer>) e).getKey());
        }

        double size = oFactions.size();
        double i = size/10;
        double j = (i+0.9);
        int page = (int) j;
//        if(Integer.parseInt(args[1]) == null) {
//            return;
//        }

        int thePage = Integer.parseInt(args[1]);
        if(thePage > page || thePage > 10) {
            CommandFaction.sendHelpMessage();
            return;
        }

        int number = 0;
        switch (thePage) {
            case 1:
                number = 1;
                break;
            case 2:
                number = 11;
                break;
            case 3:
                number = 21;
                break;
            case 4:
                number = 31;
                break;
            case 5:
                number = 41;
                break;
            case 6:
                number = 51;
                break;
            case 7:
                number = 61;
                break;
            case 8:
                number = 71;
                break;
            case 9:
                number = 81;
                break;
            case 10:
                number = 91;
                break;
        }

        for(String string : LangValues.FACTION_LIST_HEADER.getList()) {
            player.sendMessage(CC.translate(string
                    .replace("<page>", "" + thePage)
                    .replace("<total_pages>", "" + page)));
        }
        for(int k = number-1; k < number+10; k++) {
            if(oFactions.size() < k+1) {
                for(String string : LangValues.FACTION_LIST_FOOTER.getList()) {
                    player.sendMessage(CC.translate(string
                            .replace("<page>", "" + thePage)
                            .replace("<total_pages>", "" + page)));
                }
                return;
            } else {
                Faction faction = new Faction(oFactions.get(k));
                player.sendMessage(CC.translate(LangValues.FACTION_LIST_FORMAT.getMessage()
                        .replace("<position>", "" + (k+1))
                        .replace("<name>", "" + faction.getName())
                        .replace("<online>", "" + faction.getOnline())
                        .replace("<size>", "" + faction.getMembers().size())
                        .replace("<dtr>", "" + faction.getDTR())
                        .replace("<max_dtr>", "" + faction.getMaxDTR())));
            }
        }
        for(String string : LangValues.FACTION_LIST_FOOTER.getList()) {
            player.sendMessage(CC.translate(string
                    .replace("<page>", "" + thePage)
                    .replace("<total_pages>", "" + page)));
        }
    }
}
