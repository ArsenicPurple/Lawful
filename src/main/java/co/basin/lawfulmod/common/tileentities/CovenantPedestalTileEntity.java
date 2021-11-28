package co.basin.lawfulmod.common.tileentities;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.items.CovenantPaper;
import co.basin.lawfulmod.core.init.TileEntityTypeInit;
import co.basin.lawfulmod.core.util.ParticleUtil;
import co.basin.lawfulmod.core.util.UtilArrays;
import net.minecraft.block.Blocks;
import net.minecraft.command.impl.WeatherCommand;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.item.Item;
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
    private ItemStack ritualItemCache = null;

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

    private void startRitual() {
        LawfulMod.LOGGER.debug("Ritual Started");

        ritualStarted = true;
        ritualStartedAt = getLevel().getGameTime();

        //TODO: Particle Stuff
        if (!getLevel().isClientSide()) {
            for (BlockPos pos : anchorPositions) {
                if (pos == null) { continue; }
                ParticleUtil.spawnParticles((ServerWorld) getLevel(), ParticleTypes.DRIPPING_OBSIDIAN_TEAR, pos.getX(), pos.getY() + 1, pos.getZ(), 20);
            }
        }
    }

    private void finishRitual() {
        LawfulMod.LOGGER.debug("Ritual Finished");
        ritualStarted = false;
        ritualStage = 0;
        getLevel().addFreshEntity(new LightningBoltEntity(EntityType.LIGHTNING_BOLT, getLevel()));
        ItemStack stack = getRitualItem(this.getTileEntity());
        CovenantPaper covenantPaper = (CovenantPaper) stack.getItem();
        covenantPaper.setActive(stack, true);
    }

    public float getRitualStage() {
        return ritualStage;
    }

    public boolean isRitualStarted() { return ritualStarted; }

    private long ticksSinceRitualStarted() {
        return getLevel().getGameTime() - ritualStartedAt;
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
