package net.distantdig.block;

import net.distantdig.EzLib;
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
import java.util.function.Function;

public class EzBlocks {

    public static class BlockData{
        public Block block;
        public BlockItem blockItem;
    }

    public final static HashMap<String, BlockData> blockMap = new HashMap<>();

// Block Registration methods
    public static <T extends Block> void registerBlock(String modName, String key, Block properties) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(modName, key), new Block(FabricBlockSettings.copyOf(properties)));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(modName, key), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key, data);
    }

    public static <T extends Block> void registerStair(String modName, String key, String block, Block properties) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(modName, key + "_stair"), new StairBlock(getBlock(block).defaultBlockState(), FabricBlockSettings.copyOf(properties)));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(modName, key + "_stair"), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key, data);
    }

    public static <T extends Block> void registerSlab(String modName, String key, Block properties) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(modName, key + "_slab"), new SlabBlock(FabricBlockSettings.copyOf(properties)));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(modName, key + "_slab"), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key, data);
    }

    public static <T extends Block> void registerWall(String modName, String key, Block properties) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(modName, key + "_wall"), new WallBlock(FabricBlockSettings.copyOf(properties)));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(modName, key + "_wall"), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key, data);
    }

    public static <T extends Block> void registerCarpet(String modName, String key, Block properties) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(modName, key + "_carpet"), new CarpetBlock(FabricBlockSettings.copyOf(properties)));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(modName, key + "_carpet"), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key, data);
    }

    public static <T extends Block> void registerWoodButton (String modName, String key, Block properties) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(modName, key + "_button"), new ButtonBlock(FabricBlockSettings.copyOf(properties), BlockSetType.OAK, 30, true));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(modName, key + "_button"), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key, data);
    }

    public static <T extends Block> void registerStoneButton (String modName, String key, Block properties) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(modName, key + "_button"), new ButtonBlock(FabricBlockSettings.copyOf(properties), BlockSetType.STONE, 20, false));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(modName, key + "_button"), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key, data);
    }

    public static <T extends Block> void registerMetalButton (String modName, String key, Block properties) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(modName, key + "_button"), new ButtonBlock(FabricBlockSettings.copyOf(properties), BlockSetType.GOLD, 50, false));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(modName, key + "_button"), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key, data);
    }

    public static <T extends Block> void registerCustomButton (String modName, String key, Block properties, BlockSetType blockSetType, Integer numberOfTicksPressed, Boolean pressedByArrows) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(modName, key + "_button"), new ButtonBlock(FabricBlockSettings.copyOf(properties), blockSetType, numberOfTicksPressed, pressedByArrows));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(modName, key + "_button"), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key, data);
    }

    public static <T extends Block> void registerWoodPressureplate(String modName, String key, Block properties) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(modName, key + "_pressureplate"), new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING ,FabricBlockSettings.copyOf(properties), BlockSetType.OAK));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(modName, key + "_pressureplate"), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key, data);
    }

    public static <T extends Block> void registerStonePressureplate(String modName, String key, Block properties) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(modName, key + "_pressureplate"), new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS ,FabricBlockSettings.copyOf(properties), BlockSetType.STONE));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(modName, key + "_pressureplate"), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key, data);
    }

    public static <T extends Block> void registerMetalPressureplate(String modName, String key, Block properties) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(modName, key + "_pressureplate"), new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS ,FabricBlockSettings.copyOf(properties), BlockSetType.GOLD));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(modName, key + "_pressureplate"), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key, data);
    }

    public static <T extends Block> void registerDoor(String modName, String key, Block properties) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(modName, key + "_door"), new DoorBlock(FabricBlockSettings.copyOf(properties), BlockSetType.OAK));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(modName, key + "_door"), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key, data);
    }

    public static <T extends Block> void registerTrapdoor(String modName, String key, Block properties) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(modName, key + "_trapdoor"), new TrapDoorBlock(FabricBlockSettings.copyOf(properties), BlockSetType.OAK));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(modName, key + "_trapdoor"), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key, data);
    }

    public static <T extends Block> void registerFenceGate(String modName, String key, Block properties) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(modName, key + "_fence_gate"), new FenceGateBlock(FabricBlockSettings.copyOf(properties), WoodType.OAK));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(modName, key + "_fence_gate"), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key, data);
    }

    public static <T extends Block> void registerFence(String modName, String key, Block properties) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(modName, key + "_fence"), new FenceBlock(FabricBlockSettings.copyOf(properties)));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(modName, key + "_fence"), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key, data);
    }

    public static <T extends Block> void registerOther(String modName, String key, Function<BlockBehaviour.Properties,T> ctor, BlockBehaviour.Properties props) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(modName, key), ctor.apply(props));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(modName, key), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key, data);
    }

    public static <T extends Block> void registerPillar(String modName, String key, Block properties) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(modName, key), new RotatedPillarBlock(FabricBlockSettings.copyOf(properties)));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(modName, key), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key, data);
    }


    //Blockset Registry methods

    public static <T extends  Block> void registerWoodSet(String modName, String materialName, Block copiedProperties, Boolean includeTree) {
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
        registerWoodButton(modName, materialName, copiedProperties);
        registerWoodPressureplate(modName, materialName, copiedProperties);

        if (includeTree) {
            registerBlock(modName, materialName + "_leaves", Blocks.OAK_LEAVES);
            registerBlock(modName, materialName + "_sapling", Blocks.OAK_SAPLING);
        }
    }

    //Methods for storing the blockmaps
    public static <T extends Block> T getBlock(String key){ return (T)blockMap.get(key).block;}

    public static void registerBlocks() {
        EzLib.LOGGER.info("Registering Ez Blocks and BlockItems");
    }
}