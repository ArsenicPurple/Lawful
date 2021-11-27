package co.basin.lawfulmod.common.blocks;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.items.CovenantPaper;
import co.basin.lawfulmod.common.tileentities.CovenantPedestalTileEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.item.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

public class CovenantPedestal extends Block {

    public CovenantPedestal(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.0625, 0, 0.0625, 0.9375, 0.125, 0.9375), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.0625, 0.75, 0.0625, 0.9375, 0.875, 0.9375), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 0.125, 0.125, 0.875, 0.75, 0.875), IBooleanFunction.OR);

        return shape;
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        TileEntity tileEntity = world.getBlockEntity(pos);
        if (tileEntity instanceof CovenantPedestalTileEntity) {
            ItemStack stack;
            if (!(stack = ((CovenantPedestalTileEntity) tileEntity).getRitualItem(tileEntity)).isEmpty()) {
                player.inventory.add(stack);
                ((CovenantPedestalTileEntity) tileEntity).setRitualItem(tileEntity, stack);
                return ActionResultType.CONSUME;
            }
            LawfulMod.LOGGER.debug("There is no Ritual Item in the pedestal");
        }

        if (world.isClientSide()) {
            return ActionResultType.SUCCESS;
        }

        return ActionResultType.CONSUME;
    }

    @Override
    public void updateEntityAfterFallOn(IBlockReader reader, Entity entity) {
        if (entity instanceof ItemEntity) {
            ItemStack stack = ((ItemEntity) entity).getItem();
            if (stack.getItem() instanceof CovenantPaper) {
                if (((CovenantPaper) stack.getItem()).getIsActive(stack)) {
                    super.updateEntityAfterFallOn(reader, entity);
                    return;
                }

                BlockPos position = entity.blockPosition();
                TileEntity tileEntity = reader.getBlockEntity(position);
                if (tileEntity instanceof CovenantPedestalTileEntity) {
                    ((CovenantPedestalTileEntity) tileEntity).setRitualItem(tileEntity, stack);
                    entity.remove(true);
                }
            }
        }
        super.updateEntityAfterFallOn(reader, entity);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new CovenantPedestalTileEntity();
    }
}
