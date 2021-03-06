package com.rubensworks.custommealery;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

/**
 * Simple logger.
 * @author rubensworks
 *
 */
public class LoggerHelper
{
    private static Logger logger = Logger.getLogger(Reference.MOD_NAME);

    /**
     * Initialize the logger.
     */
    public static void init() {
        logger.setParent(FMLLog.getLogger());
    }
    
    /**
     * Log a new message.
     * @param logLevel The level to log at.
     * @param message The message to log.
     */
    public static void log(Level logLevel, String message) {
        logger.log(logLevel, message);
    }

    /**
     * Create a new logger.
     * @param s The id of the logger.
     * @return The logger.
     */
    public static Logger createLogger(String s) {
        Logger logger = Logger.getLogger(s);
        logger.setParent(FMLLog.getLogger());
        return logger;
    }
}
