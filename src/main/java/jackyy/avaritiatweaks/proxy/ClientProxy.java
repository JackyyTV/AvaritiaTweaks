package jackyy.avaritiatweaks.proxy;

import jackyy.avaritiatweaks.client.Keys;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
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
