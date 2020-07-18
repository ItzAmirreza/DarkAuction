package me.gamepixel.sbdarkauction.events;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class PlayerInteractWithEntity implements Listener {

    @EventHandler
    public void interactionEvent(PlayerInteractAtEntityEvent e) {

        if (e.getRightClicked().getType() == EntityType.ARMOR_STAND) {

            if (e.getRightClicked().getMetadata())

        }


    }


}
