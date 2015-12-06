package eu.trinitydev.healthbar.events;

import eu.trinitydev.healthbar.Core;
import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by Thiemo on 5-12-2015.
 * No part of this publication may be reproduced, distributed, or transmitted in any form or by any means.
 * Copyright © 2015 by Thiemo
 */
public class EntityDamage implements Listener {

    private Core plugin;

    public EntityDamage(Core instance) {
        this.plugin = instance;
    }

    @EventHandler
    public void entityDamage(EntityDamageByEntityEvent event) {
        if (event.isCancelled()) {
            return;
        }

        if (!(plugin.manager.worldEnabled(event.getDamager().getWorld().getName()))) {
            return;
        }

        if (!(plugin.manager.entityEnabled(event.getEntity().getType()))) {
            return;
        }

        if (event.getDamager() instanceof LivingEntity) {
            if (event.getDamager() instanceof Player) {
                Player player = (Player) event.getDamager();
                if (event.getEntity() instanceof Player) {
                    Player target = (Player) event.getEntity();
                    sendPlayers(player, target, event.getDamage());
                } else if (event.getEntity() instanceof LivingEntity) {
                    LivingEntity entity = (LivingEntity) event.getEntity();
                    sendPlayerEntity(entity, player, event.getDamage());
                }
            } else {
                if (event.getEntity() instanceof Player) {
                    Player player = (Player) event.getEntity();
                    LivingEntity entity = (LivingEntity) event.getDamager();
                    sendPlayerEntity(entity, player, event.getDamage());
                }
            }
        } else {
            if (event.getDamager() instanceof Arrow) {
                if (event.getEntity() instanceof Player) {
                    Player target = (Player) event.getEntity();
                    Arrow arrow = (Arrow) event.getDamager();
                    if (arrow.getShooter() instanceof LivingEntity) {
                        if (arrow.getShooter() instanceof Player) {
                            Player player = (Player) arrow.getShooter();
                            sendPlayers(player, target, event.getDamage());
                        } else {
                            LivingEntity entity = (LivingEntity) arrow.getShooter();
                            sendPlayerEntity(entity, target, event.getDamage());
                        }
                    }
                }
            }
        }
    }

    private void sendPlayerEntity(LivingEntity entity, Player player, double damage) {
        if (entity.getCustomName() == null) {
            plugin.bar.sendActionBar(player, ChatColor.translateAlternateColorCodes('&', plugin.bar_format.replace("%damager", entity.getType().toString().substring(0, 1) + entity.getType().toString().substring(1).toLowerCase()).replace("%health", plugin.manager.getEntityHealth(entity, damage))));
        } else {
            plugin.bar.sendActionBar(player, ChatColor.translateAlternateColorCodes('&', plugin.bar_format.replace("%damager", entity.getCustomName()).replace("%health", plugin.manager.getEntityHealth(entity, damage))));
        }
    }

    private void sendPlayers(Player player, Player target, double damage) {
        plugin.bar.sendActionBar(player, ChatColor.translateAlternateColorCodes('&', plugin.bar_format.replace("%damager", target.getName()).replace("%health", plugin.manager.getEntityHealth(target, damage))));
        plugin.bar.sendActionBar(target, ChatColor.translateAlternateColorCodes('&', plugin.bar_format.replace("%damager", player.getName()).replace("%health", plugin.manager.getEntityHealth(player, damage))));
    }

}
