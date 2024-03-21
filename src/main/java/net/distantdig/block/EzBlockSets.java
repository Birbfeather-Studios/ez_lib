package net.distantdig.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class EzBlockSets {

    public void woodSet(String material, Boolean verticalSlab) {
        Block log = Blocks.OAK_LOG;
        BlockSetType wood = BlockSetType.OAK;
        PressurePlateBlock.Sensitivity all = PressurePlateBlock.Sensitivity.EVERYTHING;
        EzBlocksBuilder woodBuilder = new EzBlocksBuilder(material, Blocks.OAK_PLANKS, "_planks", EzBlocksBuilder.EzMaterial.wood)     //planks
                .stair()                                                      //stair
                .slab()//slab
                .logs("", "_log", log, true)
                .wood(material + "_log", "stripped_" + material + "_log", true)
                .door(wood, Blocks.OAK_DOOR)                                  //door
                .trapdoor(wood, Blocks.OAK_TRAPDOOR)                          //trapdoor
                .fenceGate()                                                  //fence gate
                .fence()                                                      //fence
                .pressurePlate(wood, all)                                     //pressure plate
                .button(wood, 30, true)                  //button
                .verticalSlab()                                               //vertical slab
                .axe();
        woodBuilder.leaves("", "");    //leaves
    }

    public void simpleStoneSet(String material) {
        EzBlocksBuilder stoneBuilder = new EzBlocksBuilder(material, Blocks.STONE, null, EzBlocksBuilder.EzMaterial.stone)
                .stair()
                .slab()
                .wall()
                .verticalSlab()
                .pickaxe();
    }

    public void advancedStoneSet(String material) {
        BlockSetType stone = BlockSetType.STONE;
        PressurePlateBlock.Sensitivity mobs = PressurePlateBlock.Sensitivity.MOBS;
        EzBlocksBuilder advancedStoneBuilder = new EzBlocksBuilder(material, Blocks.STONE, null, EzBlocksBuilder.EzMaterial.stone)
                .stair()
                .slab()
                .wall()
                .verticalSlab()
                .pressurePlate(stone, mobs)
                .button(stone, 20, false)
                .pickaxe();
    }
}
