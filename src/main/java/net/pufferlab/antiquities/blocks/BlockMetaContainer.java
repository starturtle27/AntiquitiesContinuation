package net.pufferlab.antiquities.blocks;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.pufferlab.antiquities.Utils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BlockMetaContainer extends BlockContainer {

    private String[] elements;
    private String[] elementsBlacklist;
    private IIcon[] icons;
    private IIcon[] icons_model;
    private String name;

    protected BlockMetaContainer(Material material, String[] materials, String type, String[] blacklist) {
        super(material);
        elements = materials;
        name = type;
        elementsBlacklist = blacklist;
        if (material == Material.wood) {
            this.setHardness(2.0F);
            this.setResistance(5.0F);
            this.setStepSound(soundTypeWood);
        }
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        icons = new IIcon[elements.length];
        icons_model = new IIcon[elements.length];

        for (int i = 0; i < elements.length; i++) {
            if (!Utils.containsExactMatch(elementsBlacklist, elements[i])) {
                icons[i] = register.registerIcon("antiquities:" + elements[i]);
                icons_model[i] = register.registerIcon("antiquities:" + elements[i] + "_" + name);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
        for (int i = 0; i < elements.length; i++) {
            if (!Utils.containsExactMatch(elementsBlacklist, elements[i])) {
                list.add(new ItemStack(item, 1, i));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (meta >= elements.length || Utils.containsExactMatch(elementsBlacklist, elements[meta])) {
            return null;
        }
        if (side == 99) {
            return icons_model[meta];
        }
        return icons[meta];
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    public String[] getElements() {
        return elements;
    }

    public String[] getElementsBlacklist() {
        return elementsBlacklist;
    }

    public String getElementName() {
        return name;
    }

    public String getType(int meta) {
        return elements[meta];
    }
}
