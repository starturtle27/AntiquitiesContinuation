package net.pufferlab.antiquities;

import net.minecraft.block.Block;
import net.pufferlab.antiquities.blocks.BlockChair;
import net.pufferlab.antiquities.blocks.BlockGlobe;
import net.pufferlab.antiquities.blocks.BlockTable;
import net.pufferlab.antiquities.entity.EntitySeat;
import net.pufferlab.antiquities.itemblocks.ItemBlockMeta;
import net.pufferlab.antiquities.tileentities.TileEntityChair;
import net.pufferlab.antiquities.tileentities.TileEntityGlobe;
import net.pufferlab.antiquities.tileentities.TileEntityTable;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class Registry {

    public static Block chair;
    public static Block table;
    public static Block globe;

    public void preInit(FMLPreInitializationEvent event) {
        chair = new BlockChair(Constants.woodTypes);
        table = new BlockTable(Constants.woodTypes);
        globe = new BlockGlobe();
        GameRegistry.registerTileEntity(TileEntityChair.class, "chair");
        GameRegistry.registerTileEntity(TileEntityTable.class, "table");
        GameRegistry.registerTileEntity(TileEntityGlobe.class, "globe");

        GameRegistry.registerBlock(chair, ItemBlockMeta.class, "chair");
        GameRegistry.registerBlock(table, ItemBlockMeta.class, "table");
        GameRegistry.registerBlock(globe, "globe");
    }

    public void init() {
        int id = 0;
        EntityRegistry
            .registerModEntity(EntitySeat.class, "EntitySeatAntiquities", id++, Antiquities.instance, 64, 20, true);
    }
}
