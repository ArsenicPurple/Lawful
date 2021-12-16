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
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

public class HasteEnchantment extends Enchantment {

    public HasteEnchantment(Rarity rarity, EnchantmentType type, EquipmentSlotType[] slotTypes) {
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

    /* Create an event bus to "subscribe" to - Code will run every time that event triggers
     * Event triggers every in-game tick, or 20 times a second
     */
    @Mod.EventBusSubscriber(modid = LawfulMod.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
    public static class HasteEquipped {
        private static long lastTick;

        @SubscribeEvent
        /**
         * This method will trigger once per 40 ticks to check if the player is holding an item with the Haste enchantment
         * if so, the player will get Haste 1, or Haste 2, depending on the level of the enchantment
         * @param event a PlayerTickEvent
         */
        public static void tickEnchantment(TickEvent.PlayerTickEvent event) {
            if (event.player.level.getGameTime() - lastTick < 40) { return; }
            lastTick = event.player.level.getGameTime();

            //The player who will have items checked
            PlayerEntity playerIn = event.player;
            //Haste effect ID
            Effect hasteId = Effect.byId(3);

            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.HASTE.get(),playerIn) - 1;
            if (level < 0) { return; }
            assert hasteId != null;
            playerIn.addEffect(new EffectInstance(hasteId, 60, level));
        }
    }
}
