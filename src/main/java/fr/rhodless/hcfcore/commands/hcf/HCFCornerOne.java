package fr.rhodless.hcfcore.commands.hcf;

import fr.rhodless.hcfcore.CC;
import fr.rhodless.hcfcore.file.FileManagement;
import fr.rhodless.hcfcore.file.LocationsValues;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class HCFCornerOne {
    public HCFCornerOne(String[] args, Player player) {

        if(args.length == 1) {
            return;
        }

        FileConfiguration config = FileManagement.LOCATIONS.getConfig();

        if(args[1].equalsIgnoreCase("spawn")) {

            config.set(LocationsValues.SPAWN_1.getPath(), player.getLocation());
            FileManagement.LOCATIONS.save(config);
            player.sendMessage(CC.translate("&6Spawn corner 1 has been changed with success"));

        } else if(args[1].equalsIgnoreCase("warzone")) {

            config.set(LocationsValues.WARZONE_1.getPath(), player.getLocation());
            FileManagement.LOCATIONS.save(config);
            player.sendMessage(CC.translate("&6Warzone corner 1 has been changed with success"));

        } else if(args[1].equalsIgnoreCase("east-road")) {

            config.set(LocationsValues.EAST_ROAD_1.getPath(), player.getLocation());
            FileManagement.LOCATIONS.save(config);
            player.sendMessage(CC.translate("&6East-Road corner 1 has been changed with success"));

        } else if(args[1].equalsIgnoreCase("west-road")) {

            config.set(LocationsValues.WEST_ROAD_1.getPath(), player.getLocation());
            FileManagement.LOCATIONS.save(config);
            player.sendMessage(CC.translate("&6West-Road corner 1 has been changed with success"));

        } else if(args[1].equalsIgnoreCase("south-road")) {

            config.set(LocationsValues.SOUTH_ROAD_1.getPath(), player.getLocation());
            FileManagement.LOCATIONS.save(config);
            player.sendMessage(CC.translate("&6South-Road corner 1 has been changed with success"));

        } else if(args[1].equalsIgnoreCase("north-road")) {

            config.set(LocationsValues.NORTH_ROAD_1.getPath(), player.getLocation());
            FileManagement.LOCATIONS.save(config);
            player.sendMessage(CC.translate("&6North-Road corner 1 has been changed with success"));

        }

    }
}
