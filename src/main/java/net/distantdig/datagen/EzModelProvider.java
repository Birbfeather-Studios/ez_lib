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

            if (family.stair != null) {
                familyPool.stairs(family.stair.block);
            }
            if (family.slab != null) {
                familyPool.slab(family.slab.block);
            }
            if (family.wall != null) {
                familyPool.wall(family.wall.block);
            }

            if (family.cutBlock != null) {
                blockStateModelGenerator.createGenericCube(family.cutBlock.block);
            }
            if (family.chiseledBlock != null) {
                blockStateModelGenerator.createGenericCube(family.chiseledBlock.block);
            }
            if (family.tileBlock != null) {
                blockStateModelGenerator.createGenericCube(family.tileBlock.block);
            }
            if (family.pillarBlock != null) {
                blockStateModelGenerator.createGenericCube(family.pillarBlock.block);
            }
            if (family.pressurePlate != null) {
                familyPool.pressurePlate(family.pressurePlate.block);
            }
            if (family.button != null) {
                familyPool.button(family.button.block);
            }
        });

        EzItemGroups.BlockWoodFamilyGroupList.forEach((family) -> {
            BlockModelGenerators.BlockFamilyProvider familyPool = blockStateModelGenerator.family(family.block.block);

            if (family.stair != null) {
                familyPool.stairs(family.stair.block);
            }
            if (family.slab != null) {
                familyPool.slab(family.slab.block);
            }
            if (family.fence != null) {
                familyPool.fence(family.fence.block);
            }
            if (family.fenceGate != null) {
                familyPool.fenceGate(family.fenceGate.block);
            }
            if (family.log != null && family.wood != null) {
                blockStateModelGenerator.woodProvider(family.log.block).log(family.log.block).wood(family.wood.block);
            }
            if (family.strippedLog != null) {
                blockStateModelGenerator.woodProvider(family.strippedLog.block).log(family.strippedLog.block).wood(family.strippedWood.block);
            }
            if (family.pressurePlate != null) {
                familyPool.pressurePlate(family.pressurePlate.block);
            }
            if (family.button != null) {
                familyPool.button(family.button.block);
            }
            if (family.door != null) {
                blockStateModelGenerator.createDoor(family.door.block);
            }
            if (family.trapdoor != null) {
                blockStateModelGenerator.createTrapdoor(family.trapdoor.block);
            }
            if (family.leaves != null) {
                blockStateModelGenerator.createGenericCube(family.leaves.block);
            }
        });
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        EzItems.itemMap.forEach((k, i) -> itemModelGenerator.generateFlatItem(i, ModelTemplates.FLAT_ITEM));
    }
}
