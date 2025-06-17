package hu.AfghanGoat.SiegeMod.Items;


import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class SiegeRecipes {
    public static void init() {
        GameRegistry.addShapedRecipe(new ItemStack(SiegeItems.sausage),
                ".C.", // 3 in a row
                ".C.", // 1 in the middle
                'C', new ItemStack(Items.porkchop));
        GameRegistry.addShapedRecipe(new ItemStack(SiegeItems.catapultItem),
                "CCC", // 3 in a row
                ".C.", // 1 in the middle
                'C', new ItemStack(Items.fishing_rod));
    }

}