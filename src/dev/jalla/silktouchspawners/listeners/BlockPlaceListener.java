package dev.jalla.silktouchspawners.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BlockPlaceListener implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public void onSpawnerPlace(BlockPlaceEvent e) {
		Block block = e.getBlock();
        Material material = block.getType();

        if (material == Material.SPAWNER) {
            ItemStack placed = e.getItemInHand();
            ItemMeta meta = placed.getItemMeta();
            try {
                EntityType entity = EntityType.valueOf(meta.getLore().toString().split(": §e")[1].split("]")[0].toUpperCase());
                CreatureSpawner spawner = (CreatureSpawner) block.getState();
                spawner.setSpawnedType(entity);
                spawner.update();
                
                e.getPlayer().sendMessage("Spawner type: " + spawner.getSpawnedType().toString());
            } catch (NullPointerException exception) {
                System.out.println(exception.getMessage());
            }
        }	
	}
}
