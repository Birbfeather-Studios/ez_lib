package net.distantdig.ezLib;

import net.distantdig.ezLib.datagen.*;
import net.distantdig.ezLib.world.EzConfiguredFeatures;
import net.distantdig.ezLib.world.EzPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import org.slf4j.LoggerFactory;

public class EzLibDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(EzBlockTagProvider::new);
		pack.addProvider(EzItemTagProvider::new);
		pack.addProvider(EzLootTableProvider::new);
		pack.addProvider(EzModelProvider::new);
		pack.addProvider(EzRecipeProvider::new);
		pack.addProvider(EzLangProvider::new);
		pack.addProvider(EzWorldGenerator::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		LoggerFactory.getLogger(EzLib.getModId()).info("buildRegistry");
		registryBuilder.add(Registries.CONFIGURED_FEATURE, EzConfiguredFeatures::boostrap);
		registryBuilder.add(Registries.PLACED_FEATURE, EzPlacedFeatures::bootstrap);
	}
}
