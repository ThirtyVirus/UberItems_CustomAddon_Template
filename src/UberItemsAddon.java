import events.ProjectileHit;
import items.empty_item;
import items.example_uber_item;
import items.midas_statue;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import thirtyvirus.uber.UberItems;
import thirtyvirus.uber.UberMaterial;
import thirtyvirus.uber.helpers.AbilityType;
import thirtyvirus.uber.helpers.UberAbility;
import thirtyvirus.uber.helpers.UberCraftingRecipe;
import thirtyvirus.uber.helpers.UberRarity;

import java.util.Arrays;
import java.util.Collections;

public class UberItemsAddon extends JavaPlugin {

    public void onEnable() {

        // enforce UberItems dependancy
        if (Bukkit.getPluginManager().getPlugin("UberItems") == null) {
            this.getLogger().severe("UberItems Addons requires UberItems! disabled because UberItems dependency not found");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        // register events and UberItems
        registerEvents();
        registerUberMaterials();
        registerUberItems();

        // post confirmation in chat
        getLogger().info(getDescription().getName() + " V: " + getDescription().getVersion() + " has been enabled");
    }
    public void onDisable() {
        // posts exit message in chat
        getLogger().info(getDescription().getName() + " V: " + getDescription().getVersion() + " has been disabled");
    }
    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new ProjectileHit(), this);
    }

    // NEW UBER ITEM CHECKLIST

    // - make a new class file, named with all lowercase lettering and underscores for spaces
    // - copy the UberItemTemplate class contents into the new class, extend UberItem
    // - make a putItem entry, follow the format of previous items and make sure to give a unique id
    // - write the unique item ability code in the appropriate method

    // - add the following line of code just after executing the item's ability:
    //      onItemUse(player, item); // confirm that the item's ability has been successfully used

    // - if the ability needs a cooldown, prefix it's code with a variation of the following line of code:
    //      if (!Utilities.enforceCooldown(getMain(), player, "name", 1, item, true)) return;

    // - if the item needs work done on create (like adding enchantments, adding other data) refer to onItemStackCreate
    // - if the item needs a prefix or suffix in its description,
    //   refer to the getSpecificLorePrefix and getSpecificLoreSuffix functions, then add the following:
    //      lore.add(ChatColor.RESET + "text goes here");

    // - if you need to store & retrieve ints and strings from items, you can use the following functions:
    //      Utilities.storeIntInItem(getMain(), item, 1, "number tag");
    //      if (Utilities.getIntFromItem(getMain(), item, "number tag") == 1) // { blah blah blah }
    //      (the same case for strings, just storeStringInItem and getStringFromItem)

    private void registerUberItems() {
        UberItems.putItem("empty_item", new empty_item(Material.DIAMOND, "Empty UberItem", UberRarity.COMMON,
                false, false, false, Collections.emptyList(), null));

        UberItems.putItem("example_uber_item", new example_uber_item(Material.WOODEN_SHOVEL, "Example Uber Item",
                UberRarity.UNFINISHED, false, false, true,
                Arrays.asList(
                        new UberAbility("Example Ability", AbilityType.NONE, "Adds an enchantment glint to items when they are clicked onto this item in the inventory... because why not? xD"),
                        new UberAbility("Cooldown Demonstration", AbilityType.LEFT_CLICK, "Makes a sound? Idk you can only do it once every 5 seconds", 5),
                        new UberAbility("Midas Step", AbilityType.RIGHT_CLICK, "(toggle) every block you step on turns into gold while holding this item")),
                new UberCraftingRecipe(Arrays.asList(
                        UberItems.getMaterial("enchanted_chest").makeItem(1),
                        UberItems.getMaterial("enchanted_diamond").makeItem(1),
                        UberItems.getMaterial("enchanted_chest").makeItem(1),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.STICK, 8),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.STICK, 8),
                        new ItemStack(Material.AIR)), false, 1)));

        UberItems.putItem("midas_statue", new midas_statue(Material.TOTEM_OF_UNDYING, "Midas Statue", UberRarity.LEGENDARY,
                false, false, true,
                Arrays.asList(
                        new UberAbility("Midas Step", AbilityType.RIGHT_CLICK, "(toggle) every block you step on turns into gold while holding this item"),
                        new UberAbility("Midas Touch", AbilityType.NONE, "Turn any mobs into gold")),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.AIR),
                        UberItems.getMaterial("enchanted_diamond").makeItem(64),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.GOLD_BLOCK),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.GOLD_BLOCK),
                        new ItemStack(Material.AIR)), false, 1 )));

    }
    private void registerUberMaterials() {
        UberItems.putMaterial("enchanted_sponge", new UberMaterial(Material.SPONGE,
                "Enchanted Sponge", UberRarity.RARE, true, false, false,
                "idk why I chose sponge, but hey this demonstrates how to make a custom UberMaterial lol",
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.SPONGE, 32),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.SPONGE, 32),
                        new ItemStack(Material.SPONGE, 32),
                        new ItemStack(Material.SPONGE, 32),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.SPONGE, 32),
                        new ItemStack(Material.AIR)), false, 1)));
        UberItems.putMaterial("thumbnail", new UberMaterial(Material.GOLDEN_APPLE,
                "Titan Apple", UberRarity.MYTHIC, true, false, false,
                "" + ChatColor.GRAY + ChatColor.ITALIC + "look at what they /newline " + ChatColor.ITALIC + "need to mimic a /newline " + ChatColor.ITALIC + "fraction of our power",
                null));
    }
}