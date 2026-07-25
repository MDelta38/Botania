/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 */
package thaumcraft.client.renderers.models.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import thaumcraft.common.entities.monster.EntityWatcher;

@SideOnly(value=Side.CLIENT)
public class ModelWatcher
extends ModelBase {
    private ModelRenderer guardianBody;
    private ModelRenderer guardianEye;
    private ModelRenderer[] guardianSpines;
    private ModelRenderer[] guardianTail;
    private static final String __OBFID = "CL_00002628";

    public ModelWatcher() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.guardianSpines = new ModelRenderer[12];
        this.guardianBody = new ModelRenderer((ModelBase)this);
        this.guardianBody.func_78784_a(0, 0).func_78789_a(-6.0f, 10.0f, -8.0f, 12, 12, 16);
        this.guardianBody.func_78784_a(0, 28).func_78789_a(-8.0f, 10.0f, -6.0f, 2, 12, 12);
        this.guardianBody.field_78809_i = true;
        this.guardianBody.func_78784_a(0, 28).func_78789_a(6.0f, 10.0f, -6.0f, 2, 12, 12);
        this.guardianBody.field_78809_i = false;
        this.guardianBody.func_78784_a(16, 40).func_78789_a(-6.0f, 8.0f, -6.0f, 12, 2, 12);
        this.guardianBody.func_78784_a(16, 40).func_78789_a(-6.0f, 22.0f, -6.0f, 12, 2, 12);
        for (int i = 0; i < this.guardianSpines.length; ++i) {
            this.guardianSpines[i] = new ModelRenderer((ModelBase)this, 0, 0);
            this.guardianSpines[i].func_78789_a(-1.0f, -4.5f, -1.0f, 2, 9, 2);
            this.guardianBody.func_78792_a(this.guardianSpines[i]);
        }
        this.guardianEye = new ModelRenderer((ModelBase)this, 8, 0);
        this.guardianEye.func_78789_a(-1.0f, 15.0f, 0.0f, 2, 2, 1);
        this.guardianBody.func_78792_a(this.guardianEye);
        this.guardianTail = new ModelRenderer[3];
        this.guardianTail[0] = new ModelRenderer((ModelBase)this, 40, 0);
        this.guardianTail[0].func_78789_a(-2.0f, 14.0f, 7.0f, 4, 4, 8);
        this.guardianTail[1] = new ModelRenderer((ModelBase)this, 0, 54);
        this.guardianTail[1].func_78789_a(0.0f, 14.0f, 0.0f, 3, 3, 7);
        this.guardianTail[2] = new ModelRenderer((ModelBase)this);
        this.guardianTail[2].func_78784_a(41, 32).func_78789_a(0.0f, 14.0f, 0.0f, 2, 2, 6);
        this.guardianTail[2].func_78784_a(25, 19).func_78789_a(1.0f, 10.5f, 3.0f, 1, 9, 9);
        this.guardianBody.func_78792_a(this.guardianTail[0]);
        this.guardianTail[0].func_78792_a(this.guardianTail[1]);
        this.guardianTail[1].func_78792_a(this.guardianTail[2]);
    }

    public int func_178706_a() {
        return 54;
    }

    public void func_78088_a(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        this.func_78087_a(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
        this.guardianBody.func_78785_a(p_78088_7_);
    }

    public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
        EntityWatcher entityguardian = (EntityWatcher)p_78087_7_;
        float f6 = p_78087_3_ - (float)entityguardian.field_70173_aa;
        this.guardianBody.field_78796_g = p_78087_4_ / 57.295776f;
        this.guardianBody.field_78795_f = p_78087_5_ / 57.295776f;
        float[] afloat = new float[]{1.75f, 0.25f, 0.0f, 0.0f, 0.5f, 0.5f, 0.5f, 0.5f, 1.25f, 0.75f, 0.0f, 0.0f};
        float[] afloat1 = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.25f, 1.75f, 1.25f, 0.75f, 0.0f, 0.0f, 0.0f, 0.0f};
        float[] afloat2 = new float[]{0.0f, 0.0f, 0.25f, 1.75f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.75f, 1.25f};
        float[] afloat3 = new float[]{0.0f, 0.0f, 8.0f, -8.0f, -8.0f, 8.0f, 8.0f, -8.0f, 0.0f, 0.0f, 8.0f, -8.0f};
        float[] afloat4 = new float[]{-8.0f, -8.0f, -8.0f, -8.0f, 0.0f, 0.0f, 0.0f, 0.0f, 8.0f, 8.0f, 8.0f, 8.0f};
        float[] afloat5 = new float[]{8.0f, -8.0f, 0.0f, 0.0f, -8.0f, -8.0f, 8.0f, 8.0f, 8.0f, -8.0f, 0.0f, 0.0f};
        float f7 = (1.0f - entityguardian.func_175469_o(f6)) * 0.55f;
        for (int i = 0; i < 12; ++i) {
            this.guardianSpines[i].field_78795_f = (float)Math.PI * afloat[i];
            this.guardianSpines[i].field_78796_g = (float)Math.PI * afloat1[i];
            this.guardianSpines[i].field_78808_h = (float)Math.PI * afloat2[i];
            this.guardianSpines[i].field_78800_c = afloat3[i] * (1.0f + MathHelper.func_76134_b((float)(p_78087_3_ * 1.5f + (float)i)) * 0.01f - f7);
            this.guardianSpines[i].field_78797_d = 16.0f + afloat4[i] * (1.0f + MathHelper.func_76134_b((float)(p_78087_3_ * 1.5f + (float)i)) * 0.01f - f7);
            this.guardianSpines[i].field_78798_e = afloat5[i] * (1.0f + MathHelper.func_76134_b((float)(p_78087_3_ * 1.5f + (float)i)) * 0.01f - f7);
        }
        this.guardianEye.field_78798_e = -8.25f;
        EntityLivingBase object = Minecraft.func_71410_x().field_71451_h;
        if (entityguardian.func_175474_cn()) {
            object = entityguardian.getTargetedEntity();
        }
        if (object != null) {
            Vec3 vec3 = this.getPositionEyes((Entity)object, 0.0f);
            Vec3 vec31 = this.getPositionEyes(p_78087_7_, 0.0f);
            double d0 = vec3.field_72448_b - vec31.field_72448_b;
            this.guardianEye.field_78797_d = d0 > 0.0 ? 0.0f : 1.0f;
            Vec3 vec32 = entityguardian.func_70676_i(0.0f);
            vec32 = Vec3.func_72443_a((double)vec32.field_72450_a, (double)0.0, (double)vec32.field_72449_c);
            Vec3 vec33 = Vec3.func_72443_a((double)(vec31.field_72450_a - vec3.field_72450_a), (double)0.0, (double)(vec31.field_72449_c - vec3.field_72449_c)).func_72432_b();
            vec33.func_72442_b(1.5707964f);
            double d1 = vec32.func_72430_b(vec33);
            this.guardianEye.field_78800_c = MathHelper.func_76129_c((float)((float)Math.abs(d1))) * 2.0f * (float)Math.signum(d1);
        }
        this.guardianEye.field_78806_j = true;
        float f8 = entityguardian.func_175471_a(f6);
        this.guardianTail[0].field_78796_g = MathHelper.func_76126_a((float)f8) * (float)Math.PI * 0.05f;
        this.guardianTail[1].field_78796_g = MathHelper.func_76126_a((float)f8) * (float)Math.PI * 0.1f;
        this.guardianTail[1].field_78800_c = -1.5f;
        this.guardianTail[1].field_78797_d = 0.5f;
        this.guardianTail[1].field_78798_e = 14.0f;
        this.guardianTail[2].field_78796_g = MathHelper.func_76126_a((float)f8) * (float)Math.PI * 0.15f;
        this.guardianTail[2].field_78800_c = 0.5f;
        this.guardianTail[2].field_78797_d = 0.5f;
        this.guardianTail[2].field_78798_e = 6.0f;
    }

    private Vec3 getPositionEyes(Entity e, float p_174824_1_) {
        if (p_174824_1_ == 1.0f) {
            return Vec3.func_72443_a((double)e.field_70165_t, (double)(e.field_70163_u + (double)e.func_70047_e()), (double)e.field_70161_v);
        }
        double d0 = e.field_70169_q + (e.field_70165_t - e.field_70169_q) * (double)p_174824_1_;
        double d1 = e.field_70167_r + (e.field_70163_u - e.field_70167_r) * (double)p_174824_1_ + (double)e.func_70047_e();
        double d2 = e.field_70166_s + (e.field_70161_v - e.field_70166_s) * (double)p_174824_1_;
        return Vec3.func_72443_a((double)d0, (double)d1, (double)d2);
    }
}

