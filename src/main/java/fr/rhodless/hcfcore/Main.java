package fr.rhodless.hcfcore;

import com.avaje.ebean.config.UnderscoreNamingConvention;
import fr.rhodless.hcfcore.file.*;
import fr.rhodless.hcfcore.player.Data;
import fr.rhodless.hcfcore.register.Register;
import fr.rhodless.hcfcore.scoreboard.Update;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class Main extends JavaPlugin {

    private static Main INSTANCE;
    private static Data DATA;

    @Override
    public void onEnable() {

        INSTANCE = this;
        DATA = new Data();
        new Register(this);
        new Update(this);

        FileManagement.CONFIG.create(getLogger());
        FileManagement.LANG.create(getLogger());
        FileManagement.LOCATIONS.create(getLogger());
        FileManagement.COOLDOWNS.create(getLogger());
        FileManagement.SCOREBOARD.create(getLogger());

        for(Player players : Bukkit.getOnlinePlayers()) {
            Bukkit.getPluginManager().callEvent(new PlayerJoinEvent(players, null));
        }

        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void init() throws IOException {

        for(CooldownsValues values : CooldownsValues.values()) {
            FileConfiguration config = FileManagement.COOLDOWNS.getConfig();

            if(config.get(values.name()) == null) {
                config.set(values.name(), values.getRawTime());
                FileManagement.COOLDOWNS.save(config);
            }
        }

        for(LangValues values : LangValues.values()) {
            FileConfiguration config = FileManagement.LANG.getConfig();
            if(config.get(values.name().replace('_', '.')) == null) {
                if(values.getRawList() != null) {
                    config.set(values.name().replace('_', '.'), values.getRawList());
                } else if(values.getRawMessage() != null) {
                    config.set(values.name().replace('_', '.'), values.getRawMessage());
                } else {
                    config.set(values.name().replace('_', '.'), values.getRawValue());
                }
                FileManagement.LANG.save(config);
            }
        }

        for(LocationsValues values : LocationsValues.values()) {
            FileConfiguration config = FileManagement.LOCATIONS.getConfig();

            if(config.get(values.getPath()) == null) {
                config.set(values.getPath(), values.getRawLocation());
                FileManagement.LOCATIONS.save(config);
            }
        }

        if(FileManagement.SCOREBOARD.getConfig().get(ScoreboardValues.LINES.getPath()) == null) {
            FileConfiguration config = FileManagement.SCOREBOARD.getConfig();
            config.set(ScoreboardValues.LINES.getPath(), ScoreboardValues.LINES.getList());
            FileManagement.SCOREBOARD.save(config);
        }
        if(FileManagement.SCOREBOARD.getConfig().get(ScoreboardValues.TITLE.getPath()) == null) {
            FileConfiguration config = FileManagement.SCOREBOARD.getConfig();
            config.set(ScoreboardValues.TITLE.getPath(), ScoreboardValues.LINES.getTitle());
            FileManagement.SCOREBOARD.save(config);
        }
    }

    public static String getDate(int milliseconds) {
        String simpledate = (new SimpleDateFormat("HH:mm:ss.S")).format(milliseconds);
        String substring = simpledate.substring(0, simpledate.length() - 2);
        if(simpledate.contains("00:") && !simpledate.contains("00:.")) {
            if(simpledate.endsWith("00")) {
                return substring.replace("00:", "");
            } else {
                return simpledate.replace("00:", "").replace("00:", "");
            }
        } else {
            if(simpledate.endsWith("00")) {
                return substring;
            } else {
                return simpledate;
            }
        }

    }

    @Override
    public void onDisable() {

        for(Player players : Bukkit.getOnlinePlayers()) {
            Bukkit.getPluginManager().callEvent(new PlayerQuitEvent(players, null));
        }

    }

    public static Main getInstance() {
        return INSTANCE;
    }

    public static Data getData() {
        return DATA;
    }
}
