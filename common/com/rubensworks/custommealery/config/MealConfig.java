package com.rubensworks.custommealery.config;

import java.util.List;

import net.minecraft.item.EnumRarity;

import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;

/**
 * Config file for a meal.
 * @author rubensworks
 *
 */
public class MealConfig {
    
    private static final String EXTENSION = ".png";

    @SerializedName("id")
    private int id;
    
    @SerializedName("nameid")
    private String nameId;
    
    @SerializedName("name")
    private String name;
    
    @SerializedName("texture")
    private String texture = null;
    
    private String configLocation;
    
    @SerializedName("eatTime")
    private int itemUseDuration = 32;
    
    @SerializedName("healAmount")
    private int healAmount = 2;
    
    @SerializedName("saturationModifier")
    private float saturationModifier = 1.0F;
    
    @SerializedName("isAlwaysEdible")
    private boolean isAlwaysEdible = false;
    
    @SerializedName("isWolfsFavoriteMeat")
    private boolean isWolfsFavoriteMeat = false;
    
    @SerializedName("rarity")
    private EnumRarity rarity = EnumRarity.common;
    
    @SerializedName("hasEffect")
    private boolean hasEffect = false;
    
    @SerializedName("maxStackSize")
    private int maxStackSize = 64;
    
    @SerializedName("potionEffects")
    private List<PotionEffectConfig> potionEffects = Lists.newLinkedList();
    
    @SerializedName("recipeResultAmount")
    private int recipeResultAmount = 1;
    
    @SerializedName("recipe")
    private String[] recipeLines = null;

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

    /**
     * @return the iconPath
     */
    public String getIconPath() {
        return getConfigLocation() + "/" + getNameId() + EXTENSION;
    }

    /**
     * @return the configLocation
     */
    public String getConfigLocation() {
        return configLocation;
    }

    /**
     * @param configLocation the configLocation to set
     */
    public void setConfigLocation(String configLocation) {
        this.configLocation = configLocation;
    }

    /**
     * @return the potionEffects
     */
    public List<PotionEffectConfig> getPotionEffects() {
        return potionEffects;
    }

    /**
     * @return the isAlwaysEdible
     */
    public boolean isAlwaysEdible() {
        return isAlwaysEdible;
    }

    /**
     * @return the maxStackSize
     */
    public int getMaxStackSize() {
        return maxStackSize;
    }

    /**
     * @return the recipeLines
     */
    public String[] getRecipeLines() {
        return recipeLines;
    }

    /**
     * @return the texture
     */
    public String getTexture() {
        return texture;
    }
    
    /**
     * If this meal has a texture in a resource pack instead of in the config directory.
     * @return If it has one.
     */
    public boolean hasResourceTexture() {
        return getTexture() != null;
    }

    /**
     * @return the recipeResultAmount
     */
    public int getRecipeResultAmount() {
        return recipeResultAmount;
    }
    
}
