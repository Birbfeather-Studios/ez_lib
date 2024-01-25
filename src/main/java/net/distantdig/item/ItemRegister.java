package net.distantdig.item;

import net.distantdig.EzLib;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.function.Function;

public class ItemRegister {

    public final static HashMap<String, Item> itemMap = new HashMap<>();

    public static <T extends Item> void registerItem(String key, Function<Item.Properties, T> constructor, Item.Properties props) {

        Item data = Registry.register(BuiltInRegistries.ITEM,
                new ResourceLocation(EzLib.MOD_ID, key), //Replace EzLib.MOD_ID with dynamic mod id
                constructor.apply(props));

        itemMap.put(key, data);

        // Add to Item Group goes here
    }
}
