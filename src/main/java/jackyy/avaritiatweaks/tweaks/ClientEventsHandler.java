package jackyy.avaritiatweaks.tweaks;

import jackyy.avaritiatweaks.config.ModConfig;
import jackyy.avaritiatweaks.util.ModUtils;
import morph.avaritia.item.tools.ItemSwordInfinity;
import morph.avaritia.util.TextUtils;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@SideOnly(Side.CLIENT)
public class ClientEventsHandler {

    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        Item item = stack.getItem();
        List<String> tooltip = event.getToolTip();
        if (ModUtils.isEnhanced(stack)) {
            tooltip.add(TextUtils.makeFabulous(I18n.format("tooltips.avaritiatweaks.enhanced")));
        }
        if (item instanceof ItemSwordInfinity && ModConfig.tweaks.fixInfinitySwordTooltip) {
            for (int x = 0; x < tooltip.size(); x++) {
                if (tooltip.get(x).contains(I18n.format("attribute.name.generic.attackDamage"))) {
                    tooltip.set(x, " " + TextUtils.makeFabulous(I18n.format("tooltips.avaritiatweaks.infinite")) + " "
                            + TextFormatting.GRAY + I18n.format("attribute.name.generic.attackDamage"));
                    return;
                }
            }
        }
    }

}
