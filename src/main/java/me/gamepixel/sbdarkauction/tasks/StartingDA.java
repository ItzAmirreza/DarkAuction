package me.gamepixel.sbdarkauction.tasks;

import Utils.Utils;
import me.gamepixel.sbdarkauction.SBDarkAuction;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.Trait;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class StartingDA {


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


    }

}
