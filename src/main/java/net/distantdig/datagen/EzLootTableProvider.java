package net.distantdig.datagen;

import net.distantdig.block.EzBlocksBuilder;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class EzLootTableProvider extends FabricBlockLootTableProvider {
    public EzLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        EzBlocksBuilder.blockMap.forEach((s, block) -> {if(s.ezMaterial == EzBlocksBuilder.EzMaterial.ice) {this.dropWhenSilkTouch(block);} else {dropSelf(block);}});
        EzBlocksBuilder.stairMap.forEach((s, block) -> dropSelf(block));
        EzBlocksBuilder.slabMap.forEach((s, block) -> this.add(block, this::createSlabItemTable));
        EzBlocksBuilder.verticalSlabMap.forEach((s, block) -> dropSelf(block));
        EzBlocksBuilder.wallMap.forEach((s, block) -> dropSelf(block));
        EzBlocksBuilder.doorMap.forEach((s, block) -> this.add(block, this::createDoorTable));
        EzBlocksBuilder.trapDoorMap.forEach((s, block) -> dropSelf(block));
        EzBlocksBuilder.fenceMap.forEach((s, block) -> dropSelf(block));
        EzBlocksBuilder.fenceGateMap.forEach((s, block) -> dropSelf(block));
        EzBlocksBuilder.pressurePlateMap.forEach((s, block) -> dropSelf(block));
        EzBlocksBuilder.buttonMap.forEach((s, block) -> dropSelf(block));
        EzBlocksBuilder.carpetMap.forEach((s, block) -> dropSelf(block));
        EzBlocksBuilder.rotatedPillarMap.forEach((s, block) -> dropSelf(block));
        EzBlocksBuilder.woodMap.forEach((strings, rotatedPillarBlock) -> dropSelf(rotatedPillarBlock));
        EzBlocksBuilder.leavesMap.forEach((s, block) -> this.add(block, this.createLeavesDrops(block, s.saplingBlock,0.05f, 0.0625f, 0.08333336f, 0.1f)));
//        dropSelf(Blocks.DEEPSLATE_GOLD_ORE);
//        add(Blocks.DEEPSLATE_GOLD_ORE, createOreDrop(Blocks.DEEPSLATE_GOLD_ORE, Items.RAW_GOLD));
    }
}
