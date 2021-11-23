package co.basin.lawfulmod.common.items;

import co.basin.lawfulmod.core.util.ItemNBTUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class CovenantPaper extends SoulboundItem {
    public static final String TAG_PACT_ITEM = "pactItem";
    private ItemStack pactItemCache = null;

    public CovenantPaper(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, world, tooltip, flagIn);
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int inventorySlot, boolean isSelected) {
        if (world.isClientSide) { return; }

        if (getIsActive(stack)) {
            if (entity instanceof PlayerEntity) {
                if (((PlayerEntity)entity).inventory.contains(getPactItem(stack))) {
                    entity.hurt(new DamageSource("lawful.broken_covenant"), 2);
                }
            }
        }
    }

    /**
     * Writes an ItemStack to the NBT data of another ItemStack.
     * @param stack The ItemStack being written to.
     * @param pactItem The ItemStack being written.
     */
    public void setPactItem(ItemStack stack, ItemStack pactItem) {
        ItemNBTUtil.setCompound(stack, TAG_PACT_ITEM, pactItem.serializeNBT());
    }

    /**
     * Returns an ItemStack from NBT data. Caches the ItemStack after reading.
     * @param stack The stack to load NBT from.
     * @return {@code ItemStack} The ItemStack of the Pact.
     */
    public ItemStack getPactItem(ItemStack stack) {
        if (this.pactItemCache != null) { return this.pactItemCache; }
        CompoundNBT nbt;
        if ((nbt = ItemNBTUtil.getCompound(stack, TAG_PACT_ITEM, true)) == null) {
            return null;
        }
        return (this.pactItemCache = ItemStack.of(nbt));
    }
}
