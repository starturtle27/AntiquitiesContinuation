package net.pufferlab.antiquities.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.pufferlab.antiquities.blocks.BlockTable;

public class TileEntityTable extends TileEntity {

    public boolean connectXPos;
    public boolean connectXNeg;
    public boolean connectZPos;
    public boolean connectZNeg;

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        this.connectXPos = tag.getBoolean("connectXPos");
        this.connectXNeg = tag.getBoolean("connectXNeg");
        this.connectZPos = tag.getBoolean("connectZPos");
        this.connectZNeg = tag.getBoolean("connectZNeg");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setBoolean("connectXPos", this.connectXPos);
        tag.setBoolean("connectXNeg", this.connectXNeg);
        tag.setBoolean("connectZPos", this.connectZPos);
        tag.setBoolean("connectZNeg", this.connectZNeg);
    }

    @Override
    public void updateEntity() {
        if ((worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord + 1) instanceof BlockTable)) {
            this.connectZPos = true;
        } else {
            this.connectZPos = false;
        }
        if ((worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord - 1) instanceof BlockTable)) {
            this.connectZNeg = true;
        } else {
            this.connectZNeg = false;
        }
        if ((worldObj.getBlock(this.xCoord + 1, this.yCoord, this.zCoord) instanceof BlockTable)) {
            this.connectXPos = true;
        } else {
            this.connectXPos = false;
        }
        if ((worldObj.getBlock(this.xCoord - 1, this.yCoord, this.zCoord) instanceof BlockTable)) {
            this.connectXNeg = true;
        } else {
            this.connectXNeg = false;
        }
    }

}
