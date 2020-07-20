package me.gamepixel.sbdarkauction.commands;
import Utils.Utils;
import me.gamepixel.sbdarkauction.SBDarkAuction;
import me.gamepixel.sbdarkauction.tasks.StartingDA;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.rmi.CORBA.Util;


public class DarkAuctionCommand implements CommandExecutor {

    private StartingDA da = new StartingDA();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("darkauction.admin")) {

                if (args.length == 0) {

                    player.sendMessage(Utils.color(Utils.prefix + "&cUse Correct Command Usage: \n /da help"));

                } else {

                    if (args[0].equalsIgnoreCase("help")) {
                        //will show the help page
                        player.sendMessage(Utils.color(showhelppage()));

                    } else if (args[0].equalsIgnoreCase("setup")) {

                        player.sendMessage(Utils.color(Utils.prefix + "&eSetup section: do the following commands step by step. \n &7/da entrancenpc &f- &dSets the location of Entrance NPC (where you are standing) \n &7/da teleportloc &f- &dSets the location of Players getting teleported after being accepted by DarkAuctioner \n &7/da itemshowcase &f- &dSets the location of item that comes up for auction"));
                        //set entrance npc

                        //set teleport location

                        //set display item location


                    } else if (args[0].equalsIgnoreCase("additem")) {

                        try {
                            ItemStack item = player.getInventory().getItemInMainHand();
                            if (item != null && !Utils.auctionitems.contains(item)) {
                                Utils.auctionitems.add(item);
                                player.sendMessage(Utils.color(Utils.prefix + "&bSuccessfully added to the list of next coming auction."));

                            } else {
                                player.sendMessage(Utils.color(Utils.prefix + "&cYou do not have any item in you hand or item already added once!"));

                            }

                        } catch (Exception ex) {

                            player.sendMessage(Utils.color(Utils.prefix + "&cYou do not have any item in you hand!"));

                        }



                    } else if (args[0].equalsIgnoreCase("reload")) {

                        SBDarkAuction.getInstance().reloadConfig();
                        player.sendMessage(Utils.color(Utils.prefix + "&7Configuration successfully reloaded!"));

                    } else if (args[0].equalsIgnoreCase("entrancenpc")) {
                        //da entrancenpc

                        SBDarkAuction.getInstance().getConfig().set("entrance-npc-coordinates", Utils.convertLocToString(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ(), player.getLocation().getWorld().getName(), player.getLocation().getPitch(), player.getLocation().getYaw()));
                        player.sendMessage(Utils.color(Utils.prefix + "&aEntrance Npc Has Successfully set."));
                        SBDarkAuction.getInstance().saveConfig();
                    } else if (args[0].equalsIgnoreCase("teleportloc")) {

                        //da teleportloc
                        SBDarkAuction.getInstance().getConfig().set("teleport-at-start", Utils.convertLocToString(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ(), player.getLocation().getWorld().getName(), player.getLocation().getPitch(), player.getLocation().getYaw()));
                        player.sendMessage(Utils.color(Utils.prefix + "&aTeleport Location Has Successfully set."));
                        SBDarkAuction.getInstance().saveConfig();
                    } else if (args[0].equalsIgnoreCase("itemshowcase")) {
                        //da itemshowcase
                        SBDarkAuction.getInstance().getConfig().set("itemshowcase", Utils.convertLocToString(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ(), player.getLocation().getWorld().getName(), player.getLocation().getPitch(), player.getLocation().getYaw()));
                        player.sendMessage(Utils.color(Utils.prefix + "&aItem Showcase Has Successfully set."));
                        SBDarkAuction.getInstance().saveConfig();

                    } else if (args[0].equalsIgnoreCase("start")) {
                        //test part - will get removed after main release
                        if (Utils.auctionitems.size() >= 3) {
                            da.entrancenpcspawn();
                            player.sendMessage(Utils.color("&aAuction has started!"));

                        } else {

                            player.sendMessage(Utils.color("&aYou need at least 3 items added to start the auction."));
                        }
                    }
                    else {

                        player.sendMessage(Utils.color(showhelppage()));

                    }

                }

            } else {

                player.sendMessage(Utils.color(Utils.prefix + "&7 Developed By Dead_Light"));

            }


        } else {

            sender.sendMessage(Utils.color(Utils.prefix + "&cSORRY!, But you are only allowed to execute this command through in-game chat."));

        }


        return false;
    }

    public String showhelppage() {
        String help = Utils.prefix + "&cUse Correct Command Usage: \n &7 /da setup \n &7/da additem (Will add the item that you are holding to the next Auction) \n /da reload \n &7/da start - Starting the DarkAuction";
        return help;
    }


}
