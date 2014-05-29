package com.rubensworks.custommealery.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.Resource;
import net.minecraft.client.resources.ResourceManager;
import net.minecraft.client.resources.data.MetadataSection;
import net.minecraft.util.ResourceLocation;

/**
 * An extension of a normal {@link TextureAtlasSprite} so that it is able to load
 * external images.
 * It will ignore the original resource location and use the custom defined iconPath instead.
 * @author rubensworks
 *
 */
public class ExternalTextureAtlasSprite extends TextureAtlasSprite {
    
    private String iconPath;

    /**
     * Make a new instance.
     * @param innerSprite The inner sprite.
     * @param iconPath The real path to the icon.
     */
    public ExternalTextureAtlasSprite(TextureAtlasSprite innerSprite, String iconPath) {
        this(innerSprite.getIconName());
        copyFrom(innerSprite);
        this.iconPath = iconPath;
    }
    
    protected ExternalTextureAtlasSprite(String iconName) {
        super(iconName);
    }
    
    @Override
    public void loadSprite(Resource resource) throws IOException {
        super.loadSprite(new Resource() {
            
            @Override
            public boolean hasMetadata() {
                return false;
            }
            
            @Override
            public MetadataSection getMetadata(String s) {
                return null;
            }
            
            @Override
            public InputStream getInputStream() {
                try {
                    return new FileInputStream(new File(iconPath));
                } catch (FileNotFoundException e) {
                    return null;
                }
            }
        });
    }
    
    @Override
    public boolean load(ResourceManager manager, ResourceLocation location) throws IOException {
        loadSprite(null);
        return true;
    }

}
