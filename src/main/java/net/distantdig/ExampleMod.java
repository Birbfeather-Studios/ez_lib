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
        EzItems.registerItem("ingot", Item::new, new FabricItemSettings());
        EzItems.registerItem("ingot2", Item::new, new FabricItemSettings());
        EzItems.registerItem("ingot3", Item::new, new FabricItemSettings());
        EzItems.registerItem("ingot4", Item::new, new FabricItemSettings());
        EzItems.registerItem("ingot5", Item::new, new FabricItemSettings());
    }

    @Override
    public void registerModGroup() {
        EzItemGroups.registerItemGroup(EzItems.getItem("ingot"));
    }
}
