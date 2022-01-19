package co.basin.lawfulmod.common.enchantments.embossments;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.enchantments.Embossment;
import co.basin.lawfulmod.core.init.EnchantmentInit;
import co.basin.lawfulmod.core.util.ItemNBTUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

public class JudgementEmbossment extends Embossment {
    public JudgementEmbossment(EquipmentSlotType... slotTypes) {
        super(EnchantmentType.WEAPON, slotTypes);
    }

    public static final String TAG_LAST_HURT_BY = "lastHurtBy";

    @Override
    public Enchantment getParent() {
        return Enchantments.SMITE;
    }

    @Override
    public void doPostHurt(LivingEntity attacked, Entity attacker, int p_151367_3_) {
        if (!(attacker instanceof LivingEntity)) { return; }
        setLastHurtBy(attacked.getMainHandItem(), attacker.getUUID());
    }

    private void setLastHurtBy(ItemStack stack, UUID uuid) {
        ItemNBTUtil.setUuid(stack, TAG_LAST_HURT_BY, uuid);
    }

    @Mod.EventBusSubscriber(modid = LawfulMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class Subscriber {

        @SubscribeEvent
        public static void onLivingAttack(LivingAttackEvent event) {
            if (!(event.getSource().getEntity() instanceof LivingEntity)) { return; }

            ItemStack stack = ((LivingEntity) event.getSource().getEntity()).getMainHandItem();
            if (EnchantmentHelper.getItemEnchantmentLevel(EnchantmentInit.JUDGEMENT.get(), stack) == 0) { return; }

            if (event.getEntityLiving().getUUID().equals(ItemNBTUtil.getUuid(stack, TAG_LAST_HURT_BY))) {
                event.setCanceled(true);
                event.getEntityLiving().invulnerableTime = 0;
                event.getEntityLiving().hurt(new DamageSource("lawful.judgement"), event.getAmount() * 1.5f);
            }
        }
    }
}
