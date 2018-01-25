package jackyy.avaritiatweaks.proxy;

import jackyy.avaritiatweaks.AvaritiaTweaks;
import jackyy.avaritiatweaks.packet.PacketHandler;
import jackyy.avaritiatweaks.tweaks.ModEventsHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class CommonProxy {

	public static Configuration config;

	public void preInit(FMLPreInitializationEvent event) {
		File configDir = event.getModConfigurationDirectory();
		config = new Configuration(new File(configDir.getPath(), "AvaritiaTweaks.cfg"));
        PacketHandler.registerMessages(AvaritiaTweaks.MODID);
        MinecraftForge.EVENT_BUS.register(new ModEventsHandler());
	}

	public void init(FMLInitializationEvent event) {
	}

	public void postInit(FMLPostInitializationEvent event) {
	}

}
