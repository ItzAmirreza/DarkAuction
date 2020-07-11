package me.gamepixel.sbdarkauction.tasks;

import Utils.Utils;
import me.gamepixel.sbdarkauction.SBDarkAuction;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.Trait;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StartingDA {

    private int countnum = 10;
    private int countdown = 0;

    NPC EntranceNpc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "");
    public void entrancenpcspawn() {
        EntranceNpc.getTrait(SkinTrait.class).setSkinPersistent("salam", "LnO4hRXRK+aumyL2/Ck/ZVTgifEr5J6GxnRKNsXDIVDhlfcvPXsMtNeoiQUNCGWeC5I/wjMOesiO87Y2LaRaMVbcrSA2XMzvYQ4JyNN4aJqCfuUtIuYqvOFFp5mK4xUSUmOP0OP1WldzCrp/bYae/hSUaE1cfJZY/kiOorYwfb4kE1sObq6hUmqJfPXbVxEiMZosRZlQl/qhkTXiBasfDfNI/COuPz+OhZWEMr9MFWa/08Hb9slzjk7jVUFdIejJreHOMg24KBWYYermiiPRhF3DbRmKXonN1wokPwIQIbtzl5aQ3sIhmuJd+9He9joRcS+uP8DFoLngiqLJojgmzRAE4qLuKFjj4PIh64W4AnzP+kWtwsI0oUYHmqo7hCJ0zIHVZ4IZ/Itsomq+V3oOdO5gdJZB/fNmVWbNYp6SfNKyDnzaYtjVjoD/ijpWAaz43S0oF9ew42IBg9ytL1B0mnYfCjsec6BdW26rGVmaSZn5ERTKK3pT38QEv9Jh/uVFPoFtJ0YPWq6WqJhplhhSskpq82E7BExqOTf1NTLGWa3xz5bPZ5wadJBVYu+esdxBSO6vc0EBHlJoIqhD2VHj4BBGmXmcWt+bZFUNxXp4crkuDSGijGDQ2wSP+Rl94O54o2EHBFPoZ8EPnNuWJqQgkOeEfa/XHVMuj7ZGogAP2b8=", "ewogICJ0aW1lc3RhbXAiIDogMTU5NDI4MTIzNzM5MiwKICAicHJvZmlsZUlkIiA6ICJlZDUzZGQ4MTRmOWQ0YTNjYjRlYjY1MWRjYmE3N2U2NiIsCiAgInByb2ZpbGVOYW1lIiA6ICIwMTAwMDExMDAxMDAwMDExIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2NhMDE0NTMxYzY0ZGQ3MDMyNWJjN2FiNmMwNjI3YTY5MDAyMzE0NDY4MWQyYTQ5MDUwZDc0ZDlmMWIxNmVjMTgiCiAgICB9CiAgfQp9");
        Location location = Utils.convertStringToLoc(SBDarkAuction.getInstance().getConfig().getString("entrance-npc-coordinates"));
        EntranceNpc.setProtected(true);
        EntranceNpc.spawn(location);
        teleportToDarkauction(getNearPlayers(EntranceNpc.getEntity()));
    }


    public List getNearPlayers(Entity entity) {
        List<Entity> nearbyentities = entity.getNearbyEntities(10, 10, 10);
        List<Player> onlyrealplayerslist = new ArrayList<>();

        for (Entity en : nearbyentities) {

            if (!en.hasMetadata("NPC") && en instanceof Player) {
                Player player = (Player) en;
                onlyrealplayerslist.add(player);

            }


        }

        return onlyrealplayerslist;
    }

    public void teleportToDarkauction(List<Player> players) {

        Location location = Utils.convertStringToLoc(SBDarkAuction.getInstance().getConfig().getString("teleport-at-start"));
        //badan bayad add beshe hade aghal ye meghar pool dashte bashe <--- toye config.yml ham hast darbarash !

        Bukkit.getScheduler().scheduleSyncDelayedTask(SBDarkAuction.getInstance(), new Runnable() {
            @Override
            public void run() {

                for (Player player : players) {

                    player.teleport(location);
                    player.sendMessage(Utils.color(Utils.prefix + "&7 You have entered the Secret Auction House!"));

                }

            }
        }, 20 * 15);

        Bukkit.getScheduler().scheduleSyncDelayedTask(SBDarkAuction.getInstance(), new Runnable() {
            @Override
            public void run() {

                startFirstItem(Utils.auctionitems);

            }
        }, 20 * 5);


    }


    public void startFirstItem(List<ItemStack> items) {

        if (items.size() == 0) {

            Bukkit.broadcastMessage(Utils.color(Utils.prefix + "&cNo items for today's auction!"));

        } else {

            Random random = new Random();

            ItemStack itemst = items.get(random.nextInt(items.size()));

            Location location = Utils.convertStringToLoc(SBDarkAuction.getInstance().getConfig().getString("itemshowcase"));

            Item item = location.getWorld().dropItem(location, itemst);
            item.setVelocity(new Vector());
            item.setPickupDelay(32767);
            item.setInvulnerable(true);
            spawnArmorStand(location, item);

        }

    }

    public void spawnArmorStand(Location location, Item item) {

        Bukkit.getScheduler().scheduleSyncDelayedTask(SBDarkAuction.getInstance(), new Runnable() {
            @Override
            public void run() {
                ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                armorStand.setVisible(false);
                armorStand.setArms(false);
                armorStand.setGravity(false);
                String itemlore = item.getItemStack().getItemMeta().getDisplayName();
                if (item.getItemStack().getItemMeta().hasLore()) {
                    for (String i : item.getItemStack().getItemMeta().getLore()) {
                        itemlore = itemlore + "\n " + i;

                    }
                }

                armorStand.setCustomName(Utils.color(itemlore));
                armorStand.setCustomNameVisible(true);
                startCountDown(armorStand, item);
            }
        }, 20 * 3);

    }


    public void startCountDown(ArmorStand armorStand, Item item) {


        if (Utils.timerstatus.containsKey("status")) {

            Location newloc = new Location(armorStand.getWorld(), armorStand.getLocation().getX(), armorStand.getLocation().getY() - 1, armorStand.getLocation().getZ());

            ArmorStand timerarmor = (ArmorStand) newloc.getWorld().spawnEntity(newloc, EntityType.ARMOR_STAND);
            timerarmor.setVisible(false);
            timerarmor.setArms(false);
            timerarmor.setGravity(false);
            Utils.timerstatus.replace("status", true);

        } else {

            Location newloc = new Location(armorStand.getWorld(), armorStand.getLocation().getX(), armorStand.getLocation().getY() - 1, armorStand.getLocation().getZ());

            ArmorStand timerarmor = (ArmorStand) newloc.getWorld().spawnEntity(newloc, EntityType.ARMOR_STAND);
            timerarmor.setVisible(false);
            timerarmor.setArms(false);
            timerarmor.setGravity(false);

            Utils.timerstatus.put("status", true);
            Bukkit.getScheduler().scheduleSyncRepeatingTask(SBDarkAuction.getInstance(), new Runnable() {
                @Override
                public void run() {

                    if (countnum <= countdown && Utils.timerstatus.get("status")) {

                        armorStand.remove();
                        timerarmor.remove();
                        item.remove();
                        EntranceNpc.despawn();
                        countnum = 10;
                        Utils.timerstatus.replace("status", false);
                    } else if (Utils.timerstatus.get("status")){
                        timerarmor.setCustomName(Utils.color("&bYou have &e" + Integer.toString(countnum) + " &bSeconds to bid!"));
                        timerarmor.setCustomNameVisible(true);
                        countnum -= 1;
                    }

                }
            }, 60, 20);
        }



    }




}
