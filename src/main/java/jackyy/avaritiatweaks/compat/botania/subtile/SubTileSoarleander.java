package jackyy.avaritiatweaks.compat.botania.subtile;

import jackyy.avaritiatweaks.config.ModConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileGenerating;

public class SubTileSoarleander extends SubTileGenerating {

    public static LexiconEntry lexicon;
    private static final String BURN_TIME = "burnTime";
    private static final int RANGE = 3;
    private static final int START_BURN_EVENT = 0;
    int burnTime = 0;

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (burnTime > 0)
            burnTime--;
        if (getWorld().isRemote) {
            if (burnTime > 0 && supertile.getWorld().rand.nextInt(10) == 0) {
                Vec3d offset = getWorld().getBlockState(getPos()).getOffset(getWorld(), getPos()).addVector(0.4, 0.7, 0.4);
                supertile.getWorld().spawnParticle(EnumParticleTypes.FLAME, supertile.getPos().getX() + offset.x + Math.random() * 0.2, supertile.getPos().getY() + offset.y, supertile.getPos().getZ() + offset.z + Math.random() * 0.2, 0.0D, 0.0D, 0.0D);
            }
            return;
        }
        if (linkedCollector != null) {
            if (burnTime == 0) {
                if (mana < getMaxMana()) {
                    int slowdown = getSlowdownFactor();
                    for (EntityItem item : supertile.getWorld().getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(supertile.getPos().add(-RANGE, -RANGE, -RANGE), supertile.getPos().add(RANGE + 1, RANGE + 1, RANGE + 1)))) {
                        if (item.age >= 59 + slowdown && !item.isDead) {
                            ItemStack stack = item.getItem();
                            if (stack.isEmpty() || stack.getItem().hasContainerItem(stack))
                                continue;
                            int burnTime = 0;
                            switch (stack.getItem().getRegistryName().toString()) {
                                case "minecraft:chicken":
                                    burnTime = 1000;
                                    break;
                                case "minecraft:cooked_chicken":
                                    burnTime = 2500;
                                    break;
                                case "minecraft:egg":
                                    burnTime = 500;
                                    break;
                                case "minecraft:feather":
                                    burnTime = 250;
                                    break;
                            }
                            if (burnTime > 0 && stack.getCount() > 0) {
                                this.burnTime = burnTime;
                                stack.shrink(1);
                                supertile.getWorld().playSound(null, supertile.getPos(), SoundEvents.ENTITY_CHICKEN_HURT, SoundCategory.BLOCKS, 0.2F, 1F);
                                getWorld().addBlockEvent(getPos(), getWorld().getBlockState(getPos()).getBlock(), START_BURN_EVENT, item.getEntityId());
                                sync();
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean receiveClientEvent(int event, int param) {
        if (event == START_BURN_EVENT) {
            Entity entity = getWorld().getEntityByID(param);
            if (entity != null) {
                entity.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, entity.posX, entity.posY + 0.1, entity.posZ, 0.0D, 0.0D, 0.0D);
                entity.world.spawnParticle(EnumParticleTypes.FLAME, entity.posX, entity.posY, entity.posZ, 0.0D, 0.0D, 0.0D);
            }
            return true;
        } else {
            return super.receiveClientEvent(event, param);
        }
    }


    @Override
    public int getMaxMana() {
        return ModConfig.compats.botania.soarleander.maxMana;
    }

    @Override
    public int getColor() {
        return 0x11FF00;
    }

    @Override
    public LexiconEntry getEntry() {
        return lexicon;
    }

    @Override
    public boolean canGeneratePassively() {
        return this.burnTime > 0;
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toBlockPos(), 3);
    }

    @Override
    public int getDelayBetweenPassiveGeneration() {
        return ModConfig.compats.botania.soarleander.generationCycleDelay;
    }

    @Override
    public int getValueForPassiveGeneration() {
        return ModConfig.compats.botania.soarleander.generationCycleMana;
    }

    @Override
    public void writeToPacketNBT(NBTTagCompound tagCompound) {
        super.writeToPacketNBT(tagCompound);
        tagCompound.setInteger(BURN_TIME, burnTime);
    }

    @Override
    public void readFromPacketNBT(NBTTagCompound tagCompound) {
        super.readFromPacketNBT(tagCompound);
        burnTime = tagCompound.getInteger(BURN_TIME);
    }

}
