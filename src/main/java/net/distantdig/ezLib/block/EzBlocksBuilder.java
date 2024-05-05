package net.distantdig.ezLib.block;


import net.distantdig.ezLib.EzLib;
import net.distantdig.ezLib.datagen.EzRecipeProvider;
import net.distantdig.ezLib.world.EzConfiguredFeatures;
import net.distantdig.ezLib.world.EzPlacedFeatures;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static net.distantdig.ezLib.datagen.EzRecipeProvider.*;
import static net.distantdig.ezLib.item.EzItemGroups.BlockGroupList;

public class EzBlocksBuilder {

    private final String name;
    private final Block blockProperties;
    private final String name1;
    private final EzMaterial ezMaterial;
    private TagKey<Item> tagKey = null;
    private boolean hasWall;
    private BlockData data;
    public static RuleTest stoneReplacables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
    public static RuleTest deepslateReplacables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
    public static RuleTest netherReplacables = new TagMatchTest(BlockTags.BASE_STONE_NETHER);
    public static RuleTest endReplacables = new BlockMatchTest(Blocks.END_STONE);

    public static class FamilyData {
        public Block parentBlock;
        public Block stair;
        public Block slab;
        public Block button;
        public Block pressurePlate;
        public Block fence;
        public Block fenceGate;
        public Block wall;
        public Block door;
        public Block trapdoor;
    }

    public static class BlockData {
        public Block block;
        public BlockItem blockItem;
        public StairBlock stairBlock;
        public BlockItem stairItem;
        public SlabBlock slabBlock;
        public BlockItem slabItem;
        public EzVerticalSlabBlock verticalSlabBlock;
        public BlockItem verticalSlabItem;
        public HashMap<Block, BlockItem> extraBlocks = new HashMap<>();
        public HashMap<RotatedPillarBlock, BlockItem> extraColumns = new HashMap<>();
        public DoorBlock doorBlock;
        public BlockItem doorItem;
        public TrapDoorBlock trapdoorBlock;
        public BlockItem trapdoorItem;
        public WallBlock wallBlock;
        public BlockItem wallItem;
        public FenceGateBlock fenceGateBlock;
        public BlockItem fenceGateItem;
        public FenceBlock fenceBlock;
        public BlockItem fenceItem;
        public PressurePlateBlock pressurePlateBlock;
        public BlockItem pressurePlateItem;
        public ButtonBlock buttonBlock;
        public BlockItem buttonItem;
        public CarpetBlock carpetBlock;
        public BlockItem carpetItem;
        public LeavesBlock leavesBlock;
        public BlockItem leavesItem;
        public String MaterialType;
    }

    public class BlockMapData {
        public Block block;
        public String blockname;
        public String fullblockname;
        public EzMaterial ezMaterial;
        public Block saplingBlock;
        public boolean burnable;
        public TagKey<Item> tagKey;
        public boolean hasWall;
        public FamilyData familyData;
    }

    public static class OreData {
        public Block oreBlock;
        public Item dropItem;
        public int minDrops;
        public int maxDrops;
        public RuleTest ruleTest;
        public int veinSize;
        public int veinsPerChunk;
        public HeightRangePlacement heightRangePlacement;
        public String oreKey;
        public String orePlacedKey;

        private static Map<String, ResourceKey<ConfiguredFeature<?, ?>>> keyMap = new HashMap<>();
        private static Map<String, ResourceKey<PlacedFeature>> placedKeyMap = new HashMap<>();

        private ResourceKey<ConfiguredFeature<?, ?>> instanceKey;
        private ResourceKey<PlacedFeature> instancePlacedKey;

        public OreData(String oreKey, String orePlacedKey) {
            this.oreKey = oreKey;
            this.orePlacedKey = orePlacedKey;
            this.instanceKey = keyMap.computeIfAbsent(oreKey, k -> EzConfiguredFeatures.registerKey(oreKey));
            this.instancePlacedKey = placedKeyMap.computeIfAbsent(orePlacedKey, k -> EzPlacedFeatures.registerKey(orePlacedKey));
        }

        public ResourceKey<ConfiguredFeature<?, ?>> getInstanceKey() {
            return instanceKey;
        }
        public ResourceKey<PlacedFeature> getInstancePlacedKey() {
            return instancePlacedKey;
        }

        public static ResourceKey<ConfiguredFeature<?, ?>> getOreKey(String oreKey) {
            return keyMap.get(oreKey);
        }
        public static ResourceKey<PlacedFeature> getOrePlacedKey(String orePlacedKey) {
            return placedKeyMap.get(orePlacedKey);
        }
    }

    public enum EzMaterial {
        wood, stone, metal, wool, ice, sand, dirt, glass, netherStone
    }

    public final static HashMap<String, BlockItem> itemMap = new HashMap<>();
    public final static HashMap<Block, BlockMapData> blockMap = new HashMap<>();
    public final static HashMap<String, OreData> oreMap = new HashMap<>();
    public final static HashMap<BlockMapData, StairBlock> stairMap = new HashMap<>();
    public final static HashMap<BlockMapData, SlabBlock> slabMap = new HashMap<>();
    public final static HashMap<BlockMapData, EzVerticalSlabBlock> verticalSlabMap = new HashMap<>();
    public final static HashMap<BlockMapData, WallBlock> wallMap = new HashMap<>();
    public final static HashMap<BlockMapData, DoorBlock> doorMap = new HashMap<>();
    public final static HashMap<BlockMapData, TrapDoorBlock> trapDoorMap = new HashMap<>();
    public final static HashMap<BlockMapData, FenceBlock> fenceMap = new HashMap<>();
    public final static HashMap<BlockMapData, FenceGateBlock> fenceGateMap = new HashMap<>();
    public final static HashMap<BlockMapData, PressurePlateBlock> pressurePlateMap = new HashMap<>();
    public final static HashMap<BlockMapData, ButtonBlock> buttonMap = new HashMap<>();
    public final static HashMap<BlockMapData, CarpetBlock> carpetMap = new HashMap<>();
    public final static HashMap<BlockMapData, RotatedPillarBlock> rotatedPillarMap = new HashMap<>();
    public final static HashMap<BlockMapData, LeavesBlock> leavesMap = new HashMap<>();
    public final static HashMap<BlockMapData, Block> glassMap = new HashMap<>();
    public final static HashMap<BlockMapData, RotatedPillarBlock> woodMap = new HashMap<>();

    public final static ArrayList<Block> pickaxable = new ArrayList<>();
    public final static ArrayList<Block> axable = new ArrayList<>();
    public final static ArrayList<Block> shovolable = new ArrayList<>();
    public final static ArrayList<Block> hoeable = new ArrayList<>();
    public final static ArrayList<Block> needsIronTool = new ArrayList<>();
    public final static ArrayList<Block> needsStoneTool = new ArrayList<>();
    public final static ArrayList<Block> needsDiamondTool = new ArrayList<>();

    public EzBlocksBuilder(String name, Block blockProperties, @Nullable String suffix, EzMaterial ezMaterial) {
        this.name = name;
        this.name1 = (suffix != null) ? name + suffix : name;
        this.blockProperties = blockProperties;
        this.data = new BlockData();
        this.ezMaterial = ezMaterial;
        BlockMapData blockMapData = new BlockMapData();
        blockMapData.ezMaterial = ezMaterial;
        blockMapData.blockname = name1;
        blockMapData.fullblockname = name1;
        if (ezMaterial == EzMaterial.ice) {
            data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), this.name1),
                    new IceBlock(FabricBlockSettings.copyOf(blockProperties)));
        } else if (ezMaterial == EzMaterial.glass){
            data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), this.name1),
                    new GlassBlock(FabricBlockSettings.copyOf(blockProperties).nonOpaque()));
            glassMap.put(blockMapData, data.block);
        } else {
            data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), this.name1),
                    new Block(FabricBlockSettings.copyOf(blockProperties)));
        }
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), this.name1),
                new BlockItem(data.block, new FabricItemSettings()));
        if (ezMaterial == EzMaterial.wood) {
            this.tagKey = TagKey.create(Registries.ITEM, new ResourceLocation(EzLib.getModId() + ":" + name + "_logs"));
        }
        blockMapData.tagKey = this.tagKey;
        itemMap.put(name1, data.blockItem);
        BlockGroupList.add(data.blockItem);
        blockMap.put(data.block, blockMapData);
    }

    public EzBlocksBuilder makeOre(RuleTest replaceTestType, int vienSize, int veinsPerChunk, HeightRangePlacement heightRangePlacement) {
        OreData oreData = new OreData(name + "_key", name + "_placed_key");
        oreData.oreBlock = data.block;
        oreData.ruleTest = replaceTestType;
        oreData.veinSize = vienSize;
        oreData.veinsPerChunk = veinsPerChunk;
        oreData.heightRangePlacement = heightRangePlacement;

        oreMap.put(name, oreData);
        return this;
    }

    public EzBlocksBuilder makeOre(RuleTest replaceTestType, int vienSize, int veinsPerChunk, HeightRangePlacement heightRangePlacement, Item dropItem, int minDrops, int maxDrops) {
        OreData oreData = new OreData(name + "_key", name + "_placed_key");
        oreData.oreBlock = data.block;
        oreData.ruleTest = replaceTestType;
        oreData.veinSize = vienSize;
        oreData.veinsPerChunk = veinsPerChunk;
        oreData.heightRangePlacement = heightRangePlacement;
        oreData.dropItem = dropItem;
        oreData.minDrops = minDrops;
        oreData.maxDrops = maxDrops;

        oreMap.put(name, oreData);
        return this;
    }

    public EzBlocksBuilder stair() {
        String stairName = name + "_stair";
        BlockMapData blockMapData = new BlockMapData();
        blockMapData.blockname = stairName;
        blockMapData.fullblockname = name1;
        blockMapData.ezMaterial = this.ezMaterial;
        this.data.stairBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), stairName), new StairBlock(data.block.defaultBlockState(), FabricBlockSettings.copyOf(blockProperties)));
        this.data.stairItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), stairName), new BlockItem(data.stairBlock, new FabricItemSettings()));
        itemMap.put(stairName, data.stairItem);
        BlockGroupList.add(data.stairItem);
        stairMap.put(blockMapData, data.stairBlock);

        // Recipe Generation
        EzRecipeProvider.BlockPair set = new EzRecipeProvider.BlockPair();
        set.parent = data.block;
        set.block = data.stairBlock;
        if (this.ezMaterial.equals(EzMaterial.stone)) {
            stairStoneRecipeList.add(set);
        }

        return this;
    }

    public EzBlocksBuilder slab() {
        String slabName = name + "_slab";
        BlockMapData blockMapData = new BlockMapData();
        blockMapData.blockname = slabName;
        blockMapData.fullblockname = name1;
        blockMapData.ezMaterial = ezMaterial;
        this.data.slabBlock = register(slabName, SlabBlock::new, FabricBlockSettings.copyOf(blockProperties));
        this.data.slabItem = registerItem(slabName, data.slabBlock);
        itemMap.put(slabName, data.slabItem);
        BlockGroupList.add(data.slabItem);
        slabMap.put(blockMapData, data.slabBlock);

        // Recipe Generation
        EzRecipeProvider.BlockPair set = new EzRecipeProvider.BlockPair();
        set.parent = data.block;
        set.block = data.slabBlock;
        if (this.ezMaterial.equals(EzMaterial.stone)) {
            slabStoneRecipeList.add(set);
        }

        return this;
    }

    public EzBlocksBuilder verticalSlab() {
        String verticalSlabName = "vertical_" + name + "_slab";
        BlockMapData blockMapData = new BlockMapData();
        blockMapData.blockname = verticalSlabName;
        blockMapData.fullblockname = this.name1;
        blockMapData.ezMaterial = ezMaterial;
        this.data.verticalSlabBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), verticalSlabName), new EzVerticalSlabBlock(FabricBlockSettings.copyOf(blockProperties)));
        this.data.verticalSlabItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), verticalSlabName), new BlockItem(data.verticalSlabBlock, new FabricItemSettings()));
        itemMap.put(verticalSlabName, data.verticalSlabItem);
        BlockGroupList.add(data.verticalSlabItem);
        verticalSlabMap.put(blockMapData, data.verticalSlabBlock);

        // Recipe Generation
        EzRecipeProvider.BlockPair set = new EzRecipeProvider.BlockPair();
        set.parent = data.block;
        set.block = data.verticalSlabBlock;
        if (this.ezMaterial.equals(EzMaterial.stone)) {
            verticleSlabStoneRecipeList.add(set);
        }

        return this;
    }

    public EzBlocksBuilder extraBlock(String prefix, String suffix, @Nullable Block extraBlockProperties) {
        Block extraProperies = extraBlockProperties;
        BlockMapData blockMapData = new BlockMapData();
        blockMapData.blockname = prefix + name + suffix;
        blockMapData.ezMaterial = ezMaterial;
        blockMapData.fullblockname = name1;
        Block extraBlock;
        if (extraBlockProperties == null) {
            extraProperies = this.blockProperties;
        }
        if (ezMaterial == EzMaterial.glass) {
            extraBlock = register(blockMapData.blockname, GlassBlock::new, FabricBlockSettings.copyOf(extraProperies));
        } else if (ezMaterial == EzMaterial.ice) {
            extraBlock = register(blockMapData.blockname, IceBlock::new, FabricBlockSettings.copyOf(extraProperies));
        } else {
            extraBlock = register(blockMapData.blockname, Block::new, FabricBlockSettings.copyOf(extraProperies));
        }
        BlockItem extraItem = registerItem(blockMapData.blockname, extraBlock);
        this.data.extraBlocks.put(extraBlock, extraItem);
        itemMap.put(blockMapData.blockname, extraItem);
        BlockGroupList.add(extraItem);
        blockMap.put(extraBlock, blockMapData);

        // Recipe Generation
        EzRecipeProvider.BlockPair set = new EzRecipeProvider.BlockPair();
        set.parent = data.block;
        set.block = extraBlock;
        if (this.ezMaterial.equals(EzMaterial.stone)) {
            stoneBlockRecipeList.add(set);
        }

        return this;
    }

    public EzBlocksBuilder leaves(@Nullable String prefix, @Nullable String suffix, Block saplingBlock) {
        BlockMapData blockMapData = new BlockMapData();
        blockMapData.blockname = prefix + this.name + suffix + "_leaves";
        blockMapData.ezMaterial = ezMaterial;
        blockMapData.saplingBlock = saplingBlock;
        this.data.leavesBlock = register(blockMapData.blockname, LeavesBlock::new, FabricBlockSettings.copyOf(Blocks.OAK_LEAVES));
        this.data.leavesItem = registerItem(blockMapData.blockname, data.leavesBlock);
        itemMap.put(blockMapData.blockname, data.leavesItem);
        BlockGroupList.add(data.leavesItem);
        leavesMap.put(blockMapData, data.leavesBlock);
        hoeable.add(data.leavesBlock);
        return this;
    }

    public EzBlocksBuilder pillar(String prefix, String suffix, @Nullable Block extraBlockProperties) {
        Block extraProperties = extraBlockProperties;
        BlockMapData blockMapData = new BlockMapData();
        blockMapData.blockname = prefix + name + suffix;
        blockMapData.ezMaterial = ezMaterial;
        if (extraBlockProperties == null) {
            extraProperties = this.blockProperties;
        }
        RotatedPillarBlock pillar = register(blockMapData.blockname, RotatedPillarBlock::new, FabricBlockSettings.copyOf(extraProperties));
        BlockItem pillarItem = registerItem(blockMapData.blockname, pillar);
        this.data.extraColumns.put(pillar, pillarItem);
        itemMap.put(blockMapData.blockname, pillarItem);
        BlockGroupList.add(pillarItem);
        rotatedPillarMap.put(blockMapData, pillar);

        // Recipe Generation
        EzRecipeProvider.BlockPair set = new EzRecipeProvider.BlockPair();
        set.parent = data.block;
        set.block = pillar;
        if (this.ezMaterial.equals(EzMaterial.stone)) {
            stoneBlockRecipeList.add(set);
        }

        return this;
    }

    public EzBlocksBuilder logs(String prefix, String suffix, @Nullable Block extraBlockProperties, boolean burnable) {
        Block extraProperties = extraBlockProperties;
        BlockMapData blockMapData1 = new BlockMapData();
        BlockMapData blockMapData2 = new BlockMapData();
        blockMapData1.blockname = prefix + name + suffix;
        blockMapData1.ezMaterial = ezMaterial;
        blockMapData2.blockname = "stripped_" + prefix + name + suffix;
        blockMapData2.ezMaterial = ezMaterial;
        blockMapData1.burnable = burnable;
        blockMapData2.burnable = burnable;
        if (extraBlockProperties == null) {
            extraProperties = this.blockProperties;
        }
        RotatedPillarBlock log = register(blockMapData1.blockname, RotatedPillarBlock::new, FabricBlockSettings.copyOf(extraProperties));
        RotatedPillarBlock strippedlog = register(blockMapData2.blockname, RotatedPillarBlock::new, FabricBlockSettings.copyOf(extraProperties));
        BlockItem logItem = registerItem(blockMapData1.blockname, log);
        BlockItem strippedlogItem = registerItem(blockMapData2.blockname, strippedlog);
        this.data.extraColumns.put(log, logItem);
        this.data.extraColumns.put(strippedlog, strippedlogItem);
        StrippableBlockRegistry.register(log, strippedlog);
        itemMap.put(blockMapData1.blockname, logItem);
        itemMap.put(blockMapData2.blockname, strippedlogItem);
        BlockGroupList.add(logItem);
        BlockGroupList.add(strippedlogItem);
        rotatedPillarMap.put(blockMapData1, log);
        rotatedPillarMap.put(blockMapData2, strippedlog);
        return this;
    }

    public EzBlocksBuilder wood(String pillarname, String strippedName, Boolean burnable) {
        BlockMapData blockMapData1 = new BlockMapData();
        BlockMapData blockMapData2 = new BlockMapData();
        blockMapData1.blockname = name + "_wood";
        blockMapData2.blockname = "stripped_" + name + "_wood";
        blockMapData1.fullblockname = pillarname;
        blockMapData2.fullblockname = strippedName;
        blockMapData1.ezMaterial = ezMaterial;
        blockMapData2.ezMaterial = ezMaterial;
        blockMapData1.burnable = burnable;
        blockMapData2.burnable = burnable;
        RotatedPillarBlock woodBlock = register(blockMapData1.blockname, RotatedPillarBlock::new, FabricBlockSettings.copyOf(Blocks.OAK_WOOD));
        RotatedPillarBlock strippedwoodBlock = register(blockMapData2.blockname, RotatedPillarBlock::new, FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD));
        BlockItem woodItem = registerItem(blockMapData1.blockname, woodBlock);
        BlockItem strippedwoodItem = registerItem(blockMapData2.blockname, strippedwoodBlock);
        StrippableBlockRegistry.register(woodBlock, strippedwoodBlock);
        blockMapData1.tagKey = this.tagKey;
        itemMap.put(blockMapData1.blockname, woodItem);
        itemMap.put(blockMapData2.blockname, strippedwoodItem);
        BlockGroupList.add(woodItem);
        BlockGroupList.add(strippedwoodItem);
        woodMap.put(blockMapData1, woodBlock);
        woodMap.put(blockMapData2, strippedwoodBlock);
        return this;
    }

    public EzBlocksBuilder wall() {
        String wallName = name + "_wall";
        BlockMapData blockMapData = new BlockMapData();
        blockMapData.blockname = wallName;
        blockMapData.fullblockname = this.name1;
        blockMapData.ezMaterial = ezMaterial;
        this.data.wallBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), wallName), new WallBlock(FabricBlockSettings.copyOf(blockProperties)));
        this.data.wallItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), wallName), new BlockItem(this.data.wallBlock, new FabricItemSettings()));
        itemMap.put(wallName, data.wallItem);
        BlockGroupList.add(data.wallItem);
        wallMap.put(blockMapData, data.wallBlock);
        this.hasWall = true;

        // Recipe Generation
        EzRecipeProvider.BlockPair set = new EzRecipeProvider.BlockPair();
        set.parent = data.block;
        set.block = data.wallBlock;
        wallRecipeList.add(set);

        return this;
    }

    public EzBlocksBuilder door(BlockSetType blockSetType, Block blockProperties) {
        BlockMapData blockMapData = new BlockMapData();
        blockMapData.blockname = name + "_door";
        blockMapData.ezMaterial = ezMaterial;
        blockMapData.fullblockname = this.name1;
        this.data.doorBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), blockMapData.blockname), new DoorBlock(FabricBlockSettings.copyOf(blockProperties), blockSetType));
        this.data.doorItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), blockMapData.blockname), new BlockItem(this.data.doorBlock, new FabricItemSettings()));
        itemMap.put(blockMapData.blockname, data.doorItem);
        BlockGroupList.add(data.doorItem);
        doorMap.put(blockMapData, data.doorBlock);
//        blockMapData.familyData.door = data.doorBlock;
        return this;
    }

    public EzBlocksBuilder trapdoor(BlockSetType blockSetType, Block blockProperties) {
        BlockMapData blockMapData = new BlockMapData();
        blockMapData.blockname = name + "_trapdoor";
        blockMapData.ezMaterial = ezMaterial;
        blockMapData.fullblockname = this.name1;
        blockMapData.hasWall = this.hasWall;
        this.data.trapdoorBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), blockMapData.blockname), new TrapDoorBlock(FabricBlockSettings.copyOf(blockProperties), blockSetType));
        this.data.trapdoorItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), blockMapData.blockname), new BlockItem(this.data.trapdoorBlock, new FabricItemSettings()));
        itemMap.put(blockMapData.blockname, data.trapdoorItem);
        BlockGroupList.add(data.trapdoorItem);
        trapDoorMap.put(blockMapData, data.trapdoorBlock);
//        blockMapData.familyData.trapdoor = data.trapdoorBlock;
        return this;
    }

    public EzBlocksBuilder button(BlockSetType blockSetType, Integer ticksPressed, Boolean arrowHits) {
        String buttonName = name + "_button";
        BlockMapData blockMapData = new BlockMapData();
        blockMapData.blockname = buttonName;
        blockMapData.fullblockname = this.name1;
        blockMapData.ezMaterial = ezMaterial;
        this.data.buttonBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), buttonName), new ButtonBlock(FabricBlockSettings.copyOf(blockProperties), blockSetType, ticksPressed, arrowHits));
        this.data.buttonItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), buttonName), new BlockItem(this.data.buttonBlock, new FabricItemSettings()));
        itemMap.put(buttonName, data.buttonItem);
        BlockGroupList.add(data.buttonItem);
        buttonMap.put(blockMapData, data.buttonBlock);
//        blockMapData.familyData.button = data.buttonBlock;
        return this;
    }

    public EzBlocksBuilder fence() {
        String fenceName = name + "_fence";
        BlockMapData blockMapData = new BlockMapData();
        blockMapData.blockname = fenceName;
        blockMapData.fullblockname = this.name1;
        blockMapData.ezMaterial = ezMaterial;
        this.data.fenceBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), fenceName), new FenceBlock(FabricBlockSettings.copyOf(blockProperties)));
        this.data.fenceItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), fenceName), new BlockItem(this.data.fenceBlock, new FabricItemSettings()));
        itemMap.put(fenceName, data.fenceItem);
        BlockGroupList.add(data.fenceItem);
        fenceMap.put(blockMapData, data.fenceBlock);
//        blockMapData.familyData.fence = data.fenceBlock;
        return this;
    }

    public EzBlocksBuilder fenceGate() {
        String fenceGateName = name + "_fence_gate";
        BlockMapData blockMapData = new BlockMapData();
        blockMapData.blockname = fenceGateName;
        blockMapData.fullblockname = this.name1;
        blockMapData.ezMaterial = ezMaterial;
        this.data.fenceGateBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), fenceGateName), new FenceGateBlock(FabricBlockSettings.copyOf(blockProperties), WoodType.OAK));
        this.data.fenceGateItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), fenceGateName), new BlockItem(this.data.fenceGateBlock, new FabricItemSettings()));
        itemMap.put(fenceGateName, data.fenceGateItem);
        BlockGroupList.add(data.fenceGateItem);
        fenceGateMap.put(blockMapData, data.fenceGateBlock);
//        blockMapData.familyData.fenceGate = data.fenceGateBlock;
        return this;
    }

    public EzBlocksBuilder pressurePlate(BlockSetType type, PressurePlateBlock.Sensitivity sensitivity) {
        String pressurePlateName = name + "_pressure_plate";
        BlockMapData blockMapData = new BlockMapData();
        blockMapData.blockname = pressurePlateName;
        blockMapData.fullblockname = this.name1;
        blockMapData.ezMaterial = ezMaterial;
        this.data.pressurePlateBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), pressurePlateName), new PressurePlateBlock(sensitivity, FabricBlockSettings.copyOf(blockProperties), type));
        this.data.pressurePlateItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), pressurePlateName), new BlockItem(this.data.pressurePlateBlock, new FabricItemSettings()));
        itemMap.put(pressurePlateName, data.pressurePlateItem);
        BlockGroupList.add(data.pressurePlateItem);
        pressurePlateMap.put(blockMapData, data.pressurePlateBlock);
//        blockMapData.familyData.pressurePlate = data.pressurePlateBlock;
        return this;
    }

    public EzBlocksBuilder carpet(Block blockProperties) {
        String carpetName = name + "_carpet";
        BlockMapData blockMapData = new BlockMapData();
        blockMapData.blockname = carpetName;
        blockMapData.fullblockname = this.name1;
        blockMapData.ezMaterial = ezMaterial;
        this.data.carpetBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), carpetName), new CarpetBlock(FabricBlockSettings.copyOf(blockProperties)));
        this.data.carpetItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), carpetName), new BlockItem(this.data.carpetBlock, new FabricItemSettings()));
        itemMap.put(carpetName, data.carpetItem);
        BlockGroupList.add(data.carpetItem);
        carpetMap.put(blockMapData, data.carpetBlock);
        return this;
    }

    public EzBlocksBuilder pickaxe() {
        Optional.ofNullable(data.block).ifPresent(pickaxable::add);
        Optional.ofNullable(data.slabBlock).ifPresent(pickaxable::add);
        Optional.ofNullable(data.stairBlock).ifPresent(pickaxable::add);
        Optional.ofNullable(data.verticalSlabBlock).ifPresent(pickaxable::add);
        Optional.ofNullable(data.doorBlock).ifPresent(pickaxable::add);
        Optional.ofNullable(data.trapdoorBlock).ifPresent(pickaxable::add);
        Optional.ofNullable(data.buttonBlock).ifPresent(pickaxable::add);
        Optional.ofNullable(data.fenceBlock).ifPresent(pickaxable::add);
        Optional.ofNullable(data.fenceGateBlock).ifPresent(pickaxable::add);
        Optional.ofNullable(data.wallBlock).ifPresent(pickaxable::add);
        Optional.ofNullable(data.pressurePlateBlock).ifPresent(pickaxable::add);
        Optional.ofNullable(data.carpetBlock).ifPresent(pickaxable::add);
        Optional.ofNullable(data.extraBlocks).ifPresent(blocks -> blocks.forEach((block, blockItem) -> Optional.ofNullable(block).ifPresent(pickaxable::add)));
        Optional.ofNullable(data.extraColumns).ifPresent(columns -> columns.forEach((rotatedPillarBlock, blockItem) -> Optional.ofNullable(rotatedPillarBlock).ifPresent(pickaxable::add)));
        return this;
    }

    public EzBlocksBuilder axe() {
        Optional.ofNullable(data.block).ifPresent(axable::add);
        Optional.ofNullable(data.slabBlock).ifPresent(axable::add);
        Optional.ofNullable(data.stairBlock).ifPresent(axable::add);
        Optional.ofNullable(data.verticalSlabBlock).ifPresent(axable::add);
        Optional.ofNullable(data.doorBlock).ifPresent(axable::add);
        Optional.ofNullable(data.trapdoorBlock).ifPresent(axable::add);
        Optional.ofNullable(data.buttonBlock).ifPresent(axable::add);
        Optional.ofNullable(data.fenceBlock).ifPresent(axable::add);
        Optional.ofNullable(data.fenceGateBlock).ifPresent(axable::add);
        Optional.ofNullable(data.wallBlock).ifPresent(axable::add);
        Optional.ofNullable(data.pressurePlateBlock).ifPresent(axable::add);
        Optional.ofNullable(data.carpetBlock).ifPresent(axable::add);
        Optional.ofNullable(data.extraBlocks).ifPresent(blocks -> blocks.forEach((block, blockItem) -> Optional.ofNullable(block).ifPresent(axable::add)));
        Optional.ofNullable(data.extraColumns).ifPresent(columns -> columns.forEach((rotatedPillarBlock, blockItem) -> Optional.ofNullable(rotatedPillarBlock).ifPresent(axable::add)));
        return this;
    }

    public EzBlocksBuilder shovel() {
        Optional.ofNullable(data.block).ifPresent(shovolable::add);
        Optional.ofNullable(data.slabBlock).ifPresent(shovolable::add);
        Optional.ofNullable(data.stairBlock).ifPresent(shovolable::add);
        Optional.ofNullable(data.verticalSlabBlock).ifPresent(shovolable::add);
        Optional.ofNullable(data.doorBlock).ifPresent(shovolable::add);
        Optional.ofNullable(data.trapdoorBlock).ifPresent(shovolable::add);
        Optional.ofNullable(data.buttonBlock).ifPresent(shovolable::add);
        Optional.ofNullable(data.fenceBlock).ifPresent(shovolable::add);
        Optional.ofNullable(data.fenceGateBlock).ifPresent(shovolable::add);
        Optional.ofNullable(data.wallBlock).ifPresent(shovolable::add);
        Optional.ofNullable(data.pressurePlateBlock).ifPresent(shovolable::add);
        Optional.ofNullable(data.carpetBlock).ifPresent(shovolable::add);
        Optional.ofNullable(data.extraBlocks).ifPresent(blocks -> blocks.forEach((block, blockItem) -> Optional.ofNullable(block).ifPresent(shovolable::add)));
        Optional.ofNullable(data.extraColumns).ifPresent(columns -> columns.forEach((rotatedPillarBlock, blockItem) -> Optional.ofNullable(rotatedPillarBlock).ifPresent(shovolable::add)));
        return this;
    }

    public EzBlocksBuilder hoe() {
        Optional.ofNullable(data.block).ifPresent(hoeable::add);
        Optional.ofNullable(data.slabBlock).ifPresent(hoeable::add);
        Optional.ofNullable(data.stairBlock).ifPresent(hoeable::add);
        Optional.ofNullable(data.verticalSlabBlock).ifPresent(hoeable::add);
        Optional.ofNullable(data.doorBlock).ifPresent(hoeable::add);
        Optional.ofNullable(data.trapdoorBlock).ifPresent(hoeable::add);
        Optional.ofNullable(data.buttonBlock).ifPresent(hoeable::add);
        Optional.ofNullable(data.fenceBlock).ifPresent(hoeable::add);
        Optional.ofNullable(data.fenceGateBlock).ifPresent(hoeable::add);
        Optional.ofNullable(data.wallBlock).ifPresent(hoeable::add);
        Optional.ofNullable(data.pressurePlateBlock).ifPresent(hoeable::add);
        Optional.ofNullable(data.carpetBlock).ifPresent(hoeable::add);
        Optional.ofNullable(data.extraBlocks).ifPresent(blocks -> blocks.forEach((block, blockItem) -> Optional.ofNullable(block).ifPresent(hoeable::add)));
        Optional.ofNullable(data.extraColumns).ifPresent(columns -> columns.forEach((rotatedPillarBlock, blockItem) -> Optional.ofNullable(rotatedPillarBlock).ifPresent(hoeable::add)));
        return this;
    }

    public EzBlocksBuilder ironTool() {
        Optional.ofNullable(data.block).ifPresent(needsIronTool::add);
        Optional.ofNullable(data.slabBlock).ifPresent(needsIronTool::add);
        Optional.ofNullable(data.stairBlock).ifPresent(needsIronTool::add);
        Optional.ofNullable(data.verticalSlabBlock).ifPresent(needsIronTool::add);
        Optional.ofNullable(data.doorBlock).ifPresent(needsIronTool::add);
        Optional.ofNullable(data.trapdoorBlock).ifPresent(needsIronTool::add);
        Optional.ofNullable(data.buttonBlock).ifPresent(needsIronTool::add);
        Optional.ofNullable(data.fenceBlock).ifPresent(needsIronTool::add);
        Optional.ofNullable(data.fenceGateBlock).ifPresent(needsIronTool::add);
        Optional.ofNullable(data.wallBlock).ifPresent(needsIronTool::add);
        Optional.ofNullable(data.pressurePlateBlock).ifPresent(needsIronTool::add);
        Optional.ofNullable(data.carpetBlock).ifPresent(needsIronTool::add);
        Optional.ofNullable(data.extraBlocks).ifPresent(blocks -> blocks.forEach((block, blockItem) -> Optional.ofNullable(block).ifPresent(needsIronTool::add)));
        Optional.ofNullable(data.extraColumns).ifPresent(columns -> columns.forEach((rotatedPillarBlock, blockItem) -> Optional.ofNullable(rotatedPillarBlock).ifPresent(needsIronTool::add)));
        return this;
    }

    public EzBlocksBuilder diamondTool() {
        Optional.ofNullable(data.block).ifPresent(needsDiamondTool::add);
        Optional.ofNullable(data.slabBlock).ifPresent(needsDiamondTool::add);
        Optional.ofNullable(data.stairBlock).ifPresent(needsDiamondTool::add);
        Optional.ofNullable(data.verticalSlabBlock).ifPresent(needsDiamondTool::add);
        Optional.ofNullable(data.doorBlock).ifPresent(needsDiamondTool::add);
        Optional.ofNullable(data.trapdoorBlock).ifPresent(needsDiamondTool::add);
        Optional.ofNullable(data.buttonBlock).ifPresent(needsDiamondTool::add);
        Optional.ofNullable(data.fenceBlock).ifPresent(needsDiamondTool::add);
        Optional.ofNullable(data.fenceGateBlock).ifPresent(needsDiamondTool::add);
        Optional.ofNullable(data.wallBlock).ifPresent(needsDiamondTool::add);
        Optional.ofNullable(data.pressurePlateBlock).ifPresent(needsDiamondTool::add);
        Optional.ofNullable(data.carpetBlock).ifPresent(needsDiamondTool::add);
        Optional.ofNullable(data.extraBlocks).ifPresent(blocks -> blocks.forEach((block, blockItem) -> Optional.ofNullable(block).ifPresent(needsDiamondTool::add)));
        Optional.ofNullable(data.extraColumns).ifPresent(columns -> columns.forEach((rotatedPillarBlock, blockItem) -> Optional.ofNullable(rotatedPillarBlock).ifPresent(needsDiamondTool::add)));
        return this;
    }

    public EzBlocksBuilder stoneTool() {
        Optional.ofNullable(data.block).ifPresent(needsStoneTool::add);
        Optional.ofNullable(data.slabBlock).ifPresent(needsStoneTool::add);
        Optional.ofNullable(data.stairBlock).ifPresent(needsStoneTool::add);
        Optional.ofNullable(data.verticalSlabBlock).ifPresent(needsStoneTool::add);
        Optional.ofNullable(data.doorBlock).ifPresent(needsStoneTool::add);
        Optional.ofNullable(data.trapdoorBlock).ifPresent(needsStoneTool::add);
        Optional.ofNullable(data.buttonBlock).ifPresent(needsStoneTool::add);
        Optional.ofNullable(data.fenceBlock).ifPresent(needsStoneTool::add);
        Optional.ofNullable(data.fenceGateBlock).ifPresent(needsStoneTool::add);
        Optional.ofNullable(data.wallBlock).ifPresent(needsStoneTool::add);
        Optional.ofNullable(data.pressurePlateBlock).ifPresent(needsStoneTool::add);
        Optional.ofNullable(data.carpetBlock).ifPresent(needsStoneTool::add);
        Optional.ofNullable(data.extraBlocks).ifPresent(blocks -> blocks.forEach((block, blockItem) -> Optional.ofNullable(block).ifPresent(needsStoneTool::add)));
        Optional.ofNullable(data.extraColumns).ifPresent(columns -> columns.forEach((rotatedPillarBlock, blockItem) -> Optional.ofNullable(rotatedPillarBlock).ifPresent(needsStoneTool::add)));
        return this;
    }

    //Foundation methods required for making the code easyer and less repetitive above.
    public <T extends Block> T register(String name, Function<BlockBehaviour.Properties, T> constructor, BlockBehaviour.Properties blockProperties) {
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), name), constructor.apply(blockProperties));
    }

    public <T extends Block> BlockItem registerItem(String name, Block block) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), name), new BlockItem(block, new FabricItemSettings()));
    }
}