package net.pufferlab.antiquities.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.pufferlab.antiquities.tileentities.TileEntityChair;

public class EntitySeat extends Entity {

    private TileEntityChair seatTile;

    public EntitySeat(World world) {
        super(world);
        this.noClip = true; // no collision
        this.setSize(0.0F, 0.0F); // invisible hitbox
    }

    public EntitySeat(World world, double x, double y, double z) {
        this(world);
        this.setPosition(x, y, z);
    }

    @Override
    protected void entityInit() {}

    @Override
    protected void readEntityFromNBT(NBTTagCompound tagCompund) {}

    @Override
    protected void writeEntityToNBT(NBTTagCompound tagCompound) {}

    @Override
    public void onUpdate() {
        super.onUpdate();
        this.motionX = 0;
        this.motionY = 0;
        this.motionZ = 0;

        if (this.riddenByEntity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) this.riddenByEntity;

            player.setSprinting(false);

            if (worldObj.isRemote) {
                player.stepHeight = 0.6F;
                player.distanceWalkedModified = 0.0F;
            }
        }

        if (this.riddenByEntity == null && !this.worldObj.isRemote) {
            this.setDead();
        }

        if(!(worldObj.getTileEntity((int) this.posX, (int) this.posY, (int) this.posZ) instanceof TileEntityChair) && !this.worldObj.isRemote) {
            this.setDead();
        }
        
    }

    @Override
    public double getMountedYOffset() {
        return 0.55D;
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public boolean canBePushed() {
        return false;
    }
}
