/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.Vec3
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.models.entities.ModelWatcher;
import thaumcraft.common.entities.monster.EntityWatcher;

@SideOnly(value=Side.CLIENT)
public class RenderWatcher
extends RenderLiving {
    private static final ResourceLocation field_177114_e = new ResourceLocation("thaumcraft", "textures/models/watcher.png");
    private static final ResourceLocation field_177117_k = new ResourceLocation("thaumcraft", "textures/models/watcher_beam.png");
    int field_177115_a;

    public RenderWatcher() {
        super((ModelBase)new ModelWatcher(), 0.5f);
        this.field_177115_a = ((ModelWatcher)this.field_77045_g).func_178706_a();
    }

    private Vec3 func_177110_a(EntityLivingBase p_177110_1_, double p_177110_2_, float p_177110_4_) {
        double d1 = p_177110_1_.field_70142_S + (p_177110_1_.field_70165_t - p_177110_1_.field_70142_S) * (double)p_177110_4_;
        double d2 = p_177110_2_ + p_177110_1_.field_70137_T + (p_177110_1_.field_70163_u - p_177110_1_.field_70137_T) * (double)p_177110_4_;
        double d3 = p_177110_1_.field_70136_U + (p_177110_1_.field_70161_v - p_177110_1_.field_70136_U) * (double)p_177110_4_;
        return Vec3.func_72443_a((double)d1, (double)d2, (double)d3);
    }

    public void func_177109_a(EntityWatcher p_177109_1_, double p_177109_2_, double p_177109_4_, double p_177109_6_, float p_177109_8_, float p_177109_9_) {
        if (this.field_177115_a != ((ModelWatcher)this.field_77045_g).func_178706_a()) {
            this.field_77045_g = new ModelWatcher();
            this.field_177115_a = ((ModelWatcher)this.field_77045_g).func_178706_a();
        }
        super.func_76986_a((EntityLiving)p_177109_1_, p_177109_2_, p_177109_4_, p_177109_6_, p_177109_8_, p_177109_9_);
        EntityLivingBase entitylivingbase = p_177109_1_.getTargetedEntity();
        if (entitylivingbase != null) {
            float f2 = p_177109_1_.func_175477_p(p_177109_9_);
            Tessellator tessellator = Tessellator.field_78398_a;
            this.func_110776_a(field_177117_k);
            GL11.glTexParameterf((int)3553, (int)10242, (float)10497.0f);
            GL11.glTexParameterf((int)3553, (int)10243, (float)10497.0f);
            GL11.glDisable((int)2896);
            GL11.glDisable((int)2884);
            GL11.glDisable((int)3042);
            GL11.glDepthMask((boolean)true);
            float f3 = 240.0f;
            OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)f3, (float)f3);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)1);
            float f4 = (float)p_177109_1_.field_70170_p.func_82737_E() + p_177109_9_;
            float f5 = f4 * 0.5f % 1.0f;
            float f6 = p_177109_1_.func_70047_e();
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)p_177109_2_), (float)((float)p_177109_4_ + f6), (float)((float)p_177109_6_));
            Vec3 vec3 = this.func_177110_a(entitylivingbase, (double)entitylivingbase.field_70131_O * 0.5, p_177109_9_);
            Vec3 vec31 = this.func_177110_a((EntityLivingBase)p_177109_1_, f6, p_177109_9_);
            Vec3 vec32 = vec3.func_72444_a(vec31);
            double d3 = vec32.func_72433_c() + 1.0;
            vec32 = vec32.func_72432_b();
            float f7 = (float)Math.acos(vec32.field_72448_b);
            float f8 = (float)Math.atan2(vec32.field_72449_c, vec32.field_72450_a);
            GL11.glRotatef((float)((1.5707964f + -f8) * 57.295776f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(f7 * 57.295776f), (float)1.0f, (float)0.0f, (float)0.0f);
            boolean b0 = true;
            double d4 = (double)f4 * 0.05 * (1.0 - (double)(b0 & true) * 2.5);
            tessellator.func_78382_b();
            float f9 = f2 * f2;
            tessellator.func_78370_a(64 + (int)(f9 * 240.0f), 32 + (int)(f9 * 192.0f), 128 - (int)(f9 * 64.0f), 255);
            double d5 = (double)b0 * 0.2;
            double d6 = d5 * 1.41;
            double d7 = 0.0 + Math.cos(d4 + 2.356194490192345) * d6;
            double d8 = 0.0 + Math.sin(d4 + 2.356194490192345) * d6;
            double d9 = 0.0 + Math.cos(d4 + 0.7853981633974483) * d6;
            double d10 = 0.0 + Math.sin(d4 + 0.7853981633974483) * d6;
            double d11 = 0.0 + Math.cos(d4 + 3.9269908169872414) * d6;
            double d12 = 0.0 + Math.sin(d4 + 3.9269908169872414) * d6;
            double d13 = 0.0 + Math.cos(d4 + 5.497787143782138) * d6;
            double d14 = 0.0 + Math.sin(d4 + 5.497787143782138) * d6;
            double d15 = 0.0 + Math.cos(d4 + Math.PI) * d5;
            double d16 = 0.0 + Math.sin(d4 + Math.PI) * d5;
            double d17 = 0.0 + Math.cos(d4 + 0.0) * d5;
            double d18 = 0.0 + Math.sin(d4 + 0.0) * d5;
            double d19 = 0.0 + Math.cos(d4 + 1.5707963267948966) * d5;
            double d20 = 0.0 + Math.sin(d4 + 1.5707963267948966) * d5;
            double d21 = 0.0 + Math.cos(d4 + 4.71238898038469) * d5;
            double d22 = 0.0 + Math.sin(d4 + 4.71238898038469) * d5;
            double d23 = 0.0;
            double d24 = 0.4999;
            double d25 = -1.0f + f5;
            double d26 = d3 * (0.5 / d5) + d25;
            tessellator.func_78374_a(d15, d3, d16, d24, d26);
            tessellator.func_78374_a(d15, 0.0, d16, d24, d25);
            tessellator.func_78374_a(d17, 0.0, d18, d23, d25);
            tessellator.func_78374_a(d17, d3, d18, d23, d26);
            tessellator.func_78374_a(d19, d3, d20, d24, d26);
            tessellator.func_78374_a(d19, 0.0, d20, d24, d25);
            tessellator.func_78374_a(d21, 0.0, d22, d23, d25);
            tessellator.func_78374_a(d21, d3, d22, d23, d26);
            double d27 = 0.0;
            if (p_177109_1_.field_70173_aa % 2 == 0) {
                d27 = 0.5;
            }
            tessellator.func_78374_a(d7, d3, d8, 0.5, d27 + 0.5);
            tessellator.func_78374_a(d9, d3, d10, 1.0, d27 + 0.5);
            tessellator.func_78374_a(d13, d3, d14, 1.0, d27);
            tessellator.func_78374_a(d11, d3, d12, 0.5, d27);
            tessellator.func_78381_a();
            GL11.glPopMatrix();
        }
    }

    public void func_76986_a(EntityLiving entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
        this.func_177109_a((EntityWatcher)entity, x, y, z, p_76986_8_, partialTicks);
    }

    public void func_76986_a(EntityLivingBase entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
        this.func_177109_a((EntityWatcher)entity, x, y, z, p_76986_8_, partialTicks);
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return field_177114_e;
    }

    public void func_76986_a(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
        this.func_177109_a((EntityWatcher)entity, x, y, z, p_76986_8_, partialTicks);
    }
}

