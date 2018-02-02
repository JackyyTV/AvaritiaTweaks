package jackyy.avaritiatweaks.proxy;

import jackyy.avaritiatweaks.AvaritiaTweaks;
import jackyy.avaritiatweaks.packet.PacketHandler;
import jackyy.avaritiatweaks.tweaks.ModEventsHandler;
import jackyy.avaritiatweaks.tweaks.ModTweaks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        PacketHandler.registerMessages(AvaritiaTweaks.MODID);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ModEventsHandler());
    }

    @SubscribeEvent
    public void onItemRegistry(RegistryEvent.Register<Item> e) {
        ModTweaks.initItems(e);
    }

    @SubscribeEvent
    public void onBlockRegistry(RegistryEvent.Register<Block> e) {
        ModTweaks.initBlocks(e);
    }

    @SubscribeEvent
    public void onRecipeRegistry(RegistryEvent.Register<IRecipe> e) {
        ModTweaks.initRecipes(e);
    }

    public void init(FMLInitializationEvent event) {
        ModTweaks.initIntegrations();
    }

    public void postInit(FMLPostInitializationEvent event) {
    }

}
