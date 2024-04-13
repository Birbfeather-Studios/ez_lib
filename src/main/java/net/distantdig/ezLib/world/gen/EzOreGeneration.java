package net.distantdig.ezLib.world.gen;

import net.distantdig.ezLib.block.EzBlocksBuilder;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.levelgen.GenerationStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.distantdig.ezLib.block.EzBlocksBuilder.*;

public class EzOreGeneration {
    public static void generateOres() {

        EzBlocksBuilder.oreMap.forEach((String, OreData) -> {
            if (OreData.ruleTest.equals(stoneReplacables) || OreData.ruleTest.equals(deepslateReplacables)) {
                BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                        GenerationStep.Decoration.UNDERGROUND_ORES, OreData.orePlacedKey);
            } else if (OreData.ruleTest.equals(netherReplacables)) {
                BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(),
                        GenerationStep.Decoration.UNDERGROUND_ORES, OreData.orePlacedKey);
            } else if (OreData.ruleTest.equals(endReplacables)) {
                BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(),
                        GenerationStep.Decoration.UNDERGROUND_ORES, OreData.orePlacedKey);
            }
        });
    }
}
