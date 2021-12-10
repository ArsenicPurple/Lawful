package co.basin.lawfulmod.core.init;

import co.basin.lawfulmod.LawfulMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.*;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionInit {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, LawfulMod.MOD_ID);

    public static final RegistryObject<Potion> SUPER_STRENGTH = POTIONS.register("super_strength",
            () -> new Potion(new EffectInstance(Effects.DAMAGE_BOOST.getEffect(),800, 4),
                    new EffectInstance(Effects.MOVEMENT_SLOWDOWN.getEffect(),800, 2)
            ));
    public static final RegistryObject<Potion> LONG_SUPER_STRENGTH = POTIONS.register("long_super_strength",
            () -> new Potion(new EffectInstance(Effects.DAMAGE_BOOST.getEffect(),1600, 4),
                    new EffectInstance(Effects.MOVEMENT_SLOWDOWN.getEffect(),1600, 2)
            ));

    public static final RegistryObject<Potion> NAUSEA = POTIONS.register("nausea",
            ()-> new Potion(new EffectInstance(Effects.CONFUSION.getEffect(), 1200, 0)
            ));
    public static final RegistryObject<Potion> USEFUL_NAUSEA = POTIONS.register("useful_nausea",
            ()-> new Potion(new EffectInstance(Effects.CONFUSION.getEffect(), 1200, 0),
                    new EffectInstance(Effects.LUCK.getEffect(),1200, 2)
            ));


    public static void addPotionRecipes(){
        BrewingRecipeRegistry.addRecipe(new NewBrewingRecipe(Potions.STRONG_STRENGTH, Items.MYCELIUM, PotionInit.SUPER_STRENGTH.get()));
        BrewingRecipeRegistry.addRecipe(new NewBrewingRecipe(PotionInit.SUPER_STRENGTH.get(), Items.REDSTONE, PotionInit.LONG_SUPER_STRENGTH.get()));
        BrewingRecipeRegistry.addRecipe(new NewBrewingRecipe(Potions.AWKWARD, Items.PUFFERFISH, PotionInit.NAUSEA.get()));
        BrewingRecipeRegistry.addRecipe(new NewBrewingRecipe(PotionInit.NAUSEA.get(), Items.RABBIT_FOOT, PotionInit.USEFUL_NAUSEA.get()));
    }

    private static class NewBrewingRecipe implements IBrewingRecipe{
        private final Potion bottleInput;
        private final Item itemInput;
        private final ItemStack output;

        public NewBrewingRecipe(Potion bottleInputIn, Item itemInputIn, Potion outputIn){
            this.bottleInput = bottleInputIn;
            this.itemInput = itemInputIn;
            this.output = PotionUtils.setPotion(new ItemStack(Items.POTION), outputIn);

        }

        @Override
        public boolean isInput(ItemStack input) {
            return PotionUtils.getPotion(input).equals(this.bottleInput);
        }

        @Override
        public boolean isIngredient(ItemStack ingredient) {
            return ingredient.getItem().equals(this.itemInput);
        }

        @Override
        public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
            if (isInput(input) && isIngredient(ingredient)){
                return this.output.copy();
            }
            else{
                return ItemStack.EMPTY;
            }
        }
    }
}
