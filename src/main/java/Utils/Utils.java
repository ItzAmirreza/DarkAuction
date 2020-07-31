package Utils;

import me.deadlight.darkauction.DarkAuction;
import me.mattstudios.mfgui.gui.guis.Gui;
import org.bukkit.*;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

public class Utils {

    public static String color(String msg) {

        return ChatColor.translateAlternateColorCodes('&', msg);

    }

    public static String prefix = "&7[&8&lDark&c&lAuction&7] ";


    public static String convertLocToString(int x, int y, int z, String world, float pitch, float yaw) {

        return Integer.toString(x) + ":" + Integer.toString(y) + ":" + Integer.toString(z) + ":" + world + ":" + Float.toString(pitch) + ":" + Float.toString(yaw);
    }


    public static Location convertStringToLoc(String stringloc) {

        //4 --> pitch
        //5 --> yaw
        List<String> splited = Arrays.asList(stringloc.split(":"));

        return new Location(Bukkit.getWorld(splited.get(3)), Integer.parseInt(splited.get(0)) + 0.5, Integer.parseInt(splited.get(1)), Integer.parseInt(splited.get(2)) + 0.5, Float.parseFloat(splited.get(5)), Float.parseFloat(splited.get(4)));
    }

    public static List<ItemStack> auctionitems = new ArrayList<>();

    public static ItemStack itemRightNow;

    public static HashMap<String, Integer> guiphase = new HashMap<>();

    public static HashMap<String, Boolean> timerstatus = new HashMap<>();

    public static HashMap<String, Integer> auctionlevel = new HashMap<>();

    public static HashMap<String, Location> inauction = new HashMap<>();


    public static boolean isNewVersion() {

        return Arrays.stream(Material.values())
                .map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD");
    }


    public static Sound stal = Sound.valueOf(isNewVersion() ? "MUSIC_DISC_STAL" : "RECORD_STAL");


}
