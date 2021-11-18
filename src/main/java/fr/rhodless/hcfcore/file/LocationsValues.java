package fr.rhodless.hcfcore.file;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public enum LocationsValues {

    SPAWN_1("spawn.corner1", new Location(Bukkit.getWorld("world"), 72, 255, 72)),
    SPAWN_2("spawn.corner2", new Location(Bukkit.getWorld("world"), -72, 0, -72)),

    WARZONE_1("warzone.corner1", new Location(Bukkit.getWorld("world"), 300, 255, 300)),
    WARZONE_2("warzone.corner2", new Location(Bukkit.getWorld("world"), -300, 0, -300)),

    EAST_ROAD_1("eastroad.corner1", new Location(Bukkit.getWorld("world"), 72, 255, -16)),
    EAST_ROAD_2("eastroad.corner2", new Location(Bukkit.getWorld("world"), 3000, 0, 16)),

    WEST_ROAD_1("westroad.corner1", new Location(Bukkit.getWorld("world"), -72, 255, 16)),
    WEST_ROAD_2("westroad.corner2", new Location(Bukkit.getWorld("world"), -3000, 0, -16)),

    SOUTH_ROAD_1("southroad.corner1", new Location(Bukkit.getWorld("world"), 16, 255, 73)),
    SOUTH_ROAD_2("southroad.corner2", new Location(Bukkit.getWorld("world"), -16, 0, 3000)),

    NORTH_ROAD_1("northroad.corner1", new Location(Bukkit.getWorld("world"), -16, 255, -72)),
    NORTH_ROAD_2("northroad.corner2", new Location(Bukkit.getWorld("world"), 16, 255, -3000));


    private final String path;
    private final Location location;

    LocationsValues(String path, Location location) {
        this.path = path.toUpperCase();
        this.location = location;
    }

    public Location getRawLocation() {
        return location;
    }

    public String getPath() {
        return path;
    }

    public Location getLocation() {
        return (Location) FileManagement.LOCATIONS.getConfig().get(getPath());
    }
}
