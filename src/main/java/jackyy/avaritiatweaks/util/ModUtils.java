package jackyy.avaritiatweaks.util;

import jackyy.avaritiatweaks.tweaks.ModTweaks;
import morph.avaritia.item.ItemArmorInfinity;
import morph.avaritia.item.tools.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModUtils {

    public static ItemStack getEnchantedItem(ItemStack stack, Enchantment enchantment, int level) {
        stack.addEnchantment(enchantment, level);
        return stack;
    }

    public static void addEnhancementArmorRecipe(String name, ItemStack input, ItemStack output) {
        NBTTagCompound enhancedTag = new NBTTagCompound();
        enhancedTag.setInteger("enhanced", 1);
        if (output.getTagCompound() == null) {
            output.setTagCompound(enhancedTag);
        } else {
            output.getTagCompound().setInteger("enhanced", 1);
        }
        GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, name + "_upgrade"), null, output, Ingredient.fromStacks(input), Ingredient.fromStacks(new ItemStack(ModTweaks.enhancementCrystal)));
        GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, name + "_remove"), null, input, Ingredient.fromStacks(output));
    }

    public static void addEnhancementToolsRecipe(String name, ItemStack input, ItemStack output, String[] enchants) {
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
        GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, name + "_upgrade"), null, output, Ingredient.fromStacks(input), Ingredient.fromStacks(new ItemStack(ModTweaks.enhancementCrystal)));
        GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, name + "_remove"), null, input, Ingredient.fromStacks(output));
    }

    public static boolean isArmorValid(EntityPlayer player, EntityEquipmentSlot slot) {
        ItemStack armor = player.getItemStackFromSlot(slot);
        return !armor.isEmpty() && armor.getItem() instanceof ItemArmorInfinity
                && armor.getTagCompound() != null && armor.getTagCompound().getInteger("enhanced") == 1;
    }

    public static boolean isEnhanced(ItemStack stack) {
        return !stack.isEmpty() && (stack.getItem() instanceof ItemArmorInfinity
                || stack.getItem() instanceof ItemSwordInfinity
                || stack.getItem() instanceof ItemPickaxeInfinity
                || stack.getItem() instanceof ItemAxeInfinity
                || stack.getItem() instanceof ItemShovelInfinity
                || stack.getItem() instanceof ItemBowInfinity)
                && stack.getTagCompound() != null && stack.getTagCompound().getInteger("enhanced") == 1;
    }

    public static void checkAndAddEffect(EntityPlayer player, String[] potions) {
        for (String potion : potions) {
            Potion effect = Potion.getPotionFromResourceLocation(potion);
            if (effect != null) {
                player.addPotionEffect(new PotionEffect(effect, 300, 0, false, false));
            }
        }
    }

}
