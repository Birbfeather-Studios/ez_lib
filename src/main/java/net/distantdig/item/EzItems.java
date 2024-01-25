package net.distantdig.item;

import net.distantdig.EzLib;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

public class EzItems {

    // Most things in this class serve as examples that should be removed before release
    public static final Item EZ_EXAMPLE_ITEM = registerItem("ez_example_item", new Item(new FabricItemSettings()));

    private static void addItemsToIngredientTabItemGroup(FabricItemGroupEntries entries) {
        entries.accept(EZ_EXAMPLE_ITEM);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.MOD_ID, name), item);
    }

    public static void regigisterEzItems() {
        EzLib.LOGGER.info("Registering Ez Items");

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(EzItems::addItemsToIngredientTabItemGroup);
    }
}
