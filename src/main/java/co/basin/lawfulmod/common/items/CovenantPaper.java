package co.basin.lawfulmod.common.items;

import co.basin.lawfulmod.core.util.ItemNBTUtil;
import co.basin.lawfulmod.core.util.PactUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class CovenantPaper extends SoulboundItem {
    private static final String TAG_CAN_BE_ACTIVE = "canBeActive";
    private static final String TAG_IS_ACTIVE = "isActive";
    public static final String TAG_PACT_TYPE = "pactType";
    private int pactType = -1;

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
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);
        if (getCanBeActive(stack)) { setActive(stack, !getIsActive(stack)); }
        return super.use(worldIn, playerIn, handIn);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int inventorySlot, boolean isSelected) {
        if (world.isClientSide) { return; }

        if (getIsActive(stack) && pactType != -1) {
            if (entity instanceof PlayerEntity) {
                int invSize = ((PlayerEntity)entity).inventory.getContainerSize();
                int random = world.random.nextInt(invSize + 1);
                if (PactUtils.testForType(((PlayerEntity)entity).inventory.getItem(random), pactType)) {
                    entity.hurt(new DamageSource("lawful.broken_covenant"), 5);
                }
            }
        }
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return getIsActive(stack);
    }

    public void setPactType(ItemStack stack, int type) {
        ItemNBTUtil.setInt(stack, TAG_PACT_TYPE, type);
        pactType = type;
    }

    public int getPactType(ItemStack stack) {
        if (this.pactType != -1) { return pactType; }
        return (this.pactType = ItemNBTUtil.getInt(stack, TAG_PACT_TYPE, -1));
    }

    public void setActive(ItemStack stack, boolean active) {
        ItemNBTUtil.setBoolean(stack, TAG_IS_ACTIVE, active);
    }

    public boolean getIsActive(ItemStack stack) {
        return ItemNBTUtil.getBoolean(stack, TAG_IS_ACTIVE, false);
    }

    public void setCanBeActive(ItemStack stack) { ItemNBTUtil.setBoolean(stack, TAG_CAN_BE_ACTIVE, true); }

    public boolean getCanBeActive(ItemStack stack) {
        return ItemNBTUtil.getBoolean(stack, TAG_IS_ACTIVE, false);
    }
}
