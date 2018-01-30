package jackyy.avaritiatweaks.compat.jei.compressor;

import jackyy.avaritiatweaks.compat.jei.JEICompat;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.util.ErrorUtil;
import mezz.jei.util.Log;
import morph.avaritia.recipe.compressor.CompressorRecipe;
import net.minecraft.item.ItemStack;

public class CompressorRecipeHandler implements IRecipeHandler<CompressorRecipe> {

    @Override
    public Class<CompressorRecipe> getRecipeClass() {
        return CompressorRecipe.class;
    }

    @Override @SuppressWarnings("deprecation")
    public String getRecipeCategoryUid() {
        return null;
    }

    @Override
    public String getRecipeCategoryUid(CompressorRecipe recipe) {
        return JEICompat.NEUTRONIUM_COMPRESSOR;
    }

    @Override
    public IRecipeWrapper getRecipeWrapper(CompressorRecipe recipe) {
        return new CompressorRecipeWrapper(recipe);
    }

    @Override
    public boolean isRecipeValid(CompressorRecipe recipe) {
        if (recipe.getOutput() == null) {
            String recipeInfo = ErrorUtil.getInfoFromRecipe(recipe, this);
            Log.error("Recipe has no outputs. {}", recipeInfo);
            return false;
        }
        int inputCount = 0;
        for (ItemStack input : recipe.getInputs()) {
            if (input != null) {
                inputCount++;
            }
        }
        if (inputCount > 1) {
            String recipeInfo = ErrorUtil.getInfoFromRecipe(recipe, this);
            Log.error("Recipe has too many inputs. {}", recipeInfo);
            return false;
        }
        if (inputCount == 0) {
            String recipeInfo = ErrorUtil.getInfoFromRecipe(recipe, this);
            Log.error("Recipe has no inputs. {}", recipeInfo);
            return false;
        }
        return true;
    }

}
