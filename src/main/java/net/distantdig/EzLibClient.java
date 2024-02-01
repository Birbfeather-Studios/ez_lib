package net.distantdig;

import net.distantdig.item.EzItemGroups;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;

public class EzLibClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EzItemGroups.BlockWoodFamilyGroupList.forEach((family) -> {
            if (family.door != null) {
                BlockRenderLayerMap.INSTANCE.putBlock(family.door.block, RenderType.cutout());
            }
            if (family.trapdoor != null) {
                BlockRenderLayerMap.INSTANCE.putBlock(family.trapdoor.block, RenderType.cutout());
            }
            if (family.leaves != null) {
                BlockRenderLayerMap.INSTANCE.putBlock(family.leaves.block, RenderType.cutout());
            }
        });
    }
}
