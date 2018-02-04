package jackyy.avaritiatweaks.compat.botania.render;

import jackyy.avaritiatweaks.AvaritiaTweaks;
import jackyy.avaritiatweaks.compat.botania.tile.TileInfinitato;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import org.lwjgl.opengl.GL11;

public class RenderTileInfinitato extends TileEntitySpecialRenderer<TileInfinitato> {

    public static boolean drawHalo = true;
    private static final ResourceLocation texture = new ResourceLocation(AvaritiaTweaks.MODID,"textures/blocks/infinitato.png");
    public static final ResourceLocation halo = new ResourceLocation("avaritia", "textures/items/halo128.png");
    private static final ModelInfinitato model = new ModelInfinitato();

    @Override
    public void render(TileInfinitato te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        Minecraft mc = Minecraft.getMinecraft();
        RayTraceResult pos = mc.objectMouseOver;
        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.color(1F, 1F, 1F, 1F);
        GlStateManager.translate(x, y, z);
        GlStateManager.translate(0.5F, 1.5F, 0.5F);
        GlStateManager.scale(1F, -1F, -1F);
        int meta = te.getBlockMetadata();
        if (drawHalo) {
            mc.renderEngine.bindTexture(halo);
            GlStateManager.pushMatrix();
            double xdiff = (te.getPos().getX() + 0.5) - pos.hitVec.x;
            double ydiff = (te.getPos().getY() + 0.4) - pos.hitVec.y;
            double zdiff = (te.getPos().getZ() + 0.5) - pos.hitVec.z;
            double len = Math.sqrt(xdiff*xdiff + ydiff*ydiff + zdiff*zdiff);
            xdiff /= len;
            ydiff /= len;
            zdiff /= len;
            GlStateManager.translate(-xdiff, ydiff, zdiff);
            GlStateManager.scale(1F, -1F, -1F);
            GlStateManager.translate(0F, -1.15F, 0F);
            GlStateManager.rotate((float) -pos.hitVec.y, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate((float) pos.hitVec.x, 1.0F, 0.0F, 0.0F);
            float f = 1.6F;
            float f1 = 0.016666668F * f;
            GlStateManager.scale(f1, f1, f1);
            GlStateManager.disableLighting();
            GlStateManager.depthMask(false);
            GlStateManager.enableBlend();
            GlStateManager.disableAlpha();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
            int i=60;
            buffer.color(0.0F, 0.0F, 0.0F, 1.0F);
            buffer.pos(-i, -i, 0.0D).tex(0,0).endVertex();
            buffer.pos(-i, i, 0.0D).tex(1,0).endVertex();
            buffer.pos(i, i, 0.0D).tex(1,1).endVertex();
            buffer.pos(i, -i, 0.0D).tex(0,1).endVertex();
            tessellator.draw();
            GlStateManager.depthMask(true);
            GlStateManager.enableLighting();
            GlStateManager.disableBlend();
            GlStateManager.enableAlpha();
            GlStateManager.color(1F, 1F, 1F, 1F);
            GlStateManager.popMatrix();
        }
        float rotY = meta * 90F - 180F;
        GlStateManager.rotate(rotY, 0F, 1F, 0F);
        float jump = te.jumpTicks*0.5f;
        if(jump > 0)
            jump += partialTicks*0.5f;
        float up = (float) -Math.abs(Math.sin(jump / 10 * Math.PI)) * 0.2F;
        float rotZ = (float) Math.sin(jump / 10 * Math.PI) * 2;
        GlStateManager.translate(0F, up, 0F);
        GlStateManager.rotate(rotZ, 0F, 0F, 1F);
        mc.renderEngine.bindTexture(texture);
        model.render();
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
        if(!te.name.isEmpty() && pos != null && pos.hitVec.x == pos.hitVec.x && pos.hitVec.y == pos.hitVec.y && pos.hitVec.z == pos.hitVec.z) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(0F, -0.4F, 0F);
            GlStateManager.rotate((float) -pos.hitVec.y, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate((float) pos.hitVec.x, 1.0F, 0.0F, 0.0F);
            float f = 1.6F;
            float f1 = 0.016666668F * f;
            GlStateManager.scale(-f1, -f1, f1);
            GlStateManager.disableLighting();
            GlStateManager.translate(0.0F, 0F / f1, 0.0F);
            GlStateManager.depthMask(false);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.enableTexture2D();
            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
            int i = mc.fontRenderer.getStringWidth(te.name) / 2;
            buffer.color(0.0F, 0.0F, 0.0F, 0.25F);
            buffer.pos(-i - 1, -1.0D, 0.0D).endVertex();
            buffer.pos(-i - 1, 8.0D, 0.0D).endVertex();
            buffer.pos(i + 1, 8.0D, 0.0D).endVertex();
            buffer.pos(i + 1, -1.0D, 0.0D).endVertex();
            tessellator.draw();
            GlStateManager.enableTexture2D();
            GlStateManager.depthMask(true);
            mc.fontRenderer.drawString(te.name, -mc.fontRenderer.getStringWidth(te.name) / 2, 0, 0xFFFFFF);
            GlStateManager.enableLighting();
            GlStateManager.enableBlend();
            GlStateManager.color(1F, 1F, 1F, 1F);
            GlStateManager.scale(1F / -f1, 1F / -f1, 1F / f1);
            GlStateManager.popMatrix();
        }
        GlStateManager.popMatrix();
    }

}