package jackyy.avaritiatweaks.integrations.jei.compressor;

import jackyy.avaritiatweaks.integrations.jei.JEIIntegration;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import morph.avaritia.recipe.compressor.CompressorRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import java.util.List;

public class CompressorRecipeWrapper extends BlankRecipeWrapper {

    public final CompressorRecipe recipe;

    private final IDrawableAnimated singularity;

    public CompressorRecipeWrapper(CompressorRecipe recipe) {
        this.recipe = recipe;
        IGuiHelper helper = JEIIntegration.jeiHelpers.getGuiHelper();
        singularity = helper.createAnimatedDrawable(JEIIntegration.static_singularity, Math.max(recipe.getCost() / 16, 1), IDrawableAnimated.StartDirection.BOTTOM, false);
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputLists(ItemStack.class, getRecipeInputs(recipe));
        ingredients.setOutput(ItemStack.class, recipe.getOutput());
    }

    @Override
    public void drawAnimations(Minecraft minecraft, int recipeWidth, int recipeHeight) {
        singularity.draw(minecraft, 53, 6);
        String thing = "Input Amount: " + recipe.getCost();
        minecraft.fontRendererObj.drawString(thing, recipeWidth / 2 - minecraft.fontRendererObj.getStringWidth(thing) / 2, 31, 0x404040);
    }

    private List<List<ItemStack>> getRecipeInputs(CompressorRecipe recipe) {
        return JEIIntegration.jeiHelpers.getStackHelper().expandRecipeItemStackInputs(recipe.getInputs());
    }

}
