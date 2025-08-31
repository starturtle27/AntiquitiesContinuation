package net.pufferlab.antiquities.tileentities;

import net.minecraft.item.ItemStack;

public class TileEntityPile extends TileEntityInventory {

    public TileEntityPile() {
        super(64);
    }

    public boolean addItemInPile(ItemStack ingot) {
        for (int i = 0; i < getSizeInventory(); i++) {
            if (getInventoryStack(i) == null) {
                setInventorySlotContentsUpdate(i, ingot);
                return true;
            }
        }
        return false;

    }

    public boolean canAddItemInPile() {
        for (int i = 0; i < getSizeInventory(); i++) {
            if (getInventoryStack(i) == null) {
                return true;
            }
        }
        return false;

    }

    public int getLayer() {
        for (int i = 0; i < getSizeInventory(); i++) {
            if (getInventoryStack(i) == null) {
                return (int) (double) ((i - 1) / 8);
            }
        }
        return 7;
    }

    public int getNextSlot() {
        for (int i = 0; i < getSizeInventory(); i++) {
            if (getInventoryStack(i) == null) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }
}
