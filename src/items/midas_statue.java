package items;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import org.bukkit.inventory.meta.ItemMeta;
import thirtyvirus.uber.UberItem;
import thirtyvirus.uber.helpers.*;

public class midas_statue extends UberItem {

    public midas_statue(Material material, String name, UberRarity rarity, boolean stackable, boolean oneTimeUse, boolean hasActiveEffect, List<UberAbility> abilities, UberCraftingRecipe craftingRecipe) {
        super(material, name, rarity, stackable, oneTimeUse, hasActiveEffect, abilities, craftingRecipe);
    }
    public void onItemStackCreate(ItemStack item) { }
    public void getSpecificLorePrefix(List<String> lore, ItemStack item) { }
    public void getSpecificLoreSuffix(List<String> lore, ItemStack item) { }

    public boolean leftClickAirAction(Player player, ItemStack item) { return false; }
    public boolean leftClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) { return false; }

    public boolean rightClickAirAction(Player player, ItemStack item) {

        // toggle midas step ability
        if (Utilities.getIntFromItem(item, "ability1") == 0) {
            Utilities.storeIntInItem(item, 1, "ability1");
            Utilities.addEnchantGlint(item);
        }
        else {
            Utilities.storeIntInItem(item, 0, "ability1");
            ItemMeta meta = item.getItemMeta();
            for (Enchantment e : meta.getEnchants().keySet())
                meta.removeEnchant(e);
            item.setItemMeta(meta);
        }

        return true;
    }

    public boolean rightClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) { return false; }
    public boolean shiftLeftClickAirAction(Player player, ItemStack item) { return false; }
    public boolean shiftLeftClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) { return false; }
    public boolean shiftRightClickAirAction(Player player, ItemStack item) { return false; }
    public boolean shiftRightClickBlockAction(Player player, PlayerInteractEvent event, Block block, ItemStack item) { return false; }

    public boolean middleClickAction(Player player, ItemStack item) { return false; }

    public boolean hitEntityAction(Player player, EntityDamageByEntityEvent event, Entity target, ItemStack item) {
        Block block = target.getLocation().getBlock();
        block.setType(Material.GOLD_BLOCK);
        target.remove();
        return false;
    }

    public boolean breakBlockAction(Player player, BlockBreakEvent event, Block block, ItemStack item) { return false; }
    public boolean clickedInInventoryAction(Player player, InventoryClickEvent event, ItemStack item, ItemStack addition) { return false; }
    public boolean activeEffect(Player player, ItemStack item) {

        if (Utilities.getIntFromItem(item, "ability1") == 1) {
            Block block = player.getLocation().subtract(0, 1, 0).getBlock();
            if (block.getType().isSolid()) {
                block.setType(Material.GOLD_BLOCK);
                return true;
            }
        }

        return false;
    }
}
