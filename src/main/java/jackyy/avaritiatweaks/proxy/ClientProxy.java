package jackyy.avaritiatweaks.proxy;

import jackyy.avaritiatweaks.client.Keys;
import jackyy.avaritiatweaks.tweaks.ModTweaks;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onModelRegistry(ModelRegistryEvent e) {
        ModTweaks.initModels();
        ModTweaks.initIntegrationsClient();
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
        Keys.init();
	}

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }

}
