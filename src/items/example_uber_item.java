package items;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import thirtyvirus.uber.UberItem;
import thirtyvirus.uber.UberItems;
import thirtyvirus.uber.helpers.UberAbility;
import thirtyvirus.uber.helpers.UberRarity;

// a template class that can be copy - pasted and renamed when making new Uber Items
public class example_uber_item extends UberItem {

    public example_uber_item(int id, UberRarity rarity, String name, Material material, boolean stackable, boolean oneTimeUse, boolean hasActiveEffect, List<UberAbility> abilities) {
        super(id, rarity, name, material, stackable, oneTimeUse, hasActiveEffect, abilities);
    }
    public void onItemStackCreate(ItemStack item) { }
    public void getSpecificLorePrefix(List<String> lore, ItemStack item) { }
    public void getSpecificLoreSuffix(List<String> lore, ItemStack item) {
        lore.add(ChatColor.YELLOW + "This is the template item");
    }

    public void leftClickAirAction(Player player, ItemStack item) { }
    public void leftClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) { player.sendMessage("this is the template item");}
    public void rightClickAirAction(Player player, ItemStack item) { }
    public void rightClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) { }
    public void shiftLeftClickAirAction(Player player, ItemStack item) { }
    public void shiftLeftClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) { }
    public void shiftRightClickAirAction(Player player, ItemStack item) { }
    public void shiftRightClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) { }
    public void middleClickAction(Player player, ItemStack item) { }
    public void hitEntityAction(Player player, EntityDamageByEntityEvent event, Entity target, ItemStack item) { }
    public void breakBlockAction(Player player, BlockBreakEvent event, Block block, ItemStack item) { }
    public void clickedInInventoryAction(Player player, InventoryClickEvent event) { }
    public void activeEffect(Player player, ItemStack item) { }
}
