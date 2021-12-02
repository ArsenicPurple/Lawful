package co.basin.lawfulmod.core.util;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Decoder;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Foods;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.FoodStats;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.GameData;
import net.minecraftforge.registries.RegistryManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class PactUtils {
    private static final ArrayList<Item>[] pactItems = generateItems();

    private static final ArrayList<Predicate<ItemStack>> pactPredicates = generatePredicates();

    public static int isValidPactItem(ItemStack stack) {
        for (int i = 0; i < pactPredicates.size(); i++) {
            if (pactPredicates.get(i).test(stack)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean testForType(ItemStack stack, int i) {
        return pactPredicates.get(i).test(stack);
    }

    private static ArrayList<Predicate<ItemStack>> generatePredicates() {
        ArrayList<Predicate<ItemStack>> predicates = new ArrayList<>();

        predicates.add((stack) -> Items.ELYTRA.equals(stack.getItem()) || Items.FIREWORK_ROCKET.equals(stack.getItem()));
        predicates.add((stack) -> stack.isEdible() && stack.getItem().getFoodProperties().getNutrition() > 2);
        predicates.add((stack) -> Items.SHULKER_BOX.equals(stack.getItem()));
        predicates.add((ItemStack::isEnchanted));
        predicates.add((stack) -> Items.SHIELD.equals(stack.getItem()));

        return predicates;
    }

    private static ArrayList<Item>[] generateItems() {
        ArrayList<Item>[] items = new ArrayList[5];
        for (int i = 0; i < items.length; i++) {
            items[i] = new ArrayList<>();
        }
        items[0].addAll(Arrays.asList(Items.ELYTRA, Items.FIREWORK_ROCKET));

        List<Item> list = ImmutableList.copyOf(RegistryManager.ACTIVE.getRegistry(Item.class).getValues());
        for (Item item : list) {
            if (item.isEdible() && item.getFoodProperties().getNutrition() <= 2) {
                items[1].add(item);
            }
        }

        items[2].add(Items.SHULKER_BOX);
        return items;
    }
}
