/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.models.entities.ModelEldritchCrab;

@SideOnly(value=Side.CLIENT)
public class RenderEldritchCrab
extends RenderLiving {
    private static final ResourceLocation[] skin = new ResourceLocation[]{new ResourceLocation("thaumcraft", "textures/models/crab.png"), new ResourceLocation("thaumcraft", "textures/models/craboverlay.png")};

    public RenderEldritchCrab() {
        super((ModelBase)new ModelEldritchCrab(), 1.0f);
        this.func_77042_a(new ModelEldritchCrab());
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return skin[0];
    }

    public void renderCrab(EntityLiving crab, double par2, double par4, double par6, float par8, float par9) {
        super.func_76986_a(crab, par2, par4, par6, par8, par9);
    }

    protected int func_77032_a(EntityLivingBase par1EntityLiving, int par2, float par3) {
        if (par2 != 0) {
            return -1;
        }
        this.func_110776_a(skin[1]);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        if (par1EntityLiving.func_82150_aj()) {
            GL11.glDepthMask((boolean)false);
        } else {
            GL11.glDepthMask((boolean)true);
        }
        int c0 = 200;
        int j = c0 % 65536;
        int k = c0 / 65536;
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)j / 1.0f), (float)((float)k / 1.0f));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        return 1;
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderCrab((EntityLiving)par1Entity, par2, par4, par6, par8, par9);
    }
}

