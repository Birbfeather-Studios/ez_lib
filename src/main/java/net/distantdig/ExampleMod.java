package net.distantdig;

import net.distantdig.item.EzItemGroups;
import net.distantdig.item.EzItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.world.item.Item;

public class ExampleMod extends EzLib {

    public ExampleMod() {
        super("other_mod");
    }

    @Override
    public void registerModItems() {
        EzItems.registerItem("ingot_one", Item::new, new FabricItemSettings());
        EzItems.registerItem("ingot_two", Item::new, new FabricItemSettings());
        EzItems.registerItem("ingot_three", Item::new, new FabricItemSettings());
        EzItems.registerItem("ingot_four", Item::new, new FabricItemSettings());
        EzItems.registerItem("ingot_five", Item::new, new FabricItemSettings());
    }

    @Override
    public void registerModGroup() {
        EzItemGroups.registerItemGroup(EzItems.getItem("ingot_one"));
    }
}
