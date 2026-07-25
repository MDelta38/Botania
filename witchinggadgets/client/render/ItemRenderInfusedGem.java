/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.api.aspects.Aspect
 */
package witchinggadgets.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import witchinggadgets.client.ClientProxy;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.items.ItemInfusedGem;
import witchinggadgets.common.util.Utilities;

public class ItemRenderInfusedGem
implements IItemRenderer {
    public boolean handleRenderType(ItemStack stack, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack stack, IItemRenderer.ItemRendererHelper helper) {
        return type.equals((Object)IItemRenderer.ItemRenderType.ENTITY);
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack stack, Object ... data) {
        if (!(stack.func_77973_b() instanceof ItemInfusedGem)) {
            return;
        }
        GL11.glPushMatrix();
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)22.5f, (float)0.0f, (float)0.0f, (float)1.0f);
        switch (type) {
            case ENTITY: {
                GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
                break;
            }
            case EQUIPPED: {
                GL11.glScalef((float)1.5f, (float)1.5f, (float)1.5f);
                GL11.glTranslated((double)0.3125, (double)0.0, (double)0.0);
                GL11.glRotatef((float)15.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)5.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                break;
            }
            case EQUIPPED_FIRST_PERSON: {
                GL11.glScalef((float)1.0f, (float)1.0f, (float)-1.0f);
                GL11.glTranslated((double)0.75, (double)0.0625, (double)0.0);
                break;
            }
            case FIRST_PERSON_MAP: {
                break;
            }
            case INVENTORY: {
                GL11.glScalef((float)40.0f, (float)40.0f, (float)40.0f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)-0.125f, (float)-0.32f, (float)0.2f);
                break;
            }
        }
        GL11.glEnable((int)2896);
        GL11.glEnable((int)3042);
        GL11.glEnable((int)3042);
        ItemInfusedGem.GemCut cut = ItemInfusedGem.getCut(stack);
        ClientUtilities.bindTexture("witchinggadgets:textures/models/white.png");
        Aspect a = ItemInfusedGem.getAspect(stack);
        if (a != null) {
            float r = (float)(a.getColor() >> 16 & 0xFF) / 255.0f;
            float g = (float)(a.getColor() >> 8 & 0xFF) / 255.0f;
            float b = (float)(a.getColor() & 0xFF) / 255.0f;
            GL11.glColor4f((float)r, (float)g, (float)b, (float)0.9375f);
        }
        if (cut != null) {
            ClientProxy.gemModel.renderPart(Utilities.getTitleCase(cut.name()) + "Cut_0" + cut.ordinal());
        }
        if (cut != null && type != IItemRenderer.ItemRenderType.INVENTORY) {
            GL11.glBlendFunc((int)770, (int)1);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            float scale = 0.875f;
            GL11.glScaled((double)scale, (double)scale, (double)scale);
            GL11.glTranslatef((float)0.0f, (float)0.015625f, (float)0.0f);
            ClientProxy.gemModel.renderPart(Utilities.getTitleCase(cut.name()) + "Cut_0" + cut.ordinal());
            GL11.glTranslatef((float)0.0f, (float)-0.015625f, (float)0.0f);
            GL11.glScaled((double)(1.0f / scale), (double)(1.0f / scale), (double)(1.0f / scale));
        }
        if (cut != null && stack.hasEffect(0)) {
            GL11.glPushMatrix();
            GL11.glDisable((int)2896);
            ClientUtilities.bindTexture("textures/misc/enchanted_item_glint.png");
            GL11.glEnable((int)3042);
            OpenGlHelper.func_148821_a((int)768, (int)1, (int)1, (int)0);
            float f7 = 0.76f;
            GL11.glColor4f((float)(0.5f * f7), (float)(0.25f * f7), (float)(0.8f * f7), (float)1.0f);
            GL11.glMatrixMode((int)5890);
            GL11.glPushMatrix();
            float f8 = 0.125f;
            GL11.glScalef((float)f8, (float)f8, (float)f8);
            float f9 = (float)(Minecraft.func_71386_F() % 3000L) / 3000.0f * 8.0f;
            GL11.glTranslatef((float)f9, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)-50.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            ClientProxy.gemModel.renderPart(Utilities.getTitleCase(cut.name()) + "Cut_0" + cut.ordinal());
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)f8, (float)f8, (float)f8);
            f9 = (float)(Minecraft.func_71386_F() % 4873L) / 4873.0f * 8.0f;
            GL11.glTranslatef((float)(-f9), (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)10.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            ClientProxy.gemModel.renderPart(Utilities.getTitleCase(cut.name()) + "Cut_0" + cut.ordinal());
            GL11.glPopMatrix();
            GL11.glMatrixMode((int)5888);
            GL11.glDisable((int)3042);
            GL11.glEnable((int)2896);
            GL11.glDepthFunc((int)515);
            GL11.glPopMatrix();
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2896);
        GL11.glPopMatrix();
    }
}

