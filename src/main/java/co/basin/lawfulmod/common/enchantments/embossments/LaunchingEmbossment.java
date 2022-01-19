package co.basin.lawfulmod.common.enchantments.embossments;

import co.basin.lawfulmod.common.enchantments.Embossment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.math.vector.Vector3d;

public class LaunchingEmbossment extends Embossment {
    public LaunchingEmbossment(EquipmentSlotType... slotTypes) {
        super(EnchantmentType.BOW, slotTypes);
    }

    @Override
    public Enchantment getParent() {
        return Enchantments.PUNCH_ARROWS;
    }

    @Override
    public void doPostAttack(LivingEntity attacker, LivingEntity attacked, int enchantmentLevel) {
        Vector3d motion = attacked.getDeltaMovement();
        attacked.setDeltaMovement(motion.x, motion.y + 3, motion.z);
    }
}
