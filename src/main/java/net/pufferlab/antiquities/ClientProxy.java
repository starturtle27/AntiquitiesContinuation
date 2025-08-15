package net.pufferlab.antiquities;

import net.pufferlab.antiquities.client.renderer.BlockChairRender;
import net.pufferlab.antiquities.client.renderer.BlockTableRender;
import net.pufferlab.antiquities.client.renderer.TileEntityChairRenderer;
import net.pufferlab.antiquities.client.renderer.TileEntityTableRenderer;
import net.pufferlab.antiquities.tileentities.TileEntityChair;
import net.pufferlab.antiquities.tileentities.TileEntityTable;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

    int chairRenderID;
    int tableRenderID;

    @Override
    public void registerRenders() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChair.class, new TileEntityChairRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTable.class, new TileEntityTableRenderer());

        chairRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockChairRender(chairRenderID));
        tableRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockTableRender(tableRenderID));

    }

    @Override
    public int getChairRenderID() {
        return chairRenderID;
    }

    @Override
    public int getTableRenderID() {
        return tableRenderID;
    }
}
