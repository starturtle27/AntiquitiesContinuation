package net.pufferlab.antiquities;

import net.minecraft.block.Block;
import net.pufferlab.antiquities.blocks.*;
import net.pufferlab.antiquities.entity.EntitySeat;
import net.pufferlab.antiquities.itemblocks.ItemBlockMeta;
import net.pufferlab.antiquities.tileentities.*;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class Registry {

    public static Block chair;
    public static Block table;
    public static Block shelf_0;
    public static Block shelf_1;
    public static Block shelf_2;
    public static Block shelf_3;
    public static Block shelf_4;
    public static Block shelf_5;
    public static Block jar;
    public static Block rack;
    public static Block globe;

    public void preInit(FMLPreInitializationEvent event) {
        chair = new BlockChair(Constants.woodTypes);
        table = new BlockTable(Constants.woodTypes);
        shelf_0 = new BlockShelf(0, Constants.woodTypes);
        shelf_1 = new BlockShelf(1, Constants.woodTypes);
        shelf_2 = new BlockShelf(2, Constants.woodTypes);
        shelf_3 = new BlockShelf(3, Constants.woodTypes);
        shelf_4 = new BlockShelf(4, Constants.woodTypes);
        shelf_5 = new BlockShelf(5, Constants.woodTypes);
        jar = new BlockJar(Constants.woodTypes);
        rack = new BlockRack(Constants.woodTypes);
        globe = new BlockGlobe();
        GameRegistry.registerTileEntity(TileEntityChair.class, "antiquities_chair");
        GameRegistry.registerTileEntity(TileEntityTable.class, "antiquities_table");
        GameRegistry.registerTileEntity(TileEntityShelf.class, "antiquities_shelf");
        GameRegistry.registerTileEntity(TileEntityJar.class, "antiquities_jar");
        GameRegistry.registerTileEntity(TileEntityRack.class, "antiquities_rack");
        GameRegistry.registerTileEntity(TileEntityGlobe.class, "antiquities_globe");

        GameRegistry.registerBlock(chair, ItemBlockMeta.class, "chair");
        GameRegistry.registerBlock(shelf_0, ItemBlockMeta.class, "shelf");
        GameRegistry.registerBlock(shelf_1, ItemBlockMeta.class, "shelf_1");
        GameRegistry.registerBlock(shelf_2, ItemBlockMeta.class, "shelf_2");
        GameRegistry.registerBlock(shelf_3, ItemBlockMeta.class, "shelf_3");
        GameRegistry.registerBlock(shelf_4, ItemBlockMeta.class, "shelf_4");
        GameRegistry.registerBlock(shelf_5, ItemBlockMeta.class, "shelf_5");
        GameRegistry.registerBlock(table, ItemBlockMeta.class, "table");
        GameRegistry.registerBlock(jar, ItemBlockMeta.class, "jar");
        GameRegistry.registerBlock(rack, ItemBlockMeta.class, "rack");
        GameRegistry.registerBlock(globe, "globe");
    }

    public void init() {
        int id = 0;
        EntityRegistry
            .registerModEntity(EntitySeat.class, "EntitySeatAntiquities", id++, Antiquities.instance, 64, 20, true);
    }
}
