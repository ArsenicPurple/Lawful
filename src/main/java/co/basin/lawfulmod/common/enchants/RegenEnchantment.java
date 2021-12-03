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
    @Mod.EventBusSubscriber(modid = LawfulMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class RegenEquipped{
        @SubscribeEvent
        public static void doStuff(TickEvent.PlayerTickEvent event){
            PlayerEntity playerIn = event.player;
            Effect test =  Effect.byId(10);
            EffectInstance healy = new EffectInstance(test,20);
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
