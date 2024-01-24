package net.distantdig.item;

import net.distantdig.EzLib;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class EzItemGroups {
    public static final ItemGroup EZ_EXAMPLE_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(EzLib.MOD_ID, "ez_example_group"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.ez_example_group"))
                    .icon(() -> new ItemStack(EzItems.EZ_EXAMPLE_ITEM)).entries(((displayContext, entries) -> {

                        entries.add(EzItems.EZ_EXAMPLE_ITEM);

                    })).build());

    public static void registerItemGroups() {
        EzLib.LOGGER.info("Registering Ez Item Groups");
    }
}
