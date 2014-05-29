package com.rubensworks.custommealery.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.Resource;
import net.minecraft.client.resources.ResourceManager;
import net.minecraft.client.resources.SimpleResource;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.client.resources.data.AnimationMetadataSectionSerializer;
import net.minecraft.client.resources.data.FontMetadataSection;
import net.minecraft.client.resources.data.FontMetadataSectionSerializer;
import net.minecraft.client.resources.data.MetadataSerializer;
import net.minecraft.client.resources.data.TextureMetadataSection;
import net.minecraft.client.resources.data.TextureMetadataSectionSerializer;
import net.minecraft.util.ResourceLocation;

/**
 * An extension of a normal {@link TextureAtlasSprite} so that it is able to load
 * external images.
 * It will ignore the original resource location and use the custom defined iconPath instead.
 * @author rubensworks
 *
 */
public class ExternalTextureAtlasSprite extends TextureAtlasSprite {
    
    private static final String META_SUFFIX = ".mcmeta";
    
    private String iconPath;
    private MetadataSerializer metadataSerializer;

    /**
     * Make a new instance.
     * @param innerSprite The inner sprite.
     * @param iconPath The real path to the icon.
     */
    public ExternalTextureAtlasSprite(TextureAtlasSprite innerSprite, String iconPath) {
        this(innerSprite.getIconName());
        copyFrom(innerSprite);
        this.iconPath = iconPath;
        
        // Some of the metadata section types as defined in Minecraft.java
        this.metadataSerializer = new MetadataSerializer();
        this.metadataSerializer.registerMetadataSectionType(new TextureMetadataSectionSerializer(), TextureMetadataSection.class);
        this.metadataSerializer.registerMetadataSectionType(new FontMetadataSectionSerializer(), FontMetadataSection.class);
        this.metadataSerializer.registerMetadataSectionType(new AnimationMetadataSectionSerializer(), AnimationMetadataSection.class);
    }
    
    protected ExternalTextureAtlasSprite(String iconName) {
        super(iconName);
    }
    
    @Override
    public boolean load(ResourceManager manager, ResourceLocation location) throws IOException {
        Resource resource = new SimpleResource(location, pathToFileInputStream(iconPath),
                pathToFileInputStream(iconPath + META_SUFFIX), metadataSerializer);
        super.loadSprite(resource);
        return true;
    }
    
    private static final FileInputStream pathToFileInputStream(String path) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(path));
        } catch (FileNotFoundException e) {
            
        }
        return fis;
    }

}
