package co.basin.lawfulmod.common.enchants;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.core.init.EnchantmentInit;
import net.minecraft.enchantment.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class RegenEnchantment extends Enchantment {

    public RegenEnchantment(Rarity rarity, EnchantmentType type, EquipmentSlotType[] slotTypes) {
        super(rarity, type, slotTypes);
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public int getMinLevel() {
        return 1;
    }

    @Override
    protected boolean checkCompatibility(Enchantment enchantment) {
        return !(enchantment instanceof ProtectionEnchantment);
    }

    /* Create an event bus to "subscribe" to - Code will run every time that event triggers
     * Event triggers every in-game tick, or 20 times a second
     */
    @Mod.EventBusSubscriber(modid = LawfulMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class RegenEquipped {
        private static long lastTick;

        @SubscribeEvent
        /**
         * This method will trigger once per tick to check if the player is wearing an item with the Regen enchantment
         * if so, the player will get Regen 1, or Regen 2, depending on th level of the enchantment
         * @param event a PlayerTickEvent
         */
        public static void tickEnchantment(TickEvent.PlayerTickEvent event) {
            if (event.player.level.getGameTime() - lastTick < 40) { return; }
            lastTick = event.player.level.getGameTime();

            //Player who will have items checked
            PlayerEntity playerIn = event.player;

            //Regen effect
            Effect regenId = Effect.byId(10);

            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.REGEN.get(),playerIn) - 1;
            if (level < 0) { return; }
            assert regenId != null;
            playerIn.addEffect( new EffectInstance(regenId, 20, level));
        }
    }
}
