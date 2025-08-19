package net.pufferlab.antiquities;

import net.minecraft.block.Block;
import net.pufferlab.antiquities.blocks.BlockChair;
import net.pufferlab.antiquities.blocks.BlockGlobe;
import net.pufferlab.antiquities.blocks.BlockShelf;
import net.pufferlab.antiquities.blocks.BlockTable;
import net.pufferlab.antiquities.entity.EntitySeat;
import net.pufferlab.antiquities.itemblocks.ItemBlockMeta;
import net.pufferlab.antiquities.tileentities.TileEntityChair;
import net.pufferlab.antiquities.tileentities.TileEntityGlobe;
import net.pufferlab.antiquities.tileentities.TileEntityShelf;
import net.pufferlab.antiquities.tileentities.TileEntityTable;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class Registry {

    public static Block chair;
    public static Block table;
    public static Block shelf_0;
    public static Block shelf_1;
    public static Block shelf_2;
    public static Block globe;

    public void preInit(FMLPreInitializationEvent event) {
        chair = new BlockChair(Constants.woodTypes);
        table = new BlockTable(Constants.woodTypes);
        shelf_0 = new BlockShelf(0, Constants.woodTypes);
        shelf_1 = new BlockShelf(1, Constants.woodTypes);
        shelf_2 = new BlockShelf(2, Constants.woodTypes);
        globe = new BlockGlobe();
        GameRegistry.registerTileEntity(TileEntityChair.class, "antiquities_chair");
        GameRegistry.registerTileEntity(TileEntityTable.class, "antiquities_table");
        GameRegistry.registerTileEntity(TileEntityShelf.class, "antiquities_shelf");
        GameRegistry.registerTileEntity(TileEntityGlobe.class, "antiquities_globe");

        GameRegistry.registerBlock(chair, ItemBlockMeta.class, "chair");
        GameRegistry.registerBlock(shelf_0, ItemBlockMeta.class, "shelf");
        GameRegistry.registerBlock(shelf_1, ItemBlockMeta.class, "shelf_1");
        GameRegistry.registerBlock(shelf_2, ItemBlockMeta.class, "shelf_2");
        GameRegistry.registerBlock(table, ItemBlockMeta.class, "table");
        GameRegistry.registerBlock(globe, "globe");
    }

    public void init() {
        int id = 0;
        EntityRegistry
            .registerModEntity(EntitySeat.class, "EntitySeatAntiquities", id++, Antiquities.instance, 64, 20, true);
    }
}
