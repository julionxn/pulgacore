package net.pulga22.pulgacore.util;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

public abstract class Shortcuts {

    /**
     * Shortcut to put blocks in the translucent render layer.
     * @param blocks Blocks to add.
     */
    public static void setTranslucent(Block... blocks){
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(), blocks);
    }

}
