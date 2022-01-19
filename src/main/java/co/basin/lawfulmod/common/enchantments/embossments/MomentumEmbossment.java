package co.basin.lawfulmod.common.enchantments.embossments;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.enchantments.Embossment;
import co.basin.lawfulmod.core.init.EnchantmentInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class MomentumEmbossment extends Embossment {
    public MomentumEmbossment(EquipmentSlotType... slotTypes) {
        super(EnchantmentType.DIGGER, slotTypes);
    }

    @Override
    public Enchantment getParent() {
        return Enchantments.BLOCK_EFFICIENCY;
    }

    @Mod.EventBusSubscriber(modid = LawfulMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class Subscriber {

        @SubscribeEvent
        public static void onGetBreakSpeed(PlayerEvent.BreakSpeed event) {
            if (EnchantmentHelper.getItemEnchantmentLevel(EnchantmentInit.MOMENTUM.get(), event.getPlayer().getMainHandItem()) == 0) { return; }

            Vector3d motion = event.getPlayer().getDeltaMovement();
            float speed = MathHelper.sqrt(motion.x * motion.x + motion.y * motion.y + motion.z * motion.z);
            LawfulMod.LOGGER.debug(event.getOriginalSpeed());
            LawfulMod.LOGGER.debug(speed * 100);
            event.setNewSpeed(event.getOriginalSpeed() + speed * 100);
        }
    }
}
