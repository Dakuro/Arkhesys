package net.dakuro.arkhesys.crafting.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.dakuro.arkhesys.block.ModBlocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.util.RecipeMatcher;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class TransmutationRecipe implements IRecipe<IInventory> {

    private final ResourceLocation recipeId;
    private final NonNullList<Ingredient> ingredients;
    private final ItemStack result;
    private final float experience;
    private final int mutationTime;
    private final int fluidCost;

    public TransmutationRecipe(ResourceLocation recipeId, NonNullList<Ingredient> ingredients, ItemStack result, float experience, int mutationTime, int fluidCost) {
        this.recipeId = recipeId;
        this.ingredients = ingredients;
        this.result = result;
        this.experience = experience;
        this.mutationTime = mutationTime;
        this.fluidCost = fluidCost;
    }

    @Override
    public boolean matches(IInventory inv, World world) {
        List<ItemStack> inputs = new ArrayList<>();
        int i = 0;

        for(int j = 0; j < 6; ++j) {
            ItemStack itemstack = inv.getItem(j);
            if (!itemstack.isEmpty()) {
                ++i;
                inputs.add(itemstack);
            }
        }

        return i == this.ingredients.size() && RecipeMatcher.findMatches(inputs, this.ingredients) != null;

    }

    @Override
    public ItemStack assemble(IInventory inv) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int p_194133_1_, int p_194133_2_) {
        return true;
    }

    public float getExperience() {
        return this.experience;
    }

    public int getMutationTime() {
        return this.mutationTime;
    }

    public int getFluidCost() {
        return this.fluidCost;
    }

    @Override
    public ItemStack getResultItem() {
        return this.result;
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.INFUSER.get());
    }

    @Override
    public ResourceLocation getId() {
        return this.recipeId;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipes.TRANSMUTATION.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return ModRecipes.TRANSMUTING;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<TransmutationRecipe> {

        @Override
        public TransmutationRecipe fromJson(ResourceLocation recipeID, JsonObject json) {

            NonNullList<Ingredient> ingredients = itemsFromJson(JSONUtils.getAsJsonArray(json, "ingredients"));
            ItemStack result = CraftingHelper.getItemStack(JSONUtils.getAsJsonObject(json, "result"), true);
            float experience = JSONUtils.getAsFloat(json, "experience", 0.0F);
            int mutationTime = JSONUtils.getAsInt(json, "mutationTime", 100);
            int fluidCost = JSONUtils.getAsInt(json, "fluidCost", 500);

            return new TransmutationRecipe(recipeID, ingredients, result, experience, mutationTime, fluidCost);
        }

        private static NonNullList<Ingredient> itemsFromJson(JsonArray jsonArray) {
            NonNullList<Ingredient> nonnulllist = NonNullList.create();

            for(int i = 0; i < jsonArray.size(); ++i) {
                Ingredient ingredient = Ingredient.fromJson(jsonArray.get(i));
                if (!ingredient.isEmpty()) {
                    nonnulllist.add(ingredient);
                }
            }

            return nonnulllist;
        }

        @Nullable
        @Override
        public TransmutationRecipe fromNetwork(ResourceLocation recipeID, PacketBuffer buffer) {
            int numIngredients = buffer.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(numIngredients, Ingredient.EMPTY);

            for (int j = 0; j < ingredients.size(); ++j) {
                ingredients.set(j, Ingredient.fromNetwork(buffer));
            }

            ItemStack result = buffer.readItem();
            float experience = buffer.readFloat();
            int mutationTime = buffer.readVarInt();
            int fluidCost = buffer.readVarInt();

            return new TransmutationRecipe(recipeID, ingredients, result, experience, mutationTime, fluidCost);
        }

        @Override
        public void toNetwork(PacketBuffer buffer, TransmutationRecipe recipe) {
            buffer.writeVarInt(recipe.getIngredients().size());

            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(buffer);
            }

            buffer.writeItem(recipe.getResultItem());
            buffer.writeFloat(recipe.experience);
            buffer.writeInt(recipe.mutationTime);
            buffer.writeInt(recipe.fluidCost);
        }
    }

}
