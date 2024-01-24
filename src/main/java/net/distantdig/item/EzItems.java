package net.distantdig.item;

import net.distantdig.EzLib;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EzItems {

    public static final Item EZ_EXAMPLE_ITEM = registerItem("ez_example_item", new Item(new FabricItemSettings()));

    private static void addItemsToIngredientTabItemGroup(FabricItemGroupEntries entries) {
        entries.add(EZ_EXAMPLE_ITEM);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(EzLib.MOD_ID, name), item);
    }

    public static void regigisterEzItems() {
        EzLib.LOGGER.info("Registering Ez Items");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(EzItems::addItemsToIngredientTabItemGroup);
    }
}
