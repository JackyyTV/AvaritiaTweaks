package jackyy.avaritiatweaks.compat.botania.block;

import jackyy.avaritiatweaks.AvaritiaTweaks;
import jackyy.avaritiatweaks.compat.botania.tile.TileInfinitato;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.state.BotaniaStateProps;
import vazkii.botania.common.core.helper.InventoryHelper;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class BlockInfinitato extends BlockContainer implements ILexiconable {

    private static float f = 1F / 16F * 4F;
    private static final AxisAlignedBB AABB = new AxisAlignedBB(f, 0, f, 1F - f, f*3, 1F - f);
    public static LexiconEntry lexiconEntry;

    public BlockInfinitato() {
        super(Material.CLOTH);
        setRegistryName("infinitato");
        setUnlocalizedName(AvaritiaTweaks.MODID + ".infinitato");
        setHardness(0.25F);
        setCreativeTab(AvaritiaTweaks.TAB);
    }

    @Override @SuppressWarnings("deprecation")
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
        return AABB;
    }

    @Override
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, BotaniaStateProps.CARDINALS);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        switch(state.getValue(BotaniaStateProps.CARDINALS)) {
            case NORTH:
                return 0;
            case WEST:
                return 3;
            case EAST:
                return 1;
            case SOUTH:
            default:
                return 2;
        }
    }

    @Override  @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing side;
        switch(meta) {
            case 0:
                side = EnumFacing.NORTH;
                break;
            case 1:
                side = EnumFacing.EAST;
                break;
            case 2:
            default:
                side = EnumFacing.SOUTH;
                break;
            case 3:
                side = EnumFacing.WEST;
        }
        return this.getDefaultState().withProperty(BotaniaStateProps.CARDINALS, side);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof TileInfinitato) {
            ((TileInfinitato) tile).interact(player, hand, player.getHeldItemMainhand(), facing);
            world.spawnParticle(EnumParticleTypes.HEART, (double)pos.getX() + AABB.minX + Math.random() * (AABB.maxX - AABB.minX), (double)pos.getY() + AABB.maxY, (double)pos.getZ() + AABB.minZ + Math.random() * (AABB.maxZ - AABB.minZ), 0.0D, 0.0D, 0.0D);
            world.spawnParticle(EnumParticleTypes.HEART, (double)pos.getX() + AABB.minX + Math.random() * (AABB.maxX - AABB.minX), (double)pos.getY() + AABB.maxY, (double)pos.getZ() + AABB.minZ + Math.random() * (AABB.maxZ - AABB.minZ), 0.0D, 0.0D, 0.0D);
            world.spawnParticle(EnumParticleTypes.HEART, (double)pos.getX() + AABB.minX + Math.random() * (AABB.maxX - AABB.minX), (double)pos.getY() + AABB.maxY, (double)pos.getZ() + AABB.minZ + Math.random() * (AABB.maxZ - AABB.minZ), 0.0D, 0.0D, 0.0D);
        }
        return true;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {
        world.setBlockState(pos, state.withProperty(BotaniaStateProps.CARDINALS, entity.getHorizontalFacing().getOpposite()));
        if (stack.hasDisplayName()) {
            TileEntity tile = world.getTileEntity(pos);
            if (tile != null) {
                ((TileInfinitato)tile).name = stack.getDisplayName();
            }
        }
    }

    @Override
    public boolean removedByPlayer(@Nonnull IBlockState state, World world, @Nonnull BlockPos pos, @Nonnull EntityPlayer player, boolean willHarvest) {
        if (willHarvest) {
            onBlockHarvested(world, pos, state, player);
            return true;
        } else {
            return super.removedByPlayer(state, world, pos, player, willHarvest);
        }
    }

    @Override
    public void harvestBlock(@Nonnull World world, EntityPlayer player, @Nonnull BlockPos pos, @Nonnull IBlockState state, TileEntity te, ItemStack stack) {
        super.harvestBlock(world, player, pos, state, te, stack);
        world.setBlockToAir(pos);
    }

    @Override
    public void breakBlock(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        TileInfinitato inv = (TileInfinitato)world.getTileEntity(pos);
        InventoryHelper.dropInventory(inv, world, state, pos);
        super.breakBlock(world, pos, state);
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, @Nonnull IBlockState state, int fortune) {
        ArrayList<ItemStack> list = new ArrayList<>();
        TileEntity tile = world.getTileEntity(pos);
        if (tile != null) {
            ItemStack stack = new ItemStack(this);
            String name = ((TileInfinitato)tile).name;
            if (!name.isEmpty()) {
                stack.setStackDisplayName(name);
            }
            list.add(stack);
        }
        return list;
    }

    @Override @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override @SuppressWarnings("deprecation")
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override @SuppressWarnings("deprecation")
    public boolean isNormalCube(IBlockState state) {
        return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileInfinitato();
    }

    @Override
    public LexiconEntry getEntry(World world, BlockPos blockPos, EntityPlayer entityPlayer, ItemStack itemStack) {
        return lexiconEntry;
    }

}