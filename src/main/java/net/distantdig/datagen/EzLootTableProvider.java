package net.distantdig.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class EzLootTableProvider extends FabricBlockLootTableProvider {
    public EzLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
//        dropSelf(Blocks.DEEPSLATE_GOLD_ORE);
//        add(Blocks.DEEPSLATE_GOLD_ORE, createOreDrop(Blocks.DEEPSLATE_GOLD_ORE, Items.RAW_GOLD));
    }
}
