package hu.AfghanGoat.SiegeMod.tabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import hu.AfghanGoat.SiegeMod.Items.SiegeItems;
import hu.AfghanGoat.SiegeMod.SiegeMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CTab {


    public static void init(){
        SiegeMod.tabItems = new CreativeTabs("siegeTab") {
            @Override
            @SideOnly(Side.CLIENT)
            public Item getTabIconItem() {
                return SiegeItems.sausage;
            }
        };
    }

}