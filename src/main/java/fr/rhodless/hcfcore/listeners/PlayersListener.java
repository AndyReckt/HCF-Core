package fr.rhodless.hcfcore.listeners;

import fr.rhodless.hcfcore.CC;
import fr.rhodless.hcfcore.Main;
import fr.rhodless.hcfcore.faction.Territory;
import fr.rhodless.hcfcore.file.CooldownsValues;
import fr.rhodless.hcfcore.file.FileManagement;
import fr.rhodless.hcfcore.file.LangValues;
import fr.rhodless.hcfcore.file.LocationsValues;
import fr.rhodless.hcfcore.player.FPlayer;
import fr.rhodless.hcfcore.player.HCFClass;
import fr.rhodless.hcfcore.player.PlayerData;
import fr.rhodless.hcfcore.register.Cuboid;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unused")
public class PlayersListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        event.setJoinMessage(null);

        Main.getData().pearlCooldown.putIfAbsent(event.getPlayer().getUniqueId(), 0);
        Main.getData().goppleCooldown.putIfAbsent(event.getPlayer().getUniqueId(), 0);
        Main.getData().crappleCooldown.putIfAbsent(event.getPlayer().getUniqueId(), 0);
        Main.getData().playerClass.putIfAbsent(event.getPlayer().getUniqueId(), HCFClass.NONE);

        FileManagement config = FileManagement.CONFIG;

        if(config.getConfig().get("data." + event.getPlayer().getUniqueId()) == null) {
            List<String> allPlayers = FPlayer.allPlayers();
            allPlayers.add(event.getPlayer().getUniqueId().toString());
            Main.getInstance().getConfig().set("datas.list", allPlayers);
            Main.getInstance().getConfig().set("data." + event.getPlayer().getUniqueId() + ".kills", 0);
            Main.getInstance().getConfig().set("data." + event.getPlayer().getUniqueId() + ".deaths", 0);
            Main.getInstance().getConfig().set("data." + event.getPlayer().getUniqueId() + ".money", 2000);
        }

        Main.getInstance().getConfig().set("data." + event.getPlayer().getUniqueId() + ".username", event.getPlayer().getName());
        Main.getInstance().saveConfig();

        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {

        }, 1);

    }
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        Player player = e.getEntity();

        FileManagement file = FileManagement.LANG;
        FileManagement config = FileManagement.CONFIG;

        e.setDeathMessage(null);

        if(e.getEntity().getKiller() == null) {
            Bukkit.broadcastMessage(CC.translate(LangValues.DEATH_NATURAL.getMessage()
                    .replace("<player>", e.getEntity().getName())
                    .replace("<player_kills>", config.getConfig().getString("data." + player.getUniqueId() + ".kills"))
            ));
        } else {
            Player killer = e.getEntity().getKiller();
            Bukkit.broadcastMessage(CC.translate(LangValues.DEATH_PLAYER.getMessage()
                    .replace("<player>", e.getEntity().getName())
                    .replace("<player_kills>", config.getConfig().getString("data." + player.getUniqueId() + ".kills"))
                    .replace("<killer>", killer.getName())
                    .replace("<killer_kills>", config.getConfig().getString("data." + killer.getUniqueId() + ".kills"))
            ));
            Main.getInstance().getConfig().set("data." + killer.getUniqueId() + ".kills", config.getConfig().getInt("data." + killer.getUniqueId() + ".kills") + 1);
        }

        player.getWorld().strikeLightningEffect(player.getLocation());

        Main.getInstance().getConfig().set("data." + player.getUniqueId() + ".deaths", config.getConfig().getInt("data." + player.getUniqueId() + ".deaths") + 1);

        Main.getInstance().saveConfig();

    }

    @EventHandler
    public void onEat(PlayerItemConsumeEvent e) {

        Player player = e.getPlayer();

        if(e.getItem() != null && e.getItem().getType() == Material.GOLDEN_APPLE && e.getItem().getDurability() == 0) {
            if (Main.getData().crappleCooldown.get(player.getUniqueId()) == 0) {
                Main.getData().crappleCooldown.put(player.getUniqueId(), CooldownsValues.CRAPPLE.getTime() * 1000);
            } else {
                e.setCancelled(true);
                player.sendMessage(CC.translate(LangValues.COOLDOWN.getMessage()
                        .replace("<cooldown>", Main.getDate(Main.getData().crappleCooldown.get(player.getUniqueId())))
                ));
            }
        }

        if(e.getItem() != null && e.getItem().getType() == Material.GOLDEN_APPLE && e.getItem().getDurability() == 1) {
            if (Main.getData().goppleCooldown.get(player.getUniqueId()) == 0) {
                Main.getData().goppleCooldown.put(player.getUniqueId(), CooldownsValues.GOPPLE.getTime() * 60000);
            } else {
                e.setCancelled(true);
                player.sendMessage(CC.translate(LangValues.COOLDOWN.getMessage()
                        .replace("<cooldown>", Main.getDate(Main.getData().goppleCooldown.get(player.getUniqueId())))
                ));
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if(!(event.getEntity() instanceof Player)) return;

        Cuboid spawn = new Cuboid(LocationsValues.SPAWN_1.getLocation(), LocationsValues.SPAWN_2.getLocation());

        if(spawn.contains(event.getEntity().getLocation())) {
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if(!(event.getDamager() instanceof Player)) return;

        Cuboid spawn = new Cuboid(LocationsValues.SPAWN_1.getLocation(), LocationsValues.SPAWN_2.getLocation());

        if(spawn.contains(event.getDamager().getLocation())) {
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onWaterBucket(PlayerBucketFillEvent event) {
        Cuboid spawn = new Cuboid(LocationsValues.SPAWN_1.getLocation(), LocationsValues.SPAWN_2.getLocation());
        Cuboid warzone = new Cuboid(LocationsValues.WARZONE_1.getLocation(), LocationsValues.WARZONE_2.getLocation());
        Cuboid eastroad = new Cuboid(LocationsValues.EAST_ROAD_1.getLocation(), LocationsValues.EAST_ROAD_2.getLocation());
        Cuboid westroad = new Cuboid(LocationsValues.WEST_ROAD_1.getLocation(), LocationsValues.WEST_ROAD_2.getLocation());
        Cuboid northroad = new Cuboid(LocationsValues.NORTH_ROAD_1.getLocation(), LocationsValues.NORTH_ROAD_2.getLocation());
        Cuboid southroad = new Cuboid(LocationsValues.SOUTH_ROAD_1.getLocation(), LocationsValues.SOUTH_ROAD_2.getLocation());
        Player player = event.getPlayer();

        if(spawn.contains(event.getBlockClicked().getRelative(event.getBlockFace()))) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(LangValues.TERRITORY_CANTBUILD.getMessage()
                    .replace("<territory>", "§eSpawn")));
        }

        else if(eastroad.contains(event.getBlockClicked().getRelative(event.getBlockFace()))) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(LangValues.TERRITORY_CANTBUILD.getMessage()
                    .replace("<territory>", "§6East Road")));
        }

        else if(westroad.contains(event.getBlockClicked().getRelative(event.getBlockFace()))) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(LangValues.TERRITORY_CANTBUILD.getMessage()
                    .replace("<territory>", "§6West Road")));
        }

        else if(southroad.contains(event.getBlockClicked().getRelative(event.getBlockFace()))) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(LangValues.TERRITORY_CANTBUILD.getMessage()
                    .replace("<territory>", "§6South Road")));
        }

        else if(northroad.contains(event.getBlockClicked().getRelative(event.getBlockFace()))) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(LangValues.TERRITORY_CANTBUILD.getMessage()
                    .replace("<territory>", "§6North Road")));
        }

        else if(warzone.contains(event.getBlockClicked().getRelative(event.getBlockFace()))) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(LangValues.TERRITORY_CANTBUILD.getMessage()
                    .replace("<territory>", "§4Warzone")));
        }

        if(event.isCancelled()) {
            if(LangValues.TERRITORY_ROLLBACK.isValue()) {
                player.teleport(player.getLocation());
            }
        }
    }

    @EventHandler
    public void onWaterBucket(PlayerBucketEmptyEvent event) {
        Cuboid spawn = new Cuboid(LocationsValues.SPAWN_1.getLocation(), LocationsValues.SPAWN_2.getLocation());
        Cuboid warzone = new Cuboid(LocationsValues.WARZONE_1.getLocation(), LocationsValues.WARZONE_2.getLocation());
        Cuboid eastroad = new Cuboid(LocationsValues.EAST_ROAD_1.getLocation(), LocationsValues.EAST_ROAD_2.getLocation());
        Cuboid westroad = new Cuboid(LocationsValues.WEST_ROAD_1.getLocation(), LocationsValues.WEST_ROAD_2.getLocation());
        Cuboid northroad = new Cuboid(LocationsValues.NORTH_ROAD_1.getLocation(), LocationsValues.NORTH_ROAD_2.getLocation());
        Cuboid southroad = new Cuboid(LocationsValues.SOUTH_ROAD_1.getLocation(), LocationsValues.SOUTH_ROAD_2.getLocation());
        Player player = event.getPlayer();

        if(spawn.contains(event.getBlockClicked().getRelative(event.getBlockFace()))) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(LangValues.TERRITORY_CANTBUILD.getMessage()
                    .replace("<territory>", "§eSpawn")));
        }

        else if(eastroad.contains(event.getBlockClicked().getRelative(event.getBlockFace()))) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(LangValues.TERRITORY_CANTBUILD.getMessage()
                    .replace("<territory>", "§6East Road")));
        }

        else if(westroad.contains(event.getBlockClicked().getRelative(event.getBlockFace()))) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(LangValues.TERRITORY_CANTBUILD.getMessage()
                    .replace("<territory>", "§6West Road")));
        }

        else if(southroad.contains(event.getBlockClicked().getRelative(event.getBlockFace()))) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(LangValues.TERRITORY_CANTBUILD.getMessage()
                    .replace("<territory>", "§6South Road")));
        }

        else if(northroad.contains(event.getBlockClicked().getRelative(event.getBlockFace()))) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(LangValues.TERRITORY_CANTBUILD.getMessage()
                    .replace("<territory>", "§6North Road")));
        }

        else if(warzone.contains(event.getBlockClicked().getRelative(event.getBlockFace()))) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(LangValues.TERRITORY_CANTBUILD.getMessage()
                    .replace("<territory>", "§4Warzone")));
        }

        if(event.isCancelled()) {
            if(LangValues.TERRITORY_ROLLBACK.isValue()) {
                player.teleport(player.getLocation());
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {

        Player player = event.getPlayer();

        boolean deathban = true;

        if(Main.getData().playerTerritory.get(player.getUniqueId()) == null) {
            Main.getData().playerTerritory.put(player.getUniqueId(), new Territory(player.getLocation()).getTerritory());
            if(Main.getData().playerTerritory.get(player.getUniqueId()).equals("§eSpawn")) {
                deathban = false;
            }
            player.sendMessage(CC.translate(LangValues.TERRITORY_JOIN.getMessage()
                    .replace("<territory>", Main.getData().playerTerritory.get(player.getUniqueId())
                    .replace("<deathban>", (deathban ? "§cDeathban" : "§a")))
            ));
            return;
        }

        String territory = Main.getData().playerTerritory.get(player.getUniqueId());

        if(!territory.equals(new Territory(player.getLocation()).getTerritory())) {

            boolean deathban2 = !Main.getData().playerTerritory.get(player.getUniqueId()).equals("§eSpawn");

            player.sendMessage(CC.translate(LangValues.TERRITORY_LEAVE.getMessage()
                    .replace("<territory>", Main.getData().playerTerritory.get(player.getUniqueId()))
                            .replace("<deathban>", (deathban2 ? "§cDeathban" : "§aNon-Deathban"))
            ));

            Main.getData().playerTerritory.put(player.getUniqueId(), new Territory(player.getLocation()).getTerritory());
            Main.getData().playerTerritory.put(player.getUniqueId(), new Territory(player.getLocation()).getTerritory());
            if(Main.getData().playerTerritory.get(player.getUniqueId()).equals("§eSpawn")) {
                deathban = false;
            }
            player.sendMessage(CC.translate(LangValues.TERRITORY_JOIN.getMessage()
                    .replace("<territory>", Main.getData().playerTerritory.get(player.getUniqueId()))
                            .replace("<deathban>", (deathban ? "§cDeathban" : "§aNon-Deathban"))
            ));
        }

    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Cuboid spawn = new Cuboid(LocationsValues.SPAWN_1.getLocation(), LocationsValues.SPAWN_2.getLocation());
        Cuboid warzone = new Cuboid(LocationsValues.WARZONE_1.getLocation(), LocationsValues.WARZONE_2.getLocation());
        Cuboid eastroad = new Cuboid(LocationsValues.EAST_ROAD_1.getLocation(), LocationsValues.EAST_ROAD_2.getLocation());
        Cuboid westroad = new Cuboid(LocationsValues.WEST_ROAD_1.getLocation(), LocationsValues.WEST_ROAD_2.getLocation());
        Cuboid northroad = new Cuboid(LocationsValues.NORTH_ROAD_1.getLocation(), LocationsValues.NORTH_ROAD_2.getLocation());
        Cuboid southroad = new Cuboid(LocationsValues.SOUTH_ROAD_1.getLocation(), LocationsValues.SOUTH_ROAD_2.getLocation());
        Player player = event.getPlayer();

        if(spawn.contains(event.getBlock())) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(LangValues.TERRITORY_CANTBUILD.getMessage()
                    .replace("<territory>", "§eSpawn")));
        }

        else if(eastroad.contains(event.getBlock())) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(LangValues.TERRITORY_CANTBUILD.getMessage()
                    .replace("<territory>", "§6East Road")));
        }

        else if(westroad.contains(event.getBlock())) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(LangValues.TERRITORY_CANTBUILD.getMessage()
                    .replace("<territory>", "§6West Road")));
        }

        else if(southroad.contains(event.getBlock())) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(LangValues.TERRITORY_CANTBUILD.getMessage()
                    .replace("<territory>", "§6South Road")));
        }

        else if(northroad.contains(event.getBlock())) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(LangValues.TERRITORY_CANTBUILD.getMessage()
                    .replace("<territory>", "§6North Road")));
        }

        else if(warzone.contains(event.getBlock())) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(LangValues.TERRITORY_CANTBUILD.getMessage()
                    .replace("<territory>", "§4Warzone")));
        }

        if(event.isCancelled()) {
            if(LangValues.TERRITORY_ROLLBACK.isValue()) {
                player.teleport(player.getLocation());
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {

        Cuboid spawn = new Cuboid(LocationsValues.SPAWN_1.getLocation(), LocationsValues.SPAWN_2.getLocation());
        Cuboid warzone = new Cuboid(LocationsValues.WARZONE_1.getLocation(), LocationsValues.WARZONE_2.getLocation());
        Cuboid eastroad = new Cuboid(LocationsValues.EAST_ROAD_1.getLocation(), LocationsValues.EAST_ROAD_2.getLocation());
        Cuboid westroad = new Cuboid(LocationsValues.WEST_ROAD_1.getLocation(), LocationsValues.WEST_ROAD_2.getLocation());
        Cuboid northroad = new Cuboid(LocationsValues.NORTH_ROAD_1.getLocation(), LocationsValues.NORTH_ROAD_2.getLocation());
        Cuboid southroad = new Cuboid(LocationsValues.SOUTH_ROAD_1.getLocation(), LocationsValues.SOUTH_ROAD_2.getLocation());
        Player player = event.getPlayer();

        if(spawn.contains(event.getBlock())) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(LangValues.TERRITORY_CANTBUILD.getMessage()
                    .replace("<territory>", "§eSpawn")));
        }

        else if(eastroad.contains(event.getBlock())) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(LangValues.TERRITORY_CANTBUILD.getMessage()
                    .replace("<territory>", "§6East Road")));
        }

        else if(westroad.contains(event.getBlock())) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(LangValues.TERRITORY_CANTBUILD.getMessage()
                    .replace("<territory>", "§6West Road")));
        }

        else if(southroad.contains(event.getBlock())) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(LangValues.TERRITORY_CANTBUILD.getMessage()
                    .replace("<territory>", "§6South Road")));
        }

        else if(northroad.contains(event.getBlock())) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(LangValues.TERRITORY_CANTBUILD.getMessage()
                    .replace("<territory>", "§6North Road")));
        }

        else if(warzone.contains(event.getBlock())) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(LangValues.TERRITORY_CANTBUILD.getMessage()
                    .replace("<territory>", "§4Warzone")));
        }

        if(event.isCancelled()) {
            if(LangValues.TERRITORY_ROLLBACK.isValue()) {
                player.teleport(player.getLocation());
            }
        }

    }

    @EventHandler
    public void onPearlLaunch(final ProjectileLaunchEvent event) {
        if (!(event.getEntity() instanceof EnderPearl) || !(event.getEntity().getShooter() instanceof Player)) {
            return;
        }
        final EnderPearl enderPearl = (EnderPearl)event.getEntity();
        final Player player = (Player)enderPearl.getShooter();
        if (System.currentTimeMillis() - PlayerData.getByUUID(player.getUniqueId()).getLastLaunch() < TimeUnit.SECONDS.toMillis(CooldownsValues.PEARL.getTime())) {
            player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL));
            player.updateInventory();
            event.setCancelled(true);
            player.sendMessage(CC.translate(LangValues.COOLDOWN.getMessage().replace("<cooldown>", CooldownsValues.PEARL.getTime() - (int)(System.currentTimeMillis() - PlayerData.getByUUID(player.getUniqueId()).getLastLaunch()) / 1000 + " seconds")));
            return;
        }
        PlayerData.getByUUID(player.getUniqueId()).setLastLaunch(System.currentTimeMillis());
    }

    @EventHandler
    public void onLogin(final PlayerLoginEvent event) {
        new PlayerData(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onQuit(final PlayerQuitEvent event) {
        PlayerData.removePlayerData(event.getPlayer().getUniqueId());
    }


}
