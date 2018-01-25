package jackyy.avaritiatweaks.config;

import jackyy.avaritiatweaks.AvaritiaTweaks;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = AvaritiaTweaks.MODID, name = "AvaritiaTweaks")
public class ModConfig {

    public static Tweaks tweaks = new Tweaks();
    public static InfinityArmor infinityArmor = new InfinityArmor();

    public static class Tweaks {
        @Config.Comment("If true, enables unbreakable blocks mining with the World Breaker")
        public boolean makeWorldBreakerGreatAgain = true;
        @Config.Comment("If true, fixes the tooltip on Sword of the Cosmos so it matches with the Vanilla 1.9+ tooltips")
        public boolean fixInfinitySwordTooltip = true;
    }

    public static class InfinityArmor {
        @Config.Comment("Put a list of Potion registery names you would like to have when wearing Infinity Helmet")
        public String[] infinityHelmetPotionEffects = new String[] {"minecraft:night_vision"};
        @Config.Comment("Put a list of Potion registery names you would like to have when wearing Infinity Chestplate")
        public String[] infinityChestplatePotionEffects = new String[0];
        @Config.Comment("Put a list of Potion registery names you would like to have when wearing Infinity Leggings")
        public String[] infinityLeggingsPotionEffects = new String[0];
        @Config.Comment("Put a list of Potion registery names you would like to have when wearing Infinity Boots")
        public String[] infinityBootsPotionEffects = new String[0];
        @Config.Comment("If true, enables No Clip mode for Infinity Armor")
        public boolean infinityArmorNoClip = true;
    }

    @Mod.EventBusSubscriber
    public static class ConfigHolder {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(AvaritiaTweaks.MODID)) {
                ConfigManager.load(AvaritiaTweaks.MODID, Config.Type.INSTANCE);
            }
        }
    }

}
