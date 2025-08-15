package net.pufferlab.antiquities.client.models;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.pufferlab.antiquities.Antiquities;

public enum Models {

    CHAIR("chair.png"),
    TABLE("table.png"),

    ;

    public static final String ASSET_PATH = "textures/models/";
    private ResourceLocation location;

    private Models(String filename) {
        location = Antiquities.asResource(ASSET_PATH + filename);
    }

    public void bind() {
        Minecraft.getMinecraft().renderEngine.bindTexture(location);
    }

    public ResourceLocation getLocation() {
        return location;
    }

}
