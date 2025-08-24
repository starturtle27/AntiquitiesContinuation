package net.pufferlab.antiquities;

import net.pufferlab.antiquities.client.renderer.*;
import net.pufferlab.antiquities.tileentities.TileEntityGlobe;
import net.pufferlab.antiquities.tileentities.TileEntityJar;
import net.pufferlab.antiquities.tileentities.TileEntityShelf;
import net.pufferlab.antiquities.tileentities.TileEntityTable;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

    int chairRenderID;
    int tableRenderID;
    int shelfRenderID;
    int globeRenderID;
    int jarRenderID;

    @Override
    public void registerRenders() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTable.class, new TileEntityTableRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityShelf.class, new TileEntityShelfRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityJar.class, new TileEntityJarRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGlobe.class, new TileEntityGlobeRenderer());

        chairRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockChairRender(chairRenderID));
        tableRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockTableRender(tableRenderID));
        shelfRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockShelfRender(shelfRenderID));
        globeRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockGlobeRender(globeRenderID));
        jarRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockJarRender(jarRenderID));
    }

    @Override
    public int getChairRenderID() {
        return chairRenderID;
    }

    @Override
    public int getTableRenderID() {
        return tableRenderID;
    }

    @Override
    public int getShelfRenderID() {
        return shelfRenderID;
    }

    @Override
    public int getGlobeRenderID() {
        return globeRenderID;
    }

    @Override
    public int getJarRenderID() {
        return jarRenderID;
    }
}
