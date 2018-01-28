package jackyy.avaritiatweaks.tweaks;

import codechicken.lib.model.ModelRegistryHelper;
import codechicken.lib.util.TransformUtils;
import jackyy.avaritiatweaks.config.ModConfig;
import jackyy.avaritiatweaks.item.ItemEnhancementCrystal;
import morph.avaritia.client.render.item.HaloRenderItem;
import morph.avaritia.init.ModItems;
import morph.avaritia.recipe.extreme.ExtremeCraftingManager;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModTweaks {

    public static ItemEnhancementCrystal enhancementCrystal = new ItemEnhancementCrystal();

    public static void initItems() {
        GameRegistry.register(enhancementCrystal);
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        ModelResourceLocation location = new ModelResourceLocation(enhancementCrystal.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(enhancementCrystal, 0, location);
        IBakedModel wrapped = new HaloRenderItem(TransformUtils.DEFAULT_ITEM, (modelRegistry) -> modelRegistry.getObject(location));
        ModelRegistryHelper.register(location, wrapped);
    }

    public static void initRecipes() {
        if (ModConfig.tweaks.enableEnhancementCrystal) {
            ExtremeCraftingManager.getInstance().addRecipe(
                    new ItemStack(enhancementCrystal),
                    "    N    ",
                    "   NCN   ",
                    "  NCCCN  ",
                    "  NICIN  ",
                    "  NCICN  ",
                    "  NICIN  ",
                    "  NCCCN  ",
                    "   NCN   ",
                    "    N    ",
                    'N', ModItems.neutronium_ingot, 'C', ModItems.crystal_matrix_ingot, 'I', ModItems.infinity_catalyst
            );
            addEnhancementArmorRecipe(new ItemStack(ModItems.infinity_helmet), new ItemStack(ModItems.infinity_helmet));
            addEnhancementArmorRecipe(new ItemStack(ModItems.infinity_chestplate), new ItemStack(ModItems.infinity_chestplate));
            addEnhancementArmorRecipe(new ItemStack(ModItems.infinity_pants), new ItemStack(ModItems.infinity_pants));
            addEnhancementArmorRecipe(new ItemStack(ModItems.infinity_boots), new ItemStack(ModItems.infinity_boots));
            addEnhancementToolsRecipe(new ItemStack(ModItems.infinity_sword), new ItemStack(ModItems.infinity_sword), ModConfig.infinityTools.infinitySwordEnchantments);
            addEnhancementToolsRecipe(getInfPick(new ItemStack(ModItems.infinity_pickaxe)), getInfPick(new ItemStack(ModItems.infinity_pickaxe)), ModConfig.infinityTools.infinityPickaxeEnchantments);
            addEnhancementToolsRecipe(new ItemStack(ModItems.infinity_axe), new ItemStack(ModItems.infinity_axe), ModConfig.infinityTools.infinityAxeEnchantments);
            addEnhancementToolsRecipe(new ItemStack(ModItems.infinity_shovel), new ItemStack(ModItems.infinity_shovel), ModConfig.infinityTools.infinityShovelEnchantments);
            addEnhancementToolsRecipe(new ItemStack(ModItems.infinity_bow), new ItemStack(ModItems.infinity_bow), ModConfig.infinityTools.infinityBowEnchantments);
        }
    }

    private static ItemStack getInfPick(ItemStack stack) {
        stack.addEnchantment(Enchantments.FORTUNE, 10);
        return stack;
    }

    private static void addEnhancementArmorRecipe(ItemStack input, ItemStack output) {
        NBTTagCompound enhancedTag = new NBTTagCompound();
        enhancedTag.setInteger("enhanced", 1);
        if (output.getTagCompound() == null) {
            output.setTagCompound(enhancedTag);
        } else {
            output.getTagCompound().setInteger("enhanced", 1);
        }
        GameRegistry.addShapelessRecipe(output, input, enhancementCrystal);
        GameRegistry.addShapelessRecipe(input, output);
    }

    private static void addEnhancementToolsRecipe(ItemStack input, ItemStack output, String[] enchants) {
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
        GameRegistry.addShapelessRecipe(output, input, enhancementCrystal);
        GameRegistry.addShapelessRecipe(input, output);
    }

}
