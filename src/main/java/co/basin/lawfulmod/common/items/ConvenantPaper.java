package co.basin.lawfulmod.common.items;

import co.basin.lawfulmod.core.util.ItemNBTUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ConvenantPaper extends Item {
    private Item pactItem;
    private boolean isActive;
    CompoundNBT nbt = new CompoundNBT();

    public ConvenantPaper(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, world, tooltip, flagIn);
    }

    public void setPactItem(Item pactItem) {
        this.pactItem = pactItem;
    }

    public void setActive() {
        isActive = true;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int inventorySlot, boolean isSelected) {
        if (!world.isClientSide) {
            if (isActive) {
                if (entity instanceof PlayerEntity) {
                    if (((PlayerEntity)entity).inventory.contains(pactItem.getDefaultInstance())) {
                        entity.hurt(new DamageSource("lawful.broken_covenant"), 2);
                    }
                }
            }
        }
    }
}
