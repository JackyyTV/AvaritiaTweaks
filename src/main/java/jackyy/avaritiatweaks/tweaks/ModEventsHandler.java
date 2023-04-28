package jackyy.avaritiatweaks.tweaks;

import jackyy.avaritiatweaks.config.ModConfig;
import jackyy.avaritiatweaks.util.ModUtils;
import morph.avaritia.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModEventsHandler {

    @SubscribeEvent
    public void onLeftClick(PlayerInteractEvent.LeftClickBlock event) {
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        EntityPlayer player = event.getEntityPlayer();
        ItemStack stack = event.getItemStack();
        EnumFacing facing = event.getFace();
        Vec3d vec = event.getHitVec();
        if (!ModConfig.tweaks.makeWorldBreakerGreatAgain || facing == null || world.isRemote
                || stack.isEmpty() || player.getHeldItemMainhand().isEmpty() || player.capabilities.isCreativeMode) {
            return;
        }
        if (state.getBlockHardness(world, pos) <= -1 && stack.getItem() == ModItems.infinity_pickaxe) {
            if (stack.getTagCompound() != null && stack.getTagCompound().getBoolean("hammer")) {
                ModItems.infinity_pickaxe.onBlockStartBreak(player.getHeldItemMainhand(), pos, player);
            } else {
                ItemStack drop = block.getPickBlock(state, new RayTraceResult(vec, facing), world, pos, player);
                event.getWorld().destroyBlock(pos, false);
                world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), drop));
            }
        }
    }

    @SubscribeEvent
    public void armorTick(LivingEvent.LivingUpdateEvent event) {
        if (event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)event.getEntityLiving();
            if (ModUtils.isArmorValid(player, EntityEquipmentSlot.HEAD)) {
                ModUtils.checkAndAddEffect(player, ModConfig.infinityArmor.infinityHelmetPotionEffects);
            }
            if (ModUtils.isArmorValid(player, EntityEquipmentSlot.CHEST)) {
                ModUtils.checkAndAddEffect(player, ModConfig.infinityArmor.infinityChestplatePotionEffects);
            }
            if (ModUtils.isArmorValid(player, EntityEquipmentSlot.LEGS)) {
                ModUtils.checkAndAddEffect(player, ModConfig.infinityArmor.infinityLeggingsPotionEffects);
            }
            if (ModUtils.isArmorValid(player, EntityEquipmentSlot.FEET)) {
                ModUtils.checkAndAddEffect(player, ModConfig.infinityArmor.infinityBootsPotionEffects);
            }
        }
    }

}
