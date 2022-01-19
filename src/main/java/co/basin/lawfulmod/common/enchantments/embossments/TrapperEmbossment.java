package co.basin.lawfulmod.common.enchantments.embossments;

import co.basin.lawfulmod.common.enchantments.Embossment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class TrapperEmbossment extends Embossment {
    public TrapperEmbossment(EquipmentSlotType... slotTypes) {
        super(EnchantmentType.WEAPON, slotTypes);
    }

    @Override
    public Enchantment getParent() {
        return Enchantments.KNOCKBACK;
    }

    @Override
    public void doPostAttack(LivingEntity attacker, LivingEntity attacked, int enchantmentLevel) {
        attacked.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 5));
    }
}
