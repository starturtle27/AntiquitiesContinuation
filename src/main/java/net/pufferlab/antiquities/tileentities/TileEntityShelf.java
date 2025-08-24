package net.pufferlab.antiquities.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.world.EnumSkyBlock;

public class TileEntityShelf extends TileEntityMetaFacing implements IInventory {

    private ItemStack[] shelfInventory;

    public TileEntityShelf() {
        this.shelfInventory = new ItemStack[4];
    }

    public int getSize() {
        return this.shelfInventory.length;
    }

    public ItemStack[] getInventory() {
        return this.shelfInventory;
    }

    @Override
    public int getFacingType() {
        return 0;
    }

    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        NBTTagList tagList = compound.getTagList("shelfInventory", 10);
        this.shelfInventory = new ItemStack[getSize()];
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = tagList.getCompoundTagAt(i);
            byte slot = tag.getByte("Slot");
            if (slot >= 0 && slot < this.shelfInventory.length)
                this.shelfInventory[slot] = ItemStack.loadItemStackFromNBT(tag);
        }
    }

    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < this.shelfInventory.length; i++) {
            ItemStack stack = this.shelfInventory[i];
            if (stack != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                stack.writeToNBT(tag);
                itemList.appendTag((NBTBase) tag);
            }
        }
        compound.setTag("shelfInventory", (NBTBase) itemList);
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < this.shelfInventory.length; i++) {
            ItemStack stack = this.shelfInventory[i];
            if (stack != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                stack.writeToNBT(tag);
                itemList.appendTag((NBTBase) tag);
            }
        }

        NBTTagCompound dataTag = new NBTTagCompound();

        dataTag.setInteger("facingMeta", this.facingMeta);
        dataTag.setTag("shelfInventory", (NBTBase) itemList);

        return (Packet) new S35PacketUpdateTileEntity(
            this.xCoord,
            this.yCoord,
            this.zCoord,
            this.blockMetadata,
            dataTag);
    }

    @Override
    public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
        NBTTagCompound nbtData = packet.func_148857_g();
        this.facingMeta = nbtData.getInteger("facingMeta");
        NBTTagList tagList = nbtData.getTagList("shelfInventory", 10);
        this.shelfInventory = new ItemStack[getSize()];
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = tagList.getCompoundTagAt(i);
            byte slot = tag.getByte("Slot");
            if (slot >= 0 && slot < this.shelfInventory.length)
                this.shelfInventory[slot] = ItemStack.loadItemStackFromNBT(tag);
        }
        this.worldObj.updateLightByType(EnumSkyBlock.Block, this.xCoord, this.yCoord, this.zCoord);
    }

    public ItemStack getInventoryStack(int slot) {
        ItemStack thing = this.shelfInventory[slot];
        if (thing != null) return thing;
        return null;
    }

    public boolean removeItemsInSlot(int slot) {
        if (this.shelfInventory[slot] != null) {
            setInventorySlotContents(slot, null);
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
            return true;
        }
        return false;
    }

    public boolean addItemInSlot(int slot, ItemStack stack) {
        if (this.shelfInventory[slot] == null) {
            setInventorySlotContents(slot, stack);
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
            return true;
        }
        return false;
    }

    @Override
    public int getSizeInventory() {
        return 4;
    }

    @Override
    public ItemStack getStackInSlot(int slotIn) {
        return this.shelfInventory[slotIn];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (this.shelfInventory[index] != null) {
            ItemStack itemstack;

            if (this.shelfInventory[index].stackSize <= count) {
                itemstack = this.shelfInventory[index];
                this.shelfInventory[index] = null;
                this.markDirty();
                return itemstack;
            } else {
                itemstack = this.shelfInventory[index].splitStack(count);

                if (this.shelfInventory[index].stackSize == 0) {
                    this.shelfInventory[index] = null;
                }

                this.markDirty();
                return itemstack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index) {
        if (this.shelfInventory[index] != null) {
            ItemStack itemstack = this.shelfInventory[index];
            this.shelfInventory[index] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.shelfInventory[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
    }

    @Override
    public String getInventoryName() {
        return "Shelf";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false
            : player
                .getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D)
                <= 64.0D;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }
}
