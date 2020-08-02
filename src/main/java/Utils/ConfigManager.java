package Utils;
import me.deadlight.darkauction.DarkAuction;
import me.deadlight.darkauction.tasks.StartingDA;
import java.io.File;


public class ConfigManager {

    public static void reloadConfig() {
        try {
            Utils.config.save(DarkAuction.getInstance().getDataFolder() + File.separator + "config.yml");
            DarkAuction.getInstance().reloadConfig();
            Utils.config = DarkAuction.getInstance().getConfig();
            StartingDA.requiredmoney = Utils.config.getInt("money-required-to-enter");
        } catch (Exception e) {
            //nothing
        }



    }

    public static void reloadCommand() {
        DarkAuction.getInstance().reloadConfig();
        Utils.config = DarkAuction.getInstance().getConfig();
        StartingDA.requiredmoney = Utils.config.getInt("money-required-to-enter");
    }

}
