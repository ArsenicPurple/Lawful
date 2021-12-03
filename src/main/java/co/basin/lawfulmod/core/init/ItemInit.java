package co.basin.lawfulmod.core.init;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.items.*;
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

    public static final RegistryObject<SwordItem> ENRICHED_MANA_SWORD = ITEMS.register("enriched_mana_sword",
            () -> new SwordItem(ModItemTier.EnrichedMana, 2, 0F,
                    new Item.Properties().tab(LawfulMod.LAWFUL_GROUP)));

    public static final RegistryObject<Item> ENRICHED_MANA_AXE = ITEMS.register("enriched_mana_axe",
            () -> new AxeItem(ModItemTier.EnrichedMana, 4, -2.5F,
                    new Item.Properties().tab(LawfulMod.LAWFUL_GROUP)));

    public static final RegistryObject<Item> ENRICHED_MANA_SHOVEL = ITEMS.register("enriched_mana_shovel",
            () -> new ShovelItem(ModItemTier.EnrichedMana, 1, -2F,
                    new Item.Properties().tab(LawfulMod.LAWFUL_GROUP)));

    public static final RegistryObject<Item> ENRICHED_MANA_PICKAXE = ITEMS.register("enriched_mana_pickaxe",
            () -> new PickaxeItem(ModItemTier.EnrichedMana, 1, -2F,
                    new Item.Properties().tab(LawfulMod.LAWFUL_GROUP)));

    public static final RegistryObject<Item> ENRICHED_MANA_HOE = ITEMS.register("enriched_mana_hoe",
            () -> new HoeItem(ModItemTier.EnrichedMana, -4, 0F,
                    new Item.Properties().tab(LawfulMod.LAWFUL_GROUP)));

    public static final RegistryObject<Item> MANA_POWDER = ITEMS.register("mana_powder",
            () -> new Item(new Item.Properties()
                    .tab(LawfulMod.LAWFUL_GROUP)
            ));

    public static final RegistryObject<Item> ENRICHED_MANA_INGOT = ITEMS.register("enriched_mana_ingot",
            () -> new Item(new Item.Properties()
                    .tab(ItemGroup.TAB_MATERIALS)
            ));

    public static final RegistryObject<PickaxeItem> MANA_PICKAXE = ITEMS.register("mana_pickaxe", () -> new
            PickaxeItem(ModItemTier.MANA, 0, -2.8F, (new Item.Properties())
            .tab(LawfulMod.LAWFUL_GROUP)
            ));

    public static final RegistryObject<AxeItem> MANA_AXE = ITEMS.register("mana_axe", () -> new
            AxeItem(ModItemTier.MANA, 2, -1.8F, (new Item.Properties())
            .tab(LawfulMod.LAWFUL_GROUP)
            ));

    public static final RegistryObject<ShovelItem> MANA_SHOVEL = ITEMS.register("mana_shovel", () -> new
            ShovelItem(ModItemTier.MANA, 0, -3F, (new Item.Properties())
            .tab(LawfulMod.LAWFUL_GROUP)
            ));

    public static final RegistryObject<SwordItem> MANA_SWORD = ITEMS.register("mana_sword", () -> new
            SwordItem(ModItemTier.MANA, 4, -1F, (new Item.Properties())
            .tab(LawfulMod.LAWFUL_GROUP)
            ));

    public static final RegistryObject<HoeItem> MANA_HOE = ITEMS.register("mana_hoe", () -> new
            HoeItem(ModItemTier.MANA, 0, -3.5F, (new Item.Properties())
            .tab(LawfulMod.LAWFUL_GROUP)
            ));

    public static final RegistryObject<Item> MANA_INGOT = ITEMS.register("mana_ingot_item",
            () -> new Item(new Item.Properties().tab(LawfulMod.LAWFUL_GROUP)));

    //Block Items
    public static final RegistryObject<BlockItem> MANA_WOOD = ITEMS.register("mana_wood",
            () -> new BlockItem(BlockInit.MANA_WOOD.get(), new Item.Properties()
                    .tab(LawfulMod.LAWFUL_GROUP)
            ));

    public static final RegistryObject<BlockItem> MANA_IRON_ORE = ITEMS.register("mana_iron_ore",
            () -> new BlockItem(BlockInit.MANA_IRON_ORE.get(), new Item.Properties()
                    .tab(LawfulMod.LAWFUL_GROUP)
            ));

    public static final RegistryObject<BlockItem> ENRICHED_MANA_ORE = ITEMS.register("enriched_mana_ore",
            () -> new BlockItem(BlockInit.ENRICHED_MANA_ORE.get(), new Item.Properties()
                    .tab(ItemGroup.TAB_MATERIALS)
            ));
  
    public static final RegistryObject<CovenantPaper> COVENANT_PAPER = ITEMS.register("covenant_paper",
            () -> new CovenantPaper(new Item.Properties()
                    .tab(LawfulMod.LAWFUL_GROUP)
            ));

    // Block Items
    public static final RegistryObject<BlockItem> PACTING_TABLE_BLOCK = ITEMS.register("pacting_table",
            () -> new BlockItem(BlockInit.PACTING_TABLE_BLOCK.get(), new Item.Properties()
                    .tab(LawfulMod.LAWFUL_GROUP)
            ));

    public static final RegistryObject<BlockItem> COVENANT_PEDESTAL = ITEMS.register("covenant_pedestal",
            () -> new BlockItem(BlockInit.COVENANT_PEDESTAL.get(), new Item.Properties()
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
