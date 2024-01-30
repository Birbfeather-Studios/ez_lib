package net.distantdig.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class EzBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public EzBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {

        // Tool tags
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
            // Add blocks here
        ;

        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE)
            // Add blocks here
        ;

        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_SHOVEL)
            // Add blocks here
        ;

        // Material requirement tags
        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
            // Add blocks here
        ;

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
            // Add blocks here
        ;

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
            // Add blocks here
        ;
    }
}
