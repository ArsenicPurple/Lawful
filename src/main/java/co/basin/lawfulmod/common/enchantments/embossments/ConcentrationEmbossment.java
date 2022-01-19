package co.basin.lawfulmod.common.enchantments.embossments;

import co.basin.lawfulmod.common.enchantments.Embossment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.EquipmentSlotType;

public class ConcentrationEmbossment extends Embossment {
    protected ConcentrationEmbossment(EnchantmentType type, EquipmentSlotType... slotTypes) {
        super(type, slotTypes);
    }

    @Override
    public Enchantment getParent() {
        return Enchantments.PIERCING;
    }
}
