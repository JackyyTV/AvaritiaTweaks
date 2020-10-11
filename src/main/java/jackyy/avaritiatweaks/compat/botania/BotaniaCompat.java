package jackyy.avaritiatweaks.compat.botania;

import jackyy.avaritiatweaks.AvaritiaTweaks;
import jackyy.avaritiatweaks.compat.botania.block.BlockGaia;
import jackyy.avaritiatweaks.compat.botania.block.BlockInfinitato;
import jackyy.avaritiatweaks.compat.botania.render.RenderTileInfinitato;
import jackyy.avaritiatweaks.compat.botania.subtile.SubTileAsgardandelion;
import jackyy.avaritiatweaks.compat.botania.subtile.SubTileSoarleander;
import jackyy.avaritiatweaks.compat.botania.tile.TileInfinitato;
import jackyy.avaritiatweaks.config.ModConfig;
import jackyy.avaritiatweaks.util.ModUtils;
import jackyy.gunpowderlib.helper.ObjectHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.BotaniaAPIClient;
import vazkii.botania.api.recipe.RecipeRuneAltar;
import vazkii.botania.api.subtile.signature.BasicSignature;

public class BotaniaCompat {

    public static BlockGaia gaiaBlock = new BlockGaia();
    public static BlockInfinitato infinitato = new BlockInfinitato();
    public static ItemStack asgardandelion;
    public static ItemStack soarleander;

    public static void initItems(RegistryEvent.Register<Item> e) {
        e.getRegistry().register(new ItemBlock(gaiaBlock).setRegistryName(gaiaBlock.getRegistryName()));
        if (ModConfig.compats.botania.infinitato) {
            e.getRegistry().register(new ItemBlock(infinitato).setRegistryName(infinitato.getRegistryName()));
        }
    }

    public static void initBlocks(RegistryEvent.Register<Block> e) {
        e.getRegistry().register(gaiaBlock);
        if (ModConfig.compats.botania.asgardandelion.enabled) {
            BotaniaAPI.registerSubTile("asgardandelion", SubTileAsgardandelion.class);
            BotaniaAPI.registerSubTileSignature(SubTileAsgardandelion.class, new BasicSignature("asgardandelion") {
                @Override
                public String getUnlocalizedLoreTextForStack(ItemStack stack) {
                    return "tile.botania.flower.asgardandelion.lore";
                }
            });
            BotaniaAPI.addSubTileToCreativeMenu("asgardandelion");
        }
        if (ModConfig.compats.botania.soarleander.enabled) {
            BotaniaAPI.registerSubTile("soarleander", SubTileSoarleander.class);
            BotaniaAPI.registerSubTileSignature(SubTileSoarleander.class, new BasicSignature("soarleander") {
                @Override
                public String getUnlocalizedLoreTextForStack(ItemStack stack) {
                    return "tile.botania.flower.soarleander.lore";
                }
            });
            BotaniaAPI.addSubTileToCreativeMenu("soarleander");
        }
        if (ModConfig.compats.botania.infinitato) {
            e.getRegistry().register(infinitato);
            GameRegistry.registerTileEntity(TileInfinitato.class, new ResourceLocation(AvaritiaTweaks.MODID, "infinitato_tile"));
        }
    }

    public static void init() {
        if (ModConfig.compats.botania.asgardandelion.enabled) {
            SubTileAsgardandelion.lexicon = new BotaniaLexiconEntry("asgardandelion", BotaniaAPI.categoryGenerationFlowers);
            SubTileAsgardandelion.lexicon.setLexiconPages(
                    BotaniaAPI.internalHandler.textPage("botania.lexicon.asgardandelion.0")
            );
            SubTileAsgardandelion.lexicon.setIcon(asgardandelion);
        }
        if (ModConfig.compats.botania.soarleander.enabled) {
            SubTileSoarleander.lexicon = new BotaniaLexiconEntry("soarleander", BotaniaAPI.categoryGenerationFlowers);
            SubTileSoarleander.lexicon.setLexiconPages(
                    BotaniaAPI.internalHandler.textPage("botania.lexicon.soarleander.0"),
                    BotaniaAPI.internalHandler.runeRecipePage("botania.lexicon.soarleander.1", new RecipeRuneAltar(
                            soarleander, 8000, ModUtils.getFlower("gourmaryllis"),
                            new ItemStack(Items.CHICKEN), new ItemStack(Items.CHICKEN),
                            new ItemStack(Items.CHICKEN), new ItemStack(Items.CHICKEN),
                            new ItemStack(Items.CHICKEN), new ItemStack(Items.CHICKEN),
                            new ItemStack(Items.CHICKEN), new ItemStack(Items.CHICKEN)
                    ))
            );
            SubTileSoarleander.lexicon.setIcon(soarleander);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(gaiaBlock), 0, new ModelResourceLocation(gaiaBlock.getRegistryName(), "inventory"));
        if (ModConfig.compats.botania.asgardandelion.enabled) {
            BotaniaAPIClient.registerSubtileModel(SubTileAsgardandelion.class, new ModelResourceLocation(AvaritiaTweaks.MODID + ":asgardandelion"));
        }
        if (ModConfig.compats.botania.soarleander.enabled) {
            BotaniaAPIClient.registerSubtileModel(SubTileSoarleander.class, new ModelResourceLocation(AvaritiaTweaks.MODID + ":soarleander"));
        }
        if (ModConfig.compats.botania.infinitato) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(infinitato), 0, new ModelResourceLocation(infinitato.getRegistryName(), "facing=south"));
            ClientRegistry.bindTileEntitySpecialRenderer(TileInfinitato.class, new RenderTileInfinitato());
        }
    }

    public static void initRecipes() {
        asgardandelion = ModUtils.getFlower("asgardandelion");
        soarleander = ModUtils.getFlower("soarleander");
        if (ModConfig.compats.botania.gaiaBlock) {
            GameRegistry.addShapedRecipe(
                    new ResourceLocation(AvaritiaTweaks.MODID, "gaia_ingots_to_block"), null, new ItemStack(gaiaBlock),
                    "XXX", "XXX", "XXX",
                    'X', ObjectHelper.getItemStackByName("botania:manaresource", 1, 14)
            );
            GameRegistry.addShapelessRecipe(new ResourceLocation(AvaritiaTweaks.MODID, "gaia_block_to_ingots"), null, ObjectHelper.getItemStackByName("botania:manaresource", 9, 14), Ingredient.fromStacks(new ItemStack(gaiaBlock)));
        }
        if (ModConfig.compats.botania.soarleander.enabled) {
            BotaniaAPI.registerRuneAltarRecipe(
                    soarleander, 8000, ModUtils.getFlower("gourmaryllis"),
                    new ItemStack(Items.CHICKEN), new ItemStack(Items.CHICKEN),
                    new ItemStack(Items.CHICKEN), new ItemStack(Items.CHICKEN),
                    new ItemStack(Items.CHICKEN), new ItemStack(Items.CHICKEN),
                    new ItemStack(Items.CHICKEN), new ItemStack(Items.CHICKEN)
            );
        }
    }

}
