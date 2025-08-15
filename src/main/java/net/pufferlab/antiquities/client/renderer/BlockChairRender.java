package net.pufferlab.antiquities.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.pufferlab.antiquities.blocks.BlockChair;
import net.pufferlab.antiquities.blocks.BlockMetaContainer;
import net.pufferlab.antiquities.client.models.ModelChair;

import net.pufferlab.antiquities.tileentities.TileEntityChair;
import net.pufferlab.antiquities.tileentities.TileEntityTable;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BlockChairRender implements ISimpleBlockRenderingHandler {

    ModelChair model = new ModelChair();
    final int renderID;

    public BlockChairRender(int blockComplexRenderID) {
        this.renderID = blockComplexRenderID;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        BlockMetaContainer block2 = (BlockMetaContainer) block;
        String wood = block2.getType(metadata);
        model.render(wood);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        if (world == null || world.getTileEntity(x, y, z) != null) return false;
        MovingObjectPosition mop = Minecraft.getMinecraft().objectMouseOver;
        if (mop == null || mop.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK
            || !(world.getBlock(mop.blockX, mop.blockY, mop.blockZ) instanceof BlockChair)) return false;
        TileEntityChair chair = (TileEntityChair) world.getTileEntity(x, y, z);
        BlockMetaContainer block2 = (BlockMetaContainer) block;
        int metadata = world.getBlockMetadata(chair.xCoord, chair.yCoord, chair.zCoord);
        String wood = block2.getType(metadata);

        model.setFacing(chair.facingMeta);

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
        model.render(wood);
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
