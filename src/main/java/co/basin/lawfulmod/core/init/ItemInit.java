package co.basin.lawfulmod.core.init;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.items.AdvancedItem;
import co.basin.lawfulmod.common.items.BloodSoakedAxe;
import co.basin.lawfulmod.common.items.ConvenantPaper;
import co.basin.lawfulmod.common.items.ModItemTier;
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
  
    public static final RegistryObject<Item> MANA_INGOT = ITEMS.register("mana_ingot_item",
            () -> new Item(new Item.Properties()
                    .tab(ItemGroup.TAB_MATERIALS)
            ));

    public static final RegistryObject<Item> MANA_POWDER = ITEMS.register("mana_powder",
            () -> new Item(new Item.Properties()
                    .tab(ItemGroup.TAB_MATERIALS)
            ));

    public static final RegistryObject<Item> ENRICHED_MANA_INGOT = ITEMS.register("enriched_mana_ingot",
            () -> new Item(new Item.Properties()
                    .tab(ItemGroup.TAB_MATERIALS)
            ));



    //Block Items
    public static final RegistryObject<BlockItem> MANA_WOOD = ITEMS.register("mana_wood",
            () -> new BlockItem(BlockInit.MANA_WOOD.get(), new Item.Properties()
                    .tab(ItemGroup.TAB_BUILDING_BLOCKS)
            ));

    public static final RegistryObject<BlockItem> MANA_IRON_ORE = ITEMS.register("mana_iron_ore",
            () -> new BlockItem(BlockInit.MANA_IRON_ORE.get(), new Item.Properties()
                    .tab(ItemGroup.TAB_MATERIALS)
            ));

    public static final RegistryObject<BlockItem> ENRICHED_MANA_ORE = ITEMS.register("enriched_mana_ore",
            () -> new BlockItem(BlockInit.ENRICHED_MANA_ORE.get(), new Item.Properties()
                    .tab(ItemGroup.TAB_MATERIALS)
            ));
  
    public static final RegistryObject<ConvenantPaper> COVENANT_PAPER = ITEMS.register("covenant_paper",
            () -> new ConvenantPaper(new Item.Properties()
                    .tab(ItemGroup.TAB_MISC)
            ));

    // Block Items
    public static final RegistryObject<BlockItem> PACTING_TABLE_BLOCK = ITEMS.register("pacting_table",
            () -> new BlockItem(BlockInit.PACTING_TABLE_BLOCK.get(), new Item.Properties()
                    .tab(ItemGroup.TAB_MISC)
            ));
}
