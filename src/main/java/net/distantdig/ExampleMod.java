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
        EzBlocks.registerButton( "rocky", Blocks.STONE, 2);
        EzBlocks.registerPressurePlate( "rocky", Blocks.STONE, 2);

        //metalset
         EzBlocks.registerButton("tritanium", Blocks.NETHERITE_BLOCK, 3);
         EzBlocks.registerPressurePlate("tritanium", Blocks.NETHERITE_BLOCK, 3);

        //woolset
        EzBlocks.registerBlock( "turqoise", Blocks.WHITE_WOOL);
        EzBlocks.registerCarpet( "turqoise", Blocks.WHITE_WOOL);
    }

    @Override
    public void registerModGroup() {
        EzItemGroups.registerItemGroup(EzItems.getItem("ingot_one"));
    }
}
