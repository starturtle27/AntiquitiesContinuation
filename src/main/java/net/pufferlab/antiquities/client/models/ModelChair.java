package net.pufferlab.antiquities.client.models;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.pufferlab.antiquities.Antiquities;
import net.pufferlab.antiquities.Constants;

public class ModelChair extends ModelBase {

    private ModelRenderer bb_main;

    public ModelChair() {
        textureWidth = 64;
        textureHeight = 64;

        bb_main = new ModelRenderer(this);
        setFacing(0);
        bb_main.setRotationPoint(0.0F, 0.0F, 0.0F);
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 26, 4.0F, -8.0F + 8F, -6.0F, 2, 8, 2, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 8, 26, 4.0F, -8.0F + 8F, 4.0F, 2, 8, 2, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 14, -6.0F, -20.0F + 8F, -6.0F, 12, 10, 2, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -6.0F, -10.0F + 8F, -6.0F, 12, 2, 12, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 16, 26, -6.0F, -8.0F + 8F, 4.0F, 2, 8, 2, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 24, 26, -6.0F, -8.0F + 8F, -6.0F, 2, 8, 2, 0.0F));
    }

    public void render(String type) {
        bindTex(type + "_chair");
        bb_main.rotateAngleX = (float) Math.toRadians(180);
        bb_main.render(Constants.ModelConstant);
    }

    public void bindTex(String fileName) {
        Minecraft.getMinecraft().renderEngine
            .bindTexture(Antiquities.asResource("textures/models/" + fileName + ".png"));
    }

    public void setFacing(int meta) {
        int meta2 = meta + 1;
        bb_main.rotateAngleY = (float) ((Math.toRadians(90 * meta2) + Math.toRadians(180)) % 360);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

}
