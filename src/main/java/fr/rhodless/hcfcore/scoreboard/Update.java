package fr.rhodless.hcfcore.scoreboard;

import fr.rhodless.hcfcore.CC;
import fr.rhodless.hcfcore.Main;
import fr.rhodless.hcfcore.file.FileManagement;
import fr.rhodless.hcfcore.file.ScoreboardValues;
import fr.rhodless.hcfcore.player.FPlayer;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.*;

@SuppressWarnings({"unused"})
public class Update implements Listener {

    private final Map<UUID, FastBoard> boards = new HashMap<>();

    public Update(Main main) {

        Bukkit.getServer().getPluginManager().registerEvents(this, main);

        Bukkit.getServer().getScheduler().runTaskTimer(main, () -> {
            for (FastBoard board : boards.values()) {
                updateBoard(board);
            }

            for(Player pls : Bukkit.getOnlinePlayers()) {
                if(Main.getData().pearlCooldown.get(pls.getUniqueId()) != 0) {
                    Main.getData().pearlCooldown.put(pls.getUniqueId(), Main.getData().pearlCooldown.get(pls.getUniqueId()) - 100);
                }

                if(Main.getData().goppleCooldown.get(pls.getUniqueId()) != 0) {
                    Main.getData().goppleCooldown.put(pls.getUniqueId(), Main.getData().goppleCooldown.get(pls.getUniqueId()) - 100);
                }

                if(Main.getData().crappleCooldown.get(pls.getUniqueId()) != 0) {
                    Main.getData().crappleCooldown.put(pls.getUniqueId(), Main.getData().crappleCooldown.get(pls.getUniqueId()) - 100);
                }
            }

        }, 0, 2);


    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        FastBoard board = new FastBoard(player);

        FileManagement scoreboard = FileManagement.SCOREBOARD;

        board.updateTitle(CC.translate(ScoreboardValues.TITLE.getTitle()));

        boards.put(player.getUniqueId(), board);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        FastBoard board = boards.remove(player.getUniqueId());

        if (board != null) {
            board.delete();
        }
    }

    private void updateBoard(FastBoard board) {
        FileManagement config = FileManagement.CONFIG;
        FileConfiguration fileConfig = config.getConfig();
        List<String> list = new ArrayList<>();
        Player player = board.getPlayer();
        for(String str : ScoreboardValues.LINES.getList()) {
            if(str.contains("[display=<gopple>]")) if(Main.getData().goppleCooldown.get(player.getUniqueId()) == 0) str = null;
            if (str != null && str.contains("[display=<ender_pearl>]"))
                if (Main.getData().pearlCooldown.get(player.getUniqueId()) == 0) str = null;
            if (str != null && str.contains("[display=<crapple>]"))
                if (Main.getData().crappleCooldown.get(player.getUniqueId()) == 0) str = null;

            if(str != null)

                list.add(CC.translate(str

                    .replace("[display=<ender_pearl>]", "")
                    .replace("[display=<crapple>]", "")
                    .replace("[display=<gopple>]", "")

                    .replace("<kills>", "" + new FPlayer(player.getUniqueId().toString()).getKills())
                    .replace("<deaths>", "" + new FPlayer(player.getUniqueId().toString()).getDeaths())
                    .replace("<player>", player.getName())
                    .replace("<players>", String.valueOf(Bukkit.getOnlinePlayers().size()))
                    .replace("<pearl_cooldown>", Main.getDate(Main.getData().pearlCooldown.get(player.getUniqueId())))
                    .replace("<gopple>", Main.getDate(Main.getData().goppleCooldown.get(player.getUniqueId())))
                    .replace("<crapple>", Main.getDate(Main.getData().crappleCooldown.get(player.getUniqueId())))

            ));
        }

        board.updateLines(
            list
        );
    }

}
