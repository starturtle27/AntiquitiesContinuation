package net.pufferlab.antiquities.client.models;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.pufferlab.antiquities.Antiquities;
import net.pufferlab.antiquities.Constants;
import net.pufferlab.antiquities.Utils;
import net.pufferlab.antiquities.client.tessrender.ModelTESS;

public class ModelPile extends ModelBase {

    private ModelRenderer bb_main;
    private ModelRenderer layer0;
    private ModelRenderer layer1;

    public ModelPile() {
        textureWidth = 32;
        textureHeight = 32;

        bb_main = new ModelRenderer(this);
        bb_main.setRotationPoint(0.0F, 0.0F, 0.0F);

        layer0 = new ModelRenderer(this);
        layer0.setRotationPoint(0.0F, 0.0F, 0.0F);
        bb_main.addChild(layer0);

        layer1 = new ModelRenderer(this);
        layer1.setRotationPoint(0.0F, 0.0F, 0.0F);
        bb_main.addChild(layer1);
    }

    public void renderIngot(int i, String metal) {
        ModelRenderer layer;
        float posX = -6.5F;
        float posY = -2.0F + 8F;
        float posZ = 4.5F;
        int i2 = i % 4;
        int i3 = i % 8;
        int i5 = (int) (double) (i / 8);
        int texY = 5 * Utils.getItemFromArray(Constants.metalTypes, metal);
        float angle = 90;
        if (i5 % 2 == 0) {
            layer = layer1;
            layer.rotateAngleY = ((bb_main.rotateAngleY + (float) Math.toRadians(angle)));
        } else {
            layer = layer0;
            layer.rotateAngleY = ((bb_main.rotateAngleY));
        }
        posY = posY - (2 * i5);
        if (i3 > 3) {
            posX = posX + 7F;
        }
        posZ = posZ - (4 * i2);
        layer.cubeList.add(new ModelBox(bb_main, 0, texY, posX, posY, posZ, 6, 2, 3, 0.0F));
    }

    public void render(String type) {
        bindTex(type + "_pile");
        bb_main.rotateAngleX = (float) Math.toRadians(180);
        bb_main.render(Constants.ModelConstant);
    }

    public void render(RenderBlocks renderblocks, Tessellator tess, Block block, int meta, int x, int y, int z) {
        bb_main.rotateAngleX = (float) Math.toRadians(180);
        ModelTESS.render(renderblocks, tess, block, bb_main, Constants.ModelConstant, x, y, z, meta);
    }

    public void bindTex(String fileName) {
        Minecraft.getMinecraft().renderEngine
            .bindTexture(Antiquities.asResource("textures/blocks/" + fileName + ".png"));
    }

    public void resetFacing() {
        layer0.rotateAngleY = 0;
        layer1.rotateAngleY = 0;
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
