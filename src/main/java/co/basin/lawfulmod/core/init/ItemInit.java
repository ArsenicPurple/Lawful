package co.basin.lawfulmod.core.init;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.items.AdvancedItem;
import co.basin.lawfulmod.common.items.BloodSoakedAxe;
import co.basin.lawfulmod.common.items.ConvenantPaper;
import co.basin.lawfulmod.common.items.ModArmorMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LawfulMod.MOD_ID);

    // Items
    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item",
            () -> new Item(new Item.Properties()
                    .tab(LawfulMod.LAWFUL_GROUP)
            ));

    public static final RegistryObject<AdvancedItem> ADVANCED_EXAMPLE_ITEM = ITEMS.register("advanced_item",
            () -> new AdvancedItem(new Item.Properties()
                    .tab(LawfulMod.LAWFUL_GROUP)
                    .food(Foods.CARROT)
            ));

    // Tools
    public static final RegistryObject<BloodSoakedAxe> BLOOD_SOAKED_AXE = ITEMS.register("blood_soaked_axe",
            () -> new BloodSoakedAxe(ItemTier.NETHERITE, 3, -2.4F, (new Item.Properties())
                    .tab(LawfulMod.LAWFUL_GROUP)
                    .fireResistant()
            ));

    public static final RegistryObject<PickaxeItem> MANA_PICKAXE = ITEMS.register("mana_pickaxe", () -> new
            PickaxeItem(ModItemTier.Mana, 0, -2.8F, (new Item.Properties())
            .tab(LawfulMod.LAWFUL_GROUP)
            ));

    public static final RegistryObject<AxeItem> MANA_AXE = ITEMS.register("mana_axe", () -> new
            AxeItem(ModItemTier.Mana, 2, -1.8F, (new Item.Properties())
            .tab(LawfulMod.LAWFUL_GROUP)
            ));

    public static final RegistryObject<ShovelItem> MANA_SHOVEL = ITEMS.register("mana_shovel", () -> new
            ShovelItem(ModItemTier.Mana, 0, -3F, (new Item.Properties())
            .tab(LawfulMod.LAWFUL_GROUP)
            ));

    public static final RegistryObject<SwordItem> MANA_SWORD = ITEMS.register("mana_sword", () -> new
            SwordItem(ModItemTier.Mana, 4, -1F, (new Item.Properties())
            .tab(LawfulMod.LAWFUL_GROUP)
            ));

    public static final RegistryObject<HoeItem> MANA_HOE = ITEMS.register("mana_hoe", () -> new
            HoeItem(ModItemTier.Mana, 0, -3.5F, (new Item.Properties())
            .tab(LawfulMod.LAWFUL_GROUP)
            ));

    public static final RegistryObject<Item> MANA_INGOT = ITEMS.register("mana_ingot_item",
            () -> new Item(new Item.Properties().tab(LawfulMod.LAWFUL_GROUP)));

    //Block Items
    public static final RegistryObject<BlockItem> MANA_WOOD = ITEMS.register("mana_wood",
            () -> new BlockItem(BlockInit.MANA_WOOD.get(), new Item.Properties().tab(LawfulMod.LAWFUL_GROUP)));

    public static final RegistryObject<BlockItem> MANA_IRON_ORE = ITEMS.register("mana_iron_ore",
            () -> new BlockItem(BlockInit.MANA_IRON_ORE.get(), new Item.Properties().tab(LawfulMod.LAWFUL_GROUP)));
  
    public static final RegistryObject<ConvenantPaper> COVENANT_PAPER = ITEMS.register("covenant_paper",
            () -> new ConvenantPaper(new Item.Properties()
                    .tab(LawfulMod.LAWFUL_GROUP)
            ));

    // Block Items
    public static final RegistryObject<BlockItem> PACTING_TABLE_BLOCK = ITEMS.register("pacting_table",
            () -> new BlockItem(BlockInit.PACTING_TABLE_BLOCK.get(), new Item.Properties()
                    .tab(LawfulMod.LAWFUL_GROUP)
            ));

    public static final RegistryObject<Item> MANA_BOOTS = ITEMS.register("mana_boots",
            () -> new ArmorItem(ModArmorMaterial.MANA, EquipmentSlotType.FEET, new Item.Properties()
                    .tab(ItemGroup.TAB_COMBAT)
            ));

    public static final RegistryObject<Item> MANA_CHESTPLATE = ITEMS.register("mana_chestplate",
            () -> new ArmorItem(ModArmorMaterial.MANA, EquipmentSlotType.CHEST, new Item.Properties()
                    .tab(ItemGroup.TAB_COMBAT)
            ));

    public static final RegistryObject<Item> MANA_LEGGINGS = ITEMS.register("mana_leggings",
            () -> new ArmorItem(ModArmorMaterial.MANA, EquipmentSlotType.LEGS, new Item.Properties()
                    .tab(ItemGroup.TAB_COMBAT)
            ));

    public static final RegistryObject<Item> MANA_HELMET = ITEMS.register("mana_helmet",
            () -> new ArmorItem(ModArmorMaterial.MANA, EquipmentSlotType.HEAD, new Item.Properties()
                    .tab(ItemGroup.TAB_COMBAT)
            ));
}
