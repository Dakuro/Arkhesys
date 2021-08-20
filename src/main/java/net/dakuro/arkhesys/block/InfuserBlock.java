package net.dakuro.arkhesys.block;

import net.dakuro.arkhesys.tile_entity.InfuserTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Objects;

public class InfuserBlock extends Block {

    public static final DirectionProperty FACING = HorizontalBlock.FACING;

    public static final VoxelShape INFUSER = VoxelShapes.or(
            Block.box(0, 0, 0, 2, 11, 2),
            Block.box(1, 0, 1, 15, 12, 15),
            Block.box(14, 0, 0, 16, 11, 2),
            Block.box(14, 0, 14, 16, 11, 16),
            Block.box(0, 0, 14, 2, 11, 16)
    );

    public InfuserBlock(Properties properties) {
        super(properties);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return INFUSER;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new InfuserTileEntity();
    }

    private LazyOptional<IFluidHandler> getFluidHandler(IBlockReader world, BlockPos pos) {
        return Objects.requireNonNull(world.getBlockEntity(pos)).getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult raytrace) {
        if (world.isClientSide) {
            return ActionResultType.SUCCESS;
        }
        this.interactWith(world, pos, player);
        return ActionResultType.CONSUME;
    }

    private void interactWith(World world, BlockPos pos, PlayerEntity player) {
        TileEntity tileEntity = world.getBlockEntity(pos);
        if (tileEntity instanceof InfuserTileEntity && player instanceof ServerPlayerEntity) {
            InfuserTileEntity te = (InfuserTileEntity) tileEntity;
            NetworkHooks.openGui((ServerPlayerEntity) player, te, te::encodeExtraData);
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            TileEntity tileEntity = world.getBlockEntity(pos);
            if (tileEntity instanceof IInventory) {
                InventoryHelper.dropContents(world, pos, (IInventory) tileEntity);
                world.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, world, pos, newState, isMoving);
        }
    }

    @Override
    public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
