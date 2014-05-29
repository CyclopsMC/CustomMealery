package com.rubensworks.custommealery.config;

import net.minecraft.item.EnumRarity;

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
    
    @SerializedName("eatTime")
    private int itemUseDuration = 32;
    
    @SerializedName("healAmount")
    private int healAmount = 2;
    
    @SerializedName("saturationModifier")
    private float saturationModifier = 1.0F;
    
    @SerializedName("isWolfsFavoriteMeat")
    private boolean isWolfsFavoriteMeat = false;
    
    @SerializedName("rarity")
    private EnumRarity rarity = EnumRarity.common;
    
    @SerializedName("hasEffect")
    private boolean hasEffect = false;

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
    public EnumRarity getRarity() {
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
