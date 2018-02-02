package jackyy.avaritiatweaks.compat.botania.block;

import jackyy.avaritiatweaks.AvaritiaTweaks;
import jackyy.avaritiatweaks.config.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.Loader;

public class BlockGaia extends Block {

    public BlockGaia() {
        super(Material.IRON);
        setRegistryName("gaia_block");
        setUnlocalizedName(AvaritiaTweaks.MODID + ".gaia_block");
        setSoundType(SoundType.METAL);
        setHarvestLevel("pickaxe", 3);
        setHardness(50.0F);
        setResistance(2000.0F);
        setCreativeTab(AvaritiaTweaks.TAB);
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
        if (ModConfig.compats.botaniaCompat) {
            if (ModConfig.compats.botania.gaiaBlock && Loader.isModLoaded("botania")) {
                list.add(new ItemStack(this));
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
