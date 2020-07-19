package me.gamepixel.sbdarkauction.events;

import Utils.Utils;
import me.gamepixel.sbdarkauction.guilist.FirstGui;
import me.gamepixel.sbdarkauction.tasks.StartingDA;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerInteractWithEntity implements Listener {

    @EventHandler
    public void interactionEvent(PlayerInteractAtEntityEvent e) {

        System.out.println("lvl 1");
        if (e.getRightClicked().getType() == EntityType.ARMOR_STAND) {

            System.out.println("lvl 2");
            ArmorStand stand = (ArmorStand) e.getRightClicked();
            if (stand == StartingDA.anarmorstand || stand == StartingDA.antimerarmor) {
                e.setCancelled(true);
                System.out.println("lvl 3");
                FirstGui.gui.open(e.getPlayer());

            }

        }


    }

    @EventHandler
    public void interactionEvent2(PlayerInteractEntityEvent e) {

        System.out.println("lvl 1");
        if (e.getRightClicked().getType() == EntityType.ARMOR_STAND) {

            System.out.println("lvl 2");
            ArmorStand stand = (ArmorStand) e.getRightClicked();
            if (stand == StartingDA.anarmorstand || stand == StartingDA.antimerarmor) {
                e.setCancelled(true);
                System.out.println("lvl 3");
                FirstGui.gui.open(e.getPlayer());

            }

        }


    }

    @EventHandler
    public void equiptingarmor(PlayerArmorStandManipulateEvent e) {

        if (e.getRightClicked() == StartingDA.anarmorstand || e.getRightClicked() == StartingDA.antimerarmor) {

            e.setCancelled(true);
        }

    }


}
