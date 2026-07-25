/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  net.minecraftforge.client.MinecraftForgeClient
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.client.model.ModelOwl;
import com.emoniph.witchery.entity.EntityOwl;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderOwl
extends RenderLiving {
    private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/owl.png");
    public static final float[][] fleeceColorTable = new float[][]{{1.0f, 1.0f, 1.0f}, {0.85f, 0.5f, 0.2f}, {0.7f, 0.3f, 0.85f}, {0.4f, 0.6f, 0.85f}, {0.9f, 0.9f, 0.2f}, {0.5f, 0.8f, 0.1f}, {0.95f, 0.5f, 0.65f}, {0.3f, 0.3f, 0.3f}, {0.6f, 0.6f, 0.6f}, {0.3f, 0.5f, 0.6f}, {0.5f, 0.25f, 0.7f}, {0.2f, 0.3f, 0.7f}, {0.4f, 0.3f, 0.2f}, {0.4f, 0.5f, 0.2f}, {0.6f, 0.2f, 0.2f}, {0.1f, 0.1f, 0.1f}};

    public RenderOwl(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    public void doRenderOwl(EntityOwl entity, double par2, double par4, double par6, float par8, float par9) {
        float f1 = 1.0f;
        int j = entity.getFeatherColor();
        if (j == 0) {
            GL11.glColor3f((float)(f1 * fleeceColorTable[j][0]), (float)(f1 * fleeceColorTable[j][1]), (float)(f1 * fleeceColorTable[j][2]));
        } else {
            float alpha = 0.84313726f;
            float bR = 0.41568628f;
            float bG = 0.3137255f;
            float bB = 0.24313726f;
            GL11.glColor3f((float)(f1 * fleeceColorTable[j][0] * 0.15686274f + 0.41568628f), (float)(f1 * fleeceColorTable[j][1] * 0.15686274f + 0.3137255f), (float)(f1 * fleeceColorTable[j][2] * 0.15686274f + 0.24313726f));
        }
        super.func_76986_a((EntityLiving)entity, par2, par4, par6, par8, par9);
    }

    protected void rotateOwlCorpse(EntityOwl entity, float par2, float par3, float par4) {
        super.func_77043_a((EntityLivingBase)entity, par2, par3, par4);
    }

    public void func_76986_a(EntityLiving entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderOwl((EntityOwl)entity, par2, par4, par6, par8, par9);
    }

    protected void func_77043_a(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
        this.rotateOwlCorpse((EntityOwl)par1EntityLivingBase, par2, par3, par4);
    }

    public void func_76986_a(EntityLivingBase par1, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderOwl((EntityOwl)par1, par2, par4, par6, par8, par9);
    }

    public void func_76986_a(Entity entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderOwl((EntityOwl)entity, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return TEXTURE_URL;
    }

    protected void func_77029_c(EntityLivingBase par1EntityLiving, float par2) {
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        super.func_77029_c(par1EntityLiving, par2);
        ItemStack itemstack = par1EntityLiving.func_70694_bm();
        if (itemstack != null && itemstack.func_77973_b() != null) {
            boolean is3D;
            float f1;
            Item item = itemstack.func_77973_b();
            GL11.glPushMatrix();
            if (this.field_77045_g.field_78091_s) {
                f1 = 0.5f;
                GL11.glTranslatef((float)0.0f, (float)0.625f, (float)0.0f);
                GL11.glRotatef((float)-20.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
                GL11.glScalef((float)f1, (float)f1, (float)f1);
            }
            if (par1EntityLiving != null && par1EntityLiving instanceof EntityOwl && ModelOwl.isLanded((Entity)par1EntityLiving)) {
                GL11.glTranslatef((float)-0.0625f, (float)1.1375f, (float)0.0625f);
            } else {
                GL11.glTranslatef((float)-0.0625f, (float)1.375f, (float)0.3f);
            }
            IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer((ItemStack)itemstack, (IItemRenderer.ItemRenderType)IItemRenderer.ItemRenderType.EQUIPPED);
            boolean bl = is3D = customRenderer != null && customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack, IItemRenderer.ItemRendererHelper.BLOCK_3D);
            if (item instanceof ItemBlock && (is3D || RenderBlocks.func_147739_a((int)Block.func_149634_a((Item)item).func_149645_b()))) {
                f1 = 0.5f;
                GL11.glTranslatef((float)0.0f, (float)0.1875f, (float)-0.3125f);
                GL11.glRotatef((float)20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glScalef((float)(-(f1 *= 0.75f)), (float)(-f1), (float)f1);
            } else {
                f1 = 0.375f;
                GL11.glTranslatef((float)0.25f, (float)0.1875f, (float)-0.3f);
                GL11.glScalef((float)f1, (float)f1, (float)f1);
                GL11.glRotatef((float)60.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)-120.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            }
            if (itemstack.func_77973_b().func_77623_v()) {
                for (int i = 0; i < itemstack.func_77973_b().getRenderPasses(itemstack.func_77960_j()); ++i) {
                    int j = itemstack.func_77973_b().func_82790_a(itemstack, i);
                    float f2 = (float)(j >> 16 & 0xFF) / 255.0f;
                    float f3 = (float)(j >> 8 & 0xFF) / 255.0f;
                    float f4 = (float)(j & 0xFF) / 255.0f;
                    GL11.glColor4f((float)f2, (float)f3, (float)f4, (float)1.0f);
                    this.field_76990_c.field_78721_f.func_78443_a(par1EntityLiving, itemstack, i);
                }
            } else {
                int i = itemstack.func_77973_b().func_82790_a(itemstack, 0);
                float f5 = (float)(i >> 16 & 0xFF) / 255.0f;
                float f2 = (float)(i >> 8 & 0xFF) / 255.0f;
                float f3 = (float)(i & 0xFF) / 255.0f;
                GL11.glColor4f((float)f5, (float)f2, (float)f3, (float)1.0f);
                this.field_76990_c.field_78721_f.func_78443_a(par1EntityLiving, itemstack, 0);
            }
            GL11.glPopMatrix();
        }
    }

    protected void func_82422_c() {
        GL11.glTranslatef((float)0.0f, (float)0.1875f, (float)0.0f);
    }
}

