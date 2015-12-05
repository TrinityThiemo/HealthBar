package eu.trinitydev.healthbar;

import eu.trinitydev.healthbar.events.EntityDamage;
import eu.trinitydev.healthbar.events.PlayerJoin;
import eu.trinitydev.healthbar.utils.ActionBar;
import eu.trinitydev.healthbar.utils.BasicManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

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

    public boolean updated = true;
    public String latestversion = "";

    public void onEnable() {
        this.bar = new ActionBar(this);
        this.manager = new BasicManager(this);

        initEvents();
        initConfig();

        this.bar_format = getConfig().getString("format");
        this.health_used = getConfig().getString("health-format.health-used");
        this.health_not_used = getConfig().getString("health-format.health-not-used");
        checkVersion();

        if(!updated) {
            System.out.println(ChatColor.GOLD + "[HealthBar] " + ChatColor.GRAY + "A new update is out!");
            System.out.println(ChatColor.GOLD + "[HealthBar] " + ChatColor.GRAY + "You are running on " + ChatColor.GOLD + "v" + this.getDescription().getVersion() + ChatColor.GRAY + " and " + ChatColor.GOLD + "v" + this.latestversion + ChatColor.GRAY + " is out now!");
            System.out.println(ChatColor.GOLD + "[HealthBar] " + ChatColor.GRAY + "Update it at " + ChatColor.GOLD + "https://www.spigotmc.org/resources/healthbar.15230/");
        }
    }

    private void initEvents() {
        getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        getServer().getPluginManager().registerEvents(new EntityDamage(this), this);
    }


    private void initConfig() {
        saveDefaultConfig();
    }

    private void checkVersion(){
        try {
            URL urlv = new URL("https://raw.githubusercontent.com/TrinityThiemo/HealthBar/master/README.md");
            BufferedReader in = new BufferedReader(new InputStreamReader(urlv.openStream()));
            this.latestversion = in.readLine();

            if(this.latestversion.equalsIgnoreCase(getDescription().getVersion())){
               this.updated = true;
            }else{
                this.updated = false;
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Unable to connect to the server");
            return;
        }
    }


}

