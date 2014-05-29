package com.rubensworks.custommealery;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
        if(config.getRecipeLines() != null) {
            addRecipe(item, config);
        }
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
    
    private static void addRecipe(Item item, MealConfig config) {
        // First valid character for recipes.
        char parameterCounter = 65;
        
        // The recipe lines. These contain the three recipe box lines and the KV mapping
        // of character key as used in the recipe lines and the item ID.
        List<Object> lines = Lists.newLinkedList();
        Map<Integer, Character> parameters = Maps.newHashMap(); // ID to char
        
        if(config.getRecipeLines().length != 3) {
            throw new RuntimeException("The config for " + config.getName() + " has an invalid recipe structure.");
        }
        
        // Add the three recipe box lines.
        for(String line : config.getRecipeLines()) {
            String parameterLine = "";
            String[] elements = line.split(",");
            for(String element : elements) {
                int id = Integer.parseInt(element);
                if(!parameters.containsKey(id)) {
                    parameters.put(id, parameterCounter++);
                }
                char parameter = parameters.get(id);
                parameterLine += parameter;
            }
            lines.add(parameterLine);
        }
        
        // Add the pairs of character key and item id.
        for(Entry<Integer, Character> entry : parameters.entrySet()) {
            lines.add(entry.getValue());
            lines.add(new ItemStack(Item.itemsList[entry.getKey()]));
        }
        
        // Register with the recipe lines we just constructed.
        GameRegistry.addRecipe(new ShapedOreRecipe(item, true, lines.toArray()));
    }
    
}
