package me.gamepixel.sbdarkauction.events;

import Utils.Utils;
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

        e.getRightClicked().sendMessage("lvl 1");
        System.out.println("lvl 1");
        if (e.getRightClicked().getType() == EntityType.ARMOR_STAND) {

            e.getRightClicked().sendMessage("lvl 2");
            System.out.println("lvl 2");
            ArmorStand stand = (ArmorStand) e.getRightClicked();
            if (stand == Utils.anarmorstand) {
                e.setCancelled(true);
                e.getRightClicked().sendMessage("lvl 3");
                System.out.println("lvl 3");
                Utils.gui.open(e.getPlayer());

            }

        }


    }

    @EventHandler
    public void interactionEvent2(PlayerInteractEntityEvent e) {

        e.getRightClicked().sendMessage("lvl 1");
        System.out.println("lvl 1");
        if (e.getRightClicked().getType() == EntityType.ARMOR_STAND) {

            e.getRightClicked().sendMessage("lvl 2");
            System.out.println("lvl 2");
            ArmorStand stand = (ArmorStand) e.getRightClicked();
            if (stand == Utils.anarmorstand) {
                e.setCancelled(true);
                e.getRightClicked().sendMessage("lvl 3");
                System.out.println("lvl 3");
                Utils.gui.open(e.getPlayer());

            }

        }


    }

    @EventHandler
    public void equiptingarmor(PlayerArmorStandManipulateEvent e) {

        if (e.getRightClicked() == Utils.anarmorstand || e.getRightClicked() == Utils.antimerstand) {

            e.setCancelled(true);
        }

    }


}
