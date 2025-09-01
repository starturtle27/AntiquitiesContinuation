package net.pufferlab.antiquities.recipes;

import net.minecraft.block.Block;
import net.pufferlab.antiquities.Constants;
import net.pufferlab.antiquities.Registry;

import biomesoplenty.api.content.BOPCBlocks;

public class RecipesBOP extends RecipesHelper {

    public static Block[] woodBlocks = new Block[] { BOPCBlocks.logs1, BOPCBlocks.logs2, BOPCBlocks.logs3,
        BOPCBlocks.logs4, BOPCBlocks.planks, BOPCBlocks.woodenSingleSlab1, BOPCBlocks.woodenSingleSlab2,
        Registry.chair_bop, Registry.table_bop, Registry.shelf_0_bop, Registry.shelf_1_bop, Registry.shelf_2_bop,
        Registry.shelf_3_bop, Registry.shelf_4_bop, Registry.shelf_5_bop, Registry.jar_bop, Registry.rack_bop };
    public static String[] woodTypes = Constants.bopWoodTypes;
    public static String[] woodTypes2 = Constants.bopPlankTypes;

    public RecipesBOP() {
        super(woodBlocks, woodTypes, woodTypes2);
    }

    public void run() {
        for (String wood : Constants.bopWoodTypes) {
            addRecipes(wood);
        }
    }
}
