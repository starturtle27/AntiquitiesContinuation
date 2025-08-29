package net.pufferlab.antiquities.tileentities;

import net.minecraft.item.ItemStack;

public class TileEntityPile extends TileEntityInventory {

    public TileEntityPile() {
        super(64);
    }

    public boolean addItemInPile(ItemStack ingot) {
        for (int i = 0; i < 64; i++) {
            if (getInventoryStack(i) == null) {
                setInventorySlotContentsUpdate(i, ingot);
                return true;
            }
        }
        return false;

    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }
}
