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

    public HasteEnchantment(Rarity p_i46731_1_, EnchantmentType p_i46731_2_, EquipmentSlotType[] p_i46731_3_) {
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
    @Mod.EventBusSubscriber(modid = LawfulMod.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
    public static class HasteEquipped{
        @SubscribeEvent
        public static void doStuff(TickEvent.PlayerTickEvent event){
            PlayerEntity playerIn = event.player;
            Effect test =  Effect.byId(3);
            EffectInstance zoom = new EffectInstance(test,20);
            EffectInstance zoom2 = new EffectInstance(test, 20, 1);

            if(playerIn.getMainHandItem().isEnchanted()){
                if(EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.HASTE.get(),playerIn) == 1) {
                    playerIn.addEffect(zoom);
                }
                else if(EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.HASTE.get(),playerIn) == 2){
                playerIn.addEffect(zoom2);
                }
            }
        }
    }
}
