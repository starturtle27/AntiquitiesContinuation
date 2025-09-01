package net.pufferlab.antiquities.recipes;

import net.minecraft.block.Block;
import net.pufferlab.antiquities.Constants;
import net.pufferlab.antiquities.Registry;

import thaumcraft.common.config.ConfigBlocks;

public class RecipesTC extends RecipesHelper {

    public static Block[] woodBlocks = new Block[] { ConfigBlocks.blockMagicalLog, null, null, null,
        ConfigBlocks.blockWoodenDevice, ConfigBlocks.blockSlabWood, null, Registry.chair_tc, Registry.table_tc,
        Registry.shelf_0_tc, Registry.shelf_1_tc, Registry.shelf_2_tc, Registry.shelf_3_tc, Registry.shelf_4_tc,
        Registry.shelf_5_tc, Registry.jar_tc, Registry.rack_tc };
    public static String[] woodTypes = Constants.thaumcraftWoodTypes;
    public static String[] woodTypes2 = Constants.thaumcraftPlankTypes;

    public RecipesTC() {
        super(woodBlocks, woodTypes, woodTypes2);
    }

    public void run() {
        for (String wood : Constants.thaumcraftWoodTypes) {
            addRecipes(wood);
        }
    }
}
