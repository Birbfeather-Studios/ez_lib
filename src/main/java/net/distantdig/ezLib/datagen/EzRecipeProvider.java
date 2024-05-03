package net.distantdig.ezLib.datagen;

import net.distantdig.ezLib.block.EzBlocksBuilder;
import net.distantdig.ezLib.item.EzItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;

public class EzRecipeProvider extends FabricRecipeProvider {
//    private static final List<ItemLike> GOLD_SMELTABLES = List.of(Items.DEEPSLATE_GOLD_ORE);

    public static class BlockPair {
        public Block parent;
        public Block block;
    }

    // Item Recipe Lists
    public static ArrayList<BlockPair> stoneBlockRecipeList = new ArrayList<>();
    public static ArrayList<BlockPair> stairStoneRecipeList = new ArrayList<>();
    public static ArrayList<BlockPair> slabStoneRecipeList = new ArrayList<>();
    public static ArrayList<BlockPair> verticleSlabStoneRecipeList = new ArrayList<>();
    public static ArrayList<BlockPair> wallRecipeList = new ArrayList<>();

    public static ArrayList<EzItems.ToolData<SwordItem>> swordRecipeList = new ArrayList<>();
    public static ArrayList<EzItems.ToolData<PickaxeItem>> pickaxeRecipeList = new ArrayList<>();
    public static ArrayList<EzItems.ToolData<AxeItem>> axeRecipeList = new ArrayList<>();
    public static ArrayList<EzItems.ToolData<ShovelItem>> shovelRecipeList = new ArrayList<>();
    public static ArrayList<EzItems.ToolData<HoeItem>> hoeRecipeList = new ArrayList<>();

    public static ArrayList<EzItems.ArmorData> helmetRecipeList = new ArrayList<>();
    public static ArrayList<EzItems.ArmorData> chestplateRecipeList = new ArrayList<>();
    public static ArrayList<EzItems.ArmorData> leggingsRecipeList = new ArrayList<>();
    public static ArrayList<EzItems.ArmorData> bootsRecipeList = new ArrayList<>();

    public static ArrayList<EzItems.ToolData> smithingToolRecipeList = new ArrayList<>();
    public static ArrayList<EzItems.ArmorData> smithingArmorRecipeList = new ArrayList<>();

    public EzRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> exporter) {
//        oreSmelting(exporter, GOLD_SMELTABLES, RecipeCategory.MISC, Items.RAW_GOLD, 0.7f, 200, "gold2");
//        oreBlasting(exporter, GOLD_SMELTABLES, RecipeCategory.MISC, Items.RAW_GOLD, 0.7f, 200, "gold2");

        // Stone Cutter Recipes
        stoneBlockRecipeList.forEach((blockPair -> stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, blockPair.block.asItem(), blockPair.parent.asItem(), 1)));
        stairStoneRecipeList.forEach((blockPair -> stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, blockPair.block.asItem(), blockPair.parent.asItem(), 1)));
        slabStoneRecipeList.forEach((blockPair -> stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, blockPair.block.asItem(), blockPair.parent.asItem(), 2)));
        verticleSlabStoneRecipeList.forEach((blockPair -> stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, blockPair.block.asItem(), blockPair.parent.asItem(), 2)));
        wallRecipeList.forEach((blockPair -> stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, blockPair.block.asItem(), blockPair.parent.asItem(), 1)));

        // Tool Crafting Recipes
        swordRecipeList.forEach((data) -> ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, data.toolItem, 1)
                .pattern("R")
                .pattern("R")
                .pattern("S")
                .define('R', data.repairItem)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(data.repairItem), has(data.repairItem))
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(exporter, new ResourceLocation(getSimpleRecipeName(data.toolItem) + "_from_" + getSimpleRecipeName(data.repairItem))));

        pickaxeRecipeList.forEach((data) -> ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, data.toolItem, 1)
                .pattern("RRR")
                .pattern(" S ")
                .pattern(" S ")
                .define('R', data.repairItem)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(data.repairItem), has(data.repairItem))
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(exporter, new ResourceLocation(getSimpleRecipeName(data.toolItem) + "_from_" + getSimpleRecipeName(data.repairItem))));

        axeRecipeList.forEach((data) -> ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, data.toolItem, 1)
                .pattern("RR")
                .pattern("SR")
                .pattern("S ")
                .define('R', data.repairItem)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(data.repairItem), has(data.repairItem))
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(exporter, new ResourceLocation(getSimpleRecipeName(data.toolItem) + "_from_" + getSimpleRecipeName(data.repairItem))));

        shovelRecipeList.forEach((data) -> ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, data.toolItem, 1)
                .pattern("R")
                .pattern("S")
                .pattern("S")
                .define('R', data.repairItem)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(data.repairItem), has(data.repairItem))
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(exporter, new ResourceLocation(getSimpleRecipeName(data.toolItem) + "_from_" + getSimpleRecipeName(data.repairItem))));

        hoeRecipeList.forEach((data) -> ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, data.toolItem, 1)
                .pattern("RR")
                .pattern("S ")
                .pattern("S ")
                .define('R', data.repairItem)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(data.repairItem), has(data.repairItem))
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(exporter, new ResourceLocation(getSimpleRecipeName(data.toolItem) + "_from_" + getSimpleRecipeName(data.repairItem))));

        // Armor Crafting Recipes
        helmetRecipeList.forEach((data) -> ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, data.armorItem, 1)
                .pattern("RRR")
                .pattern("R R")
                .define('R', data.repairItem)
                .unlockedBy(getHasName(data.repairItem), has(data.repairItem))
                .save(exporter, new ResourceLocation(getSimpleRecipeName(data.armorItem) + "_from_" + getSimpleRecipeName(data.repairItem))));

        chestplateRecipeList.forEach((data) -> ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, data.armorItem, 1)
                .pattern("R R")
                .pattern("RRR")
                .pattern("RRR")
                .define('R', data.repairItem)
                .unlockedBy(getHasName(data.repairItem), has(data.repairItem))
                .save(exporter, new ResourceLocation(getSimpleRecipeName(data.armorItem) + "_from_" + getSimpleRecipeName(data.repairItem))));

        leggingsRecipeList.forEach((data) -> ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, data.armorItem, 1)
                .pattern("RRR")
                .pattern("R R")
                .pattern("R R")
                .define('R', data.repairItem)
                .unlockedBy(getHasName(data.repairItem), has(data.repairItem))
                .save(exporter, new ResourceLocation(getSimpleRecipeName(data.armorItem) + "_from_" + getSimpleRecipeName(data.repairItem))));

        bootsRecipeList.forEach((data) -> ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, data.armorItem, 1)
                .pattern("R R")
                .pattern("R R")
                .define('R', data.repairItem)
                .unlockedBy(getHasName(data.repairItem), has(data.repairItem))
                .save(exporter, new ResourceLocation(getSimpleRecipeName(data.armorItem) + "_from_" + getSimpleRecipeName(data.repairItem))));

        // Smithing Recipes
        smithingToolRecipeList.forEach((data -> SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(data.previousTool),
                        Ingredient.of(data.repairItem),
                        RecipeCategory.TOOLS, data.toolItem)
                .unlocks(getHasName(data.repairItem), has(data.repairItem))
                .save(exporter, getItemName(data.toolItem) + "_smithing")));

        smithingArmorRecipeList.forEach((data -> SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(data.previousArmor),
                        Ingredient.of(data.repairItem),
                        RecipeCategory.TOOLS, data.armorItem)
                .unlocks(getHasName(data.repairItem), has(data.repairItem))
                .save(exporter, getItemName(data.armorItem) + "_smithing")));

        //blocks

        //block -> because there can be multiple blocks of these, and they can be associated in different ways players should probably make these themselves
        //planks
        EzBlocksBuilder.blockMap.forEach((block, strings) -> {
            if (strings.ezMaterial == EzBlocksBuilder.EzMaterial.wood & Objects.equals(strings.blockname, strings.fullblockname)) {
                VanillaRecipeProvider.planksFromLogs(exporter, EzBlocksBuilder.itemMap.get(strings.blockname), strings.tagKey, 4);
            }
        });

        EzBlocksBuilder.stairMap.forEach((strings, stairBlock) -> VanillaRecipeProvider
                .stairBuilder(EzBlocksBuilder.itemMap.get(strings.blockname), Ingredient.of(EzBlocksBuilder.itemMap.get(strings.fullblockname)))
                .unlockedBy("has_item", has(EzBlocksBuilder.itemMap.get(strings.fullblockname)))
                .save(exporter));

        EzBlocksBuilder.slabMap.forEach((strings, slabBlock) -> VanillaRecipeProvider
                .slabBuilder(RecipeCategory.BUILDING_BLOCKS, EzBlocksBuilder.itemMap.get(strings.blockname), Ingredient.of(EzBlocksBuilder.itemMap.get(strings.fullblockname)))
                .unlockedBy("has_item", has(EzBlocksBuilder.itemMap.get(strings.fullblockname)))
                .save(exporter));

        EzBlocksBuilder.verticalSlabMap.forEach((strings, ezVerticalSlabBlock) -> ShapedRecipeBuilder
                .shaped(RecipeCategory.BUILDING_BLOCKS, EzBlocksBuilder.itemMap.get(strings.blockname), 6)
                .define('#', EzBlocksBuilder.itemMap.get(strings.fullblockname))
                .pattern("#  ").pattern("#  ").pattern("#  ").unlockedBy("has_item", has(EzBlocksBuilder.itemMap.get(strings.fullblockname))).save(exporter));

        EzBlocksBuilder.wallMap.forEach((strings, wallBlock) -> VanillaRecipeProvider
                .wallBuilder(RecipeCategory.BUILDING_BLOCKS, EzBlocksBuilder.itemMap.get(strings.blockname), Ingredient.of(EzBlocksBuilder.itemMap.get(strings.fullblockname)))
                .unlockedBy("has_item", has(EzBlocksBuilder.itemMap.get(strings.fullblockname)))
                .save(exporter));

        EzBlocksBuilder.doorMap.forEach((strings, doorBlock) -> VanillaRecipeProvider
                .doorBuilder(EzBlocksBuilder.itemMap.get(strings.blockname), Ingredient.of(EzBlocksBuilder.itemMap.get(strings.fullblockname)))
                .unlockedBy("has_item", has(EzBlocksBuilder.itemMap.get(strings.fullblockname)))
                .save(exporter));

        EzBlocksBuilder.trapDoorMap.forEach((strings, trapDoorBlock) -> {
            if (strings.hasWall) {
                VanillaRecipeProvider
                        .trapdoorBuilder(EzBlocksBuilder.itemMap.get(strings.blockname), Ingredient.of(EzBlocksBuilder.itemMap.get(strings.fullblockname + "_slab")))
                        .unlockedBy("has_item", has(EzBlocksBuilder.itemMap.get(strings.fullblockname)))
                        .save(exporter);
            } else {
                VanillaRecipeProvider
                        .trapdoorBuilder(EzBlocksBuilder.itemMap.get(strings.blockname), Ingredient.of(EzBlocksBuilder.itemMap.get(strings.fullblockname)))
                        .unlockedBy("has_item", has(EzBlocksBuilder.itemMap.get(strings.fullblockname)))
                        .save(exporter);
            }
        });


        EzBlocksBuilder.fenceMap.forEach((strings, fenceBlock) -> VanillaRecipeProvider
                .fenceBuilder(EzBlocksBuilder.itemMap.get(strings.blockname), Ingredient.of(EzBlocksBuilder.itemMap.get(strings.fullblockname)))
                .unlockedBy("has_item", has(EzBlocksBuilder.itemMap.get(strings.fullblockname)))
                .save(exporter));

        EzBlocksBuilder.fenceGateMap.forEach((strings, fenceGateBlock) -> VanillaRecipeProvider
                .fenceGateBuilder(EzBlocksBuilder.itemMap.get(strings.blockname), Ingredient.of(EzBlocksBuilder.itemMap.get(strings.fullblockname)))
                .unlockedBy("has_item", has(EzBlocksBuilder.itemMap.get(strings.fullblockname)))
                .save(exporter));

        EzBlocksBuilder.pressurePlateMap.forEach((strings, pressurePlateBlock) -> VanillaRecipeProvider
                .pressurePlateBuilder(RecipeCategory.REDSTONE, EzBlocksBuilder.itemMap.get(strings.blockname), Ingredient.of(EzBlocksBuilder.itemMap.get(strings.fullblockname)))
                .unlockedBy("has_item", has(EzBlocksBuilder.itemMap.get(strings.fullblockname)))
                .save(exporter));

        EzBlocksBuilder.buttonMap.forEach((strings, buttonBlock) -> VanillaRecipeProvider
                .buttonBuilder(EzBlocksBuilder.itemMap.get(strings.blockname), Ingredient.of(EzBlocksBuilder.itemMap.get(strings.fullblockname)))
                .unlockedBy("has_item", has(EzBlocksBuilder.itemMap.get(strings.fullblockname)))
                .save(exporter));

        EzBlocksBuilder.carpetMap.forEach((strings, carpetBlock) -> VanillaRecipeProvider
                .carpet(exporter, EzBlocksBuilder.itemMap.get(strings.blockname), EzBlocksBuilder.itemMap.get(strings.fullblockname)));

        //rotated pillar -> because there can be mulitple blocks of this, the player should probably make a recipe themselves


        //wood
        EzBlocksBuilder.woodMap.forEach((strings, woodblock) -> VanillaRecipeProvider
                .woodFromLogs(exporter, EzBlocksBuilder.itemMap.get(strings.blockname), EzBlocksBuilder.itemMap.get(strings.fullblockname)));

    }
}
