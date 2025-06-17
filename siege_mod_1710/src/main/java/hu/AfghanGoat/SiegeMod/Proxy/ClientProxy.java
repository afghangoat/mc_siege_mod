package hu.AfghanGoat.SiegeMod.Proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import hu.AfghanGoat.SiegeMod.Mobs.CatapultMob;
import hu.AfghanGoat.SiegeMod.Mobs.ModelCatapult;
import hu.AfghanGoat.SiegeMod.Mobs.RenderCatapult;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(CatapultMob.class, new RenderCatapult());
    }
}
