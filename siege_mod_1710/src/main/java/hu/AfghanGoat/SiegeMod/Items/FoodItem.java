package hu.AfghanGoat.SiegeMod.Items;

//import com.keller23.mc17.ExampleMod.creativetabs.CTabs;
//import com.keller23.mc17.ExampleMod.lib.Constants;
import cpw.mods.fml.common.registry.GameRegistry;
import hu.AfghanGoat.SiegeMod.AGLib;
//import net.minecraft.creativetab.CreativeTabs;
import hu.AfghanGoat.SiegeMod.SiegeMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.item.ItemFood;

public class FoodItem extends ItemFood {

    public String name = "sausage";
    public int customDamage=10;

    public FoodItem(CreativeTabs creativeTabs, int heal, float saturation, boolean wolfMeat){
        super(heal, saturation, wolfMeat);
        //TODO set item damage to 10
        setUnlocalizedName(AGLib.ModID  + "_" + name);
        setTextureName(SiegeMod.MODID+":"+name);
        setCreativeTab(creativeTabs);
        GameRegistry.registerItem(this, name);
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
        /*if (world.isRemote) {

            //player.attackEntityFrom(DamageSource.generic, customDamage);
           // player.eyeHeight=1.1f;

        } else {
            player.dimension=1;
        }

            MinecraftServer server = MinecraftServer.getServer();
            server.getCommandManager().executeCommand(server, "opCommand"); // Replace "opCommand" with your command

        player.boundingBox.expand(0.5F, 1.5F, 0.5F);
        player.eyeHeight= 4.0F;
         */

        if (!world.isRemote) {
            player.addChatMessage(new ChatComponentText("You are going to Csömör! (Warp field stabilized)"));

        }

        spawnCritParticles(world, player);
        player.playSound("portal.travel", 10.0F, 10.0F);
        player.height=5.0f;
        if(SiegeMod.enwarp==true){
            player.dimension=1;
        }

        return super.onEaten(stack, world, player);
    }

    private void spawnCritParticles(World world, EntityPlayer player) {
        double posX = player.posX;
        double posY = player.posY + player.height / 2.0; // Center of the player's height
        double posZ = player.posZ;
        int crit_amm=20;
        double crit_speed=0.1;
        double crit_off=0.5;
        for (int i = 0; i < crit_amm; i++) { // Adjust for the number of particles
            double offsetX = (world.rand.nextDouble() - crit_off) * crit_off;
            double offsetY = (world.rand.nextDouble() - crit_off) * crit_off;
            double offsetZ = (world.rand.nextDouble() - crit_off) * crit_off;
            double speedX = (world.rand.nextDouble() - crit_off) * crit_speed;
            double speedY = (world.rand.nextDouble() - crit_off) * crit_speed;
            double speedZ = (world.rand.nextDouble() - crit_off) * crit_speed;

            world.spawnParticle("crit", posX + offsetX, posY + offsetY, posZ + offsetZ, speedX, speedY, speedZ);
        }
    }
}