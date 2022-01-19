package co.basin.lawfulmod.common.blocks;

import co.basin.lawfulmod.common.containers.EmbossmentContainer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EmbossmentTableBlock extends Block {
    public EmbossmentTableBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0, 0, 1, 0.125, 1), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.0625, 0.5625, 0.5625, 0.4375, 0.625, 0.9375), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.0625, 0.5625, 0.0625, 0.4375, 0.625, 0.4375), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.5625, 0.5625, 0.5625, 0.9375, 0.625, 0.9375), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.5625, 0.5625, 0.0625, 0.9375, 0.625, 0.4375), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.3125, 0.75, 0.3125, 0.6875, 0.8125, 0.6875), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0.25, 0, 1, 0.5, 1), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0.125, 0, 1, 0.25, 1), IBooleanFunction.OR);

        return shape;
    }

    public ActionResultType use(BlockState blockState, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        if (world.isClientSide) {
            return ActionResultType.SUCCESS;
        }

        player.openMenu(blockState.getMenuProvider(world, pos));
        return ActionResultType.CONSUME;
    }

    @Nullable
    public INamedContainerProvider getMenuProvider(BlockState blockState, World world, BlockPos pos) {
        return new SimpleNamedContainerProvider((id, playerInventory, player) -> {
            return new EmbossmentContainer(id, playerInventory, new Inventory(5));
        }, new TranslationTextComponent("title.lawful.embossment_table"));
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
