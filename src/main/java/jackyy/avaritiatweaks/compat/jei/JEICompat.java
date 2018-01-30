package jackyy.avaritiatweaks.compat.jei;

import jackyy.avaritiatweaks.compat.jei.compressor.CompressorRecipeCategory;
import jackyy.avaritiatweaks.compat.jei.compressor.CompressorRecipeHandler;
import jackyy.avaritiatweaks.config.ModConfig;
import jackyy.avaritiatweaks.tweaks.ModTweaks;
import mezz.jei.api.*;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import morph.avaritia.client.gui.GUINeutroniumCompressor;
import morph.avaritia.init.ModBlocks;
import morph.avaritia.init.ModItems;
import morph.avaritia.recipe.compressor.CompressorManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

@JEIPlugin
public class JEICompat implements IModPlugin {

    public static final String NEUTRONIUM_COMPRESSOR = "Avatitia.Compressor";
    public static IJeiHelpers jeiHelpers;
    public static IDrawableStatic neutronium_compressor;
    public static IDrawableStatic static_singularity;

    @Override
    public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {
    }

    @Override
    public void registerIngredients(IModIngredientRegistration registry) {
    }

    @Override
    public void register(IModRegistry registry) {
        if (ModConfig.compats.jeiCompat) {
            if (ModConfig.Compats.jei.neutroniumCompressor) {
                jeiHelpers = registry.getJeiHelpers();
                IGuiHelper helper = jeiHelpers.getGuiHelper();
                ResourceLocation location = new ResourceLocation("avaritia:textures/gui/compressor.png");
                neutronium_compressor = helper.createDrawable(location, 37, 29, 102, 41);
                static_singularity = helper.createDrawable(location, 176, 16, 16, 16);

                registry.addRecipeCategories(new CompressorRecipeCategory());
                registry.addRecipeHandlers(new CompressorRecipeHandler());
                registry.addRecipeClickArea(GUINeutroniumCompressor.class, 62, 35, 22, 15, NEUTRONIUM_COMPRESSOR);
                registry.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.neutronium_compressor), NEUTRONIUM_COMPRESSOR);
                registry.addRecipes(CompressorManager.getRecipes());
            }
            if (ModConfig.Compats.jei.descriptions) {
                registry.addDescription(ModItems.neutron_pile, "desc.avaritiatweaks.neutron_pile");
                registry.addDescription(new ItemStack(ModBlocks.neutron_collector), "desc.avaritiatweaks.neutron_collector");
                registry.addDescription(new ItemStack(ModTweaks.enhancementCrystal), "desc.avaritiatweaks.enhancement_crystal");
            }
        }
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
    }

}
