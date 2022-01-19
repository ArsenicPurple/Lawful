package co.basin.lawfulmod.core.init;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.effects.BleedingEffect;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectInit {
    public static DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, LawfulMod.MOD_ID);

    public static final RegistryObject<BleedingEffect> BLEEDING = EFFECTS.register("bleeding",
            () -> new BleedingEffect(EffectType.HARMFUL, 13113111));

}
