package jackyy.avaritiatweaks.compat.botania.render;

import jackyy.avaritiatweaks.AvaritiaTweaks;
import jackyy.avaritiatweaks.compat.botania.BotaniaCompat;
import jackyy.avaritiatweaks.compat.botania.tile.TileInfinitato;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import org.lwjgl.opengl.GL11;

public class RenderTileInfinitato extends TileEntitySpecialRenderer<TileInfinitato> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(AvaritiaTweaks.MODID,"textures/blocks/infinitato.png");
    private static final ModelInfinitato MODEL = new ModelInfinitato();

    @Override
    public void renderTileEntityAt(TileInfinitato te, double x, double y, double z, float partialTicks, int destroyStage) {
        if (!te.getWorld().isBlockLoaded(te.getPos(), false)
                || te.getWorld().getBlockState(te.getPos()).getBlock() != BotaniaCompat.infinitato)
            return;
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer buffer = tessellator.getBuffer();
        Minecraft mc = Minecraft.getMinecraft();
        RayTraceResult pos = mc.objectMouseOver;
        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.color(1F, 1F, 1F, 1F);
        GlStateManager.translate(x, y, z);
        GlStateManager.translate(0.5F, 1.5F, 0.5F);
        GlStateManager.scale(1F, -1F, -1F);
        int meta = te.getBlockMetadata();
        float rotY = meta * 90F - 180F;
        GlStateManager.rotate(rotY, 0F, 1F, 0F);
        float jump = te.jumpTicks*0.5f;
        if(jump > 0)
            jump += partialTicks*0.5f;
        float up = (float) -Math.abs(Math.sin(jump / 10 * Math.PI)) * 0.2F;
        float rotZ = (float) Math.sin(jump / 10 * Math.PI) * 2;
        GlStateManager.translate(0F, up, 0F);
        GlStateManager.rotate(rotZ, 0F, 0F, 1F);
        mc.renderEngine.bindTexture(TEXTURE);
        MODEL.render();
        GlStateManager.pushMatrix();
        mc.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        float scale = 1F / 4F;
        GlStateManager.translate(0F, 1F, 0F);
        GlStateManager.scale(scale, scale, scale);
        GlStateManager.popMatrix();
        GlStateManager.rotate(-rotZ, 0F, 0F, 1F);
        GlStateManager.rotate(-rotY, 0F, 1F, 0F);
        GlStateManager.color(1F, 1F, 1F);
        GlStateManager.scale(1F, -1F, -1F);
        if (!te.name.isEmpty() && pos != null && te.getPos().equals(pos.getBlockPos())) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(0F, -0.6F, 0F);
            GlStateManager.rotate(-mc.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(mc.getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
            float f = 1.6F;
            float f1 = 0.016666668F * f;
            GlStateManager.scale(-f1, -f1, f1);
            GlStateManager.disableLighting();
            GlStateManager.translate(0.0F, 0F / f1, 0.0F);
            GlStateManager.depthMask(false);
            GlStateManager.enableBlend();
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GlStateManager.disableTexture2D();
            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
            int i = mc.fontRenderer.getStringWidth(te.name) / 2;
            buffer.pos(-i - 1, -1.0D, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
            buffer.pos(-i - 1, 8.0D, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
            buffer.pos(i + 1, 8.0D, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
            buffer.pos(i + 1, -1.0D, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
            tessellator.draw();
            GlStateManager.enableTexture2D();
            GlStateManager.depthMask(true);
            mc.fontRenderer.drawString(te.name, -mc.fontRenderer.getStringWidth(te.name) / 2, 0, 0xFFFFFF);
            GlStateManager.enableLighting();
            GlStateManager.disableBlend();
            GlStateManager.color(1F, 1F, 1F, 1F);
            GlStateManager.scale(1F / -f1, 1F / -f1, 1F / f1);
            GlStateManager.popMatrix();
        }
        GlStateManager.popMatrix();
    }

}