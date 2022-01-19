package co.basin.lawfulmod.common.containers;

import co.basin.lawfulmod.core.init.ContainerTypeInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class EmbossmentContainer extends Container {
    private final IInventory inventory;

    public EmbossmentContainer(int id, PlayerInventory playerInventory, PacketBuffer packetBuffer) {
        this(id, playerInventory, new Inventory(5));
    }

    public EmbossmentContainer(int id, PlayerInventory playerInventory, IInventory inventory) {
        super(ContainerTypeInit.EMBOSSMENT_TABLE.get(), id);
        checkContainerSize(inventory, 5);
        this.inventory = inventory;
        inventory.startOpen(playerInventory.player);

        this.addSlot(new Slot(inventory, 0, 24, 20));
        this.addSlot(new Slot(inventory, 1, 89, 20));
        this.addSlot(new Slot(inventory, 2, 57, 34));
        this.addSlot(new Slot(inventory, 3, 24, 48));
        this.addSlot(new Slot(inventory, 4, 89, 48));

        //Player Inventory
        for(int y = 0; y < 3; ++y) {
            for(int x = 0; x < 9; ++x) {
                this.addSlot(new Slot(playerInventory, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
            }
        }

        for(int x = 0; x < 9; ++x) {
            this.addSlot(new Slot(playerInventory, x, 8 + x * 18, 142));
        }

    }

    public boolean stillValid(PlayerEntity player) {
        return this.inventory.stillValid(player);
    }

    public ItemStack quickMoveStack(PlayerEntity player, int slotId) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotId);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (slotId < 9) {
                if (!this.moveItemStackTo(itemstack1, 9, 45, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, 9, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }

    public void removed(PlayerEntity player) {
        super.removed(player);
        this.inventory.stopOpen(player);
    }
}
