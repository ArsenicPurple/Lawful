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

public class CovenantPedestalTileEntity extends TileEntity implements ITickableTileEntity {
    public static final String TAG_EMITTING_BLOCKS = "emittingBlocks";
    public static final String TAG_RITUAL_ITEM = "ritualItem";
    private BlockPos[] emittingBlocksCache = null;
    private ItemStack ritualItemCache = null;

    public CovenantPedestalTileEntity() {
        super(TileEntityTypeInit.COVENANT_PEDESTAL.get());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net, pkt);
    }

    @Override
    public void tick() {

    }

    public void setRitualItem(TileEntity tileEntity, ItemStack ritualItem) {
        CompoundNBT nbt = ritualItem.serializeNBT();
        tileEntity.getTileData().put(TAG_RITUAL_ITEM, nbt);
        ritualItemCache = ritualItem;
    }

    public ItemStack getRitualItem(TileEntity tileEntity) {
        if (ritualItemCache != null) { return ritualItemCache; }
        CompoundNBT nbt = tileEntity.getTileData().getCompound(TAG_RITUAL_ITEM);
        ItemStack ritualItem = ItemStack.of(nbt);
        return (ritualItemCache = ritualItem);
    }

    public void setEmittingBlocks(TileEntity tileEntity, BlockPos[] positions) {
        long[] array = new long[positions.length];
        for (int i = 0; i < positions.length; i++) {
            array[i] = positions[i].asLong();
        }
        tileEntity.getTileData().putLongArray(TAG_EMITTING_BLOCKS, array);
    }

    public BlockPos[] getEmittingBlocks(TileEntity tileEntity) {
        if (emittingBlocksCache != null) { return emittingBlocksCache; }
        long[] array = tileEntity.getTileData().getLongArray(TAG_EMITTING_BLOCKS);
        BlockPos[] positions = new BlockPos[array.length];
        for (int i = 0; i < array.length; i++) {
            positions[i] = BlockPos.of(array[i]);
        }
        return (emittingBlocksCache = positions);
    }
}
