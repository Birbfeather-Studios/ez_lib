package net.distantdig.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class EzRecipeProvider extends FabricRecipeProvider {
//    private static final List<ItemLike> GOLD_SMELTABLES = List.of(Items.DEEPSLATE_GOLD_ORE);
    public EzRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> exporter) {
//        oreSmelting(exporter, GOLD_SMELTABLES, RecipeCategory.MISC, Items.RAW_GOLD, 0.7f, 200, "gold2");
//        oreBlasting(exporter, GOLD_SMELTABLES, RecipeCategory.MISC, Items.RAW_GOLD, 0.7f, 200, "gold2");
    }
}
