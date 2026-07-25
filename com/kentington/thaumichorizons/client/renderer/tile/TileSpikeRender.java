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

import com.kentington.thaumichorizons.common.tiles.TileSpike;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;

public class TileSpikeRender
extends TileEntitySpecialRenderer {
    private IModelCustom model = AdvancedModelLoader.loadModel((ResourceLocation)SPIKE);
    private static final ResourceLocation SPIKE = new ResourceLocation("thaumichorizons", "textures/models/spike.obj");
    static String tx1 = "textures/models/metalspike.png";
    static String tx2 = "textures/models/woodenspike.png";
    static String tx3 = "textures/models/toothspike.png";

    public void func_147500_a(TileEntity te, double x, double y, double z, float f) {
        TileSpike tco = (TileSpike)te;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)(y + 0.5)), (float)((float)z + 0.5f));
        GL11.glScalef((float)0.35f, (float)0.35f, (float)0.35f);
        switch (tco.direction) {
            case 1: {
                GL11.glTranslatef((float)0.0f, (float)-1.45f, (float)0.0f);
                break;
            }
            case 0: {
                GL11.glTranslatef((float)0.0f, (float)1.45f, (float)0.0f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                break;
            }
            case 2: {
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)1.45f);
                GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                break;
            }
            case 3: {
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-1.45f);
                GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                break;
            }
            case 4: {
                GL11.glTranslatef((float)1.45f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                break;
            }
            case 5: {
                GL11.glTranslatef((float)-1.45f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)-90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            }
        }
        if (tco.spikeType == 0) {
            UtilsFX.bindTexture((String)"thaumichorizons", (String)tx1);
        } else if (tco.spikeType == 1) {
            UtilsFX.bindTexture((String)"thaumichorizons", (String)tx2);
        } else {
            UtilsFX.bindTexture((String)"thaumichorizons", (String)tx3);
        }
        this.model.renderAll();
        GL11.glPopMatrix();
    }
}

