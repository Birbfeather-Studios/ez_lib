package net.distantdig.ezLib;

import net.distantdig.ezLib.block.EzBlocksBuilder;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;

public class EzLibClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EzBlocksBuilder.doorMap.forEach((s, doorBlock) -> BlockRenderLayerMap.INSTANCE.putBlock(doorBlock, RenderType.cutout()));
        EzBlocksBuilder.trapDoorMap.forEach((s, trapDoorBlock) -> BlockRenderLayerMap.INSTANCE.putBlock(trapDoorBlock, RenderType.cutout()));
        EzBlocksBuilder.leavesMap.forEach((s, leavesBlock) -> BlockRenderLayerMap.INSTANCE.putBlock(leavesBlock,RenderType.cutout()));
        EzBlocksBuilder.glassMap.forEach((s, glassBlock) -> BlockRenderLayerMap.INSTANCE.putBlock(glassBlock,RenderType.translucent()));
        //EzBlocksBuilder.verticalSlabMap.forEach((strings, ezVerticalSlabBlock) -> BlockRenderLayerMap.INSTANCE.putBlock(ezVerticalSlabBlock, RenderType.cutout()));
    }
}
