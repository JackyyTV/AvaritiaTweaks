package jackyy.avaritiatweaks.packet;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {

    public static SimpleNetworkWrapper INSTANCE;
    private static int packetId = 0;

    public static void registerMessages(String channelName) {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
        INSTANCE.registerMessage(PacketToggleNoClip.class, PacketToggleNoClip.class, packetId++, Side.SERVER);
    }

}
