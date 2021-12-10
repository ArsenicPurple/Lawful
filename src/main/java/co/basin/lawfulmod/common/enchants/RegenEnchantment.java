package co.basin.lawfulmod.common.enchants;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.core.init.EnchantmentInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class RegenEnchantment extends Enchantment{
    public RegenEnchantment(Rarity p_i46731_1_, EnchantmentType p_i46731_2_, EquipmentSlotType[] p_i46731_3_) {
        super(p_i46731_1_, p_i46731_2_, p_i46731_3_);
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public int getMinLevel() {
        return 1;
    }
    /*create an event bus to "subscribe" to - code will run every time that event triggers
     * event triggers every ingame tick, or 20 times a second
     */
    @Mod.EventBusSubscriber(modid = LawfulMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class RegenEquipped{
        @SubscribeEvent
        /**
         * This method will trigger once per tick to check if the player is wearing an item with the Regen enchantment
         * if so, the player will get Regen 1, or Regen 2, depending on th level of the enchantment
         * @param event a PlayerTickEvent
         */
        public static void doStuff(TickEvent.PlayerTickEvent event){
            //Player who will have items checked
            PlayerEntity playerIn = event.player;
            //Regen effect
            Effect test =  Effect.byId(10);
            //Instance of Regen 1, lasting 1 second
            EffectInstance healy = new EffectInstance(test,20);
            //Instance of Regen 2, lasting 1 second
            EffectInstance healy2 = new EffectInstance(test, 20, 1);

            if(playerIn.getItemBySlot(EquipmentSlotType.HEAD).isEnchanted() || playerIn.getItemBySlot(EquipmentSlotType.CHEST).isEnchanted()
            || playerIn.getItemBySlot(EquipmentSlotType.LEGS).isEnchanted() || playerIn.getItemBySlot(EquipmentSlotType.FEET).isEnchanted()){
                if(EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.REGEN.get(),playerIn) == 1) {
                    playerIn.addEffect(healy);
                }
                else if(EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.REGEN.get(),playerIn) == 2){
                    playerIn.addEffect(healy2);
                }
            }
        }
    }
}
