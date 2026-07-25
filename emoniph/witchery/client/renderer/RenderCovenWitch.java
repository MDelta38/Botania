/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelWitch
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.entity.EntityCovenWitch;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelWitch;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderCovenWitch
extends RenderLiving {
    private static final ResourceLocation BASIC_TEXTURE = new ResourceLocation("witchery", "textures/entities/covenwitch1.png");
    private static final ResourceLocation A_TEXTURE = new ResourceLocation("witchery", "textures/entities/covenwitch2.png");
    private static final ResourceLocation B_TEXTURE = new ResourceLocation("witchery", "textures/entities/covenwitch3.png");
    private static final ResourceLocation C_TEXTURE = new ResourceLocation("witchery", "textures/entities/covenwitch4.png");
    private static final ResourceLocation D_TEXTURE = new ResourceLocation("witchery", "textures/entities/covenwitch5.png");
    private final ModelWitch model;

    public RenderCovenWitch() {
        super((ModelBase)new ModelWitch(0.0f), 0.5f);
        this.model = (ModelWitch)this.field_77045_g;
    }

    public void func_82412_a(EntityCovenWitch par1EntityCovenWitch, double par2, double par4, double par6, float par8, float par9) {
        ItemStack itemstack = par1EntityCovenWitch.func_70694_bm();
        this.model.field_82900_g = itemstack != null;
        super.func_76986_a((EntityLiving)par1EntityCovenWitch, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation getCovenWitchTextures(EntityCovenWitch par1EntityCovenWitch) {
        switch (par1EntityCovenWitch.getTameSkin()) {
            default: {
                return BASIC_TEXTURE;
            }
            case 1: {
                return A_TEXTURE;
            }
            case 2: {
                return B_TEXTURE;
            }
            case 3: {
                return C_TEXTURE;
            }
            case 4: 
        }
        return D_TEXTURE;
    }

    protected void func_82411_a(EntityCovenWitch par1EntityCovenWitch, float par2) {
        float f1 = 1.0f;
        GL11.glColor3f((float)f1, (float)f1, (float)f1);
        super.func_77029_c((EntityLivingBase)par1EntityCovenWitch, par2);
        ItemStack itemstack = par1EntityCovenWitch.func_70694_bm();
        if (itemstack != null) {
            float f2;
            GL11.glPushMatrix();
            if (this.field_77045_g.field_78091_s) {
                f2 = 0.5f;
                GL11.glTranslatef((float)0.0f, (float)0.625f, (float)0.0f);
                GL11.glRotatef((float)-20.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
                GL11.glScalef((float)f2, (float)f2, (float)f2);
            }
            this.model.field_82898_f.func_78794_c(0.0625f);
            GL11.glTranslatef((float)-0.0625f, (float)0.53125f, (float)0.21875f);
            if (itemstack.func_77973_b() instanceof ItemBlock && RenderBlocks.func_147739_a((int)Block.func_149634_a((Item)itemstack.func_77973_b()).func_149645_b())) {
                f2 = 0.5f;
                GL11.glTranslatef((float)0.0f, (float)0.1875f, (float)-0.3125f);
                GL11.glRotatef((float)20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glScalef((float)(f2 *= 0.75f), (float)(-f2), (float)f2);
            } else if (itemstack.func_77973_b() == Items.field_151031_f) {
                f2 = 0.625f;
                GL11.glTranslatef((float)0.0f, (float)0.125f, (float)0.3125f);
                GL11.glRotatef((float)-20.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glScalef((float)f2, (float)(-f2), (float)f2);
                GL11.glRotatef((float)-100.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            } else if (itemstack.func_77973_b().func_77662_d()) {
                f2 = 0.625f;
                if (itemstack.func_77973_b().func_77629_n_()) {
                    GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glTranslatef((float)0.0f, (float)-0.125f, (float)0.0f);
                }
                this.func_82410_b();
                GL11.glScalef((float)f2, (float)(-f2), (float)f2);
                GL11.glRotatef((float)-100.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            } else {
                f2 = 0.375f;
                GL11.glTranslatef((float)0.25f, (float)0.1875f, (float)-0.1875f);
                GL11.glScalef((float)f2, (float)f2, (float)f2);
                GL11.glRotatef((float)60.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            }
            GL11.glRotatef((float)-15.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)40.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            this.field_76990_c.field_78721_f.func_78443_a((EntityLivingBase)par1EntityCovenWitch, itemstack, 0);
            if (itemstack.func_77973_b().func_77623_v()) {
                this.field_76990_c.field_78721_f.func_78443_a((EntityLivingBase)par1EntityCovenWitch, itemstack, 1);
            }
            GL11.glPopMatrix();
        }
    }

    protected void func_82410_b() {
        GL11.glTranslatef((float)0.0f, (float)0.1875f, (float)0.0f);
    }

    protected void func_82409_b(EntityCovenWitch par1EntityCovenWitch, float par2) {
        float f1 = 0.9375f;
        GL11.glScalef((float)f1, (float)f1, (float)f1);
    }

    public void func_76986_a(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
        this.func_82412_a((EntityCovenWitch)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    protected void func_77041_b(EntityLivingBase par1EntityLivingBase, float par2) {
        this.func_82409_b((EntityCovenWitch)par1EntityLivingBase, par2);
    }

    protected void func_77029_c(EntityLivingBase par1EntityLivingBase, float par2) {
        this.func_82411_a((EntityCovenWitch)par1EntityLivingBase, par2);
    }

    public void func_76986_a(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
        this.func_82412_a((EntityCovenWitch)par1EntityLivingBase, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return this.getCovenWitchTextures((EntityCovenWitch)par1Entity);
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.func_82412_a((EntityCovenWitch)par1Entity, par2, par4, par6, par8, par9);
    }
}

