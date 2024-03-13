package net.distantdig.datagen;

import net.distantdig.block.EzBlocksBuilder;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class EzLootTableProvider extends FabricBlockLootTableProvider {
    public EzLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        EzBlocksBuilder.blockMap.forEach((s, block) -> dropSelf(block));
        EzBlocksBuilder.stairMap.forEach((s, block) -> dropSelf(block));
        EzBlocksBuilder.slabMap.forEach((s, block) -> dropSelf(block));
        EzBlocksBuilder.verticalSlabMap.forEach((s, block) -> dropSelf(block));
        EzBlocksBuilder.wallMap.forEach((s, block) -> dropSelf(block));
        EzBlocksBuilder.doorMap.forEach((s, block) -> createDoorTable(block));
        EzBlocksBuilder.trapDoorMap.forEach((s, block) -> dropSelf(block));
        EzBlocksBuilder.fenceMap.forEach((s, block) -> dropSelf(block));
        EzBlocksBuilder.fenceGateMap.forEach((s, block) -> dropSelf(block));
        EzBlocksBuilder.pressurePlateMap.forEach((s, block) -> dropSelf(block));
        EzBlocksBuilder.buttonMap.forEach((s, block) -> dropSelf(block));
        EzBlocksBuilder.carpetMap.forEach((s, block) -> dropSelf(block));
        EzBlocksBuilder.rotatedPillarMap.forEach((s, block) -> dropSelf(block));
        EzBlocksBuilder.leavesMap.forEach((s, block) -> dropSelf(block));
//        dropSelf(Blocks.DEEPSLATE_GOLD_ORE);
//        add(Blocks.DEEPSLATE_GOLD_ORE, createOreDrop(Blocks.DEEPSLATE_GOLD_ORE, Items.RAW_GOLD));
    }
}
