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
import net.pufferlab.antiquities.client.helper.ModelTESS;

public class ModelPedestal extends ModelBase {

    private final ModelRenderer bb_main;

    public ModelPedestal() {
        textureWidth = 32;
        textureHeight = 32;

        bb_main = new ModelRenderer(this);
        bb_main.setRotationPoint(0.0F, 0.0F, 0.0F);
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -4.0F, -3.0F + 8F, -2.0F, 8, 3, 4, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 7, 4.0F, -2.0F + 8F, -2.0F, 1, 2, 4, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 7, -5.0F, -2.0F + 8F, -2.0F, 1, 2, 4, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 7, 5.0F, -1.0F + 8F, -2.0F, 1, 1, 4, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 7, -6.0F, -1.0F + 8F, -2.0F, 1, 1, 4, 0.0F));
    }

    public void render(String type) {
        bindTex(type + "_pedestal");
        bb_main.rotateAngleX = (float) Math.toRadians(180);
        bb_main.render(Constants.ModelConstant);
    }

    public void render(RenderBlocks renderblocks, Tessellator tess, Block block, int meta, int x, int y, int z) {
        bb_main.rotateAngleX = (float) Math.toRadians(180);
        ModelTESS.renderBlock(renderblocks, tess, block, bb_main, Constants.ModelConstant, x, y, z, meta);
    }

    public void setFacing(int meta) {
        int meta2 = meta + 1;
        bb_main.rotateAngleY = (float) ((Math.toRadians(90 * meta2)) % 360);
    }

    public void bindTex(String fileName) {
        Minecraft.getMinecraft().renderEngine
            .bindTexture(Antiquities.asResource("textures/blocks/" + fileName + ".png"));
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
