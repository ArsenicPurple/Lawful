package co.basin.lawfulmod.core.init;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.items.AdvancedItem;
import co.basin.lawfulmod.common.items.BloodSoakedAxe;
import co.basin.lawfulmod.common.items.CovenantPaper;
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
  
    public static final RegistryObject<Item> MANA_INGOT = ITEMS.register("mana_ingot_item",
            () -> new Item(new Item.Properties()
                    .tab(LawfulMod.LAWFUL_GROUP)
            ));

    //Block Items
    public static final RegistryObject<BlockItem> MANA_WOOD = ITEMS.register("mana_wood",
            () -> new BlockItem(BlockInit.MANA_WOOD.get(), new Item.Properties()
                    .tab(LawfulMod.LAWFUL_GROUP)
            ));

    public static final RegistryObject<BlockItem> MANA_IRON_ORE = ITEMS.register("mana_iron_ore",
            () -> new BlockItem(BlockInit.MANA_IRON_ORE.get(), new Item.Properties()
                    .tab(LawfulMod.LAWFUL_GROUP)
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
}
