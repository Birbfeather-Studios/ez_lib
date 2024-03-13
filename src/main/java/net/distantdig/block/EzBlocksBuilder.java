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
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Function;

public class EzBlocksBuilder {

    private final String name;
    private final Block blockProperties;
    private final String name1;
    private BlockData data;

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
        public String one;
        public String two;
        public String three;
        public String four;
    }


    public final static HashMap<String, BlockItem> inventoryMap = new HashMap<>();
    public final static HashMap<String, Block> blockMap = new HashMap<>();
    public final static HashMap<Strings, StairBlock> stairMap = new HashMap<>();
    public final static HashMap<Strings, SlabBlock> slabMap = new HashMap<>();
    public final static HashMap<Strings, EzVerticalSlabBlock> verticalSlabMap = new HashMap<>();
    public final static HashMap<Strings, WallBlock> wallMap = new HashMap<>();
    public final static HashMap<String, DoorBlock> doorMap = new HashMap<>();
    public final static HashMap<String, TrapDoorBlock> trapDoorMap = new HashMap<>();
    public final static HashMap<Strings, FenceBlock> fenceMap = new HashMap<>();
    public final static HashMap<Strings, FenceGateBlock> fenceGateMap = new HashMap<>();
    public final static HashMap<Strings, PressurePlateBlock> pressurePlateMap = new HashMap<>();
    public final static HashMap<Strings, ButtonBlock> buttonMap = new HashMap<>();
    public final static HashMap<Strings, CarpetBlock> carpetMap = new HashMap<>();
    public final static HashMap<String, RotatedPillarBlock> rotatedPillarMap = new HashMap<>();
    public final static HashMap<String, LeavesBlock> leavesMap = new HashMap<>();
    public final static ArrayList<Block> pickaxable = new ArrayList<>();
    public final static ArrayList<Block> axable = new ArrayList<>();
    public final static ArrayList<Block> shovolable = new ArrayList<>();
    public final static HashMap<Strings, RotatedPillarBlock> woodMap = new HashMap<>();

    public EzBlocksBuilder(String name, Block blockProperties, @Nullable String suffix) {

        this.name = name;
        this.name1 = (suffix != null) ? name + suffix : name;
        this.blockProperties = blockProperties;
        this.data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), this.name1), new Block(FabricBlockSettings.copyOf(blockProperties)));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), this.name1), new BlockItem(data.block, new FabricItemSettings()));
        blockMap.put(name1, data.block);
        inventoryMap.put(name1, data.blockItem);
    }

    public EzBlocksBuilder stair() {
        String stairName = name + "_stair";
        Strings strings = new Strings();
        strings.one = stairName;
        strings.two = name1;
        this.data.stairBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), stairName), new StairBlock(data.block.defaultBlockState(), FabricBlockSettings.copyOf(blockProperties)));
        this.data.stairItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), stairName), new BlockItem(data.stairBlock, new FabricItemSettings()));
        inventoryMap.put(stairName, data.stairItem);
        stairMap.put(strings, data.stairBlock);
        return this;
    }

    public EzBlocksBuilder slab() {
        String slabName = name + "_slab";
        Strings strings = new Strings();
        strings.one = slabName;
        strings.two = name1;
        this.data.slabBlock = register(slabName, SlabBlock::new, FabricBlockSettings.copyOf(blockProperties));
        this.data.slabItem = registerItem(slabName, data.slabBlock);
        inventoryMap.put(slabName, data.slabItem);
        slabMap.put(strings, data.slabBlock);
        return this;
    }

    public EzBlocksBuilder verticalSlab() {
        String verticalSlabName = "vertical_" + name + "_slab";
        Strings strings = new Strings();
        strings.one = verticalSlabName;
        strings.two = this.name1;
        this.data.verticalSlabBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), verticalSlabName), new EzVerticalSlabBlock(FabricBlockSettings.copyOf(blockProperties)));
        this.data.verticalSlabItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), verticalSlabName), new BlockItem(data.verticalSlabBlock, new FabricItemSettings()));
        inventoryMap.put(verticalSlabName, data.verticalSlabItem);
        verticalSlabMap.put(strings, data.verticalSlabBlock);
        return this;
    }

    public EzBlocksBuilder extraBlock(String prefix, String suffix, @Nullable Block extraBlockProperties) {
        Block extraProperies = extraBlockProperties;
        String extraBlockName = prefix + name + suffix;
        if (extraBlockProperties == null) {
            extraProperies = this.blockProperties;
        }
        Block extraBlock = register(extraBlockName, Block::new, FabricBlockSettings.copyOf(extraProperies));
        BlockItem extraItem = registerItem(extraBlockName, extraBlock);
        this.data.extraBlocks.put(extraBlock, extraItem);
        inventoryMap.put(extraBlockName, extraItem);
        blockMap.put(extraBlockName, extraBlock);
        return this;
    }

    public EzBlocksBuilder leaves(@Nullable String prefix, @Nullable String suffix) {
        String leavesName = prefix + this.name + suffix + "_leaves";
        this.data.leavesBlock = register(leavesName, LeavesBlock::new, FabricBlockSettings.copyOf(Blocks.OAK_LEAVES));
        this.data.leavesItem = registerItem(leavesName, data.leavesBlock);
        inventoryMap.put(leavesName, data.leavesItem);
        leavesMap.put(leavesName, data.leavesBlock);
        return this;
    }

    public EzBlocksBuilder pillar(String prefix, String suffix, @Nullable Block extraBlockProperties) {
        Block extraProperties = extraBlockProperties;
        String extraPillarName = prefix + name + suffix;
        if (extraBlockProperties == null) {
            extraProperties = this.blockProperties;
        }
        RotatedPillarBlock pillar = register(extraPillarName, RotatedPillarBlock::new, FabricBlockSettings.copyOf(extraProperties));
        BlockItem pillarItem = registerItem(extraPillarName, pillar);
        this.data.extraColumns.put(pillar, pillarItem);
        inventoryMap.put(extraPillarName, pillarItem);
        rotatedPillarMap.put(extraPillarName, pillar);
        return this;
    }

    public EzBlocksBuilder wood(String pillarname, String strippedName) {
        Strings strings = new Strings();
        strings.one = name + "_wood";
        strings.two = "stripped_" + name + "_wood";
        strings.three = pillarname;
        strings.four = strippedName;
        RotatedPillarBlock woodBlock = register(strings.one, RotatedPillarBlock::new, FabricBlockSettings.copyOf(Blocks.OAK_WOOD));
        RotatedPillarBlock strippedwoodBlock = register(strings.two, RotatedPillarBlock::new, FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD));
        BlockItem woodItem = registerItem(strings.one, woodBlock);
        BlockItem strippedwoodItem = registerItem(strings.two, strippedwoodBlock);
        inventoryMap.put(strings.one, woodItem);
        inventoryMap.put(strings.two, strippedwoodItem);
        woodMap.put(strings, woodBlock);
        woodMap.put(strings, strippedwoodBlock);
        return this;
    }

    public EzBlocksBuilder door(BlockSetType blockSetType, Block blockProperties) {
        String doorName = name + "_door";
        this.data.doorBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), doorName), new DoorBlock(FabricBlockSettings.copyOf(blockProperties), blockSetType));
        this.data.doorItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), doorName), new BlockItem(this.data.doorBlock, new FabricItemSettings()));
        inventoryMap.put(doorName, data.doorItem);
        doorMap.put(doorName, data.doorBlock);
        return this;
    }

    public EzBlocksBuilder trapdoor(BlockSetType blockSetType, Block blockProperties) {
        String trapdoorName = name + "_trapdoor";
        this.data.trapdoorBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), trapdoorName), new TrapDoorBlock(FabricBlockSettings.copyOf(blockProperties), blockSetType));
        this.data.trapdoorItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), trapdoorName), new BlockItem(this.data.trapdoorBlock, new FabricItemSettings()));
        inventoryMap.put(trapdoorName, data.trapdoorItem);
        trapDoorMap.put(trapdoorName, data.trapdoorBlock);
        return this;
    }

    public EzBlocksBuilder button(BlockSetType blockSetType, Integer ticksPressed, Boolean arrowHits) {
        String buttonName = name + "_button";
        Strings strings = new Strings();
        strings.one = buttonName;
        strings.two = this.name1;
        this.data.buttonBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), buttonName), new ButtonBlock(FabricBlockSettings.copyOf(blockProperties), blockSetType, ticksPressed, arrowHits));
        this.data.buttonItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), buttonName), new BlockItem(this.data.buttonBlock, new FabricItemSettings()));
        inventoryMap.put(buttonName, data.buttonItem);
        buttonMap.put(strings, data.buttonBlock);
        return this;
    }

    public EzBlocksBuilder fence() {
        String fenceName = name + "_fence";
        Strings strings = new Strings();
        strings.one = fenceName;
        strings.two = this.name1;
        this.data.fenceBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), fenceName), new FenceBlock(FabricBlockSettings.copyOf(blockProperties)));
        this.data.fenceItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), fenceName), new BlockItem(this.data.fenceBlock, new FabricItemSettings()));
        inventoryMap.put(fenceName, data.fenceItem);
        fenceMap.put(strings, data.fenceBlock);
        return this;
    }

    public EzBlocksBuilder fenceGate() {
        String fenceGateName = name + "_fence_gate";
        Strings strings = new Strings();
        strings.one = fenceGateName;
        strings.two = this.name1;
        this.data.fenceGateBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), fenceGateName), new FenceGateBlock(FabricBlockSettings.copyOf(blockProperties), WoodType.OAK));
        this.data.fenceGateItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), fenceGateName), new BlockItem(this.data.fenceGateBlock, new FabricItemSettings()));
        inventoryMap.put(fenceGateName, data.fenceGateItem);
        fenceGateMap.put(strings, data.fenceGateBlock);
        return this;
    }

    public EzBlocksBuilder wall() {
        String wallName = name + "_wall";
        Strings strings = new Strings();
        strings.one = wallName;
        strings.two = this.name1;
        this.data.wallBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), wallName), new WallBlock(FabricBlockSettings.copyOf(blockProperties)));
        this.data.wallItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), wallName), new BlockItem(this.data.wallBlock, new FabricItemSettings()));
        inventoryMap.put(wallName, data.wallItem);
        wallMap.put(strings, data.wallBlock);
        return this;
    }

    public EzBlocksBuilder pressurePlate(BlockSetType type, PressurePlateBlock.Sensitivity sensitivity) {
        String pressurePlateName = name + "_pressure_plate";
        Strings strings = new Strings();
        strings.one = pressurePlateName;
        strings.two = this.name1;
        this.data.pressurePlateBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), pressurePlateName), new PressurePlateBlock(sensitivity, FabricBlockSettings.copyOf(blockProperties), type));
        this.data.pressurePlateItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), pressurePlateName), new BlockItem(this.data.pressurePlateBlock, new FabricItemSettings()));
        inventoryMap.put(pressurePlateName, data.pressurePlateItem);
        pressurePlateMap.put(strings, data.pressurePlateBlock);
        return this;
    }

    public EzBlocksBuilder carpet() {
        String carpetName = name + "_carpet";
        Strings strings = new Strings();
        strings.one = carpetName;
        strings.two = this.name1;
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
        Optional.ofNullable(data.leavesBlock).ifPresent(pickaxable::add);
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
        Optional.ofNullable(data.leavesBlock).ifPresent(axable::add);
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
        Optional.ofNullable(data.leavesBlock).ifPresent(shovolable::add);
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

    //Foundation methods required for making the code easyer and less repetitive above.
    public <T extends Block> T register(String name, Function<BlockBehaviour.Properties, T> constructor, BlockBehaviour.Properties blockProperties) {
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), name), constructor.apply(blockProperties));
    }

    public <T extends Block> BlockItem registerItem(String name, Block block) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), name), new BlockItem(block, new FabricItemSettings()));
    }
}