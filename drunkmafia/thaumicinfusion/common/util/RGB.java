/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package drunkmafia.thaumicinfusion.common.util;

import java.util.Random;
import org.lwjgl.opengl.GL11;

public class RGB {
    private float r;
    private float g;
    private float b;
    private int rgb;

    public RGB() {
        Random rand = new Random();
        this.r = rand.nextFloat();
        this.g = rand.nextFloat();
        this.b = rand.nextFloat();
    }

    public RGB(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public RGB(int rgb) {
        this.rgb = rgb;
        this.r = (float)(rgb >> 16 & 0xFF) / 255.0f;
        this.g = (float)(rgb >> 8 & 0xFF) / 255.0f;
        this.b = (float)(rgb & 0xFF) / 255.0f;
    }

    public void addRGB(RGB rgb) {
        this.r += Math.min(255.0f, rgb.r);
        this.g += Math.min(255.0f, rgb.g);
        this.b += Math.min(255.0f, rgb.b);
    }

    public void takeRGB(RGB rgb) {
        this.r += Math.min(0.0f, rgb.r);
        this.g += Math.min(0.0f, rgb.g);
        this.b += Math.min(0.0f, rgb.b);
    }

    public void glColor3f() {
        GL11.glColor3f((float)this.r, (float)this.g, (float)this.b);
    }

    public float getRGB() {
        return this.rgb;
    }

    public float getB() {
        return this.b;
    }

    public float getG() {
        return this.g;
    }

    public float getR() {
        return this.r;
    }
}

