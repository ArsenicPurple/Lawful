package co.basin.lawfulmod.common.enchantments.embossments;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.enchantments.Embossment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;

public class LeechEmbossment extends Embossment {
    public LeechEmbossment(EquipmentSlotType... slotTypes) {
        super(EnchantmentType.WEAPON, slotTypes);
    }

    @Override
    public Enchantment getParent() {
        return Enchantments.BANE_OF_ARTHROPODS;
    }

    @Override
    public void doPostAttack(LivingEntity attacker, LivingEntity attacked, int enchantmentLevel) {
        LawfulMod.LOGGER.debug("Running Leech Enchantment");
        if (!(attacker instanceof PlayerEntity)) { return; }
        LawfulMod.LOGGER.debug(((PlayerEntity)attacker).getAttackStrengthScale(0.5F));

        //if (!attacked.isDeadOrDying()) { attacker.heal(attacker.); }
    }
}
