package co.basin.lawfulmod.common.tileentities;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.items.CovenantPaper;
import co.basin.lawfulmod.core.init.TileEntityTypeInit;
import co.basin.lawfulmod.core.util.ParticleUtil;
import co.basin.lawfulmod.core.util.UtilArrays;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.server.ServerWorld;

public class CovenantPedestalTileEntity extends TileEntity implements ITickableTileEntity {
    public static final String TAG_RITUAL_ITEM = "ritualItem";
    private ItemStack ritualItem = null;

    private long ritualStartedAt = 0;
    private boolean ritualStarted;
    private float ritualStage = 0;

    private final BlockPos[] anchorPositions = new BlockPos[4];

    public CovenantPedestalTileEntity() {
        super(TileEntityTypeInit.COVENANT_PEDESTAL.get());
    }

    @Override
    public void tick() {
        if (ritualStarted) {
            ritualStage += 0.01;
            if (ticksSinceRitualStarted() >= 300) {
                finishRitual();
            }
        }
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        if (ritualItem == null) { return super.save(nbt); }
        nbt.put(TAG_RITUAL_ITEM, ritualItem.serializeNBT());
        return super.save(nbt);
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        ritualItem = ItemStack.of(nbt.getCompound(TAG_RITUAL_ITEM));
        super.load(state, nbt);
    }

    private void startRitual() {
        LawfulMod.LOGGER.debug("Ritual Started");

        ritualStarted = true;
        ritualStartedAt = getLevel().getGameTime();

        if (!getLevel().isClientSide()) {
            for (BlockPos pos : anchorPositions) {
                if (pos == null) { continue; }
                ParticleUtil.spawnParticles((ServerWorld) getLevel(), ParticleTypes.DRIPPING_OBSIDIAN_TEAR, pos.getX(), pos.getY() + 1, pos.getZ(), 20);
            }
        }
    }

    private void finishRitual() {
        // LOG
        LawfulMod.LOGGER.debug("Ritual Finished");
        //
        ritualStarted = false;
        ritualStage = 0;
        if (ritualItem.isEmpty()) {
            LawfulMod.LOGGER.debug("Processed Empty Ritual Item");
            return;
        }
        CovenantPaper covenantPaper = (CovenantPaper) ritualItem.getItem();
        covenantPaper.setTogglable(ritualItem);
        covenantPaper.setActive(ritualItem, true);
    }

    public float getRitualStage() {
        return ritualStage;
    }

    public boolean isRitualStarted() { return ritualStarted; }

    private long ticksSinceRitualStarted() {
        return getLevel().getGameTime() - ritualStartedAt;
    }

    public void setRitualItem(ItemStack ritualItem) {
        this.ritualItem = ritualItem;

        if (ritualBlocksAreValid()) {
            startRitual();
        }
    }

    public ItemStack getRitualItem() {
        return this.ritualItem;
    }

    private boolean ritualBlocksAreValid() {
        int pillarCount = 0;

        for (Vector3i offset : UtilArrays.PILLAR_OFFSETS) {
            int cryingCount = 0;
            BlockPos position = getBlockPos().offset(offset);
            for (int i = 0; i < 2; i++) {
                if (getLevel().getBlockState(position.above(i)).getBlock().is(Blocks.CRYING_OBSIDIAN)) {
                    cryingCount++;
                }
            }

            if (cryingCount == 2) {
                if (getLevel().getBlockState(position.above(2)).getBlock().is(Blocks.RESPAWN_ANCHOR)) {
                    pillarCount++;
                    anchorPositions[pillarCount - 1] = position.above(2);
                }
            }
        }

        if (pillarCount >= 2) {
            LawfulMod.LOGGER.debug("Ritual Blocks Found");
            return true;
        }
        return false;
    }
}
