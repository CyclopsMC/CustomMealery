package com.rubensworks.custommealery;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rubensworks.custommealery.config.CraftingRecipe;
import com.rubensworks.custommealery.config.FurnaceRecipe;
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
    
    /**
     * Register the crafting and furnace recipes for the registered meals.
     * Should only be called once after ALL meals have been registered at
     * {@link MealRegistry#register(MealConfig)} or {@link MealRegistry#registerAll(List)}.
     */
    public static void registerAllRecipes() {
        for(Meal meal : REGISTERED_MEALS) {
            MealConfig config = meal.getConfig();
            if(config.hasCraftingRecipe()) {
                addCraftingRecipe(meal, config);
            }
            if(config.hasFurnaceRecipe()) {
                addFurnaceRecipe(meal, config);
            }
        }
    }
    
    private static void addCraftingRecipe(Item item, MealConfig config) {
        CraftingRecipe recipe = config.getCraftingRecipe();
        
        // First valid character for recipes.
        char parameterCounter = 65;
        
        // The recipe lines. These contain the three recipe box lines and the KV mapping
        // of character key as used in the recipe lines and the item ID.
        List<Object> lines = Lists.newLinkedList();
        Map<String, Character> parameters = Maps.newHashMap(); // key to char
        
        if(recipe.getRecipeLines().length != 3) {
            CustomMealery.log("The config for " + config.getName()
                    + " has an invalid recipe structure, skipping.", Level.SEVERE);
            return;
        }
        
        // Add the three recipe box lines.
        for(String line : recipe.getRecipeLines()) {
            String parameterLine = "";
            String[] elements = line.split(",");
            for(String element : elements) {
                if(" ".equals(element)) {
                    parameterLine += " ";
                } else {
                    if(!parameters.containsKey(element)) {
                        parameters.put(element, parameterCounter++);
                    }
                    char parameter = parameters.get(element);
                    parameterLine += parameter;
                }
            }
            lines.add(parameterLine);
        }
        
        // Add the pairs of character key and item id.
        for(Entry<String, Character> entry : parameters.entrySet()) {
            lines.add(entry.getValue());
            Object line = makeItemStack(entry.getKey());
            if(line == null) {
                CustomMealery.log("The recipe for " + config.getName()
                        + " has an invalid structure, skipping.", Level.SEVERE);
                return;
            }
            lines.add(line);
        }
        
        // Register with the recipe lines we just constructed.
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(item, recipe.getRecipeResultAmount()), true, lines.toArray()));
    }

    private static Object makeItemStack(String key) {
        if(key.contains(":")) {
            // There is a meta value
            String[] elements = key.split(":");
            try {
                int id = Integer.parseInt(elements[0]);
                int meta = Integer.parseInt(elements[1]);
                return new ItemStack(Item.itemsList[id], 1, meta);
            } catch (NumberFormatException e) {
                return null;
            }
        } else {
            try {
                // There is just an id
                int id = Integer.parseInt(key);
                return new ItemStack(Item.itemsList[id]);
            } catch (NumberFormatException e) {
                // Assume an ore dict key
                return key;
            }
        }
    }
    
    private static void addFurnaceRecipe(Item item, MealConfig config) {
        FurnaceRecipe recipe = config.getFurnaceRecipe();
        
        int inputID = recipe.getInputID();
        ItemStack output = new ItemStack(item, recipe.getRecipeResultAmount());
        int xp = recipe.getExperience();
        
        if(recipe.hasCustomMeta()) {
            int inputMeta = recipe.getInputMeta();
            FurnaceRecipes.smelting().addSmelting(inputID, inputMeta, output, xp);
        } else {
            FurnaceRecipes.smelting().addSmelting(inputID, output, xp);
        }
    }
    
}
