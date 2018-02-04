package jackyy.avaritiatweaks;

import jackyy.avaritiatweaks.proxy.CommonProxy;
import jackyy.avaritiatweaks.tweaks.ModTweaks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = AvaritiaTweaks.MODID, version = AvaritiaTweaks.VERSION, name = AvaritiaTweaks.MODNAME, dependencies = AvaritiaTweaks.DEPENDS, acceptedMinecraftVersions = AvaritiaTweaks.MCVERSION, guiFactory = AvaritiaTweaks.GUIFACTORY, certificateFingerprint = "@FINGERPRINT@", useMetadata = true)
public class AvaritiaTweaks {

    public static final String MODID = "avaritiatweaks";
    public static final String MODNAME = "Avaritia Tweaks";
    public static final String VERSION = "1.10.2-1.1";
    public static final String MCVERSION = "[1.10,1.11)";
    public static final String DEPENDS = "required-after:avaritia;required-after:CodeChickenLib;after:Botania;";
    public static final String GUIFACTORY = "jackyy.avaritiatweaks.config.ConfigGuiFactory";
    public static final String COMMON_PROXY = "jackyy.avaritiatweaks.proxy.CommonProxy";
    public static final String CLIENT_PROXY = "jackyy.avaritiatweaks.proxy.ClientProxy";
    public static final CreativeTabs TAB = new CreativeTabs(MODID) {
        @Override
        public Item getTabIconItem() {
            return ModTweaks.enhancementCrystal;
        }
    };

    public static Logger logger = LogManager.getLogger(MODNAME);

    @SidedProxy(serverSide = AvaritiaTweaks.COMMON_PROXY, clientSide = AvaritiaTweaks.CLIENT_PROXY)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void onFingerprintViolation(FMLFingerprintViolationEvent event) {
        logger.warn("Invalid fingerprint detected! The file " + event.getSource().getName() + " may have been modified. This will NOT be supported by the mod author!");
    }

}
