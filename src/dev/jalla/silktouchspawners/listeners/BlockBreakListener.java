package dev.jalla.silktouchspawners.listeners;

import java.util.Collections;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BlockBreakListener implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public void onSpawnerMine(BlockBreakEvent e) {
		Block block = e.getBlock();
		Material material = block.getType();
		Player player = e.getPlayer();
		
		// If our material is a spawner, and our break event isn't canceled:
		if(material == Material.SPAWNER && !e.isCancelled()) {
			CreatureSpawner spawner = (CreatureSpawner) block.getState();
			
			// If the player holds an item with the Silk Touch enchantment:
			if(player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
				// Set our Experience drop to 0 to prevent farming.
                e.setExpToDrop(0);
                
                // Give the block.
                if (player.getInventory().firstEmpty() != -1) {
                    ItemStack item = new ItemStack(material);
                    ItemMeta meta = item.getItemMeta();
                    meta.setLore(Collections.singletonList(ChatColor.WHITE + "Type: " + ChatColor.YELLOW + spawner.getSpawnedType().toString().toLowerCase()));
                    item.setItemMeta(meta);
                    player.getInventory().addItem(item);
                    block.getDrops().clear();
                } else {
                    player.sendMessage(ChatColor.RED + "Unable to give you the Spawner. Please clear some space in your inventory.");
                    e.setCancelled(true);
        		}
			}
		}
	}
}
