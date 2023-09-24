package com.fluff_stuff.newgods.commands;

import com.fluff_stuff.newgods.Data;
import com.fluff_stuff.newgods.NewGods;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class adminDelete implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player)sender;
      if (!player.hasPermission("newgods.admin")) {
        player.sendMessage(ChatColor.DARK_RED + "Sorry but you're not permitted to do this.");
        return true;
      } 
      if (Data.godNames.contains(args[0])) {
        String gToPurge = label;
        for (String p : Data.playerNames) {
          int playerID = NewGods.data.getPlayerID(p);
          String god = Data.playerGod.get(playerID);
          if (god.equalsIgnoreCase(args[0]))
            God.LeaveGod(player); 
        } 
        player.sendMessage(ChatColor.GREEN + "All entries for god " + label + " removed. Please Restart the server for changes to take effect.");
        return true;
      } 
      player.sendMessage(ChatColor.DARK_RED + "There is no god with that name.");
    } 
    return true;
  }
}
