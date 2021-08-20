package net.dakuro.arkhesys.potion;

import net.dakuro.arkhesys.item.ModItems;
import net.dakuro.arkhesys.utils.Registration;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.*;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.fml.RegistryObject;

public class ModPotions
{
    public static final RegistryObject<Effect> ASTRAAL_EFFECT = Registration.EFFECTS.register("astraal",
            () -> new ModEffect(EffectType.BENEFICIAL, 0xFF2AC8F2));

    public static final RegistryObject<Potion> ASTRAAL_POTION = Registration.POTIONS.register("astraal",
            () -> new Potion(new EffectInstance(ASTRAAL_EFFECT.get(), 3500, 0)));

    public static final RegistryObject<Potion> LONG_ASTRAAL_POTION = Registration.POTIONS.register("long_astraal",
            () -> new Potion(new EffectInstance(ASTRAAL_EFFECT.get(), 6500, 0)));

    public static final RegistryObject<Potion> STRONG_ASTRAAL_POTION = Registration.POTIONS.register("strong_astraal",
            () -> new Potion(new EffectInstance(ASTRAAL_EFFECT.get(), 3500, 1)));

    public static void addPotionRecipes(){
        BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(
                PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), Potions.REGENERATION), ModItems.CHAOS_GEM.get(),
                PotionUtils.setPotion(new ItemStack(ModItems.UNIV_BOTTLE.get()), ASTRAAL_POTION.get())));

        BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(
                PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), Potions.LONG_REGENERATION), ModItems.CHAOS_GEM.get(),
                PotionUtils.setPotion(new ItemStack(ModItems.UNIV_BOTTLE.get()), LONG_ASTRAAL_POTION.get())));

        BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(
                PotionUtils.setPotion(new ItemStack(ModItems.UNIV_BOTTLE.get()), ASTRAAL_POTION.get()), Items.REDSTONE,
                PotionUtils.setPotion(new ItemStack(ModItems.UNIV_BOTTLE.get()), LONG_ASTRAAL_POTION.get())));

        BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(
                PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), Potions.STRONG_REGENERATION), ModItems.CHAOS_GEM.get(),
                PotionUtils.setPotion(new ItemStack(ModItems.UNIV_BOTTLE.get()), STRONG_ASTRAAL_POTION.get())));

        BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(
                PotionUtils.setPotion(new ItemStack(ModItems.UNIV_BOTTLE.get()), ASTRAAL_POTION.get()), Items.GLOWSTONE_DUST,
                PotionUtils.setPotion(new ItemStack(ModItems.UNIV_BOTTLE.get()), STRONG_ASTRAAL_POTION.get())));
    }

    private static class BetterBrewingRecipe implements IBrewingRecipe {
        private final ItemStack start;
        private final Item ingredient;
        private final ItemStack result;

        public BetterBrewingRecipe(ItemStack start, Item ingredient, ItemStack result) {
            this.start = start;
            this.ingredient = ingredient;
            this.result = result;
        }

        @Override
        public boolean isInput(ItemStack start) {
            return (PotionUtils.getPotion(start).equals(PotionUtils.getPotion(this.start))) && (start.getItem().equals(this.start.getItem()));
        }

        @Override
        public boolean isIngredient(ItemStack ingredient) {
            return ingredient.getItem().equals(this.ingredient);
        }

        @Override
        public ItemStack getOutput(ItemStack start, ItemStack ingredient) {
            if(isInput(start) && isIngredient(ingredient)) {
                return this.result.copy();
            } else {
                return ItemStack.EMPTY;
            }
        }

    }

    public static void init() { }
}
