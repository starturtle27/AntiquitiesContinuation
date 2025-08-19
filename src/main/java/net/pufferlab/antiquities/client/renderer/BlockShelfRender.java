package net.pufferlab.antiquities.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.pufferlab.antiquities.blocks.BlockShelf;
import net.pufferlab.antiquities.client.models.ModelShelf;
import net.pufferlab.antiquities.client.models.ModelShelfFull;
import net.pufferlab.antiquities.client.models.ModelShelfLong;
import net.pufferlab.antiquities.tileentities.TileEntityShelf;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BlockShelfRender implements ISimpleBlockRenderingHandler {

    ModelShelfFull model0 = new ModelShelfFull();
    ModelShelf model1 = new ModelShelf();
    ModelShelfLong model2 = new ModelShelfLong();
    final int renderID;

    public BlockShelfRender(int blockComplexRenderID) {
        this.renderID = blockComplexRenderID;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        BlockShelf block2 = (BlockShelf) block;
        String wood = block2.getType(metadata);
        if (block2.getShelfType() == 0) {
            model0.render(wood);
        } else if (block2.getShelfType() == 1) {
            model1.render(wood);
        } else if (block2.getShelfType() == 2) {
            model2.render(wood);
        }
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        if (world == null || world.getTileEntity(x, y, z) != null) return false;
        MovingObjectPosition mop = Minecraft.getMinecraft().objectMouseOver;
        if (mop == null || mop.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK
            || !(world.getBlock(mop.blockX, mop.blockY, mop.blockZ) instanceof BlockShelf)) return false;
        TileEntityShelf shelf = (TileEntityShelf) world.getTileEntity(x, y, z);
        BlockShelf block2 = (BlockShelf) block;
        int metadata = world.getBlockMetadata(shelf.xCoord, shelf.yCoord, shelf.zCoord);
        String wood = block2.getType(metadata);

        model0.setFacing(shelf.facingMeta);
        model1.setFacing(shelf.facingMeta);
        model2.setFacing(shelf.facingMeta);

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
        if (block2.getShelfType() == 0) {
            model0.render(wood);
        } else if (block2.getShelfType() == 1) {
            model1.render(wood);
        } else if (block2.getShelfType() == 2) {
            model2.render(wood);
        }
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
