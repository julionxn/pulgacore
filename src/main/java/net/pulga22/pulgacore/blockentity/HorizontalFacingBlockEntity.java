package net.pulga22.pulgacore.blockentity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.pulga22.pulgacore.util.render3d.MatricesInfo;
import org.jetbrains.annotations.Nullable;

/**
 * A block entity capable of rendering a quad depending on the rotation of the block.
 * @see net.pulga22.pulgacore.util.Render3DUtils#renderRotatedQuad(HorizontalFacingBlockEntity, MatrixStack, Identifier, MatricesInfo, MatricesInfo)
 */
public class HorizontalFacingBlockEntity extends BlockEntity {

    private final BlockState state;

    public HorizontalFacingBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.state = state;
    }

    @Nullable
    public Direction getFacing(){
        if (world != null){
            BlockState blockState = world.getBlockState(getPos());
            if (blockState.isOf(state.getBlock())){
                return blockState.get(Properties.HORIZONTAL_FACING);
            }
        }
        return null;
    }

}
