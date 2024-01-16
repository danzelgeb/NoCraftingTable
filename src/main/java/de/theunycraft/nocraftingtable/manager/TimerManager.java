package de.theunycraft.nocraftingtable.manager;

import de.theunycraft.nocraftingtable.NoCraftingTable;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimerManager {
    private boolean running;
    private int time;

    public TimerManager(int time) {
        this.running = false;
        this.time = time;
    }

    public void start() {

    }

    public void stop() {

    }

    public void reset() {

    }

    public void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (Bukkit.getOnlinePlayers().size() == 0) stop();
                Bukkit.getOnlinePlayers().forEach(player -> {
                    player.sendActionBar(formateTime(time));
                });

                if (isRunning()) {
                    time++;
                }
                NoCraftingTable.getInstance().getConfig().set("timer.time", time);
            }
        }.runTaskTimer(NoCraftingTable.getInstance(), 20, 20);
    }

    private Component formateTime(int time) {
        if (!isRunning()) return Component.text(ChatColor.BOLD.toString() + ChatColor.GOLD.toString() + "Timer Paused");
        String string = "";

        int days = 0;
        int hours = 0;
        int min = 0;
        int sec = 0;

        if (time / 60 / 60 / 24 >= 1) {
            days = time / 60 / 60 / 24;
            time = time - (time / 60 / 24) * 60;
        }
        if (time / 60 / 60 >= 1) {
            hours = time / 60 / 60;
            time = time - (time / 60) * 60;
        }
        if (time / 60 >= 1) {
            hours = time / 60;
            time = time - (time / 60) * 60;
        }
        if (time >= 1) {
            sec = time;
        }
        if (days >= 1)
            if (days == 1) {
                string = days + "d";
            } else {
                string = days + "d";
            }
        if (hours != 0)
            if (hours <= 9) {
                string = string + "0" + hours + ":";
            } else {
                string = string + hours + ":";
            }
        if (min <= 9) {
            string = string + "0" + min + ":";
        } else {
            string = string + min + ":";
        }
        if (sec <= 9) {
            string = string + "0" + sec;
        } else {
            string = string + sec;
        }

        return Component.text(ChatColor.AQUA + string);
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getTime() {
        return time;
    }
}
