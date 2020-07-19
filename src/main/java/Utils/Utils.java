package Utils;

import me.gamepixel.sbdarkauction.SBDarkAuction;
import me.mattstudios.mfgui.gui.guis.Gui;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Utils {

    public static String color(String msg) {

        return ChatColor.translateAlternateColorCodes('&', msg);

    }

    public static String prefix = "&7[&8&lDark&c&lAuction&7] ";


    public static String convertLocToString(int x, int y, int z, String world) {

        return Integer.toString(x) + ":" + Integer.toString(y) + ":" + Integer.toString(z) + ":" + world;
    }


    public static Location convertStringToLoc(String stringloc) {

        List<String> splited = Arrays.asList(stringloc.split(":"));

        return new Location(Bukkit.getWorld(splited.get(3)), Integer.parseInt(splited.get(0)) + 0.5, Integer.parseInt(splited.get(1)), Integer.parseInt(splited.get(2)) + 0.5);
    }

    public static List<ItemStack> auctionitems = new ArrayList<>();

    public static ArmorStand antimerstand;

    public static ArmorStand anarmorstand;

    public static Gui gui = new Gui(SBDarkAuction.getInstance(), 6, "&6Bid Page");

    public static ItemStack itemRightNow;

    public static HashMap<String, Boolean> timerstatus = new HashMap<>();

    public static HashMap<String, Integer> auctionlevel = new HashMap<>();

    public static HashMap<String, Location> inauction = new HashMap<>();

    public static HashMap<String, Integer> topbid = new HashMap<>();

    public static HashMap<String, String> topbidplayer = new HashMap<>();

    public static me.muffinplayz.sbcore.Main sbCore = (me.muffinplayz.sbcore.Main) Bukkit.getServer().getPluginManager().getPlugin("SBCore");

}
