package co.basin.lawfulmod.common.blocks;

import co.basin.lawfulmod.common.items.CovenantPaper;
import co.basin.lawfulmod.common.tileentities.CovenantRitualTileEntity;
import co.basin.lawfulmod.core.util.ItemNBTUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.LongArrayNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class CovenantRitualBlock extends Block {
    public static final String TAG_EMITTING_BLOCKS = "emittingBlocks";
    public static final String TAG_RITUAL_ITEM = "ritualItem";
    private BlockPos[] emittingBlocksCache = null;
    private ItemStack ritualItemCache = null;

    public CovenantRitualBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity entity, Hand hand, BlockRayTraceResult result) {
        if (world.isClientSide) {
            return ActionResultType.SUCCESS;
        }

        return ActionResultType.CONSUME;
    }

    @Override
    public void updateEntityAfterFallOn(IBlockReader reader, Entity entity) {
        if (entity instanceof ItemEntity) {
            ItemStack stack;
            if ((stack = ((ItemEntity) entity).getItem()).getItem() instanceof CovenantPaper) {
                if (((CovenantPaper) stack.getItem()).getIsActive(stack)) {
                    super.updateEntityAfterFallOn(reader, entity);
                    return;
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
        return new CovenantRitualTileEntity();
    }

    public void setRitualItem(TileEntity tileEntity, ItemStack ritualItem) {

    }

    public void setEmittingBlocks(ItemStack stack, BlockPos[] positions) {
        long[] array = new long[positions.length];
        for (int i = 0; i < positions.length; i++) {
            array[i] = positions[i].asLong();
        }
        stack.addTagElement(TAG_EMITTING_BLOCKS, new LongArrayNBT(array));
    }

    public BlockPos[] getEmittingBlocks(ItemStack stack) {
        if (emittingBlocksCache != null) { return emittingBlocksCache; }
        long[] array = stack.getOrCreateTag().getLongArray(TAG_EMITTING_BLOCKS);
        BlockPos[] positions = new BlockPos[array.length];
        for (int i = 0; i < array.length; i++) {
            positions[i] = BlockPos.of(array[i]);
        }
        return (emittingBlocksCache = positions);
    }
}
