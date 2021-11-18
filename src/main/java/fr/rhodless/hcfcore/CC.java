package fr.rhodless.hcfcore;

import org.bukkit.ChatColor;

public class CC {

    public static String translate(String textToTranslate) {
        return ChatColor.translateAlternateColorCodes('&',textToTranslate);
    }

}
