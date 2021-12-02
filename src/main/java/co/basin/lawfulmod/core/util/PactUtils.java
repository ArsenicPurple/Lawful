package co.basin.lawfulmod.core.util;

import com.google.common.collect.ImmutableList;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.FoodStats;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.GameData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PactUtils {
    private static Item[][] pactItems = {
            {Items.ELYTRA, Items.FIREWORK_ROCKET},
            {Items.BAKED_POTATO},
            {},
            {},
            {},
    };

    private static Item[][] generateItems() {
        ArrayList<Item>[] items = new ArrayList[5];

        items[0] = new ArrayList<>(Arrays.asList(Items.ELYTRA, Items.FIREWORK_ROCKET));

        List<Item> list = GameData.
        for (Item.) {

        }
    }
}
