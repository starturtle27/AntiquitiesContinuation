package net.pufferlab.antiquities.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.pufferlab.antiquities.Utils;

public class ItemBlockRotatedXZ extends ItemBlock {

    public ItemBlockRotatedXZ(Block block) {
        super(block);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
        float hitX, float hitY, float hitZ) {
        int x2 = Utils.getBlockX(side, x);
        int y2 = Utils.getBlockY(side, y);
        int z2 = Utils.getBlockZ(side, z);
        boolean flag = false;

        if (side > 1) {
            int meta = Utils.getDirectionXZ(side);
            if (place(stack, world, x2, y2, z2, this.field_150939_a, meta)) {
                place(stack, world, x2, y2, z2, this.field_150939_a, meta);
                flag = true;
            }
        } else {
            int yaw = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
            int metayaw = Utils.getDirectionXZYaw(yaw);
            if (place(stack, world, x2, y2, z2, this.field_150939_a, metayaw)) {
                place(stack, world, x2, y2, z2, this.field_150939_a, metayaw);
                flag = true;
            }
        }

        return flag;
    }

    private boolean place(ItemStack stack, World world, int x, int y, int z, Block toPlace, int metadata) {
        if (world.checkNoEntityCollision(toPlace.getCollisionBoundingBoxFromPool(world, x, y, z))
            && world.setBlock(x, y, z, toPlace, metadata, 3)) {
            world.setBlock(x, y, z, toPlace, metadata, 2);
            stack.stackSize -= 1;
            world.playSoundEffect(
                x + 0.5f,
                y + 0.5f,
                z + 0.5f,
                this.field_150939_a.stepSound.func_150496_b(),
                (this.field_150939_a.stepSound.getVolume() + 1.0F) / 2.0F,
                this.field_150939_a.stepSound.getPitch() * 0.8F);
            return true;
        } else {
            return false;
        }
    }
}
