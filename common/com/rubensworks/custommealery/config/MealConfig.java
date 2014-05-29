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
    
    private String configLocation;
    
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
    
    @SerializedName("potionEffects")
    private List<PotionEffectConfig> potionEffects = Lists.newLinkedList();

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
    
}
