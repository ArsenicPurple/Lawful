package co.basin.lawfulmod.core.init;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.enchantments.HasteEnchantment;
import co.basin.lawfulmod.common.enchantments.RegenEnchantment;
import co.basin.lawfulmod.common.enchantments.embossments.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantmentInit {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, LawfulMod.MOD_ID);

    // Enchantments
    public static final RegistryObject<Enchantment> HASTE = ENCHANTMENTS.register("haste",
                        () -> new HasteEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentType.DIGGER, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND}));

    public static final RegistryObject<Enchantment> REGEN = ENCHANTMENTS.register("regen",
            () -> new RegenEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentType.ARMOR,
                    new EquipmentSlotType[]{EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET}));

    //Embossments
    public static final RegistryObject<Enchantment> SERRATED = ENCHANTMENTS.register("serrated",
            () -> new SerratedEmbossment(EquipmentSlotType.MAINHAND));

    public static final RegistryObject<Enchantment> LEECH = ENCHANTMENTS.register("leech",
            () -> new LeechEmbossment(EquipmentSlotType.MAINHAND));

    public static final RegistryObject<Enchantment> BLAZING = ENCHANTMENTS.register("blazing",
            () -> new BlazingEmbossment(EquipmentSlotType.MAINHAND));

    public static final RegistryObject<Enchantment> TRANSCENDENCE = ENCHANTMENTS.register("transcendence",
            () -> new TranscendenceEmbossment(EquipmentSlotType.MAINHAND));

    public static final RegistryObject<Enchantment> CHAINING = ENCHANTMENTS.register("chaining",
            () -> new ChainingEmbossment(EquipmentSlotType.MAINHAND));

    public static final RegistryObject<Enchantment> COMBUSTION = ENCHANTMENTS.register("combustion",
            () -> new CombustionEmbossment(EquipmentSlotType.MAINHAND));

    public static final RegistryObject<Enchantment> JUDGEMENT = ENCHANTMENTS.register("judgement",
            () -> new JudgementEmbossment(EquipmentSlotType.MAINHAND));

    public static final RegistryObject<Enchantment> MATERIALIZATION = ENCHANTMENTS.register("materialization",
            () -> new MaterializationEmbossment(EquipmentSlotType.MAINHAND));

    public static final RegistryObject<Enchantment> MOMENTUM = ENCHANTMENTS.register("momentum",
            () -> new MomentumEmbossment(EquipmentSlotType.MAINHAND));

    public static final RegistryObject<Enchantment> RESILIENCE = ENCHANTMENTS.register("resilience",
            () -> new ResilienceEmbossment(EquipmentSlotType.MAINHAND));

    public static final RegistryObject<Enchantment> SCAVENGER = ENCHANTMENTS.register("scavenger",
            () -> new ScavengerEmbossment(EquipmentSlotType.MAINHAND));

    public static final RegistryObject<Enchantment> LAUNCHING = ENCHANTMENTS.register("launching",
            () -> new LaunchingEmbossment(EquipmentSlotType.MAINHAND));

    public static final RegistryObject<Enchantment> TRAPPER = ENCHANTMENTS.register("trapper",
            () -> new TrapperEmbossment(EquipmentSlotType.MAINHAND));
}
