package Utils;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.deadlight.darkauction.DarkAuction;
import me.deadlight.darkauction.guilist.FirstGui;
import org.bukkit.entity.Player;

public class PlaceHolders extends PlaceholderExpansion {
    private DarkAuction plugin;

    /**
     * Since we register the expansion inside our own plugin, we
     * can simply use this method here to get an instance of our
     * plugin.
     *
     * @param plugin
     *        The instance of our plugin.
     */
    public PlaceHolders(DarkAuction plugin){
        this.plugin = plugin;
    }

    /**
     * Because this is an internal class,
     * you must override this method to let PlaceholderAPI know to not unregister your expansion class when
     * PlaceholderAPI is reloaded
     *
     * @return true to persist through reloads
     */
    @Override
    public boolean persist(){
        return true;
    }

    /**
     * Because this is a internal class, this check is not needed
     * and we can simply return {@code true}
     *
     * @return Always true since it's an internal class.
     */
    @Override
    public boolean canRegister(){
        return true;
    }

    /**
     * The name of the person who created this expansion should go here.
     * <br>For convienience do we return the author from the plugin.yml
     *
     * @return The name of the author as a String.
     */
    @Override
    public String getAuthor(){
        return "Dead_Light";
    }

    /**
     * The placeholder identifier should go here.
     * <br>This is what tells PlaceholderAPI to call our onRequest
     * method to obtain a value if a placeholder starts with our
     * identifier.
     * <br>The identifier has to be lowercase and can't contain _ or %
     *
     * @return The identifier in {@code %<identifier>_<value>%} as String.
     */
    @Override
    public String getIdentifier(){
        return "darkauction";
    }

    /**
     * This is the version of the expansion.
     * <br>You don't have to use numbers, since it is set as a String.
     *
     * For convenience do we return the version from the plugin.yml
     *
     * @return The version as a String.
     */
    @Override
    public String getVersion(){
        return plugin.getDescription().getVersion();
    }

    /**
     * This is the method called when a placeholder with our identifier
     * is found and needs a value.
     * <br>We specify the value identifier in this method.
     * <br>Since version 2.9.1 can you use OfflinePlayers in your requests.
     *
     */

    @Override
    public String onPlaceholderRequest(Player player, String identifier){


        //the name of item which is under auction right now
        if(identifier.equalsIgnoreCase("itemrightnow")){
            if (Utils.itemRightNow == null) {

                return "-";

            } else {
                if (Utils.itemRightNow.getItemStack().getItemMeta().hasDisplayName()) {
                    return Utils.itemRightNow.getItemStack().getItemMeta().getDisplayName();
                } else if (!Utils.itemRightNow.getItemStack().getItemMeta().hasDisplayName()) {

                    return Utils.itemRightNow.getItemStack().getType().toString();
                } else {
                    return "-";
                }
            }
        }


        if (identifier.equalsIgnoreCase("highestbiddedplayer")){

            if (FirstGui.topbidplayer.get("top") == null) {
                return "-";
            } else {
                return FirstGui.topbidplayer.get("top");
            }

        }

        if (identifier.equalsIgnoreCase("highestbiddedamount")) {

            if (FirstGui.topbid.containsKey(FirstGui.topbidplayer.get("top"))) {

                return Integer.toString(FirstGui.topbid.get(FirstGui.topbidplayer.get("top")));

            } else {
                return "-";
            }
        }

        if (identifier.equalsIgnoreCase("highestbiddedamountforplayer")) {

            if (FirstGui.topbid.containsKey(player.getName())) {

                return Integer.toString(FirstGui.topbid.get(player.getName()));

            } else {
                return "-";
            }

        }

        if (identifier.equalsIgnoreCase("status")) {

            if (Utils.ahstatus) {
                return "Yes";
            } else if (!Utils.ahstatus) {
                return "No";
            } else {
                return "-";
            }
        }


        if (identifier.equalsIgnoreCase("auctionlevel")) {

            if (!Utils.auctionlevel.isEmpty()) {

                return Integer.toString(Utils.auctionlevel.get("level"));

            } else {
                return "-";
            }

        }


        return null;
    }
}
