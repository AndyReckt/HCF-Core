package fr.rhodless.hcfcore.commands.hcf;

import fr.rhodless.hcfcore.CC;
import fr.rhodless.hcfcore.file.FileManagement;
import fr.rhodless.hcfcore.file.LocationsValues;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class HCFCornerTwo {
    public HCFCornerTwo(String[] args, Player player) {

        if(args.length == 1) {
            return;
        }

        FileConfiguration config = FileManagement.LOCATIONS.getConfig();

        if(args[1].equalsIgnoreCase("spawn")) {
            
            config.set(LocationsValues.SPAWN_2.getPath(), player.getLocation());
            FileManagement.LOCATIONS.save(config);
            player.sendMessage(CC.translate("&6Spawn corner 2 has been changed with success"));
        
        } else if(args[1].equalsIgnoreCase("warzone")) {
            
            config.set(LocationsValues.WARZONE_2.getPath(), player.getLocation());
            FileManagement.LOCATIONS.save(config);
            player.sendMessage(CC.translate("&6Warzone corner 2 has been changed with success"));
        
        } else if(args[1].equalsIgnoreCase("east-road")) {
            
            config.set(LocationsValues.EAST_ROAD_2.getPath(), player.getLocation());
            FileManagement.LOCATIONS.save(config);
            player.sendMessage(CC.translate("&6East-Road corner 2 has been changed with success"));
        
        } else if(args[1].equalsIgnoreCase("west-road")) {
            
            config.set(LocationsValues.WEST_ROAD_2.getPath(), player.getLocation());
            FileManagement.LOCATIONS.save(config);
            player.sendMessage(CC.translate("&6West-Road corner 2 has been changed with success"));
        
        } else if(args[1].equalsIgnoreCase("south-road")) {
            
            config.set(LocationsValues.SOUTH_ROAD_2.getPath(), player.getLocation());
            FileManagement.LOCATIONS.save(config);
            player.sendMessage(CC.translate("&6South-Road corner 2 has been changed with success"));
        
        } else if(args[1].equalsIgnoreCase("north-road")) {
            
            config.set(LocationsValues.NORTH_ROAD_2.getPath(), player.getLocation());
            FileManagement.LOCATIONS.save(config);
            player.sendMessage(CC.translate("&6North-Road corner 2 has been changed with success"));

        }

    }
}
