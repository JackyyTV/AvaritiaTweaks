package jackyy.avaritiatweaks.compat.jei;

import jackyy.avaritiatweaks.config.ModConfig;
import jackyy.avaritiatweaks.tweaks.ModTweaks;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import morph.avaritia.init.ModBlocks;
import morph.avaritia.init.ModItems;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JEICompat implements IModPlugin {

    @Override
    public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {
    }

    @Override
    public void registerIngredients(IModIngredientRegistration registry) {
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {

    }

    @Override
    public void register(IModRegistry registry) {
        if (ModConfig.compats.jeiCompat) {
            if (ModConfig.compats.jei.descriptions) {
                registry.addIngredientInfo(ModItems.neutron_pile, VanillaTypes.ITEM, "desc.avaritiatweaks.neutron_pile");
                registry.addIngredientInfo(new ItemStack(ModBlocks.neutron_collector), VanillaTypes.ITEM, "desc.avaritiatweaks.neutron_collector");
                registry.addIngredientInfo(new ItemStack(ModTweaks.enhancementCrystal), VanillaTypes.ITEM, "desc.avaritiatweaks.enhancement_crystal");
            }
        }
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
    }

}
