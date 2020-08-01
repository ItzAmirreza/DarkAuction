package me.deadlight.darkauction.tasks;

import Utils.Utils;
import me.deadlight.darkauction.DarkAuction;
import me.deadlight.darkauction.guilist.FirstGui;
import me.mattstudios.mfgui.gui.components.xseries.XMaterial;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.util.Vector;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StartingDA {

    public static int requiredmoney = Utils.config.getInt("money-required-to-enter");
    public static List<ItemStack> listofauctioneditems = new ArrayList<>();
    public Random random = new Random();
    public static ArmorStand anarmorstand;
    public static ArmorStand antimerarmor;
    public static ArmorStand topbidarmor;
    public static List<Player> playersinauction = new ArrayList<>();
    private Item anitem;
    public static int countnum = 10;
    private final int countdown = 0;
    public static NPC EntranceNpc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "");
    public void entrancenpcspawn() {
        EntranceNpc.getTrait(SkinTrait.class).setSkinPersistent("salam", "LnO4hRXRK+aumyL2/Ck/ZVTgifEr5J6GxnRKNsXDIVDhlfcvPXsMtNeoiQUNCGWeC5I/wjMOesiO87Y2LaRaMVbcrSA2XMzvYQ4JyNN4aJqCfuUtIuYqvOFFp5mK4xUSUmOP0OP1WldzCrp/bYae/hSUaE1cfJZY/kiOorYwfb4kE1sObq6hUmqJfPXbVxEiMZosRZlQl/qhkTXiBasfDfNI/COuPz+OhZWEMr9MFWa/08Hb9slzjk7jVUFdIejJreHOMg24KBWYYermiiPRhF3DbRmKXonN1wokPwIQIbtzl5aQ3sIhmuJd+9He9joRcS+uP8DFoLngiqLJojgmzRAE4qLuKFjj4PIh64W4AnzP+kWtwsI0oUYHmqo7hCJ0zIHVZ4IZ/Itsomq+V3oOdO5gdJZB/fNmVWbNYp6SfNKyDnzaYtjVjoD/ijpWAaz43S0oF9ew42IBg9ytL1B0mnYfCjsec6BdW26rGVmaSZn5ERTKK3pT38QEv9Jh/uVFPoFtJ0YPWq6WqJhplhhSskpq82E7BExqOTf1NTLGWa3xz5bPZ5wadJBVYu+esdxBSO6vc0EBHlJoIqhD2VHj4BBGmXmcWt+bZFUNxXp4crkuDSGijGDQ2wSP+Rl94O54o2EHBFPoZ8EPnNuWJqQgkOeEfa/XHVMuj7ZGogAP2b8=", "ewogICJ0aW1lc3RhbXAiIDogMTU5NDI4MTIzNzM5MiwKICAicHJvZmlsZUlkIiA6ICJlZDUzZGQ4MTRmOWQ0YTNjYjRlYjY1MWRjYmE3N2U2NiIsCiAgInByb2ZpbGVOYW1lIiA6ICIwMTAwMDExMDAxMDAwMDExIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2NhMDE0NTMxYzY0ZGQ3MDMyNWJjN2FiNmMwNjI3YTY5MDAyMzE0NDY4MWQyYTQ5MDUwZDc0ZDlmMWIxNmVjMTgiCiAgICB9CiAgfQp9");
        Location location = Utils.convertStringToLoc(Utils.config.getString("entrance-npc-coordinates"));
        EntranceNpc.setProtected(true);
        EntranceNpc.spawn(location);
        teleportToDarkauction(getNearPlayers(EntranceNpc.getEntity()));
    }


    public List getNearPlayers(Entity entity) {
        List<Entity> nearbyentities = entity.getNearbyEntities(5, 5, 5);
        List<Player> onlyrealplayerslist = new ArrayList<>();

        for (Entity en : nearbyentities) {

            if (!en.hasMetadata("NPC") && en instanceof Player) {
                Player player = (Player) en;
                onlyrealplayerslist.add(player);

            }


        }

        return onlyrealplayerslist;
    }

    public boolean checkIfPlayerHasMoney(Player player) {


        if (DarkAuction.getInstance().eco.getBalance(Bukkit.getOfflinePlayer(player.getUniqueId())) >= requiredmoney) {

            return true;
        } else {
            return false;
        }
    }



    public void teleportToDarkauction(List<Player> players) {

        Location location = Utils.convertStringToLoc(Utils.config.getString("teleport-at-start"));
        //badan bayad add beshe hade aghal ye meghar pool dashte bashe <--- toye config.yml ham hast darbarash !
        Bukkit.getScheduler().scheduleSyncDelayedTask(DarkAuction.getInstance(), new Runnable() {
            @Override
            public void run() {
                for (Player player : players) {
                    if (checkIfPlayerHasMoney(player)) {
                        playersinauction.add(player);
                        Utils.inauction.put(player.getName(), player.getLocation());
                        player.teleport(location);
                        Utils.biddedamount.put(player, 0);
                        player.sendMessage(Utils.color(Utils.prefix + "&7 You have entered the Secret Auction House!"));
                    } else {

                        player.sendMessage(Utils.color(Utils.prefix + "&dEhem, but you don't have enough money to join the party. Come back when you have!"));
                        player.playSound(player.getLocation() , Sound.ENTITY_VILLAGER_NO, 1.0F, 1.0F);
                    }

                }

            }
        }, 20 * 5);
        //splashing invisibility
        Bukkit.getScheduler().scheduleSyncDelayedTask(DarkAuction.getInstance(), new Runnable() {
            @Override
            public void run() {
                Location location = Utils.convertStringToLoc(Utils.config.getString("itemshowcase"));
                Potion potion = new Potion(PotionType.INVISIBILITY, 2);
                potion.setSplash(true);


                ItemStack potionstack = new ItemStack(Material.SPLASH_POTION);
                playersinauction.forEach(player -> {
                    ThrownPotion thrownPotion = player.launchProjectile(ThrownPotion.class);
                    thrownPotion.setItem(potionstack);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 5000, 2));
                });
            }
        }, 20 * 10);

        Bukkit.getScheduler().scheduleSyncDelayedTask(DarkAuction.getInstance(), new Runnable() {
            @Override
            public void run() {
                Location location = Utils.convertStringToLoc(Utils.config.getString("itemshowcase"));
                for (Player player : playersinauction) {

                    player.playSound(location, Utils.stal, 1.0F, 1.0F);

                }
                startFirstItem(Utils.auctionitems);

            }
        }, 20 * 15);


    }


    //first item auction
    public void startFirstItem(List<ItemStack> items) {

        if (items.size() == 0) {

            Bukkit.broadcastMessage(Utils.color(Utils.prefix + "&cNo items for today's auction!"));
            closeAuction();
        } else {



            ItemStack itemst = items.get(random.nextInt(items.size()));
            listofauctioneditems.add(itemst);
            Utils.itemRightNow = itemst;
            Location location = Utils.convertStringToLoc(Utils.config.getString("itemshowcase"));
            Item item = location.getWorld().dropItem(location, itemst);
            item.setVelocity(new Vector());
            item.setPickupDelay(32767);
            item.setInvulnerable(true);
            item.setGravity(false);
            Utils.auctionlevel.put("level", 1);
            spawnArmorStand(location, item);



        }

    }

    //second item auction
    public void startSecondItem() {
        List<ItemStack> items = Utils.auctionitems;
        boolean again = true;
        ItemStack itemstt = items.get(random.nextInt(items.size()));
        while (again) {
            ItemStack itemst = items.get(random.nextInt(items.size()));
            if (!listofauctioneditems.contains(itemst)) {

                itemstt = itemst;
                again = false;
            }

        }

        Utils.itemRightNow = itemstt;
        Location location = Utils.convertStringToLoc(Utils.config.getString("itemshowcase"));

        Item item = location.getWorld().dropItem(location, itemstt);
        item.setVelocity(new Vector());
        item.setPickupDelay(32767);
        item.setInvulnerable(true);
        item.setGravity(false);
        Utils.auctionlevel.replace("level", 2);
        spawnArmorStand(location, item);


    }

    public void startThirdItem() {

        List<ItemStack> items = Utils.auctionitems;
        boolean again = true;
        ItemStack itemstt = items.get(random.nextInt(items.size()));
        while (again) {
            ItemStack itemst = items.get(random.nextInt(items.size()));
            if (!listofauctioneditems.contains(itemst)) {

                itemstt = itemst;
                again = false;
            }

        }

        Utils.itemRightNow = itemstt;
        Location location = Utils.convertStringToLoc(Utils.config.getString("itemshowcase"));

        Item item = location.getWorld().dropItem(location, itemstt);
        item.setVelocity(new Vector());
        item.setPickupDelay(32767);
        item.setInvulnerable(true);
        item.setGravity(false);
        Utils.auctionlevel.replace("level", 3);
        spawnArmorStand(location, item);
    }

    public void spawnArmorStand(Location location, Item item) {
        FirstGui.makeguipage();
        Bukkit.getScheduler().scheduleSyncDelayedTask(DarkAuction.getInstance(), new Runnable() {
            @Override
            public void run() {

                anarmorstand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                anarmorstand.setVisible(false);
                anarmorstand.setArms(false);
                anarmorstand.setGravity(false);
                if (!item.getItemStack().getItemMeta().hasDisplayName()) {

                    String itemlore = item.getItemStack().getType().name();
                    anarmorstand.setCustomName(Utils.color("&a&l" + itemlore));
                    anarmorstand.setCustomNameVisible(true);
                    startCountDown(item);

                } else {

                    String itemlore = item.getItemStack().getItemMeta().getDisplayName();
                    anarmorstand.setCustomName(Utils.color(itemlore));
                    anarmorstand.setCustomNameVisible(true);
                    startCountDown(item);
                }


            }
        }, 20 * 3);

    }


    public void startCountDown(Item item) {

        anitem = item;
        if (Utils.timerstatus.containsKey("status")) {

            Location newloc = new Location(anarmorstand.getWorld(), anarmorstand.getLocation().getX(), anarmorstand.getLocation().getY() - 1, anarmorstand.getLocation().getZ());

            antimerarmor = (ArmorStand) newloc.getWorld().spawnEntity(newloc, EntityType.ARMOR_STAND);
            antimerarmor.setVisible(false);
            antimerarmor.setArms(false);
            antimerarmor.setGravity(false);
            Utils.timerstatus.replace("status", true);

            Location bidarmorloc = new Location(anarmorstand.getWorld(), anarmorstand.getLocation().getX(), anarmorstand.getLocation().getY() - 0.5, anarmorstand.getLocation().getZ());
            topbidarmor = (ArmorStand) bidarmorloc.getWorld().spawnEntity(bidarmorloc, EntityType.ARMOR_STAND);
            topbidarmor.setVisible(false);
            topbidarmor.setArms(false);
            topbidarmor.setGravity(false);
            topbidarmor.setCustomName(Utils.color("&cTop Bid: &b" + "0 &eCoins ◈"));

        } else {

            Location newloc = new Location(anarmorstand.getWorld(), anarmorstand.getLocation().getX(), anarmorstand.getLocation().getY() - 1, anarmorstand.getLocation().getZ());

            antimerarmor = (ArmorStand) newloc.getWorld().spawnEntity(newloc, EntityType.ARMOR_STAND);
            antimerarmor.setVisible(false);
            antimerarmor.setArms(false);
            antimerarmor.setGravity(false);
            Utils.timerstatus.put("status", true);
            Location bidarmorloc = new Location(anarmorstand.getWorld(), anarmorstand.getLocation().getX(), anarmorstand.getLocation().getY() - 0.5, anarmorstand.getLocation().getZ());
            topbidarmor = (ArmorStand) bidarmorloc.getWorld().spawnEntity(bidarmorloc, EntityType.ARMOR_STAND);
            topbidarmor.setVisible(false);
            topbidarmor.setArms(false);
            topbidarmor.setGravity(false);
            topbidarmor.setCustomName(Utils.color("&cTop Bid: &b" + "0 &eCoins ◈"));

            startTimerTask();

        }



    }

    public void startTimerTask() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(DarkAuction.getInstance(), new Runnable() {
            @Override
            public void run() {

                if (countnum <= countdown && Utils.timerstatus.get("status")) {
                    Location location = Utils.convertStringToLoc(Utils.config.getString("itemshowcase"));
                    location.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, location, 1);
                    location.getWorld().playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);
                    anarmorstand.remove();
                    antimerarmor.remove();
                    topbidarmor.remove();
                    anitem.remove();
                    countnum = 10;

                    Utils.timerstatus.replace("status", false);
                    if (FirstGui.topbidplayer.isEmpty()) {
                        playersinauction.forEach(player -> {
                            FirstGui.gui.close(player);
                            player.sendMessage(Utils.color("&7No one has bidded for this item... Poor item..."));
                        });
                        executeNextLevel();
                    } else {
                        Player player = Bukkit.getPlayer(FirstGui.topbidplayer.get("top"));
                        player.getInventory().addItem(anitem.getItemStack());
                        Utils.guiphase.put("phase", 1);
                        List<Player> newplayerlist = new ArrayList<>(playersinauction);
                        newplayerlist.remove(Bukkit.getPlayer(FirstGui.topbidplayer.get("top")));
                        for (Player player1 : newplayerlist) {

                            DarkAuction.getInstance().eco.depositPlayer(Bukkit.getOfflinePlayer(player1.getUniqueId()), Utils.biddedamount.get(player1));

                        }
                        playersinauction.forEach(player2 -> {
                            Utils.biddedamount.put(player2, 0);
                            FirstGui.gui.close(player2);

                            player2.sendMessage(Utils.color("&e&l" + FirstGui.topbidplayer.get("top") + " &7Won the item for " + "&e" + FirstGui.topbid.get(FirstGui.topbidplayer.get("top")) + " &eCoins ◈"));
                        });
                        FirstGui.topbidplayer.clear();

                        executeNextLevel();
                    }
                } else if (Utils.timerstatus.get("status")){

                    topbidarmor.setCustomNameVisible(true);
                    antimerarmor.setCustomName(Utils.color("&bYou have &e" + Integer.toString(countnum) + " &bSeconds to bid!"));
                    antimerarmor.setCustomNameVisible(true);
                    ItemStack timegui = XMaterial.CLOCK.parseItem();
                    timegui.setAmount(countnum);
                    ItemMeta meta = timegui.getItemMeta();
                    meta.setDisplayName(Utils.color("&eRemaining Time: " + Integer.toString(countnum) + " &eSeconds"));
                    timegui.setItemMeta(meta);
                    FirstGui.gui.updateItem(4, new GuiItem(timegui));
                    countnum -= 1;
                }

            }
        }, 60, 20);
    }

    public void executeNextLevel() {
        int level = Utils.auctionlevel.get("level");

        if (level == 1) {

            Bukkit.getScheduler().scheduleSyncDelayedTask(DarkAuction.getInstance(), new Runnable() {
                @Override
                public void run() {
                    startSecondItem();


                }
            }, 20 * 3);

        } else if (level == 2) {

            Bukkit.getScheduler().scheduleSyncDelayedTask(DarkAuction.getInstance(), new Runnable() {
                @Override
                public void run() {
                    startThirdItem();
                }
            }, 20 * 3);

        } else if (level == 3) {
            //close the auction
            Bukkit.getScheduler().scheduleSyncDelayedTask(DarkAuction.getInstance(), new Runnable() {
                @Override
                public void run() {
                    closeAuction();
                }
            }, 20 * 3);

        }

    }

    public void closeAuction() {

        listofauctioneditems.clear();
        EntranceNpc.despawn();
        EntranceNpc.destroy();
        Utils.guiphase.put("phase", 1);
        Location location = Utils.convertStringToLoc(Utils.config.getString("itemshowcase"));
        playersinauction.forEach(player -> {
            player.stopSound(Utils.stal);
            player.removePotionEffect(PotionEffectType.INVISIBILITY);
        });
        teleportBack();
        Utils.auctionlevel.remove("level");
        Utils.inauction.clear();
        playersinauction.clear();
    }

    public void teleportBack() {

        for (Player player : playersinauction) {

            player.teleport(Utils.inauction.get(player.getName()));
            player.sendMessage(Utils.color(Utils.prefix + "&bHope you had a great time :)"));

        }

    }
    public static void spawnBiddingHolo(int amount, String bidder) {
        topbidarmor.setCustomName(Utils.color("&cTop Bid: &b" + Integer.toString(amount) + " &eCoins ◈" + " &bBy " + bidder));
        topbidarmor.setCustomNameVisible(true);

    }




}
