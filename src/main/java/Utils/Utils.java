package Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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

    public static HashMap<String, Boolean> timerstatus = new HashMap<>();

}
