/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemBowBoneRenderer
implements IItemRenderer {
    private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    private RenderManager renderManager = RenderManager.field_78727_a;
    private Minecraft mc = Minecraft.func_71410_x();
    private TextureManager texturemanager = this.mc.func_110434_K();

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return type == IItemRenderer.ItemRenderType.EQUIPPED;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return false;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        EntityLivingBase entity = (EntityLivingBase)data[1];
        ItemRenderer irInstance = this.mc.field_71460_t.field_78516_c;
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        float f2 = 2.6666667f;
        GL11.glRotatef((float)-20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)-60.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glScalef((float)f2, (float)f2, (float)f2);
        GL11.glTranslatef((float)-0.25f, (float)-0.1875f, (float)0.1875f);
        float f3 = 0.625f;
        GL11.glTranslatef((float)0.0f, (float)0.125f, (float)0.3125f);
        GL11.glRotatef((float)-20.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glScalef((float)f3, (float)(-f3), (float)f3);
        GL11.glRotatef((float)-100.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        this.renderItem(entity, item, 0);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
    }

    private void renderItem(EntityLivingBase par1EntityLiving, ItemStack par2ItemStack, int par3) {
        IIcon icon = par1EntityLiving.func_70620_b(par2ItemStack, par3);
        if (icon == null) {
            return;
        }
        this.texturemanager.func_110577_a(this.texturemanager.func_130087_a(par2ItemStack.func_94608_d()));
        Tessellator tessellator = Tessellator.field_78398_a;
        float f = icon.func_94209_e();
        float f1 = icon.func_94212_f();
        float f2 = icon.func_94206_g();
        float f3 = icon.func_94210_h();
        float f4 = 0.0f;
        float f5 = 0.3f;
        GL11.glEnable((int)32826);
        GL11.glTranslatef((float)(-f4), (float)(-f5), (float)0.0f);
        float f6 = 1.5f;
        GL11.glScalef((float)f6, (float)f6, (float)f6);
        GL11.glRotatef((float)50.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)335.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glTranslatef((float)-0.9375f, (float)-0.0625f, (float)0.0f);
        ItemRenderer.func_78439_a((Tessellator)tessellator, (float)f1, (float)f2, (float)f, (float)f3, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.0625f);
        if (par2ItemStack.hasEffect(par3)) {
            GL11.glDepthFunc((int)514);
            GL11.glDisable((int)2896);
            this.texturemanager.func_110577_a(new ResourceLocation("textures/misc/enchanted_item_glint.png"));
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)768, (int)1);
            float f7 = 0.76f;
            GL11.glColor4f((float)(0.5f * f7), (float)(0.25f * f7), (float)(0.8f * f7), (float)1.0f);
            GL11.glMatrixMode((int)5890);
            GL11.glPushMatrix();
            float f8 = 0.125f;
            GL11.glScalef((float)f8, (float)f8, (float)f8);
            float f9 = (float)(Minecraft.func_71386_F() % 3000L) / 3000.0f * 8.0f;
            GL11.glTranslatef((float)f9, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)-50.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            ItemRenderer.func_78439_a((Tessellator)tessellator, (float)0.0f, (float)0.0f, (float)1.0f, (float)1.0f, (int)256, (int)256, (float)0.0625f);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)f8, (float)f8, (float)f8);
            f9 = (float)(Minecraft.func_71386_F() % 4873L) / 4873.0f * 8.0f;
            GL11.glTranslatef((float)(-f9), (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)10.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            ItemRenderer.func_78439_a((Tessellator)tessellator, (float)0.0f, (float)0.0f, (float)1.0f, (float)1.0f, (int)256, (int)256, (float)0.0625f);
            GL11.glPopMatrix();
            GL11.glMatrixMode((int)5888);
            GL11.glDisable((int)3042);
            GL11.glEnable((int)2896);
            GL11.glDepthFunc((int)515);
        }
        GL11.glDisable((int)32826);
    }
}

