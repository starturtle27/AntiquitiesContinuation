package net.pufferlab.antiquities;

import net.pufferlab.antiquities.client.renderer.*;
import net.pufferlab.antiquities.tileentities.TileEntityChair;
import net.pufferlab.antiquities.tileentities.TileEntityGlobe;
import net.pufferlab.antiquities.tileentities.TileEntityTable;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

    int chairRenderID;
    int tableRenderID;
    int globeRenderID;

    @Override
    public void registerRenders() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChair.class, new TileEntityChairRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTable.class, new TileEntityTableRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGlobe.class, new TileEntityGlobeRenderer());

        chairRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockChairRender(chairRenderID));
        tableRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockTableRender(tableRenderID));
        globeRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockGlobeRender(globeRenderID));
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
    public int getGlobeRenderID() {
        return globeRenderID;
    }
}
