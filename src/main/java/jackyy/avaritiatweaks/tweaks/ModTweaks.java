package jackyy.avaritiatweaks.tweaks;

import codechicken.lib.model.ModelRegistryHelper;
import codechicken.lib.util.TransformUtils;
import jackyy.avaritiatweaks.compat.botania.BotaniaCompat;
import jackyy.avaritiatweaks.config.ModConfig;
import jackyy.avaritiatweaks.item.ItemEnhancementCrystal;
import jackyy.avaritiatweaks.util.ModUtils;
import morph.avaritia.client.render.item.HaloRenderItem;
import morph.avaritia.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModTweaks {

    public static ItemEnhancementCrystal enhancementCrystal = new ItemEnhancementCrystal();

    public static void initItems(RegistryEvent.Register<Item> e) {
        e.getRegistry().register(enhancementCrystal);
        if (ModConfig.compats.botaniaCompat && Loader.isModLoaded("botania")) {
            BotaniaCompat.initItems(e);
        }
    }

    public static void initBlocks(RegistryEvent.Register<Block> e) {
        if (ModConfig.compats.botaniaCompat && Loader.isModLoaded("botania")) {
            BotaniaCompat.initBlocks(e);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        ModelResourceLocation location = new ModelResourceLocation(enhancementCrystal.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(enhancementCrystal, 0, location);
        IBakedModel wrapped = new HaloRenderItem(TransformUtils.DEFAULT_ITEM, (modelRegistry) -> modelRegistry.getObject(location));
        ModelRegistryHelper.register(location, wrapped);
    }

    public static void initRecipes(RegistryEvent.Register<IRecipe> e) {
        if (ModConfig.tweaks.enableEnhancementCrystal) {
            ModUtils.addEnhancementArmorRecipe("infinity_helmet", new ItemStack(ModItems.infinity_helmet), new ItemStack(ModItems.infinity_helmet));
            ModUtils.addEnhancementArmorRecipe("infinity_chestplate", new ItemStack(ModItems.infinity_chestplate), new ItemStack(ModItems.infinity_chestplate));
            ModUtils.addEnhancementArmorRecipe("infinity_pants", new ItemStack(ModItems.infinity_pants), new ItemStack(ModItems.infinity_pants));
            ModUtils.addEnhancementArmorRecipe("infinity_boots", new ItemStack(ModItems.infinity_boots), new ItemStack(ModItems.infinity_boots));
            ModUtils.addEnhancementToolsRecipe("infinity_sword", new ItemStack(ModItems.infinity_sword), new ItemStack(ModItems.infinity_sword), ModConfig.infinityTools.infinitySwordEnchantments);
            ModUtils.addEnhancementToolsRecipe("infinity_pickaxe", ModUtils.getInfPick(new ItemStack(ModItems.infinity_pickaxe)), ModUtils.getInfPick(new ItemStack(ModItems.infinity_pickaxe)), ModConfig.infinityTools.infinityPickaxeEnchantments);
            ModUtils.addEnhancementToolsRecipe("infinity_axe", new ItemStack(ModItems.infinity_axe), new ItemStack(ModItems.infinity_axe), ModConfig.infinityTools.infinityAxeEnchantments);
            ModUtils.addEnhancementToolsRecipe("infinity_shovel", new ItemStack(ModItems.infinity_shovel), new ItemStack(ModItems.infinity_shovel), ModConfig.infinityTools.infinityShovelEnchantments);
            ModUtils.addEnhancementToolsRecipe("infinity_hoe", new ItemStack(ModItems.infinity_hoe), new ItemStack(ModItems.infinity_hoe), ModConfig.infinityTools.infinityHoeEnchantments);
            ModUtils.addEnhancementToolsRecipe("infinity_bow", new ItemStack(ModItems.infinity_bow), new ItemStack(ModItems.infinity_bow), ModConfig.infinityTools.infinityBowEnchantments);
        }
        if (ModConfig.compats.botaniaCompat && Loader.isModLoaded("botania")) {
            BotaniaCompat.initRecipes();
        }
    }

    public static void initIntegrations() {
        if (ModConfig.compats.botaniaCompat && Loader.isModLoaded("botania")) {
            BotaniaCompat.init();
        }
    }

    @SideOnly(Side.CLIENT)
    public static void initIntegrationsClient() {
        if (ModConfig.compats.botaniaCompat && Loader.isModLoaded("botania")) {
            BotaniaCompat.initModels();
        }
    }

}
