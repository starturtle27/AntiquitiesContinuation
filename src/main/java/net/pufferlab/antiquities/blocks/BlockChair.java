package net.pufferlab.antiquities.blocks;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.pufferlab.antiquities.Antiquities;
import net.pufferlab.antiquities.entity.EntitySeat;
import net.pufferlab.antiquities.tileentities.TileEntityChair;

public class BlockChair extends BlockContainer {

    public BlockChair() {
        super(Material.wood);
        this.setBlockBounds(0.1F, 0F, 0.1F, 0.9F, 1F, 0.9F);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
        float hitY, float hitZ) {
        if (world.isRemote) return true;

        List<EntitySeat> seats = world
            .getEntitiesWithinAABB(EntitySeat.class, AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1));
        if (!seats.isEmpty()) return true;

        EntitySeat seat = new EntitySeat(world, x + 0.5, y + 0.0, z + 0.5);
        world.spawnEntityInWorld(seat);

        player.mountEntity(seat);

        return true;

    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityChair();
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
        return Antiquities.proxy.getChairRenderID();
    }
}
