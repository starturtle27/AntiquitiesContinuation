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

public class ModelRack extends ModelBase {

    private final ModelRenderer bb_main;

    public ModelRack() {
        textureWidth = 32;
        textureHeight = 32;

        bb_main = new ModelRenderer(this);
        bb_main.setRotationPoint(0.0F, 0.0F, 0.0F);
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -6.0F, -15.0F + 8F, 6.0F, 12, 3, 2, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 8, 5, -5.0F, -14.0F + 8F, 5.0F, 1, 1, 1, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 8, 5, -0.5F, -14.0F + 8F, 5.0F, 1, 1, 1, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 8, 5, 4.0F, -14.0F + 8F, 5.0F, 1, 1, 1, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 5, -8.0F, -16.0F + 8F, 6.0F, 2, 16, 2, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 5, 6.0F, -16.0F + 8F, 6.0F, 2, 16, 2, 0.0F));
    }

    public void render(String type) {
        bindTex(type + "_rack");
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
