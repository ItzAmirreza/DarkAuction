package me.gamepixel.sbdarkauction.guilist;

import Utils.Utils;
import me.gamepixel.sbdarkauction.SBDarkAuction;
import me.mattstudios.mfgui.gui.guis.Gui;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class FirstGui {

    public Gui gui = new Gui(SBDarkAuction.getInstance(), 6, "&6Bid Page");

    public GuiItem graystainedglass = new GuiItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7));
    //red border
    public GuiItem redstainedglass = new GuiItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14));
    //gold nuggets
    public GuiItem goldnuggets = new GuiItem(new ItemStack(Material.GOLD_NUGGET, 1));

    public GuiItem barrier = new GuiItem(new ItemStack(Material.BARRIER, 1));


    public void makeguipage() {

        int firstAmount = 0;
        for (int i : biddingItemsSlot()) {
            firstAmount = firstAmount + 150;
            goldnuggets.getItemStack().getItemMeta().setDisplayName(Utils.color("&a&l" + Integer.toString(firstAmount) + "&e&lCoins ◈"));
            gui.setItem(i, goldnuggets);
        }


        GuiItem sellingitem = new GuiItem(Utils.itemRightNow);
        fillTheBorder();
        //setting the main item which is going to be sold
        gui.setItem(22, sellingitem);
        //
        
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
    public List<Integer> biddingItemsSlot() {
        List<Integer> ints = new ArrayList<>();
        ints.add(10);
        ints.add(11);
        ints.add(12;
        ints.add(14);
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

        return biddingItemsSlot();
    }


}
