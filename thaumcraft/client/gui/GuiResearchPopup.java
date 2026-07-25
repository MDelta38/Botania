/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.client.gui.GuiResearchBrowser;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.lib.utils.InventoryUtils;

@SideOnly(value=Side.CLIENT)
public class GuiResearchPopup
extends Gui {
    private Minecraft theGame;
    private int windowWidth;
    private int windowHeight;
    private ArrayList<ResearchItem> theResearch = new ArrayList();
    private long researchTime;
    private RenderItem itemRender;
    private static final ResourceLocation texture = new ResourceLocation("textures/gui/achievement/achievement_background.png");

    public GuiResearchPopup(Minecraft par1Minecraft) {
        this.theGame = par1Minecraft;
        this.itemRender = new RenderItem();
    }

    public void queueResearchInformation(ResearchItem research) {
        if (this.researchTime == 0L) {
            this.researchTime = Minecraft.func_71386_F();
        }
        this.theResearch.add(research);
        GuiResearchBrowser.lastX = research.displayColumn;
        GuiResearchBrowser.lastY = research.displayRow;
    }

    private void updateResearchWindowScale() {
        GL11.glViewport((int)0, (int)0, (int)this.theGame.field_71443_c, (int)this.theGame.field_71440_d);
        GL11.glMatrixMode((int)5889);
        GL11.glLoadIdentity();
        GL11.glMatrixMode((int)5888);
        GL11.glLoadIdentity();
        this.windowWidth = this.theGame.field_71443_c;
        this.windowHeight = this.theGame.field_71440_d;
        ScaledResolution var1 = new ScaledResolution(Minecraft.func_71410_x(), this.theGame.field_71443_c, this.theGame.field_71440_d);
        this.windowWidth = var1.func_78326_a();
        this.windowHeight = var1.func_78328_b();
        GL11.glClear((int)256);
        GL11.glMatrixMode((int)5889);
        GL11.glLoadIdentity();
        GL11.glOrtho((double)0.0, (double)this.windowWidth, (double)this.windowHeight, (double)0.0, (double)1000.0, (double)3000.0);
        GL11.glMatrixMode((int)5888);
        GL11.glLoadIdentity();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-2000.0f);
    }

    public void updateResearchWindow() {
        if (this.theResearch.size() > 0 && this.researchTime != 0L) {
            double var1 = (double)(Minecraft.func_71386_F() - this.researchTime) / 3000.0;
            if (var1 < 0.0 || var1 > 1.0) {
                this.theResearch.remove(0);
                this.researchTime = this.theResearch.size() > 0 ? Minecraft.func_71386_F() : 0L;
            } else {
                this.updateResearchWindowScale();
                GL11.glDisable((int)2929);
                GL11.glDepthMask((boolean)false);
                double var3 = var1 * 2.0;
                if (var3 > 1.0) {
                    var3 = 2.0 - var3;
                }
                var3 *= 4.0;
                if ((var3 = 1.0 - var3) < 0.0) {
                    var3 = 0.0;
                }
                var3 *= var3;
                var3 *= var3;
                int var5 = 0;
                int var6 = 0 - (int)(var3 * 36.0);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GL11.glEnable((int)3553);
                this.theGame.func_110434_K().func_110577_a(texture);
                GL11.glDisable((int)2896);
                this.func_73729_b(var5, var6, 96, 202, 160, 32);
                this.theGame.field_71466_p.func_78276_b("Research Completed!", var5 + 30, var6 + 7, -256);
                int offset = this.theGame.field_71466_p.func_78256_a(this.theResearch.get(0).getName());
                if (offset <= 125) {
                    this.theGame.field_71466_p.func_78276_b(this.theResearch.get(0).getName(), var5 + 30, var6 + 18, -1);
                } else {
                    float vv = 125.0f / (float)offset;
                    GL11.glPushMatrix();
                    GL11.glTranslatef((float)(var5 + 30), (float)((float)(var6 + 16) + 2.0f / vv), (float)0.0f);
                    GL11.glScalef((float)vv, (float)vv, (float)vv);
                    this.theGame.field_71466_p.func_78276_b(this.theResearch.get(0).getName(), 0, 0, -1);
                    GL11.glPopMatrix();
                }
                GL11.glDepthMask((boolean)true);
                GL11.glEnable((int)2929);
                RenderHelper.func_74520_c();
                GL11.glDisable((int)2896);
                GL11.glEnable((int)32826);
                GL11.glEnable((int)2903);
                GL11.glEnable((int)2896);
                if (this.theResearch.get((int)0).icon_item != null) {
                    this.itemRender.func_77015_a(Minecraft.func_71410_x().field_71466_p, Minecraft.func_71410_x().field_71446_o, InventoryUtils.cycleItemStack(this.theResearch.get((int)0).icon_item), var5 + 8, var6 + 8);
                } else if (this.theResearch.get((int)0).icon_resource != null) {
                    Minecraft.func_71410_x().field_71446_o.func_110577_a(this.theResearch.get((int)0).icon_resource);
                    UtilsFX.drawTexturedQuadFull(var5 + 8, var6 + 8, this.field_73735_i);
                }
                GL11.glDisable((int)2896);
            }
        }
    }
}

