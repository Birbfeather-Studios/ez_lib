package net.distantdig.world;

import net.distantdig.EzLib;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class EzConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> RUBY_ORE_KEY = registerKey("ruby_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_RUBY_ORE_KEY = registerKey("nether_ruby_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_RUBY_ORE_KEY = registerKey("end_ruby_ore");

    public static void boostrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplacables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplacables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherReplacables = new TagMatchTest(BlockTags.BASE_STONE_NETHER);
        RuleTest endReplacables = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState> overworldRubyOres =
                List.of(OreConfiguration.target(stoneReplacables, Blocks.OAK_PLANKS.defaultBlockState()),
                        OreConfiguration.target(deepslateReplacables, Blocks.SPRUCE_PLANKS.defaultBlockState()));

        List<OreConfiguration.TargetBlockState> netherRubyOres =
                List.of(OreConfiguration.target(netherReplacables, Blocks.WARPED_PLANKS.defaultBlockState()));

        List<OreConfiguration.TargetBlockState> endRubyOres =
                List.of(OreConfiguration.target(endReplacables, Blocks.BIRCH_PLANKS.defaultBlockState()));

        register(context, RUBY_ORE_KEY, Feature.ORE, new OreConfiguration(overworldRubyOres, 12));
        register(context, NETHER_RUBY_ORE_KEY, Feature.ORE, new OreConfiguration(netherRubyOres, 12));
        register(context, END_RUBY_ORE_KEY, Feature.ORE, new OreConfiguration(endRubyOres, 12));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(EzLib.getModId(), name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                   ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
