package hu.AfghanGoat.SiegeMod.Command;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.Potion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Command1 extends CommandBase {

    Random rand = new Random();

    private final List<String> aliases;

    public Command1() {
        aliases = new ArrayList<String>();
        aliases.add("sex");
    }

    @Override
    public String getCommandName() {
        return "sex";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/sex <playername>";
    }

    @Override
    public List<String> getCommandAliases() {
        return aliases;
    }
    public static int isSex=0;
    private static int globalState=0;

    private final static int act_length=20;

    private static double g_posX=0.0;
    private static double g_posY=0.0;
    private static double g_posZ=0.0;
    private final static double g_radius_sq=25;
    private boolean check_dist(double x1, double y1, double z1){
        double dx=x1-g_posX;
        double dy=y1-g_posY;
        double dz=z1-g_posZ;
        if(dx*dx+dy*dy+dz*dz<g_radius_sq){
            return true;
        }
        return false;
    }

    public void beginSex(double xx, double yy, double zz){
        g_posX=xx;
        g_posY=yy;
        g_posZ=zz;
        globalState=0;
        isSex=1;
        tickCounter=0;
        odd_players=0;
    }

    public int odd_players=0;
    private int tickCounter = 0; // Counter for ticks

    @SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            tickCounter++; // Increment the tick counter

            // Check if one second (20 ticks) has passed
            if (tickCounter >= act_length&&isSex==1) {
                tickCounter = 0; // Reset the counter

                odd_players++;

                // Loop through all players and send them a message
                for (Object player : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
                    EntityPlayerMP curMP = (EntityPlayerMP) player;
                    if(check_dist(curMP.posX,curMP.posY,curMP.posZ)==true){
                        try_act(curMP);
                        int rint = rand.nextInt(3);
                        float t_pitch=1.0f;
                        String sound;
                        switch (rint) {
                            case 0:
                                sound = "mob.ghast.scream";
                                t_pitch=2.0f;
                                break;
                            case 1:
                                sound = "mob.ghast.death";
                                t_pitch=2.0f;
                                break;
                            case 2:
                                sound = "random.pop";
                                t_pitch=2.0f;
                                break;
                            default:
                                sound = "random.eat";
                                t_pitch=0.0f;
                        }

                        // Play sound for each player at their current position
                        event.world.playSoundAtEntity(curMP, sound, 10.0F, t_pitch);
                        //curMP.addChatMessage(new ChatComponentText("Hello,  This message is sent every second."));
                    }
                }
                if(globalState>=act_length){
                    isSex = 0;
                } else {
                    globalState++;
                }
            }
        }
    }

    public void try_act(EntityPlayerMP player) {

        if (globalState < act_length) {
            player.addChatMessage(new ChatComponentText(" "));
            if (odd_players % 2 == 0) {
                player.setPositionAndUpdate(g_posX, g_posY + 0.5, g_posZ);
                player.setSneaking(true);
            } else {
                player.setSneaking(false);
                ((EntityPlayer) player).sleepInBedAt((int) g_posX, (int) g_posY, (int) g_posZ);
            }
        } else {
            player.setPositionAndUpdate(g_posX, g_posY + 0.5, g_posZ);
            int rint = rand.nextInt(14);
            switch(rint){
                case 0:
                    player.addChatMessage(new ChatComponentText("Oh no, you caught tripper!"));
                    PotionEffect eff = new PotionEffect(Potion.poison.id, 20000, 100);
                    player.addPotionEffect(eff);
                    break;

                case 1:
                    player.addChatMessage(new ChatComponentText("Oh no, you caught AIDS!"));
                    PotionEffect eff2 = new PotionEffect(Potion.wither.id, 20000, 100);
                    player.addPotionEffect(eff2);
                    break;

                case 2:
                    player.addChatMessage(new ChatComponentText("Oh no, you hurt yourself during sex!"));
                    PotionEffect eff3 = new PotionEffect(Potion.weakness.id, 20000, 100);
                    player.addPotionEffect(eff3);
                    break;

                case 3:
                    player.addChatMessage(new ChatComponentText("Oh no, you caught syphilis!"));
                    PotionEffect eff4 = new PotionEffect(Potion.hunger.id, 20000, 100);
                    player.addPotionEffect(eff4);
                    break;
            }

        }
        odd_players++;
    }

    private Entity findNearestMob(World world, EntityPlayerMP player) {
        Entity nearestMob = null;
        double nearestDistance = Double.MAX_VALUE;

        for (Object obj : world.loadedEntityList) {
            if (obj instanceof Entity) {
                Entity entity = (Entity) obj;
                if (entity instanceof EntityLiving && entity != player) {
                    double distance = entity.getDistanceToEntity(player);
                    if (distance < nearestDistance) {
                        nearestDistance = distance;
                        nearestMob = entity;
                    }
                }
            }
        }
        return nearestMob;
    }
    public void beginActPlayer(EntityPlayerMP player){
        ((EntityPlayer) player).sleepInBedAt((int)player.posX,(int)(player.posY+0.5),(int)player.posZ);

    }
    public void applySlowness(Entity entity) {
        // Create a new PotionEffect for Slowness
        // Potion ID for Slowness is 2
        // Duration is specified in ticks (10 seconds = 200 ticks)
        // Amplifier is 100 (this is the level of the effect)
        PotionEffect slownessEffect = new PotionEffect(Potion.moveSlowdown.id, 200, 100);

        if (entity instanceof EntityPlayerMP) {
            ((EntityPlayerMP) entity).addPotionEffect(slownessEffect);
        } else if (entity instanceof EntityLivingBase) {
            ((EntityLivingBase) entity).addPotionEffect(slownessEffect); // For other living entities
        }
    }
    public void beginActEntity(Entity poor_mob){
        applySlowness(poor_mob);

        poor_mob.setFire(20);
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (!(sender instanceof EntityPlayerMP)) {
            sender.addChatMessage(new ChatComponentText("This command can only be used by players."));
            return;
        }
        if(isSex==1){
            sender.addChatMessage(new ChatComponentText("There is already a sex going on!"));
            return;
        }
        EntityPlayerMP executingPlayer = (EntityPlayerMP) sender;
        if (executingPlayer.worldObj.isDaytime()) {
            sender.addChatMessage(new ChatComponentText("You can only sex at night!"));
            return;
        }
        World world = executingPlayer.worldObj;

        if (args.length == 1) {

            EntityPlayerMP targetPlayer = getPlayer(sender, args[0]);
            if (targetPlayer == null) {
                sender.addChatMessage(new ChatComponentText("Player not found: " + args[0]));
                return;
            }
            g_posX=targetPlayer.posX;
            g_posY=targetPlayer.posY;
            g_posZ=targetPlayer.posZ;
            if (check_dist(executingPlayer.posX, executingPlayer.posY, executingPlayer.posZ)==false){
                sender.addChatMessage(new ChatComponentText("Player not is too far to start sex! "));
                return;
            }
            targetPlayer.setPositionAndUpdate(executingPlayer.posX, executingPlayer.posY, executingPlayer.posZ);
            //targetPlayer és executingPlayer
            beginActPlayer(executingPlayer);
            beginActPlayer(targetPlayer);

            beginSex(executingPlayer.posX,executingPlayer.posY,executingPlayer.posZ);
        } else {

            Entity nearestMob = findNearestMob(world, executingPlayer);
            if (nearestMob != null) {
                nearestMob.setPosition(executingPlayer.posX+0.5, executingPlayer.posY, executingPlayer.posZ+0.5);
            } else {
                sender.addChatMessage(new ChatComponentText("You can't have sex alone!"));
                return;
            }

            executingPlayer.sleepInBedAt((int)executingPlayer.posX,(int)executingPlayer.posY,(int)executingPlayer.posZ);

            beginActPlayer(executingPlayer);
            beginActEntity(nearestMob);

            beginSex(executingPlayer.posX,executingPlayer.posY,executingPlayer.posZ);
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return sender instanceof EntityPlayerMP; // Only players can use this command
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
        return args.length == 1 ? getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames()) : null;
    }
}
