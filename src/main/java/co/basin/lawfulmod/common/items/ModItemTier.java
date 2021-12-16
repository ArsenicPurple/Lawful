package co.basin.lawfulmod.common.items;

import co.basin.lawfulmod.LawfulMod;
import co.basin.lawfulmod.common.items.AdvancedItem;
import co.basin.lawfulmod.core.init.ItemInit;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import java.util.function.Supplier;

/**
 * Creation of an item tier
 */
public enum ModItemTier implements IItemTier {
    /**
     * Level is the relative level of the item, this prevents some item tiers from mining some blocks
     * Uses is the durability value of the item
     * Speed is the mining speed of the item
     * Damage is how much damage the item does
     * Enchantment value raises the average level, and rarity, of enchantments you get when enchanting the item
     */
    Mana(3, 100, 10.0F, 2.5F, 20,
            () -> Ingredient.of(ItemInit.MANA_INGOT.get())),
    EnrichedMana(5, 2500, 11.0F, 5.0F, 25,
            () -> Ingredient.of(ItemInit.ENRICHED_MANA_INGOT.get()))
    ;

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyValue<Ingredient> repairIngredient;

    ModItemTier(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = new LazyValue<>(repairIngredient);
    }

    @Override
    public int getUses() {
        return this.uses;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.damage;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
