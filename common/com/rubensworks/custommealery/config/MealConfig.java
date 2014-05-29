package com.rubensworks.custommealery.config;

import com.google.gson.annotations.SerializedName;

/**
 * Config file for a meal.
 * @author rubensworks
 *
 */
public class MealConfig {

    @SerializedName("id")
    private int id;
    
    @SerializedName("nameid")
    private String nameId;
    
    @SerializedName("name")
    private String name;
    
    private int itemUseDuration = 32;
    
    private int healAmount = 2;
    
    private float saturationModifier = 1.0F;
    
    private boolean isWolfsFavoriteMeat = true;
    
    private String rarity = "epic";
    
    private boolean hasEffect = true;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the nameid
     */
    public String getNameId() {
        return nameId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the rarity
     */
    public String getRarity() {
        return rarity;
    }

    /**
     * @return the hasEffect
     */
    public boolean isHasEffect() {
        return hasEffect;
    }

    /**
     * @return the itemUseDuration
     */
    public int getItemUseDuration() {
        return itemUseDuration;
    }

    /**
     * @return the healAmount
     */
    public int getHealAmount() {
        return healAmount;
    }

    /**
     * @return the saturationModifier
     */
    public float getSaturationModifier() {
        return saturationModifier;
    }

    /**
     * @return the isWolfsFavoriteMeat
     */
    public boolean isWolfsFavoriteMeat() {
        return isWolfsFavoriteMeat;
    }
    
}
