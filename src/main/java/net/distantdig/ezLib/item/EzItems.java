package net.distantdig.ezLib.item;

import net.distantdig.ezLib.EzLib;
import net.distantdig.ezLib.datagen.EzItemTagProvider;
import net.distantdig.ezLib.datagen.EzModelProvider;
import net.distantdig.ezLib.datagen.EzRecipeProvider;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;

import java.util.HashMap;
import java.util.function.Function;

public class EzItems {

    // Data Structures
    public final static HashMap<String, Item> itemMap = new HashMap<>();

    public static class ToolData<T extends TieredItem> {
        public T toolItem;
        public Item repairItem;
        public T previousTool;
    }

    public static class ToolSet {
        public SwordItem sword;
        public PickaxeItem pickaxe;
        public AxeItem axe;
        public ShovelItem shovel;
        public HoeItem hoe;
    }

    public static class ArmorData {
        public ArmorItem armorItem;
        public Item repairItem;
        public ArmorItem previousArmor;
    }

    public static class ArmorSet {
        public ArmorItem helmet;
        public ArmorItem chestplate;
        public ArmorItem leggings;
        public ArmorItem boots;
    }

    // Base Registers
    public static <T extends Item> T registerItem(String key, Function<Item.Properties, T> constructor, Item.Properties props) {

        T item = Registry.register(BuiltInRegistries.ITEM,
                new ResourceLocation(EzLib.getModId(), key),
                constructor.apply(props));
        itemMap.put(key, item);
        EzItemGroups.ItemGroupList.add(item);

        EzModelProvider.FlatModelList.add(item);

        // Register recipes

        return item;
    }
    private static <T extends Item> T registerItemWithoutModel(String key, Function<Item.Properties, T> constructor, Item.Properties props) {

        T item = Registry.register(BuiltInRegistries.ITEM,
                new ResourceLocation(EzLib.getModId(), key),
                constructor.apply(props));
        itemMap.put(key, item);
        EzItemGroups.ItemGroupList.add(item);

        // Register recipes

        return item;
    }

    // Food Items
    public static Item registerFoodItem(String key, FoodProperties foodProps, Item.Properties props) {

        return registerItem(key, Item::new, props.food(foodProps));
    }
    public static Item registerFoodItem(String key, int hunger, int saturationModifier, Item.Properties props) {
        FoodProperties food = new FoodProperties.Builder().nutrition(hunger).saturationMod(saturationModifier).build();

        return registerItem(key, Item::new, props.food(food));
    }

    // Fuel Items
    public static Item registerFuelItem(String key, int burnTime, Item.Properties props) {
        Item item = registerItem(key, Item::new, props);

        FuelRegistry.INSTANCE.add(item, burnTime);

        return item;
    }

    // Individual Tools
    public static SwordItem registerSword(String key, Tier material, Integer damage, Float attackSpeed, Item.Properties props) {
        ToolData<SwordItem> data = new ToolData<>();

        data.toolItem = registerItemWithoutModel(key, properties -> new SwordItem(material, damage, attackSpeed - 4.0f, new Item.Properties()), props);
        data.repairItem = material.getRepairIngredient().getItems()[0].getItem();

        EzModelProvider.HandheldModelList.add(data.toolItem);
        EzRecipeProvider.swordRecipeList.add(data);

        return data.toolItem;
    }
    public static SwordItem registerSword(String key, Tier material, Integer damage, Float attackSpeed, Item.Properties props, SwordItem previousTool) {
        ToolData<SwordItem> data = new ToolData<>();

        data.toolItem = registerItemWithoutModel(key, properties -> new SwordItem(material, damage, attackSpeed - 4.0f, new Item.Properties()), props);
        data.repairItem = material.getRepairIngredient().getItems()[0].getItem();
        data.previousTool = previousTool;

        EzModelProvider.HandheldModelList.add(data.toolItem);
        EzRecipeProvider.smithingToolRecipeList.add(data);
        return data.toolItem;
    }

    public static PickaxeItem registerPickaxe(String key, Tier material, Integer damage, Float attackSpeed, Item.Properties props) {
        ToolData<PickaxeItem> data = new ToolData<>();

        data.toolItem = registerItemWithoutModel(key, properties -> new PickaxeItem(material, damage, attackSpeed - 4.0f, new Item.Properties()), props);
        data.repairItem = material.getRepairIngredient().getItems()[0].getItem();

        EzModelProvider.HandheldModelList.add(data.toolItem);
        EzRecipeProvider.pickaxeRecipeList.add(data);
        return data.toolItem;
    }
    public static PickaxeItem registerPickaxe(String key, Tier material, Integer damage, Float attackSpeed, Item.Properties props, PickaxeItem previousTool) {
        ToolData<PickaxeItem> data = new ToolData<>();

        data.toolItem = registerItemWithoutModel(key, properties -> new PickaxeItem(material, damage, attackSpeed - 4.0f, new Item.Properties()), props);
        data.repairItem = material.getRepairIngredient().getItems()[0].getItem();
        data.previousTool = previousTool;

        EzModelProvider.HandheldModelList.add(data.toolItem);
        EzRecipeProvider.smithingToolRecipeList.add(data);
        return data.toolItem;
    }

    public static AxeItem registerAxe(String key, Tier material, Integer damage, Float attackSpeed, Item.Properties props) {
        ToolData<AxeItem> data = new ToolData<>();

        data.toolItem = registerItemWithoutModel(key, properties -> new AxeItem(material, damage, attackSpeed - 4.0f, new Item.Properties()), props);
        data.repairItem = material.getRepairIngredient().getItems()[0].getItem();

        EzModelProvider.HandheldModelList.add(data.toolItem);
        EzRecipeProvider.axeRecipeList.add(data);
        return data.toolItem;
    }
    public static AxeItem registerAxe(String key, Tier material, Integer damage, Float attackSpeed, Item.Properties props, AxeItem previousTool) {
        ToolData<AxeItem> data = new ToolData<>();

        data.toolItem = registerItemWithoutModel(key, properties -> new AxeItem(material, damage, attackSpeed - 4.0f, new Item.Properties()), props);
        data.repairItem = material.getRepairIngredient().getItems()[0].getItem();
        data.previousTool = previousTool;

        EzModelProvider.HandheldModelList.add(data.toolItem);
        EzRecipeProvider.smithingToolRecipeList.add(data);
        return data.toolItem;
    }

    public static ShovelItem registerShovel(String key, Tier material, Integer damage, Float attackSpeed, Item.Properties props) {
        ToolData<ShovelItem> data = new ToolData<>();

        data.toolItem = registerItemWithoutModel(key, properties -> new ShovelItem(material, damage, attackSpeed - 4.0f, new Item.Properties()), props);
        data.repairItem = material.getRepairIngredient().getItems()[0].getItem();

        EzModelProvider.HandheldModelList.add(data.toolItem);
        EzRecipeProvider.shovelRecipeList.add(data);
        return data.toolItem;
    }
    public static ShovelItem registerShovel(String key, Tier material, Integer damage, Float attackSpeed, Item.Properties props, ShovelItem previousTool) {
        ToolData<ShovelItem> data = new ToolData<>();

        data.toolItem = registerItemWithoutModel(key, properties -> new ShovelItem(material, damage, attackSpeed - 4.0f, new Item.Properties()), props);
        data.repairItem = material.getRepairIngredient().getItems()[0].getItem();
        data.previousTool = previousTool;

        EzModelProvider.HandheldModelList.add(data.toolItem);
        EzRecipeProvider.smithingToolRecipeList.add(data);
        return data.toolItem;
    }

    public static HoeItem registerHoe(String key, Tier material, Integer damage, Float attackSpeed, Item.Properties props) {
        ToolData<HoeItem> data = new ToolData<>();

        data.toolItem = registerItemWithoutModel(key, properties -> new HoeItem(material, damage, attackSpeed - 4.0f, new Item.Properties()), props);
        data.repairItem = material.getRepairIngredient().getItems()[0].getItem();

        EzModelProvider.HandheldModelList.add(data.toolItem);
        EzRecipeProvider.hoeRecipeList.add(data);
        return data.toolItem;
    }
    public static HoeItem registerHoe(String key, Tier material, Integer damage, Float attackSpeed, Item.Properties props, HoeItem previousTool) {
        ToolData<HoeItem> data = new ToolData<>();

        data.toolItem = registerItemWithoutModel(key, properties -> new HoeItem(material, damage, attackSpeed - 4.0f, new Item.Properties()), props);
        data.repairItem = material.getRepairIngredient().getItems()[0].getItem();
        data.previousTool = previousTool;

        EzModelProvider.HandheldModelList.add(data.toolItem);
        EzRecipeProvider.smithingToolRecipeList.add(data);
        return data.toolItem;
    }

    // Tool Sets
    public static ToolSet registerToolSet(String key, Tier material, Integer damage, Float attackSpeed, Item.Properties props) {
        ToolSet set = new ToolSet();

        set.sword = registerSword(key + "_sword", material, damage, attackSpeed, props);
        set.pickaxe = registerPickaxe(key + "_pickaxe", material, damage - 2, attackSpeed - 0.4f, props);
        set.axe = registerAxe(key + "_axe", material, damage + 2, attackSpeed + 0.4f, props);
        set.shovel = registerShovel(key + "_shovel", material, damage - 2, attackSpeed - 0.6f, props);
        set.hoe = registerHoe(key + "_hoe", material, 1, attackSpeed * 2, props);

        return set;
    }
    public static ToolSet registerToolSet(String key, Tier material, Integer damage, Float attackSpeed, Item.Properties props, ToolSet previousSet) {
        ToolSet set = new ToolSet();

        set.sword = registerSword(key + "_sword", material, damage, attackSpeed, props, previousSet.sword);
        set.pickaxe = registerPickaxe(key + "_pickaxe", material, damage - 2, attackSpeed - 0.4f, props, previousSet.pickaxe);
        set.axe = registerAxe(key + "_axe", material, damage + 2, attackSpeed + 0.4f, props, previousSet.axe);
        set.shovel = registerShovel(key + "_shovel", material, damage - 2, attackSpeed - 0.6f, props, previousSet.shovel);
        set.hoe = registerHoe(key + "_hoe", material, 1, attackSpeed * 2, props, previousSet.hoe);

        return set;
    }

    // Individual Armors
    public static ArmorItem registerHelmet(String key, ArmorMaterial material, Item.Properties props) {
        ArmorData data = new ArmorData();

        data.armorItem = registerItem(key, properties -> new ArmorItem(material, ArmorItem.Type.HELMET, new Item.Properties()), props);
        data.repairItem = material.getRepairIngredient().getItems()[0].getItem();

        EzRecipeProvider.helmetRecipeList.add(data);
        return data.armorItem;
    }
    public static ArmorItem registerHelmet(String key, ArmorMaterial material, Item.Properties props, ArmorItem previousArmor) {
        ArmorData data = new ArmorData();

        data.armorItem = registerItem(key, properties -> new ArmorItem(material, ArmorItem.Type.HELMET, new Item.Properties()), props);
        data.repairItem = material.getRepairIngredient().getItems()[0].getItem();
        data.previousArmor = previousArmor;

        EzRecipeProvider.smithingArmorRecipeList.add(data);
        return data.armorItem;
    }

    public static ArmorItem registerChestplate(String key, ArmorMaterial material, Item.Properties props) {
        ArmorData data = new ArmorData();

        data.armorItem = registerItem(key, properties -> new ArmorItem(material, ArmorItem.Type.CHESTPLATE, new Item.Properties()), props);
        data.repairItem = material.getRepairIngredient().getItems()[0].getItem();

        EzRecipeProvider.chestplateRecipeList.add(data);
        return data.armorItem;
    }
    public static ArmorItem registerChestplate(String key, ArmorMaterial material, Item.Properties props, ArmorItem previousArmor) {
        ArmorData data = new ArmorData();

        data.armorItem = registerItem(key, properties -> new ArmorItem(material, ArmorItem.Type.CHESTPLATE, new Item.Properties()), props);
        data.repairItem = material.getRepairIngredient().getItems()[0].getItem();
        data.previousArmor = previousArmor;

        EzRecipeProvider.smithingArmorRecipeList.add(data);
        return data.armorItem;
    }

    public static ArmorItem registerLeggings(String key, ArmorMaterial material, Item.Properties props) {
        ArmorData data = new ArmorData();

        data.armorItem = registerItem(key, properties -> new ArmorItem(material, ArmorItem.Type.LEGGINGS, new Item.Properties()), props);
        data.repairItem = material.getRepairIngredient().getItems()[0].getItem();

        EzRecipeProvider.leggingsRecipeList.add(data);
        return data.armorItem;
    }
    public static ArmorItem registerLeggings(String key, ArmorMaterial material, Item.Properties props, ArmorItem previousArmor) {
        ArmorData data = new ArmorData();

        data.armorItem = registerItem(key, properties -> new ArmorItem(material, ArmorItem.Type.LEGGINGS, new Item.Properties()), props);
        data.repairItem = material.getRepairIngredient().getItems()[0].getItem();
        data.previousArmor = previousArmor;

        EzRecipeProvider.smithingArmorRecipeList.add(data);
        return data.armorItem;
    }

    public static ArmorItem registerBoots(String key, ArmorMaterial material, Item.Properties props) {
        ArmorData data = new ArmorData();

        data.armorItem = registerItem(key, properties -> new ArmorItem(material, ArmorItem.Type.BOOTS, new Item.Properties()), props);
        data.repairItem = material.getRepairIngredient().getItems()[0].getItem();

        EzRecipeProvider.bootsRecipeList.add(data);
        return data.armorItem;
    }
    public static ArmorItem registerBoots(String key, ArmorMaterial material, Item.Properties props, ArmorItem previousArmor) {
        ArmorData data = new ArmorData();

        data.armorItem = registerItem(key, properties -> new ArmorItem(material, ArmorItem.Type.BOOTS, new Item.Properties()), props);
        data.repairItem = material.getRepairIngredient().getItems()[0].getItem();
        data.previousArmor = previousArmor;

        EzRecipeProvider.smithingArmorRecipeList.add(data);
        return data.armorItem;
    }

    // Armor Sets
    public static ArmorSet registerArmorSet(String key, ArmorMaterial material, Item.Properties props) {
        ArmorSet set = new ArmorSet();

        set.helmet = registerHelmet(key + "_helmet", material, props);
        set.chestplate = registerChestplate(key + "_chestplate", material, props);
        set.leggings = registerLeggings(key + "_leggings", material, props);
        set.boots = registerBoots(key + "_boots", material, props);

        EzItemTagProvider.trimmableArmor.add(set);
        return set;
    }
    public static ArmorSet registerArmorSet(String key, ArmorMaterial material, Item.Properties props, ArmorSet previousSet) {
        ArmorSet set = new ArmorSet();

        set.helmet = registerHelmet(key + "_helmet", material, props, previousSet.helmet);
        set.chestplate = registerChestplate(key + "_chestplate", material, props, previousSet.chestplate);
        set.leggings = registerLeggings(key + "_leggings", material, props, previousSet.leggings);
        set.boots = registerBoots(key + "_boots", material, props, previousSet.boots);

        EzItemTagProvider.trimmableArmor.add(set);
        return set;
    }

    // Utils
    public static Item getItem(String key) {
        return itemMap.get(key);
    }
}
