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
import net.pufferlab.antiquities.client.tessrender.ModelTESS;

public class ModelShelfFull extends ModelBase {

    private final ModelRenderer bb_main;

    public ModelShelfFull() {
        textureWidth = 64;
        textureHeight = 64;

        bb_main = new ModelRenderer(this);
        setFacing(0);
        bb_main.setRotationPoint(0.0F, 0.0F, 0.0F);
        // bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -8.0F, -16.0F + 8F, 6.0F, 16, 16, 2, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 1 - 1, 18, 7.0F, -16.0F + 8F, 0.0F, 1, 16, 6 + 2, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 1 - 1, 18, -8.0F, -16.0F + 8F, 0.0F, 1, 16, 6 + 2, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 36 - 2, 8 - 2, 0.0F, -9.0F + 8F, 0.0F, 7, 2, 6 + 2, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 36 - 2, 8 - 2, -7.0F, -9.0F + 8F, 0.0F, 7, 2, 6 + 2, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 36 - 2, 16 - 2, 0.0F, -16.0F + 8F, 0.0F, 7, 1, 6 + 2, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 36 - 2, 16 - 2, -7.0F, -16.0F + 8F, 0.0F, 7, 1, 6 + 2, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 36 - 2, 8 - 2, -7.0F, -1.0F + 8F, 0.0F, 7, 1, 6 + 2, 0.0F));
        bb_main.cubeList.add(new ModelBox(bb_main, 36 - 2, 8 - 2, 0.0F, -1.0F + 8F, 0.0F, 7, 1, 6 + 2, 0.0F));
    }

    public void render(String type) {
        bindTex(type + "_shelf");
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
