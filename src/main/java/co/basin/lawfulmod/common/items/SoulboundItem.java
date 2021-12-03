package co.basin.lawfulmod.common.items;

import co.basin.lawfulmod.core.util.ItemNBTUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

/*
Some code taken from the Botania Mod
Link Here: https://github.com/Vazkii/Botania/blob/8f0eb1340e9684e9143879958bb7b35ce39b095e/src/main/java/vazkii/botania/common/core/helper/ItemNBTHelper.java#L25
 */

public class SoulboundItem extends Item {
    public SoulboundItem(Properties properties) {
        super(properties);
    }

    private static final String TAG_PLAYER_ID = "playerUUID";

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);

        if (!hasUUID(stack)) {
            tooltip.add(new TranslationTextComponent("tooltip.lawful.unbound"));
        } else {
            if (getSoulboundUUID(stack).equals(Minecraft.getInstance().player.getUUID())) {
                tooltip.add(new TranslationTextComponent("tooltip.lawful.bound_to", Minecraft.getInstance().player.getName()));
            }
        }
    }

    @Override
    public boolean verifyTagAfterLoad(CompoundNBT nbt) {
        return super.verifyTagAfterLoad(nbt);
    }

    @Override
    public int getEntityLifespan(ItemStack itemStack, World world) {
        return Integer.MAX_VALUE;
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isClientSide()) {
            UUID uuid;
            if ((uuid = getSoulboundUUID(playerIn.getItemInHand(handIn).getStack())) == null) {
                return ActionResult.success(playerIn.getItemInHand(handIn));
            }

            if (!uuid.equals(playerIn.getUUID())) {
                EffectInstance effect = new EffectInstance(Effects.WEAKNESS, 2000,20);

                playerIn.addEffect(effect);

                ITextComponent textComponent = new TranslationTextComponent("notif.lawful.foreign_soulbound");
                playerIn.sendMessage(textComponent, playerIn.getUUID());

                return ActionResult.fail(playerIn.getItemInHand(handIn));
            }
        }

        return ActionResult.success(playerIn.getItemInHand(handIn));
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!worldIn.isClientSide() && entityIn instanceof PlayerEntity) {
            if (stack.isEmpty()) {
                return;
            }

            if (!hasUUID(stack)) {
                bindToUUID(entityIn.getUUID(), stack);
            }
        }

        super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
    }

    public void bindToUUID(UUID uuid, ItemStack stack) {
        ItemNBTUtil.setString(stack, TAG_PLAYER_ID, uuid.toString());
    }

    public UUID getSoulboundUUID(ItemStack stack) {
        if (ItemNBTUtil.verifyExistance(stack, TAG_PLAYER_ID)) {
            try {
                return UUID.fromString(ItemNBTUtil.getString(stack, TAG_PLAYER_ID, ""));
            } catch (IllegalArgumentException ex) { // Bad UUID in tag
                ItemNBTUtil.removeEntry(stack, TAG_PLAYER_ID);
            }
        }
        return null;
    }

    public boolean hasUUID(ItemStack stack) {
        return getSoulboundUUID(stack) != null;
    }
}
