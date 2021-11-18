package fr.rhodless.hcfcore.player;

import fr.rhodless.hcfcore.faction.Territory;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Data {

    public HashMap<UUID, Integer> pearlCooldown = new HashMap<>();
    public HashMap<UUID, Integer> goppleCooldown = new HashMap<>();
    public HashMap<UUID, Integer> crappleCooldown = new HashMap<>();
    public HashMap<UUID, HCFClass> playerClass = new HashMap<>();
    public HashMap<UUID, String> playerTerritory = new HashMap<>();

}
