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
    }

    public static class BlockStoneFamily {
        public BlockData block;
        public BlockData slab;
        public BlockData stair;
        public BlockData wall;
        public BlockData cutBlock;
        public BlockData chiseledBlock;
        public BlockData tileBlock;
        public BlockData pillarBlock;
        public BlockData button;
        public BlockData pressurePlate;
    }

    public static class BlockWoodFamily {
    }

    public final static HashMap<String, BlockData> blockMap = new HashMap<>();

    // Block Registration methods
    public static <T extends Block> BlockData registerBlock(String key, Block properties) {
        return registerMain(key, Block::new, FabricBlockSettings.copyOf(properties));
    }

    public static <T extends Block> BlockData registerSlab(String key, Block properties) {
        return registerMain(key + "_slab", SlabBlock::new, FabricBlockSettings.copyOf(properties));
    }

    public static <T extends Block> BlockData registerWall(String key, Block properties) {
        return registerMain(key + "_wall", WallBlock::new, FabricBlockSettings.copyOf(properties));
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

    public static <T extends Block> BlockData registerMain(String key, Function<BlockBehaviour.Properties, T> ctor, BlockBehaviour.Properties props) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), key), ctor.apply(props));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), key), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key, data);
        EzItemGroups.BlockGroupList.add(data.blockItem);

        return data;
    }

    public static <T extends Block> void registerDoor(String key, Block properties) {
        registerWithMaterial(key + "_door", DoorBlock::new, FabricBlockSettings.copyOf(properties), BlockSetType.OAK);
    }

    public static <T extends Block> void registerTrapdoor(String key, Block properties) {
        registerWithMaterial(key + "_trap_door", TrapDoorBlock::new, FabricBlockSettings.copyOf(properties), BlockSetType.OAK);
    }

    public static <T extends Block> void registerWithMaterial(String key, BiFunction<BlockBehaviour.Properties, BlockSetType, T> ctor, BlockBehaviour.Properties properties, BlockSetType blockSetType) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), key), ctor.apply(properties, blockSetType));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), key), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key, data);
        EzItemGroups.BlockGroupList.add(data.blockItem);
    }

    public static <T extends Block> BlockData registerButton(String key, Block properties, Integer oneIsWoodTwoIsStoneThreeIsMetal) {
        if (oneIsWoodTwoIsStoneThreeIsMetal == 1) {
            return registerCustomButton(key, properties, BlockSetType.OAK, 30, true);
        } else if (oneIsWoodTwoIsStoneThreeIsMetal == 2) {
            return registerCustomButton(key, properties, BlockSetType.STONE, 20, false);
        } else if (oneIsWoodTwoIsStoneThreeIsMetal == 3) {
            return registerCustomButton(key, properties, BlockSetType.GOLD, 50, false);
        }
        return null;
    }

    public static <T extends Block> BlockData registerCustomButton(String key, Block properties, BlockSetType blockSetType, Integer numberOfTicksPressed, Boolean pressedByArrows) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), key + "_button"), new ButtonBlock(FabricBlockSettings.copyOf(properties), blockSetType, numberOfTicksPressed, pressedByArrows));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), key + "_button"), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key + "_button", data);
        EzItemGroups.BlockGroupList.add(data.blockItem);

        return data;
    }

    public static <T extends Block> BlockData registerPressurePlate(String key, Block properties, Integer oneIsWoodTwoIsStoneThreeIsMetal) {
        if (oneIsWoodTwoIsStoneThreeIsMetal == 1) {
            return registerCustomPressurePlate(key, properties, PressurePlateBlock.Sensitivity.EVERYTHING, BlockSetType.OAK);
        } else if (oneIsWoodTwoIsStoneThreeIsMetal == 2) {
            return registerCustomPressurePlate(key, properties, PressurePlateBlock.Sensitivity.MOBS, BlockSetType.STONE);
        } else if (oneIsWoodTwoIsStoneThreeIsMetal == 3) {
            return registerCustomPressurePlate(key, properties, PressurePlateBlock.Sensitivity.MOBS, BlockSetType.GOLD);
        }
        return null;
    }

    public static <T extends Block> BlockData registerCustomPressurePlate(String key, Block properties, PressurePlateBlock.Sensitivity sensitivity, BlockSetType blockSetType) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), key + "_pressure_plate"), new PressurePlateBlock(sensitivity, FabricBlockSettings.copyOf(properties), blockSetType));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), key + "_pressure_plate"), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key + "_pressure_plate", data);
        EzItemGroups.BlockGroupList.add(data.blockItem);

        return data;
    }

    public static <T extends Block> void registerFenceGate(String key, Block properties) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), key + "_fence_gate"), new FenceGateBlock(FabricBlockSettings.copyOf(properties), WoodType.OAK));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), key + "_fence_gate"), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key + "_fence_gate", data);
        EzItemGroups.BlockGroupList.add(data.blockItem);
    }

    public static <T extends Block> BlockData registerStair(String key, String block, Block properties) {
        BlockData data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), key + "_stairs"), new StairBlock(getBlock(block).defaultBlockState(), FabricBlockSettings.copyOf(properties)));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), key + "_stairs"), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(key + "_stairs", data);
        EzItemGroups.BlockGroupList.add(data.blockItem);

        return data;
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
        // Main Set
        BlockStoneFamily family = new BlockStoneFamily();

        family.block = registerBlock(materialName, copiedProperties);
        family.stair = registerStair(materialName, materialName, copiedProperties);
        family.slab = registerSlab(materialName, copiedProperties);
        family.wall = registerWall(materialName, copiedProperties);
        family.cutBlock = registerBlock("cut_" + materialName, copiedProperties);
        family.chiseledBlock = registerBlock("chiseled_" + materialName, copiedProperties);
        family.tileBlock = registerBlock(materialName + "_tiles", copiedProperties);
        family.pillarBlock = registerBlock(materialName + "_pillar", copiedProperties);
        family.pressurePlate = registerPressurePlate(materialName, copiedProperties, 2);
        family.button = registerButton(materialName, copiedProperties, 2);

        EzItemGroups.BlockStoneFamilyGroupList.add(family);

        // Polished Set
        BlockStoneFamily polishedFamily = new BlockStoneFamily();

        polishedFamily.block = registerBlock("polished_" + materialName, copiedProperties);
        polishedFamily.stair = registerStair("polished_" + materialName, "polished_" + materialName, copiedProperties);
        polishedFamily.slab = registerSlab("polished_" + materialName, copiedProperties);
        polishedFamily.wall = registerWall("polished_" + materialName, copiedProperties);

        EzItemGroups.BlockStoneFamilyGroupList.add(polishedFamily);

        // Brick Set
        BlockStoneFamily brickFamily = new BlockStoneFamily();

        brickFamily.block = registerBlock(materialName + "_brick", copiedProperties);
        brickFamily.stair = registerStair(materialName + "_brick", materialName + "_brick", copiedProperties);
        brickFamily.slab = registerSlab(materialName + "_brick", copiedProperties);
        brickFamily.wall = registerWall(materialName + "_brick", copiedProperties);

        EzItemGroups.BlockStoneFamilyGroupList.add(brickFamily);


        // Mossy Set
        if (mossy) {
            BlockStoneFamily mossyFamily = new BlockStoneFamily();

            mossyFamily.block = registerBlock("mossy_" + materialName, copiedProperties);
            mossyFamily.stair = registerStair("mossy_" + materialName, "mossy_" + materialName, copiedProperties);
            mossyFamily.slab = registerSlab("mossy_" + materialName, copiedProperties);
            mossyFamily.wall = registerWall("mossy_" + materialName, copiedProperties);

            EzItemGroups.BlockStoneFamilyGroupList.add(mossyFamily);
        }

        // Mossy Brick Set
        if (mossyBrick) {
            BlockStoneFamily mossyBrickFamily = new BlockStoneFamily();

            mossyBrickFamily.block = registerBlock("mossy_" + materialName + "_bricks", copiedProperties);
            mossyBrickFamily.stair = registerStair("mossy_" + materialName + "_brick", "mossy_" + materialName + "_bricks", copiedProperties);
            mossyBrickFamily.slab = registerSlab("mossy_" + materialName + "_brick", copiedProperties);
            mossyBrickFamily.wall = registerWall("mossy_" + materialName + "_brick", copiedProperties);

            EzItemGroups.BlockStoneFamilyGroupList.add(mossyBrickFamily);
        }

        // Cracked Brick Set
        if (crackedBrick) {
            BlockStoneFamily crackedBrickFamily = new BlockStoneFamily();

            crackedBrickFamily.block = registerBlock(materialName + "_cracked_bricks", copiedProperties);
            crackedBrickFamily.tileBlock = registerBlock(materialName + "_cracked_tiles", copiedProperties);

            EzItemGroups.BlockStoneFamilyGroupList.add(crackedBrickFamily);
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