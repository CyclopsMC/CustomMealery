package com.rubensworks.custommealery.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.rubensworks.custommealery.CustomMealery;

/**
 * Loads the config files.
 * @author rubensworks
 *
 */
public class ConfigLoader {
    
    private static final String TEMPLATE_NAME = "_template.json";
    private static final Pattern CONFIG_PATTERN = Pattern.compile("^[^_].*\\.json");

    private static ConfigLoader _instance = null;
    
    private ConfigLoader() {
        
    }
    
    /**
     * Get the unique instance.
     * @return The instance.
     */
    public static ConfigLoader getInstance() {
        if(_instance == null) {
            _instance = new ConfigLoader();
        }
        return _instance;
    }
    
    /**
     * Initializes the config folder and config template if necessary.
     * @param configDirectory The directory to initialize in.
     * @return The initialized root folder.
     * @throws IOException If something went wrong in file creations.
     */
    public File init(String configDirectory) throws IOException {
        // Make directory if not exists.
        File rootFolder = new File(configDirectory);
        if(!rootFolder.exists()) {
            rootFolder.mkdir();
        }
        
        // Make template if not exists.
        File template = new File(configDirectory, TEMPLATE_NAME);
        if(!template.exists()) {
            template.createNewFile();
            // TODO: place some info in this.
        }
        
        return rootFolder;
    }
    
    /**
     * Loop through the config folder to find all the meal configs.
     * Note that this will ignore all files with an underscore as prefix and
     * files without a ".json" prefix.
     * @param rootFolder The root folder that will contain all the meal config files.
     * @return The list of meal configs.
     */
    public List<MealConfig> findMeals(File rootFolder) {
        List<MealConfig> configs = Lists.newLinkedList();
        
        for(File file : rootFolder.listFiles()) {
            if(file.isFile() && CONFIG_PATTERN.matcher(file.getName()).matches()) {
                try {
                    configs.add(loadMeal(file));
                    CustomMealery.log("Loaded config " + file.getName() + ".", Level.FINE);
                } catch (JsonSyntaxException e) {
                    CustomMealery.log("The config " + file.getName() + " has an invalid syntax.", Level.SEVERE);
                } catch (JsonIOException e) {
                    CustomMealery.log("Something went wrong while reading " + file.getName() + ".", Level.SEVERE);
                }
            } else if(file.isDirectory()) {
                configs.addAll(findMeals(file));
            } else {
                CustomMealery.log("Skipped config " + file.getName() + ".", Level.FINE);
            }
        }
        
        return configs;
    }
    
    /**
     * Load in a file that contains meal details and save it to a {@link MealConfig}.
     * @param file The file that contains JSON details about the meal.
     * @return The config file derived from the file contents.
     * @throws JsonIOException Something went wrong while IO-ing the JSON.
     * @throws JsonSyntaxException The JSON had a syntax error.
     */
    public MealConfig loadMeal(File file) throws JsonSyntaxException, JsonIOException {
        Gson gson = new Gson();
        try {
            return gson.fromJson(new BufferedReader(new FileReader(file)), MealConfig.class);
        } catch (FileNotFoundException e) {
            // Not possible.
            return null;
        }
    }
    
}
