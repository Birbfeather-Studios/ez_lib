package net.distantdig.ezLib.world;

import net.distantdig.ezLib.EzLibDataGenerator;
import net.distantdig.ezLib.block.EzBlocksBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class EzPlacedFeatures {

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        var EntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        EzBlocksBuilder.oreMap.forEach((String, OreData) -> {
            ResourceKey<PlacedFeature> ORE_PLACED_KEY = registerKey(String);
            OreData.orePlacedKey = ORE_PLACED_KEY;

            register(context, ORE_PLACED_KEY, EntryLookup.getOrThrow(OreData.oreKey),
                    EzOrePlacement.commonOrePlacement(OreData.veinsPerChunk, //veins per chunk
                            OreData.heightRangePlacement));
        });
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(EzLibDataGenerator.getModId(), name));
    }

    public static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> config,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(config, List.copyOf(modifiers)));
    }
}
