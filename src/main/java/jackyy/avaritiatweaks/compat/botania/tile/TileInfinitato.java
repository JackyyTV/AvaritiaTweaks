package jackyy.avaritiatweaks.compat.botania.tile;

import jackyy.avaritiatweaks.util.ModUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import vazkii.botania.common.block.tile.TileTinyPotato;

import java.util.List;

public class TileInfinitato extends TileTinyPotato {

    private static final String TAG_NAME = "name";
    public int jumpTicks = 0;
    public String name = "";
    private int nextDoIt = 0;

    public void interact(EntityPlayer player, EnumHand hand, ItemStack stack, EnumFacing side) {
        jump();
        if(name.equalsIgnoreCase("shia labeouf") && !world.isRemote && nextDoIt == 0) {
            nextDoIt = 40;
            world.playSound(player, player.getPosition(), ModUtils.getSoundFromName("botania:doit"),
                    SoundCategory.BLOCKS, 2.5F, 0.7F);
        }
        double radius = 10.5;
        int time = 3600;
        List<EntityLivingBase> inspired = world.getEntitiesWithinAABB(EntityLivingBase.class,
                new AxisAlignedBB(getPos().getX() - radius, getPos().getY() - radius, getPos().getZ() - radius,
                        getPos().getX() + radius, getPos().getY() + radius, getPos().getZ() + radius));
        for (EntityLivingBase ent : inspired) {
            double dx = ent.posX - getPos().getX();
            double dy = ent.posY - getPos().getY();
            double dz = ent.posZ - getPos().getZ();
            double dist = Math.sqrt(dx*dx + dy*dy + dz*dz);
            if (dist <= radius) {
                ent.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, time, 1));
                ent.addPotionEffect(new PotionEffect(MobEffects.SPEED, time, 0));
                ent.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, time, 0));
                ent.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, time, 1));
                ent.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, time, 0));
                ent.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, time, 1));
                ent.addPotionEffect(new PotionEffect(MobEffects.HASTE, time, 1));
                ent.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, time, 0));
                ent.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, time, 0));
                ent.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, time, 0));
                ent.addPotionEffect(new PotionEffect(MobEffects.LUCK, time, 0));
                ent.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, time, 4));
                ent.addPotionEffect(new PotionEffect(MobEffects.SATURATION, time, 4));
            }
        }
    }

    @Override
    public void jump() {
        if (jumpTicks == 0) {
            jumpTicks = 40;
        }
    }

    @Override
    public void update() {
        if(jumpTicks > 0) {
            jumpTicks--;
            if (jumpTicks == 20 || jumpTicks == 0) {
                this.world.createExplosion(null, getPos().getX() + 0.5, getPos().getY(), getPos().getZ() + 0.5, 0.0f, true);
            }
        }
        if(nextDoIt > 0)
            nextDoIt--;
    }

    @Override
    public void writePacketNBT(NBTTagCompound cmp) {
        cmp.setString(TAG_NAME, name);
    }

    @Override
    public void readPacketNBT(NBTTagCompound cmp) {
        name = cmp.getString(TAG_NAME);
    }

    @Override
    public int getSizeInventory() {
        return 6;
    }

}