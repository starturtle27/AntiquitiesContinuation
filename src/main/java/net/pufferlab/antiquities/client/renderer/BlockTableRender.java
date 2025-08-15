package net.pufferlab.antiquities.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.pufferlab.antiquities.blocks.BlockMetaContainer;
import net.pufferlab.antiquities.blocks.BlockTable;
import net.pufferlab.antiquities.client.models.ModelTable;
import net.pufferlab.antiquities.tileentities.TileEntityTable;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BlockTableRender implements ISimpleBlockRenderingHandler {

    ModelTable model = new ModelTable();
    final int renderID;

    public BlockTableRender(int blockComplexRenderID) {
        this.renderID = blockComplexRenderID;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        BlockMetaContainer block2 = (BlockMetaContainer) block;
        String wood = block2.getType(metadata);
        this.model.top1C.isHidden = true;
        this.model.top2C.isHidden = true;
        model.render(wood);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        if (world == null || world.getTileEntity(x, y, z) != null) return false;
        MovingObjectPosition mop = Minecraft.getMinecraft().objectMouseOver;
        if (mop == null || mop.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK
            || !(world.getBlock(mop.blockX, mop.blockY, mop.blockZ) instanceof BlockTable)) return false;
        TileEntityTable table = (TileEntityTable) world.getTileEntity(x, y, z);

        this.model.leg1.isHidden = table.connectZPos || table.connectXPos;
        this.model.side1.isHidden = table.connectZPos;
        this.model.leg2.isHidden = table.connectZPos || table.connectXNeg;

        this.model.side2.isHidden = table.connectXNeg;

        this.model.leg3.isHidden = table.connectZNeg || table.connectXNeg;
        this.model.side3.isHidden = table.connectZNeg;
        this.model.leg4.isHidden = table.connectZNeg || table.connectXPos;

        this.model.side4.isHidden = table.connectXPos;

        this.model.top1B.isHidden = table.connectXNeg;
        this.model.top1.isHidden = table.connectXNeg;

        this.model.top1C.isHidden = !table.connectXNeg;

        this.model.top1CB.isHidden = !(table.connectXNeg && table.connectZNeg);

        this.model.top3B.isHidden = table.connectXPos;
        this.model.top3.isHidden = table.connectXPos;

        this.model.top2B.isHidden = table.connectZNeg;
        this.model.top2.isHidden = table.connectZNeg;

        this.model.top2C.isHidden = !table.connectZNeg;

        this.model.top4B.isHidden = table.connectZPos;
        this.model.top4.isHidden = table.connectZPos;

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
        BlockMetaContainer block2 = (BlockMetaContainer) block;
        int metadata = world.getBlockMetadata(x, y, z);
        String wood = block2.getType(metadata);
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
