package co.basin.lawfulmod.common.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;

public abstract class Embossment extends Enchantment {
    protected Embossment(EnchantmentType type, EquipmentSlotType... slotTypes) {
        super(Rarity.VERY_RARE, type, slotTypes);
    }

    public abstract Enchantment getParent();

    public void doPostAttack(LivingEntity attacker, LivingEntity attacked, int enchantmentLevel) {}

    @Override
    public void doPostAttack(LivingEntity attacker, Entity attacked, int enchantmentLevel) {
        if (!(attacked instanceof LivingEntity)) { return; }

        doPostAttack(attacker, (LivingEntity) attacked, enchantmentLevel);
    }

    @Override
    protected boolean checkCompatibility(Enchantment enchantment) {
        return !(enchantment instanceof Embossment);
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    @Override
    public boolean isDiscoverable() {
        return false;
    }

    @Override
    public boolean isTradeable() {
        return false;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return false;
    }
}
