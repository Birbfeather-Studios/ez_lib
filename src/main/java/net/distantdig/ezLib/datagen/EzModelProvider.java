package net.distantdig.ezLib.datagen;

import net.distantdig.ezLib.block.EzBlocksBuilder;
import net.distantdig.ezLib.item.EzItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;

public class EzModelProvider extends FabricModelProvider {

    public static ArrayList<Item> FlatModelList = new ArrayList<>();
    public static ArrayList<Item> HandheldModelList = new ArrayList<>();
    public static ArrayList<EzItems.ArmorData> ArmorModelList = new ArrayList<>();

    public EzModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        EzBlocksBuilder.blockMap.forEach((block, strings) -> {
//            blockStateModelGenerator.createTrivialCube(block);
            strings.family = blockStateModelGenerator.family(block);
        });
        EzBlocksBuilder.stairMap.forEach((strings, block) -> EzBlocksBuilder.blockMap.get(EzBlocksBuilder.inventoryMap.get(strings.fullblockname).getBlock()).family.stairs(block));
        EzBlocksBuilder.slabMap.forEach((strings, block) -> EzBlocksBuilder.blockMap.get(EzBlocksBuilder.inventoryMap.get(strings.fullblockname).getBlock()).family.slab(block));
        EzBlocksBuilder.verticalSlabMap.forEach((strings, block) -> {JsonGenerators.createEzVerticalSlab(strings);});
        EzBlocksBuilder.leavesMap.forEach((strings, block) -> {JsonGenerators.createEzBlock(strings);});
        EzBlocksBuilder.rotatedPillarMap.forEach((strings, block) -> {JsonGenerators.createEzColumn(strings);});
        EzBlocksBuilder.woodMap.forEach((strings, rotatedPillarBlock) -> JsonGenerators.createEzWood(strings));
        EzBlocksBuilder.doorMap.forEach((strings, block) -> {JsonGenerators.createEzDoor(strings);});
        EzBlocksBuilder.trapDoorMap.forEach((strings, block) -> {JsonGenerators.createEzTrapDoor(strings);});
        EzBlocksBuilder.buttonMap.forEach((strings, block) -> {JsonGenerators.createEzButton(strings);});
        EzBlocksBuilder.fenceMap.forEach((strings, block) -> {JsonGenerators.createEzFence(strings);});
        EzBlocksBuilder.fenceGateMap.forEach((strings, block) -> {JsonGenerators.createEzFenceGate(strings);});
        EzBlocksBuilder.wallMap.forEach((strings, block) -> {JsonGenerators.createEzWall(strings);});
        EzBlocksBuilder.pressurePlateMap.forEach((strings, block) -> {JsonGenerators.createEzPressurePlate(strings);});
        EzBlocksBuilder.carpetMap.forEach((strings, block) -> {JsonGenerators.createEzCarpet(strings);});


    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
//        EzItems.itemMap.forEach((k, i) -> itemModelGenerator.generateFlatItem(i, ModelTemplates.FLAT_ITEM));

        FlatModelList.forEach((item -> itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_ITEM)));
        HandheldModelList.forEach((item -> itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_HANDHELD_ITEM)));
        ArmorModelList.forEach((armorData -> itemModelGenerator.generateArmorTrims(armorData.armorItem)));
    }
}
