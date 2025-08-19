package net.pufferlab.antiquities.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class SlotShelf extends Slot {

    final ContainerShelf containerShelf;

    public SlotShelf(ContainerShelf shelfContainer, IInventory iInventory, int x, int y, int z) {
        super(iInventory, x, y, z);
        this.containerShelf = shelfContainer;
    }

    public int getSlotStackLimit() {
        return 64;
    }
}
