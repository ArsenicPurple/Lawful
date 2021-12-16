package co.basin.lawfulmod.core.util;

import co.basin.lawfulmod.common.items.CovenantPaper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

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

    public static boolean isItemBreakingCovenant(ItemStack stack, int type) {
        return stack != null && !stack.isEmpty() && (stack.getItem() instanceof CovenantPaper || pactPredicates.get(type).test(stack));
    }

    public static EffectInstance[] getPactEffects(int type) {
        switch (type) {
            case 0: return new EffectInstance[] { new EffectInstance(Effects.MOVEMENT_SPEED, 200, 6), new EffectInstance(Effects.JUMP, 200, 2) };
            case 1: return new EffectInstance[] { new EffectInstance(Effects.DAMAGE_BOOST, 200, 5) };
            case 2: return new EffectInstance[] { new EffectInstance(Effects.HERO_OF_THE_VILLAGE, 200, 20) };
            case 3: return new EffectInstance[] { new EffectInstance(Effects.DAMAGE_RESISTANCE, 200, 255) };
            case 4: return new EffectInstance[] { new EffectInstance(Effects.HEALTH_BOOST, 200, 10) };
            default: return null;
        }
    }

    public static ITextComponent getName(int type) {
        switch (type) {
            case 0: return new TranslationTextComponent("covenant.lawful.movement");
            case 1: return new TranslationTextComponent("covenant.lawful.damage");
            case 2: return new TranslationTextComponent("covenant.lawful.haggling");
            case 3: return new TranslationTextComponent("covenant.lawful.immortality");
            case 4: return new TranslationTextComponent("covenant.lawful.vitality");
            default: return null;
        }
    }

    private static ArrayList<Predicate<ItemStack>> generatePredicates() {
        ArrayList<Predicate<ItemStack>> predicates = new ArrayList<>();

        predicates.add((stack) -> Items.ELYTRA.equals(stack.getItem()) || Items.FIREWORK_ROCKET.equals(stack.getItem()));
        predicates.add((stack) -> stack.isEdible() && stack.getItem().getFoodProperties().getNutrition() > 1.5);
        predicates.add((stack) -> Items.SHULKER_BOX.equals(stack.getItem()));
        predicates.add((stack -> stack.isEnchanted()));
        predicates.add((stack) -> Items.SHIELD.equals(stack.getItem()));

        return predicates;
    }
}
