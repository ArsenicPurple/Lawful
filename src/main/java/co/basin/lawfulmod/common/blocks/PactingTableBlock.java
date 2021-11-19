package co.basin.lawfulmod.common.blocks;

import co.basin.lawfulmod.common.containers.PactingContainer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.tileentity.EnchantingTableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.INameable;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class PactingTableBlock extends Block {
    public PactingTableBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context){
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 0, 0.125, 0.875, 0.125, 0.875), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.1875, 0.125, 0.1875, 0.8125, 0.25, 0.8125), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.375, 0.25, 0.375, 0.625, 0.875, 0.625), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0.875, 0, 1, 0.9375, 1), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0.9375, 0, 1, 1, 0.0625), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0.9375, 0.9375, 1, 1, 1), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0.9375, 0.0625, 0.0625, 1, 0.9375), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.9375, 0.9375, 0.0625, 1, 1, 0.9375), IBooleanFunction.OR);
        return shape;
    }

    public ActionResultType use(BlockState blockState, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        if (world.isClientSide) {
            return ActionResultType.SUCCESS;
        } else {
            player.openMenu(blockState.getMenuProvider(world, pos));
            return ActionResultType.CONSUME;
        }
    }

    @Nullable
    public INamedContainerProvider getMenuProvider(BlockState blockState, World world, BlockPos pos) {
        return new SimpleNamedContainerProvider((id, playerInventory, player) -> {
            return new PactingContainer(id, playerInventory, IWorldPosCallable.create(world, pos));
        }, new TranslationTextComponent("title.lawful.pacting_table"));
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
