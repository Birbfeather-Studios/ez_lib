package net.distantdig.datagen;

import com.google.gson.*;
import net.distantdig.EzLib;
import net.distantdig.block.EzBlocksBuilder;
import net.distantdig.item.EzItemGroups;
import net.distantdig.item.EzItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.world.level.block.Block;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.minecraft.world.item.*;

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
        EzBlocksBuilder.blockMap.forEach((name, block) -> {JsonGenerators.createEzBlock(name);});
        EzBlocksBuilder.stairMap.forEach((name, block) -> {JsonGenerators.createEzStair(name);});
        EzBlocksBuilder.slabMap.forEach((strings, block) -> {JsonGenerators.createEzSlab(strings);});
        EzBlocksBuilder.verticalSlabMap.forEach((strings, block) -> {JsonGenerators.createEzVerticalSlab(strings);});
        EzBlocksBuilder.leavesMap.forEach((name, block) -> {JsonGenerators.createEzBlock(name);});
        EzBlocksBuilder.rotatedPillarMap.forEach((name, block) -> {JsonGenerators.createEzColumn(name);});
        EzBlocksBuilder.doorMap.forEach((name, block) -> {JsonGenerators.createEzDoor(name);});
        EzBlocksBuilder.trapDoorMap.forEach((name, block) -> {JsonGenerators.createEzTrapDoor(name);});
        EzBlocksBuilder.buttonMap.forEach((strings, block) -> {JsonGenerators.createEzButton(strings);});
        EzBlocksBuilder.fenceMap.forEach((name, block) -> {JsonGenerators.createEzFence(name);});
        EzBlocksBuilder.fenceGateMap.forEach((name, block) -> {JsonGenerators.createEzFenceGate(name);});
        EzBlocksBuilder.wallMap.forEach((name, block) -> {JsonGenerators.createEzWall(name);});
        EzBlocksBuilder.pressurePlateMap.forEach((name, block) -> {JsonGenerators.createEzPressurePlate(name);});
        EzBlocksBuilder.carpetMap.forEach((name, block) -> {JsonGenerators.createEzCarpet(name);});
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
//        EzItems.itemMap.forEach((k, i) -> itemModelGenerator.generateFlatItem(i, ModelTemplates.FLAT_ITEM));

        FlatModelList.forEach((item -> itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_ITEM)));
        HandheldModelList.forEach((item -> itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_HANDHELD_ITEM)));
        ArmorModelList.forEach((armorData -> itemModelGenerator.generateArmorTrims(armorData.armorItem)));
    }
}
