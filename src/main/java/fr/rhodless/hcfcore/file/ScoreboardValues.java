package fr.rhodless.hcfcore.file;

import fr.rhodless.hcfcore.player.FPlayer;
import jdk.nashorn.internal.ir.LexicalContext;

import java.util.Arrays;
import java.util.List;

public enum ScoreboardValues {

    TITLE("scoreboard.title", "&6&lzHCF"),
    LINES("scoreboard.lines", Arrays.asList(
            "&7&m----------------------",
            "&2&lKills: &f<kills>",
            "&4&lDeaths: &f<deaths>",
            "&d&lPearl Cooldown: &f<pearl_cooldown> [display=<ender_pearl>]",
            "&6&lGopple: &f<gopple> [display=<gopple>]",
            "&e&lCrapple: &f<crapple> [display=<crapple>]",
            "&7&m----------------------"
    ));

    private final String path;
    private List<String> list;
    private String title;

    ScoreboardValues(String path, List<String> list) {
        this.path = path;
        this.list = list;
    }

    ScoreboardValues(String path, String title) {
        this.path = path;
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public List<String> getList() {
        return list;
    }

    public String getTitle() {
        return title;
    }
}
