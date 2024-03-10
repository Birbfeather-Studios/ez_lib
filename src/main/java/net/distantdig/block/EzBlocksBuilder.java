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
import java.util.List;
import java.util.function.Function;

public class EzBlocksBuilder {

    private final String name;
    private final Block blockProperties;
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
        public List<Block> extraBlocks = new ArrayList<>();
        public List<BlockItem> extraBlockItems = new ArrayList<>();
        public List<RotatedPillarBlock> extraColumns = new ArrayList<>();
        public List<BlockItem> extraColumnItems = new ArrayList<>();
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
        public Block buttonBlock;
        public BlockItem buttonItem;
    }

    public final static HashMap<String, BlockData> inventoryMap = new HashMap<>();
    public final static HashMap<String, BlockData> stairMap = new HashMap<>();

    public EzBlocksBuilder(String name, Block blockProperties) {
        this.name = name;
        this.blockProperties = blockProperties;
        this.data = new BlockData();
        data.block = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), name), new Block(FabricBlockSettings.copyOf(blockProperties)));
        data.blockItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), name), new BlockItem(data.block, new FabricItemSettings()));
        inventoryMap.put(name, data);
    }

    public EzBlocksBuilder stair() {
        String stairName = name + "_stair";
        this.data.stairBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), stairName), new StairBlock(data.block.defaultBlockState(), FabricBlockSettings.copyOf(blockProperties)));
        this.data.stairItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), stairName), new BlockItem(data.stairBlock, new FabricItemSettings()));
        inventoryMap.put(stairName, data);
        return this;
    }

    public EzBlocksBuilder slab() {
        String slabName = name + "_slab";
        this.data.slabBlock = register(slabName, SlabBlock::new, FabricBlockSettings.copyOf(blockProperties));
        this.data.slabItem = registerItem(slabName, data.slabBlock);
        inventoryMap.put(slabName, data);
        return this;
    }

    public EzBlocksBuilder verticalSlab() {
        String verticalSlabName = "vertical_" + name + "_slab";
        this.data.verticalSlabBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), verticalSlabName), new EzVerticalSlabBlock(data.block.defaultBlockState(), FabricBlockSettings.copyOf(blockProperties)));
        this.data.verticalSlabItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), verticalSlabName), new BlockItem(data.verticalSlabBlock, new FabricItemSettings()));
        inventoryMap.put(verticalSlabName, data);
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
        this.data.extraBlocks.add(extraBlock);
        this.data.extraBlockItems.add(extraItem);
        return this;
    }

    public EzBlocksBuilder pillar(String prefix, String suffix, @Nullable Block extraBlockProperties) {
        Block extraProperties = extraBlockProperties;
        String extraPillarName = prefix + name + suffix;
        if (extraBlockProperties == null) {
            extraProperties = this.blockProperties;
        }
        RotatedPillarBlock pillar = register(extraPillarName, RotatedPillarBlock::new, FabricBlockSettings.copyOf(extraProperties));
        BlockItem pillaritem = registerItem(extraPillarName, pillar);
        this.data.extraColumns.add(pillar);
        this.data.extraColumnItems.add(pillaritem);
        return this;
    }

    public EzBlocksBuilder door(BlockSetType blockSetType) {
        String doorName = name + "_door";
        this.data.doorBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), doorName), new DoorBlock(FabricBlockSettings.copyOf(blockProperties), blockSetType));
        this.data.doorItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), doorName), new BlockItem(this.data.doorBlock, new FabricItemSettings()));
        return this;
    }

    public EzBlocksBuilder trapdoor(BlockSetType blockSetType) {
        String trapdoorName = name + "_trapdoor";
        this.data.trapdoorBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), trapdoorName), new TrapDoorBlock(FabricBlockSettings.copyOf(blockProperties), blockSetType));
        this.data.trapdoorItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), trapdoorName), new BlockItem(this.data.trapdoorBlock, new FabricItemSettings()));
        return this;
    }

    public EzBlocksBuilder button(BlockSetType blockSetType, Integer ticksPressed, Boolean arrowHits) {
        String buttonName = name + "_button";
        this.data.buttonBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), buttonName), new ButtonBlock(FabricBlockSettings.copyOf(blockProperties), blockSetType, ticksPressed, arrowHits));
        this.data.buttonItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), buttonName), new BlockItem(this.data.buttonBlock, new FabricItemSettings()));
        return this;
    }


    public EzBlocksBuilder fence() {
        String fenceName = name + "_fence";
        this.data.fenceBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), fenceName), new FenceBlock(FabricBlockSettings.copyOf(blockProperties)));
        this.data.fenceItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), fenceName), new BlockItem(this.data.fenceBlock, new FabricItemSettings()));
        return this;
    }

    public EzBlocksBuilder fenceGate() {
        String fenceGateName = name + "_fence_gate";
        this.data.fenceGateBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), fenceGateName), new FenceGateBlock(FabricBlockSettings.copyOf(blockProperties), WoodType.OAK));
        this.data.fenceGateItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), fenceGateName), new BlockItem(this.data.fenceGateBlock, new FabricItemSettings()));
        return this;
    }

    public EzBlocksBuilder wall() {
        String wallName = name + "_wall";
        this.data.wallBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), wallName), new WallBlock(FabricBlockSettings.copyOf(blockProperties)));
        this.data.wallItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), wallName), new BlockItem(this.data.wallBlock, new FabricItemSettings()));
        return this;
    }

    public EzBlocksBuilder pressurePlate(BlockSetType type, PressurePlateBlock.Sensitivity sensitivity) {
        String pressurePlateName = name + "_pressure_plate";
        this.data.pressurePlateBlock = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(EzLib.getModId(), pressurePlateName), new PressurePlateBlock(sensitivity, FabricBlockSettings.copyOf(blockProperties), type));
    this.data.pressurePlateItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(EzLib.getModId(), pressurePlateName), new BlockItem(this.data.pressurePlateBlock, new FabricItemSettings()));
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