package hu.AfghanGoat.SiegeMod;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import hu.AfghanGoat.SiegeMod.Command.Command1;
import hu.AfghanGoat.SiegeMod.Items.SiegeRecipes;
import hu.AfghanGoat.SiegeMod.Mobs.CatapultMob;
import hu.AfghanGoat.SiegeMod.Proxy.ClientProxy;
import hu.AfghanGoat.SiegeMod.Proxy.CommonProxy;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import hu.AfghanGoat.SiegeMod.Items.SiegeItems;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import hu.AfghanGoat.SiegeMod.tabs.CTab;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

//gradle tab -> Tasks/forgegradle/runClient
//Build: Tasks/build/build
@Mod(modid = SiegeMod.MODID, version = SiegeMod.VERSION)
public class SiegeMod
{
    public static final String MODID = "siege_mod";
    public static final String VERSION = "1.0";

    public static Configuration config;
    public static boolean enwarp;

    public static CreativeTabs tabItems;

    @SidedProxy(clientSide = "hu.AfghanGoat.SiegeMod.Proxy.ClientProxy", serverSide = "hu.AfghanGoat.SiegeMod.Proxy.CommonProxy")
    public static CommonProxy proxy;
    public static int mob1_id;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        File configFile = new File(event.getModConfigurationDirectory(), "siege_mod/config.yml");
        config = new Configuration(configFile);
        loadConfig();
        CTab.init();

        mob1_id=0;
        EntityRegistry.registerModEntity(CatapultMob.class, "Catapult", mob1_id, this, 64, 1, true);
        proxy.registerRenderers();
        // Register the world tick handler
        //int entityID = EntityRegistry.findGlobalUniqueEntityId();
        //EntityRegistry.registerGlobalEntityID(EntityCubeMob.class, "CubeMob", entityID);
        //EntityRegistry.registerModEntity(EntityCubeMob.class, "CubeMob", entityID, this, 64, 1, true);

        // Register the spawn egg with color values (e.g., red and green)
        //EntityRegistry.registerEgg(EntityCubeMob.class, 0xFF0000, 0x00FF00);
    }
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// some example code
        SiegeItems.init();
        SiegeRecipes.init();
        cpw.mods.fml.common.FMLCommonHandler.instance().bus().register(new Command1());
        //System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
    }
    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new Command1());
    }
    private void loadConfig() {
        // Add a setting to enable or disable a tower feature
        enwarp = config.get("qol", "warpField", true, "Enable or disable the 'spectator' mode upon eating the sausage.").getBoolean();

        // Save the config if any changes were made
        if (config.hasChanged()) {
            config.save();
        }
    }

}
