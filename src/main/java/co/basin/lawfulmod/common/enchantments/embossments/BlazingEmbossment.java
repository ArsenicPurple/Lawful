package co.basin.lawfulmod.common.enchantments.embossments;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.enchantments.Embossment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class BlazingEmbossment extends Embossment {
    public BlazingEmbossment(EquipmentSlotType... slotTypes) {
        super(EnchantmentType.WEAPON, slotTypes);
    }

    @Override
    public Enchantment getParent() {
        return Enchantments.FIRE_ASPECT;
    }

    @Override
    public void doPostAttack(LivingEntity attacker, LivingEntity attacked, int enchantmentLevel) {
        LawfulMod.LOGGER.debug("Running Blazing Enchantment");

        if (attacked.isDeadOrDying()) {
            Vector3d position = attacked.position();
            attacker.level.explode(attacked, new DamageSource("lawful.blazing_explosion"), null, position.x, position.y, position.z, 2.0F, false, Explosion.Mode.NONE);
        }
    }

    @Mod.EventBusSubscriber(modid = LawfulMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class Subscriber {

        @SubscribeEvent
        public static void onLivingDeath(LivingDeathEvent event) {
            System.out.println(event.getSource().msgId);
            if (!event.getSource().msgId.equals("lawful.blazing_explosion")) { return; }

            LivingEntity entity = event.getEntityLiving();
            Vector3d position = entity.position();
            entity.level.explode(entity, new DamageSource("lawful.blazing_explosion"), null, position.x, position.y, position.z, 2.0F, false, Explosion.Mode.NONE);
        }
    }
}
