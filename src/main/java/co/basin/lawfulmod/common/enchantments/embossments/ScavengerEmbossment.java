package co.basin.lawfulmod.common.enchantments.embossments;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.enchantments.Embossment;
import co.basin.lawfulmod.core.init.EnchantmentInit;
import co.basin.lawfulmod.core.util.ItemNBTUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LootingLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

public class ScavengerEmbossment extends Embossment {
    public ScavengerEmbossment(EquipmentSlotType... slotTypes) {
        super(EnchantmentType.WEAPON, slotTypes);
    }

    public static final String TAG_LAST_MOB_HURT = "LastMobHurt";
    public static final String TAG_LAST_DAMAGE_DEALT = "LastDamageDone";

    @Override
    public Enchantment getParent() {
        return Enchantments.MOB_LOOTING;
    }

    @Mod.EventBusSubscriber(modid = LawfulMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class Subscriber {

        @SubscribeEvent
        public static void onLivingAttack(LivingAttackEvent event) {
            if (event.getSource().getDirectEntity() == null) { return; }
            if (!(event.getSource().getDirectEntity() instanceof LivingEntity)) { return; }

            LivingEntity attacker = ((LivingEntity) event.getSource().getDirectEntity());
            if (EnchantmentHelper.getItemEnchantmentLevel(EnchantmentInit.SCAVENGER.get(), attacker.getMainHandItem()) == 0) { return; }

            ItemNBTUtil.setUuid(attacker.getMainHandItem(), TAG_LAST_MOB_HURT, event.getEntityLiving().getUUID());
            ItemNBTUtil.setFloat(attacker.getMainHandItem(), TAG_LAST_DAMAGE_DEALT, event.getAmount());
        }

        @SubscribeEvent
        public static void onGetLootingLevel(LootingLevelEvent event) {
            if (event.getDamageSource().getDirectEntity() == null) { return; }
            if (!(event.getDamageSource().getDirectEntity() instanceof LivingEntity)) { return; }
            if (event.getDamageSource().isProjectile()) { return; }

            LivingEntity attacker = ((LivingEntity) event.getDamageSource().getDirectEntity());
            ItemStack stack = attacker.getMainHandItem();
            if (EnchantmentHelper.getItemEnchantmentLevel(EnchantmentInit.SCAVENGER.get(), stack) == 0) { return; }

            UUID lastMobHurtUUID = ItemNBTUtil.getUuid(stack, TAG_LAST_MOB_HURT);
            if (!event.getEntityLiving().getUUID().equals(lastMobHurtUUID)) { return; }
            if (event.getEntityLiving().getMaxHealth() <= ItemNBTUtil.getInt(stack, TAG_LAST_DAMAGE_DEALT, 0)) {
                event.setLootingLevel(7);
            }
        }
    }
}
