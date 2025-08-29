package net.pufferlab.antiquities.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.pufferlab.antiquities.Antiquities;
import net.pufferlab.antiquities.Utils;
import net.pufferlab.antiquities.tileentities.TileEntityPile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPile extends BlockContainer {

    private IIcon[] icons;

    public BlockPile() {
        super(Material.iron);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityPile();
    }

    @Override
    public void onBlockPlacedBy(World worldIn, int x, int y, int z, EntityLivingBase placer, ItemStack itemIn) {
        super.onBlockPlacedBy(worldIn, x, y, z, placer, itemIn);

        int yaw = MathHelper.floor_double((double) (placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        int metayaw = Utils.getDirectionXZYaw(yaw);
        TileEntityPile pile = (TileEntityPile) worldIn.getTileEntity(x, y, z);
        if (pile != null) {
            pile.setFacingMeta(metayaw);
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
        float hitY, float hitZ) {
        super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);

        if (world.getTileEntity(x, y, z) instanceof TileEntityPile pile) {
            ItemStack heldItem = player.getHeldItem();

            pile.addItemInPile(heldItem);

            return true;
        }
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        icons = new IIcon[1];
        icons[0] = register.registerIcon("antiquities:pile");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return icons[0];
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return Antiquities.proxy.getPileRenderID();
    }
}
