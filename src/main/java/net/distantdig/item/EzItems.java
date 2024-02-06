package net.distantdig.item;

import net.distantdig.EzLib;
import net.distantdig.datagen.EzRecipeProvider;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
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

    // Base Register
    public static <T extends Item> T registerItem(String key, Function<Item.Properties, T> constructor, Item.Properties props) {

        T item = Registry.register(BuiltInRegistries.ITEM,
                new ResourceLocation(EzLib.getModId(), key),
                constructor.apply(props));
        itemMap.put(key, item);
        EzItemGroups.ItemGroupList.add(item);

        // Register recipes

        return item;
    }

    // Individual Tools
    public static SwordItem registerSword(String key, Tier material, Integer damage, Float attackSpeed, Item.Properties props) {
        ToolData<SwordItem> data = new ToolData<>();

        data.toolItem = registerItem(key, properties -> new SwordItem(material, damage, attackSpeed - 4.0f, new Item.Properties()), props);
        data.repairItem = material.getRepairIngredient().getItems()[0].getItem();

        EzRecipeProvider.swordRecipeList.add(data);
        return data.toolItem;
    }
    public static SwordItem registerSword(String key, Tier material, Integer damage, Float attackSpeed, Item.Properties props, SwordItem previousTool) {
        ToolData<SwordItem> data = new ToolData<>();

        data.toolItem = registerItem(key, properties -> new SwordItem(material, damage, attackSpeed - 4.0f, new Item.Properties()), props);
        data.repairItem = material.getRepairIngredient().getItems()[0].getItem();
        data.previousTool = previousTool;

        EzRecipeProvider.smithingRecipeList.add(data);
        return data.toolItem;
    }

    public static PickaxeItem registerPickaxe(String key, Tier material, Integer damage, Float attackSpeed, Item.Properties props) {
        ToolData<PickaxeItem> data = new ToolData<>();

        data.toolItem = registerItem(key, properties -> new PickaxeItem(material, damage, attackSpeed - 4.0f, new Item.Properties()), props);
        data.repairItem = material.getRepairIngredient().getItems()[0].getItem();

        EzRecipeProvider.pickaxeRecipeList.add(data);
        return data.toolItem;
    }
    public static PickaxeItem registerPickaxe(String key, Tier material, Integer damage, Float attackSpeed, Item.Properties props, PickaxeItem previousTool) {
        ToolData<PickaxeItem> data = new ToolData<>();

        data.toolItem = registerItem(key, properties -> new PickaxeItem(material, damage, attackSpeed - 4.0f, new Item.Properties()), props);
        data.repairItem = material.getRepairIngredient().getItems()[0].getItem();
        data.previousTool = previousTool;

        EzRecipeProvider.smithingRecipeList.add(data);
        return data.toolItem;
    }

    public static AxeItem registerAxe(String key, Tier material, Integer damage, Float attackSpeed, Item.Properties props) {
        ToolData<AxeItem> data = new ToolData<>();

        data.toolItem = registerItem(key, properties -> new AxeItem(material, damage, attackSpeed - 4.0f, new Item.Properties()), props);
        data.repairItem = material.getRepairIngredient().getItems()[0].getItem();

        EzRecipeProvider.axeRecipeList.add(data);
        return data.toolItem;
    }
    public static AxeItem registerAxe(String key, Tier material, Integer damage, Float attackSpeed, Item.Properties props, AxeItem previousTool) {
        ToolData<AxeItem> data = new ToolData<>();

        data.toolItem = registerItem(key, properties -> new AxeItem(material, damage, attackSpeed - 4.0f, new Item.Properties()), props);
        data.repairItem = material.getRepairIngredient().getItems()[0].getItem();
        data.previousTool = previousTool;

        EzRecipeProvider.smithingRecipeList.add(data);
        return data.toolItem;
    }

    public static ShovelItem registerShovel(String key, Tier material, Integer damage, Float attackSpeed, Item.Properties props) {
        ToolData<ShovelItem> data = new ToolData<>();

        data.toolItem = registerItem(key, properties -> new ShovelItem(material, damage, attackSpeed - 4.0f, new Item.Properties()), props);
        data.repairItem = material.getRepairIngredient().getItems()[0].getItem();

        EzRecipeProvider.shovelRecipeList.add(data);
        return data.toolItem;
    }
    public static ShovelItem registerShovel(String key, Tier material, Integer damage, Float attackSpeed, Item.Properties props, ShovelItem previousTool) {
        ToolData<ShovelItem> data = new ToolData<>();

        data.toolItem = registerItem(key, properties -> new ShovelItem(material, damage, attackSpeed - 4.0f, new Item.Properties()), props);
        data.repairItem = material.getRepairIngredient().getItems()[0].getItem();
        data.previousTool = previousTool;

        EzRecipeProvider.smithingRecipeList.add(data);
        return data.toolItem;
    }

    public static HoeItem registerHoe(String key, Tier material, Integer damage, Float attackSpeed, Item.Properties props) {
        ToolData<HoeItem> data = new ToolData<>();

        data.toolItem = registerItem(key, properties -> new HoeItem(material, damage, attackSpeed - 4.0f, new Item.Properties()), props);
        data.repairItem = material.getRepairIngredient().getItems()[0].getItem();

        EzRecipeProvider.hoeRecipeList.add(data);
        return data.toolItem;
    }
    public static HoeItem registerHoe(String key, Tier material, Integer damage, Float attackSpeed, Item.Properties props, HoeItem previousTool) {
        ToolData<HoeItem> data = new ToolData<>();

        data.toolItem = registerItem(key, properties -> new HoeItem(material, damage, attackSpeed - 4.0f, new Item.Properties()), props);
        data.repairItem = material.getRepairIngredient().getItems()[0].getItem();
        data.previousTool = previousTool;

        EzRecipeProvider.smithingRecipeList.add(data);
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

    // Utils
    public static Item getItem(String key) {
        return itemMap.get(key);
    }
}
