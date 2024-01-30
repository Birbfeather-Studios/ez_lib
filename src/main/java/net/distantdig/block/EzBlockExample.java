package net.distantdig.block;

import net.distantdig.EzLib;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class EzBlockExample {
    public static void registerModBlocks() {
        //woodset
        EzBlocks.registerWoodSet(EzLib.MOD_ID, "mahogany", Blocks.OAK_PLANKS, false);

        //rockset
        EzBlocks.registerStoneButton(EzLib.MOD_ID, "rocky", Blocks.STONE);
        EzBlocks.registerStonePressureplate(EzLib.MOD_ID, "rocky", Blocks.STONE);

        //metalset
        EzBlocks.registerMetalButton(EzLib.MOD_ID, "tritanium", Blocks.NETHERITE_BLOCK);
        EzBlocks.registerMetalPressureplate(EzLib.MOD_ID, "tritanium", Blocks.NETHERITE_BLOCK);

        //woolset
        EzBlocks.registerBlock(EzLib.MOD_ID, "turqoise", Blocks.WHITE_WOOL);
        EzBlocks.registerCarpet(EzLib.MOD_ID, "turqoise", Blocks.WHITE_WOOL);
    }
}
