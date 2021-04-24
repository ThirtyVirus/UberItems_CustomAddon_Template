package items;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import thirtyvirus.uber.UberItem;
import thirtyvirus.uber.helpers.*;

public class example_uber_item extends UberItem {

    public example_uber_item(Material material, String name, UberRarity rarity, boolean stackable, boolean oneTimeUse, boolean hasActiveEffect, List<UberAbility> abilities, UberCraftingRecipe craftingRecipe) {
        super(material, name, rarity, stackable, oneTimeUse, hasActiveEffect, abilities, craftingRecipe);
    }
    public void onItemStackCreate(ItemStack item) { Utilities.addEnchantGlint(item); }
    public void getSpecificLorePrefix(List<String> lore, ItemStack item) { lore.add(ChatColor.GREEN + "this is how you add prefixes to your items!"); }
    public void getSpecificLoreSuffix(List<String> lore, ItemStack item) { lore.add(ChatColor.GREEN + "this is how you add suffixes to your items!"); }

    // the play sound ability
    public boolean leftClickAirAction(Player player, ItemStack item) {

        // enforce the cooldown for the ability (the 5 seconds in the description is only visual, don't forget to enforce it in your code!
        if (Utilities.enforceCooldown(player, "make-sound", 5, item, true)) return false;

        // play the sound (aka use the ability) then return true, meaning that the ability successfully was used
        Utilities.playSound(ActionSound.CLICK, player);
        return true;
    }

    // make sure that the ability works when left clicking air OR a block
    public boolean leftClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) { return leftClickAirAction(player, item); }

    // toggle the midas step ability
    public boolean rightClickAirAction(Player player, ItemStack item) {

        // toggling the effect places an int in the item itself, and also changes the type
        if (Utilities.getIntFromItem(item, "midas-step") == 0) {
            Utilities.storeIntInItem(item, 1, "midas-step");
            item.setType(Material.GOLDEN_SHOVEL);
        }
        else {
            Utilities.storeIntInItem(item, 0, "midas-step");
            item.setType(Material.WOODEN_SHOVEL);
        }

        return false;
    }

    // make sure that the ability works when right clicking air OR a block
    public boolean rightClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) { return rightClickAirAction(player, item); }

    public boolean shiftLeftClickAirAction(Player player, ItemStack item) { return false; }
    public boolean shiftLeftClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) { return false; }
    public boolean shiftRightClickAirAction(Player player, ItemStack item) { return false; }
    public boolean shiftRightClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) { return false; }
    public boolean middleClickAction(Player player, ItemStack item) { return false; }
    public boolean hitEntityAction(Player player, EntityDamageByEntityEvent event, Entity target, ItemStack item) { return false; }

    // make the durability of the item is infinite
    public boolean breakBlockAction(Player player, BlockBreakEvent event, Block block, ItemStack item) {
        Utilities.repairItem(item);
        return false;
    }

    // enchant an item when clicked onto this item in the inventory (doesn't work in creative mode b/c MC is stupid)
    public boolean clickedInInventoryAction(Player player, InventoryClickEvent event, ItemStack item, ItemStack addition) {
        Utilities.addEnchantGlint(addition);
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1,1);
        event.setCancelled(true);
        return true;
    }

    // actually do the midas step ability
    public boolean activeEffect(Player player, ItemStack item) {

        // get the block that the player is standing on
        Block block = player.getLocation().subtract(0,1,0).getBlock();

        // make sure that the ability is toggled
        if (Utilities.getIntFromItem(item, "midas-step") == 1) {

            // make sure that the block is solid
            if (block.getType().isSolid()) {
                block.setType(Material.GOLD_BLOCK);
                return true;
            }
        }

        return false;
    }
}
