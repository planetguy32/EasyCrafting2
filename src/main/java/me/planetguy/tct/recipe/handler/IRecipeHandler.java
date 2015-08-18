package me.planetguy.tct.recipe.handler;

import java.util.List;

import me.planetguy.tct.recipe.WrappedRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public interface IRecipeHandler {

    public List<Object> getInputs(IRecipe recipe);

    public boolean matchItem(ItemStack target, ItemStack candidate, WrappedRecipe recipe);

    public ItemStack getCraftingResult(WrappedRecipe recipe, List<ItemStack> usedIngredients);
    
}
