package net.distantdig.datagen;

import com.google.gson.*;
import net.distantdig.EzLib;
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

    public static class ColumFamily {
        public Block block;
    }

    //I was so done with the way minecraft generates their blocks and blockmodels, that I decided to just hard-code my own generator for the creation of blockitem models.
    public static void itemModelGenerator(String key) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("parent", EzLib.getModId() + ":block/" + key);
        String jsondata = gson.toJson(jsonObject);
        Path projectPath = Paths.get(System.getProperty("user.dir")).getParent().getParent();
        Path extraPath = Paths.get("src" + File.separator + "main" + File.separator + "resources" + File.separator + "assets" + File.separator + EzLib.getModId() + File.separator + "models" + File.separator + "item" + File.separator + key + ".json");
        Path path = projectPath.resolve(extraPath);
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter(path.toFile());
            writer.write(jsondata);
            writer.close();
            System.out.println("Custom Model File created in: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {


        EzItemGroups.simpleBlockMap.forEach((key, data) -> {
            blockStateModelGenerator.createTrivialCube(data.block);
            itemModelGenerator(key);
        });
        EzItemGroups.simplePillarMap.forEach((key, data) -> {
            ColumFamily family = new ColumFamily();
            family.block = data.block;
            blockStateModelGenerator.woodProvider(family.block).log(family.block);
            itemModelGenerator(key);
        });

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


        EzItemGroups.BlockWoodFamilyGroupList.forEach((family) ->

        {
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
//        EzItems.itemMap.forEach((k, i) -> itemModelGenerator.generateFlatItem(i, ModelTemplates.FLAT_ITEM));

        FlatModelList.forEach((item -> itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_ITEM)));
        HandheldModelList.forEach((item -> itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_HANDHELD_ITEM)));
        ArmorModelList.forEach((armorData -> itemModelGenerator.generateArmorTrims(armorData.armorItem)));
    }
}
