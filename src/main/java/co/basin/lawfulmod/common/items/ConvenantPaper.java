package co.basin.lawfulmod.common.items;

import co.basin.lawfulmod.core.util.ItemNBTUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ConvenantPaper extends SoulboundItem {
    public static final String TAG_PACT_ITEM = "pactItem";

    public ConvenantPaper(Properties properties) {
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
                if (((PlayerEntity)entity).inventory.contains(pactItem.getDefaultInstance())) {
                    entity.hurt(new DamageSource("lawful.broken_covenant"), 2);
                }
            }
        }
    }

    public void setPactItem(ItemStack stack) {
        ItemNBTUtil.setCompound(stack, TAG_PACT_ITEM, stack.serializeNBT());
    }

    public boolean getPactItem(ItemStack stack) {
        CompoundNBT  nbt;
        if ((nbt = ItemNBTUtil.getCompound(stack, TAG_PACT_ITEM, true)) ==  null) {
            return false;
        }
        stack.deserializeNBT(nbt);
        return true;
    }
}
