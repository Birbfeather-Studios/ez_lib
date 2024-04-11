package net.distantdig.ezLib;

import net.distantdig.ezLib.datagen.*;
import net.distantdig.ezLib.world.EzConfiguredFeatures;
import net.distantdig.ezLib.world.EzPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import org.jetbrains.annotations.Nullable;

public class EzLibDataGenerator implements DataGeneratorEntrypoint {

	protected static String MOD_ID;

	public static String getModId() {
		return MOD_ID;
	}

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
	public @Nullable String getEffectiveModId() {
		String modId = EzLib.getModId();
		if (FabricLoader.getInstance().getModContainer(modId).isPresent()) {
			EzLibDataGenerator.MOD_ID = modId;
			return modId;
		} else {
			EzLibDataGenerator.MOD_ID = "ez_lib";
			return null;
		}
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.CONFIGURED_FEATURE, EzConfiguredFeatures::boostrap);
		registryBuilder.add(Registries.PLACED_FEATURE, EzPlacedFeatures::bootstrap);
	}
}
