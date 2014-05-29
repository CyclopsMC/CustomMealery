package com.rubensworks.custommealery;

import java.util.List;

import com.google.common.collect.Lists;
import com.rubensworks.custommealery.config.MealConfig;
import com.rubensworks.custommealery.item.Meal;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * This is able to register all the meals.
 * @author rubensworks
 *
 */
public final class MealRegistry {
    
    /**
     * All the registered meals.
     */
    public static final List<Meal> REGISTERED_MEALS = Lists.newLinkedList();

    /**
     * Register a meal as item.
     * @param config The config of the meal.
     */
    public static void register(MealConfig config) {
        Meal item = new Meal(config);
        GameRegistry.registerItem(item, config.getNameId());
        item.setCreativeTab(CustomMealeryTab.getInstance());
        LanguageRegistry.addName(item, config.getName());
        REGISTERED_MEALS.add(item);
    }
    
    /**
     * Register all the meals as item.
     * @param configs The configs of the meals.
     */
    public static void registerAll(List<MealConfig> configs) {
        for(MealConfig config : configs) {
            register(config);
        }
    }
    
}
