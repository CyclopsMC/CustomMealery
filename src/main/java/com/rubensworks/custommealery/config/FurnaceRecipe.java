package com.rubensworks.custommealery.config;

import com.google.gson.annotations.SerializedName;

/**
 * Holder class for furnace recipes.
 * @author rubensworks
 *
 */
public class FurnaceRecipe {
    
    private static final int DEFAULT_META = -1;

    @SerializedName("recipeResultAmount")
    private int recipeResultAmount = 1;
    
    @SerializedName("inputid")
    private int inputID;
    
    @SerializedName("inputmeta")
    private int inputMeta = DEFAULT_META;
    
    @SerializedName("experience")
    private int experience = 0;

    /**
     * @return the inputID
     */
    public int getInputID() {
        return inputID;
    }

    /**
     * @return the inputMeta
     */
    public int getInputMeta() {
        return inputMeta;
    }

    /**
     * @return the experience
     */
    public int getExperience() {
        return experience;
    }
    
    /**
     * If the input item has a non-default meta value.
     * @return If it has a custom meta value.
     */
    public boolean hasCustomMeta() {
        return getInputMeta() != DEFAULT_META;
    }

    /**
     * @return the defaultMeta
     */
    public static int getDefaultMeta() {
        return DEFAULT_META;
    }

    /**
     * @return the recipeResultAmount
     */
    public int getRecipeResultAmount() {
        return recipeResultAmount;
    }
    
}
