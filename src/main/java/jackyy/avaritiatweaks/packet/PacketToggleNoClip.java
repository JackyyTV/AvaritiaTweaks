package jackyy.avaritiatweaks.packet;

import io.netty.buffer.ByteBuf;
import jackyy.avaritiatweaks.tweaks.ModEventsHandler;
import morph.avaritia.handler.AvaritiaEventHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketToggleNoClip implements IMessage, IMessageHandler<PacketToggleNoClip, IMessage> {

    @Override
    public void fromBytes(ByteBuf buf) { }

    @Override
    public void toBytes(ByteBuf buf) { }

    @Override
    public IMessage onMessage(PacketToggleNoClip message, MessageContext context) {
        EntityPlayerMP playerMP = context.getServerHandler().playerEntity;
        if (AvaritiaEventHandler.isInfinite(playerMP)) {
            ModEventsHandler.toggleNoClip(playerMP);
        }
        return null;
    }

}
