package jackyy.avaritiatweaks.util;

import jackyy.avaritiatweaks.tweaks.ModTweaks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Reference {

    public static final String MODID = "avaritiatweaks";
    public static final String MODNAME = "Avaritia Tweaks";
    public static final String VERSION = "1.12.2-1.3.1";
    public static final String MCVERSION = "[1.12,1.13)";

    public static final String BOTANIA = "botania";

    public static final String DEPENDS
            = "required-after:avaritia;"
            + "required-after:codechickenlib;"
            + "required-after:gunpowderlib@[1.12.2-1.1,);"
            + "after:" + BOTANIA + ";";

    public static final CreativeTabs TAB = new CreativeTabs(MODID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModTweaks.enhancementCrystal);
        }
    };

    public static final Logger LOGGER = LogManager.getLogger(MODNAME);

    public static final String COMMON_PROXY = "jackyy.avaritiatweaks.proxy.CommonProxy";
    public static final String CLIENT_PROXY = "jackyy.avaritiatweaks.proxy.ClientProxy";

}
