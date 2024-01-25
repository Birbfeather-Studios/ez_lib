package net.distantdig;

import net.distantdig.item.EzItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.world.item.Item;

public class ExampleMod extends EzLib {

    public static String MOD_ID = "other_mod";

    @Override
    public String getModId() {
        return MOD_ID;
    }

    @Override
    public void registerModItems() {
        EzItems.registerItem(MOD_ID, "ingot", Item::new, new FabricItemSettings());
    }
}
