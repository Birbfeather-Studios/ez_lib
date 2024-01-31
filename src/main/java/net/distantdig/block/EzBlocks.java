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

import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

public class EzBlocks {

    public static class BlockData {
        public Block block;
        public BlockItem blockItem;
    }

    public final static HashMap<String, BlockData> blockMap = new HashMap<>();

    // Block Registration methods
    public static <T extends Block> void registerBlock(String modName, String key, Block properties) {
        registerMain(modName, key, Block::new, FabricBlockSettings.copyOf(properties));
    }

    public static <T extends Block> void registerSlab(String modName, String key, Block properties) {
        registerMain(modName, key + "_slab", SlabBlock::new, FabricBlockSettings.copyOf(properties));
    }

    public static <T extends Block> void registerWall(String modName, String key, Block properties) {
        registerMain(modName, key + "_wall", WallBlock::new, FabricBlockSettings.copyOf(properties));
    }

    public static <T extends Block> void registerCarpet(String modName, String key, Block properties) {
        registerMain(modName, key + "_carpet", CarpetBlock::new, FabricBlockSettings.copyOf(properties));
    }

    public static <T extends Block> void registerFence(String modName, String key, Block properties) {
        registerMain(modName, key + "_fence", FenceBlock::new, FabricBlockSettings.copyOf(properties));
    }

    public static <T extends Block> void registerPillar(String modName, String key, Block properties) {
        registerMain(modName, key, RotatedPillarBlock::new, FabricBlockSettings.copyOf(properties)); //no additional key identification because we want to use this method for both logs and pillars right?
    }

    public static <T extends Block> void registerMain(String modName, String key, Function<BlockBehaviour.Properties, T> ctor, BlockBehaviour.Properties props) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(modName, key), ctor.apply(props));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(modName, key), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key, data);
        EzItemGroups.BlockGroupList.add(data.blockItem);
    }

    public static <T extends Block> void registerDoor(String modName, String key, Block properties) {
        registerWithMaterial(modName, key + "_door", DoorBlock::new, FabricBlockSettings.copyOf(properties), BlockSetType.OAK);
    }

    public static <T extends Block> void registerTrapdoor(String modName, String key, Block properties) {
        registerWithMaterial(modName, key + "_trapdoor", TrapDoorBlock::new, FabricBlockSettings.copyOf(properties), BlockSetType.OAK);
    }

    public static <T extends Block> void registerWithMaterial(String modName, String key, BiFunction<BlockBehaviour.Properties, BlockSetType, T> ctor, BlockBehaviour.Properties properties, BlockSetType blockSetType) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(modName, key), ctor.apply(properties, blockSetType));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(modName, key), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key, data);
        EzItemGroups.BlockGroupList.add(data.blockItem);
    }

    public static <T extends Block> void registerButton(String modName, String key, Block properties, Integer oneIsWoodTwoIsStoneThreeIsMetal) {
        if (oneIsWoodTwoIsStoneThreeIsMetal == 1) {
            registerCustomButton(modName, key, properties, BlockSetType.OAK, 30, true);
        } else if (oneIsWoodTwoIsStoneThreeIsMetal == 2) {
            registerCustomButton(modName, key, properties, BlockSetType.STONE, 20, false);
        } else if (oneIsWoodTwoIsStoneThreeIsMetal == 3) {
            registerCustomButton(modName, key, properties, BlockSetType.GOLD, 50, false);
        }
    }

    public static <T extends Block> void registerCustomButton(String modName, String key, Block properties, BlockSetType blockSetType, Integer numberOfTicksPressed, Boolean pressedByArrows) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(modName, key + "_button"), new ButtonBlock(FabricBlockSettings.copyOf(properties), blockSetType, numberOfTicksPressed, pressedByArrows));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(modName, key + "_button"), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key, data);
        EzItemGroups.BlockGroupList.add(data.blockItem);
    }

    public static <T extends Block> void registerPressurePlate(String modName, String key, Block properties, Integer oneIsWoodTwoIsStoneThreeIsMetal) {
        if (oneIsWoodTwoIsStoneThreeIsMetal == 1) {
            registerCustomPressurePlate(modName, key, properties, PressurePlateBlock.Sensitivity.EVERYTHING, BlockSetType.OAK);
        } else if (oneIsWoodTwoIsStoneThreeIsMetal == 2) {
            registerCustomPressurePlate(modName, key, properties, PressurePlateBlock.Sensitivity.MOBS, BlockSetType.STONE);
        } else if (oneIsWoodTwoIsStoneThreeIsMetal == 3) {
            registerCustomPressurePlate(modName, key, properties, PressurePlateBlock.Sensitivity.MOBS, BlockSetType.GOLD);
        }
    }

    public static <T extends Block> void registerCustomPressurePlate(String modName, String key, Block properties, PressurePlateBlock.Sensitivity sensitivity, BlockSetType blockSetType) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(modName, key + "_pressureplate"), new PressurePlateBlock(sensitivity, FabricBlockSettings.copyOf(properties), blockSetType));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(modName, key + "_pressureplate"), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key, data);
        EzItemGroups.BlockGroupList.add(data.blockItem);
    }

    public static <T extends Block> void registerFenceGate(String modName, String key, Block properties) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(modName, key + "_fence_gate"), new FenceGateBlock(FabricBlockSettings.copyOf(properties), WoodType.OAK));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(modName, key + "_fence_gate"), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key, data);
        EzItemGroups.BlockGroupList.add(data.blockItem);
    }

    public static <T extends Block> void registerStair(String modName, String key, String block, Block properties) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(modName, key + "_stair"), new StairBlock(getBlock(block).defaultBlockState(), FabricBlockSettings.copyOf(properties)));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(modName, key + "_stair"), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key, data);
        EzItemGroups.BlockGroupList.add(data.blockItem);
    }

    //Blockset Registry methods

    public static <T extends Block> void registerWoodSet(String modName, String materialName, Block copiedProperties, Boolean includeTree) {
        registerPillar(modName, materialName + "_log", Blocks.OAK_LOG);
        registerPillar(modName, "stripped_" + materialName + "_log", Blocks.OAK_LOG);
        registerPillar(modName, materialName + "_wood", Blocks.OAK_LOG);
        registerPillar(modName, "stripped_" + materialName + "_wood", Blocks.OAK_LOG);
        registerBlock(modName, materialName + "planks", copiedProperties);
        registerStair(modName, materialName, materialName + "planks", copiedProperties);
        registerSlab(modName, materialName, copiedProperties);
        registerFence(modName, materialName, copiedProperties);
        registerFenceGate(modName, materialName, copiedProperties);
        registerDoor(modName, materialName, copiedProperties);
        registerTrapdoor(modName, materialName, copiedProperties);
        registerButton(modName, materialName, copiedProperties, 1);
        registerPressurePlate(modName, materialName, copiedProperties, 1);

        if (includeTree) {
            registerBlock(modName, materialName + "_leaves", Blocks.OAK_LEAVES);
            registerBlock(modName, materialName + "_sapling", Blocks.OAK_SAPLING);
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