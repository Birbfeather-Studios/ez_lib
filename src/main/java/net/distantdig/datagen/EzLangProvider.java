package net.distantdig.datagen;

import net.distantdig.EzLib;
import net.distantdig.item.EzItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import org.apache.commons.lang3.text.WordUtils;

public class EzLangProvider extends FabricLanguageProvider {
    public EzLangProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        EzItems.itemMap.forEach((key, item) -> {
            String string = WordUtils.capitalizeFully(key.replace("_", " "));

            translationBuilder.add(item, string);
        });

        String string = WordUtils.capitalizeFully(EzLib.getModId().replace("_", " "));
        translationBuilder.add("itemgroup." + EzLib.getModId(), string);

    }
}
