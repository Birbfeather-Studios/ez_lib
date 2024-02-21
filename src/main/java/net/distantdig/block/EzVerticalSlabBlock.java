package net.distantdig.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;


public class EzVerticalSlabBlock extends Block {
    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private final Block base;
    private final BlockState baseState;
    //d = Xmin, e = Ymin, f = Zmin, G = Xmax, H = Ymax, I = Zmax, negative Z = North, Negative X = West, Positive Y = up and Negative Y = down etc.
    private final VoxelShape NORTH = box(0, 0, 0, 16, 16, 8);
    private final VoxelShape WEST = box(0, 0, 0, 8, 16, 16);
    private final VoxelShape SOUTH = box(0, 8, 0, 16, 16, 16);
    private final VoxelShape EAST = box(8, 0, 0, 16, 16, 16);


    public EzVerticalSlabBlock(BlockState blockState, Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
        this.base = blockState.getBlock();
        this.baseState = blockState;
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState blockState) {
        return true;
    }

    @Override
    public void attack(BlockState blockState, Level level, BlockPos blockPos, Player player) {
        this.baseState.attack(level, blockPos, player);
    }

    @Override
    public void destroy(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState) {
        this.base.destroy(levelAccessor, blockPos, blockState);
    }

    @Override
    public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity) {
        this.base.stepOn(level, blockPos, blockState, entity);
    }

    @Override
    public float getExplosionResistance() {
        return this.base.getExplosionResistance();
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        BlockPos blockPos = blockPlaceContext.getClickedPos();
        FluidState fluidState = blockPlaceContext.getLevel().getFluidState(blockPos);
        return this.defaultBlockState().setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);


    }

    @Override
    public BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        switch (blockState.getValue(FACING)) {
            case NORTH -> {return NORTH;}
            case WEST -> {return WEST;}
            case SOUTH -> {return SOUTH;}
            case EAST -> {return EAST;}
            default -> {return Shapes.block();}
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }
}
