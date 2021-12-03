package co.basin.lawfulmod.common.containers;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.blocks.PactingTableBlock;
import co.basin.lawfulmod.common.items.CovenantPaper;
import co.basin.lawfulmod.core.init.ContainerTypeInit;
import co.basin.lawfulmod.core.util.PactUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.AbstractRepairContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;

public class PactingContainer extends AbstractRepairContainer {
    private final IntReferenceHolder cost = IntReferenceHolder.standalone();

    public PactingContainer(int id, PlayerInventory playerInventory, PacketBuffer buffer) {
        this(id, playerInventory, IWorldPosCallable.NULL);
    }

    public PactingContainer(int id, PlayerInventory playerInventory, IWorldPosCallable pos) {
        super(ContainerTypeInit.PACTING_TABLE.get(), id, playerInventory, pos);
        this.addDataSlot(this.cost);
    }

    @Override
    protected boolean mayPickup(PlayerEntity p_230303_1_, boolean p_230303_2_) {
        return (p_230303_1_.abilities.instabuild || p_230303_1_.experienceLevel >= this.cost.get()) && this.cost.get() > 0;
    }

    @Override
    protected ItemStack onTake(PlayerEntity p_230301_1_, ItemStack stack) {
        if (!p_230301_1_.abilities.instabuild) {
            p_230301_1_.giveExperienceLevels(-this.cost.get());
        }

        this.inputSlots.setItem(0, ItemStack.EMPTY);
        this.inputSlots.setItem(1, ItemStack.EMPTY);
        return stack;
    }

    @Override
    protected boolean isValidBlock(BlockState blockState) {
        return blockState.getBlock() instanceof PactingTableBlock;
    }

    @Override
    public void createResult() {
        this.cost.set(1);
        ItemStack result = this.inputSlots.getItem(0).copy();
        ItemStack pactItem = this.inputSlots.getItem(1);
        if (result.getItem() instanceof CovenantPaper) {
            int pactType;
            if ((pactType = PactUtils.isValidPactItem(pactItem)) == -1) { return; }
            CovenantPaper itemStack = ((CovenantPaper) result.getItem());
            itemStack.setPactType(result.getStack(), pactType);
            itemStack.setActive(result.getStack(), false);
            // LOG
            LawfulMod.LOGGER.debug("Pact Type Set To: " + pactType);
            //
            this.resultSlots.setItem(0, result.getStack());
        }
    }
}
