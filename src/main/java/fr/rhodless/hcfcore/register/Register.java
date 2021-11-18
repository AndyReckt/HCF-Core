package fr.rhodless.hcfcore.register;

import fr.rhodless.hcfcore.Main;
import fr.rhodless.hcfcore.commands.CommandFaction;
import fr.rhodless.hcfcore.commands.HCFCommand;
import fr.rhodless.hcfcore.listeners.PlayersListener;

public class Register {

    public Register(Main main) {
        main.getCommand("f").setExecutor(new CommandFaction());
        main.getCommand("f").setTabCompleter(new CommandFaction());

        main.getCommand("hcf").setExecutor(new HCFCommand());
        main.getCommand("hcf").setTabCompleter(new HCFCommand());

        main.getServer().getPluginManager().registerEvents(new PlayersListener(), main);
    }
}
