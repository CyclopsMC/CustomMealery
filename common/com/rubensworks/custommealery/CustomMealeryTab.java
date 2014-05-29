package com.rubensworks.custommealery;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * Creative tab for EvilCraft.
 * @author rubensworks
 *
 */
public class CustomMealeryTab extends CreativeTabs{
    
    private static CustomMealeryTab _instance = null;
    
    /**
     * Get the unique instance.
     * @return The unique instance.
     */
    public static CustomMealeryTab getInstance() {
        if(_instance == null) {
            _instance = new CustomMealeryTab();
        }
        return _instance;
    }

    private CustomMealeryTab() {
        super(Reference.MOD_NAME);
        LanguageRegistry.instance().addStringLocalization("itemGroup." + Reference.MOD_NAME, "en_US", Reference.MOD_NAME);
    }
    
    @Override
    public int getTabIconItemIndex() {
        return Item.cookie.itemID;
    }
    
}
