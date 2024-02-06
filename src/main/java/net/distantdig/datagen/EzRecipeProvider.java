package net.distantdig.datagen;

import net.distantdig.item.EzItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
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

    public static ArrayList<EzItems.ToolData> smithingRecipeList = new ArrayList<>();

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

        // Tool Smithing Recipes
        smithingRecipeList.forEach((data -> SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                Ingredient.of(data.previousTool),
                Ingredient.of(data.repairItem),
                RecipeCategory.TOOLS, data.toolItem)
                .unlocks(getHasName(data.repairItem), has(data.repairItem))
                .save(exporter, getItemName(data.toolItem) + "_smithing")
    ));
    }
}
