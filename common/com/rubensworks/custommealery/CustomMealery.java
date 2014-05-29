package com.rubensworks.custommealery;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import net.minecraftforge.common.MinecraftForge;

import com.rubensworks.custommealery.config.ConfigLoader;
import com.rubensworks.custommealery.config.MealConfig;
import com.rubensworks.custommealery.proxy.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

/**
 * The main mod class of Custom Mealery.
 * @author rubensworks
 *
 */
@Mod(modid = Reference.MOD_ID,
    name = Reference.MOD_NAME,
    useMetadata = true,
    version = Reference.MOD_VERSION
    )
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class CustomMealery {
    
    /**
     * The proxy of this mod, depending on 'side' a different proxy will be inside this field.
     * @see cpw.mods.fml.common.SidedProxy
     */
    @SidedProxy(clientSide = Reference.GROUP_NAME + ".proxy.ClientProxy",
            serverSide = Reference.GROUP_NAME + ".proxy.CommonProxy")
    public static CommonProxy proxy;
    
    /**
     * The unique instance of this mod, will only be available after @see EvilCraft#preInit()
     */
    public static CustomMealery _instance;
    
    /**
     * The pre-initialization, will register required configs.
     * @param event The Forge event required for this.
     */
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LoggerHelper.init();
        LoggerHelper.log(Level.INFO, "preInit()");
        
        // Save this instance, so we can use it later
        CustomMealery._instance = this;
        
        // Init the config directory.
        File rootFolder = null;
        try {
            String rootFolderName = event.getModConfigurationDirectory()
                    + "/" + Reference.MOD_ID;
            rootFolder = ConfigLoader.getInstance().init(rootFolderName);
            List<MealConfig> configs = ConfigLoader.getInstance().findMeals(rootFolder);
            MealRegistry.registerAll(configs);
        } catch (IOException e) {
            log("Something went wrong while trying to initialize config folder and template, "
                    + "no meals were registered.", Level.SEVERE);
            System.err.println(e);
        }        
    }
    
    /**
     * Register the config dependent things like world generation and proxy handlers.
     * @param event The Forge event required for this.
     */
    @EventHandler
    public void init(FMLInitializationEvent event) {
        LoggerHelper.log(Level.INFO, "init()");
    }
    
    /**
     * Register the event hooks.
     * @param event The Forge event required for this.
     */
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        LoggerHelper.log(Level.INFO, "postInit()");
        
        MinecraftForge.EVENT_BUS.register(proxy);
    }
    
    /**
     * Log a new info message for this mod.
     * @param message The message to show.
     */
    public static void log(String message) {
        log(message, Level.INFO);
    }
    
    /**
     * Log a new message of the given level for this mod.
     * @param message The message to show.
     * @param level The level in which the message must be shown.
     */
    public static void log(String message, Level level) {
        LoggerHelper.log(level, message);
    }

}
