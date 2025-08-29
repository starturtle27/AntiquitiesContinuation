package net.pufferlab.antiquities.events;

import static net.minecraftforge.event.entity.player.PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.pufferlab.antiquities.Registry;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventHandler {

    @SubscribeEvent
    public void playerInteractEventHandler(PlayerInteractEvent event) {
        if (event.action == RIGHT_CLICK_BLOCK) {
            ItemStack heldItem = event.entityPlayer.inventory.getCurrentItem();
            int[] oreIDS = OreDictionary.getOreIDs(heldItem);
            for (int oreID : oreIDS) {
                String oreName = OreDictionary.getOreName(oreID);
                if (oreName.contains("ingot")) {
                    // String[] metalNames = oreName.split("ingot");
                    // String metalName = metalNames[1];
                    place(heldItem, event.world, event.x, event.y, event.z, Registry.pile, 0, event);
                }
            }
        }
    }

    private void place(ItemStack stack, World world, int x, int y, int z, Block toPlace, int metadata,
        PlayerInteractEvent event) {
        if (world.checkNoEntityCollision(toPlace.getCollisionBoundingBoxFromPool(world, x, y, z))
            && world.setBlock(x, y, z, toPlace, metadata, 3)) {
            world.setBlock(x, y, z, toPlace, metadata, 2);
            stack.stackSize -= 1;
            world.playSoundEffect(
                x + 0.5f,
                y + 0.5f,
                z + 0.5f,
                toPlace.stepSound.func_150496_b(),
                (toPlace.stepSound.getVolume() + 1.0F) / 2.0F,
                toPlace.stepSound.getPitch() * 0.8F);
            event.entityPlayer.swingItem();
        }
    }
}
