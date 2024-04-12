package net.distantdig.ezLib.world;

import net.distantdig.ezLib.EzLibDataGenerator;
import net.distantdig.ezLib.block.EzBlocksBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

import java.util.List;

public class EzConfiguredFeatures {

    public static void boostrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        EzBlocksBuilder.oreMap.forEach((String, OreData) -> {
            ResourceKey<ConfiguredFeature<?, ?>> ORE_KEY = registerKey(String);
            OreData.oreKey = registerKey(String);

            List<OreConfiguration.TargetBlockState> OreList = List.of(OreConfiguration.target(OreData.ruleTest, OreData.oreBlock.defaultBlockState()));

            register(context, ORE_KEY, Feature.ORE, new OreConfiguration(OreList, OreData.veinSize));
        });
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(EzLibDataGenerator.getModId(), name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(
            BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
