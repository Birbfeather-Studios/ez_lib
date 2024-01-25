package net.distantdig.item;

import net.distantdig.EzLib;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class EzItemGroups {

    //Example Item Group that should be removed before release
    public static final CreativeModeTab EZ_EXAMPLE_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            new ResourceLocation(EzLib.MOD_ID, "ez_example_group"),
            FabricItemGroup.builder().title(Component.translatable("itemgroup.ez_example_group"))
                    .icon(() -> new ItemStack(EzItems.EZ_EXAMPLE_ITEM)).displayItems(((displayContext, entries) -> {

                        entries.accept(EzItems.EZ_EXAMPLE_ITEM);

                    })).build());

    public static void registerItemGroups() {
        EzLib.LOGGER.info("Registering Ez Item Groups");
    }
}
