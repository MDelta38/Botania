/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.entity;

import cpw.mods.fml.client.FMLClientHandler;
import java.util.Random;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.common.entities.EntitySpecialItem;

public class RenderSpecialItem
extends Render {
    private RenderBlocks renderBlocks = new RenderBlocks();
    private Random random = new Random();
    public boolean field_77024_a = true;
    public float zLevel = 0.0f;

    public RenderSpecialItem() {
        this.field_76989_e = 0.15f;
        this.field_76987_f = 0.75f;
    }

    public void doRenderItem(EntitySpecialItem par1EntityItem, double par2, double par4, double par6, float par8, float par9) {
        this.random.setSeed(187L);
        float var11 = MathHelper.func_76126_a((float)(((float)par1EntityItem.field_70292_b + par9) / 10.0f + par1EntityItem.field_70290_d)) * 0.1f + 0.1f;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)par2), (float)((float)par4 + var11 + 0.15f), (float)((float)par6));
        int q = !FMLClientHandler.instance().getClient().field_71474_y.field_74347_j ? 5 : 10;
        Tessellator tessellator = Tessellator.field_78398_a;
        RenderHelper.func_74518_a();
        float f1 = (float)par1EntityItem.field_70292_b / 500.0f;
        float f3 = 0.9f;
        float f2 = 0.0f;
        Random random = new Random(245L);
        GL11.glDisable((int)3553);
        GL11.glShadeModel((int)7425);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        GL11.glDisable((int)3008);
        GL11.glEnable((int)2884);
        GL11.glDepthMask((boolean)false);
        GL11.glPushMatrix();
        for (int i = 0; i < q; ++i) {
            GL11.glRotatef((float)(random.nextFloat() * 360.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)(random.nextFloat() * 360.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(random.nextFloat() * 360.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)(random.nextFloat() * 360.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)(random.nextFloat() * 360.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(random.nextFloat() * 360.0f + f1 * 360.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            tessellator.func_78371_b(6);
            float fa = random.nextFloat() * 20.0f + 5.0f + f2 * 10.0f;
            float f4 = random.nextFloat() * 2.0f + 1.0f + f2 * 2.0f;
            tessellator.func_78384_a(0xFFFFFF, (int)(255.0f * (1.0f - f2)));
            tessellator.func_78377_a(0.0, 0.0, 0.0);
            tessellator.func_78384_a(0xFF00FF, 0);
            tessellator.func_78377_a(-0.866 * (double)(f4 /= 30.0f / ((float)Math.min(par1EntityItem.field_70292_b, 10) / 10.0f)), (double)(fa /= 30.0f / ((float)Math.min(par1EntityItem.field_70292_b, 10) / 10.0f)), (double)(-0.5f * f4));
            tessellator.func_78377_a(0.866 * (double)f4, (double)fa, (double)(-0.5f * f4));
            tessellator.func_78377_a(0.0, (double)fa, (double)(1.0f * f4));
            tessellator.func_78377_a(-0.866 * (double)f4, (double)fa, (double)(-0.5f * f4));
            tessellator.func_78381_a();
        }
        GL11.glPopMatrix();
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)2884);
        GL11.glDisable((int)3042);
        GL11.glShadeModel((int)7424);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)3008);
        RenderHelper.func_74519_b();
        GL11.glPopMatrix();
        RenderItem ri = new RenderItem();
        ri.func_76976_a(RenderManager.field_78727_a);
        ItemStack var10 = par1EntityItem.func_92059_d();
        if (var10 != null) {
            EntityItem ei = new EntityItem(par1EntityItem.field_70170_p, par1EntityItem.field_70165_t, par1EntityItem.field_70163_u, par1EntityItem.field_70161_v, var10);
            ei.field_70292_b = par1EntityItem.field_70292_b;
            ei.field_70290_d = par1EntityItem.field_70290_d;
            ri.func_76986_a(ei, par2, par4, par6, par8, par9);
        }
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderItem((EntitySpecialItem)par1Entity, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return AbstractClientPlayer.field_110314_b;
    }
}

