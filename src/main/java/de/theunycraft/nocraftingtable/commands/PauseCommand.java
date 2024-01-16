package de.theunycraft.nocraftingtable.commands;

import de.theunycraft.nocraftingtable.NoCraftingTable;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class PauseCommand  implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        NoCraftingTable.getInstance().getTimerManager().stop();
        commandSender.sendMessage("Timer paused...");
        return false;
    }
}