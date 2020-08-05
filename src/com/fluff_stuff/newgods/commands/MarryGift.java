package com.fluff_stuff.newgods.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.fluff_stuff.newgods.NewGods;

public class MarryGift implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if(player.hasPermission("newgods.marrygift")==false){
				player.sendMessage(ChatColor.DARK_RED+"Sorry but you're not permitted to do this.");
				return true;
			}
			int playerID = NewGods.data.getPlayerID(player.getName());
			String spouce = NewGods.data.playerPartner.get(playerID);
			if(spouce.equals("null")){
				player.sendMessage(ChatColor.DARK_RED+"You need to be married to do this.");
				return true;
			}
			Player p = Bukkit.getServer().getPlayer(spouce);
			if(p==null){
				player.sendMessage(ChatColor.DARK_RED+"Your partner isn't online.");
				return true;
			}
			player.sendMessage(ChatColor.AQUA+"Gifted the item you were holding.");
			ItemStack gift = player.getItemInHand();
			p.getInventory().addItem(gift);
			p.sendMessage(ChatColor.AQUA+player.getName()+" gifted you "+gift.toString()+".");
			player.getItemInHand().setAmount(0);
		}
		return true;
	}

}
