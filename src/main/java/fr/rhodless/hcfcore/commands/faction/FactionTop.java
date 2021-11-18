package fr.rhodless.hcfcore.commands.faction;

import fr.rhodless.hcfcore.CC;
import fr.rhodless.hcfcore.Main;
import fr.rhodless.hcfcore.faction.Faction;
import fr.rhodless.hcfcore.file.LangValues;
import org.bukkit.entity.Player;

import java.util.*;

@SuppressWarnings({"unchecked cast", "unused"})
public class FactionTop {
    public FactionTop(String[] args, Player player) {
        List<String> factions = Faction.getFactions();
        HashMap<String, Integer> map = new HashMap<>();
        factions.forEach(faction -> {
            map.put(faction, new Faction(faction).getPoints());
        });
        Object[] a = map.entrySet().toArray();

        Arrays.sort(a, (o1, o2) -> ((Map.Entry<String, Integer>) o2).getValue().compareTo(((Map.Entry<String, Integer>) o1).getValue()));

        List<String> oFactions = new ArrayList<>();

        for (Object e : a) {
            oFactions.add(((Map.Entry<String, Integer>) e).getKey());
        }

        for(String string : LangValues.FACTION_TOP_HEADER.getList()) {
            player.sendMessage(CC.translate(string));
        }

        for(int i = 0; i <= 10; i++) {
            if(oFactions.size() < i+1) {
                for(String string : LangValues.FACTION_TOP_FOOTER.getList()) {
                    player.sendMessage(CC.translate(string));
                }
                return;
            } else {
                player.sendMessage(CC.translate(LangValues.FACTION_TOP_FORMAT.getMessage()
                        .replace("<position>", "" + (i+1))
                        .replace("<name>", new Faction(oFactions.get(i)).getName())
                        .replace("<points>", "" + new Faction(oFactions.get(i)).getPoints())));
            }
        }

        for(String string : LangValues.FACTION_TOP_FOOTER.getList()) {
            player.sendMessage(CC.translate(string));
        }
    }
}
