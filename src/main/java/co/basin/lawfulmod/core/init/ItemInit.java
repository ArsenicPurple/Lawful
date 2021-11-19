package co.basin.lawfulmod.core.init;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.items.AdvancedItem;
import co.basin.lawfulmod.common.items.BloodSoakedAxe;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LawfulMod.MOD_ID);

    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item",
            () -> new Item(new Item.Properties()
                    .tab(ItemGroup.TAB_MISC)
            ));


    public static final RegistryObject<AdvancedItem> ADVANCED_EXAMPLE_ITEM = ITEMS.register("advanced_item",
            () -> new AdvancedItem(new Item.Properties()
                    .tab(ItemGroup.TAB_MISC)
                    .food(Foods.CARROT)
            ));

    public static final RegistryObject<BloodSoakedAxe> BLOOD_SOAKED_AXE = ITEMS.register("blood_soaked_axe",
            () -> new BloodSoakedAxe(ItemTier.NETHERITE, 3, -2.4F, (new Item.Properties())
                    .tab(ItemGroup.TAB_COMBAT)
                    .fireResistant()
            ));

    public static final RegistryObject<Item> MANA_INGOT = ITEMS.register("mana_ingot_item",
            () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));



    //Block Items
    public static final RegistryObject<BlockItem> MANA_WOOD = ITEMS.register("mana_wood",
            () -> new BlockItem(BlockInit.MANA_WOOD.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<BlockItem> MANA_IRON_ORE = ITEMS.register("mana_iron_ore",
            () -> new BlockItem(BlockInit.MANA_IRON_ORE.get(), new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
}
