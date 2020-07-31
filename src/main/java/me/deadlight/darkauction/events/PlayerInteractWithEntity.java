package me.deadlight.darkauction.events;

import me.deadlight.darkauction.guilist.FirstGui;
import me.deadlight.darkauction.tasks.StartingDA;
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

        if (e.getRightClicked().getType() == EntityType.ARMOR_STAND) {

            ArmorStand stand = (ArmorStand) e.getRightClicked();
            if (stand == StartingDA.anarmorstand || stand == StartingDA.antimerarmor) {
                e.setCancelled(true);
                FirstGui.gui.open(e.getPlayer());

            }

        }


    }

    @EventHandler
    public void interactionEvent2(PlayerInteractEntityEvent e) {

        if (e.getRightClicked().getType() == EntityType.ARMOR_STAND) {

            ArmorStand stand = (ArmorStand) e.getRightClicked();
            if (stand == StartingDA.anarmorstand || stand == StartingDA.antimerarmor) {
                e.setCancelled(true);
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
