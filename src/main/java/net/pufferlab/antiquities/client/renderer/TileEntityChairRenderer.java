package net.pufferlab.antiquities.client.renderer;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.pufferlab.antiquities.client.models.ModelChair;

public class TileEntityChairRenderer extends TileEntitySpecialRenderer {

    ModelChair model = new ModelChair();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTicks) {
        return;
    }
}
