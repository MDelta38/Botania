/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.ActiveRenderInfo
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.boss.BossStatus
 *  net.minecraft.entity.boss.IBossDisplayData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.Vec3
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.entities.monster.boss.EntityCultistPortal;

@SideOnly(value=Side.CLIENT)
public class RenderCultistPortal
extends Render {
    public static final ResourceLocation portaltex = new ResourceLocation("thaumcraft", "textures/misc/cultist_portal.png");

    public RenderCultistPortal() {
        this.field_76989_e = 0.1f;
        this.field_76987_f = 0.5f;
    }

    public void renderPortal(EntityCultistPortal portal, double px, double py, double pz, float par8, float f) {
        double d;
        if (BossStatus.field_82826_b < 100) {
            BossStatus.func_82824_a((IBossDisplayData)portal, (boolean)false);
        }
        long nt = System.nanoTime();
        long time = nt / 50000000L;
        float scaley = 1.5f;
        int e = (int)Math.min(50.0f, (float)portal.field_70173_aa + f);
        if (portal.field_70737_aN > 0) {
            d = Math.sin((double)(portal.field_70737_aN * 72) * Math.PI / 180.0);
            scaley = (float)((double)scaley - d / 4.0);
            e = (int)((double)e + 6.0 * d);
        }
        if (portal.pulse > 0) {
            d = Math.sin((double)(portal.pulse * 36) * Math.PI / 180.0);
            scaley = (float)((double)scaley + d / 4.0);
            e = (int)((double)e + 12.0 * d);
        }
        float scale = (float)e / 50.0f * 1.3f;
        py += (double)(portal.field_70131_O / 2.0f);
        float m = (1.0f - portal.func_110143_aJ() / portal.func_110138_aP()) / 3.0f;
        float bob = MathHelper.func_76126_a((float)((float)portal.field_70173_aa / (5.0f - 12.0f * m))) * m + m;
        float bob2 = MathHelper.func_76126_a((float)((float)portal.field_70173_aa / (6.0f - 15.0f * m))) * m + m;
        float alpha = 1.0f - bob;
        scaley -= bob / 4.0f;
        scale -= bob2 / 3.0f;
        UtilsFX.bindTexture(portaltex);
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
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
            tessellator.func_78369_a(1.0f, 1.0f, 1.0f, alpha);
            Vec3 v1 = Vec3.func_72443_a((double)(-arX - arYZ), (double)(-arXZ), (double)(-arZ - arXY));
            Vec3 v2 = Vec3.func_72443_a((double)(-arX + arYZ), (double)arXZ, (double)(-arZ + arXY));
            Vec3 v3 = Vec3.func_72443_a((double)(arX + arYZ), (double)arXZ, (double)(arZ + arXY));
            Vec3 v4 = Vec3.func_72443_a((double)(arX - arYZ), (double)(-arXZ), (double)(arZ - arXY));
            int frame = 15 - (int)time % 16;
            float f2 = (float)frame / 16.0f;
            float f3 = f2 + 0.0625f;
            float f4 = 0.0f;
            float f5 = 1.0f;
            tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
            tessellator.func_78374_a(px + v1.field_72450_a * (double)scale, py + v1.field_72448_b * (double)scaley, pz + v1.field_72449_c * (double)scale, (double)f3, (double)f4);
            tessellator.func_78374_a(px + v2.field_72450_a * (double)scale, py + v2.field_72448_b * (double)scaley, pz + v2.field_72449_c * (double)scale, (double)f3, (double)f5);
            tessellator.func_78374_a(px + v3.field_72450_a * (double)scale, py + v3.field_72448_b * (double)scaley, pz + v3.field_72449_c * (double)scale, (double)f2, (double)f5);
            tessellator.func_78374_a(px + v4.field_72450_a * (double)scale, py + v4.field_72448_b * (double)scaley, pz + v4.field_72449_c * (double)scale, (double)f2, (double)f4);
            tessellator.func_78381_a();
        }
        GL11.glDisable((int)32826);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderPortal((EntityCultistPortal)par1Entity, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return AbstractClientPlayer.field_110314_b;
    }
}

