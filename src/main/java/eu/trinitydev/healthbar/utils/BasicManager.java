package eu.trinitydev.healthbar.utils;

import eu.trinitydev.healthbar.Core;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by Thiemo on 5-12-2015.
 * No part of this publication may be reproduced, distributed, or transmitted in any form or by any means.
 * Copyright © 2015 by Thiemo
 */
public class BasicManager {

    private Core plugin;

    public BasicManager(Core instance) {
        this.plugin = instance;
    }

    public String getEntityHealth(LivingEntity entity, double damage) {
        ArrayList<String> entity_health = new ArrayList<String>();
        int int_damage = (int) damage;
        int total = 0;

        for (int i = 0; i < (entity.getHealth() -
                int_damage); ) {
            entity_health.add(ChatColor.translateAlternateColorCodes('&', plugin.health_used));
            i++;
        }

        for (int i = 0; i < (int) entity.getMaxHealth(); ) {
            if (entity_health.size() < (int) entity.getMaxHealth()) {
                entity_health.add(ChatColor.translateAlternateColorCodes('&', plugin.health_not_used));
            }

            i++;
        }

        return entity_health.toString().replace(",", "").replace("[", "").replace("]", "").replace(" ", "");
    }

    public boolean worldEnabled(String world) {
        boolean enabled = true;
        if (plugin.getConfig().getStringList("disabled-worlds")
                .contains(world)) {
            enabled = false;
        }

        return enabled;
    }


    public boolean entityEnabled(EntityType type) {
        boolean enabled = true;
        if (plugin.getConfig().getStringList("disabled-entities")
                .contains(type.toString().toLowerCase())) {
            enabled = false;
        }

        return enabled;
    }


}

