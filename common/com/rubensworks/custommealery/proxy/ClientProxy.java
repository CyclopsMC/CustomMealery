package com.rubensworks.custommealery.proxy;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.ForgeSubscribe;

import com.rubensworks.custommealery.MealRegistry;
import com.rubensworks.custommealery.client.ExternalTextureAtlasSprite;
import com.rubensworks.custommealery.item.Meal;


/**
 * Proxy for the client side.
 * @author rubensworks
 *
 */
public class ClientProxy extends CommonProxy{
    
    private static boolean ISOBFUSICATED_CHECKED = false;
    private static boolean ISOBFUSICATED;
    
    private static final int ITEM_TEXTURE_TYPE = 1;
    
    /**
     * Check if Minecraft is currently running in an obfusicated environment.
     * @return If we run obfusicated.
     */
    public static boolean isObfusicated() {
        if(!ISOBFUSICATED_CHECKED) {
            ISOBFUSICATED_CHECKED = true;
            ISOBFUSICATED = false;
            try {
                Minecraft.getMinecraft().getClass().getField("currentScreen");
            } catch (NoSuchFieldException e) {
                ISOBFUSICATED = true;
            } catch (SecurityException e) {}
        }
        return ISOBFUSICATED;
    }
    
    private static final String getMapRegisteredSpritesFieldName() {
        if(isObfusicated()) {
            return "field_110574_e";
        } else {
            return "mapRegisteredSprites";
        }
    }
    
    @SuppressWarnings("unchecked")
    private Map<String, TextureAtlasSprite> getRegisteredSpritesMap(TextureMap textureMap) {
        try {
            Field registeredSpritesMapField = TextureMap.class.getDeclaredField(getMapRegisteredSpritesFieldName());

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(registeredSpritesMapField, registeredSpritesMapField.getModifiers() & ~Modifier.FINAL);

            registeredSpritesMapField.setAccessible(true);
            return (Map<String, TextureAtlasSprite>) registeredSpritesMapField.get(textureMap);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Register the correct textures for the meals.
     * @param event The pre-{@link TextureStitchEvent}.
     * @throws IOException If an error occured.
     */
    @ForgeSubscribe
    public void textureHook(TextureStitchEvent.Pre event) throws IOException {
        if(event.map.textureType == ITEM_TEXTURE_TYPE) {
            for(Meal meal : MealRegistry.REGISTERED_MEALS) {
                // Only load icons from the meals that don't already have a
                // texture from a texture pack.
                if(!meal.getConfig().hasResourceTexture()) {
                    TextureAtlasSprite oldSprite = event.map.getTextureExtry(meal.getIconString());
                    Map<String, TextureAtlasSprite> spritesMap = getRegisteredSpritesMap(event.map);
                    ExternalTextureAtlasSprite newSprite = new ExternalTextureAtlasSprite(oldSprite,
                            meal.getConfig().getIconPath());
                    spritesMap.put(meal.getIconString(), newSprite);
                    meal.setIcon(newSprite);
                }
            }
        }
    }
    
}
