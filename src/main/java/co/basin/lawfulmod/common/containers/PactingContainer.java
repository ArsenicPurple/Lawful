package co.basin.lawfulmod.common.containers;

import co.basin.lawfulmod.common.blocks.PactingTableBlock;
import co.basin.lawfulmod.common.items.CovenantPaper;
import co.basin.lawfulmod.core.init.ContainerTypeInit;
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
        ItemStack result = this.inputSlots.getItem(0).copy();
        this.cost.set(1);
        if (result.getItem() instanceof CovenantPaper) {
            CovenantPaper itemStack = ((CovenantPaper) result.getItem());
            itemStack.setPactItem(result.getStack(), this.inputSlots.getItem(1));
            itemStack.setActive(result.getStack(), false);
            this.resultSlots.setItem(0, result.getStack());
        }
    }
}
