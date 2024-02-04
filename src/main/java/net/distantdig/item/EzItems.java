package net.distantdig.item;

import net.distantdig.EzLib;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;

import java.util.HashMap;
import java.util.function.Function;

public class EzItems {

    public final static HashMap<String, Item> itemMap = new HashMap<>();

    public static <T extends Item> void registerItem(String key, Function<Item.Properties, T> constructor, Item.Properties props) {

        T item = Registry.register(BuiltInRegistries.ITEM,
                new ResourceLocation(EzLib.getModId(), key),
                constructor.apply(props));
        itemMap.put(key, item);
        EzItemGroups.ItemGroupList.add(item);

        // Register recipes
    }

    public static void registerSword(String key, Tier material, Integer damage, Float attackSpeed, Item.Properties props) {

        registerItem(key, properties -> new SwordItem(material, damage, attackSpeed, new Item.Properties()), props);

        // Register recipes
    }
    public static void registerPickaxe(String key, Tier material, Integer damage, Float attackSpeed, Item.Properties props) {

        registerItem(key, properties -> new PickaxeItem(material, damage, attackSpeed, new Item.Properties()), props);

        // Register recipes
    }
    public static void registerAxe(String key, Tier material, Integer damage, Float attackSpeed, Item.Properties props) {

        registerItem(key, properties -> new AxeItem(material, damage, attackSpeed, new Item.Properties()), props);

        // Register recipes
    }public static void registerShovel(String key, Tier material, Integer damage, Float attackSpeed, Item.Properties props) {

        registerItem(key, properties -> new ShovelItem(material, damage, attackSpeed, new Item.Properties()), props);

        // Register recipes
    }
    public static void registerHoe(String key, Tier material, Integer damage, Float attackSpeed, Item.Properties props) {

        registerItem(key, properties -> new HoeItem(material, damage, attackSpeed, new Item.Properties()), props);

        // Register recipes
    }

    public static void registerToolSet(String key, Tier material, Integer damage, Float attackSpeed, Item.Properties props) {

        registerSword(key + "_sword", material, damage, attackSpeed, props);
        registerPickaxe(key + "_pickaxe", material, damage, attackSpeed, props);
        registerAxe(key + "_axe", material, damage, attackSpeed, props);
        registerShovel(key + "_shovel", material, damage, attackSpeed, props);
        registerHoe(key + "_hoe", material, damage, attackSpeed, props);

        // Register recipes
    }

    public static Item getItem(String key) {
        return itemMap.get(key);
    }
}
