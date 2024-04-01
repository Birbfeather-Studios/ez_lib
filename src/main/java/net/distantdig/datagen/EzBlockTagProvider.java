package net.distantdig.datagen;

import net.distantdig.block.EzBlockTags;
import net.distantdig.block.EzBlocksBuilder;
import net.distantdig.block.EzBlocksBuilder.EzMaterial;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class EzBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public EzBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {

        // Tool tags
        FabricTagProvider<Block>.FabricTagBuilder pickaxe = getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE);
        FabricTagProvider<Block>.FabricTagBuilder axe = getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE);
        FabricTagProvider<Block>.FabricTagBuilder shovel = getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_SHOVEL);
        FabricTagProvider<Block>.FabricTagBuilder hoe = getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_HOE);

        FabricTagProvider<Block>.FabricTagBuilder walls = getOrCreateTagBuilder(BlockTags.WALLS);
        FabricTagProvider<Block>.FabricTagBuilder fences = getOrCreateTagBuilder(BlockTags.FENCES);
        FabricTagProvider<Block>.FabricTagBuilder stairs = getOrCreateTagBuilder(BlockTags.STAIRS);
        FabricTagProvider<Block>.FabricTagBuilder slabs = getOrCreateTagBuilder(BlockTags.SLABS);
        FabricTagProvider<Block>.FabricTagBuilder buttons = getOrCreateTagBuilder(BlockTags.BUTTONS);
        FabricTagProvider<Block>.FabricTagBuilder doors = getOrCreateTagBuilder(BlockTags.DOORS); //add non-wooden door directly to this
        FabricTagProvider<Block>.FabricTagBuilder trapdoors = getOrCreateTagBuilder(BlockTags.TRAPDOORS);
        FabricTagProvider<Block>.FabricTagBuilder leaves = getOrCreateTagBuilder(BlockTags.LEAVES);
        FabricTagProvider<Block>.FabricTagBuilder pressurePlates = getOrCreateTagBuilder(BlockTags.PRESSURE_PLATES); //if not stone or wood add directly here
        FabricTagProvider<Block>.FabricTagBuilder logs = getOrCreateTagBuilder(BlockTags.LOGS); //add all non-burnable logs here
        FabricTagProvider<Block>.FabricTagBuilder burnableLogs = getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN); //add all burnable logs here, they are automatically added to logs
        FabricTagProvider<Block>.FabricTagBuilder naturalLogs = getOrCreateTagBuilder(BlockTags.OVERWORLD_NATURAL_LOGS); //same as logs that burn, idk why this exists tbh?
        FabricTagProvider<Block>.FabricTagBuilder netherStone = getOrCreateTagBuilder(BlockTags.BASE_STONE_NETHER);
        FabricTagProvider<Block>.FabricTagBuilder overworldStone = getOrCreateTagBuilder(BlockTags.BASE_STONE_OVERWORLD); //all stone like blocks in overworld
        FabricTagProvider<Block>.FabricTagBuilder stoneButtons = getOrCreateTagBuilder(BlockTags.STONE_BUTTONS); //all stone buttons are automatically added to the buttons
        FabricTagProvider<Block>.FabricTagBuilder stonePressurePlates = getOrCreateTagBuilder(BlockTags.STONE_PRESSURE_PLATES);
        FabricTagProvider<Block>.FabricTagBuilder dirt = getOrCreateTagBuilder(BlockTags.DIRT); //automatically added to all the growable and spawnable tags
        FabricTagProvider<Block>.FabricTagBuilder planks = getOrCreateTagBuilder(BlockTags.PLANKS);
        FabricTagProvider<Block>.FabricTagBuilder woodenButtons = getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS);
        FabricTagProvider<Block>.FabricTagBuilder woodenDoors = getOrCreateTagBuilder(BlockTags.WOODEN_DOORS);
        FabricTagProvider<Block>.FabricTagBuilder woodenFences = getOrCreateTagBuilder(BlockTags.WOODEN_FENCES);
        FabricTagProvider<Block>.FabricTagBuilder woodenPressurePlates = getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES);
        FabricTagProvider<Block>.FabricTagBuilder woodenSlabs = getOrCreateTagBuilder(BlockTags.WOODEN_SLABS);
        FabricTagProvider<Block>.FabricTagBuilder woodenStairs = getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS);
        FabricTagProvider<Block>.FabricTagBuilder woodenTrapdoors = getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS);
        FabricTagProvider<Block>.FabricTagBuilder woolCarpets = getOrCreateTagBuilder(BlockTags.WOOL_CARPETS);
        FabricTagProvider<Block>.FabricTagBuilder replacable = getOrCreateTagBuilder(BlockTags.REPLACEABLE);
        FabricTagProvider<Block>.FabricTagBuilder fenceGates = getOrCreateTagBuilder(BlockTags.FENCE_GATES);
        FabricTagProvider<Block>.FabricTagBuilder woodenVerticalSlabs = getOrCreateTagBuilder(EzBlockTags.WOODEN_VERTICAL_SLAB);
        FabricTagProvider<Block>.FabricTagBuilder wool = getOrCreateTagBuilder(BlockTags.WOOL);
        FabricTagProvider<Block>.FabricTagBuilder ice = getOrCreateTagBuilder(BlockTags.ICE);
        FabricTagProvider<Block>.FabricTagBuilder sand = getOrCreateTagBuilder(BlockTags.SAND);
        FabricTagProvider<Block>.FabricTagBuilder beaconBaseBlock = getOrCreateTagBuilder(BlockTags.BEACON_BASE_BLOCKS);
        FabricTagProvider<Block>.FabricTagBuilder snifferDigs = getOrCreateTagBuilder(BlockTags.SNIFFER_DIGGABLE_BLOCK);
        FabricTagProvider<Block>.FabricTagBuilder convertsToMud = getOrCreateTagBuilder(BlockTags.CONVERTABLE_TO_MUD);
        FabricTagProvider<Block>.FabricTagBuilder stoneTool = getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL);
        FabricTagProvider<Block>.FabricTagBuilder ironTool = getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL);
        FabricTagProvider<Block>.FabricTagBuilder diamondTool = getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL);
        FabricTagProvider<Block>.FabricTagBuilder sculkReplaces = getOrCreateTagBuilder(BlockTags.SCULK_REPLACEABLE);
        FabricTagProvider<Block>.FabricTagBuilder geodeInvalid = getOrCreateTagBuilder(BlockTags.GEODE_INVALID_BLOCKS);
        FabricTagProvider<Block>.FabricTagBuilder polarBearSpawn = getOrCreateTagBuilder(BlockTags.POLAR_BEARS_SPAWNABLE_ON_ALTERNATE);
        FabricTagProvider<Block>.FabricTagBuilder snowLayerCannotSurvive = getOrCreateTagBuilder(BlockTags.SNOW_LAYER_CANNOT_SURVIVE_ON);
        FabricTagProvider<Block>.FabricTagBuilder rabbitSpawn = getOrCreateTagBuilder(BlockTags.RABBITS_SPAWNABLE_ON);
        FabricTagProvider<Block>.FabricTagBuilder azaleaRoots = getOrCreateTagBuilder(BlockTags.AZALEA_ROOT_REPLACEABLE);
        FabricTagProvider<Block>.FabricTagBuilder lushGround = getOrCreateTagBuilder(BlockTags.LUSH_GROUND_REPLACEABLE);
        FabricTagProvider<Block>.FabricTagBuilder enderMan = getOrCreateTagBuilder(BlockTags.ENDERMAN_HOLDABLE);
        FabricTagProvider<Block>.FabricTagBuilder snapsGoatHorn = getOrCreateTagBuilder(BlockTags.SNAPS_GOAT_HORN);
        FabricTagProvider<Block>.FabricTagBuilder goatsSpawnableOn = getOrCreateTagBuilder(BlockTags.GOATS_SPAWNABLE_ON);
        FabricTagProvider<Block>.FabricTagBuilder stoneOreReplacable = getOrCreateTagBuilder(BlockTags.STONE_ORE_REPLACEABLES);
        //FabricTagProvider<Block>.FabricTagBuilder = getOrCreateTagBuilder(BlockTags.);
        //FabricTagProvider<Block>.FabricTagBuilder = getOrCreateTagBuilder(BlockTags.);
        //FabricTagProvider<Block>.FabricTagBuilder = getOrCreateTagBuilder(BlockTags.);
        //FabricTagProvider<Block>.FabricTagBuilder = getOrCreateTagBuilder(BlockTags.);


        EzBlocksBuilder.pickaxable.forEach(pickaxe::add);
        EzBlocksBuilder.axable.forEach(axe::add);
        EzBlocksBuilder.shovolable.forEach(shovel::add);
        EzBlocksBuilder.hoeable.forEach(hoe::add);
        EzBlocksBuilder.needsIronTool.forEach(ironTool::add);
        EzBlocksBuilder.needsStoneTool.forEach(stoneTool::add);
        EzBlocksBuilder.needsDiamondTool.forEach(diamondTool::add);
        EzBlocksBuilder.blockMap.forEach((strings, block) -> {
            switch (strings.ezMaterial) {
                case ice -> {
                    ice.add(block);
                    geodeInvalid.add(block);
                    polarBearSpawn.add(block);
                    snowLayerCannotSurvive.add(block);
                }
                case sand -> {
                    sand.add(block);
                    sculkReplaces.add(block);
                    rabbitSpawn.add(block);
                    azaleaRoots.add(block);
                    enderMan.add(block);
                    lushGround.add(block);
                }
                case dirt -> {
                    dirt.add(block);
                    snifferDigs.add(block);
                    convertsToMud.add(block);
                }
                case wood -> planks.add(block);
                case stone -> {
                    overworldStone.add(block);
                    snapsGoatHorn.add(block);
                    goatsSpawnableOn.add(block);
                    stoneOreReplacable.add(block);
                }
                case wool -> wool.add(block);
                case metal -> beaconBaseBlock.add(block);
                case netherStone -> netherStone.add(block);
            }
        });
        EzBlocksBuilder.stairMap.forEach((strings, stairBlock) -> ezAdd(woodenStairs, stairs, stairBlock, EzMaterial.wood, strings));
        EzBlocksBuilder.slabMap.forEach((strings, slabBlock) -> ezAdd(woodenSlabs, slabs, slabBlock, EzMaterial.wood, strings));
        EzBlocksBuilder.verticalSlabMap.forEach((strings, ezVerticalSlabBlock) -> woodenVerticalSlabs.add(ezVerticalSlabBlock));
        EzBlocksBuilder.doorMap.forEach((strings, doorBlock) -> ezAdd(woodenDoors, doors, doorBlock, EzMaterial.wood, strings));
        EzBlocksBuilder.trapDoorMap.forEach((strings, trapDoorBlock) -> ezAdd(woodenTrapdoors, trapdoors, trapDoorBlock, EzMaterial.wood, strings));
        EzBlocksBuilder.leavesMap.forEach((strings, leavesBlock) -> leaves.add(leavesBlock));
        EzBlocksBuilder.rotatedPillarMap.forEach((strings, rotatedPillarBlock) -> {
            switch (strings.ezMaterial) {
                case ice -> ice.add(rotatedPillarBlock);
                case sand -> sand.add(rotatedPillarBlock);
                case dirt -> {
                    dirt.add(rotatedPillarBlock);
                    snifferDigs.add(rotatedPillarBlock);
                    convertsToMud.add(rotatedPillarBlock);
                }
                case wood -> {
                    if (strings.burnable) {
                        burnableLogs.add(rotatedPillarBlock);
                        naturalLogs.add(rotatedPillarBlock);
                    } else {
                        logs.add(rotatedPillarBlock);
                    }
                }
                case stone -> {
                    snapsGoatHorn.add(rotatedPillarBlock);
                }
                case wool -> wool.add(rotatedPillarBlock);
                case metal -> beaconBaseBlock.add(rotatedPillarBlock);
                case netherStone -> netherStone.add(rotatedPillarBlock);
            }
        });
        EzBlocksBuilder.woodMap.forEach((strings, rotatedPillarBlock) -> {
            if (strings.burnable) {
                burnableLogs.add(rotatedPillarBlock);
                naturalLogs.add(rotatedPillarBlock);
            } else {
                logs.add(rotatedPillarBlock);
            }
        });
        EzBlocksBuilder.wallMap.forEach((strings, wallBlock) -> walls.add(wallBlock));
        EzBlocksBuilder.buttonMap.forEach((strings, buttonBlock) -> ezAddTwo(woodenButtons, stoneButtons, buttons, buttonBlock, EzMaterial.wood, EzMaterial.stone, strings));
        EzBlocksBuilder.pressurePlateMap.forEach((strings, pressurePlateBlock) -> ezAddTwo(woodenPressurePlates, stonePressurePlates, pressurePlates, pressurePlateBlock, EzMaterial.wood, EzMaterial.stone, strings));
        EzBlocksBuilder.fenceMap.forEach((strings, fenceBlock) -> ezAdd(woodenFences, fences, fenceBlock, EzMaterial.wood, strings));
        EzBlocksBuilder.fenceGateMap.forEach((strings, fenceGateBlock) -> fenceGates.add(fenceGateBlock));
        EzBlocksBuilder.carpetMap.forEach((strings, carpetBlock) -> ezAdd(woolCarpets, replacable, carpetBlock, EzMaterial.wool, strings));

        EzBlocksBuilder.wallMap.forEach((strings, wallBlock) -> walls.add(wallBlock));
        EzBlocksBuilder.leavesMap.forEach((strings, leavesBlock) -> leaves.add(leavesBlock));
    }

    public void ezAdd(FabricTagProvider<Block>.FabricTagBuilder tag1, FabricTagProvider<Block>.FabricTagBuilder tag2, Block block, EzMaterial material, EzBlocksBuilder.Strings strings) {
        if (strings.ezMaterial == material) {
            tag1.add(block);
        } else {
            tag2.add(block);
        }
    }

    public void ezAddTwo(FabricTagProvider<Block>.FabricTagBuilder tag1, FabricTagProvider<Block>.FabricTagBuilder tag2, FabricTagProvider<Block>.FabricTagBuilder tag3, Block block, EzMaterial material, EzMaterial material2, EzBlocksBuilder.Strings strings) {
        if (strings.ezMaterial == material) {
            tag1.add(block);
        } else if (strings.ezMaterial == material2) {
            tag2.add(block);
        } else {
            tag3.add(block);
        }
    }
}
