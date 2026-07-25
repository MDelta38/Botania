/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.boss.BossStatus
 *  net.minecraft.entity.boss.IBossDisplayData
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.EnumDifficulty
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumDifficulty;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.models.entities.ModelEldritchGuardian;
import thaumcraft.common.config.Config;
import thaumcraft.common.entities.monster.boss.EntityEldritchWarden;

@SideOnly(value=Side.CLIENT)
public class RenderEldritchGuardian
extends RenderLiving {
    protected ModelEldritchGuardian modelMain;
    private static final ResourceLocation[] skin = new ResourceLocation[]{new ResourceLocation("thaumcraft", "textures/models/eldritch_guardian.png"), new ResourceLocation("thaumcraft", "textures/models/eldritch_warden.png")};

    public RenderEldritchGuardian(ModelEldritchGuardian par1ModelBiped, float par2) {
        super((ModelBase)par1ModelBiped, par2);
        this.modelMain = par1ModelBiped;
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return entity instanceof EntityEldritchWarden ? skin[1] : skin[0];
    }

    protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2) {
        if (par1EntityLiving instanceof EntityEldritchWarden) {
            BossStatus.func_82824_a((IBossDisplayData)((EntityEldritchWarden)par1EntityLiving), (boolean)false);
            GL11.glScalef((float)1.5f, (float)1.5f, (float)1.5f);
        }
    }

    public void doRenderLiving(EntityLiving guardian, double par2, double par4, double par6, float par8, float par9) {
        GL11.glEnable((int)3042);
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        GL11.glBlendFunc((int)770, (int)771);
        float base = 1.0f;
        double d3 = par4 - (double)guardian.field_70129_M;
        if (guardian instanceof EntityEldritchWarden) {
            d3 -= (double)(guardian.field_70131_O * ((float)((EntityEldritchWarden)guardian).getSpawnTimer() / 150.0f));
        } else {
            double d8;
            EntityLivingBase e = Minecraft.func_71410_x().field_71451_h;
            float d6 = e.field_70170_p.field_73013_u == EnumDifficulty.HARD ? 576.0f : 1024.0f;
            float d7 = 256.0f;
            base = guardian.field_70170_p != null && guardian.field_70170_p.field_73011_w.field_76574_g == Config.dimensionOuterId ? 1.0f : ((d8 = guardian.func_70092_e(e.field_70165_t, e.field_70163_u, e.field_70161_v)) < 256.0 ? 0.6f : (float)(1.0 - Math.min((double)(d6 - d7), d8 - (double)d7) / (double)(d6 - d7)) * 0.6f);
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)base);
        super.func_76986_a(guardian, par2, d3, par6, par8, par9);
        GL11.glDisable((int)3042);
        GL11.glAlphaFunc((int)516, (float)0.1f);
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderLiving((EntityLiving)par1Entity, par2, par4, par6, par8, par9);
    }
}

