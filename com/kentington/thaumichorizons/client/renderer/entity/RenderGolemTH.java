/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.UtilsFX
 *  thaumcraft.client.renderers.entity.RenderGolemBase
 *  thaumcraft.client.renderers.models.entities.ModelGolemAccessories
 *  thaumcraft.common.config.ConfigItems
 */
package com.kentington.thaumichorizons.client.renderer.entity;

import com.kentington.thaumichorizons.client.renderer.model.ModelGolemTH;
import com.kentington.thaumichorizons.common.entities.EntityGolemTH;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.entity.RenderGolemBase;
import thaumcraft.client.renderers.models.entities.ModelGolemAccessories;
import thaumcraft.common.config.ConfigItems;

public class RenderGolemTH
extends RenderGolemBase {
    ResourceLocation voidGolem = new ResourceLocation("thaumichorizons", "textures/models/golem_void.png");
    ModelBase damage;
    ModelBase accessories = new ModelGolemAccessories(0.0f, 30.0f);

    public RenderGolemTH(ModelBase arg0) {
        super(arg0);
        if (arg0 instanceof ModelGolemTH) {
            ModelGolemTH mg = new ModelGolemTH(false);
            mg.pass = 2;
            this.damage = mg;
        }
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        if (entity instanceof EntityGolemTH) {
            EntityGolemTH golem = (EntityGolemTH)entity;
            if (golem.texture == null && golem.blocky != null && golem.blocky != Blocks.field_150350_a) {
                golem.loadTexture();
            } else if (golem.texture == null) {
                return this.voidGolem;
            }
            return golem.texture;
        }
        return null;
    }

    public void render(EntityGolemTH e, double par2, double par4, double par6, float par8, float par9) {
        super.func_76986_a((EntityLiving)e, par2, par4, par6, par8, par9);
    }

    public void func_76986_a(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
        this.render((EntityGolemTH)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.render((EntityGolemTH)par1Entity, par2, par4, par6, par8, par9);
    }

    protected int func_77032_a(EntityLivingBase entity, int pass, float par3) {
        if (pass == 0) {
            String deco = ((EntityGolemTH)entity).getGolemDecoration();
            if (((EntityGolemTH)entity).getCore() > -1) {
                GL11.glPushMatrix();
                GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glTranslatef((float)0.0875f, (float)-0.96f, (float)(0.15f + (deco.contains("P") ? 0.03f : 0.0f)));
                GL11.glScaled((double)0.175, (double)0.175, (double)0.175);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                Tessellator tessellator = Tessellator.field_78398_a;
                IIcon icon = ConfigItems.itemGolemCore.func_77617_a((int)((EntityGolemTH)entity).getCore());
                float f1 = icon.func_94212_f();
                float f2 = icon.func_94206_g();
                float f3 = icon.func_94209_e();
                float f4 = icon.func_94210_h();
                this.field_76990_c.field_78724_e.func_110577_a(TextureMap.field_110576_c);
                ItemRenderer.func_78439_a((Tessellator)tessellator, (float)f1, (float)f2, (float)f3, (float)f4, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.2f);
                GL11.glPopMatrix();
            }
            int upgrades = ((EntityGolemTH)entity).upgrades.length;
            float shift = 0.08f;
            GL11.glPushMatrix();
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            for (int a = 0; a < upgrades; ++a) {
                GL11.glPushMatrix();
                GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glTranslatef((float)(-0.05f - shift * (float)(upgrades - 1) / 2.0f + shift * (float)a), (float)-1.106f, (float)0.099f);
                GL11.glScaled((double)0.1, (double)0.1, (double)0.1);
                Tessellator tessellator = Tessellator.field_78398_a;
                IIcon icon = ConfigItems.itemGolemUpgrade.func_77617_a((int)((EntityGolemTH)entity).getUpgrade(a));
                float f1 = icon.func_94212_f();
                float f2 = icon.func_94206_g();
                float f3 = icon.func_94209_e();
                float f4 = icon.func_94210_h();
                this.field_76990_c.field_78724_e.func_110577_a(TextureMap.field_110576_c);
                tessellator.func_78382_b();
                tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
                tessellator.func_78374_a(0.0, 0.0, 0.0, (double)f1, (double)f4);
                tessellator.func_78374_a(1.0, 0.0, 0.0, (double)f3, (double)f4);
                tessellator.func_78374_a(1.0, 1.0, 0.0, (double)f3, (double)f2);
                tessellator.func_78374_a(0.0, 1.0, 0.0, (double)f1, (double)f2);
                tessellator.func_78381_a();
                GL11.glPopMatrix();
            }
            GL11.glDisable((int)3042);
            GL11.glPopMatrix();
        } else {
            if (pass == 1 && (((EntityGolemTH)entity).getGolemDecoration().length() > 0 || ((EntityGolemTH)entity).advanced)) {
                UtilsFX.bindTexture((String)"textures/models/golem_decoration.png");
                this.func_77042_a(this.accessories);
                return 1;
            }
            if (pass == 2 && ((EntityGolemTH)entity).getHealthPercentage() < 1.0f) {
                UtilsFX.bindTexture((String)"textures/models/golem_damage.png");
                this.func_77042_a(this.damage);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)(1.0f - ((EntityGolemTH)entity).getHealthPercentage()));
                return 2;
            }
        }
        return -1;
    }
}

