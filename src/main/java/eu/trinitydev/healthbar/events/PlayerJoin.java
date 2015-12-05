package eu.trinitydev.healthbar.events;

import eu.trinitydev.healthbar.Core;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Thiemo on 5-12-2015.
 * No part of this publication may be reproduced, distributed, or transmitted in any form or by any means.
 * Copyright © 2015 by Thiemo
 */
public class PlayerJoin implements Listener {

    private Core plugin;

    public PlayerJoin(Core instance) {
        this.plugin = instance;
    }


    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("healthbar.update")) {
            return;
        }

        if (!plugin.updated) {
            if(!plugin.getConfig().getBoolean("announce-update")) {
                return;
            }
            player.sendMessage(ChatColor.GOLD + "[HealthBar] " + ChatColor.GRAY + "A new update is out!");
            player.sendMessage(ChatColor.GOLD + "[HealthBar] " + ChatColor.GRAY + "You are running on " + ChatColor.GOLD + "v" + plugin.getDescription().getVersion() + ChatColor.GRAY + " and " + ChatColor.GOLD + "v" + plugin.latestversion + ChatColor.GRAY + " is out now!");
            player.sendMessage(ChatColor.GOLD + "[HealthBar] " + ChatColor.GRAY + "Update it at " + ChatColor.GOLD + "https://www.spigotmc.org/resources/healthbar.15230/");
        }
    }
}
