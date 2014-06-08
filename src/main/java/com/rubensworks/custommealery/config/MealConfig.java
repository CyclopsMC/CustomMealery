package com.rubensworks.custommealery.config;

import java.util.List;

import net.minecraft.item.EnumRarity;

import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;
import com.rubensworks.custommealery.Color;

/**
 * Config file for a meal.
 * @author rubensworks
 *
 */
public class MealConfig {
    
    private static final String EXTENSION = ".png";
    private static final int DEFAULT_DAMAGE_VALUE = -1;

    @SerializedName("id")
    private int id;
    
    @SerializedName("nameid")
    private String nameId;
    
    @SerializedName("name")
    private String name;
    
    @SerializedName("texture")
    private String texture = null;
    
    @SerializedName("colorOverlay")
    private Color color = null;
    
    private String configLocation;
    
    @SerializedName("eatTime")
    private int itemUseDuration = 32;
    
    @SerializedName("healAmount")
    private int healAmount = 2;
    
    @SerializedName("saturationModifier")
    private float saturationModifier = 0.5F;
    
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
    
    @SerializedName("damageValue")
    private int damageValue = DEFAULT_DAMAGE_VALUE;
    
    @SerializedName("potionEffects")
    private List<PotionEffectConfig> potionEffects = Lists.newLinkedList();
    
    @SerializedName("craftingRecipe")
    private CraftingRecipe craftingRecipe = null;
    
    @SerializedName("furnaceRecipe")
    private FurnaceRecipe furnaceRecipe = null;

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
     * @return the craftingRecipe
     */
    public CraftingRecipe getCraftingRecipe() {
        return craftingRecipe;
    }
    
    /**
     * If this meal has a crafting recipe.
     * @return If it has one.
     */
    public boolean hasCraftingRecipe() {
        return getCraftingRecipe() != null;
    }
    
    /**
     * @return the furnaceRecipe
     */
    public FurnaceRecipe getFurnaceRecipe() {
        return furnaceRecipe;
    }
    
    /**
     * If this meal has a furnace recipe.
     * @return If it has one.
     */
    public boolean hasFurnaceRecipe() {
        return getFurnaceRecipe() != null;
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
     * @return the color
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * If this meal config should have a custom color overlay for the icon.
     * @return If it has a texture color overlay.
     */
    public boolean hasColorOverlay() {
        return getColor() != null;
    }

    /**
     * @return the damageValue
     */
    public int getDamageValue() {
        return damageValue;
    }
    
    /**
     * If this meal should be based on damage values so that it can be eaten multiple times.
     * @return If it has a damage value.
     */
    public boolean hasDamageValue() {
        return getDamageValue() != DEFAULT_DAMAGE_VALUE;
    }
    
}
