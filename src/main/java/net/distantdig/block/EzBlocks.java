package net.distantdig.block;

import net.distantdig.item.EzItemGroups;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.distantdig.EzLib;

import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

public class EzBlocks {

    public static class BlockData {
        public Block block;
        public BlockItem blockItem;
        public String blockType;
    }

    public final static HashMap<String, BlockData> blockMap = new HashMap<>();

    // Block Registration methods
    public static <T extends Block> void registerBlock(String key, Block properties) {
        registerMain(key, Block::new, FabricBlockSettings.copyOf(properties));
    }

    public static <T extends Block> void registerSlab(String key, Block properties) {
        registerMain(key + "_slab", SlabBlock::new, FabricBlockSettings.copyOf(properties));
    }

    public static <T extends Block> void registerWall(String key, Block properties) {
        registerMain(key + "_wall", WallBlock::new, FabricBlockSettings.copyOf(properties));
    }

    public static <T extends Block> void registerCarpet(String key, Block properties) {
        registerMain(key + "_carpet", CarpetBlock::new, FabricBlockSettings.copyOf(properties));
    }

    public static <T extends Block> void registerFence(String key, Block properties) {
        registerMain(key + "_fence", FenceBlock::new, FabricBlockSettings.copyOf(properties));
    }

    public static <T extends Block> void registerPillar(String key, Block properties) {
        registerMain(key, RotatedPillarBlock::new, FabricBlockSettings.copyOf(properties)); //no additional key identification because we want to use this method for both logs and pillars right?
    }

    public static <T extends Block> void registerMain(String key, Function<BlockBehaviour.Properties, T> ctor, BlockBehaviour.Properties props) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), key), ctor.apply(props));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), key), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key, data);
        EzItemGroups.BlockGroupList.add(data.blockItem);
    }

    public static <T extends Block> void registerDoor(String key, Block properties) {
        registerWithMaterial(key + "_door", DoorBlock::new, FabricBlockSettings.copyOf(properties), BlockSetType.OAK);
    }

    public static <T extends Block> void registerTrapdoor(String key, Block properties) {
        registerWithMaterial(key + "_trapdoor", TrapDoorBlock::new, FabricBlockSettings.copyOf(properties), BlockSetType.OAK);
    }

    public static <T extends Block> void registerWithMaterial(String key, BiFunction<BlockBehaviour.Properties, BlockSetType, T> ctor, BlockBehaviour.Properties properties, BlockSetType blockSetType) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), key), ctor.apply(properties, blockSetType));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), key), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key, data);
        EzItemGroups.BlockGroupList.add(data.blockItem);
    }

    public static <T extends Block> void registerButton(String key, Block properties, Integer oneIsWoodTwoIsStoneThreeIsMetal) {
        if (oneIsWoodTwoIsStoneThreeIsMetal == 1) {
            registerCustomButton(key, properties, BlockSetType.OAK, 30, true);
        } else if (oneIsWoodTwoIsStoneThreeIsMetal == 2) {
            registerCustomButton(key, properties, BlockSetType.STONE, 20, false);
        } else if (oneIsWoodTwoIsStoneThreeIsMetal == 3) {
            registerCustomButton(key, properties, BlockSetType.GOLD, 50, false);
        }
    }

    public static <T extends Block> void registerCustomButton(String key, Block properties, BlockSetType blockSetType, Integer numberOfTicksPressed, Boolean pressedByArrows) {
        BlockData data = new BlockData();
        data.blockType = "button";
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), key + "_button"), new ButtonBlock(FabricBlockSettings.copyOf(properties), blockSetType, numberOfTicksPressed, pressedByArrows));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), key + "_button"), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key + "_button", data);
        EzItemGroups.BlockGroupList.add(data.blockItem);
    }

    public static <T extends Block> void registerPressurePlate(String key, Block properties, Integer oneIsWoodTwoIsStoneThreeIsMetal) {
        if (oneIsWoodTwoIsStoneThreeIsMetal == 1) {
            registerCustomPressurePlate(key, properties, PressurePlateBlock.Sensitivity.EVERYTHING, BlockSetType.OAK);
        } else if (oneIsWoodTwoIsStoneThreeIsMetal == 2) {
            registerCustomPressurePlate(key, properties, PressurePlateBlock.Sensitivity.MOBS, BlockSetType.STONE);
        } else if (oneIsWoodTwoIsStoneThreeIsMetal == 3) {
            registerCustomPressurePlate(key, properties, PressurePlateBlock.Sensitivity.MOBS, BlockSetType.GOLD);
        }
    }

    public static <T extends Block> void registerCustomPressurePlate(String key, Block properties, PressurePlateBlock.Sensitivity sensitivity, BlockSetType blockSetType) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), key + "_pressureplate"), new PressurePlateBlock(sensitivity, FabricBlockSettings.copyOf(properties), blockSetType));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), key + "_pressureplate"), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key + "_pressureplate", data);
        EzItemGroups.BlockGroupList.add(data.blockItem);
    }

    public static <T extends Block> void registerFenceGate(String key, Block properties) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), key + "_fence_gate"), new FenceGateBlock(FabricBlockSettings.copyOf(properties), WoodType.OAK));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), key + "_fence_gate"), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key + "_fence_gate", data);
        EzItemGroups.BlockGroupList.add(data.blockItem);
    }

    public static <T extends Block> void registerStair(String key, String block, Block properties) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), key + "_stair"), new StairBlock(getBlock(block).defaultBlockState(), FabricBlockSettings.copyOf(properties)));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), key + "_stair"), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key + "_stair", data);
        EzItemGroups.BlockGroupList.add(data.blockItem);
    }

    //Blockset Registry methods

    public static <T extends Block> void registerWoodSet(String materialName, Block copiedProperties, Boolean includeTree) {
        registerPillar(materialName + "_log", Blocks.OAK_LOG);
        registerPillar("stripped_" + materialName + "_log", Blocks.OAK_LOG);
        registerPillar(materialName + "_wood", Blocks.OAK_LOG);
        registerPillar("stripped_" + materialName + "_wood", Blocks.OAK_LOG);
        registerBlock(materialName + "_planks", copiedProperties);
        registerStair(materialName, materialName + "_planks", copiedProperties);
        registerSlab(materialName, copiedProperties);
        registerFence(materialName, copiedProperties);
        registerFenceGate(materialName, copiedProperties);
        registerDoor(materialName, copiedProperties);
        registerTrapdoor(materialName, copiedProperties);
        registerButton(materialName, copiedProperties, 1);
        registerPressurePlate(materialName, copiedProperties, 1);

        if (includeTree) {
            registerBlock(materialName + "_leaves", Blocks.OAK_LEAVES);
            registerBlock(materialName + "_sapling", Blocks.OAK_SAPLING);
        }
    }

    public static <T extends Block> void registerStoneSet(String materialName, Block copiedProperties, Boolean crackedBrick, Boolean mossyBrick, Boolean mossy) {
        registerBlock(materialName, copiedProperties);
        registerStair(materialName, materialName, copiedProperties);
        registerSlab(materialName, copiedProperties);
        registerWall(materialName, copiedProperties);
        registerBlock("cut_" + materialName, copiedProperties);
        registerBlock("polished_" + materialName, copiedProperties);
        registerStair("polished_" + materialName, "polished_" + materialName, copiedProperties);
        registerSlab("polished_" + materialName, copiedProperties);
        registerWall("polished_" + materialName, copiedProperties);
        registerBlock("chiseled_" + materialName, copiedProperties);
        registerBlock(materialName + "_brick", copiedProperties);
        registerStair(materialName + "_brick", materialName + "_brick", copiedProperties);
        registerSlab(materialName + "_brick", copiedProperties);
        registerWall(materialName + "_brick", copiedProperties);
        registerBlock(materialName + "_tiles", copiedProperties);
        registerBlock(materialName + "_pillar", copiedProperties);
        registerPressurePlate(materialName, copiedProperties, 2);
        registerButton(materialName, copiedProperties, 2);
        if (mossy == true) {
            registerBlock("mossy_" + materialName, copiedProperties);
            registerStair("mossy_" + materialName, "mossy_" + materialName, copiedProperties);
            registerSlab("mossy_" + materialName, copiedProperties);
            registerWall("mossy_" + materialName, copiedProperties);
        }
        if (mossyBrick == true) {
            registerBlock(materialName + "_mossy_bricks", copiedProperties);
        }
        if (crackedBrick == true) {
            registerBlock(materialName + "_cracked_bricks", copiedProperties);
            registerBlock(materialName + "_cracked_tiles", copiedProperties);
        }
    }

    //Methods for storing the blockmaps
    public static <T extends Block> T getBlock(String key) {
        return (T) blockMap.get(key).block;
    }

    public static void registerBlocks() {
//        EzLib.LOGGER.info("Registering Ez Blocks and BlockItems");
    }
}