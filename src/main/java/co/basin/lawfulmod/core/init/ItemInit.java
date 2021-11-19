package co.basin.lawfulmod.core.init;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.items.AdvancedItem;
import co.basin.lawfulmod.common.items.BloodSoakedAxe;
import net.minecraft.item.Foods;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemTier;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LawfulMod.MOD_ID);

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
}
