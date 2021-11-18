package fr.rhodless.hcfcore.faction;

import fr.rhodless.hcfcore.file.LocationsValues;
import fr.rhodless.hcfcore.register.Cuboid;
import org.bukkit.Location;

public class Territory {

    private final Location location;

    public Territory(Location location) {
        this.location = location;
    }

    public String getTerritory() {

        String territory = "§2Wilderness";

        Cuboid spawn = new Cuboid(LocationsValues.SPAWN_1.getLocation(), LocationsValues.SPAWN_2.getLocation());
        Cuboid warzone = new Cuboid(LocationsValues.WARZONE_1.getLocation(), LocationsValues.WARZONE_2.getLocation());
        Cuboid southroad = new Cuboid(LocationsValues.SOUTH_ROAD_1.getLocation(), LocationsValues.SOUTH_ROAD_2.getLocation());
        Cuboid northroad = new Cuboid(LocationsValues.NORTH_ROAD_1.getLocation(), LocationsValues.NORTH_ROAD_2.getLocation());
        Cuboid eastroad = new Cuboid(LocationsValues.EAST_ROAD_1.getLocation(), LocationsValues.EAST_ROAD_2.getLocation());
        Cuboid westroad = new Cuboid(LocationsValues.WEST_ROAD_1.getLocation(), LocationsValues.WEST_ROAD_2.getLocation());

        if(spawn.contains(location)) {
            territory = "§eSpawn";
        } else if(southroad.contains(location)) {
            territory = "§6South-Road";
        } else if(northroad.contains(location)) {
            territory = "§6North-Road";
        } else if(eastroad.contains(location)) {
            territory = "§6East-Road";
        } else if(westroad.contains(location)) {
            territory = "§6West-Road";
        } else if(warzone.contains(location)) {
            territory = "§4Warzone";
        }

        for(String string : Faction.getFactions()) {
            Cuboid cuboid = new Faction(string).getClaim();
            if(cuboid.contains(location)) {
                territory = "§c" + string;
            }
        }

        return territory;

    }

    public Location getLocation() {
        return location;
    }
}
