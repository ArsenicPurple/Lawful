package co.basin.lawfulmod.common.tileentities;

import co.basin.lawfulmod.core.init.TileEntityTypeInit;
import co.basin.lawfulmod.core.util.UtilArrays;
import net.minecraft.block.Blocks;
import net.minecraft.command.impl.WeatherCommand;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.server.ServerWorld;

public class CovenantPedestalTileEntity extends TileEntity implements ITickableTileEntity {
    public static final String TAG_EMITTING_BLOCKS = "emittingBlocks";
    public static final String TAG_RITUAL_ITEM = "ritualItem";
    private BlockPos[] emittingBlocksCache = null;
    private ItemStack ritualItemCache = null;

    private long ritualStartAt = 0;
    private boolean ritualStarted;
    private float ritualStage = 0;


    public CovenantPedestalTileEntity() {
        super(TileEntityTypeInit.COVENANT_PEDESTAL.get());
    }

    @Override
    public void tick() {
        if (ritualStarted) {
            ritualStage += 0.01;
            if (ticksSinceRitualStarted() > 100) {
                finishRitual();
            }
        }
    }

    private void startRitual() {
        ritualStarted = true;
        // Do particle stuff
    }

    private void finishRitual() {
        getLevel().addFreshEntity(new LightningBoltEntity(EntityType.LIGHTNING_BOLT, getLevel()));
    }

    public float getRitualStage() {
        return ritualStage;
    }

    private long ticksSinceRitualStarted() {
        return getLevel().getGameTime() - ritualStartAt;
    }


    public void setRitualItem(TileEntity tileEntity, ItemStack ritualItem) {
        tileEntity.getTileData().put(TAG_RITUAL_ITEM, ritualItem.serializeNBT());
        ritualItemCache = ritualItem;

        if (ritualBlocksAreValid()) {
            startRitual();
        }
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

    private boolean ritualBlocksAreValid() {
        int cryingCount = 0;
        int anchorCount = 0;

        for (Vector3i offset : UtilArrays.CRYING_OFFSETS) {
            if (getLevel().getBlockState(getBlockPos().offset(offset)).getBlock().is(Blocks.CRYING_OBSIDIAN)) {
                cryingCount++;
            }
        }

        for (Vector3i offset : UtilArrays.ANCHOR_OFFSETS) {
            if (getLevel().getBlockState(getBlockPos().offset(offset)).getBlock().is(Blocks.CRYING_OBSIDIAN)) {
                anchorCount++;
            }
        }

        if (anchorCount >= 2 && cryingCount >= 4) {
            return true;
        }
        return false;
    }
}
