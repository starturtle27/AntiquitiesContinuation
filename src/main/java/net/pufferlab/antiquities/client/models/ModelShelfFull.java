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

public class ModelShelfFull extends ModelBase {

    private final ModelRenderer bb_main;

    public ModelShelfFull() {
        textureWidth = 64;
        textureHeight = 64;

        bb_main = new ModelRenderer(this);
        setFacing(0);
        bb_main.setRotationPoint(0.0F, 0.0F, 0.0F);
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 28, -8.0F, -7.0F + 8F, 0.0F, 1, 6, 8, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 28, -8.0F, -15.0F + 8F, 0.0F, 1, 6, 8, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 1, 28, 7.0F, -15.0F + 8F, 0.0F, 1, 6, 8, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 1, 28, 7.0F, -7.0F + 8F, 0.0F, 1, 6, 8, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 1, 50, -8.0F, -16.0F + 8F, 0.0F, 16, 1, 8, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 1, 42, -8.0F, -1.0F + 8F, 0.0F, 16, 1, 8, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 1, 42, -8.0F, -9.0F + 8F, 0.0F, 16, 2, 8, 0.0F));
    }

    public void render(String type) {
        bindTex(type + "_shelf");
        bb_main.rotateAngleX = (float) Math.toRadians(180);
        bb_main.render(Constants.ModelConstant);
    }

    public void render(RenderBlocks renderblocks, Tessellator tess, Block block, int meta, int x, int y, int z) {
        bb_main.rotateAngleX = (float) Math.toRadians(180);
        ModelTESS.renderBlock(renderblocks, tess, block, bb_main, Constants.ModelConstant, x, y, z, meta);
    }

    public void bindTex(String fileName) {
        Minecraft.getMinecraft().renderEngine
            .bindTexture(Antiquities.asResource("textures/blocks/" + fileName + ".png"));
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
