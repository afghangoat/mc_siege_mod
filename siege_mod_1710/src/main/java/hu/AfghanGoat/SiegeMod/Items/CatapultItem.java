package hu.AfghanGoat.SiegeMod.Items;

//import com.keller23.mc17.ExampleMod.creativetabs.CTabs;
//import com.keller23.mc17.ExampleMod.lib.Constants;
import cpw.mods.fml.common.registry.GameRegistry;
import hu.AfghanGoat.SiegeMod.AGLib;
//import net.minecraft.creativetab.CreativeTabs;
import hu.AfghanGoat.SiegeMod.Mobs.CatapultMob;
import hu.AfghanGoat.SiegeMod.SiegeMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;

public class CatapultItem extends Item {

    public String name = "catapultItem";
    public int customDamage=10;

    public CatapultItem(CreativeTabs creativeTabs){
        setUnlocalizedName(AGLib.ModID  + "_" + name);
        setTextureName(SiegeMod.MODID+":"+name);
        GameRegistry.registerItem(this, name);
        setCreativeTab(creativeTabs);
    }
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            // Adjust position based on side clicked
            x += side == 5 ? 1 : side == 4 ? -1 : 0;
            y += side == 1 ? 1 : side == 0 ? -1 : 0;
            z += side == 3 ? 1 : side == 2 ? -1 : 0;

            // Spawn the entity in the world
            CatapultMob entity = new CatapultMob(world);
            entity.setPosition(x + 0.5, y + 1, z + 0.5);
            world.spawnEntityInWorld(entity);

            // Optionally reduce item stack if not in creative mode
            if (!player.capabilities.isCreativeMode) {
                stack.stackSize--;
            }
        }
        return true;
    }
    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        super.onCreated(stack, world, player);

        // Adding NBT lore
        NBTTagCompound display = new NBTTagCompound();
        NBTTagList lore = new NBTTagList();
        lore.appendTag(new NBTTagString("Can be placed and right-clicked with:"));
        lore.appendTag(new NBTTagString("Tnt,gunpowder,coal."));
        display.setTag("Lore", lore);
        stack.setTagInfo("display", display);
    }
}
