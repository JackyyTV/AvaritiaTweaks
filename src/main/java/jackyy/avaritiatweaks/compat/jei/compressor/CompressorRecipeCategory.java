package jackyy.avaritiatweaks.compat.jei.compressor;

import jackyy.avaritiatweaks.compat.jei.JEICompat;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import net.minecraft.client.resources.I18n;

public class CompressorRecipeCategory extends BlankRecipeCategory<CompressorRecipeWrapper> {

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    private final String localizedName;

    public CompressorRecipeCategory() {
        localizedName = I18n.format("container.neutronium_compressor");
    }

    @Override
    public String getUid() {
        return JEICompat.NEUTRONIUM_COMPRESSOR;
    }

    @Override
    public String getTitle() {
        return localizedName;
    }

    @Override
    public IDrawable getBackground() {
        return JEICompat.neutronium_compressor;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, CompressorRecipeWrapper recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup stackGroup = recipeLayout.getItemStacks();
        stackGroup.init(INPUT_SLOT, true, 1, 5);
        stackGroup.init(OUTPUT_SLOT, false, 79, 5);
        stackGroup.set(ingredients);
    }

}
