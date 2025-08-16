package net.pufferlab.antiquities.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.pufferlab.antiquities.blocks.BlockGlobe;
import net.pufferlab.antiquities.client.models.ModelGlobe;
import net.pufferlab.antiquities.tileentities.TileEntityGlobe;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BlockGlobeRender implements ISimpleBlockRenderingHandler {

    ModelGlobe model = new ModelGlobe();
    final int renderID;

    public BlockGlobeRender(int blockComplexRenderID) {
        this.renderID = blockComplexRenderID;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        int facingMeta = 0;
        model.setFacing(facingMeta);

        GL11.glPushMatrix();
        model.render();

        if (facingMeta == 2) {
            GL11.glRotatef(-22.5F, 0, 0.0F, 1.0F);
        } else if (facingMeta == 1) {
            GL11.glRotatef(22.5F, 1.0F, 0.0F, 0);
        } else if (facingMeta == 3) {
            GL11.glRotatef(-22.5F, 1.0F, 0.0F, 0);
        } else {
            GL11.glRotatef(22.5F, 0, 0.0F, 1.0F);
        }
        GL11.glRotatef(0F, 0.0F, 1.0F, 0.0F);

        model.renderGlobe();

        GL11.glPopMatrix();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        if (world == null || world.getTileEntity(x, y, z) != null) return false;
        MovingObjectPosition mop = Minecraft.getMinecraft().objectMouseOver;
        if (mop == null || mop.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK
            || !(world.getBlock(mop.blockX, mop.blockY, mop.blockZ) instanceof BlockGlobe)) return false;
        TileEntityGlobe globe = (TileEntityGlobe) world.getTileEntity(x, y, z);

        model.setFacing(globe.facingMeta);

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
        model.render();
        GL11.glPopMatrix();
        return true;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return renderID;
    }
}
