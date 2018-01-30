package jackyy.avaritiatweaks.compat.botania;

import jackyy.avaritiatweaks.AvaritiaTweaks;
import jackyy.avaritiatweaks.compat.botania.block.BlockGaia;
import jackyy.avaritiatweaks.compat.botania.subtile.SubTileAsgardandelion;
import jackyy.avaritiatweaks.compat.botania.subtile.SubTileSoarleander;
import jackyy.avaritiatweaks.config.ModConfig;
import jackyy.avaritiatweaks.util.ModUtils;
import morph.avaritia.init.ModItems;
import morph.avaritia.recipe.extreme.ExtremeCraftingManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.BotaniaAPIClient;
import vazkii.botania.api.recipe.RecipeRuneAltar;
import vazkii.botania.api.subtile.signature.BasicSignature;

public class BotaniaCompat {

    public static BlockGaia gaiaBlock = new BlockGaia();
    public static ItemStack asgardandelion = ModUtils.getFlower("asgardandelion");
    public static ItemStack soarleander = ModUtils.getFlower("soarleander");

    public static RecipeRuneAltar soarleanderRecipe;

    public static void preInit() {
        GameRegistry.register(gaiaBlock);
        GameRegistry.register(new ItemBlock(gaiaBlock), gaiaBlock.getRegistryName());
        if (ModConfig.Compats.botania.asgardandelion) {
            BotaniaAPI.registerSubTile("asgardandelion", SubTileAsgardandelion.class);
            BotaniaAPI.registerSubTileSignature(SubTileAsgardandelion.class, new BasicSignature("asgardandelion") {
                @Override
                public String getUnlocalizedLoreTextForStack(ItemStack stack) {
                    return "tile.botania.flower.asgardandelion.lore";
                }
            });
            BotaniaAPI.addSubTileToCreativeMenu("asgardandelion");
        }
        if (ModConfig.Compats.botania.soarleander) {
            BotaniaAPI.registerSubTile("soarleander", SubTileSoarleander.class);
            BotaniaAPI.registerSubTileSignature(SubTileSoarleander.class, new BasicSignature("soarleander") {
                @Override
                public String getUnlocalizedLoreTextForStack(ItemStack stack) {
                    return "tile.botania.flower.soarleander.lore";
                }
            });
            BotaniaAPI.addSubTileToCreativeMenu("soarleander");
        }
    }

    public static void init() {
        if (ModConfig.Compats.botania.asgardandelion) {
            SubTileAsgardandelion.lexicon = new BotaniaLexiconEntry("asgardandelion", BotaniaAPI.categoryGenerationFlowers);
            SubTileAsgardandelion.lexicon.addPage(BotaniaAPI.internalHandler.textPage("botania.lexicon.asgardandelion.0"));
            SubTileAsgardandelion.lexicon.setIcon(asgardandelion);
        }
        if (ModConfig.Compats.botania.soarleander) {
            SubTileSoarleander.lexicon = new BotaniaLexiconEntry("soarleander", BotaniaAPI.categoryGenerationFlowers);
            SubTileSoarleander.lexicon.setLexiconPages(
                    BotaniaAPI.internalHandler.textPage("botania.lexicon.soarleander.0"),
                    BotaniaAPI.internalHandler.runeRecipePage("botania.lexicon.soarleander.1", soarleanderRecipe)
            );
            SubTileSoarleander.lexicon.setIcon(soarleander);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(gaiaBlock), 0, new ModelResourceLocation(gaiaBlock.getRegistryName(), "inventory"));
        if (ModConfig.Compats.botania.asgardandelion) {
            BotaniaAPIClient.registerSubtileModel(SubTileAsgardandelion.class, new ModelResourceLocation(AvaritiaTweaks.MODID + ":asgardandelion"));
        }
        if (ModConfig.Compats.botania.soarleander) {
            BotaniaAPIClient.registerSubtileModel(SubTileSoarleander.class, new ModelResourceLocation(AvaritiaTweaks.MODID + ":soarleander"));
        }
    }

    public static void initRecipes() {
        if (ModConfig.Compats.botania.gaiaBlock) {
            GameRegistry.addShapedRecipe(
                    new ItemStack(gaiaBlock),
                    "XXX", "XXX", "XXX",
                    'X', ModUtils.getStackFromName("botania:manaresource", 1, 14)
            );
            GameRegistry.addShapelessRecipe(ModUtils.getStackFromName("botania:manaresource", 9, 14), new ItemStack(gaiaBlock));
        }
        if (ModConfig.Compats.botania.asgardandelion) {
            ExtremeCraftingManager.getInstance().addRecipe(asgardandelion,
                    "   III   ",
                    "  IIIII  ",
                    "  IIXII  ",
                    "  IIIII  ",
                    "   III   ",
                    " nn N nn ",
                    "nnnnNnnnn",
                    " nn N nn ",
                    "    N    ",
                    'I', new ItemStack(ModItems.resource, 1, 6),
                    'X', new ItemStack(ModItems.resource, 1, 5),
                    'N', new ItemStack(ModItems.resource, 1, 4),
                    'n', new ItemStack(ModItems.resource, 1, 3)
            );
        }
        if (ModConfig.Compats.botania.soarleander) {
            soarleanderRecipe = BotaniaAPI.registerRuneAltarRecipe(
                    soarleander, 8000, ModUtils.getFlower("gourmaryllis"),
                    new ItemStack(Items.CHICKEN), new ItemStack(Items.CHICKEN),
                    new ItemStack(Items.CHICKEN), new ItemStack(Items.CHICKEN),
                    new ItemStack(Items.CHICKEN), new ItemStack(Items.CHICKEN),
                    new ItemStack(Items.CHICKEN), new ItemStack(Items.CHICKEN)
            );
        }
    }

}
