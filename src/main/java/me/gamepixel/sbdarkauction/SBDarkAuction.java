package me.gamepixel.sbdarkauction;
import Utils.Utils;
import me.gamepixel.sbdarkauction.commands.DarkAuctionCommand;
import me.gamepixel.sbdarkauction.tasks.StartingDA;
import org.bukkit.plugin.java.JavaPlugin;

public final class SBDarkAuction extends JavaPlugin {

    public static SBDarkAuction instance;
    public static SBDarkAuction getInstance() {

        return instance;
    }


    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        getServer().getPluginCommand("da").setExecutor(new DarkAuctionCommand());

        if (getServer().getPluginManager().getPlugin("Citizens") != null) {
            getServer().getConsoleSender().sendMessage(Utils.color(Utils.prefix + "&aPlugin Has Been Enabled."));
        } else {
            getServer().getConsoleSender().sendMessage(Utils.color(Utils.prefix + "&cThis plugin requires Citizens Plugin."));
            getServer().getPluginManager().disablePlugin(this);
        }

    }

    @Override
    public void onDisable() {


        getServer().getConsoleSender().sendMessage(Utils.color(Utils.prefix + "&cPlugin Has Been Disabled."));
    }
}
