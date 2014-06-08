package com.rubensworks.custommealery.config;

import com.google.gson.annotations.SerializedName;

/**
 * Holder class for crafting recipes.
 * @author rubensworks
 *
 */
public class CraftingRecipe {

    @SerializedName("recipeResultAmount")
    private int recipeResultAmount = 1;
    
    @SerializedName("recipe")
    private String[] recipeLines = null;

    /**
     * @return the recipeResultAmount
     */
    public int getRecipeResultAmount() {
        return recipeResultAmount;
    }

    /**
     * @return the recipeLines
     */
    public String[] getRecipeLines() {
        return recipeLines;
    }
    
}
