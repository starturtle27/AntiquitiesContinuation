package net.pufferlab.antiquities.client.compat;

import net.minecraft.item.ItemStack;
import net.pufferlab.antiquities.Registry;
import net.pufferlab.antiquities.Tags;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEIConfig implements IConfigureNEI {

    @Override
    public void loadConfig() {
        API.hideItem(new ItemStack(Registry.pile));
    }

    @Override
    public String getName() {
        return "Antiquities NEI Plugin";
    }

    @Override
    public String getVersion() {
        return Tags.VERSION;
    }
}
