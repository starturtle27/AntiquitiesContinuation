package net.pufferlab.antiquities.tileentities;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.pufferlab.antiquities.blocks.BlockMetaContainer;

public class TileEntityChair extends TileEntity {

    public int facingMeta;

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        this.facingMeta = tag.getInteger("facingMeta");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        tag.setInteger("facingMeta", this.facingMeta);
    }

    public void setFacingMeta(int meta) {
        this.facingMeta = meta;
        this.worldObj.notifyBlockChange(this.xCoord, this.yCoord, this.zCoord, this.blockType);
        this.markDirty();
    }
}
