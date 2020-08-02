package me.deadlight.darkauction.commands;
import Utils.*;
import me.deadlight.darkauction.tasks.StartingDA;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


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

                        player.sendMessage(Utils.color(Utils.prefix + "&eSetup section: execute the following commands step by step. \n &7/da entrancenpc &f- &dSets the location of Entrance NPC (where you are standing) \n &7/da teleportloc &f- &dSets the location of Players getting teleported after being accepted by DarkAuctioner \n &7/da itemshowcase &f- &dSets the location of item that comes up for auction"));
                        //set entrance npc

                        //set teleport location

                        //set display item location


                    } else if (args[0].equalsIgnoreCase("additem")) {
                        if (args.length >= 3) {

                            try {
                                int modifier = Integer.parseInt(args[2]);
                                int price = Integer.parseInt(args[1]);
                                try {
                                    DAItem item = new DAItem(player.getInventory().getItemInMainHand(), price, modifier);
                                    if (item.getItemStack() != null && !Utils.auctionitems.contains(item)) {
                                        Utils.auctionitems.add(item);
                                        player.sendMessage(Utils.color(Utils.prefix + "&bSuccessfully added to the list of next coming auction."));

                                    } else {
                                        player.sendMessage(Utils.color(Utils.prefix + "&cYou do not have any item in you hand or item already added once!"));

                                    }

                                } catch (Exception ex) {

                                    player.sendMessage(Utils.color(Utils.prefix + "&cYou do not have any item in you hand!"));

                                }
                            } catch (NumberFormatException exception) {

                                player.sendMessage(Utils.color(Utils.prefix + "&cPlease put a number for the price and modifier."));

                            }


                        } else {

                            player.sendMessage(Utils.color(Utils.prefix + "&6You are missing an argument! &c/da additem price modifier"));

                        }



                    } else if (args[0].equalsIgnoreCase("reload")) {

                        ConfigManager.reloadCommand();
                        player.sendMessage(Utils.color(Utils.prefix + "&7Configuration successfully reloaded!"));

                    } else if (args[0].equalsIgnoreCase("entrancenpc")) {
                        //da entrancenpc
                        Utils.config.set("entrance-npc-coordinates", Utils.convertLocToString(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ(), player.getLocation().getWorld().getName(), player.getLocation().getPitch(), player.getLocation().getYaw()));
                        player.sendMessage(Utils.color(Utils.prefix + "&aEntrance Npc Has Successfully set."));
                        ConfigManager.reloadConfig();
                    } else if (args[0].equalsIgnoreCase("teleportloc")) {

                        //da teleportloc
                        Utils.config.set("teleport-at-start", Utils.convertLocToString(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ(), player.getLocation().getWorld().getName(), player.getLocation().getPitch(), player.getLocation().getYaw()));
                        player.sendMessage(Utils.color(Utils.prefix + "&aTeleport Location Has Successfully set."));
                        ConfigManager.reloadConfig();
                    } else if (args[0].equalsIgnoreCase("itemshowcase")) {
                        //da itemshowcase
                        Utils.config.set("itemshowcase", Utils.convertLocToString(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ(), player.getLocation().getWorld().getName(), player.getLocation().getPitch(), player.getLocation().getYaw()));
                        player.sendMessage(Utils.color(Utils.prefix + "&aItem Showcase Has Successfully set."));
                        ConfigManager.reloadConfig();

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
