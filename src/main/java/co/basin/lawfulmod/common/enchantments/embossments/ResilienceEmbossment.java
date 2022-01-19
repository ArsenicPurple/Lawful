package co.basin.lawfulmod.common.enchantments.embossments;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.enchantments.Embossment;
import co.basin.lawfulmod.core.init.EnchantmentInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.MobEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ResilienceEmbossment extends Embossment {
    public ResilienceEmbossment(EquipmentSlotType... slotTypes) {
        super(EnchantmentType.BREAKABLE, slotTypes);
    }

    @Override
    public Enchantment getParent() {
        return Enchantments.UNBREAKING;
    }

    @Mod.EventBusSubscriber(modid = LawfulMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class Subscriber {

        @SubscribeEvent
        public static void onPlayerDestroyItem(PlayerDestroyItemEvent event) {
            if (EnchantmentHelper.getItemEnchantmentLevel(EnchantmentInit.RESILIENCE.get(), event.getOriginal()) == 0) { return; }

            if (event.getHand() != null) {
                event.getPlayer().inventory.add(event.getPlayer().inventory.selected, event.getOriginal());
                return;
            }

            if (event.getOriginal().getItem() instanceof ArmorItem) {
                EquipmentSlotType equipmentslottype = MobEntity.getEquipmentSlotForItem(event.getOriginal());
                if (event.getPlayer().getItemBySlot(equipmentslottype).isEmpty()) {
                    event.getPlayer().setItemSlot(equipmentslottype, event.getOriginal());
                    event.getOriginal().setCount(0);
                }
                return;
            }

            event.getPlayer().addItem(event.getOriginal());
        }

        @SubscribeEvent
        public static void onUseItem(LivingEntityUseItemEvent.Start event) {
            if (EnchantmentHelper.getItemEnchantmentLevel(EnchantmentInit.RESILIENCE.get(), event.getItem()) == 0) { return; }
            if (event.getItem().getDamageValue() <= 1) {
                event.setCanceled(true);
            }
        }
    }
}
