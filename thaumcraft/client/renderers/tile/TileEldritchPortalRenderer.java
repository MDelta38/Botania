/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ActiveRenderInfo
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.Vec3
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.tiles.TileEldritchPortal;

public class TileEldritchPortalRenderer
extends TileEntitySpecialRenderer {
    public static final ResourceLocation portaltex = new ResourceLocation("thaumcraft", "textures/misc/eldritch_portal.png");

    public void func_147500_a(TileEntity te, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        if (te.func_145831_w() != null) {
            this.renderPortal((TileEldritchPortal)te, x, y, z, f);
        }
        GL11.glPopMatrix();
    }

    private void renderPortal(TileEldritchPortal te, double x, double y, double z, float f) {
        long nt = System.nanoTime();
        long time = nt / 50000000L;
        int c = (int)Math.min(30.0f, (float)te.opencount + f);
        int e = (int)Math.min(5.0f, (float)te.opencount + f);
        float scale = (float)e / 5.0f;
        float scaley = (float)c / 30.0f;
        UtilsFX.bindTexture(portaltex);
        GL11.glPushMatrix();
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)1.0f, (float)0.0f, (float)1.0f, (float)1.0f);
        if (Minecraft.func_71410_x().field_71451_h instanceof EntityPlayer) {
            Tessellator tessellator = Tessellator.field_78398_a;
            float arX = ActiveRenderInfo.field_74588_d;
            float arZ = ActiveRenderInfo.field_74586_f;
            float arYZ = ActiveRenderInfo.field_74587_g;
            float arXY = ActiveRenderInfo.field_74596_h;
            float arXZ = ActiveRenderInfo.field_74589_e;
            EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().field_71451_h;
            double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * (double)f;
            double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * (double)f;
            double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * (double)f;
            tessellator.func_78382_b();
            tessellator.func_78380_c(220);
            tessellator.func_78369_a(1.0f, 1.0f, 1.0f, 1.0f);
            double px = x + 0.5;
            double py = y + 0.5;
            double pz = z + 0.5;
            Vec3 v1 = Vec3.func_72443_a((double)(-arX - arYZ), (double)(-arXZ), (double)(-arZ - arXY));
            Vec3 v2 = Vec3.func_72443_a((double)(-arX + arYZ), (double)arXZ, (double)(-arZ + arXY));
            Vec3 v3 = Vec3.func_72443_a((double)(arX + arYZ), (double)arXZ, (double)(arZ + arXY));
            Vec3 v4 = Vec3.func_72443_a((double)(arX - arYZ), (double)(-arXZ), (double)(arZ - arXY));
            int frame = (int)time % 16;
            float f2 = (float)frame / 16.0f;
            float f3 = f2 + 0.0625f;
            float f4 = 0.0f;
            float f5 = 1.0f;
            tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
            tessellator.func_78374_a(px + v1.field_72450_a * (double)scale, py + v1.field_72448_b * (double)scaley, pz + v1.field_72449_c * (double)scale, (double)f2, (double)f5);
            tessellator.func_78374_a(px + v2.field_72450_a * (double)scale, py + v2.field_72448_b * (double)scaley, pz + v2.field_72449_c * (double)scale, (double)f3, (double)f5);
            tessellator.func_78374_a(px + v3.field_72450_a * (double)scale, py + v3.field_72448_b * (double)scaley, pz + v3.field_72449_c * (double)scale, (double)f3, (double)f4);
            tessellator.func_78374_a(px + v4.field_72450_a * (double)scale, py + v4.field_72448_b * (double)scaley, pz + v4.field_72449_c * (double)scale, (double)f2, (double)f4);
            tessellator.func_78381_a();
        }
        GL11.glDisable((int)3042);
        GL11.glDepthMask((boolean)true);
        GL11.glPopMatrix();
    }
}

