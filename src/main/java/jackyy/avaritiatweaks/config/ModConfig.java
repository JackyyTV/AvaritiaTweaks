package jackyy.avaritiatweaks.config;

import jackyy.avaritiatweaks.AvaritiaTweaks;
import jackyy.avaritiatweaks.util.ModUtils;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;
import java.lang.invoke.MethodHandle;
import java.util.Map;
import java.util.Optional;

@Config(modid = AvaritiaTweaks.MODID, name = "AvaritiaTweaks")
public class ModConfig {

    public static Tweaks tweaks = new Tweaks();
    public static Compats compats = new Compats();
    public static InfinityArmor infinityArmor = new InfinityArmor();
    public static InfinityTools infinityTools = new InfinityTools();

    public static class Tweaks {
        @Config.Comment("If true, enables unbreakable blocks mining with the World Breaker")
        public boolean makeWorldBreakerGreatAgain = true;
        @Config.Comment("If true, fixes the tooltip on Sword of the Cosmos so it matches with the Vanilla 1.9+ tooltips")
        public boolean fixInfinitySwordTooltip = true;
        @Config.Comment({
                "If true, enables the Enhancement Crystal,",
                "which allows it to be crafted with Infinity Tools / Armor to get special capabilities",
                "e.g. Enchantments, Potion Effects, etc."
        })
        public boolean enableEnhancementCrystal = true;
    }

    public static class Compats {
        @Config.Comment("If true, enables JEI compat")
        public boolean jeiCompat = true;
        public static JEI jei = new JEI();
        public static class JEI {
            @Config.Comment("If true, enables Neutronium Compressor recipes integration on JEI")
            public boolean neutroniumCompressor = true;
            @Config.Comment("If true, provides descriptions for some items on JEI")
            public boolean descriptions = true;
        }
        @Config.Comment("If true, enables Botania compat")
        public boolean botaniaCompat = true;
        public static Botania botania = new Botania();
        public static class Botania {
            @Config.Comment("If true, enables Gaia Block")
            public boolean gaiaBlock = true;
            @Config.Comment("If true, enables Asgardandelion")
            public boolean asgardandelion = true;
            @Config.Comment("If true, enables Soarleander")
            public boolean soarleander = true;
        }
    }

    public static class InfinityArmor {
        @Config.Comment({
                "Put a list of Potion registery names you would like to have when wearing Infinity Helmet",
                "Requires Enhancement Crystal to be installed on Infinity Helmet"
        })
        public String[] infinityHelmetPotionEffects = new String[] {"minecraft:night_vision"};
        @Config.Comment({
                "Put a list of Potion registery names you would like to have when wearing Infinity Chestplate",
                "Requires Enhancement Crystal to be installed on Infinity Chestplate"
        })
        public String[] infinityChestplatePotionEffects = new String[0];
        @Config.Comment({
                "Put a list of Potion registery names you would like to have when wearing Infinity Leggings",
                "Requires Enhancement Crystal to be installed on Infinity Leggings"
        })
        public String[] infinityLeggingsPotionEffects = new String[0];
        @Config.Comment({
                "Put a list of Potion registery names you would like to have when wearing Infinity Boots",
                "Requires Enhancement Crystal to be installed on Infinity Boots"
        })
        public String[] infinityBootsPotionEffects = new String[0];
        @Config.Comment({
                "If true, enables No Clip mode for Infinity Armor",
                "Requires Enhancement Crystal to be installed on Infinity Chestplate"
        })
        public boolean infinityArmorNoClip = true;
    }

    public static class InfinityTools {
        @Config.Comment({
                "Put a list of Enchantment registery names + Enchantment level you would like to have on Sword of the Cosmos",
                "Requires Enhancement Crystal to be installed on Sword of the Cosmos",
                "Example: minecraft:unbreaking@100"
        })
        public String[] infinitySwordEnchantments = new String[] {"minecraft:looting@10"};
        @Config.Comment({
                "Put a list of Enchantment registery names + Enchantment level you would like to have on World Breaker",
                "Requires Enhancement Crystal to be installed on World Breaker",
                "Example: minecraft:unbreaking@100"
        })
        public String[] infinityPickaxeEnchantments = new String[0];
        @Config.Comment({
                "Put a list of Enchantment registery names + Enchantment level you would like to have on Nature's Ruin",
                "Requires Enhancement Crystal to be installed on Nature's Ruin",
                "Example: minecraft:unbreaking@100"
        })
        public String[] infinityAxeEnchantments = new String[0];
        @Config.Comment({
                "Put a list of Enchantment registery names + Enchantment level you would like to have on Planet Eater",
                "Requires Enhancement Crystal to be installed on Planet Eater",
                "Example: minecraft:unbreaking@100"
        })
        public String[] infinityShovelEnchantments = new String[0];
        @Config.Comment({
                "Put a list of Enchantment registery names + Enchantment level you would like to have on Longbow of the Heavens",
                "Requires Enhancement Crystal to be installed on Longbow of the Heavens",
                "Example: minecraft:unbreaking@100"
        })
        public String[] infinityBowEnchantments = new String[0];
    }

    @Mod.EventBusSubscriber
    public static class ConfigHolder {
        private static final MethodHandle CONFIGS_GETTER = ModUtils.findFieldGetter(ConfigManager.class, "CONFIGS");
        private static Configuration config;
        @SuppressWarnings("unchecked")
        public static Configuration getConfig() {
            if (config == null) {
                try {
                    final String fileName = "AvaritiaTweaks.cfg";
                    final Map<String, Configuration> configsMap = (Map<String, Configuration>) CONFIGS_GETTER.invokeExact();
                    final Optional<Map.Entry<String, Configuration>> entryOptional = configsMap.entrySet().stream()
                            .filter(entry -> fileName.equals(new File(entry.getKey()).getName())).findFirst();
                    entryOptional.ifPresent(stringConfigurationEntry -> config = stringConfigurationEntry.getValue());
                } catch (Throwable throwable) {
                    AvaritiaTweaks.logger.error("Failed to get Configuration instance!", throwable);
                }
            }
            return config;
        }
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(AvaritiaTweaks.MODID)) {
                ConfigManager.load(AvaritiaTweaks.MODID, Config.Type.INSTANCE);
            }
        }
    }

}
