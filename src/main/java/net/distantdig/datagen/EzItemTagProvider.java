package net.distantdig.datagen;

import net.distantdig.block.EzBlocksBuilder;
import net.distantdig.item.EzItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class EzItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public static ArrayList<EzItems.ArmorSet> trimmableArmor = new ArrayList<>();

    public EzItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        trimmableArmor.forEach((armorSet -> getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(armorSet.helmet, armorSet.chestplate,armorSet.leggings,armorSet.boots)));
        EzBlocksBuilder.woodMap.forEach((strings, block) -> {
            if(strings.tagKey != null) {
                getOrCreateTagBuilder(strings.tagKey)
                        .add(EzBlocksBuilder.inventoryMap.get(strings.blockname))
                        .add(EzBlocksBuilder.inventoryMap.get("stripped_" + strings.blockname))
                        .add(EzBlocksBuilder.inventoryMap.get(strings.fullblockname))
                        .add(EzBlocksBuilder.inventoryMap.get("stripped_" + strings.fullblockname));}});
    }
}
