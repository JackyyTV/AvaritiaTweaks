package jackyy.avaritiatweaks.config;

import jackyy.avaritiatweaks.util.ModUtils;
import jackyy.avaritiatweaks.util.Reference;
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

@Config(modid = Reference.MODID, name = "AvaritiaTweaks")
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
            @Config.Comment("If true, enables Infinitato")
            public boolean infinitato = true;
            public static Asgardandelion asgardandelion = new Asgardandelion();
            public static class Asgardandelion {
                @Config.Comment("If true, enables Asgardandelion")
                public boolean enabled = true;
                @Config.Comment("Set the max mana that can be stored by Asgardandelion")
                public int maxMana = 1000000;
                @Config.Comment("Set the amount of mana generated during each generation cycle for Asgardandelion")
                public int generationCycleMana = 1000000;
                @Config.Comment("Set the delay in ticks between each generation cycle for Asgardandelion")
                public int generationCycleDelay = 1;
            }
            public static Soarleander soarleander = new Soarleander();
            public static class Soarleander {
                @Config.Comment("If true, enables Soarleander")
                public boolean enabled = true;
                @Config.Comment("Set the max mana that can be stored by Soarleander")
                public int maxMana = 1000000;
                @Config.Comment("Set the amount of mana generated during each generation cycle for Soarleander")
                public int generationCycleMana = 100;
                @Config.Comment("Set the delay in ticks between each generation cycle for Soarleander")
                public int generationCycleDelay = 20;
            }
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
                    Reference.LOGGER.error("Failed to get Configuration instance!", throwable);
                }
            }
            return config;
        }
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(Reference.MODID)) {
                ConfigManager.load(Reference.MODID, Config.Type.INSTANCE);
            }
        }
    }

}
