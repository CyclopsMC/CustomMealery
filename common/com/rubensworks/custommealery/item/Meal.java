package com.rubensworks.custommealery.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.rubensworks.custommealery.Reference;
import com.rubensworks.custommealery.config.MealConfig;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * General class for all the possible meals.
 * @author rubensworks
 *
 */
public class Meal extends ItemFood {
    
    private MealConfig config;

    protected Meal(MealConfig config, int id, int healAmount, float saturationModifier, boolean isWolfsFavoriteMeat) {
        super(id, healAmount, saturationModifier, isWolfsFavoriteMeat);
        this.config = config;
        this.setUnlocalizedName(this.getUniqueName());
    }
    
    /**
     * Make a new meal.
     * @param config The config file defining this meal.
     */
    public Meal(MealConfig config) {
        this(config, config.getId(), config.getHealAmount(),
                config.getSaturationModifier(), config.isWolfsFavoriteMeat());
    }
    
    @Override
    public int getMaxItemUseDuration(ItemStack itemStack) {
        return config.getItemUseDuration();
    }
    
    /**
     * Get the unique name for this item.
     * @return The unique name.
     */
    public String getUniqueName() {
        return "items." + config.getNameId();
    }
    
    @Override
    public String getIconString() {
        return Reference.MOD_ID + ":" + config.getNameId();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon(getIconString());
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack itemStack){
        return config.getRarity();
    }
    
    @Override
    public boolean hasEffect(ItemStack itemStack){
        return config.isHasEffect();
    }
    
    @Override
    public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player) {
        --itemStack.stackSize;
        this.onFoodEaten(itemStack, world, player);
        return itemStack;
    }
    
}
