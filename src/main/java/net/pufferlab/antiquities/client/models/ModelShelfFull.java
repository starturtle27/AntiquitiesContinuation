package net.pufferlab.antiquities.client.models;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.pufferlab.antiquities.Antiquities;
import net.pufferlab.antiquities.Constants;

public class ModelShelfFull extends ModelBase {

    private final ModelRenderer bb_main;

    public ModelShelfFull() {
        textureWidth = 64;
        textureHeight = 64;

        bb_main = new ModelRenderer(this);
        bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
        setFacing(0);
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -8.0F, -16.0F + 32F, 6.0F, 16, 16, 2, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 1, 18, 7.0F, -16.0F + 32F, 0.0F, 1, 16, 6, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 1, 18, -8.0F, -16.0F + 32F, 0.0F, 1, 16, 6, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 36, 0, 0.0F, -9.0F + 32F, 0.0F, 7, 2, 6, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 36, 0, -7.0F, -9.0F + 32F, 0.0F, 7, 2, 6, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 36, 8, 0.0F, -16.0F + 32F, 0.0F, 7, 1, 6, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 36, 8, -7.0F, -16.0F + 32F, 0.0F, 7, 1, 6, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 36, 0, -7.0F, -1.0F + 32F, 0.0F, 7, 1, 6, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 36, 0, 0.0F, -1.0F + 32F, 0.0F, 7, 1, 6, 0.0F));
    }

    public void render(String type) {
        bindTex(type + "_shelf");
        bb_main.rotateAngleX = (float) Math.toRadians(180);
        bb_main.render(Constants.ModelConstant);
    }

    public void bindTex(String fileName) {
        Minecraft.getMinecraft().renderEngine
            .bindTexture(Antiquities.asResource("textures/models/" + fileName + ".png"));
    }

    public void setFacing(int meta) {
        int meta2 = meta + 1;
        bb_main.rotateAngleY = (float) ((Math.toRadians(90 * meta2)) % 360);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
