package net.distantdig.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;

public class EzModelProvider extends FabricModelProvider {
    public EzModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
//        blockStateModelGenerator.createGenericCube(Blocks.DEEPSLATE);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
//        itemModelGenerator.generateFlatItem(Items.DIAMOND, ModelTemplates.FLAT_ITEM);
    }
}
