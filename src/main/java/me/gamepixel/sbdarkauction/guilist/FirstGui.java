package me.gamepixel.sbdarkauction.guilist;

import Utils.Utils;
import me.gamepixel.sbdarkauction.SBDarkAuction;
import me.gamepixel.sbdarkauction.tasks.StartingDA;
import me.mattstudios.mfgui.gui.guis.Gui;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FirstGui {

    public static Gui gui = new Gui(SBDarkAuction.getInstance(), 6, Utils.color("&6Bid Page"));
    public static GuiItem graystainedglass = new GuiItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7));
    //red border
    public static GuiItem redstainedglass = new GuiItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14));
    //gold nuggets
    public static HashMap<String, Integer> topbid = new HashMap<>();

    public static HashMap<String, String> topbidplayer = new HashMap<>();


    public static void makeguipage() {

        GuiItem sellingitem = new GuiItem(Utils.itemRightNow);
        //setting the main item which is going to be sold
        gui.setItem(22, sellingitem);
        //borders
        gui.getFiller().fillBorder(redstainedglass);
        for (int i : biddingItemsSlot()) {
            ItemStack goldnug = new ItemStack(Material.GOLD_NUGGET, findRightAmount(i));
            int initialamount = 0;
            int amount = 0;
            while (initialamount != findRightAmount(i)) {
                amount = amount + 150;
                initialamount++;
            }
            ItemMeta metai = goldnug.getItemMeta();
            metai.setDisplayName(Utils.color("&e" + Integer.toString(amount) + " &eCoins ◈"));
            goldnug.setItemMeta(metai);
            ItemStack barr = new ItemStack(Material.BARRIER, findRightAmount(i));
            ItemMeta metai2 = barr.getItemMeta();
            metai2.setDisplayName(Utils.color("&c" + Integer.toString(amount) + " &cCoins ◈"));
            barr.setItemMeta(metai2);
            GuiItem goldnuggets = new GuiItem(goldnug);
            GuiItem barrier = new GuiItem(barr);
            gui.setItem(i, goldnuggets);
            gui.addSlotAction(i, event -> {
                // Handle your open action
                if (event.getCurrentItem().getType() == Material.BARRIER) {

                    event.setCancelled(true);
                    event.getWhoClicked().sendMessage(Utils.color("&cThere is a bid higher than this amount!"));

                } else if (event.getCurrentItem().getType() == Material.GOLD_NUGGET) {
                    event.setCancelled(true);
                    topbid.put(event.getWhoClicked().getName(), Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).replace("Coins", "").replace("◈", "").replace(" ", "")));
                    topbidplayer.put("top", event.getWhoClicked().getName());
                    gui.updateItem(i, getPlayerHead(event.getWhoClicked().getName(), findRightAmount(i)));
                    StartingDA.countnum = 10;
                    ChangeToBarrierSlot(findPreviousBids(findRightAmount(i)));
                }

            });
        }
        gui.setDefaultClickAction(event -> {
            // Handle your default action here
            event.setCancelled(true);
        });
        
    }

    public static void ChangeToBarrierSlot(List<Integer> list) {

        for (int i : list) {
            int initialamount = 0;
            int amount = 0;
            while (initialamount != findRightAmount(i)) {
                amount = amount + 150;
                initialamount++;
            }
            ItemStack barr = new ItemStack(Material.BARRIER, findRightAmount(i));
            ItemMeta metai2 = barr.getItemMeta();
            metai2.setDisplayName(Utils.color("&c" + Integer.toString(amount) + " &cCoins ◈"));
            barr.setItemMeta(metai2);
            GuiItem barrier = new GuiItem(barr);
            gui.updateItem(i, barrier);

        }

    }

    public static List<Integer> findPreviousBids(int num) {

        List<Integer> barrierslots = new ArrayList<>();
        for (int i : biddingItemsSlot()) {

            int rightamount = findRightAmount(i);
            if (rightamount < num) {

                barrierslots.add(i);

            }


        }

        return barrierslots;
    }

    public static int findRightAmount(int i) {
        //10-11-12-14-15-16-19-21-23-25-28-30-32-34-36-37-39-40-41-43-44
        switch (i) {
            case 10:
                return 5;
            case 11:
                return 6;
            case 12:
                return 7;
            case 14:
                return 15;
            case 15:
                return 16;
            case 16:
                return 17;
            case 19:
                return 4;
            case 21:
                return 8;
            case 23:
                return 14;
            case 25:
                return 18;
            case 28:
                return 3;
            case 30:
                return 9;
            case 32:
                return 13;
            case 34:
                return 19;
            case 36:
                return 1;
            case 37:
                return 2;
            case 39:
                return 10;
            case 40:
                return 11;
            case 41:
                return 12;
            case 43:
                return 20;
            case 44:
                return 21;
            default:
                return 100;
        }
    }

    public int findRightSlot(int i) {
        //10-11-12-14-15-16-19-21-23-25-28-30-32-34-36-37-39-40-41-43-44
        switch (i) {
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


    public List<Integer> zojnumbers() {
        int num = 0;
        List<Integer> outgoinglist = new ArrayList<>();
        while (num < 54) {
            if (num%2 == 0 && num != 36 && num != 44) {
                outgoinglist.add(num);                
            }
            num++;
        }
        
        return outgoinglist;
    }

    public List<Integer> fardnumbers() {
        int num = 0;
        List<Integer> outgoinglist = new ArrayList<>();
        while (num < 54) {
            if (num%2 != 0) {
                outgoinglist.add(num);
            }
            num++;
        }

        return outgoinglist;
    }
    
    public void fillTheBorder() {
        
        for (int i : zojnumbers()) {

            gui.setItem(i, graystainedglass);

            
        }
        for (int i : fardnumbers()) {

            gui.setItem(i, redstainedglass);

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

    public static GuiItem getPlayerHead(String name, int amount) {

        ItemStack head = new ItemStack(Material.SKULL_ITEM, amount);
        head.setDurability((short) 3);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        meta.setOwner(name);
        head.setItemMeta(meta);

        GuiItem skull = new GuiItem(head);
        return skull;
    }


    ///////When player clicks on any one the biding slots...


}
