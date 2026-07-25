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
import com.emoniph.witchery.client.model.ModelHandBow;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemHandBow;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderHandBow
implements IItemRenderer {
    protected ModelHandBow model = new ModelHandBow();
    private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/handbow.png");
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

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack stack, Object ... data) {
        switch (type) {
            case EQUIPPED: 
            case EQUIPPED_FIRST_PERSON: {
                GL11.glPushMatrix();
                ItemGeneral.BoltType boltType = ItemHandBow.getLoadedBoltType(stack);
                Minecraft.func_71410_x().func_110434_K().func_110577_a(TEXTURE_URL);
                GL11.glRotatef((float)((float)this.rx), (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)((float)this.ry), (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)((float)this.rz), (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)((float)this.tx - 0.03f), (float)((float)this.ty - 0.13f), (float)((float)this.tz + 0.13f));
                float SCALE = (float)this.scale;
                GL11.glScalef((float)SCALE, (float)SCALE, (float)SCALE);
                if (data.length > 1 && data[1] != null) {
                    if (data[1] instanceof EntityPlayer) {
                        int useCount;
                        EntityPlayer player = (EntityPlayer)data[1];
                        int n = useCount = player.func_71052_bv() > 0 ? Witchery.Items.CROSSBOW_PISTOL.func_77626_a(stack) - player.func_71052_bv() : 0;
                        if ((EntityPlayer)data[1] != Minecraft.func_71410_x().field_71451_h || Minecraft.func_71410_x().field_71474_y.field_74320_O != 0 || (Minecraft.func_71410_x().field_71462_r instanceof GuiInventory || Minecraft.func_71410_x().field_71462_r instanceof GuiContainerCreative) && RenderManager.field_78727_a.field_78735_i == 180.0f) {
                            this.renderModel((Entity)player, boltType, useCount);
                        } else {
                            if (player.func_71052_bv() > 0) {
                                GL11.glRotatef((float)-25.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                                GL11.glTranslatef((float)0.0f, (float)0.1f, (float)0.0f);
                            }
                            GL11.glTranslatef((float)0.2f, (float)0.1f, (float)0.0f);
                            this.renderModel((Entity)player, boltType, useCount);
                        }
                    } else {
                        this.renderModel((Entity)data[1], boltType, -1);
                    }
                }
                GL11.glPopMatrix();
                break;
            }
        }
    }

    private void renderModel(Entity player, ItemGeneral.BoltType boltType, int useCount) {
        if (boltType != null) {
            useCount = 100;
        } else if (!player.func_70093_af() || useCount == -1) {
            useCount = 0;
        }
        this.model.render(player, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, boltType, useCount);
    }
}

