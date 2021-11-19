package co.basin.lawfulmod.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class CovenantRitualBlock extends Block {


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


}
