package co.basin.lawfulmod.common.blocks;

import co.basin.lawfulmod.core.util.ItemNBTUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class CovenantRitualBlock extends Block {
    public static final String TAG_EMITTING_BLOCKS = "emittingBlocks";

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

    public BlockPos[] getEmittingBlocks(ItemStack stack) {
        ItemNBTUtil.getList(stack, TAG_EMITTING_BLOCKS, BlockPos.class.hashCode(), false);
    }
}
