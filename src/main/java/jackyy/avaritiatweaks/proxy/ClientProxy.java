package jackyy.avaritiatweaks.proxy;

import jackyy.avaritiatweaks.tweaks.ModTweaks;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        ModTweaks.initModels();
        ModTweaks.initIntegrationsClient();
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
	}

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }

}
