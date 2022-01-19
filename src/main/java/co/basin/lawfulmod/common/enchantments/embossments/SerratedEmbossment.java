package co.basin.lawfulmod.common.enchantments.embossments;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.enchantments.Embossment;
import co.basin.lawfulmod.core.init.EffectInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.potion.EffectInstance;

public class SerratedEmbossment extends Embossment {
    public SerratedEmbossment(EquipmentSlotType... slotTypes) {
        super(EnchantmentType.WEAPON, slotTypes);
    }

    @Override
    public Enchantment getParent() {
        return Enchantments.SHARPNESS;
    }

    @Override
    public void doPostAttack(LivingEntity attacker, LivingEntity attacked, int enchantmentLevel) {
        LawfulMod.LOGGER.debug("Running Serrated Enchantment");

        EffectInstance instance = attacked.getEffect(EffectInit.BLEEDING.get());
        if (instance != null) {
            int amplifier = instance.getAmplifier();
            instance.update(new EffectInstance(EffectInit.BLEEDING.get(), 60, amplifier == 2 ? amplifier : amplifier + 1));
            return;
        }
        attacked.addEffect(new EffectInstance(EffectInit.BLEEDING.get(), 60, 0));
    }
}
