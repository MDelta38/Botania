/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.renderer.OpenGlHelper
 *  org.lwjgl.opengl.GL11
 */
package witchinggadgets.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;
import witchinggadgets.client.gui.GuiMagicalTileLock;

public class GuiButtonMagicTile
extends GuiButton {
    public boolean moveTop = false;
    public boolean moveBottom = false;
    public boolean moveLeft = false;
    public boolean moveRight = false;
    int moveProgress = 0;

    public GuiButtonMagicTile(int id, int x, int y) {
        super(id, x, y, 30, 30, "" + id);
    }

    public void func_146112_a(Minecraft mc, int mX, int mY) {
        if (this.moveProgress > 0 && this.moveProgress < 16) {
            if (this.moveTop) {
                this.field_146129_i -= 2;
            } else if (this.moveBottom) {
                this.field_146129_i += 2;
            } else if (this.moveLeft) {
                this.field_146128_h -= 2;
            } else if (this.moveRight) {
                this.field_146128_h += 2;
            }
            ++this.moveProgress;
        } else if (GuiMagicalTileLock.currentTile == this) {
            this.moveProgress = 0;
            this.moveRight = false;
            this.moveLeft = false;
            this.moveBottom = false;
            this.moveTop = false;
            GuiMagicalTileLock.currentTile = null;
        }
        if (this.field_146125_m) {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.field_146123_n = mX >= this.field_146128_h && mY >= this.field_146129_i && mX < this.field_146128_h + this.field_146120_f && mY < this.field_146129_i + this.field_146121_g;
            GL11.glEnable((int)3042);
            OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
            GL11.glBlendFunc((int)770, (int)771);
            this.func_73729_b(this.field_146128_h, this.field_146129_i, 128, 0, this.field_146120_f, this.field_146121_g);
            this.func_73729_b(this.field_146128_h, this.field_146129_i, 128 + this.field_146127_k % 3 * 30, 30 + this.field_146127_k / 3 * 30, this.field_146120_f, this.field_146121_g);
            this.func_146119_b(mc, mX, mY);
        }
    }
}

