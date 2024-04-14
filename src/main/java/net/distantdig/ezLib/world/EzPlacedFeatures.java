package net.distantdig.ezLib.world;

import net.distantdig.ezLib.block.EzBlocksBuilder;
import net.distantdig.ezLib.util.EzUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

import static net.distantdig.ezLib.block.EzBlocksBuilder.oreMap;

public class EzPlacedFeatures {

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        var EntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        oreMap.forEach((String, OreData) -> {
//            OreData.orePlacedKey = registerKey(String + "_placed_key");

            register(context, EzBlocksBuilder.OreData.getOrePlacedKey(OreData.orePlacedKey),
                    EntryLookup.getOrThrow(EzBlocksBuilder.OreData.getOreKey(OreData.oreKey)),
                    EzOrePlacement.commonOrePlacement(OreData.veinsPerChunk, //veins per chunk
                            OreData.heightRangePlacement));
        });
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(EzUtils.checkModContainerId(), name));
    }

    public static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> config,
                                List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(config, List.copyOf(modifiers)));
    }
}
