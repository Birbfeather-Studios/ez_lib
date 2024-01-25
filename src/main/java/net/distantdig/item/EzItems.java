package net.distantdig.item;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.function.Function;

public class EzItems {

    public final static HashMap<String, Item> itemMap = new HashMap<>();

    public static <T extends Item> void registerItem(String modId, String key, Function<Item.Properties, T> constructor, Item.Properties props) {

        Item data = Registry.register(BuiltInRegistries.ITEM,
                new ResourceLocation(modId, key),
                constructor.apply(props));

        itemMap.put(key, data);

        // Add to Item Group goes here

        // Register recipes

        // Generate the models
    }
}
