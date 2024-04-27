package net.distantdig.ezLib.datagen;

import net.distantdig.ezLib.block.EzBlocksBuilder;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class EzLootTableProvider extends FabricBlockLootTableProvider {
    public EzLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        EzBlocksBuilder.blockMap.forEach((block, s) -> {if(s.ezMaterial == EzBlocksBuilder.EzMaterial.ice) {this.dropWhenSilkTouch(block);} else {dropSelf(block);}});
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
        EzBlocksBuilder.oreMap.forEach((s, oreData) -> {
            if (oreData.dropItem != null) {
                add(oreData.oreBlock, oreDropRate(oreData.oreBlock, oreData.dropItem, oreData.minDrops, oreData.maxDrops));
            } else {
                dropSelf(oreData.oreBlock);
            }
        });
//        dropSelf(Blocks.DEEPSLATE_GOLD_ORE);
//        add(Blocks.DEEPSLATE_GOLD_ORE, createOreDrop(Blocks.DEEPSLATE_GOLD_ORE, Items.RAW_GOLD));
    }

    private LootTable.Builder oreDropRate(Block block, Item item, int minDrop, int maxDrop) {
        return createSilkTouchDispatchTable(block, this.applyExplosionDecay(block,
                LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between((float)minDrop, (float)maxDrop)))
                        .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }
}
