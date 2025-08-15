package net.pufferlab.antiquities.client.renderer;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.pufferlab.antiquities.blocks.BlockMetaContainer;
import net.pufferlab.antiquities.client.models.ModelTable;
import net.pufferlab.antiquities.tileentities.TileEntityTable;

import org.lwjgl.opengl.GL11;

public class TileEntityTableRenderer extends TileEntitySpecialRenderer {

    ModelTable model = new ModelTable();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTicks) {
        World world = tileEntity.getWorldObj();
        TileEntityTable table = (TileEntityTable) tileEntity;

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
        BlockMetaContainer block2 = (BlockMetaContainer) tileEntity.blockType;
        int metadata = world.getBlockMetadata((int) x, (int) y, (int) z);
        String wood = block2.getType(metadata);
        this.model.render(wood);
        GL11.glPopMatrix();
    }
}
