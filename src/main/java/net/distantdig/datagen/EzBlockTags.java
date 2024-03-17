package net.distantdig.datagen;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class EzBlockTags {
    public static final TagKey<Block> WOODEN_VERTICAL_SLAB = EzBlockTags.ezTagCreate("wooden_vertical_slab");
    private static TagKey<Block> ezTagCreate(String string) {
        return TagKey.create(Registries.BLOCK, new ResourceLocation(string));
    }
}
