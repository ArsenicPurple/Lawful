package co.basin.lawfulmod.core.init;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.enchants.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantmentInit {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, LawfulMod.MOD_ID);

    public static final RegistryObject<Enchantment> HASTE = ENCHANTMENTS.register("haste",
                        () -> new HasteEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentType.DIGGER, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND}));

    public static final RegistryObject<Enchantment> REGEN = ENCHANTMENTS.register("regen",
            () -> new RegenEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentType.ARMOR,
                    new EquipmentSlotType[]{EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET}));
}
