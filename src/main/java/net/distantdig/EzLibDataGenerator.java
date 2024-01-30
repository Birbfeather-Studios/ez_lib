package net.distantdig;

import net.distantdig.datagen.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

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
	}
}
