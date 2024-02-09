package net.distantdig;

import net.distantdig.block.EzBlocks;
import net.distantdig.item.EzItemGroups;
import net.distantdig.item.EzItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;

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
    public void registerModBlocks() {
        //woodset
        EzBlocks.registerWoodSet("mahogany", Blocks.OAK_PLANKS, false);

        //rockset
        EzBlocks.registerStoneSet("chalk", Blocks.STONE, true, true, true);

        //metalset

        //woolset
        EzBlocks.registerSimpleBlock("titanium_block", Blocks.GOLD_BLOCK);
        EzBlocks.registerSimplePillar("ornate_chalk_pillar", Blocks.QUARTZ_PILLAR);
        EzBlocks.registerCarpet("turqoise", Blocks.WHITE_WOOL);
    }

    @Override
    public void registerModGroup() {
        EzItemGroups.registerItemGroup(EzItems.getItem("ingot_one"));
    }
}
