package co.basin.lawfulmod.common.tileentities;

import co.basin.lawfulmod.core.init.TileEntityTypeInit;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.LongArrayNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

public class CovenantRitualTileEntity extends TileEntity implements ITickableTileEntity {
    public static final String TAG_EMITTING_BLOCKS = "emittingBlocks";
    public static final String TAG_RITUAL_ITEM = "ritualItem";
    private BlockPos[] emittingBlocksCache = null;
    private ItemStack ritualItemCache = null;

    public CovenantRitualTileEntity() {
        super(TileEntityTypeInit.COVENANT_RITUAL_TILE_ENTITY.get());
    }

    private CompoundNBT nbt = new CompoundNBT();

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net, pkt);
    }

    @Override
    public CompoundNBT getTileData() {
        return super.getTileData();
    }

    @Override
    public void tick() {

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
