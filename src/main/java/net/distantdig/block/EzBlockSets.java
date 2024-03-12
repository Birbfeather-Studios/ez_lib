package net.distantdig.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class EzBlockSets {

    public void woodSet (String material, Boolean verticalSlab) {
        Block log = Blocks.OAK_LOG;
        BlockSetType wood = BlockSetType.OAK;
        PressurePlateBlock.Sensitivity all = PressurePlateBlock.Sensitivity.EVERYTHING;
        EzBlocksBuilder woodBuilder = new  EzBlocksBuilder(material, Blocks.OAK_PLANKS, "_planks")     //planks
                .stair()                                                      //stair
                .slab()                                                       //slab
                .pillar("", "_log", log)                         //log
                .pillar("", "_wood", log)                        //wood
                .pillar("stripped_", "_log", log)                //stripped log
                .pillar("stripped_", "_wood", log)               //stripped wood
                .door(wood)                                                   //door
                .trapdoor(wood)                                               //trapdoor
                .fenceGate()                                                  //fence gate
                .fence()                                                      //fence
                .pressurePlate(wood, all)                                     //pressure plate
                .button(wood, 30, true)                  //button
                .leaves("","")    //leaves
                ;
        if (verticalSlab) {woodBuilder.verticalSlab();}                       //vertical slab
    }

    public void stoneSet (String material, Boolean verticalSlab,Boolean buttons) {
        BlockSetType stone = BlockSetType.STONE;
        PressurePlateBlock.Sensitivity mobs = PressurePlateBlock.Sensitivity.MOBS;
        EzBlocksBuilder stoneBuilder = new EzBlocksBuilder(material, Blocks.STONE, null)
                .stair()
                .slab()
                .wall();
        if (buttons) {stoneBuilder.button(stone, 20, false).pressurePlate(stone, mobs);}
        if (verticalSlab) {stoneBuilder.verticalSlab();}
    }
}
