/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.inventory.GuiContainerCreative
 *  net.minecraft.client.gui.inventory.GuiInventory
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.client.model.ModelCaneSword;
import com.emoniph.witchery.util.Config;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderCaneSword
implements IItemRenderer {
    private final ModelCaneSword model = new ModelCaneSword();
    private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/canesword.png");
    double rx = 100.0;
    double ry = -51.0;
    double rz = -81.0;
    double tx = 0.125;
    double ty = 0.12;
    double tz = -0.85;
    double scale = 1.0;
    private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        switch (type) {
            case EQUIPPED: 
            case EQUIPPED_FIRST_PERSON: {
                return true;
            }
        }
        return false;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return false;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        switch (type) {
            case EQUIPPED: 
            case EQUIPPED_FIRST_PERSON: {
                GL11.glPushMatrix();
                Minecraft.func_71410_x().func_110434_K().func_110577_a(TEXTURE_URL);
                GL11.glRotatef((float)((float)this.rx), (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)((float)this.ry + 70.0f), (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)((float)this.rz - 5.0f), (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)((float)this.tx + 0.35f), (float)((float)this.ty + 0.0f), (float)((float)this.tz + 0.85f));
                float SCALE = 1.0f;
                GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
                if (data.length > 1 && data[1] != null) {
                    boolean deployed;
                    boolean bl = deployed = data[1] instanceof EntityLivingBase ? Witchery.Items.CANE_SWORD.isDrawn((EntityLivingBase)data[1]) : false;
                    if (data[1] instanceof EntityPlayer) {
                        EntityPlayer player = (EntityPlayer)data[1];
                        if ((EntityPlayer)data[1] != Minecraft.func_71410_x().field_71451_h || Minecraft.func_71410_x().field_71474_y.field_74320_O != 0 || (Minecraft.func_71410_x().field_71462_r instanceof GuiInventory || Minecraft.func_71410_x().field_71462_r instanceof GuiContainerCreative) && RenderManager.field_78727_a.field_78735_i == 180.0f) {
                            if (deployed) {
                                GL11.glRotatef((float)80.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                                GL11.glRotatef((float)10.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                                GL11.glRotatef((float)0.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                                GL11.glTranslatef((float)0.0f, (float)-0.3f, (float)-0.5f);
                            }
                            this.renderModel((Entity)player, false, deployed, item);
                        } else {
                            if (deployed) {
                                GL11.glTranslatef((float)0.4f, (float)0.3f, (float)-0.2f);
                                GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                                GL11.glRotatef((float)1.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                                GL11.glRotatef((float)20.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                            } else {
                                GL11.glRotatef((float)70.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                                GL11.glRotatef((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                                GL11.glRotatef((float)0.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                                GL11.glTranslatef((float)0.0f, (float)-0.5f, (float)-0.9f);
                            }
                            this.renderModel((Entity)player, true, deployed, item);
                        }
                    } else {
                        if (deployed) {
                            GL11.glRotatef((float)80.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                            GL11.glRotatef((float)10.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                            GL11.glRotatef((float)0.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                            GL11.glTranslatef((float)0.0f, (float)-0.3f, (float)-0.5f);
                        }
                        this.renderModel((Entity)data[1], false, deployed, item);
                    }
                }
                GL11.glPopMatrix();
                break;
            }
        }
    }

    private void renderModel(Entity player, boolean firstPerson, boolean deployed, ItemStack item) {
        this.model.render(player, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, firstPerson, deployed);
        Minecraft mc = Minecraft.func_71410_x();
        if (mc.field_71474_y.field_74347_j && Config.instance().render3dGlintEffect && item != null && item.func_77948_v()) {
            float f9 = player.field_70173_aa;
            mc.field_71446_o.func_110577_a(RES_ITEM_GLINT);
            GL11.glEnable((int)3042);
            float f10 = 0.5f;
            GL11.glColor4f((float)f10, (float)f10, (float)f10, (float)1.0f);
            GL11.glDepthFunc((int)514);
            GL11.glDepthMask((boolean)false);
            for (int k = 0; k < 2; ++k) {
                GL11.glDisable((int)2896);
                float f11 = 0.76f;
                GL11.glColor4f((float)(0.5f * f11), (float)(0.25f * f11), (float)(0.8f * f11), (float)1.0f);
                GL11.glBlendFunc((int)768, (int)1);
                GL11.glMatrixMode((int)5890);
                GL11.glLoadIdentity();
                float f12 = f9 * (0.001f + (float)k * 0.003f) * 20.0f;
                float f13 = 0.33333334f;
                GL11.glScalef((float)f13, (float)f13, (float)f13);
                GL11.glRotatef((float)(30.0f - (float)k * 60.0f), (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)0.0f, (float)f12, (float)0.0f);
                GL11.glMatrixMode((int)5888);
                this.model.render(player, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, firstPerson, deployed);
            }
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glMatrixMode((int)5890);
            GL11.glDepthMask((boolean)true);
            GL11.glLoadIdentity();
            GL11.glMatrixMode((int)5888);
            GL11.glEnable((int)2896);
            GL11.glDisable((int)3042);
            GL11.glDepthFunc((int)515);
        }
    }
}

