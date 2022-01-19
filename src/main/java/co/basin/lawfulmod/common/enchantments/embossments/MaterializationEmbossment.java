package co.basin.lawfulmod.common.enchantments.embossments;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.enchantments.Embossment;
import co.basin.lawfulmod.core.init.EnchantmentInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class MaterializationEmbossment extends Embossment {
    public MaterializationEmbossment(EquipmentSlotType... slotTypes) {
        super(EnchantmentType.BOW, slotTypes);
    }

    @Override
    public Enchantment getParent() {
        return Enchantments.INFINITY_ARROWS;
    }

    @Mod.EventBusSubscriber(modid = LawfulMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class Subscriber {

        @SubscribeEvent
        public static void onArrowNock(ArrowNockEvent event) {
            if (EnchantmentHelper.getItemEnchantmentLevel(EnchantmentInit.MATERIALIZATION.get(), event.getBow()) == 0) { return; }
            event.setAction(ActionResult.success(new ItemStack(Items.ARROW)));
        }

        @SubscribeEvent
        public static void onArrowLoose(ArrowLooseEvent event) {
            if (EnchantmentHelper.getItemEnchantmentLevel(EnchantmentInit.MATERIALIZATION.get(), event.getBow()) == 0) { return; }
            event.setCanceled(true);

            if (event.getEntity() instanceof PlayerEntity) {
                if (event.getCharge() < 0) return;

                PlayerEntity player = (PlayerEntity)event.getEntity();
                ItemStack arrow = new ItemStack(Items.ARROW);
                float charge = BowItem.getPowerForTime(event.getCharge());
                if (!((double)charge < 0.1D)) {
                    if (!event.getWorld().isClientSide) {
                        AbstractArrowEntity projectile = ((ArrowItem) Items.ARROW).createArrow(event.getWorld(), arrow, player);
                        projectile = ((BowItem) event.getBow().getItem()).customArrow(projectile);
                        projectile.shootFromRotation(player, player.xRot, player.yRot, 0.0F, charge * 3.0F, 1.0F);

                        int power = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, event.getBow());
                        int punch = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, event.getBow());

                        if (charge == 1.0F) { projectile.setCritArrow(true); }
                        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, event.getBow()) > 0) { projectile.setSecondsOnFire(100); }
                        if (power > 0) { projectile.setBaseDamage(projectile.getBaseDamage() + (double)power * 0.5D + 0.5D); }
                        if (punch > 0) { projectile.setKnockback(punch); }

                        event.getBow().hurtAndBreak(1, player, (entity) -> entity.broadcastBreakEvent(entity.getUsedItemHand()));
                        projectile.pickup = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                        event.getWorld().addFreshEntity(projectile);
                    }

                    event.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (event.getWorld().random.nextFloat() * 0.4F + 1.2F) + charge * 0.5F);
                    player.awardStat(Stats.ITEM_USED.get(Items.BOW));
                }
            }
        }
    }
}