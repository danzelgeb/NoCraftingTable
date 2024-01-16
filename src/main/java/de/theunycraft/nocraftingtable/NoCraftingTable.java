package de.theunycraft.nocraftingtable;

import de.theunycraft.nocraftingtable.commands.PauseCommand;
import de.theunycraft.nocraftingtable.commands.StartCommand;
import de.theunycraft.nocraftingtable.listener.BlockInteractListener;
import de.theunycraft.nocraftingtable.listener.JoinQuitListener;
import de.theunycraft.nocraftingtable.manager.TimerManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class NoCraftingTable extends JavaPlugin {
    private static NoCraftingTable instance;
    private TimerManager timerManager;

    @Override
    public void onLoad() {
        instance = this;
        saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new BlockInteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new JoinQuitListener(), this);

        getCommand("start").setExecutor(new StartCommand());
        getCommand("stop").setExecutor(new PauseCommand());
        getCommand("pause").setExecutor(new PauseCommand());

        timerManager = new TimerManager(getConfig().getInt("timer.time"));
        timerManager.run();
    }

    @Override
    public void onDisable() {
        getConfig().set("timer.time", timerManager.getTime());
        saveConfig();
    }

    public static NoCraftingTable getInstance() {
        return instance;
    }

    public TimerManager getTimerManager() {
        return timerManager;
    }
}
