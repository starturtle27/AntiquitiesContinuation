package net.pufferlab.antiquities.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.pufferlab.antiquities.Utils;
import net.pufferlab.antiquities.blocks.BlockMetaContainer;

public class ItemBlockMeta extends ItemBlock {
    private String[] elements;
    private String[] elementsBlacklist;
    private String name;
    private BlockMetaContainer blockC;

    public ItemBlockMeta(Block block) {
        super(block);

        blockC = (BlockMetaContainer) field_150939_a;

        elements = blockC.getElements();
        elementsBlacklist = blockC.getElementsBlacklist();
        name = blockC.getElementName();
        this.setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        if (stack.getItemDamage() >= elements.length
            || Utils.containsExactMatch(elementsBlacklist, elements[stack.getItemDamage()])) {
            return "tile.antiquities.error";
        }
        return "tile.antiquities." + elements[stack.getItemDamage()] + "_" + name;
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }
}
