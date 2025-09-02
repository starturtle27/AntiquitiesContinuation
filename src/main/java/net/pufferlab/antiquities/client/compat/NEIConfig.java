package net.pufferlab.antiquities.client.compat;

import net.pufferlab.antiquities.Tags;

import codechicken.nei.api.IConfigureNEI;

public class NEIConfig implements IConfigureNEI {

    @Override
    public void loadConfig() {}

    @Override
    public String getName() {
        return "Antiquities NEI Plugin";
    }

    @Override
    public String getVersion() {
        return Tags.VERSION;
    }
}
