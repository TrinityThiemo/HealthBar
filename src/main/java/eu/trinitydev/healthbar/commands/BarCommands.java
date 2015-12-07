package eu.trinitydev.healthbar.commands;

import eu.trinitydev.healthbar.Core;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Thiemo on 7-12-2015.
 * No part of this publication may be reproduced, distributed, or transmitted in any form or by any means.
 * Copyright © 2015 by Thiemo
 */
public class BarCommands implements CommandExecutor {

    private Core plugin;

    public BarCommands(Core instance) {
        this.plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("healthbar.reload")) {
                    sender.sendMessage(ChatColor.GOLD + "[HealthBar] " + ChatColor.GRAY + "Configuration file has been reloaded");

                    plugin.reloadConfig();
                    plugin.disabled_worlds.addAll(plugin.getConfig().getStringList("disabled-worlds"));
                    plugin.disabled_entities.addAll(plugin.getConfig().getStringList("disabled-entities"));
                    plugin.bar_format = plugin.getConfig().getString("format");
                    plugin.health_used = plugin.getConfig().getString("health-format.health-used");
                    plugin.health_not_used = plugin.getConfig().getString("health-format.health-not-used");

                    if(plugin.getConfig().get("display-names") != null)
                        for(String names : plugin.getConfig().getConfigurationSection("display-names").getKeys(false)) {
                            plugin.display_names.put(names, plugin.getConfig().getString("display-names." + names));
                        }
                }
            }
        }
        return false;
    }
}
