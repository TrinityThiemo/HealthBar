package eu.trinitydev.healthbar;

import eu.trinitydev.healthbar.events.EntityDamage;
import eu.trinitydev.healthbar.utils.ActionBar;
import eu.trinitydev.healthbar.utils.BasicManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Thiemo on 5-12-2015.
 * No part of this publication may be reproduced, distributed, or transmitted in any form or by any means.
 * Copyright © 2015 by Thiemo
 */
public class Core extends JavaPlugin {

    public ActionBar bar;
    public BasicManager manager;
    public String bar_format = "&f%damager - &c[%health&c]";
    public String health_used = "&c|";
    public String health_not_used = "&4|";

    public void onEnable() {
        this.bar = new ActionBar(this);
        this.manager = new BasicManager(this);

        initEvents();
        initConfig();

        this.bar_format = getConfig().getString("format");
        this.health_used = getConfig().getString("health-format.health-used");
        this.health_not_used = getConfig().getString("health-format.health-not-used");
    }

    private void initEvents() {
        getServer().getPluginManager().registerEvents(new EntityDamage(this), this);
    }


    private void initConfig() {
        saveDefaultConfig();
    }


}

