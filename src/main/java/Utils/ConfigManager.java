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
            Messages.bidpage = Utils.config.getString("messages.bidpage");
            Messages.higherthanthisamount = Utils.config.getString("messages.higherthanthisamount");
            Messages.playerhasbiddedx = Utils.config.getString("messages.playerhasbiddedx");
            Messages.notEnoughMoney = Utils.config.getString("messages.notenoughmoney");
            Messages.enteredAh = Utils.config.getString("messages.enteredah");
            Messages.needmoneytojoin = Utils.config.getString("messages.needmoneytojoin");
            Messages.notEnoughMembers = Utils.config.getString("messages.notenoughmembers");
            Messages.topbid = Utils.config.getString("messages.topbid");
            Messages.nobid = Utils.config.getString("messages.nobid");
            Messages.playerhaswon = Utils.config.getString("messages.playerhaswon");
            Messages.nsecondstobid = Utils.config.getString("messages.nsecondstobid");
            Messages.guiNSecondstobid = Utils.config.getString("messages.guitimeremaining");
            Messages.ahfinished = Utils.config.getString("messages.ahfinished");
            Messages.topbidholo = Utils.config.getString("messages.topbidholo");
            Messages.alreadyhighest = Utils.config.getString("messages.alreadyhighest");
        } catch (Exception e) {
            //nothing
        }



    }

    public static void reloadCommand() {
        DarkAuction.getInstance().reloadConfig();
        Utils.config = DarkAuction.getInstance().getConfig();
        StartingDA.requiredmoney = Utils.config.getInt("money-required-to-enter");
        Messages.bidpage = Utils.config.getString("messages.bidpage");
        Messages.higherthanthisamount = Utils.config.getString("messages.higherthanthisamount");
        Messages.playerhasbiddedx = Utils.config.getString("messages.playerhasbiddedx");
        Messages.notEnoughMoney = Utils.config.getString("messages.notenoughmoney");
        Messages.enteredAh = Utils.config.getString("messages.enteredah");
        Messages.needmoneytojoin = Utils.config.getString("messages.needmoneytojoin");
        Messages.notEnoughMembers = Utils.config.getString("messages.notenoughmembers");
        Messages.topbid = Utils.config.getString("messages.topbid");
        Messages.nobid = Utils.config.getString("messages.nobid");
        Messages.playerhaswon = Utils.config.getString("messages.playerhaswon");
        Messages.nsecondstobid = Utils.config.getString("messages.nsecondstobid");
        Messages.guiNSecondstobid = Utils.config.getString("messages.guitimeremaining");
        Messages.ahfinished = Utils.config.getString("messages.ahfinished");
        Messages.topbidholo = Utils.config.getString("messages.topbidholo");
        Messages.alreadyhighest = Utils.config.getString("messages.alreadyhighest");
    }

}
