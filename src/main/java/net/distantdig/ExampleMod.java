package net.distantdig;

import net.distantdig.block.EzBlocks;
import net.distantdig.item.EzItemGroups;
import net.distantdig.item.EzItems;
import net.distantdig.item.EzMaterials;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;

public class ExampleMod extends EzLib {

    public ExampleMod() {
        super("other_mod");
    }

    @Override
    public void registerModItems() {
        Item INGOT_ONE = EzItems.registerItem("ingot_one", Item::new, new FabricItemSettings());
        Item INGOT_TWO = EzItems.registerItem("ingot_two", Item::new, new FabricItemSettings());
        Item INGOT_THREE = EzItems.registerItem("ingot_three", Item::new, new FabricItemSettings());
        Item INGOT_FOUR = EzItems.registerItem("ingot_four", Item::new, new FabricItemSettings());
        Item INGOT_FIVE = EzItems.registerItem("ingot_five", Item::new, new FabricItemSettings());

        EzItems.ToolSet IRON_2 = EzItems.registerToolSet("iron2", EzMaterials.TEST_MATERIAL1, 3, 1.6f, new FabricItemSettings());
        EzItems.ToolSet IRON_3 = EzItems.registerToolSet("iron3", EzMaterials.TEST_MATERIAL2, 3, 1.6f, new FabricItemSettings(), IRON_2);
        EzItems.ToolSet IRON_4 = EzItems.registerToolSet("iron4", EzMaterials.TEST_MATERIAL3, 3, 1.6f, new FabricItemSettings(), IRON_3);
    }

    @Override
    public void registerModBlocks() {
        //woodset
        EzBlocks.registerWoodSet("mahogany", Blocks.OAK_PLANKS, false);

        //rockset
        EzBlocks.registerStoneSet("rocky", Blocks.STONE, true, true, true);

        //metalset
        EzBlocks.registerButton("tritanium", Blocks.NETHERITE_BLOCK, 3);
        EzBlocks.registerPressurePlate("tritanium", Blocks.NETHERITE_BLOCK, 3);

        //woolset
        EzBlocks.registerBlock("turqoise_wool", Blocks.WHITE_WOOL);
        EzBlocks.registerCarpet("turqoise", Blocks.WHITE_WOOL);
    }

    @Override
    public void registerModGroup() {
        EzItemGroups.registerItemGroup(EzItems.getItem("ingot_one"));
    }
}
