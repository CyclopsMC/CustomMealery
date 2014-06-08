package com.rubensworks.custommealery.config;

import com.google.gson.annotations.SerializedName;

/**
 * Config class for potion effects in a meal.
 * @author rubensworks
 *
 */
public class PotionEffectConfig {

    @SerializedName("potionid")
    private int id;
    
    @SerializedName("probability")
    private float probability = 1.0F;
    
    @SerializedName("duration")
    private int duration;
    
    @SerializedName("amplifier")
    private int amplifier = 1;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the probability
     */
    public float getProbability() {
        return probability;
    }

    /**
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @return the amplifier
     */
    public int getAmplifier() {
        return amplifier;
    }
    
}
