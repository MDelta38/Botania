/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package vazkii.botania.common.block.tile;

import net.minecraft.nbt.NBTTagCompound;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.tile.TileMod;
import vazkii.botania.common.integration.coloredlights.ColoredLightHelper;

public class TileManaFlame
extends TileMod {
    private static final String TAG_COLOR = "color";
    int color = 0x20FF20;
    int lightColor = -1;

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return this.color;
    }

    public void func_145845_h() {
        float c = 0.3f;
        if (Math.random() < (double)c) {
            float v = 0.1f;
            float r = (float)(this.color >> 16 & 0xFF) / 255.0f + (float)(Math.random() - 0.5) * v;
            float g = (float)(this.color >> 8 & 0xFF) / 255.0f + (float)(Math.random() - 0.5) * v;
            float b = (float)(this.color & 0xFF) / 255.0f + (float)(Math.random() - 0.5) * v;
            float w = 0.15f;
            float h = 0.05f;
            double x = (double)this.field_145851_c + 0.5 + (Math.random() - 0.5) * (double)w;
            double y = (double)this.field_145848_d + 0.25 + (Math.random() - 0.5) * (double)h;
            double z = (double)this.field_145849_e + 0.5 + (Math.random() - 0.5) * (double)w;
            float s = 0.2f + (float)Math.random() * 0.1f;
            float m = 0.03f + (float)Math.random() * 0.015f;
            Botania.proxy.wispFX(this.field_145850_b, x, y, z, r, g, b, s, -m);
        }
    }

    public int getLightColor() {
        if (this.lightColor == -1) {
            float r = (float)(this.color >> 16 & 0xFF) / 255.0f;
            float g = (float)(this.color >> 8 & 0xFF) / 255.0f;
            float b = (float)(this.color & 0xFF) / 255.0f;
            this.lightColor = ColoredLightHelper.makeRGBLightValue(r, g, b, 1.0f);
        }
        return this.lightColor;
    }

    @Override
    public void writeCustomNBT(NBTTagCompound cmp) {
        cmp.func_74768_a(TAG_COLOR, this.color);
    }

    @Override
    public void readCustomNBT(NBTTagCompound cmp) {
        this.color = cmp.func_74762_e(TAG_COLOR);
    }
}

