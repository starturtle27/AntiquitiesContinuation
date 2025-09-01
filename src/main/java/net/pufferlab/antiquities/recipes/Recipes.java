package net.pufferlab.antiquities.recipes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.pufferlab.antiquities.Constants;
import net.pufferlab.antiquities.Registry;

public class Recipes extends RecipesHelper {

    public static Block[] woodBlocks = new Block[] { Blocks.log, Blocks.log2, null, null, Blocks.planks,
        Blocks.wooden_slab, null, Registry.chair, Registry.table, Registry.shelf_0, Registry.shelf_1, Registry.shelf_2,
        Registry.shelf_3, Registry.shelf_4, Registry.shelf_5, Registry.jar, Registry.rack };
    public static String[] woodTypes = Constants.woodTypes;
    public static String[] woodTypes2 = woodTypes;

    public Recipes() {
        super(woodBlocks, woodTypes, woodTypes2);
    }

    public void run() {
        for (String wood : Constants.woodTypes) {
            addRecipes(wood);
        }
        addSpecialRecipes();
    }
}
