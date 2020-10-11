package jackyy.avaritiatweaks.compat.botania.subtile;

import jackyy.avaritiatweaks.config.ModConfig;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileGenerating;
import vazkii.botania.common.core.handler.MethodHandles;

import java.util.List;

public class SubTileSoarleander extends SubTileGenerating {

    public static LexiconEntry lexicon;
    private static final String BURN_TIME = "burnTime";
    private static final int RANGE = 3;
    int burnTime = 0;

    @Override
    public void onUpdate() {
        super.onUpdate();
        if(linkedCollector != null) {
            if(burnTime == 0) {
                if(mana < getMaxMana()) {
                    boolean burnt = false;
                    int slowdown = getSlowdownFactor();
                    List<EntityItem> items = supertile.getWorld().getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(supertile.getPos().add(-RANGE, -RANGE, -RANGE), supertile.getPos().add(RANGE + 1, RANGE + 1, RANGE + 1)));
                    for (EntityItem item : items) {
                        int itemAge;
                        try {
                            itemAge = (int) MethodHandles.itemAge_getter.invokeExact(item);
                        } catch (Throwable t) {
                            continue;
                        }
                        if (itemAge >= 59 + slowdown && !item.isDead) {
                            ItemStack stack = item.getEntityItem();
                            if (stack.getItem().hasContainerItem(stack))
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
                            if (burnTime > 0 && stack.stackSize > 0) {
                                this.burnTime = burnTime;
                                if (!supertile.getWorld().isRemote) {
                                    stack.stackSize--;
                                    supertile.getWorld().playSound(null, supertile.getPos(), SoundEvents.ENTITY_CHICKEN_HURT, SoundCategory.BLOCKS, 0.2F, 1F);
                                    if (stack.stackSize == 0)
                                        item.setDead();
                                    burnt = true;
                                } else {
                                    item.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, item.posX, item.posY + 0.1, item.posZ, 0.0D, 0.0D, 0.0D);
                                    item.worldObj.spawnParticle(EnumParticleTypes.FLAME, item.posX, item.posY, item.posZ, 0.0D, 0.0D, 0.0D);
                                }
                                break;
                            }
                        }
                    }
                    if (burnt)
                        sync();
                }
            } else {
                if (supertile.getWorld().rand.nextInt(10) == 0)
                    supertile.getWorld().spawnParticle(EnumParticleTypes.FLAME, supertile.getPos().getX() + 0.4 + Math.random() * 0.2, supertile.getPos().getY() + 0.65, supertile.getPos().getZ() + 0.4 + Math.random() * 0.2, 0.0D, 0.0D, 0.0D);
                burnTime--;
            }
        }
    }

    @Override
    public int getMaxMana() {
        return ModConfig.Compats.Botania.soarleander.maxMana;
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
        return ModConfig.Compats.Botania.soarleander.generationCycleDelay;
    }

    @Override
    public int getValueForPassiveGeneration() {
        return ModConfig.Compats.Botania.soarleander.generationCycleMana;
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
