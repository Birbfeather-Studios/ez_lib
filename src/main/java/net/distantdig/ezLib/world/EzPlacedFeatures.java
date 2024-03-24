package net.distantdig.ezLib.world;

import net.distantdig.ezLib.EzLib;
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
    public static final ResourceKey<PlacedFeature> BIRT_ORE_PLACED = registerKey("birt_ore_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        var EntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, BIRT_ORE_PLACED, EntryLookup.getOrThrow(EzConfiguredFeatures.BIRT_KEY),
                EzOrePlacement.commonOrePlacement(12, //viens per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))));
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(EzLib.getModId(), name));
    }

    public static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> config,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(config, List.copyOf(modifiers)));
    }
}
