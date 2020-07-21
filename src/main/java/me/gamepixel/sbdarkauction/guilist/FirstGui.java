package me.gamepixel.sbdarkauction.guilist;

import Utils.Utils;
import me.gamepixel.sbdarkauction.SBDarkAuction;
import me.gamepixel.sbdarkauction.tasks.StartingDA;
import me.mattstudios.mfgui.gui.guis.Gui;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FirstGui {

    //phase 1 --> gold nugget
    //phase 2 --> gold ingot
    //phase 3 --> emerald ingot
    //phase 4 --> Diamond
    public static Gui gui = new Gui(SBDarkAuction.getInstance(), 6, Utils.color("&6Bid Page"));
    public static GuiItem graystainedglass = new GuiItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7));
    //red border
    public static GuiItem redstainedglass = new GuiItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14));
    //gold nuggets
    public static HashMap<String, Integer> topbid = new HashMap<>();

    public static HashMap<String, String> topbidplayer = new HashMap<>();


    public static void makeguipage() {

        if (!Utils.guiphase.containsKey("phase")) {

            Utils.guiphase.put("phase", 1);

        }

        GuiItem sellingitem = new GuiItem(Utils.itemRightNow);
        //setting the main item which is going to be sold
        gui.setItem(22, sellingitem);
        //borders
        gui.getFiller().fillBorder(redstainedglass);
        for (int i : biddingItemsSlot()) {
            int amount = findRightPrice(i);
            ItemStack barr = new ItemStack(Material.BARRIER, findRightAmount(i));
            ItemMeta metai2 = barr.getItemMeta();
            metai2.setDisplayName(Utils.color("&c" + Integer.toString(amount) + " &cCoins ◈"));
            barr.setItemMeta(metai2);
            GuiItem barrier = new GuiItem(barr);
            if (Utils.guiphase.get("phase") == 1) {
                ItemStack goldnug = new ItemStack(Material.GOLD_NUGGET, findRightAmount(i));
                ItemMeta metai = goldnug.getItemMeta();
                metai.setDisplayName(Utils.color("&e" + Integer.toString(amount) + " &eCoins ◈"));
                goldnug.setItemMeta(metai);
                GuiItem goldnuggets = new GuiItem(goldnug);
                gui.setItem(i, goldnuggets);
            } else if (Utils.guiphase.get("phase") == 2) {
                ItemStack goldnug = new ItemStack(Material.GOLD_INGOT, findRightAmount(i));
                ItemMeta metai = goldnug.getItemMeta();
                metai.setDisplayName(Utils.color("&e" + Integer.toString(amount) + " &eCoins ◈"));
                goldnug.setItemMeta(metai);
                GuiItem goldnuggets = new GuiItem(goldnug);
                gui.setItem(i, goldnuggets);


            } else if (Utils.guiphase.get("phase") == 3) {

                ItemStack goldnug = new ItemStack(Material.EMERALD, findRightAmount(i));
                ItemMeta metai = goldnug.getItemMeta();
                metai.setDisplayName(Utils.color("&e" + Integer.toString(amount) + " &eCoins ◈"));
                goldnug.setItemMeta(metai);
                GuiItem goldnuggets = new GuiItem(goldnug);
                gui.setItem(i, goldnuggets);

            } else if (Utils.guiphase.get("phase") == 4) {
                ItemStack goldnug = new ItemStack(Material.DIAMOND, findRightAmount(i));
                ItemMeta metai = goldnug.getItemMeta();
                metai.setDisplayName(Utils.color("&e" + Integer.toString(amount) + " &eCoins ◈"));
                goldnug.setItemMeta(metai);
                GuiItem goldnuggets = new GuiItem(goldnug);
                gui.setItem(i, goldnuggets);


            }


            gui.addSlotAction(i, event -> {
                // Handle your open action
                if (event.getCurrentItem().getType() == Material.BARRIER) {

                    event.setCancelled(true);
                    event.getWhoClicked().sendMessage(Utils.color("&cThere is a bid higher than this amount!"));
                    Player player = (Player) event.getWhoClicked();
                    player.playNote(player.getLocation(), Instrument.BASS_DRUM, Note.natural(1, Note.Tone.E));

                } else if (event.getCurrentItem().getType() == Material.GOLD_NUGGET || event.getCurrentItem().getType() == Material.DIAMOND || event.getCurrentItem().getType() == Material.GOLD_INGOT || event.getCurrentItem().getType() == Material.EMERALD) {
                    event.setCancelled(true);
                    if (topbidplayer.get("top") == null || !topbidplayer.get("top").equals(event.getWhoClicked().getName())) {

                        if (isNextPageBid(event.getCurrentItem().getAmount())) {

                            topbid.put(event.getWhoClicked().getName(), Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).replace("Coins", "").replace("◈", "").replace(" ", "")));
                            int biddedamount = Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).replace("Coins", "").replace("◈", "").replace(" ", ""));
                            topbidplayer.put("top", event.getWhoClicked().getName());
                            gui.updateItem(i, getPlayerHead(event.getWhoClicked().getName(), findRightAmount(i)));
                            StartingDA.countnum = 10;
                            Player player = (Player) event.getWhoClicked();
                            StartingDA.spawnBiddingHolo(biddedamount, player.getName());
                            player.playSound(player.getLocation(), Sound.BLOCK_METAL_PLACE, 1.0F, 1.0F);
                            ChangeToBarrierSlot(findPreviousBids(findRightAmount(i), player));
                            for (Player player1 : StartingDA.playersinauction) {

                                player1.sendMessage(Utils.color("&e" + player.getName() + " &7Has bidded &c" + Integer.toString(biddedamount) + "&e Coins ◈&7!"));
                                player1.playSound(player1.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.5F);

                            }
                            Bukkit.getScheduler().scheduleSyncDelayedTask(SBDarkAuction.getInstance(), new Runnable() {
                                @Override
                                public void run() {
                                    if (!(Utils.guiphase.get("phase") + 1 > 4)) {
                                        Utils.guiphase.put("phase", Utils.guiphase.get("phase") + 1);
                                        makeguipage();
                                        try {
                                            gui.update();
                                        } catch (Exception e) {
                                            //do noth
                                        }
                                    }
                                }
                            }, 20 * 2);
                        } else {

                            topbid.put(event.getWhoClicked().getName(), Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).replace("Coins", "").replace("◈", "").replace(" ", "")));
                            int biddedamount = Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).replace("Coins", "").replace("◈", "").replace(" ", ""));
                            topbidplayer.put("top", event.getWhoClicked().getName());
                            gui.updateItem(i, getPlayerHead(event.getWhoClicked().getName(), findRightAmount(i)));
                            StartingDA.countnum = 10;
                            Player player = (Player) event.getWhoClicked();
                            StartingDA.spawnBiddingHolo(biddedamount, player.getName());
                            player.playSound(player.getLocation(), Sound.BLOCK_METAL_PLACE, 1.0F, 1.0F);
                            ChangeToBarrierSlot(findPreviousBids(findRightAmount(i), player));
                            for (Player player1 : StartingDA.playersinauction) {

                                player1.sendMessage(Utils.color("&e" + player.getName() + " &7Has bidded &c" + Integer.toString(biddedamount) + "&e Coins ◈&7!"));
                                player1.playSound(player1.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.5F);

                            }

                        }

                    } else if (topbidplayer.get("top").equals(event.getWhoClicked().getName())) {
                        Player player = (Player) event.getWhoClicked();
                        player.sendMessage(Utils.color("&cYou are already the top bidder!"));
                        player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1.0F, 2.0F);

                    }

                }

            });
        }
        gui.setDefaultClickAction(event -> {
            // Handle your default action here
            event.setCancelled(true);
        });

        gui.getFiller().fill(graystainedglass);
        
    }

    public static void ChangeToBarrierSlot(List<Integer> list) {

        for (int i : list) {
            int amount = findRightPrice(i);
            ItemStack barr = new ItemStack(Material.BARRIER, findRightAmount(i));
            ItemMeta metai2 = barr.getItemMeta();
            metai2.setDisplayName(Utils.color("&c" + Integer.toString(amount) + " &cCoins ◈"));
            barr.setItemMeta(metai2);
            GuiItem barrier = new GuiItem(barr);
            gui.updateItem(i, barrier);

        }

    }

    public static List<Integer> findPreviousBids(int num, Player player) {
        ItemStack playerhead = getPlayerHead(player.getName(), num).getItemStack();
        List<Integer> barrierslots = new ArrayList<>();
        for (int i : biddingItemsSlot()) {
            Material matt = gui.getGuiItem(i).getItemStack().getType();
            if (matt == Material.GOLD_NUGGET || matt == Material.DIAMOND || matt == Material.EMERALD || matt == Material.GOLD_INGOT) {
                int rightamount = findRightAmount(i);
                if (rightamount < num) {

                    barrierslots.add(i);

                }
            }
        }

        return barrierslots;
    }

    public static int findRightAmount(int i) {
        //10-11-12-14-15-16-19-21-23-25-28-30-32-34-36-37-39-40-41-43-44
        int phasenumber = Utils.guiphase.get("phase");
        switch (i) {
            case 10:
                return 5+ ((phasenumber - 1 ) * 21);
            case 11:
                return 6+ ((phasenumber - 1 ) * 21);
            case 12:
                return 7+ ((phasenumber - 1 ) * 21);
            case 14:
                return 15+ ((phasenumber - 1 ) * 21);
            case 15:
                return 16+ ((phasenumber - 1 ) * 21);
            case 16:
                return 17+ ((phasenumber - 1 ) * 21);
            case 19:
                return 4+ ((phasenumber - 1 ) * 21);
            case 21:
                return 8+ ((phasenumber - 1 ) * 21);
            case 23:
                return 14+ ((phasenumber - 1 ) * 21);
            case 25:
                return 18+ ((phasenumber - 1 ) * 21);
            case 28:
                return 3+ ((phasenumber - 1 ) * 21);
            case 30:
                return 9+ ((phasenumber - 1 ) * 21);
            case 32:
                return 13+ ((phasenumber - 1 ) * 21);
            case 34:
                return 19+ ((phasenumber - 1 ) * 21);
            case 36:
                return 1 + ((phasenumber - 1 ) * 21);
            case 37:
                return 2+ ((phasenumber - 1 ) * 21);
            case 39:
                return 10+ ((phasenumber - 1 ) * 21);
            case 40:
                return 11+ ((phasenumber - 1 ) * 21);
            case 41:
                return 12+ ((phasenumber - 1 ) * 21);
            case 43:
                return 20+ ((phasenumber - 1 ) * 21);
            case 44:
                return 21+ ((phasenumber - 1 ) * 21);
            default:
                return 100;
        }
    }


    //fix this error later
    public static int findRightSlot(int i) {

        //22 --> 1

        //10-11-12-14-15-16-19-21-23-25-28-30-32-34-36-37-39-40-41-43-44
        int phasenumber = Utils.guiphase.get("phase");
        int num = i - ((phasenumber - 1) * 21);
        switch (num) {
            case 5:
                return 10;
            case 6:
                return 11;
            case 7:
                return 12;
            case 15:
                return 14;
            case 16:
                return 15;
            case 17:
                return 16;
            case 4:
                return 19;
            case 8:
                return 21;
            case 14:
                return 23;
            case 18:
                return 25;
            case 3:
                return 28;
            case 9:
                return 30;
            case 13:
                return 32;
            case 19:
                return 34;
            case 1:
                return 36;
            case 2:
                return 37;
            case 10:
                return 39;
            case 11:
                return 40;
            case 12:
                return 41;
            case 20:
                return 43;
            case 21:
                return 44;
            default:
                return 100;
        }
    }


//10-11-12-14-15-16-19-21-23-25-28-30-32-34-36-37-39-40-41-43-44
    public static List<Integer> biddingItemsSlot() {
        List<Integer> ints = new ArrayList<>();
        ints.add(10);
        ints.add(11);
        ints.add(12);
        ints.add(14);
        ints.add(15);
        ints.add(16);
        ints.add(19);
        ints.add(21);
        ints.add(23);
        ints.add(25);
        ints.add(28);
        ints.add(30);
        ints.add(32);
        ints.add(34);
        ints.add(36);
        ints.add(37);
        ints.add(39);
        ints.add(40);
        ints.add(41);
        ints.add(43);
        ints.add(44);

        return ints;
    }

    public static GuiItem getPlayerHead(String name, int amountslot) {
        int amount = findRightPrice(findRightSlot(amountslot));
        ItemStack head = new ItemStack(Material.SKULL_ITEM, amountslot);
        head.setDurability((short) 3);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        meta.setOwner(name);
        meta.setDisplayName(Utils.color("&d&l" + name + " &dBidded&e " + Integer.toString(amount) + " Coins ◈"));
        head.setItemMeta(meta);
        GuiItem skull = new GuiItem(head);
        return skull;
    }

    public static boolean isNextPageBid(int slotrealamount) {

        if (slotrealamount%21 == 0) {

            return true;

        } else {
            return false;
        }
    }


    public static int findRightPrice(int slot) {

        int initialamount = 0;
        int amount = 0;
        while (initialamount != findRightAmount(slot)) {
            amount = amount + 150;
            initialamount++;
        }
        return amount;
    }

}
