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

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;

public class EzRecipeProvider extends FabricRecipeProvider {
//    private static final List<ItemLike> GOLD_SMELTABLES = List.of(Items.DEEPSLATE_GOLD_ORE);

    // Item Recipe Lists
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
                VanillaRecipeProvider.planksFromLogs(exporter, EzBlocksBuilder.inventoryMap.get(strings.blockname), strings.tagKey, 4);
            }
        });

        EzBlocksBuilder.stairMap.forEach((strings, stairBlock) -> VanillaRecipeProvider
                .stairBuilder(EzBlocksBuilder.inventoryMap.get(strings.blockname), Ingredient.of(EzBlocksBuilder.inventoryMap.get(strings.fullblockname)))
                .unlockedBy("has_item", has(EzBlocksBuilder.inventoryMap.get(strings.fullblockname)))
                .save(exporter));

        EzBlocksBuilder.slabMap.forEach((strings, slabBlock) -> VanillaRecipeProvider
                .slabBuilder(RecipeCategory.BUILDING_BLOCKS, EzBlocksBuilder.inventoryMap.get(strings.blockname), Ingredient.of(EzBlocksBuilder.inventoryMap.get(strings.fullblockname)))
                .unlockedBy("has_item", has(EzBlocksBuilder.inventoryMap.get(strings.fullblockname)))
                .save(exporter));

        EzBlocksBuilder.verticalSlabMap.forEach((strings, ezVerticalSlabBlock) -> ShapedRecipeBuilder
                .shaped(RecipeCategory.BUILDING_BLOCKS, EzBlocksBuilder.inventoryMap.get(strings.blockname), 6)
                .define('#', EzBlocksBuilder.inventoryMap.get(strings.fullblockname))
                .pattern("#  ").pattern("#  ").pattern("#  ").unlockedBy("has_item", has(EzBlocksBuilder.inventoryMap.get(strings.fullblockname))).save(exporter));

        EzBlocksBuilder.wallMap.forEach((strings, wallBlock) -> VanillaRecipeProvider
                .wallBuilder(RecipeCategory.BUILDING_BLOCKS, EzBlocksBuilder.inventoryMap.get(strings.blockname), Ingredient.of(EzBlocksBuilder.inventoryMap.get(strings.fullblockname)))
                .unlockedBy("has_item", has(EzBlocksBuilder.inventoryMap.get(strings.fullblockname)))
                .save(exporter));

        EzBlocksBuilder.doorMap.forEach((strings, doorBlock) -> VanillaRecipeProvider
                .doorBuilder(EzBlocksBuilder.inventoryMap.get(strings.blockname), Ingredient.of(EzBlocksBuilder.inventoryMap.get(strings.fullblockname)))
                .unlockedBy("has_item", has(EzBlocksBuilder.inventoryMap.get(strings.fullblockname)))
                .save(exporter));

        EzBlocksBuilder.trapDoorMap.forEach((strings, trapDoorBlock) -> {
            if (strings.hasWall) {
                VanillaRecipeProvider
                        .trapdoorBuilder(EzBlocksBuilder.inventoryMap.get(strings.blockname), Ingredient.of(EzBlocksBuilder.inventoryMap.get(strings.fullblockname + "_slab")))
                        .unlockedBy("has_item", has(EzBlocksBuilder.inventoryMap.get(strings.fullblockname)))
                        .save(exporter);
            } else {
                VanillaRecipeProvider
                        .trapdoorBuilder(EzBlocksBuilder.inventoryMap.get(strings.blockname), Ingredient.of(EzBlocksBuilder.inventoryMap.get(strings.fullblockname)))
                        .unlockedBy("has_item", has(EzBlocksBuilder.inventoryMap.get(strings.fullblockname)))
                        .save(exporter);
            }
        });


        EzBlocksBuilder.fenceMap.forEach((strings, fenceBlock) -> VanillaRecipeProvider
                .fenceBuilder(EzBlocksBuilder.inventoryMap.get(strings.blockname), Ingredient.of(EzBlocksBuilder.inventoryMap.get(strings.fullblockname)))
                .unlockedBy("has_item", has(EzBlocksBuilder.inventoryMap.get(strings.fullblockname)))
                .save(exporter));

        EzBlocksBuilder.fenceGateMap.forEach((strings, fenceGateBlock) -> VanillaRecipeProvider
                .fenceGateBuilder(EzBlocksBuilder.inventoryMap.get(strings.blockname), Ingredient.of(EzBlocksBuilder.inventoryMap.get(strings.fullblockname)))
                .unlockedBy("has_item", has(EzBlocksBuilder.inventoryMap.get(strings.fullblockname)))
                .save(exporter));

        EzBlocksBuilder.pressurePlateMap.forEach((strings, pressurePlateBlock) -> VanillaRecipeProvider
                .pressurePlateBuilder(RecipeCategory.REDSTONE, EzBlocksBuilder.inventoryMap.get(strings.blockname), Ingredient.of(EzBlocksBuilder.inventoryMap.get(strings.fullblockname)))
                .unlockedBy("has_item", has(EzBlocksBuilder.inventoryMap.get(strings.fullblockname)))
                .save(exporter));

        EzBlocksBuilder.buttonMap.forEach((strings, buttonBlock) -> VanillaRecipeProvider
                .buttonBuilder(EzBlocksBuilder.inventoryMap.get(strings.blockname), Ingredient.of(EzBlocksBuilder.inventoryMap.get(strings.fullblockname)))
                .unlockedBy("has_item", has(EzBlocksBuilder.inventoryMap.get(strings.fullblockname)))
                .save(exporter));

        EzBlocksBuilder.carpetMap.forEach((strings, carpetBlock) -> VanillaRecipeProvider
                .carpet(exporter, EzBlocksBuilder.inventoryMap.get(strings.blockname), EzBlocksBuilder.inventoryMap.get(strings.fullblockname)));

        //rotated pillar -> because there can be mulitple blocks of this, the player should probably make a recipe themselves


        //wood
        EzBlocksBuilder.woodMap.forEach((strings, woodblock) -> VanillaRecipeProvider
                .woodFromLogs(exporter, EzBlocksBuilder.inventoryMap.get(strings.blockname), EzBlocksBuilder.inventoryMap.get(strings.fullblockname)));

    }
}
