package jackyy.avaritiatweaks.item;

import jackyy.avaritiatweaks.config.ModConfig;
import jackyy.avaritiatweaks.util.Reference;
import morph.avaritia.api.IHaloRenderItem;
import morph.avaritia.entity.EntityImmortalItem;
import morph.avaritia.init.AvaritiaTextures;
import morph.avaritia.init.ModItems;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemEnhancementCrystal extends Item implements IHaloRenderItem {

    public ItemEnhancementCrystal() {
        setRegistryName("enhancement_crystal");
        setUnlocalizedName(Reference.MODID + ".enhancement_crystal");
        setCreativeTab(Reference.TAB);
    }

    @Override @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List<String> tooltip, boolean advanced) {
        tooltip.add(TextFormatting.DARK_GRAY + "" + TextFormatting.ITALIC + I18n.format("tooltips.avaritiatweaks.enhancement_crystal"));
    }

    @Override @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> list) {
        if (ModConfig.tweaks.enableEnhancementCrystal) {
            list.add(new ItemStack(item));
        }
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return ModItems.COSMIC_RARITY;
    }

    @Override
    public boolean hasCustomEntity(ItemStack stack) {
        return true;
    }

    @Override
    public Entity createEntity(World world, Entity location, ItemStack itemstack) {
        return new EntityImmortalItem(world, location, itemstack);
    }

    @Override @SideOnly(Side.CLIENT)
    public boolean shouldDrawHalo(ItemStack itemStack) {
        return true;
    }

    @Override @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getHaloTexture(ItemStack itemStack) {
        return AvaritiaTextures.HALO;
    }

    @Override @SideOnly(Side.CLIENT)
    public int getHaloColour(ItemStack itemStack) {
        return -16777216;
    }

    @Override @SideOnly(Side.CLIENT)
    public int getHaloSize(ItemStack itemStack) {
        return 10;
    }

    @Override @SideOnly(Side.CLIENT)
    public boolean shouldDrawPulse(ItemStack itemStack) {
        return true;
    }

}
