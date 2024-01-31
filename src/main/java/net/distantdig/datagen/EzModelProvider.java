package net.distantdig.datagen;

import net.distantdig.item.EzItemGroups;
import net.distantdig.item.EzItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;

public class EzModelProvider extends FabricModelProvider {
    public EzModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        EzItemGroups.BlockStoneFamilyGroupList.forEach((family) -> {
            BlockModelGenerators.BlockFamilyProvider familyPool = blockStateModelGenerator.family(family.block.block);

            familyPool.stairs(family.stair.block);
            familyPool.slab(family.slab.block);
        });
//        blockStateModelGenerator.createGenericCube(Blocks.DEEPSLATE);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        EzItems.itemMap.forEach((k, i) -> itemModelGenerator.generateFlatItem(i, ModelTemplates.FLAT_ITEM));
    }
}
