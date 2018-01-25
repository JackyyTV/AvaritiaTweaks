package jackyy.avaritiatweaks.client;

import jackyy.avaritiatweaks.config.ModConfig;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.IKeyConflictContext;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class Keys {

    public static final KeyBinding NOCLIP = new KeyBinding("key.infinity_armor.noclip", new IKeyConflictContext() {
        @Override
        public boolean isActive() {
            return KeyConflictContext.IN_GAME.isActive();
        }
        @Override
        public boolean conflicts(IKeyConflictContext other) {
            return other == this || KeyConflictContext.IN_GAME.isActive();
        }
    }, Keyboard.KEY_APOSTROPHE, "key.categories.avaritiatweaks");

    public static void init() {
        if (ModConfig.infinityArmor.infinityArmorNoClip) {
            ClientRegistry.registerKeyBinding(NOCLIP);
        }
    }

}
