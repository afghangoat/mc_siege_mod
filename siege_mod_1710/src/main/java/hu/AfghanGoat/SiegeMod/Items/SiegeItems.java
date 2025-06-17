package hu.AfghanGoat.SiegeMod.Items;

import hu.AfghanGoat.SiegeMod.SiegeMod;
import net.minecraft.item.Item;

public final class SiegeItems {
    public static Item catapultItem;
    public static Item sausage;

    public static void init() {
        //testItem = new TestItem(ExampleMod.tabBlocks);
        sausage = new FoodItem( SiegeMod.tabItems,4, 0.5f, true);
        catapultItem=new CatapultItem(SiegeMod.tabItems);
    }
}
