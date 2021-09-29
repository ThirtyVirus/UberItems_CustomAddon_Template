package events;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import thirtyvirus.uber.UberItems;
import thirtyvirus.uber.helpers.Utilities;

public class ProjectileHit implements Listener {

    @EventHandler
    private void onProjectileHit(ProjectileHitEvent event) {
        if (event.getEntityType() == EntityType.ENDER_PEARL) {
            if (Utilities.getEntityTag(event.getEntity(), "infini").equals("a")) {
                Player player = Bukkit.getPlayer(Utilities.getEntityTag(event.getEntity(), "infininame"));
                if (player == null) return;
                player.getInventory().addItem(UberItems.getItem("infina_pearl").makeItem(1));
            }
        }
    }

    @EventHandler
    private void onDamageEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() == EntityType.ENDER_PEARL) {
            if (Utilities.getEntityTag(event.getDamager(), "infini").equals("a")) {
                event.setCancelled(true);
            }
        }
    }

}
