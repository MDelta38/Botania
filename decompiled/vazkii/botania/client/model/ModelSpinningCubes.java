/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.handler.ClientTickHandler;

public class ModelSpinningCubes
extends ModelBase {
    ModelRenderer spinningCube = new ModelRenderer((ModelBase)this, 42, 0);

    public ModelSpinningCubes() {
        this.spinningCube.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.spinningCube.func_78793_a(0.0f, 0.0f, 0.0f);
        this.spinningCube.func_78787_b(64, 64);
    }

    public void renderSpinningCubes(int cubes, int repeat, int origRepeat) {
        GL11.glDisable((int)3553);
        float modifier = 6.0f;
        float rotationModifier = 0.2f;
        float radiusBase = 0.35f;
        float radiusMod = 0.05f;
        double ticks = (double)((float)ClientTickHandler.ticksInGame + ClientTickHandler.partialTicks) - 1.3 * (double)(origRepeat - repeat);
        float offsetPerCube = 360 / cubes;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)-0.025f, (float)0.85f, (float)-0.025f);
        for (int i = 0; i < cubes; ++i) {
            float offset = offsetPerCube * (float)i;
            float deg = (int)(ticks / (double)0.2f % 360.0 + (double)offset);
            float rad = deg * (float)Math.PI / 180.0f;
            float radiusX = (float)((double)0.35f + (double)0.05f * Math.sin(ticks / 6.0));
            float radiusZ = (float)((double)0.35f + (double)0.05f * Math.cos(ticks / 6.0));
            float x = (float)((double)radiusX * Math.cos(rad));
            float z = (float)((double)radiusZ * Math.sin(rad));
            float y = (float)Math.cos((ticks + (double)(50 * i)) / 5.0) / 10.0f;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)x, (float)y, (float)z);
            float xRotate = (float)Math.sin(ticks * (double)0.2f) / 2.0f;
            float yRotate = (float)Math.max((double)0.6f, Math.sin(ticks * (double)0.1f) / 2.0 + 0.5);
            float zRotate = (float)Math.cos(ticks * (double)0.2f) / 2.0f;
            GL11.glRotatef((float)deg, (float)xRotate, (float)yRotate, (float)zRotate);
            if (repeat < origRepeat) {
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)((float)repeat / (float)origRepeat * 0.4f));
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                GL11.glDisable((int)3008);
            } else {
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            }
            int light = 0xF000F0;
            int lightmapX = light % 65536;
            int lightmapY = light / 65536;
            OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)lightmapX, (float)lightmapY);
            this.spinningCube.func_78785_a(0.0625f);
            if (repeat < origRepeat) {
                GL11.glDisable((int)3042);
                GL11.glEnable((int)3008);
            }
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
        GL11.glEnable((int)3553);
        if (repeat != 0) {
            this.renderSpinningCubes(cubes, repeat - 1, origRepeat);
        }
    }
}

