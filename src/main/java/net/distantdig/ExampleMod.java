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
        EzBlocks.registerWoodSet(EzLib.getModId(), "mahogany", Blocks.OAK_PLANKS, false);

        //rockset
        EzBlocks.registerStoneButton(EzLib.getModId(), "rocky", Blocks.STONE);
        EzBlocks.registerStonePressureplate(EzLib.getModId(), "rocky", Blocks.STONE);

        //metalset
        EzBlocks.registerMetalButton(EzLib.getModId(), "tritanium", Blocks.NETHERITE_BLOCK);
        EzBlocks.registerMetalPressureplate(EzLib.getModId(), "tritanium", Blocks.NETHERITE_BLOCK);

        //woolset
        EzBlocks.registerBlock(EzLib.getModId(), "turqoise", Blocks.WHITE_WOOL);
        EzBlocks.registerCarpet(EzLib.getModId(), "turqoise", Blocks.WHITE_WOOL);
    }

    @Override
    public void registerModGroup() {
        EzItemGroups.registerItemGroup(EzItems.getItem("ingot_one"));
    }
}
