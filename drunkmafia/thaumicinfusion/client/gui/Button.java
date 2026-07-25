/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package drunkmafia.thaumicinfusion.client.gui;

import drunkmafia.thaumicinfusion.client.gui.Image;
import drunkmafia.thaumicinfusion.client.gui.TIGui;
import org.lwjgl.opengl.GL11;

public class Button {
    private final Image normal;
    private final Image hover;
    private final Image selected;
    private final Image icon;
    private final String text;
    private final Runnable runnable;
    private final TIGui gui;
    public boolean isSelected;

    public Button(Image normal, Image hover, Image selected, Image icon, String text, Runnable runnable) {
        this.normal = normal;
        this.hover = hover;
        this.selected = selected;
        this.icon = icon;
        this.text = text;
        this.runnable = runnable;
        this.gui = normal.getGui();
    }

    public void drawButton(int mouseX, int mouseY) {
        if (this.hover.isInRect(mouseX, mouseY)) {
            this.hover.drawImage();
        } else if (this.isSelected) {
            this.selected.drawImage();
        } else {
            this.normal.drawImage();
        }
        if (this.icon != null) {
            this.icon.drawImage();
        }
        if (this.text != null) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)this.gui.getGuiLeft(), (float)this.gui.getGuiTop(), (float)0.0f);
            GL11.glDisable((int)2896);
            this.gui.getFontRenderer().func_78276_b(this.text, this.normal.x, this.normal.y, 1);
            GL11.glEnable((int)2896);
            GL11.glPopMatrix();
        }
    }

    public void onMouseClick(int mouseX, int mouseY) {
        if (this.normal.isInRect(mouseX, mouseY) || this.selected.isInRect(mouseX, mouseY) || this.hover.isInRect(mouseX, mouseY)) {
            boolean bl = this.isSelected = !this.isSelected;
            if (this.runnable != null) {
                this.runnable.run();
            }
        }
    }
}

