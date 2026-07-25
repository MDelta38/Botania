/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.crash.CrashReport
 *  net.minecraft.crash.CrashReportCategory
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemCloth
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ReportedException
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.ForgeHooksClient
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.renderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderItem3d
extends Render {
    private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    private RenderBlocks renderBlocksRi = new RenderBlocks();
    private Random random = new Random();
    public boolean renderWithColor = true;
    public float zLevel;
    public static boolean renderInFrame;
    private static final String __OBFID = "CL_00001003";
    protected final boolean alwaysFancy;

    public RenderItem3d(boolean alwaysFancy) {
        this.field_76989_e = 0.15f;
        this.field_76987_f = 0.75f;
        this.alwaysFancy = alwaysFancy;
    }

    public void doRender(EntityItem par1EntityItem, double par2, double par4, double par6, float par8, float par9) {
        ItemStack itemstack = par1EntityItem.func_92059_d();
        if (itemstack.func_77973_b() != null) {
            this.func_110777_b((Entity)par1EntityItem);
            this.random.setSeed(187L);
            GL11.glPushMatrix();
            float f2 = this.shouldBob() ? MathHelper.func_76126_a((float)(((float)par1EntityItem.field_70292_b + par9) / 10.0f + par1EntityItem.field_70290_d)) * 0.1f + 0.1f : 0.0f;
            float f3 = (((float)par1EntityItem.field_70292_b + par9) / 20.0f + par1EntityItem.field_70290_d) * 57.295776f;
            int b0 = 1;
            if (par1EntityItem.func_92059_d().field_77994_a > 1) {
                b0 = 2;
            }
            if (par1EntityItem.func_92059_d().field_77994_a > 5) {
                b0 = 3;
            }
            if (par1EntityItem.func_92059_d().field_77994_a > 20) {
                b0 = 4;
            }
            if (par1EntityItem.func_92059_d().field_77994_a > 40) {
                b0 = 5;
            }
            b0 = this.getMiniBlockCount(itemstack, (byte)b0);
            GL11.glTranslatef((float)((float)par2), (float)((float)par4 + f2), (float)((float)par6));
            GL11.glEnable((int)32826);
            if (!ForgeHooksClient.renderEntityItem((EntityItem)par1EntityItem, (ItemStack)itemstack, (float)f2, (float)f3, (Random)this.random, (TextureManager)this.field_76990_c.field_78724_e, (RenderBlocks)this.field_147909_c, (int)b0)) {
                if (itemstack.func_94608_d() == 0 && itemstack.func_77973_b() instanceof ItemBlock && RenderBlocks.func_147739_a((int)Block.func_149634_a((Item)itemstack.func_77973_b()).func_149645_b())) {
                    Block block = Block.func_149634_a((Item)itemstack.func_77973_b());
                    GL11.glRotatef((float)f3, (float)0.0f, (float)1.0f, (float)0.0f);
                    if (renderInFrame) {
                        GL11.glScalef((float)1.25f, (float)1.25f, (float)1.25f);
                        GL11.glTranslatef((float)0.0f, (float)0.05f, (float)0.0f);
                        GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    }
                    float f9 = 0.25f;
                    int k = block.func_149645_b();
                    if (k == 1 || k == 19 || k == 12 || k == 2) {
                        f9 = 0.5f;
                    }
                    if (block.func_149701_w() > 0) {
                        GL11.glAlphaFunc((int)516, (float)0.1f);
                        GL11.glEnable((int)3042);
                        OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
                    }
                    GL11.glScalef((float)f9, (float)f9, (float)f9);
                    for (int l = 0; l < b0; ++l) {
                        GL11.glPushMatrix();
                        if (l > 0) {
                            float f6 = (this.random.nextFloat() * 2.0f - 1.0f) * 0.2f / f9;
                            float f7 = (this.random.nextFloat() * 2.0f - 1.0f) * 0.2f / f9;
                            float f8 = (this.random.nextFloat() * 2.0f - 1.0f) * 0.2f / f9;
                            GL11.glTranslatef((float)f6, (float)f7, (float)f8);
                        }
                        this.renderBlocksRi.func_147800_a(block, itemstack.func_77960_j(), 1.0f);
                        GL11.glPopMatrix();
                    }
                    if (block.func_149701_w() > 0) {
                        GL11.glDisable((int)3042);
                    }
                } else if (itemstack.func_77973_b().func_77623_v()) {
                    if (renderInFrame) {
                        GL11.glScalef((float)0.5128205f, (float)0.5128205f, (float)0.5128205f);
                        GL11.glTranslatef((float)0.0f, (float)-0.05f, (float)0.0f);
                    } else {
                        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
                    }
                    for (int j = 0; j < itemstack.func_77973_b().getRenderPasses(itemstack.func_77960_j()); ++j) {
                        this.random.setSeed(187L);
                        IIcon iicon1 = itemstack.func_77973_b().getIcon(itemstack, j);
                        if (this.renderWithColor) {
                            int k = itemstack.func_77973_b().func_82790_a(itemstack, j);
                            float f5 = (float)(k >> 16 & 0xFF) / 255.0f;
                            float f6 = (float)(k >> 8 & 0xFF) / 255.0f;
                            float f7 = (float)(k & 0xFF) / 255.0f;
                            GL11.glColor4f((float)f5, (float)f6, (float)f7, (float)1.0f);
                            this.renderDroppedItem(par1EntityItem, iicon1, b0, par9, f5, f6, f7, j);
                            continue;
                        }
                        this.renderDroppedItem(par1EntityItem, iicon1, b0, par9, 1.0f, 1.0f, 1.0f, j);
                    }
                } else {
                    if (itemstack != null && itemstack.func_77973_b() instanceof ItemCloth) {
                        GL11.glAlphaFunc((int)516, (float)0.1f);
                        GL11.glEnable((int)3042);
                        OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
                    }
                    if (renderInFrame) {
                        GL11.glScalef((float)0.5128205f, (float)0.5128205f, (float)0.5128205f);
                        GL11.glTranslatef((float)0.0f, (float)-0.05f, (float)0.0f);
                    } else {
                        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
                    }
                    IIcon iicon = itemstack.func_77954_c();
                    if (this.renderWithColor) {
                        int i = itemstack.func_77973_b().func_82790_a(itemstack, 0);
                        float f4 = (float)(i >> 16 & 0xFF) / 255.0f;
                        float f5 = (float)(i >> 8 & 0xFF) / 255.0f;
                        float f6 = (float)(i & 0xFF) / 255.0f;
                        this.renderDroppedItem(par1EntityItem, iicon, b0, par9, f4, f5, f6);
                    } else {
                        this.renderDroppedItem(par1EntityItem, iicon, b0, par9, 1.0f, 1.0f, 1.0f);
                    }
                    if (itemstack != null && itemstack.func_77973_b() instanceof ItemCloth) {
                        GL11.glDisable((int)3042);
                    }
                }
            }
            GL11.glDisable((int)32826);
            GL11.glPopMatrix();
        }
    }

    protected ResourceLocation getEntityTexture(EntityItem par1EntityItem) {
        return this.field_76990_c.field_78724_e.func_130087_a(par1EntityItem.func_92059_d().func_94608_d());
    }

    private void renderDroppedItem(EntityItem par1EntityItem, IIcon par2Icon, int par3, float par4, float par5, float par6, float par7) {
        this.renderDroppedItem(par1EntityItem, par2Icon, par3, par4, par5, par6, par7, 0);
    }

    private void renderDroppedItem(EntityItem par1EntityItem, IIcon par2Icon, int par3, float par4, float par5, float par6, float par7, int pass) {
        Tessellator tessellator = Tessellator.field_78398_a;
        if (par2Icon == null) {
            TextureManager texturemanager = Minecraft.func_71410_x().func_110434_K();
            ResourceLocation resourcelocation = texturemanager.func_130087_a(par1EntityItem.func_92059_d().func_94608_d());
            par2Icon = ((TextureMap)texturemanager.func_110581_b(resourcelocation)).func_110572_b("missingno");
        }
        float f14 = par2Icon.func_94209_e();
        float f15 = par2Icon.func_94212_f();
        float f4 = par2Icon.func_94206_g();
        float f5 = par2Icon.func_94210_h();
        float f6 = 1.0f;
        float f7 = 0.5f;
        float f8 = 0.25f;
        if (this.alwaysFancy || this.field_76990_c.field_78733_k.field_74347_j) {
            GL11.glPushMatrix();
            if (renderInFrame) {
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            } else {
                GL11.glRotatef((float)((((float)par1EntityItem.field_70292_b + par4) / 20.0f + par1EntityItem.field_70290_d) * 57.295776f), (float)0.0f, (float)1.0f, (float)0.0f);
            }
            float f9 = 0.0625f;
            float f10 = 0.021875f;
            ItemStack itemstack = par1EntityItem.func_92059_d();
            int j = itemstack.field_77994_a;
            int b0 = j < 2 ? 1 : (j < 16 ? 2 : (j < 32 ? 3 : 4));
            b0 = this.getMiniItemCount(itemstack, (byte)b0);
            GL11.glTranslatef((float)(-f7), (float)(-f8), (float)(-((f9 + f10) * (float)b0 / 2.0f)));
            for (int k = 0; k < b0; ++k) {
                if (k > 0 && this.shouldSpreadItems()) {
                    float x = (this.random.nextFloat() * 2.0f - 1.0f) * 0.3f / 0.5f;
                    float y = (this.random.nextFloat() * 2.0f - 1.0f) * 0.3f / 0.5f;
                    float z = (this.random.nextFloat() * 2.0f - 1.0f) * 0.3f / 0.5f;
                    GL11.glTranslatef((float)x, (float)y, (float)(f9 + f10));
                } else {
                    GL11.glTranslatef((float)0.0f, (float)0.0f, (float)(f9 + f10));
                }
                if (itemstack.func_94608_d() == 0) {
                    this.func_110776_a(TextureMap.field_110575_b);
                } else {
                    this.func_110776_a(TextureMap.field_110576_c);
                }
                GL11.glColor4f((float)par5, (float)par6, (float)par7, (float)1.0f);
                ItemRenderer.func_78439_a((Tessellator)tessellator, (float)f15, (float)f4, (float)f14, (float)f5, (int)par2Icon.func_94211_a(), (int)par2Icon.func_94216_b(), (float)f9);
                if (!itemstack.hasEffect(pass)) continue;
                GL11.glDepthFunc((int)514);
                GL11.glDisable((int)2896);
                this.field_76990_c.field_78724_e.func_110577_a(RES_ITEM_GLINT);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)768, (int)1);
                float f11 = 0.76f;
                GL11.glColor4f((float)(0.5f * f11), (float)(0.25f * f11), (float)(0.8f * f11), (float)1.0f);
                GL11.glMatrixMode((int)5890);
                GL11.glPushMatrix();
                float f12 = 0.125f;
                GL11.glScalef((float)f12, (float)f12, (float)f12);
                float f13 = (float)(Minecraft.func_71386_F() % 3000L) / 3000.0f * 8.0f;
                GL11.glTranslatef((float)f13, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)-50.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                ItemRenderer.func_78439_a((Tessellator)tessellator, (float)0.0f, (float)0.0f, (float)1.0f, (float)1.0f, (int)255, (int)255, (float)f9);
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glScalef((float)f12, (float)f12, (float)f12);
                f13 = (float)(Minecraft.func_71386_F() % 4873L) / 4873.0f * 8.0f;
                GL11.glTranslatef((float)(-f13), (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)10.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                ItemRenderer.func_78439_a((Tessellator)tessellator, (float)0.0f, (float)0.0f, (float)1.0f, (float)1.0f, (int)255, (int)255, (float)f9);
                GL11.glPopMatrix();
                GL11.glMatrixMode((int)5888);
                GL11.glDisable((int)3042);
                GL11.glEnable((int)2896);
                GL11.glDepthFunc((int)515);
            }
            GL11.glPopMatrix();
        } else {
            for (int l = 0; l < par3; ++l) {
                GL11.glPushMatrix();
                if (l > 0) {
                    float f10 = (this.random.nextFloat() * 2.0f - 1.0f) * 0.3f;
                    float f16 = (this.random.nextFloat() * 2.0f - 1.0f) * 0.3f;
                    float f17 = (this.random.nextFloat() * 2.0f - 1.0f) * 0.3f;
                    GL11.glTranslatef((float)f10, (float)f16, (float)f17);
                }
                if (!renderInFrame) {
                    GL11.glRotatef((float)(180.0f - this.field_76990_c.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
                }
                GL11.glColor4f((float)par5, (float)par6, (float)par7, (float)1.0f);
                tessellator.func_78382_b();
                tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
                tessellator.func_78374_a((double)(0.0f - f7), (double)(0.0f - f8), 0.0, (double)f14, (double)f5);
                tessellator.func_78374_a((double)(f6 - f7), (double)(0.0f - f8), 0.0, (double)f15, (double)f5);
                tessellator.func_78374_a((double)(f6 - f7), (double)(1.0f - f8), 0.0, (double)f15, (double)f4);
                tessellator.func_78374_a((double)(0.0f - f7), (double)(1.0f - f8), 0.0, (double)f14, (double)f4);
                tessellator.func_78381_a();
                GL11.glPopMatrix();
            }
        }
    }

    public void renderItemIntoGUI(FontRenderer par1FontRenderer, TextureManager par2TextureManager, ItemStack par3ItemStack, int par4, int par5) {
        this.renderItemIntoGUI(par1FontRenderer, par2TextureManager, par3ItemStack, par4, par5, false);
    }

    public void renderItemIntoGUI(FontRenderer par1FontRenderer, TextureManager par2TextureManager, ItemStack par3ItemStack, int par4, int par5, boolean renderEffect) {
        int k = par3ItemStack.func_77960_j();
        IIcon object = par3ItemStack.func_77954_c();
        GL11.glEnable((int)3042);
        OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
        if (par3ItemStack.func_94608_d() == 0 && RenderBlocks.func_147739_a((int)Block.func_149634_a((Item)par3ItemStack.func_77973_b()).func_149645_b())) {
            par2TextureManager.func_110577_a(TextureMap.field_110575_b);
            Block block = Block.func_149634_a((Item)par3ItemStack.func_77973_b());
            GL11.glPushMatrix();
            GL11.glTranslatef((float)(par4 - 2), (float)(par5 + 3), (float)(-3.0f + this.zLevel));
            GL11.glScalef((float)10.0f, (float)10.0f, (float)10.0f);
            GL11.glTranslatef((float)1.0f, (float)0.5f, (float)1.0f);
            GL11.glScalef((float)1.0f, (float)1.0f, (float)-1.0f);
            GL11.glRotatef((float)210.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            int l = par3ItemStack.func_77973_b().func_82790_a(par3ItemStack, 0);
            float f3 = (float)(l >> 16 & 0xFF) / 255.0f;
            float f4 = (float)(l >> 8 & 0xFF) / 255.0f;
            float f = (float)(l & 0xFF) / 255.0f;
            if (this.renderWithColor) {
                GL11.glColor4f((float)f3, (float)f4, (float)f, (float)1.0f);
            }
            GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            this.renderBlocksRi.field_147844_c = this.renderWithColor;
            this.renderBlocksRi.func_147800_a(block, k, 1.0f);
            this.renderBlocksRi.field_147844_c = true;
            GL11.glPopMatrix();
        } else if (par3ItemStack.func_77973_b().func_77623_v()) {
            GL11.glDisable((int)2896);
            GL11.glEnable((int)3008);
            par2TextureManager.func_110577_a(TextureMap.field_110576_c);
            GL11.glDisable((int)3008);
            GL11.glDisable((int)3553);
            GL11.glEnable((int)3042);
            OpenGlHelper.func_148821_a((int)0, (int)0, (int)0, (int)0);
            GL11.glColorMask((boolean)false, (boolean)false, (boolean)false, (boolean)true);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            Tessellator tessellator = Tessellator.field_78398_a;
            tessellator.func_78382_b();
            tessellator.func_78378_d(-1);
            tessellator.func_78377_a((double)(par4 - 2), (double)(par5 + 18), (double)this.zLevel);
            tessellator.func_78377_a((double)(par4 + 18), (double)(par5 + 18), (double)this.zLevel);
            tessellator.func_78377_a((double)(par4 + 18), (double)(par5 - 2), (double)this.zLevel);
            tessellator.func_78377_a((double)(par4 - 2), (double)(par5 - 2), (double)this.zLevel);
            tessellator.func_78381_a();
            GL11.glColorMask((boolean)true, (boolean)true, (boolean)true, (boolean)true);
            GL11.glEnable((int)3553);
            GL11.glEnable((int)3008);
            Item item = par3ItemStack.func_77973_b();
            for (int l = 0; l < item.getRenderPasses(k); ++l) {
                OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
                par2TextureManager.func_110577_a(item.func_94901_k() == 0 ? TextureMap.field_110575_b : TextureMap.field_110576_c);
                IIcon iicon = item.getIcon(par3ItemStack, l);
                int i1 = par3ItemStack.func_77973_b().func_82790_a(par3ItemStack, l);
                float f = (float)(i1 >> 16 & 0xFF) / 255.0f;
                float f1 = (float)(i1 >> 8 & 0xFF) / 255.0f;
                float f2 = (float)(i1 & 0xFF) / 255.0f;
                if (this.renderWithColor) {
                    GL11.glColor4f((float)f, (float)f1, (float)f2, (float)1.0f);
                }
                GL11.glDisable((int)2896);
                GL11.glEnable((int)3008);
                this.renderIcon(par4, par5, iicon, 16, 16);
                GL11.glDisable((int)3008);
                GL11.glEnable((int)2896);
                if (!renderEffect || !par3ItemStack.hasEffect(l)) continue;
                this.renderEffect(par2TextureManager, par4, par5);
            }
            GL11.glDisable((int)3008);
            GL11.glEnable((int)2896);
        } else {
            GL11.glDisable((int)2896);
            ResourceLocation resourcelocation = par2TextureManager.func_130087_a(par3ItemStack.func_94608_d());
            par2TextureManager.func_110577_a(resourcelocation);
            if (object == null) {
                object = ((TextureMap)Minecraft.func_71410_x().func_110434_K().func_110581_b(resourcelocation)).func_110572_b("missingno");
            }
            int l = par3ItemStack.func_77973_b().func_82790_a(par3ItemStack, 0);
            float f3 = (float)(l >> 16 & 0xFF) / 255.0f;
            float f4 = (float)(l >> 8 & 0xFF) / 255.0f;
            float f = (float)(l & 0xFF) / 255.0f;
            if (this.renderWithColor) {
                GL11.glColor4f((float)f3, (float)f4, (float)f, (float)1.0f);
            }
            GL11.glDisable((int)2896);
            GL11.glEnable((int)3008);
            this.renderIcon(par4, par5, object, 16, 16);
            GL11.glDisable((int)3008);
            GL11.glEnable((int)2896);
            if (renderEffect && par3ItemStack.hasEffect(0)) {
                this.renderEffect(par2TextureManager, par4, par5);
            }
            GL11.glEnable((int)2896);
        }
        GL11.glEnable((int)2884);
    }

    public void renderItemAndEffectIntoGUI(FontRenderer par1FontRenderer, TextureManager par2TextureManager, final ItemStack par3ItemStack, int par4, int par5) {
        if (par3ItemStack != null) {
            this.zLevel += 50.0f;
            try {
                if (!ForgeHooksClient.renderInventoryItem((RenderBlocks)this.field_147909_c, (TextureManager)par2TextureManager, (ItemStack)par3ItemStack, (boolean)this.renderWithColor, (float)this.zLevel, (float)par4, (float)par5)) {
                    this.renderItemIntoGUI(par1FontRenderer, par2TextureManager, par3ItemStack, par4, par5, true);
                }
            }
            catch (Throwable throwable) {
                CrashReport crashreport = CrashReport.func_85055_a((Throwable)throwable, (String)"Rendering item");
                CrashReportCategory crashreportcategory = crashreport.func_85058_a("Item being rendered");
                crashreportcategory.func_71500_a("Item Type", new Callable(){
                    private static final String __OBFID = "CL_00001004";

                    public String call() {
                        return String.valueOf(par3ItemStack.func_77973_b());
                    }
                });
                crashreportcategory.func_71500_a("Item Aux", new Callable(){
                    private static final String __OBFID = "CL_00001005";

                    public String call() {
                        return String.valueOf(par3ItemStack.func_77960_j());
                    }
                });
                crashreportcategory.func_71500_a("Item NBT", new Callable(){
                    private static final String __OBFID = "CL_00001006";

                    public String call() {
                        return String.valueOf(par3ItemStack.func_77978_p());
                    }
                });
                crashreportcategory.func_71500_a("Item Foil", new Callable(){
                    private static final String __OBFID = "CL_00001007";

                    public String call() {
                        return String.valueOf(par3ItemStack.func_77962_s());
                    }
                });
                throw new ReportedException(crashreport);
            }
            this.zLevel -= 50.0f;
        }
    }

    public void renderEffect(TextureManager manager, int x, int y) {
        GL11.glDepthFunc((int)514);
        GL11.glDisable((int)2896);
        GL11.glDepthMask((boolean)false);
        manager.func_110577_a(RES_ITEM_GLINT);
        GL11.glEnable((int)3008);
        GL11.glEnable((int)3042);
        GL11.glColor4f((float)0.5f, (float)0.25f, (float)0.8f, (float)1.0f);
        this.renderGlint(x * 431278612 + y * 32178161, x - 2, y - 2, 20, 20);
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)3008);
        GL11.glEnable((int)2896);
        GL11.glDepthFunc((int)515);
    }

    private void renderGlint(int par1, int par2, int par3, int par4, int par5) {
        for (int j1 = 0; j1 < 2; ++j1) {
            OpenGlHelper.func_148821_a((int)772, (int)1, (int)0, (int)0);
            float f = 0.00390625f;
            float f1 = 0.00390625f;
            float f2 = (float)(Minecraft.func_71386_F() % (long)(3000 + j1 * 1873)) / (3000.0f + (float)(j1 * 1873)) * 256.0f;
            float f3 = 0.0f;
            Tessellator tessellator = Tessellator.field_78398_a;
            float f4 = 4.0f;
            if (j1 == 1) {
                f4 = -1.0f;
            }
            tessellator.func_78382_b();
            tessellator.func_78374_a((double)(par2 + 0), (double)(par3 + par5), (double)this.zLevel, (double)((f2 + (float)par5 * f4) * f), (double)((f3 + (float)par5) * f1));
            tessellator.func_78374_a((double)(par2 + par4), (double)(par3 + par5), (double)this.zLevel, (double)((f2 + (float)par4 + (float)par5 * f4) * f), (double)((f3 + (float)par5) * f1));
            tessellator.func_78374_a((double)(par2 + par4), (double)(par3 + 0), (double)this.zLevel, (double)((f2 + (float)par4) * f), (double)((f3 + 0.0f) * f1));
            tessellator.func_78374_a((double)(par2 + 0), (double)(par3 + 0), (double)this.zLevel, (double)((f2 + 0.0f) * f), (double)((f3 + 0.0f) * f1));
            tessellator.func_78381_a();
        }
    }

    public void renderItemOverlayIntoGUI(FontRenderer par1FontRenderer, TextureManager par2TextureManager, ItemStack par3ItemStack, int par4, int par5) {
        this.renderItemOverlayIntoGUI(par1FontRenderer, par2TextureManager, par3ItemStack, par4, par5, null);
    }

    public void renderItemOverlayIntoGUI(FontRenderer par1FontRenderer, TextureManager par2TextureManager, ItemStack par3ItemStack, int par4, int par5, String par6Str) {
        if (par3ItemStack != null) {
            if (par3ItemStack.field_77994_a > 1 || par6Str != null) {
                String s1 = par6Str == null ? String.valueOf(par3ItemStack.field_77994_a) : par6Str;
                GL11.glDisable((int)2896);
                GL11.glDisable((int)2929);
                GL11.glDisable((int)3042);
                par1FontRenderer.func_78261_a(s1, par4 + 19 - 2 - par1FontRenderer.func_78256_a(s1), par5 + 6 + 3, 0xFFFFFF);
                GL11.glEnable((int)2896);
                GL11.glEnable((int)2929);
            }
            if (par3ItemStack.func_77973_b().showDurabilityBar(par3ItemStack)) {
                double health = par3ItemStack.func_77973_b().getDurabilityForDisplay(par3ItemStack);
                int j1 = (int)Math.round(13.0 - health * 13.0);
                int k = (int)Math.round(255.0 - health * 255.0);
                GL11.glDisable((int)2896);
                GL11.glDisable((int)2929);
                GL11.glDisable((int)3553);
                GL11.glDisable((int)3008);
                GL11.glDisable((int)3042);
                Tessellator tessellator = Tessellator.field_78398_a;
                int l = 255 - k << 16 | k << 8;
                int i1 = (255 - k) / 4 << 16 | 0x3F00;
                this.renderQuad(tessellator, par4 + 2, par5 + 13, 13, 2, 0);
                this.renderQuad(tessellator, par4 + 2, par5 + 13, 12, 1, i1);
                this.renderQuad(tessellator, par4 + 2, par5 + 13, j1, 1, l);
                GL11.glEnable((int)3553);
                GL11.glEnable((int)2896);
                GL11.glEnable((int)2929);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            }
        }
    }

    private void renderQuad(Tessellator par1Tessellator, int par2, int par3, int par4, int par5, int par6) {
        par1Tessellator.func_78382_b();
        par1Tessellator.func_78378_d(par6);
        par1Tessellator.func_78377_a((double)(par2 + 0), (double)(par3 + 0), 0.0);
        par1Tessellator.func_78377_a((double)(par2 + 0), (double)(par3 + par5), 0.0);
        par1Tessellator.func_78377_a((double)(par2 + par4), (double)(par3 + par5), 0.0);
        par1Tessellator.func_78377_a((double)(par2 + par4), (double)(par3 + 0), 0.0);
        par1Tessellator.func_78381_a();
    }

    public void renderIcon(int par1, int par2, IIcon par3Icon, int par4, int par5) {
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78374_a((double)(par1 + 0), (double)(par2 + par5), (double)this.zLevel, (double)par3Icon.func_94209_e(), (double)par3Icon.func_94210_h());
        tessellator.func_78374_a((double)(par1 + par4), (double)(par2 + par5), (double)this.zLevel, (double)par3Icon.func_94212_f(), (double)par3Icon.func_94210_h());
        tessellator.func_78374_a((double)(par1 + par4), (double)(par2 + 0), (double)this.zLevel, (double)par3Icon.func_94212_f(), (double)par3Icon.func_94206_g());
        tessellator.func_78374_a((double)(par1 + 0), (double)(par2 + 0), (double)this.zLevel, (double)par3Icon.func_94209_e(), (double)par3Icon.func_94206_g());
        tessellator.func_78381_a();
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return this.getEntityTexture((EntityItem)par1Entity);
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRender((EntityItem)par1Entity, par2, par4, par6, par8, par9);
    }

    public boolean shouldSpreadItems() {
        return true;
    }

    public boolean shouldBob() {
        return true;
    }

    public byte getMiniBlockCount(ItemStack stack, byte original) {
        return original;
    }

    public byte getMiniItemCount(ItemStack stack, byte original) {
        return original;
    }
}

