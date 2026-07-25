/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.inventory.Container
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package thaumic.tinkerer.client.gui.kami;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiIchorPouch
extends GuiContainer {
    private static final ResourceLocation gui = new ResourceLocation("ttinkerer:textures/gui/ichorPouch.png");
    int x;
    int y;

    public GuiIchorPouch(Container par1Container) {
        super(par1Container);
    }

    protected boolean func_146983_a(int slot) {
        if (this.field_146297_k.field_71474_y.field_151456_ac[this.field_146297_k.field_71439_g.field_71071_by.field_70461_c].func_151463_i() != slot) {
            super.func_146983_a(slot);
        }
        return false;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        this.field_147000_g = 256;
        this.field_146999_f = 256;
        this.field_147003_i = this.x = (this.field_146294_l - this.field_146999_f) / 2;
        this.field_147009_r = this.y = (this.field_146295_m - this.field_147000_g) / 2;
    }

    protected void func_146976_a(float f, int i, int j) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3042);
        this.field_146297_k.field_71446_o.func_110577_a(gui);
        this.func_73729_b(this.x, this.y, 0, 0, this.field_146999_f, this.field_147000_g);
        GL11.glDisable((int)3042);
    }
}

