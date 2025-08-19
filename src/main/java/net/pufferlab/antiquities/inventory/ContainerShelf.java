package net.pufferlab.antiquities.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.pufferlab.antiquities.tileentities.TileEntityShelf;

public class ContainerShelf extends Container {

    protected TileEntityShelf tileEntity;
    protected SlotShelf slotShelf;

    public ContainerShelf(InventoryPlayer inventoryPlayer, TileEntityShelf tile) {
        this.tileEntity = tile;
        addSlotToContainer(this.slotShelf = new SlotShelf(this, (IInventory) this.tileEntity, 0, 64, 22));
        addSlotToContainer(this.slotShelf = new SlotShelf(this, (IInventory) this.tileEntity, 1, 96, 22));
        addSlotToContainer(this.slotShelf = new SlotShelf(this, (IInventory) this.tileEntity, 2, 64, 51));
        addSlotToContainer(this.slotShelf = new SlotShelf(this, (IInventory) this.tileEntity, 3, 96, 51));
        bindPlayerInventory(inventoryPlayer);
    }

    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        int i;
        for (i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++)
                addSlotToContainer(new Slot((IInventory) inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
        }
        for (i = 0; i < 9; i++) addSlotToContainer(new Slot((IInventory) inventoryPlayer, i, 8 + i * 18, 142));
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }
}
