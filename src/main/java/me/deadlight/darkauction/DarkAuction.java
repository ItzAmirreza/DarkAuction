package me.deadlight.darkauction;
import Utils.Utils;
import me.deadlight.darkauction.commands.DarkAuctionCommand;
import me.deadlight.darkauction.events.PlayerInteractWithEntity;
import me.deadlight.darkauction.tasks.StartingDA;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class DarkAuction extends JavaPlugin {

    public static DarkAuction instance;
    public static DarkAuction getInstance() {

        return instance;
    }

    public Economy eco;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        getServer().getPluginCommand("da").setExecutor(new DarkAuctionCommand());
        getServer().getPluginManager().registerEvents(new PlayerInteractWithEntity(), this);

        if (getServer().getPluginManager().getPlugin("Citizens") != null) {
            if (setupEconomy()) {
                getServer().getConsoleSender().sendMessage(Utils.color(Utils.prefix + "&aPlugin Has Been Enabled."));
            } else {

                getServer().getConsoleSender().sendMessage(Utils.color(Utils.prefix + "&cThis plugin requires Vault Plugin."));
                getServer().getPluginManager().disablePlugin(this);
            }
        } else {
            getServer().getConsoleSender().sendMessage(Utils.color(Utils.prefix + "&cThis plugin requires Citizens Plugin."));
            getServer().getPluginManager().disablePlugin(this);
        }

    }

    @Override
    public void onDisable() {


        getServer().getConsoleSender().sendMessage(Utils.color(Utils.prefix + "&cPlugin Has Been Disabled."));
        StartingDA.EntranceNpc.despawn();
    }



    private boolean setupEconomy() {

        RegisteredServiceProvider<Economy> economy = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);

        if (economy != null) {

            eco = economy.getProvider();


        }
        return (eco != null);
    }
}
