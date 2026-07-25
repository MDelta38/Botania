/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.gear.ModelWand;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ItemWandRenderer
implements IItemRenderer {
    private ModelWand model = new ModelWand();

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return helper != IItemRenderer.ItemRendererHelper.BLOCK_3D;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        Minecraft mc = Minecraft.func_71410_x();
        if (item == null || !(item.func_77973_b() instanceof ItemWandCasting)) {
            return;
        }
        ItemWandCasting wand = (ItemWandCasting)item.func_77973_b();
        ItemStack focusStack = wand.getFocusItem(item);
        boolean staff = wand.isStaff(item);
        float pt = UtilsFX.getTimer((Minecraft)mc).field_74281_c;
        ItemRenderer ir = RenderManager.field_78727_a.field_78721_f;
        EntityLivingBase wielder = null;
        if (type == IItemRenderer.ItemRenderType.EQUIPPED || type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
            wielder = (EntityLivingBase)data[1];
        }
        GL11.glPushMatrix();
        if (staff) {
            GL11.glTranslated((double)0.0, (double)0.5, (double)0.0);
        }
        if (type != IItemRenderer.ItemRenderType.INVENTORY) {
            if (type == IItemRenderer.ItemRenderType.ENTITY) {
                if (staff) {
                    GL11.glTranslated((double)0.0, (double)1.5, (double)0.0);
                    GL11.glScaled((double)0.9, (double)0.9, (double)0.9);
                } else {
                    GL11.glTranslated((double)0.0, (double)1.0, (double)0.0);
                }
            } else {
                GL11.glTranslated((double)0.5, (double)1.5, (double)0.5);
                if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
                    GL11.glScaled((double)1.0, (double)1.1, (double)1.0);
                }
            }
        } else {
            if (staff) {
                GL11.glScaled((double)0.8, (double)0.8, (double)0.8);
            }
            GL11.glRotatef((float)66.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glTranslated((double)0.0, (double)0.6, (double)0.0);
            if (staff) {
                GL11.glTranslated((double)-0.7, (double)0.6, (double)0.0);
            }
        }
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        if (wielder != null && wielder instanceof EntityPlayer && ((EntityPlayer)wielder).func_71011_bu() != null) {
            float t = (float)((EntityPlayer)wielder).func_71057_bx() + pt;
            if (t > 3.0f) {
                t = 3.0f;
            }
            GL11.glTranslated((double)0.0, (double)1.0, (double)0.0);
            if (type != IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
                GL11.glRotatef((float)33.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            } else {
                GL11.glRotatef((float)10.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)10.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            }
            GL11.glRotatef((float)(60.0f * (t / 3.0f)), (float)-1.0f, (float)0.0f, (float)0.0f);
            if (wand.animation == ItemFocusBasic.WandFocusAnimation.WAVE || wand.getFocus(item) != null && wand.getFocus(item).getAnimation(focusStack) == ItemFocusBasic.WandFocusAnimation.WAVE) {
                float wave = MathHelper.func_76126_a((float)(((float)((EntityPlayer)wielder).func_71057_bx() + pt) / 10.0f)) * 10.0f;
                GL11.glRotatef((float)wave, (float)0.0f, (float)0.0f, (float)1.0f);
                wave = MathHelper.func_76126_a((float)(((float)((EntityPlayer)wielder).func_71057_bx() + pt) / 15.0f)) * 10.0f;
                GL11.glRotatef((float)wave, (float)1.0f, (float)0.0f, (float)0.0f);
            } else if (wand.getFocus(item) != null && wand.getFocus(item).getAnimation(focusStack) == ItemFocusBasic.WandFocusAnimation.CHARGE) {
                float wave = MathHelper.func_76126_a((float)(((float)((EntityPlayer)wielder).func_71057_bx() + pt) / 0.8f)) * 1.0f;
                GL11.glRotatef((float)wave, (float)0.0f, (float)0.0f, (float)1.0f);
                wave = MathHelper.func_76126_a((float)(((float)((EntityPlayer)wielder).func_71057_bx() + pt) / 0.7f)) * 1.0f;
                GL11.glRotatef((float)wave, (float)1.0f, (float)0.0f, (float)0.0f);
            }
            GL11.glTranslated((double)0.0, (double)-1.0, (double)0.0);
        }
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        this.model.render(item);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }
}

