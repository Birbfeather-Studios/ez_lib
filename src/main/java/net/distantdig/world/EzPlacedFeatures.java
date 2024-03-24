package net.distantdig.world;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class EzPlacedFeatures {
    public static final ResourceKey<PlacedFeature> WOOD_ORE_PLACED = registerKey("wood_ore_placed");
    public static final ResourceKey<PlacedFeature> NETHER_WOOD_ORE_PLACED = registerKey("nether_wood_ore_placed");
    public static final ResourceKey<PlacedFeature> END_WOOD_ORE_PLACED = registerKey("end_wood_ore_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        var EntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, WOOD_ORE_PLACED, EntryLookup.getOrThrow(EzConfiguredFeatures.RUBY_ORE_KEY),
                EzOrePlacement.commonOrePlacement(12, //viens per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))));

        register(context, NETHER_WOOD_ORE_PLACED, EntryLookup.getOrThrow(EzConfiguredFeatures.NETHER_RUBY_ORE_KEY),
                EzOrePlacement.commonOrePlacement(12, //viens per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))));

        register(context, END_WOOD_ORE_PLACED, EntryLookup.getOrThrow(EzConfiguredFeatures.END_RUBY_ORE_KEY),
                EzOrePlacement.commonOrePlacement(12, //viens per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))));
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation("other_mod", name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> config,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(config, List.copyOf(modifiers)));
    }
}
