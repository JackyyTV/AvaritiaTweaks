package jackyy.avaritiatweaks.compat.botania.block;

import jackyy.avaritiatweaks.config.ModConfig;
import jackyy.avaritiatweaks.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BlockGaia extends Block {

    public BlockGaia() {
        super(Material.IRON);
        setRegistryName("gaia_block");
        setUnlocalizedName(Reference.MODID + ".gaia_block");
        setSoundType(SoundType.METAL);
        setHarvestLevel("pickaxe", 3);
        setHardness(50.0F);
        setResistance(2000.0F);
        setCreativeTab(Reference.TAB);
    }

    @Override @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
        if (ModConfig.compats.botaniaCompat) {
            if (ModConfig.Compats.botania.gaiaBlock && Loader.isModLoaded("Botania")) {
                list.add(new ItemStack(item));
            }
        }
    }

    @Override
    public boolean isBeaconBase(IBlockAccess world, BlockPos pos, BlockPos beacon) {
        return true;
    }

    @Override
    public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity) {
        return false;
    }

}
