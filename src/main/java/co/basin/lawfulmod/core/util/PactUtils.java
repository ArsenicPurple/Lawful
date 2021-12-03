package co.basin.lawfulmod.core.util;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PactUtils {
    private static final ArrayList<Predicate<ItemStack>> pactPredicates = generatePredicates();

    public static int isValidPactItem(ItemStack stack) {
        for (int i = 0; i < pactPredicates.size(); i++) {
            if (pactPredicates.get(i).test(stack)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean testForType(ItemStack stack, int type) {
        return stack != null && !stack.isEmpty() && pactPredicates.get(type).test(stack);
    }

    public static EffectInstance[] getPactEffects(int type) {
        switch (type) {
            case 0: return new EffectInstance[] { new EffectInstance(Effects.MOVEMENT_SPEED, 6), new EffectInstance(Effects.JUMP, 2) };
            case 1: return new EffectInstance[] { new EffectInstance(Effects.DAMAGE_BOOST, 5) };
            case 2: return new EffectInstance[] { new EffectInstance(Effects.HERO_OF_THE_VILLAGE, 20) };
            case 3: return new EffectInstance[] { new EffectInstance(Effects.DAMAGE_RESISTANCE, 255) };
            case 4: return new EffectInstance[] { new EffectInstance(Effects.HEALTH_BOOST, 10) };
            default: return null;
        }
    }

    private static ArrayList<Predicate<ItemStack>> generatePredicates() {
        ArrayList<Predicate<ItemStack>> predicates = new ArrayList<>();

        predicates.add((stack) -> Items.ELYTRA.equals(stack.getItem()) || Items.FIREWORK_ROCKET.equals(stack.getItem()));
        predicates.add((stack) -> stack.isEdible() && stack.getItem().getFoodProperties().getNutrition() > 1.5);
        predicates.add((stack) -> Items.SHULKER_BOX.equals(stack.getItem()));
        predicates.add(ItemStack::isEnchanted);
        predicates.add((stack) -> Items.SHIELD.equals(stack.getItem()));

        return predicates;
    }
}
