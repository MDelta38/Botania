/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.model.AdvancedModelLoader
 *  net.minecraftforge.client.model.IModelCustom
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.UtilsFX
 */
package com.kentington.thaumichorizons.client.renderer.tile;

import com.kentington.thaumichorizons.common.tiles.TileNodeMonitor;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;

public class TileNodeMonitorRender
extends TileEntitySpecialRenderer {
    private IModelCustom model = AdvancedModelLoader.loadModel((ResourceLocation)SCANNER);
    private IModelCustom modelScreen = AdvancedModelLoader.loadModel((ResourceLocation)SCANNERSCREEN);
    private static final ResourceLocation SCANNER = new ResourceLocation("thaumcraft", "textures/models/scanner.obj");
    private static final ResourceLocation SCANNERSCREEN = new ResourceLocation("thaumichorizons", "textures/models/hexagon.obj");
    static String tx1 = "textures/models/nodemon.png";
    static String tx2 = "textures/models/nodemonscreen.png";
    static String tx3 = "textures/models/nodemonscreenactive.png";

    public void func_147500_a(TileEntity te, double x, double y, double z, float f) {
        TileNodeMonitor tco = (TileNodeMonitor)te;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)(y + 0.5)), (float)((float)z + 0.5f));
        GL11.glScalef((float)0.4f, (float)0.4f, (float)0.4f);
        switch (tco.direction) {
            case 1: {
                GL11.glTranslatef((float)0.0f, (float)-0.8f, (float)0.0f);
                GL11.glRotatef((float)tco.rotation, (float)0.0f, (float)1.0f, (float)0.0f);
                break;
            }
            case 0: {
                GL11.glTranslatef((float)0.0f, (float)0.8f, (float)0.0f);
                GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)tco.rotation, (float)0.0f, (float)1.0f, (float)0.0f);
                break;
            }
            case 2: {
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.8f);
                GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)tco.rotation, (float)0.0f, (float)1.0f, (float)0.0f);
                break;
            }
            case 3: {
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.8f);
                GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)tco.rotation, (float)0.0f, (float)1.0f, (float)0.0f);
                break;
            }
            case 4: {
                GL11.glTranslatef((float)0.8f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)tco.rotation, (float)0.0f, (float)1.0f, (float)0.0f);
                break;
            }
            case 5: {
                GL11.glTranslatef((float)-0.8f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)-90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)tco.rotation, (float)0.0f, (float)1.0f, (float)0.0f);
            }
        }
        UtilsFX.bindTexture((String)"thaumichorizons", (String)tx1);
        this.model.renderAll();
        GL11.glEnable((int)2977);
        GL11.glEnable((int)3042);
        GL11.glEnable((int)32826);
        GL11.glBlendFunc((int)770, (int)771);
        if (tco.switchy) {
            UtilsFX.bindTexture((String)"thaumichorizons", (String)tx3);
        } else {
            UtilsFX.bindTexture((String)"thaumichorizons", (String)tx2);
        }
        this.modelScreen.renderAll();
        GL11.glPopMatrix();
    }
}

