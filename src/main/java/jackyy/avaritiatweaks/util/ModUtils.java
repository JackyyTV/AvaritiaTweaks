package jackyy.avaritiatweaks.util;

import jackyy.avaritiatweaks.tweaks.ModTweaks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModUtils {

    public static ItemStack getStackFromName(String name, int amount, int meta) {
        Item item = Item.REGISTRY.getObject(new ResourceLocation(name));
        ItemStack stack = ItemStack.EMPTY;
        if (item != null) {
            stack = new ItemStack(item, amount, meta);
        }
        return stack;
    }

    public static ItemStack getFlower(String type) {
        ItemStack flower = getStackFromName("botania:specialflower", 1, 0);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("type", type);
        flower.setTagCompound(tag);
        return flower;
    }

    public static ItemStack getInfPick(ItemStack stack) {
        stack.addEnchantment(Enchantments.FORTUNE, 10);
        return stack;
    }

    public static void addEnhancementArmorRecipe(ItemStack input, ItemStack output) {
        NBTTagCompound enhancedTag = new NBTTagCompound();
        enhancedTag.setInteger("enhanced", 1);
        if (output.getTagCompound() == null) {
            output.setTagCompound(enhancedTag);
        } else {
            output.getTagCompound().setInteger("enhanced", 1);
        }
        GameRegistry.addShapelessRecipe(output, input, ModTweaks.enhancementCrystal);
        GameRegistry.addShapelessRecipe(input, output);
    }

    public static void addEnhancementToolsRecipe(ItemStack input, ItemStack output, String[] enchants) {
        NBTTagCompound enhancedTag = new NBTTagCompound();
        enhancedTag.setInteger("enhanced", 1);
        if (output.getTagCompound() == null) {
            output.setTagCompound(enhancedTag);
        } else {
            output.getTagCompound().setInteger("enhanced", 1);
        }
        for (String enchant : enchants) {
            String[] enchantmentAndLevel = enchant.split("@", 2);
            Enchantment enchantment = Enchantment.getEnchantmentByLocation(enchantmentAndLevel[0]);
            int level = Integer.parseInt(enchantmentAndLevel[1]);
            if (enchantment != null) {
                output.addEnchantment(enchantment, level);
            }
        }
        GameRegistry.addShapelessRecipe(output, input, ModTweaks.enhancementCrystal);
        GameRegistry.addShapelessRecipe(input, output);
    }

}
