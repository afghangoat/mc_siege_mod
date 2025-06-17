package hu.AfghanGoat.SiegeMod.Mobs;

import cpw.mods.fml.common.registry.EntityRegistry;
import hu.AfghanGoat.SiegeMod.Items.SiegeItems;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIFollowGolem;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class CatapultMob extends EntityCreature {
    public static int entityID;
    public static boolean is_ai;
    public CatapultMob(World world) {
        super(world);
        this.setSize(2.0F, 3.0F);
        preventEntitySpawning=true;
        this.tasks.addTask(0, new EntityAILookIdle(this));
        this.tasks.addTask(1, new EntityAITempt(this,1.0, Items.iron_ingot,false));
    }
    public boolean isAIEnabled(){
        return true;
    }
    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);

        if (!this.worldObj.isRemote) {

            this.dropItem(SiegeItems.catapultItem, 1);
        }
    }
    @Override
    public boolean interact(EntityPlayer player) {
        ItemStack heldItem = player.getHeldItem();
		if(heldItem==null){
			return false;
		}

        // TODO multiple bullets
        if (heldItem.getItem() == Item.getItemFromBlock(Blocks.tnt)) {
            launchTNT(player,80,1.2);

            if (!player.capabilities.isCreativeMode) {
                heldItem.stackSize--;

            }
            return true;
        } else if(heldItem.getItem()==Items.gunpowder){
            launchTNT(player,40,0.6);

            if (!player.capabilities.isCreativeMode) {
                heldItem.stackSize--;

            }
            return true;
        } else if(heldItem.getItem()==Items.coal){
            launchTNT(player,140,0.6);

            if (!player.capabilities.isCreativeMode) {
                heldItem.stackSize--;

            }
            return true;
        }
        return true;
    }
    private void launchTNT(EntityPlayer player,int fus,double vel) {
        player.playSound("block.chest.open", 10.0F, 0.0F);
        World world=player.getEntityWorld();
        if (!world.isRemote) {
            EntityTNTPrimed tnt = new EntityTNTPrimed(world, this.posX, this.posY, this.posZ, player);
            tnt.fuse=fus;
            tnt.setFire(1);


            // Set the direction of the TNT based on the player's look direction
            double directionX = -Math.sin(Math.toRadians(player.rotationYaw)) * Math.cos(Math.toRadians(player.rotationPitch));
            double directionZ = Math.cos(Math.toRadians(player.rotationYaw)) * Math.cos(Math.toRadians(player.rotationPitch));
            double directionY = -Math.sin(Math.toRadians(player.rotationPitch));

            tnt.motionX = directionX * vel;
            tnt.motionY = directionY * (0.3+vel);
            tnt.motionZ = directionZ * vel;

            // Spawn the TNT entity in the world
            world.spawnEntityInWorld(tnt);
        }
    }
   // entityID = EntityRegistry.findGlobalUniqueEntityId();
     //   EntityRegistry.registerGlobalEntityID(CatapultMob.class, "Catapult", entityID);

    // Override other behaviors if needed, like attack or AI behavior
}
