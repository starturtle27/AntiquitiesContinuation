package net.pufferlab.antiquities.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.pufferlab.antiquities.blocks.BlockMetaContainer;
import net.pufferlab.antiquities.client.models.ModelChair;

import net.pufferlab.antiquities.tileentities.TileEntityChair;
import org.lwjgl.opengl.GL11;

public class TileEntityChairRenderer extends TileEntitySpecialRenderer {

    ModelChair model = new ModelChair();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTicks) {
        World world = tileEntity.getWorldObj();
        TileEntityChair chair = (TileEntityChair) tileEntity;
        Block block = world.getBlock(chair.xCoord, chair.yCoord, chair.zCoord);
        String wood = "null";
        if (block instanceof BlockMetaContainer block2) {
            int metadata = world.getBlockMetadata(chair.xCoord, chair.yCoord, chair.zCoord);
            wood = block2.getType(metadata);
        }

        model.setFacing(chair.facingMeta);

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
        model.render(wood);
        GL11.glPopMatrix();
    }
}
