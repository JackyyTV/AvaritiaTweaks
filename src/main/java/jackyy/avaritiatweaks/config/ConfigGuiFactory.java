package jackyy.avaritiatweaks.config;

import jackyy.avaritiatweaks.AvaritiaTweaks;
import jackyy.avaritiatweaks.proxy.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ConfigGuiFactory implements IModGuiFactory {

    @Override
    public void initialize(Minecraft minecraftInstance) {
    }

    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass() {
        return ConfigGui.class;
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }

    @Override @SuppressWarnings("deprecation")
    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
        return null;
    }

    public static class ConfigGui extends GuiConfig {
        public ConfigGui(GuiScreen parentScreen) {
            super(parentScreen, getConfigElements(), AvaritiaTweaks.MODID, false,
                    false, GuiConfig.getAbridgedConfigPath(CommonProxy.config.toString()));
        }

        @Override
        public void onGuiClosed() {
            super.onGuiClosed();
            CommonProxy.config.save();
        }

        private static List<IConfigElement> getConfigElements() {
            List<IConfigElement> list = new ArrayList<>();
            Configuration cfg = CommonProxy.config;
            list.add(new ConfigElement(cfg.getCategory("general")));
            return list;
        }
    }

}
