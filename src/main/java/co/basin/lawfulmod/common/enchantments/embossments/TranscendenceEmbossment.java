package co.basin.lawfulmod.common.enchantments.embossments;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.enchantments.Embossment;
import co.basin.lawfulmod.common.entities.TranscendenceProjectileEntity;
import co.basin.lawfulmod.core.init.EnchantmentInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class TranscendenceEmbossment extends Embossment {
    public TranscendenceEmbossment(EquipmentSlotType... slotTypes) {
        super(EnchantmentType.WEAPON, slotTypes);
    }

    @Override
    public Enchantment getParent() {
        return Enchantments.SWEEPING_EDGE;
    }

    @Override
    public void doPostAttack(LivingEntity attacker, LivingEntity attacked, int enchantmentLevel) { }

    @Mod.EventBusSubscriber(modid = LawfulMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class Subscriber {

        @SubscribeEvent
        public static void onPlayerRightClickItem(PlayerInteractEvent.RightClickItem event) {
            PlayerEntity player = event.getPlayer();
            ItemStack stack = event.getItemStack();
            if (stack.isEnchanted() && EnchantmentHelper.getItemEnchantmentLevel(EnchantmentInit.TRANSCENDENCE.get(), stack) != 0) {
                LawfulMod.LOGGER.debug("Making Transcendence Projectile");
                TranscendenceProjectileEntity projectile = new TranscendenceProjectileEntity(player, stack);
                projectile.shoot(player.getLookAngle(), 2, 1);
                player.level.addFreshEntity(projectile);

                LawfulMod.LOGGER.debug("Shooting Transcendence Projectile");
            }
//            PlayerEntity player = event.getPlayer();
//            ItemStack stack = event.getItemStack();
//            if (stack.isEnchanted() && EnchantmentHelper.getItemEnchantmentLevel(EnchantmentInit.TRANSCENDENCE.get(), stack) != 0) {
//                MagicProjectileEntity projectile = new MagicProjectileEntity(player, player.level)
//                        .setDamage(2)
//                        .setParticle(ParticleTypes.SWEEP_ATTACK);
//                projectile.shoot(player.getLookAngle(), 2, 1);
//                player.level.addFreshEntity(projectile);
//            }
        }
    }
}
