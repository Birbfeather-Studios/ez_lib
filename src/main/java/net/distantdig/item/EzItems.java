package net.distantdig.item;

import net.distantdig.EzLib;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.function.Function;

public class EzItems {

    public final static HashMap<String, Item> itemMap = new HashMap<>();

    public static <T extends Item> void registerItem(String key, Function<Item.Properties, T> constructor, Item.Properties props) {

        Item item = Registry.register(BuiltInRegistries.ITEM,
                new ResourceLocation(EzLib.getModId(), key),
                constructor.apply(props));

        itemMap.put(key, item);

        EzItemGroups.ItemGroupList.add(item);

        // Register recipes
    }

    public static Item getItem(String key) {
        return itemMap.get(key);
    }
}
