package eu.trinitydev.healthbar.events;

import eu.trinitydev.healthbar.Core;
import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;

/**
 * Created by Thiemo on 4-12-2015.
 */
public class EntityDamage implements Listener {

    private Core plugin;

    public EntityDamage(Core instance) {
        this.plugin = instance;
    }

    public String getEntityHealth(LivingEntity entity, double damage) {
        ArrayList<String> entity_health = new ArrayList<String>();
        int int_damage = (int) damage;
        int total = 0;

        for (int i = 0; i < (entity.getHealth() -
                int_damage);) {
            entity_health.add(ChatColor.translateAlternateColorCodes('&', plugin.health_used));
            i++;
        }

        for(int i = 0; i < (int) entity.getMaxHealth();) {
            if (entity_health.size() < (int) entity.getMaxHealth()) {
                entity_health.add(ChatColor.translateAlternateColorCodes('&', plugin.health_not_used));
            }

            i++;
        }
        String health = entity_health.toString().replace(",", "").replace("[", "").replace("]", "").replace(" ", "");
        return health;
    }

    @EventHandler
    public void entityDamage(EntityDamageByEntityEvent event) {
        if(event.isCancelled()) {
            return;
        }
        if (event.getDamager() instanceof LivingEntity) {
            if (event.getDamager() instanceof Player) {
                Player player = (Player) event.getDamager();
                if (event.getEntity() instanceof Player) {
                    Player target = (Player) event.getEntity();
                    String bar_send_player = ChatColor.translateAlternateColorCodes('&', plugin.bar_format.replace("%damager", target.getName()).replace("%health", getEntityHealth(target, event.getDamage())));
                    String bar_send_target = ChatColor.translateAlternateColorCodes('&', plugin.bar_format.replace("%damager", player.getName()).replace("%health", getEntityHealth(player, event.getDamage())));
                    plugin.bar.sendActionBar(player, bar_send_player);
                    plugin.bar.sendActionBar(target, bar_send_target);
                } else if (event.getEntity() instanceof LivingEntity) {
                    LivingEntity entity = (LivingEntity) event.getEntity();
                    String bar_send = ChatColor.translateAlternateColorCodes('&', plugin.bar_format.replace("%damager", entity.getType().toString().substring(0, 1) + entity.getType().toString().substring(1).toLowerCase()).replace("%health", getEntityHealth(entity, event.getDamage())));
                    plugin.bar.sendActionBar(player, bar_send);
                }
            } else {
                if(event.getEntity() instanceof  Player) {
                    Player player = (Player) event.getEntity();
                    LivingEntity entity = (LivingEntity) event.getDamager();
                    String bar_send = ChatColor.translateAlternateColorCodes('&', plugin.bar_format.replace("%damager", entity.getType().toString().substring(0, 1) + entity.getType().toString().substring(1).toLowerCase()).replace("%health", getEntityHealth(entity, event.getDamage())));
                    plugin.bar.sendActionBar(player, bar_send);
                }
            }
        } else {
            if(event.getDamager() instanceof Arrow) {
                if (event.getEntity() instanceof Player) {
                    Player target = (Player) event.getEntity();
                    Arrow arrow = (Arrow) event.getDamager();
                    if (arrow.getShooter() instanceof LivingEntity) {
                        if (arrow.getShooter() instanceof Player) {
                            Player player = (Player) arrow.getShooter();
                            String bar_send_player = ChatColor.translateAlternateColorCodes('&', plugin.bar_format.replace("%damager", target.getName()).replace("%health", getEntityHealth(target, event.getDamage())));
                            String bar_send_target = ChatColor.translateAlternateColorCodes('&', plugin.bar_format.replace("%damager", player.getName()).replace("%health", getEntityHealth(player, event.getDamage())));
                            plugin.bar.sendActionBar(player, bar_send_player);
                            plugin.bar.sendActionBar(target, bar_send_target);
                        } else {
                            LivingEntity entity = (LivingEntity)arrow.getShooter();
                            String bar_send = ChatColor.translateAlternateColorCodes('&', plugin.bar_format.replace("%damager", entity.getType().toString().substring(0, 1) + entity.getType().toString().substring(1).toLowerCase()).replace("%health", getEntityHealth(entity, event.getDamage())));
                            plugin.bar.sendActionBar(target, bar_send);
                        }
                    }
                }
            }
        }
    }

}
