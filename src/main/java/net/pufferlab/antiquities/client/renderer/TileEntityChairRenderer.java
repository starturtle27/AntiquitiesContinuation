package net.pufferlab.antiquities.client.renderer;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.pufferlab.antiquities.client.models.ModelChair;

import org.lwjgl.opengl.GL11;

public class TileEntityChairRenderer extends TileEntitySpecialRenderer {

    ModelChair model = new ModelChair();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTicks) {
        int meta = tileEntity.blockMetadata;

        model.setFacing(meta);

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
        model.render();
        GL11.glPopMatrix();
    }
}
