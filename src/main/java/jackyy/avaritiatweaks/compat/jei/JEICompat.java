package jackyy.avaritiatweaks.compat.jei;

import jackyy.avaritiatweaks.config.ModConfig;
import jackyy.avaritiatweaks.tweaks.ModTweaks;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.IModIngredientRegistration;
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
            if (ModConfig.Compats.jei.descriptions) {
                registry.addIngredientInfo(ModItems.neutron_pile, ItemStack.class, "desc.avaritiatweaks.neutron_pile");
                registry.addIngredientInfo(new ItemStack(ModBlocks.neutron_collector), ItemStack.class, "desc.avaritiatweaks.neutron_collector");
                registry.addIngredientInfo(new ItemStack(ModTweaks.enhancementCrystal), ItemStack.class, "desc.avaritiatweaks.enhancement_crystal");
            }
        }
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
    }

}
