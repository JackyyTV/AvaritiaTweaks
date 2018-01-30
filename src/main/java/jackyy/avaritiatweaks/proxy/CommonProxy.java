package jackyy.avaritiatweaks.proxy;

import jackyy.avaritiatweaks.AvaritiaTweaks;
import jackyy.avaritiatweaks.packet.PacketHandler;
import jackyy.avaritiatweaks.tweaks.ModEventsHandler;
import jackyy.avaritiatweaks.tweaks.ModTweaks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        PacketHandler.registerMessages(AvaritiaTweaks.MODID);
        ModTweaks.initItems();
        ModTweaks.initRecipes();
        ModTweaks.preInitIntegrations();
        MinecraftForge.EVENT_BUS.register(new ModEventsHandler());
    }

    public void init(FMLInitializationEvent event) {
        ModTweaks.initIntegrations();
    }

    public void postInit(FMLPostInitializationEvent event) {
    }

}
