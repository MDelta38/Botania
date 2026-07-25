/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.MathHelper
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.nodes.NodeModifier
 *  thaumcraft.client.lib.UtilsFX
 */
package witchinggadgets.client.render;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.NodeModifier;
import thaumcraft.client.lib.UtilsFX;
import witchinggadgets.client.ClientProxy;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.items.tools.ItemPrimordialGlove;

public class ItemRenderPrimordialGauntlet
implements IItemRenderer {
    public boolean handleRenderType(ItemStack stack, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack stack, IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack stack, Object ... data) {
        Minecraft mc = Minecraft.func_71410_x();
        Tessellator tessellator = Tessellator.field_78398_a;
        int ticksExisted = mc.field_71439_g.field_70173_aa;
        String[] fingers = new String[]{"Thumb", "Index", "Middle", "Ring", "Pinky"};
        int[][] fingerOverlayColour = new int[][]{{93, 25, 79}, {103, 39, 90}, {113, 52, 100}, {123, 67, 111}, {132, 80, 122}, {141, 94, 132}, {152, 108, 142}, {161, 122, 153}, {171, 135, 164}, {161, 122, 153}, {152, 108, 142}, {141, 94, 132}, {132, 80, 122}, {123, 67, 111}, {113, 52, 100}, {103, 39, 90}};
        try {
            GL11.glPushMatrix();
            AbstractClientPlayer equippingPlayer = null;
            switch (type) {
                case INVENTORY: {
                    GL11.glRotated((double)170.0, (double)1.0, (double)0.0, (double)0.0);
                    GL11.glRotated((double)10.0, (double)0.0, (double)1.0, (double)0.0);
                    GL11.glRotated((double)-30.0, (double)0.0, (double)0.0, (double)1.0);
                    GL11.glScaled((double)3.0, (double)3.5, (double)3.0);
                    GL11.glTranslated((double)0.5, (double)-0.6, (double)-0.3);
                    break;
                }
                case ENTITY: {
                    GL11.glScaled((double)1.5, (double)1.75, (double)1.5);
                    GL11.glTranslated((double)0.0, (double)-0.4, (double)0.0);
                    break;
                }
                case EQUIPPED: {
                    equippingPlayer = (AbstractClientPlayer)data[1];
                    double scale = 2.68;
                    ClientUtilities.bindTexture(equippingPlayer.func_110306_p().func_110624_b() + ":" + equippingPlayer.func_110306_p().func_110623_a());
                    GL11.glRotated((double)-45.0, (double)0.0, (double)1.0, (double)0.0);
                    GL11.glRotated((double)160.0, (double)0.0, (double)0.0, (double)1.0);
                    GL11.glScaled((double)scale, (double)scale, (double)scale);
                    GL11.glTranslated((double)-0.434, (double)-0.887, (double)-0.0014);
                    RenderHelper.func_74519_b();
                    break;
                }
                case EQUIPPED_FIRST_PERSON: {
                    equippingPlayer = (AbstractClientPlayer)data[1];
                    double scale = 2.0;
                    if (equippingPlayer.func_71039_bw()) {
                        GL11.glRotated((double)20.0, (double)0.0, (double)0.0, (double)1.0);
                        GL11.glTranslated((double)0.5, (double)-0.5, (double)0.0);
                    }
                    ClientUtilities.bindTexture(equippingPlayer.func_110306_p().func_110624_b() + ":" + equippingPlayer.func_110306_p().func_110623_a());
                    GL11.glRotated((double)280.0, (double)0.0, (double)0.0, (double)1.0);
                    GL11.glRotated((double)-60.0, (double)1.0, (double)0.0, (double)0.0);
                    GL11.glRotated((double)-75.0, (double)0.0, (double)1.0, (double)0.0);
                    GL11.glScaled((double)scale, (double)scale, (double)(-scale));
                    GL11.glTranslated((double)-0.0, (double)-0.85, (double)-0.51);
                    RenderHelper.func_74519_b();
                    ClientProxy.gauntletModel.renderOnly(new String[]{"Arm_00"});
                    GL11.glScaled((double)-1.0, (double)1.0, (double)1.0);
                    break;
                }
            }
            float overlayScale = 1.00001f;
            GL11.glPushMatrix();
            ClientUtilities.bindTexture("witchinggadgets:textures/models/primordialBracelet.png");
            GL11.glColor3d((double)0.8, (double)0.8, (double)0.8);
            ClientProxy.gauntletModel.renderPart("Guard_Arm_04");
            ClientProxy.gauntletModel.renderPart("Guard_Hand_05");
            GL11.glColor3d((double)1.0, (double)1.0, (double)1.0);
            for (int f = 0; f < 5; ++f) {
                float fingerAngle = 0.0f;
                float xx = f < 2 ? 0.09375f : (f == 2 ? 0.03125f : (f == 3 ? -0.03125f : -0.09375f));
                float zz = f == 0 ? 0.0833167f : -0.0913815f;
                float yy = 0.625f;
                GL11.glTranslated((double)xx, (double)yy, (double)zz);
                if (equippingPlayer != null && equippingPlayer.func_71039_bw()) {
                    fingerAngle = -80.0f;
                    GL11.glRotatef((float)(f == 0 ? 30.0f : (f == 1 ? -30.0f : (f == 3 ? 20.0f : (f == 4 ? 40.0f : 0.0f)))), (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)fingerAngle, (float)(f == 0 ? -1.0f : 1.0f), (float)0.0f, (float)0.0f);
                }
                ClientProxy.gauntletModel.renderPart("Finger_" + fingers[f] + "_f" + f);
                int colour = ticksExisted % 32 / 2;
                GL11.glColor3d((double)((double)fingerOverlayColour[colour][0] / 255.0), (double)((double)fingerOverlayColour[colour][1] / 255.0), (double)((double)fingerOverlayColour[colour][2] / 255.0));
                GL11.glScalef((float)overlayScale, (float)overlayScale, (float)overlayScale);
                ClientProxy.gauntletModel.renderPart("Finger_" + fingers[f] + "_Overlay_f" + f + "_1");
                GL11.glScalef((float)(1.0f / overlayScale), (float)(1.0f / overlayScale), (float)(1.0f / overlayScale));
                GL11.glColor3d((double)1.0, (double)1.0, (double)1.0);
                if (equippingPlayer != null && equippingPlayer.func_71039_bw()) {
                    GL11.glRotatef((float)(-fingerAngle), (float)(f == 0 ? -1.0f : 1.0f), (float)0.0f, (float)0.0f);
                    GL11.glRotatef((float)(f == 0 ? 30.0f : (f == 1 ? -30.0f : (f == 3 ? 20.0f : (f == 4 ? 40.0f : 0.0f)))), (float)0.0f, (float)-1.0f, (float)0.0f);
                }
                GL11.glTranslated((double)(-xx), (double)(-yy), (double)(-zz));
            }
            float runeColour = ticksExisted % 32 + 1;
            if (runeColour > 15.0f) {
                runeColour = 16.0f - (runeColour - 16.0f);
            }
            GL11.glScalef((float)overlayScale, (float)overlayScale, (float)overlayScale);
            GL11.glColor4f((float)(runeColour * 0.2f), (float)(runeColour * 0.2f), (float)(runeColour * 0.2f), (float)1.0f);
            ClientUtilities.bindTexture("witchinggadgets:textures/gui/bracelet.png");
            ClientProxy.gauntletModel.renderPart("Runes_06");
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glScalef((float)(1.0f / overlayScale), (float)(1.0f / overlayScale), (float)(1.0f / overlayScale));
            ClientUtilities.bindTexture("witchinggadgets:textures/models/white.png");
            ItemStack[] gems = ItemPrimordialGlove.getSetGems(stack);
            if (gems != null) {
                for (int g = 0; g < gems.length; ++g) {
                    if (gems[g] == null) continue;
                    Color col = new Color(gems[g].func_77973_b().func_82790_a(gems[g], 0));
                    if (gems[g].func_77960_j() == 0) {
                        GL11.glColor3d((double)((double)col.getRed() / 255.0), (double)((double)col.getGreen() / 255.0), (double)((double)col.getBlue() / 255.0));
                    } else {
                        GL11.glColor3d((double)((double)col.getRed() / 511.0), (double)((double)col.getGreen() / 511.0), (double)((double)col.getBlue() / 511.0));
                    }
                    ClientProxy.gauntletModel.renderPart("Gem0" + g + "_oval0" + g);
                    GL11.glColor3d((double)1.0, (double)1.0, (double)1.0);
                }
            }
            GL11.glPopMatrix();
            if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
                GL11.glTranslated((double)0.1, (double)0.0, (double)0.0);
            }
            GL11.glTranslated((double)0.0, (double)0.675, (double)0.0);
            GL11.glDisable((int)2896);
            UtilsFX.bindTexture((String)"textures/misc/nodes.png");
            if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
                int sel;
                int n = sel = stack.func_77942_o() ? stack.func_77978_p().func_74762_e("selected") : -1;
                if (sel >= 0 && sel < gems.length && gems[sel] != null) {
                    GL11.glPushMatrix();
                    GL11.glRotated((double)-90.0, (double)1.0, (double)0.0, (double)0.0);
                    GL11.glRotated((double)-90.0, (double)0.0, (double)1.0, (double)0.0);
                    GL11.glTranslated((double)-0.1875, (double)0.147, (double)0.0);
                    GL11.glEnable((int)3042);
                    GL11.glTranslated((double)(sel < 2 ? 0.09375 : (sel == 3 ? 0.0125 : 0.035)), (double)0.0, (double)(sel == 0 || sel == 2 ? 0.05625 : (sel == 1 || sel == 4 ? 0.14 : 0.1)));
                    GL11.glRotated((double)45.0, (double)1.0, (double)0.0, (double)0.0);
                    int perm = (int)(System.currentTimeMillis() / 64L % 32L);
                    double uMin = (double)perm * 0.03125;
                    double uMax = (double)(perm + 1) * 0.03125;
                    double vMin = gems[sel].func_77960_j() == 0 ? 0.0 : 0.09375;
                    double vMax = gems[sel].func_77960_j() == 0 ? 0.03125 : 0.125;
                    tessellator.func_78382_b();
                    tessellator.func_78378_d(gems[sel].func_77973_b().func_82790_a(gems[sel], 0));
                    tessellator.func_78374_a((double)-0.025f, (double)0.025f, 0.0, uMin, vMax);
                    tessellator.func_78374_a((double)0.025f, (double)0.025f, 0.0, uMax, vMax);
                    tessellator.func_78374_a((double)0.025f, (double)-0.025f, 0.0, uMax, vMin);
                    tessellator.func_78374_a((double)-0.025f, (double)-0.025f, 0.0, uMin, vMin);
                    tessellator.func_78381_a();
                    vMin = gems[sel].func_77960_j() == 0 ? 0.125 : 0.09375;
                    vMax = gems[sel].func_77960_j() == 0 ? 0.15625 : 0.125;
                    tessellator.func_78382_b();
                    tessellator.func_78378_d(gems[sel].func_77973_b().func_82790_a(gems[sel], 0));
                    tessellator.func_78374_a((double)-0.025f, (double)0.025f, 0.0, uMin, vMax);
                    tessellator.func_78374_a((double)0.025f, (double)0.025f, 0.0, uMax, vMax);
                    tessellator.func_78374_a((double)0.025f, (double)-0.025f, 0.0, uMax, vMin);
                    tessellator.func_78374_a((double)-0.025f, (double)-0.025f, 0.0, uMin, vMin);
                    tessellator.func_78381_a();
                    GL11.glPopMatrix();
                }
                GL11.glRotated((double)-90.0, (double)0.0, (double)1.0, (double)0.0);
                GL11.glRotated((double)-40.0, (double)1.0, (double)0.0, (double)0.0);
                GL11.glTranslated((double)0.0, (double)-0.01, (double)0.05);
            } else if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
                double pRot = ((AbstractClientPlayer)data[1]).field_70761_aq % 360.0f;
                GL11.glRotated((double)(90.0 + pRot - (double)RenderManager.field_78727_a.field_78735_i % 360.0), (double)0.0, (double)1.0, (double)0.0);
                GL11.glRotated((double)((double)RenderManager.field_78727_a.field_78732_j % 360.0), (double)1.0, (double)0.0, (double)0.0);
            } else if (type == IItemRenderer.ItemRenderType.ENTITY) {
                EntityItem entItem = (EntityItem)data[1];
                double entRot = (((float)entItem.field_70292_b + UtilsFX.getTimer((Minecraft)mc).field_74281_c) / 20.0f + entItem.field_70290_d) * 57.295776f;
                GL11.glRotated((double)(-entRot + (double)RenderManager.field_78727_a.field_78735_i % 360.0), (double)0.0, (double)1.0, (double)0.0);
                GL11.glRotated((double)((double)RenderManager.field_78727_a.field_78732_j % 360.0), (double)1.0, (double)0.0, (double)0.0);
            }
            AspectList nodeAspects = new AspectList();
            if (type != IItemRenderer.ItemRenderType.INVENTORY && stack.func_77942_o() && stack.func_77978_p().func_74764_b("storedNode")) {
                NBTTagCompound nodeTag = stack.func_77978_p().func_74775_l("storedNode");
                int nodeType = nodeTag.func_74762_e("type");
                NodeModifier nodeModifier = NodeModifier.values()[nodeTag.func_74762_e("modifier")];
                nodeAspects.readFromNBT(nodeTag);
                float alpha = 1.0f;
                float size = 1.0f;
                if (nodeModifier != null) {
                    switch (nodeModifier.ordinal()) {
                        case 1: {
                            alpha *= 1.5f;
                            break;
                        }
                        case 2: {
                            alpha *= 0.66f;
                            break;
                        }
                        case 3: {
                            alpha *= MathHelper.func_76126_a((float)((float)ticksExisted / 3.0f)) * 0.25f + 0.33f;
                        }
                    }
                }
                float nodeScale = 0.0f;
                int count = 0;
                float bscale = 0.25f;
                float average = 0.0f;
                for (Aspect aspect : nodeAspects.getAspects()) {
                    if (aspect == null) break;
                    if (aspect.getBlend() == 771) {
                        alpha = (float)((double)alpha * 1.5);
                    }
                    average += (float)nodeAspects.getAmount(aspect);
                    GL11.glPushMatrix();
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)770, (int)aspect.getBlend());
                    nodeScale = MathHelper.func_76126_a((float)((float)ticksExisted / (14.0f - (float)count))) * bscale + bscale * 2.0f;
                    nodeScale = 0.2f + nodeScale * ((float)nodeAspects.getAmount(aspect) / 50.0f);
                    nodeScale *= size;
                    float mod = 2.0f * ((float)((System.currentTimeMillis() + (long)(count * 512)) % 4096L) / 4096.0f);
                    if (mod > 1.0f) {
                        mod = 2.0f - mod;
                    }
                    float radius = 0.075f + 0.05f * mod * ((float)nodeAspects.getAmount(aspect) / average);
                    int perm = (int)(System.currentTimeMillis() / 64L % 32L) + count * 4;
                    double uMin = (double)perm * 0.03125;
                    double uMax = (double)(perm + 1) * 0.03125;
                    tessellator.func_78382_b();
                    tessellator.func_78384_a(aspect.getColor(), (int)(alpha * 255.0f));
                    tessellator.func_78374_a((double)(-radius), (double)radius, (double)(-count) * 0.001, uMin, 0.03125);
                    tessellator.func_78374_a((double)radius, (double)radius, (double)(-count) * 0.001, uMax, 0.03125);
                    tessellator.func_78374_a((double)radius, (double)(-radius), (double)(-count) * 0.001, uMax, 0.0);
                    tessellator.func_78374_a((double)(-radius), (double)(-radius), (double)(-count) * 0.001, uMin, 0.0);
                    tessellator.func_78381_a();
                    GL11.glDisable((int)3042);
                    GL11.glPopMatrix();
                    ++count;
                    if (aspect.getBlend() != 771) continue;
                    alpha = (float)((double)alpha / 1.5);
                }
                GL11.glPushMatrix();
                GL11.glEnable((int)3042);
                nodeScale = 0.1f + (average /= (float)nodeAspects.size()) / 150.0f;
                nodeScale *= size;
                if (nodeType != 0) {
                    GL11.glBlendFunc((int)770, (int)(nodeType == 3 || nodeType == 4 ? 771 : 1));
                }
                int perm = (int)(System.currentTimeMillis() / 64L % 32L);
                int overl = nodeType == 2 ? 6 : (nodeType == 3 ? 2 : (nodeType == 4 ? 5 : (nodeType == 5 ? 4 : (nodeType == 6 ? 3 : 1))));
                double uMin = (double)perm * 0.03125;
                double uMax = (double)(perm + 1) * 0.03125;
                tessellator.func_78382_b();
                tessellator.func_78374_a(-0.0825, 0.0825, (double)(-count) * 0.001, uMin, (double)(overl + 1) * 0.03125);
                tessellator.func_78374_a(0.0825, 0.0825, (double)(-count) * 0.001, uMax, (double)(overl + 1) * 0.03125);
                tessellator.func_78374_a(0.0825, -0.0825, (double)(-count) * 0.001, uMax, (double)(overl + 0) * 0.03125);
                tessellator.func_78374_a(-0.0825, -0.0825, (double)(-count) * 0.001, uMin, (double)(overl + 0) * 0.03125);
                tessellator.func_78381_a();
                GL11.glDisable((int)3042);
                GL11.glPopMatrix();
            }
            GL11.glEnable((int)2896);
            GL11.glPopMatrix();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

