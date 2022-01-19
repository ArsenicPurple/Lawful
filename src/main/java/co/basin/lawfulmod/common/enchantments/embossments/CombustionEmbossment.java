package co.basin.lawfulmod.common.enchantments.embossments;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.enchantments.Embossment;
import co.basin.lawfulmod.common.entities.MagicProjectileEntity;
import co.basin.lawfulmod.core.init.EnchantmentInit;
import co.basin.lawfulmod.core.util.ItemNBTUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class CombustionEmbossment extends Embossment {
    public CombustionEmbossment(EquipmentSlotType... slotTypes) {
        super(EnchantmentType.BOW, slotTypes);
    }

    public static final String TAG_TIME_FLAMING_STARTED = "timeFlamingStarted";

    @Override
    public Enchantment getParent() {
        return Enchantments.FLAMING_ARROWS;
    }

    @Override
    public void doPostAttack(LivingEntity attacker, LivingEntity attacked, int enchantmentLevel) {
        ItemStack bow;
        if (!(attacker.getMainHandItem().getItem() instanceof BowItem)) { return; }
        bow = attacker.getMainHandItem();

        if (attacked.isDeadOrDying()) { setTimeFlamingStarted(bow, attacker.level.getGameTime()); }
    }

    private void setTimeFlamingStarted(ItemStack stack, long time) {
        ItemNBTUtil.setLong(stack, TAG_TIME_FLAMING_STARTED, time);
    }

    @Mod.EventBusSubscriber(modid = LawfulMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class Subscriber {

        @SubscribeEvent
        public static void onArrowLoose(ArrowLooseEvent event) {
            if (EnchantmentHelper.getItemEnchantmentLevel(EnchantmentInit.COMBUSTION.get(), event.getBow()) == 0) { return; }
            if (event.getWorld().getGameTime() - ItemNBTUtil.getLong(event.getBow(), TAG_TIME_FLAMING_STARTED, 0) > 40) { return; }
            event.setCanceled(true);

            MagicProjectileEntity projectile = new MagicProjectileEntity(event.getEntityLiving(), event.getEntityLiving().level)
                    .setDamage(24)
                    .setParticle(ParticleTypes.FLAME);
            projectile.shoot(event.getEntityLiving().getLookAngle(), 5, 0);
            event.getEntityLiving().level.addFreshEntity(projectile);
        }
    }
}
