package net.distantdig.ezLib.block;


import net.distantdig.ezLib.EzLib;
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
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
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
import java.util.Optional;
import java.util.function.Function;

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

    public class Strings {
        public String blockname;
        public String fullblockname;
        public EzMaterial ezMaterial;
        public Block saplingBlock;
        public boolean burnable;
        public TagKey<Item> tagKey;
        public boolean hasWall;
    }

    public static class OreData {
        public Block oreBlock;
        public RuleTest ruleTest;
        public int veinSize;
        public int veinsPerChunk;
        public HeightRangePlacement heightRangePlacement;
        public static ResourceKey<ConfiguredFeature<?, ?>> oreKey;
        public static ResourceKey<PlacedFeature> orePlacedKey;
    }

    public enum EzMaterial {
        wood, stone, metal, wool, ice, sand, dirt, netherStone
    }

    public final static HashMap<String, BlockItem> inventoryMap = new HashMap<>();
    public final static HashMap<Strings, Block> blockMap = new HashMap<>();
    public final static HashMap<String, OreData> oreMap = new HashMap<>();
    public final static HashMap<Strings, StairBlock> stairMap = new HashMap<>();
    public final static HashMap<Strings, SlabBlock> slabMap = new HashMap<>();
    public final static HashMap<Strings, EzVerticalSlabBlock> verticalSlabMap = new HashMap<>();
    public final static HashMap<Strings, WallBlock> wallMap = new HashMap<>();
    public final static HashMap<Strings, DoorBlock> doorMap = new HashMap<>();
    public final static HashMap<Strings, TrapDoorBlock> trapDoorMap = new HashMap<>();
    public final static HashMap<Strings, FenceBlock> fenceMap = new HashMap<>();
    public final static HashMap<Strings, FenceGateBlock> fenceGateMap = new HashMap<>();
    public final static HashMap<Strings, PressurePlateBlock> pressurePlateMap = new HashMap<>();
    public final static HashMap<Strings, ButtonBlock> buttonMap = new HashMap<>();
    public final static HashMap<Strings, CarpetBlock> carpetMap = new HashMap<>();
    public final static HashMap<Strings, RotatedPillarBlock> rotatedPillarMap = new HashMap<>();
    public final static HashMap<Strings, LeavesBlock> leavesMap = new HashMap<>();
    public final static HashMap<Strings, RotatedPillarBlock> woodMap = new HashMap<>();

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
        Strings strings = new Strings();
        strings.ezMaterial = ezMaterial;
        strings.blockname = name1;
        strings.fullblockname = name1;
        if (ezMaterial == EzMaterial.ice) {data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), this.name1), new IceBlock(FabricBlockSettings.copyOf(blockProperties)));} else
        {data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), this.name1), new Block(FabricBlockSettings.copyOf(blockProperties)));}
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), this.name1), new BlockItem(data.block, new FabricItemSettings()));
        if (ezMaterial == EzMaterial.wood) {this.tagKey = TagKey.create(Registries.ITEM, new ResourceLocation(EzLib.getModId() + ":" + name + "_logs"));}
        strings.tagKey = this.tagKey;
        inventoryMap.put(name1, data.blockItem);
        blockMap.put(strings, data.block);
    }

    public EzBlocksBuilder makeOre(RuleTest replaceTestType, int vienSize, int veinsPerChunk, HeightRangePlacement heightRangePlacement) {
        OreData oreData = new OreData();
        oreData.oreBlock = data.block;
        oreData.ruleTest = replaceTestType;
        oreData.veinSize = vienSize;
        oreData.veinsPerChunk = veinsPerChunk;
        oreData.heightRangePlacement = heightRangePlacement;

        OreData.oreKey = EzConfiguredFeatures.registerKey(name + "_key");
        OreData.orePlacedKey = EzPlacedFeatures.registerKey(name + "_placed_key");

                oreMap.put(name, oreData);
        return this;
    }

    public EzBlocksBuilder stair() {
        String stairName = name + "_stair";
        Strings strings = new Strings();
        strings.blockname = stairName;
        strings.fullblockname = name1;
        strings.ezMaterial = this.ezMaterial;
        this.data.stairBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), stairName), new StairBlock(data.block.defaultBlockState(), FabricBlockSettings.copyOf(blockProperties)));
        this.data.stairItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), stairName), new BlockItem(data.stairBlock, new FabricItemSettings()));
        inventoryMap.put(stairName, data.stairItem);
        stairMap.put(strings, data.stairBlock);
        return this;
    }

    public EzBlocksBuilder slab() {
        String slabName = name + "_slab";
        Strings strings = new Strings();
        strings.blockname = slabName;
        strings.fullblockname = name1;
        strings.ezMaterial = ezMaterial;
        this.data.slabBlock = register(slabName, SlabBlock::new, FabricBlockSettings.copyOf(blockProperties));
        this.data.slabItem = registerItem(slabName, data.slabBlock);
        inventoryMap.put(slabName, data.slabItem);
        slabMap.put(strings, data.slabBlock);
        return this;
    }

    public EzBlocksBuilder verticalSlab() {
        String verticalSlabName = "vertical_" + name + "_slab";
        Strings strings = new Strings();
        strings.blockname = verticalSlabName;
        strings.fullblockname = this.name1;
        strings.ezMaterial = ezMaterial;
        this.data.verticalSlabBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), verticalSlabName), new EzVerticalSlabBlock(FabricBlockSettings.copyOf(blockProperties)));
        this.data.verticalSlabItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), verticalSlabName), new BlockItem(data.verticalSlabBlock, new FabricItemSettings()));
        inventoryMap.put(verticalSlabName, data.verticalSlabItem);
        verticalSlabMap.put(strings, data.verticalSlabBlock);
        return this;
    }

    public EzBlocksBuilder extraBlock(String prefix, String suffix, @Nullable Block extraBlockProperties) {
        Block extraProperies = extraBlockProperties;
        Strings strings = new Strings();
        strings.blockname = prefix + name + suffix;
        strings.ezMaterial = ezMaterial;
        strings.fullblockname = name1;
        if (extraBlockProperties == null) {
            extraProperies = this.blockProperties;
        }
        Block extraBlock = register(strings.blockname, Block::new, FabricBlockSettings.copyOf(extraProperies));
        BlockItem extraItem = registerItem(strings.blockname, extraBlock);
        this.data.extraBlocks.put(extraBlock, extraItem);
        inventoryMap.put(strings.blockname, extraItem);
        blockMap.put(strings, extraBlock);
        return this;
    }

    public EzBlocksBuilder leaves(@Nullable String prefix, @Nullable String suffix, Block saplingBlock) {
        Strings strings = new Strings();
        strings.blockname = prefix + this.name + suffix + "_leaves";
        strings.ezMaterial = ezMaterial;
        strings.saplingBlock = saplingBlock;
        this.data.leavesBlock = register(strings.blockname, LeavesBlock::new, FabricBlockSettings.copyOf(Blocks.OAK_LEAVES));
        this.data.leavesItem = registerItem(strings.blockname, data.leavesBlock);
        inventoryMap.put(strings.blockname, data.leavesItem);
        leavesMap.put(strings, data.leavesBlock);
        hoeable.add(data.leavesBlock);
        return this;
    }

    public EzBlocksBuilder pillar(String prefix, String suffix, @Nullable Block extraBlockProperties) {
        Block extraProperties = extraBlockProperties;
        Strings strings = new Strings();
        strings.blockname = prefix + name + suffix;
        strings.ezMaterial = ezMaterial;
        if (extraBlockProperties == null) {
            extraProperties = this.blockProperties;
        }
        RotatedPillarBlock pillar = register(strings.blockname, RotatedPillarBlock::new, FabricBlockSettings.copyOf(extraProperties));
        BlockItem pillarItem = registerItem(strings.blockname, pillar);
        this.data.extraColumns.put(pillar, pillarItem);
        inventoryMap.put(strings.blockname, pillarItem);
        rotatedPillarMap.put(strings, pillar);
        return this;
    }

    public EzBlocksBuilder logs(String prefix, String suffix, @Nullable Block extraBlockProperties, boolean burnable) {
        Block extraProperties = extraBlockProperties;
        Strings strings1 = new Strings();
        Strings strings2 = new Strings();
        strings1.blockname = prefix + name + suffix;
        strings1.ezMaterial = ezMaterial;
        strings2.blockname = "stripped_" + prefix + name + suffix;
        strings2.ezMaterial = ezMaterial;
        strings1.burnable = burnable;
        strings2.burnable = burnable;
        if (extraBlockProperties == null) {
            extraProperties = this.blockProperties;
        }
        RotatedPillarBlock log = register(strings1.blockname, RotatedPillarBlock::new, FabricBlockSettings.copyOf(extraProperties));
        RotatedPillarBlock strippedlog = register(strings2.blockname, RotatedPillarBlock::new, FabricBlockSettings.copyOf(extraProperties));
        BlockItem logItem = registerItem(strings1.blockname, log);
        BlockItem strippedlogItem = registerItem(strings2.blockname, strippedlog);
        this.data.extraColumns.put(log, logItem);
        this.data.extraColumns.put(strippedlog, strippedlogItem);
        StrippableBlockRegistry.register(log, strippedlog);
        inventoryMap.put(strings1.blockname, logItem);
        inventoryMap.put(strings2.blockname, strippedlogItem);
        rotatedPillarMap.put(strings1, log);
        rotatedPillarMap.put(strings2, strippedlog);
        return this;
    }

    public EzBlocksBuilder wood(String pillarname, String strippedName, Boolean burnable) {
        Strings strings1 = new Strings();
        Strings strings2 = new Strings();
        strings1.blockname = name + "_wood";
        strings2.blockname = "stripped_" + name + "_wood";
        strings1.fullblockname = pillarname;
        strings2.fullblockname = strippedName;
        strings1.ezMaterial = ezMaterial;
        strings2.ezMaterial = ezMaterial;
        strings1.burnable = burnable;
        strings2.burnable = burnable;
        RotatedPillarBlock woodBlock = register(strings1.blockname, RotatedPillarBlock::new, FabricBlockSettings.copyOf(Blocks.OAK_WOOD));
        RotatedPillarBlock strippedwoodBlock = register(strings2.blockname, RotatedPillarBlock::new, FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD));
        BlockItem woodItem = registerItem(strings1.blockname, woodBlock);
        BlockItem strippedwoodItem = registerItem(strings2.blockname, strippedwoodBlock);
        StrippableBlockRegistry.register(woodBlock, strippedwoodBlock);
        strings1.tagKey = this.tagKey;
        inventoryMap.put(strings1.blockname, woodItem);
        inventoryMap.put(strings2.blockname, strippedwoodItem);
        woodMap.put(strings1, woodBlock);
        woodMap.put(strings2, strippedwoodBlock);
        return this;
    }

    public EzBlocksBuilder wall() {
        String wallName = name + "_wall";
        Strings strings = new Strings();
        strings.blockname = wallName;
        strings.fullblockname = this.name1;
        strings.ezMaterial = ezMaterial;
        this.data.wallBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), wallName), new WallBlock(FabricBlockSettings.copyOf(blockProperties)));
        this.data.wallItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), wallName), new BlockItem(this.data.wallBlock, new FabricItemSettings()));
        inventoryMap.put(wallName, data.wallItem);
        wallMap.put(strings, data.wallBlock);
        this.hasWall = true;
        return this;
    }

    public EzBlocksBuilder door(BlockSetType blockSetType, Block blockProperties) {
        Strings strings = new Strings();
        strings.blockname = name + "_door";
        strings.ezMaterial = ezMaterial;
        strings.fullblockname = this.name1;
        this.data.doorBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), strings.blockname), new DoorBlock(FabricBlockSettings.copyOf(blockProperties), blockSetType));
        this.data.doorItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), strings.blockname), new BlockItem(this.data.doorBlock, new FabricItemSettings()));
        inventoryMap.put(strings.blockname, data.doorItem);
        doorMap.put(strings, data.doorBlock);
        return this;
    }

    public EzBlocksBuilder trapdoor(BlockSetType blockSetType, Block blockProperties) {
        Strings strings = new Strings();
        strings.blockname = name + "_trapdoor";
        strings.ezMaterial = ezMaterial;
        strings.fullblockname = this.name1;
        strings.hasWall = this.hasWall;
        this.data.trapdoorBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), strings.blockname), new TrapDoorBlock(FabricBlockSettings.copyOf(blockProperties), blockSetType));
        this.data.trapdoorItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), strings.blockname), new BlockItem(this.data.trapdoorBlock, new FabricItemSettings()));
        inventoryMap.put(strings.blockname, data.trapdoorItem);
        trapDoorMap.put(strings, data.trapdoorBlock);
        return this;
    }

    public EzBlocksBuilder button(BlockSetType blockSetType, Integer ticksPressed, Boolean arrowHits) {
        String buttonName = name + "_button";
        Strings strings = new Strings();
        strings.blockname = buttonName;
        strings.fullblockname = this.name1;
        strings.ezMaterial = ezMaterial;
        this.data.buttonBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), buttonName), new ButtonBlock(FabricBlockSettings.copyOf(blockProperties), blockSetType, ticksPressed, arrowHits));
        this.data.buttonItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), buttonName), new BlockItem(this.data.buttonBlock, new FabricItemSettings()));
        inventoryMap.put(buttonName, data.buttonItem);
        buttonMap.put(strings, data.buttonBlock);
        return this;
    }

    public EzBlocksBuilder fence() {
        String fenceName = name + "_fence";
        Strings strings = new Strings();
        strings.blockname = fenceName;
        strings.fullblockname = this.name1;
        strings.ezMaterial = ezMaterial;
        this.data.fenceBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), fenceName), new FenceBlock(FabricBlockSettings.copyOf(blockProperties)));
        this.data.fenceItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), fenceName), new BlockItem(this.data.fenceBlock, new FabricItemSettings()));
        inventoryMap.put(fenceName, data.fenceItem);
        fenceMap.put(strings, data.fenceBlock);
        return this;
    }

    public EzBlocksBuilder fenceGate() {
        String fenceGateName = name + "_fence_gate";
        Strings strings = new Strings();
        strings.blockname = fenceGateName;
        strings.fullblockname = this.name1;
        strings.ezMaterial = ezMaterial;
        this.data.fenceGateBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), fenceGateName), new FenceGateBlock(FabricBlockSettings.copyOf(blockProperties), WoodType.OAK));
        this.data.fenceGateItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), fenceGateName), new BlockItem(this.data.fenceGateBlock, new FabricItemSettings()));
        inventoryMap.put(fenceGateName, data.fenceGateItem);
        fenceGateMap.put(strings, data.fenceGateBlock);
        return this;
    }

    public EzBlocksBuilder pressurePlate(BlockSetType type, PressurePlateBlock.Sensitivity sensitivity) {
        String pressurePlateName = name + "_pressure_plate";
        Strings strings = new Strings();
        strings.blockname = pressurePlateName;
        strings.fullblockname = this.name1;
        strings.ezMaterial = ezMaterial;
        this.data.pressurePlateBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), pressurePlateName), new PressurePlateBlock(sensitivity, FabricBlockSettings.copyOf(blockProperties), type));
        this.data.pressurePlateItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), pressurePlateName), new BlockItem(this.data.pressurePlateBlock, new FabricItemSettings()));
        inventoryMap.put(pressurePlateName, data.pressurePlateItem);
        pressurePlateMap.put(strings, data.pressurePlateBlock);
        return this;
    }

    public EzBlocksBuilder carpet(Block blockProperties) {
        String carpetName = name + "_carpet";
        Strings strings = new Strings();
        strings.blockname = carpetName;
        strings.fullblockname = this.name1;
        strings.ezMaterial = ezMaterial;
        this.data.carpetBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), carpetName), new CarpetBlock(FabricBlockSettings.copyOf(blockProperties)));
        this.data.carpetItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), carpetName), new BlockItem(this.data.carpetBlock, new FabricItemSettings()));
        inventoryMap.put(carpetName, data.carpetItem);
        carpetMap.put(strings, data.carpetBlock);
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