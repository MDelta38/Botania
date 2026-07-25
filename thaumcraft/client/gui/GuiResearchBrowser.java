/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.gui;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.gui.GuiResearchRecipe;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.tile.TileNodeRenderer;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketPlayerCompleteToServer;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.utils.InventoryUtils;

@SideOnly(value=Side.CLIENT)
public class GuiResearchBrowser
extends GuiScreen {
    private static int guiMapTop;
    private static int guiMapLeft;
    private static int guiMapBottom;
    private static int guiMapRight;
    protected int paneWidth = 256;
    protected int paneHeight = 230;
    protected int mouseX = 0;
    protected int mouseY = 0;
    protected double field_74117_m;
    protected double field_74115_n;
    protected double guiMapX;
    protected double guiMapY;
    protected double field_74124_q;
    protected double field_74123_r;
    private int isMouseButtonDown = 0;
    public static int lastX;
    public static int lastY;
    private GuiButton button;
    private LinkedList<ResearchItem> research = new LinkedList();
    public static HashMap<String, ArrayList<String>> completedResearch;
    public static ArrayList<String> highlightedItem;
    private static String selectedCategory;
    private FontRenderer galFontRenderer;
    private ResearchItem currentHighlight = null;
    private String player = "";
    long popuptime = 0L;
    String popupmessage = "";
    public boolean hasScribestuff = false;

    public GuiResearchBrowser() {
        int var2 = 141;
        int var3 = 141;
        this.guiMapX = this.field_74124_q = (double)(lastX * 24 - var2 / 2 - 12);
        this.field_74117_m = this.field_74124_q;
        this.guiMapY = this.field_74123_r = (double)(lastY * 24 - var3 / 2);
        this.field_74115_n = this.field_74123_r;
        this.updateResearch();
        this.galFontRenderer = FMLClientHandler.instance().getClient().field_71464_q;
        this.player = Minecraft.func_71410_x().field_71439_g.func_70005_c_();
    }

    public GuiResearchBrowser(double x, double y) {
        this.guiMapX = this.field_74124_q = x;
        this.field_74117_m = this.field_74124_q;
        this.guiMapY = this.field_74123_r = y;
        this.field_74115_n = this.field_74123_r;
        this.updateResearch();
        this.galFontRenderer = FMLClientHandler.instance().getClient().field_71464_q;
        this.player = Minecraft.func_71410_x().field_71439_g.func_70005_c_();
    }

    public void updateResearch() {
        if (this.field_146297_k == null) {
            this.field_146297_k = Minecraft.func_71410_x();
        }
        this.research.clear();
        this.hasScribestuff = false;
        if (selectedCategory == null) {
            Set<String> cats = ResearchCategories.researchCategories.keySet();
            selectedCategory = (String)cats.iterator().next();
        }
        Collection<ResearchItem> col = ResearchCategories.getResearchList((String)GuiResearchBrowser.selectedCategory).research.values();
        for (ResearchItem res : col) {
            this.research.add(res);
        }
        if (ResearchManager.consumeInkFromPlayer((EntityPlayer)this.field_146297_k.field_71439_g, false) && InventoryUtils.isPlayerCarrying((EntityPlayer)this.field_146297_k.field_71439_g, new ItemStack(Items.field_151121_aF)) >= 0) {
            this.hasScribestuff = true;
        }
        guiMapTop = ResearchCategories.getResearchList((String)GuiResearchBrowser.selectedCategory).minDisplayColumn * 24 - 85;
        guiMapLeft = ResearchCategories.getResearchList((String)GuiResearchBrowser.selectedCategory).minDisplayRow * 24 - 112;
        guiMapBottom = ResearchCategories.getResearchList((String)GuiResearchBrowser.selectedCategory).maxDisplayColumn * 24 - 112;
        guiMapRight = ResearchCategories.getResearchList((String)GuiResearchBrowser.selectedCategory).maxDisplayRow * 24 - 61;
    }

    public void func_146281_b() {
        int var2 = 141;
        int var3 = 141;
        lastX = (int)((this.guiMapX + (double)(var2 / 2) + 12.0) / 24.0);
        lastY = (int)((this.guiMapY + (double)(var3 / 2)) / 24.0);
        super.func_146281_b();
    }

    public void func_73866_w_() {
    }

    protected void func_146284_a(GuiButton par1GuiButton) {
        super.func_146284_a(par1GuiButton);
    }

    protected void func_73869_a(char par1, int par2) {
        if (par2 == this.field_146297_k.field_71474_y.field_151445_Q.func_151463_i()) {
            highlightedItem.clear();
            this.field_146297_k.func_147108_a((GuiScreen)null);
            this.field_146297_k.func_71381_h();
        } else {
            if (par2 == 1) {
                highlightedItem.clear();
            }
            super.func_73869_a(par1, par2);
        }
    }

    public void func_73863_a(int mx, int my, float par3) {
        int var4 = (this.field_146294_l - this.paneWidth) / 2;
        int var5 = (this.field_146295_m - this.paneHeight) / 2;
        if (Mouse.isButtonDown((int)0)) {
            int var6 = var4 + 8;
            int var7 = var5 + 17;
            if ((this.isMouseButtonDown == 0 || this.isMouseButtonDown == 1) && mx >= var6 && mx < var6 + 224 && my >= var7 && my < var7 + 196) {
                if (this.isMouseButtonDown == 0) {
                    this.isMouseButtonDown = 1;
                } else {
                    this.guiMapX -= (double)(mx - this.mouseX);
                    this.guiMapY -= (double)(my - this.mouseY);
                    this.field_74124_q = this.field_74117_m = this.guiMapX;
                    this.field_74123_r = this.field_74115_n = this.guiMapY;
                }
                this.mouseX = mx;
                this.mouseY = my;
            }
            if (this.field_74124_q < (double)guiMapTop) {
                this.field_74124_q = guiMapTop;
            }
            if (this.field_74123_r < (double)guiMapLeft) {
                this.field_74123_r = guiMapLeft;
            }
            if (this.field_74124_q >= (double)guiMapBottom) {
                this.field_74124_q = guiMapBottom - 1;
            }
            if (this.field_74123_r >= (double)guiMapRight) {
                this.field_74123_r = guiMapRight - 1;
            }
        } else {
            this.isMouseButtonDown = 0;
        }
        this.func_146276_q_();
        this.genResearchBackground(mx, my, par3);
        if (this.popuptime > System.currentTimeMillis()) {
            int xq = var4 + 128;
            int yq = var5 + 128;
            int var41 = this.field_146289_q.func_78267_b(this.popupmessage, 150) / 2;
            this.func_73733_a(xq - 78, yq - var41 - 3, xq + 78, yq + var41 + 3, -1073741824, -1073741824);
            this.field_146289_q.func_78279_b(this.popupmessage, xq - 75, yq - var41, 150, -7302913);
        }
        Set<String> cats = ResearchCategories.researchCategories.keySet();
        int count = 0;
        boolean swop = false;
        for (Object e : cats) {
            if (count == 9) {
                count = 0;
                swop = true;
            }
            ResearchCategoryList rcl = ResearchCategories.getResearchList((String)e);
            if (((String)e).equals("ELDRITCH") && !ResearchManager.isResearchComplete(this.player, "ELDRITCHMINOR")) continue;
            int mposx = mx - (var4 - 24 + (swop ? 280 : 0));
            int mposy = my - (var5 + count * 24);
            if (mposx >= 0 && mposx < 24 && mposy >= 0 && mposy < 24) {
                this.field_146289_q.func_78261_a(ResearchCategories.getCategoryName((String)e), mx, my - 8, 0xFFFFFF);
            }
            ++count;
        }
    }

    public void func_73876_c() {
        this.field_74117_m = this.guiMapX;
        this.field_74115_n = this.guiMapY;
        double var1 = this.field_74124_q - this.guiMapX;
        double var3 = this.field_74123_r - this.guiMapY;
        if (var1 * var1 + var3 * var3 < 4.0) {
            this.guiMapX += var1;
            this.guiMapY += var3;
        } else {
            this.guiMapX += var1 * 0.85;
            this.guiMapY += var3 * 0.85;
        }
    }

    /*
     * WARNING - void declaration
     */
    protected void genResearchBackground(int par1, int par2, float par3) {
        int var42;
        int var30;
        int var27;
        int var26;
        int var24;
        long t = System.nanoTime() / 50000000L;
        int var4 = MathHelper.func_76128_c((double)(this.field_74117_m + (this.guiMapX - this.field_74117_m) * (double)par3));
        int var5 = MathHelper.func_76128_c((double)(this.field_74115_n + (this.guiMapY - this.field_74115_n) * (double)par3));
        if (var4 < guiMapTop) {
            var4 = guiMapTop;
        }
        if (var5 < guiMapLeft) {
            var5 = guiMapLeft;
        }
        if (var4 >= guiMapBottom) {
            var4 = guiMapBottom - 1;
        }
        if (var5 >= guiMapRight) {
            var5 = guiMapRight - 1;
        }
        int var8 = (this.field_146294_l - this.paneWidth) / 2;
        int var9 = (this.field_146295_m - this.paneHeight) / 2;
        int var10 = var8 + 16;
        int var11 = var9 + 17;
        this.field_73735_i = 0.0f;
        GL11.glDepthFunc((int)518);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-200.0f);
        GL11.glEnable((int)3553);
        RenderHelper.func_74520_c();
        GL11.glDisable((int)2896);
        GL11.glEnable((int)32826);
        GL11.glEnable((int)2903);
        GL11.glPushMatrix();
        GL11.glScalef((float)2.0f, (float)2.0f, (float)1.0f);
        int vx = (int)((float)(var4 - guiMapTop) / (float)Math.abs(guiMapTop - guiMapBottom) * 288.0f);
        int vy = (int)((float)(var5 - guiMapLeft) / (float)Math.abs(guiMapLeft - guiMapRight) * 316.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(ResearchCategories.getResearchList((String)GuiResearchBrowser.selectedCategory).background);
        this.func_73729_b(var10 / 2, var11 / 2, vx / 2, vy / 2, 112, 98);
        GL11.glScalef((float)0.5f, (float)0.5f, (float)1.0f);
        GL11.glPopMatrix();
        GL11.glEnable((int)2929);
        GL11.glDepthFunc((int)515);
        if (completedResearch.get(this.player) != null) {
            for (int var22 = 0; var22 < this.research.size(); ++var22) {
                boolean var29;
                boolean var28;
                int var25;
                int a;
                ResearchItem var33 = this.research.get(var22);
                if (var33.parents != null && var33.parents.length > 0) {
                    for (a = 0; a < var33.parents.length; ++a) {
                        ResearchItem parent;
                        if (var33.parents[a] == null || !ResearchCategories.getResearch((String)var33.parents[a]).category.equals(selectedCategory) || (parent = ResearchCategories.getResearch(var33.parents[a])).isVirtual()) continue;
                        var24 = var33.displayColumn * 24 - var4 + 11 + var10;
                        var25 = var33.displayRow * 24 - var5 + 11 + var11;
                        var26 = parent.displayColumn * 24 - var4 + 11 + var10;
                        var27 = parent.displayRow * 24 - var5 + 11 + var11;
                        var28 = completedResearch.get(this.player).contains(var33.key);
                        var29 = completedResearch.get(this.player).contains(parent.key);
                        int n = var30 = Math.sin((double)(Minecraft.func_71386_F() % 600L) / 600.0 * Math.PI * 2.0) > 0.6 ? 255 : 130;
                        if (var28) {
                            this.drawLine(var24, var25, var26, var27, 0.1f, 0.1f, 0.1f, par3, false);
                            continue;
                        }
                        if (var33.isLost() || (var33.isHidden() || var33.isLost()) && !completedResearch.get(this.player).contains("@" + var33.key) || var33.isConcealed() && !this.canUnlockResearch(var33)) continue;
                        if (var29) {
                            this.drawLine(var24, var25, var26, var27, 0.0f, 1.0f, 0.0f, par3, true);
                            continue;
                        }
                        if ((parent.isHidden() || var33.isLost()) && !completedResearch.get(this.player).contains("@" + parent.key) || parent.isConcealed() && !this.canUnlockResearch(parent)) continue;
                        this.drawLine(var24, var25, var26, var27, 0.0f, 0.0f, 1.0f, par3, true);
                    }
                }
                if (var33.siblings == null || var33.siblings.length <= 0) continue;
                for (a = 0; a < var33.siblings.length; ++a) {
                    ResearchItem sibling;
                    if (var33.siblings[a] == null || !ResearchCategories.getResearch((String)var33.siblings[a]).category.equals(selectedCategory) || (sibling = ResearchCategories.getResearch(var33.siblings[a])).isVirtual() || sibling.parents != null && (sibling.parents == null || Arrays.asList(sibling.parents).contains(var33.key))) continue;
                    var24 = var33.displayColumn * 24 - var4 + 11 + var10;
                    var25 = var33.displayRow * 24 - var5 + 11 + var11;
                    var26 = sibling.displayColumn * 24 - var4 + 11 + var10;
                    var27 = sibling.displayRow * 24 - var5 + 11 + var11;
                    var28 = completedResearch.get(this.player).contains(var33.key);
                    var29 = completedResearch.get(this.player).contains(sibling.key);
                    if (var28) {
                        this.drawLine(var24, var25, var26, var27, 0.1f, 0.1f, 0.2f, par3, false);
                        continue;
                    }
                    if (var33.isLost() || var33.isHidden() && !completedResearch.get(this.player).contains("@" + var33.key) || var33.isConcealed() && !this.canUnlockResearch(var33)) continue;
                    if (var29) {
                        this.drawLine(var24, var25, var26, var27, 0.0f, 1.0f, 0.0f, par3, true);
                        continue;
                    }
                    if (sibling.isHidden() && !completedResearch.get(this.player).contains("@" + sibling.key) || sibling.isConcealed() && !this.canUnlockResearch(sibling)) continue;
                    this.drawLine(var24, var25, var26, var27, 0.0f, 0.0f, 1.0f, par3, true);
                }
            }
        }
        this.currentHighlight = null;
        RenderItem itemRenderer = new RenderItem();
        GL11.glEnable((int)32826);
        GL11.glEnable((int)2903);
        if (completedResearch.get(this.player) != null) {
            for (var24 = 0; var24 < this.research.size(); ++var24) {
                float var38;
                ResearchItem var35 = this.research.get(var24);
                var26 = var35.displayColumn * 24 - var4;
                var27 = var35.displayRow * 24 - var5;
                if (var35.isVirtual() || var26 < -24 || var27 < -24 || var26 > 224 || var27 > 196) continue;
                var42 = var10 + var26;
                int var41 = var11 + var27;
                if (completedResearch.get(this.player).contains(var35.key)) {
                    if (ThaumcraftApi.getWarp(var35.key) > 0) {
                        this.drawForbidden(var42 + 11, var41 + 11);
                    }
                    var38 = 1.0f;
                    GL11.glColor4f((float)var38, (float)var38, (float)var38, (float)1.0f);
                } else {
                    if (!completedResearch.get(this.player).contains("@" + var35.key) && (var35.isLost() || var35.isHidden() && !completedResearch.get(this.player).contains("@" + var35.key) || var35.isConcealed() && !this.canUnlockResearch(var35))) continue;
                    if (ThaumcraftApi.getWarp(var35.key) > 0) {
                        this.drawForbidden(var42 + 11, var41 + 11);
                    }
                    if (this.canUnlockResearch(var35)) {
                        var38 = (float)Math.sin((double)(Minecraft.func_71386_F() % 600L) / 600.0 * Math.PI * 2.0) * 0.25f + 0.75f;
                        GL11.glColor4f((float)var38, (float)var38, (float)var38, (float)1.0f);
                    } else {
                        var38 = 0.3f;
                        GL11.glColor4f((float)var38, (float)var38, (float)var38, (float)1.0f);
                    }
                }
                UtilsFX.bindTexture("textures/gui/gui_research.png");
                GL11.glEnable((int)2884);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                if (var35.isRound()) {
                    this.func_73729_b(var42 - 2, var41 - 2, 54, 230, 26, 26);
                } else if (var35.isHidden()) {
                    if (Config.researchDifficulty == -1 || Config.researchDifficulty == 0 && var35.isSecondary()) {
                        this.func_73729_b(var42 - 2, var41 - 2, 230, 230, 26, 26);
                    } else {
                        this.func_73729_b(var42 - 2, var41 - 2, 86, 230, 26, 26);
                    }
                } else if (Config.researchDifficulty == -1 || Config.researchDifficulty == 0 && var35.isSecondary()) {
                    this.func_73729_b(var42 - 2, var41 - 2, 110, 230, 26, 26);
                } else {
                    this.func_73729_b(var42 - 2, var41 - 2, 0, 230, 26, 26);
                }
                if (var35.isSpecial()) {
                    this.func_73729_b(var42 - 2, var41 - 2, 26, 230, 26, 26);
                }
                if (!this.canUnlockResearch(var35)) {
                    float var40 = 0.1f;
                    GL11.glColor4f((float)var40, (float)var40, (float)var40, (float)1.0f);
                    itemRenderer.field_77024_a = false;
                }
                GL11.glDisable((int)3042);
                if (highlightedItem.contains(var35.key)) {
                    GL11.glPushMatrix();
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)770, (int)771);
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    this.field_146297_k.field_71446_o.func_110577_a(ParticleEngine.particleTexture);
                    int px = (int)(t % 16L) * 16;
                    GL11.glTranslatef((float)(var42 - 5), (float)(var41 - 5), (float)0.0f);
                    UtilsFX.drawTexturedQuad(0, 0, px, 80, 16, 16, 0.0);
                    GL11.glDisable((int)3042);
                    GL11.glPopMatrix();
                }
                if (var35.icon_item != null) {
                    GL11.glPushMatrix();
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)770, (int)771);
                    RenderHelper.func_74520_c();
                    GL11.glDisable((int)2896);
                    GL11.glEnable((int)32826);
                    GL11.glEnable((int)2903);
                    GL11.glEnable((int)2896);
                    itemRenderer.func_82406_b(this.field_146289_q, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(var35.icon_item), var42 + 3, var41 + 3);
                    GL11.glDisable((int)2896);
                    GL11.glDepthMask((boolean)true);
                    GL11.glEnable((int)2929);
                    GL11.glDisable((int)3042);
                    GL11.glPopMatrix();
                } else if (var35.icon_resource != null) {
                    GL11.glPushMatrix();
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)770, (int)771);
                    this.field_146297_k.field_71446_o.func_110577_a(var35.icon_resource);
                    if (!itemRenderer.field_77024_a) {
                        GL11.glColor4f((float)0.2f, (float)0.2f, (float)0.2f, (float)1.0f);
                    }
                    UtilsFX.drawTexturedQuadFull(var42 + 3, var41 + 3, this.field_73735_i);
                    GL11.glPopMatrix();
                }
                if (!this.canUnlockResearch(var35)) {
                    itemRenderer.field_77024_a = true;
                }
                if (par1 >= var10 && par2 >= var11 && par1 < var10 + 224 && par2 < var11 + 196 && par1 >= var42 && par1 <= var42 + 22 && par2 >= var41 && par2 <= var41 + 22) {
                    this.currentHighlight = var35;
                }
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            }
        }
        GL11.glDisable((int)2929);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Set<String> cats = ResearchCategories.researchCategories.keySet();
        int count = 0;
        boolean swop = false;
        for (Object e : cats) {
            int s2;
            ResearchCategoryList rcl = ResearchCategories.getResearchList((String)e);
            if (((String)e).equals("ELDRITCH") && !ResearchManager.isResearchComplete(this.player, "ELDRITCHMINOR")) continue;
            GL11.glPushMatrix();
            if (count == 9) {
                count = 0;
                swop = true;
            }
            int s0 = !swop ? 0 : 264;
            int s1 = 0;
            int n = s2 = swop ? 14 : 0;
            if (!selectedCategory.equals((String)e)) {
                s1 = 24;
                s2 = swop ? 6 : 8;
            }
            UtilsFX.bindTexture("textures/gui/gui_research.png");
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            if (swop) {
                this.drawTexturedModalRectReversed(var8 + s0 - 8, var9 + count * 24, 176 + s1, 232, 24, 24);
            } else {
                this.func_73729_b(var8 - 24 + s0, var9 + count * 24, 152 + s1, 232, 24, 24);
            }
            if (highlightedItem.contains((String)e)) {
                GL11.glPushMatrix();
                this.field_146297_k.field_71446_o.func_110577_a(ParticleEngine.particleTexture);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                int px = (int)(16L * (t % 16L));
                UtilsFX.drawTexturedQuad(var8 - 27 + s2 + s0, var9 - 4 + count * 24, px, 80, 16, 16, -90.0);
                GL11.glPopMatrix();
            }
            GL11.glPushMatrix();
            this.field_146297_k.field_71446_o.func_110577_a(rcl.icon);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            UtilsFX.drawTexturedQuadFull(var8 - 19 + s2 + s0, var9 + 4 + count * 24, -80.0);
            GL11.glPopMatrix();
            if (!selectedCategory.equals((String)e)) {
                UtilsFX.bindTexture("textures/gui/gui_research.png");
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                if (swop) {
                    this.drawTexturedModalRectReversed(var8 + s0 - 8, var9 + count * 24, 224, 232, 24, 24);
                } else {
                    this.func_73729_b(var8 - 24 + s0, var9 + count * 24, 200, 232, 24, 24);
                }
            }
            GL11.glPopMatrix();
            ++count;
        }
        UtilsFX.bindTexture("textures/gui/gui_research.png");
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.func_73729_b(var8, var9, 0, 0, this.paneWidth, this.paneHeight);
        GL11.glPopMatrix();
        this.field_73735_i = 0.0f;
        GL11.glDepthFunc((int)515);
        GL11.glDisable((int)2929);
        GL11.glEnable((int)3553);
        super.func_73863_a(par1, par2, par3);
        if (completedResearch.get(this.player) != null && this.currentHighlight != null) {
            String var34 = this.currentHighlight.getName();
            var26 = par1 + 6;
            var27 = par2 - 4;
            boolean bl = false;
            FontRenderer fr = this.field_146289_q;
            if (!completedResearch.get(this.player).contains(this.currentHighlight.key) && !this.canUnlockResearch(this.currentHighlight)) {
                fr = this.galFontRenderer;
            }
            if (this.canUnlockResearch(this.currentHighlight)) {
                void var27_41;
                int warp;
                boolean secondary = !completedResearch.get(this.player).contains(this.currentHighlight.key) && this.currentHighlight.tags != null && this.currentHighlight.tags.size() > 0 && (Config.researchDifficulty == -1 || Config.researchDifficulty == 0 && this.currentHighlight.isSecondary());
                boolean primary = !secondary && !completedResearch.get(this.player).contains(this.currentHighlight.key);
                var42 = (int)Math.max((float)fr.func_78256_a(var34), (float)fr.func_78256_a(this.currentHighlight.getText()) / 1.9f);
                int var41 = fr.func_78267_b(var34, var42) + 5;
                if (primary) {
                    var27_39 += 9;
                    var42 = (int)Math.max((float)var42, (float)fr.func_78256_a(StatCollector.func_74838_a((String)"tc.research.shortprim")) / 1.9f);
                }
                if (secondary) {
                    var27_40 += 29;
                    var42 = (int)Math.max((float)var42, (float)fr.func_78256_a(StatCollector.func_74838_a((String)"tc.research.short")) / 1.9f);
                }
                if ((warp = ThaumcraftApi.getWarp(this.currentHighlight.key)) > 5) {
                    warp = 5;
                }
                String ws = StatCollector.func_74838_a((String)"tc.forbidden");
                String wr = StatCollector.func_74838_a((String)("tc.forbidden.level." + warp));
                String wte = ws.replaceAll("%n", wr);
                if (ThaumcraftApi.getWarp(this.currentHighlight.key) > 0) {
                    var27_41 += 9;
                    var42 = (int)Math.max((float)var42, (float)fr.func_78256_a(wte) / 1.9f);
                }
                this.func_73733_a(var26 - 3, var27 - 3, var26 + var42 + 3, var27 + var41 + 6 + var27_41, -1073741824, -1073741824);
                GL11.glPushMatrix();
                GL11.glTranslatef((float)var26, (float)(var27 + var41 - 1), (float)0.0f);
                GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
                this.field_146289_q.func_78261_a(this.currentHighlight.getText(), 0, 0, -7302913);
                GL11.glPopMatrix();
                if (warp > 0) {
                    GL11.glPushMatrix();
                    GL11.glTranslatef((float)var26, (float)(var27 + var41 + 8), (float)0.0f);
                    GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
                    this.field_146289_q.func_78261_a(wte, 0, 0, 0xFFFFFF);
                    GL11.glPopMatrix();
                    var41 += 9;
                }
                GL11.glPushMatrix();
                if (primary) {
                    GL11.glPushMatrix();
                    GL11.glTranslatef((float)var26, (float)(var27 + var41 + 8), (float)0.0f);
                    GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
                    if (ResearchManager.getResearchSlot((EntityPlayer)this.field_146297_k.field_71439_g, this.currentHighlight.key) >= 0) {
                        this.field_146289_q.func_78261_a(StatCollector.func_74838_a((String)"tc.research.hasnote"), 0, 0, 16753920);
                    } else if (this.hasScribestuff) {
                        this.field_146289_q.func_78261_a(StatCollector.func_74838_a((String)"tc.research.getprim"), 0, 0, 8900331);
                    } else {
                        this.field_146289_q.func_78261_a(StatCollector.func_74838_a((String)"tc.research.shortprim"), 0, 0, 14423100);
                    }
                    GL11.glPopMatrix();
                } else if (secondary) {
                    boolean enough = true;
                    int cc = 0;
                    for (Aspect a : this.currentHighlight.tags.getAspectsSortedAmount()) {
                        if (Thaumcraft.proxy.playerKnowledge.hasDiscoveredAspect(this.player, a)) {
                            float alpha = 1.0f;
                            if (Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(this.player, a) < this.currentHighlight.tags.getAmount(a)) {
                                alpha = (float)Math.sin((double)(Minecraft.func_71386_F() % 600L) / 600.0 * Math.PI * 2.0) * 0.25f + 0.75f;
                                enough = false;
                            }
                            GL11.glPushMatrix();
                            GL11.glPushAttrib((int)1048575);
                            UtilsFX.drawTag(var26 + cc * 16, var27 + var41 + 8, a, (float)this.currentHighlight.tags.getAmount(a), 0, 0.0, 771, alpha, false);
                            GL11.glPopAttrib();
                            GL11.glPopMatrix();
                        } else {
                            enough = false;
                            GL11.glPushMatrix();
                            UtilsFX.bindTexture("textures/aspects/_unknown.png");
                            GL11.glColor4f((float)0.5f, (float)0.5f, (float)0.5f, (float)0.5f);
                            GL11.glTranslated((double)(var26 + cc * 16), (double)(var27 + var41 + 8), (double)0.0);
                            UtilsFX.drawTexturedQuadFull(0, 0, 0.0);
                            GL11.glPopMatrix();
                        }
                        ++cc;
                    }
                    GL11.glPushMatrix();
                    GL11.glTranslatef((float)var26, (float)(var27 + var41 + 27), (float)0.0f);
                    GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
                    if (enough) {
                        this.field_146289_q.func_78261_a(StatCollector.func_74838_a((String)"tc.research.purchase"), 0, 0, 8900331);
                    } else {
                        this.field_146289_q.func_78261_a(StatCollector.func_74838_a((String)"tc.research.short"), 0, 0, 14423100);
                    }
                    GL11.glPopMatrix();
                }
                GL11.glPopMatrix();
            } else {
                GL11.glPushMatrix();
                var42 = (int)Math.max((float)fr.func_78256_a(var34), (float)fr.func_78256_a(StatCollector.func_74838_a((String)"tc.researchmissing")) / 1.5f);
                String var39 = StatCollector.func_74838_a((String)"tc.researchmissing");
                var30 = fr.func_78267_b(var39, var42 * 2);
                this.func_73733_a(var26 - 3, var27 - 3, var26 + var42 + 3, var27 + var30 + 10, -1073741824, -1073741824);
                GL11.glTranslatef((float)var26, (float)(var27 + 12), (float)0.0f);
                GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
                this.field_146289_q.func_78279_b(var39, 0, 0, var42 * 2, -9416624);
                GL11.glPopMatrix();
            }
            fr.func_78261_a(var34, var26, var27, this.canUnlockResearch(this.currentHighlight) ? (this.currentHighlight.isSpecial() ? -128 : -1) : (this.currentHighlight.isSpecial() ? -8355776 : -8355712));
        }
        GL11.glEnable((int)2929);
        GL11.glEnable((int)2896);
        RenderHelper.func_74518_a();
    }

    protected void func_73864_a(int par1, int par2, int par3) {
        block9: {
            block7: {
                block8: {
                    boolean secondary;
                    this.popuptime = System.currentTimeMillis() - 1L;
                    if (this.currentHighlight == null || completedResearch.get(this.player).contains(this.currentHighlight.key) || !this.canUnlockResearch(this.currentHighlight)) break block7;
                    this.updateResearch();
                    boolean bl = secondary = this.currentHighlight.tags != null && this.currentHighlight.tags.size() > 0 && (Config.researchDifficulty == -1 || Config.researchDifficulty == 0 && this.currentHighlight.isSecondary());
                    if (!secondary) break block8;
                    boolean enough = true;
                    for (Aspect a : this.currentHighlight.tags.getAspects()) {
                        if (Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(this.player, a) >= this.currentHighlight.tags.getAmount(a)) continue;
                        enough = false;
                        break;
                    }
                    if (enough) {
                        PacketHandler.INSTANCE.sendToServer((IMessage)new PacketPlayerCompleteToServer(this.currentHighlight.key, this.field_146297_k.field_71439_g.func_70005_c_(), this.field_146297_k.field_71439_g.field_70170_p.field_73011_w.field_76574_g, 0));
                    }
                    break block9;
                }
                if (!this.hasScribestuff || ResearchManager.getResearchSlot((EntityPlayer)this.field_146297_k.field_71439_g, this.currentHighlight.key) != -1) break block9;
                PacketHandler.INSTANCE.sendToServer((IMessage)new PacketPlayerCompleteToServer(this.currentHighlight.key, this.field_146297_k.field_71439_g.func_70005_c_(), this.field_146297_k.field_71439_g.field_70170_p.field_73011_w.field_76574_g, 1));
                this.popuptime = System.currentTimeMillis() + 3000L;
                this.popupmessage = new ChatComponentTranslation(StatCollector.func_74838_a((String)"tc.research.popup"), new Object[]{"" + this.currentHighlight.getName()}).func_150260_c();
                break block9;
            }
            if (this.currentHighlight != null && completedResearch.get(this.player).contains(this.currentHighlight.key)) {
                this.field_146297_k.func_147108_a((GuiScreen)new GuiResearchRecipe(this.currentHighlight, 0, this.guiMapX, this.guiMapY));
            } else {
                int var4 = (this.field_146294_l - this.paneWidth) / 2;
                int var5 = (this.field_146295_m - this.paneHeight) / 2;
                Set<String> cats = ResearchCategories.researchCategories.keySet();
                int count = 0;
                boolean swop = false;
                for (Object e : cats) {
                    ResearchCategoryList rcl = ResearchCategories.getResearchList((String)e);
                    if (((String)e).equals("ELDRITCH") && !ResearchManager.isResearchComplete(this.player, "ELDRITCHMINOR")) continue;
                    if (count == 9) {
                        count = 0;
                        swop = true;
                    }
                    int mposx = par1 - (var4 - 24 + (swop ? 280 : 0));
                    int mposy = par2 - (var5 + count * 24);
                    if (mposx >= 0 && mposx < 24 && mposy >= 0 && mposy < 24) {
                        selectedCategory = (String)e;
                        this.updateResearch();
                        this.playButtonClick();
                        break;
                    }
                    ++count;
                }
            }
        }
        super.func_73864_a(par1, par2, par3);
    }

    public void drawTexturedModalRectReversed(int par1, int par2, int par3, int par4, int par5, int par6) {
        float f = 0.00390625f;
        float f1 = 0.00390625f;
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78374_a((double)(par1 + 0), (double)(par2 + par6), (double)this.field_73735_i, (double)((float)(par3 + 0) * f), (double)((float)(par4 + par6) * f1));
        tessellator.func_78374_a((double)(par1 + par5), (double)(par2 + par6), (double)this.field_73735_i, (double)((float)(par3 - par5) * f), (double)((float)(par4 + par6) * f1));
        tessellator.func_78374_a((double)(par1 + par5), (double)(par2 + 0), (double)this.field_73735_i, (double)((float)(par3 - par5) * f), (double)((float)(par4 + 0) * f1));
        tessellator.func_78374_a((double)(par1 + 0), (double)(par2 + 0), (double)this.field_73735_i, (double)((float)(par3 + 0) * f), (double)((float)(par4 + 0) * f1));
        tessellator.func_78381_a();
    }

    private void playButtonClick() {
        this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:cameraclack", 0.4f, 1.0f, false);
    }

    private boolean canUnlockResearch(ResearchItem res) {
        ResearchItem parent;
        if (res.parents != null && res.parents.length > 0) {
            for (String pt : res.parents) {
                parent = ResearchCategories.getResearch(pt);
                if (parent == null || completedResearch.get(this.player).contains(parent.key)) continue;
                return false;
            }
        }
        if (res.parentsHidden != null && res.parentsHidden.length > 0) {
            for (String pt : res.parentsHidden) {
                parent = ResearchCategories.getResearch(pt);
                if (parent == null || completedResearch.get(this.player).contains(parent.key)) continue;
                return false;
            }
        }
        return true;
    }

    public boolean func_73868_f() {
        return false;
    }

    private void drawLine(int x, int y, int x2, int y2, float r, float g, float b, float te, boolean wiggle) {
        float count = (float)FMLClientHandler.instance().getClient().field_71439_g.field_70173_aa + te;
        Tessellator var12 = Tessellator.field_78398_a;
        GL11.glPushMatrix();
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        GL11.glDisable((int)3553);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        double d3 = x - x2;
        double d4 = y - y2;
        float dist = MathHelper.func_76133_a((double)(d3 * d3 + d4 * d4));
        int inc = (int)(dist / 2.0f);
        float dx = (float)(d3 / (double)inc);
        float dy = (float)(d4 / (double)inc);
        if (Math.abs(d3) > Math.abs(d4)) {
            dx *= 2.0f;
        } else {
            dy *= 2.0f;
        }
        GL11.glLineWidth((float)3.0f);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        var12.func_78371_b(3);
        for (int a = 0; a <= inc; ++a) {
            float r2 = r;
            float g2 = g;
            float b2 = b;
            float mx = 0.0f;
            float my = 0.0f;
            float op = 0.6f;
            if (wiggle) {
                float phase = (float)a / (float)inc;
                mx = MathHelper.func_76126_a((float)((count + (float)a) / 7.0f)) * 5.0f * (1.0f - phase);
                my = MathHelper.func_76126_a((float)((count + (float)a) / 5.0f)) * 5.0f * (1.0f - phase);
                r2 *= 1.0f - phase;
                g2 *= 1.0f - phase;
                b2 *= 1.0f - phase;
                op *= phase;
            }
            var12.func_78369_a(r2, g2, b2, op);
            var12.func_78377_a((double)((float)x - dx * (float)a + mx), (double)((float)y - dy * (float)a + my), 0.0);
            if (Math.abs(d3) > Math.abs(d4)) {
                dx *= 1.0f - 1.0f / ((float)inc * 3.0f / 2.0f);
                continue;
            }
            dy *= 1.0f - 1.0f / ((float)inc * 3.0f / 2.0f);
        }
        var12.func_78381_a();
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)2848);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)32826);
        GL11.glEnable((int)3553);
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glPopMatrix();
    }

    private void drawForbidden(double x, double y) {
        int count = FMLClientHandler.instance().getClient().field_71439_g.field_70173_aa;
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        UtilsFX.bindTexture(TileNodeRenderer.nodetex);
        int frames = 32;
        int part = count % frames;
        GL11.glTranslated((double)x, (double)y, (double)0.0);
        UtilsFX.renderAnimatedQuadStrip(80.0f, 0.66f, frames, 5, frames - 1 - part, 0.0f, 0x440055);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    static {
        lastX = -5;
        lastY = -6;
        completedResearch = new HashMap();
        highlightedItem = new ArrayList();
        selectedCategory = null;
    }
}

