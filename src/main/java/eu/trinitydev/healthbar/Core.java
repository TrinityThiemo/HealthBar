package eu.trinitydev.healthbar;

import eu.trinitydev.healthbar.events.EntityDamage;
import eu.trinitydev.healthbar.utils.ActionBar;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    public ActionBar bar;
    public String bar_format = "&f%damager - &c[%health&c]";
    public String health_used = "&c|";
    public String health_not_used = "&4|";

    public void onEnable() {
        this.bar = new ActionBar(this);

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

