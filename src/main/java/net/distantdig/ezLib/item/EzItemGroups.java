package net.distantdig.ezLib.item;

import net.distantdig.ezLib.EzLib;
import net.distantdig.ezLib.block.EzBlocksBuilder;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;

public class EzItemGroups {

    public static ArrayList<ItemLike> ItemGroupList = new ArrayList<>();
    public static ArrayList<ItemLike> BlockGroupList = new ArrayList<>();


    public static void registerItemGroup(Item icon) {

        String modId = EzLib.getModId();
        BlockGroupList.addAll(EzBlocksBuilder.inventoryMap.values());

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                new ResourceLocation(modId, modId + "group"),
                FabricItemGroup.builder().title(Component.translatable("itemgroup." + modId))
                        .icon(() -> new ItemStack(icon)).displayItems(((displayContext, entries) -> {
                            ItemGroupList.forEach(entries::accept);
                            BlockGroupList.forEach(entries::accept);
                        })).build());
    }
}
