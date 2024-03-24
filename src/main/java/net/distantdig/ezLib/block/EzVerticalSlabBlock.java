package net.distantdig.ezLib.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;


public class EzVerticalSlabBlock extends HorizontalDirectionalBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty SINGLE = BooleanProperty.create("single");

    public EzVerticalSlabBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(SINGLE, true).setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, SINGLE, WATERLOGGED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        BlockPos blockPos = blockPlaceContext.getClickedPos();
        BlockState blockState = blockPlaceContext.getLevel().getBlockState(blockPos);
        if(blockState.is(this)) {return blockState.setValue(SINGLE, false).setValue(WATERLOGGED, false);}
        FluidState fluidState = blockPlaceContext.getLevel().getFluidState(blockPos);
        return this.defaultBlockState().setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState blockState) {
        return blockState.getValue(SINGLE);
    }

    @Override
    public boolean canBeReplaced(BlockState blockState, BlockPlaceContext blockPlaceContext) {
        ItemStack itemStack = blockPlaceContext.getItemInHand();
        Boolean single = blockState.getValue(SINGLE);
        Direction placeDirection = blockPlaceContext.getClickedFace();
        Direction blockdirection = blockState.getValue(FACING);
        return single && itemStack.is(this.asItem()) && placeDirection==blockdirection;
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        Direction facing = blockState.getValue(FACING);
        Boolean single = blockState.getValue(SINGLE);
        if (single) {
            switch (facing) {
                case NORTH:
                    return Shapes.create(0.0f, 0.0f, 0.5f, 1.0f, 1.0f, 1.0f);
                case WEST:
                    return Shapes.create(0.5f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                case SOUTH:
                    return Shapes.create(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.5f);
                case EAST:
                    return Shapes.create(0.0f, 0.0f, 0.0f, 0.5f, 1.0f, 1.0f);
            }
        }
        return Shapes.box(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }
}