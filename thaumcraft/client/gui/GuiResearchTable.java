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
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
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
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.lib.PlayerNotifications;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.container.ContainerResearchTable;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketAspectCombinationToServer;
import thaumcraft.common.lib.network.playerdata.PacketAspectPlaceToServer;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.research.ResearchNoteData;
import thaumcraft.common.lib.utils.HexUtils;
import thaumcraft.common.tiles.TileResearchTable;

@SideOnly(value=Side.CLIENT)
public class GuiResearchTable
extends GuiContainer {
    private static boolean RESEARCHER_1;
    private static boolean RESEARCHER_2;
    private static boolean RESEARCHDUPE;
    private final int HEX_SIZE = 9;
    private float xSize_lo;
    private float ySize_lo;
    private long butcount1 = 0L;
    private long butcount2 = 0L;
    private int page = 0;
    private int lastPage = 0;
    private int isMouseButtonDown = 0;
    private TileResearchTable tileEntity;
    private FontRenderer galFontRenderer;
    private String username;
    EntityPlayer player;
    public Aspect select1 = null;
    public Aspect select2 = null;
    private AspectList aspectlist = new AspectList();
    private HashMap<String, Rune> runes = new HashMap();
    private float popupScale = 0.05f;
    private Aspect draggedAspect;
    public ResearchNoteData note = null;
    long lastRuneCheck = 0L;
    private HashMap<String, HexUtils.Hex[]> lines = new HashMap();
    private ArrayList<String> checked = new ArrayList();
    private ArrayList<String> highlight = new ArrayList();

    public GuiResearchTable(EntityPlayer player, TileResearchTable e) {
        super((Container)new ContainerResearchTable(player.field_71071_by, e));
        this.tileEntity = e;
        this.field_146999_f = 255;
        this.field_147000_g = 255;
        this.galFontRenderer = FMLClientHandler.instance().getClient().field_71464_q;
        this.username = player.func_70005_c_();
        this.player = player;
        RESEARCHER_1 = ResearchManager.isResearchComplete(player.func_70005_c_(), "RESEARCHER1");
        RESEARCHER_2 = ResearchManager.isResearchComplete(player.func_70005_c_(), "RESEARCHER2");
        RESEARCHDUPE = ResearchManager.isResearchComplete(player.func_70005_c_(), "RESEARCHDUPE");
        int count = 0;
        for (Aspect aspect : Aspect.aspects.values()) {
            this.aspectlist.add(aspect, count);
            ++count;
        }
    }

    protected void func_146979_b(int mx, int my) {
        Minecraft mc = Minecraft.func_71410_x();
        long time = System.nanoTime() / 1000000L;
        if (PlayerNotifications.getListAndUpdate(time).size() > 0) {
            GL11.glPushMatrix();
            Thaumcraft.instance.renderEventHandler.notifyHandler.renderNotifyHUD(this.field_146294_l, this.field_146295_m, time);
            GL11.glPopMatrix();
        }
    }

    public void func_73863_a(int mx, int my, float par3) {
        int sx;
        super.func_73863_a(mx, my, par3);
        this.xSize_lo = mx;
        this.ySize_lo = my;
        int var5 = this.field_147003_i;
        int var6 = this.field_147009_r;
        int gx = (this.field_146294_l - this.field_146999_f) / 2;
        int gy = (this.field_146295_m - this.field_147000_g) / 2;
        if (this.note != null && RESEARCHDUPE && this.note.isComplete()) {
            int var7 = mx - (gx + 37);
            int var8 = my - (gy + 5);
            if (var7 >= 0 && var8 >= 0 && var7 < 24 && var8 < 24) {
                RenderHelper.func_74520_c();
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResearchItem rr = ResearchCategories.getResearch(this.note.key);
                String ss = StatCollector.func_74838_a((String)"tc.research.copy");
                GL11.glEnable((int)3042);
                UtilsFX.bindTexture("textures/gui/guiresearchtable2.png");
                this.func_73729_b(gx + 100, gy + 21, 184, 224, 48, 16);
                AspectList al = rr.tags.copy();
                for (Aspect aspect : al.getAspects()) {
                    al.add(aspect, this.note.copies);
                }
                int count = 0;
                for (Aspect aspect : al.getAspectsSorted()) {
                    UtilsFX.drawTag(gx + 100 + 48 + count * 16, gy + 21, aspect, al.getAmount(aspect), 0, this.field_73735_i);
                    ++count;
                }
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                this.field_146289_q.func_78261_a(ss, gx + 100, gy + 12, -1);
            }
        }
        RenderHelper.func_74518_a();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (Mouse.isButtonDown((int)0)) {
            sx = gx + 10;
            int sy = gy + 40;
            if (this.isMouseButtonDown == 0 && mx >= sx && mx < sx + 80 && my >= sy && my < sy + 80) {
                Aspect aspect = this.getClickedAspect(mx, my, gx, gy, false);
                if (aspect != null) {
                    this.playButtonAspect();
                    this.isMouseButtonDown = 1;
                    this.draggedAspect = aspect;
                }
            } else if (this.isMouseButtonDown == 1 && this.draggedAspect != null) {
                GL11.glEnable((int)3042);
                this.drawOrb(mx - 8, my - 8, this.draggedAspect.getColor());
                GL11.glDisable((int)3042);
            }
        } else {
            if (this.isMouseButtonDown == 1 && this.draggedAspect != null) {
                int mouseY;
                int mouseX;
                HexUtils.Hex hp;
                if (this.note != null && this.note.hexEntries.containsKey((hp = new HexUtils.Pixel(mouseX = mx - (gx + 169), mouseY = my - (gy + 83)).toHex(9)).toString()) && this.note.hexEntries.get((Object)hp.toString()).type == 0) {
                    this.playButtonCombine();
                    this.playButtonWrite();
                    PacketHandler.INSTANCE.sendToServer((IMessage)new PacketAspectPlaceToServer(this.player, (byte)hp.q, (byte)hp.r, this.tileEntity.field_145851_c, this.tileEntity.field_145848_d, this.tileEntity.field_145849_e, this.draggedAspect));
                    this.draggedAspect = null;
                }
                if (this.draggedAspect != null) {
                    Aspect aspect;
                    boolean skip = false;
                    int mouseX2 = mx - (gx + 20);
                    int mouseY2 = my - (gy + 146);
                    if (mouseX2 >= -16 && mouseY2 >= -16 && mouseX2 < 16 && mouseY2 < 16) {
                        this.playButtonAspect();
                        this.select1 = this.draggedAspect;
                        skip = true;
                    }
                    mouseX2 = mx - (gx + 79);
                    mouseY2 = my - (gy + 146);
                    if (!skip && mouseX2 >= -16 && mouseY2 >= -16 && mouseX2 < 16 && mouseY2 < 16) {
                        this.playButtonAspect();
                        this.select2 = this.draggedAspect;
                        skip = true;
                    }
                    if (!skip && (aspect = this.getClickedAspect(mx, my, gx, gy, false)) == this.draggedAspect) {
                        if (this.select1 == null) {
                            this.select1 = this.draggedAspect;
                        } else if (this.select2 == null) {
                            this.select2 = this.draggedAspect;
                        }
                    }
                }
            }
            this.isMouseButtonDown = 0;
            this.draggedAspect = null;
        }
        this.drawAspectText(var5 + 10, var6 + 40, mx, my);
        if (this.note != null && (this.tileEntity.func_70301_a(0) == null || this.tileEntity.func_70301_a(0).func_77960_j() == this.tileEntity.func_70301_a(0).func_77958_k())) {
            sx = Math.max(this.field_146289_q.func_78256_a(StatCollector.func_74838_a((String)"tile.researchtable.noink.0")), this.field_146289_q.func_78256_a(StatCollector.func_74838_a((String)"tile.researchtable.noink.1"))) / 2;
            UtilsFX.drawCustomTooltip((GuiScreen)this, field_146296_j, this.field_146289_q, Arrays.asList(StatCollector.func_74838_a((String)"tile.researchtable.noink.0"), StatCollector.func_74838_a((String)"tile.researchtable.noink.1")), gx + 157 - sx, gy + 84, 11);
        }
    }

    protected void func_146976_a(float par1, int par2, int par3) {
        int var5 = this.field_147003_i;
        int var6 = this.field_147009_r;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3042);
        UtilsFX.bindTexture("textures/gui/guiresearchtable2.png");
        this.func_73729_b(var5, var6, 0, 0, 255, 167);
        this.func_73729_b(var5 + 40, var6 + 167, 0, 166, 184, 88);
        if (this.page < this.lastPage) {
            this.func_73729_b(var5 + 51, var6 + 121, 208, 208, 24, 8);
        }
        if (this.page > 0) {
            this.func_73729_b(var5 + 27, var6 + 121, 184, 208, 24, 8);
        }
        if (this.butcount2 < System.nanoTime() && this.select1 != null && this.select2 != null) {
            this.func_73729_b(var5 + 35, var6 + 139, 184, 184, 32, 16);
            this.drawOrb(var5 + 43, var6 + 139);
        } else if (this.butcount2 >= System.nanoTime() && this.select1 != null && this.select2 != null) {
            this.func_73729_b(var5 + 35, var6 + 139, 184, 184, 32, 16);
            this.func_73729_b(var5 + 35, var6 + 139, 184, 168, 32, 16);
        }
        if (RESEARCHDUPE && this.note != null && this.note.isComplete()) {
            this.func_73729_b(var5 + 37, var6 + 5, 232, 200, 24, 24);
        }
        this.drawAspects(var5 + 10, var6 + 40);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderHelper.func_74518_a();
        this.drawResearchData(var5, var6, par2, par3);
    }

    private void drawAspects(int x, int y) {
        AspectList aspects = Thaumcraft.proxy.getPlayerKnowledge().getAspectsDiscovered(this.username);
        if (aspects != null) {
            int count = aspects.size();
            this.lastPage = (count - 20) / 5;
            count = 0;
            int drawn = 0;
            for (Aspect aspect : aspects.getAspectsSorted()) {
                if (++count - 1 < this.page * 5 || drawn >= 25) continue;
                boolean faded = aspects.getAmount(aspect) <= 0 && this.tileEntity.bonusAspects.getAmount(aspect) <= 0;
                int xx = drawn / 5 * 16;
                int yy = drawn % 5 * 16;
                UtilsFX.drawTag(x + xx, y + yy, aspect, aspects.getAmount(aspect), this.tileEntity.bonusAspects.getAmount(aspect), this.field_73735_i, 771, faded ? 0.33f : 1.0f);
                ++drawn;
            }
        }
        if (this.select1 != null && Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(this.player.func_70005_c_(), this.select1) <= 0 && this.tileEntity.bonusAspects.getAmount(this.select1) <= 0) {
            this.select1 = null;
        }
        if (this.select2 != null && Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(this.player.func_70005_c_(), this.select2) <= 0 && this.tileEntity.bonusAspects.getAmount(this.select2) <= 0) {
            this.select2 = null;
        }
        if (this.select1 != null) {
            UtilsFX.drawTag(x + 3, y + 99, this.select1, 0.0f, 0, this.field_73735_i);
        }
        if (this.select2 != null) {
            UtilsFX.drawTag(x + 61, y + 99, this.select2, 0.0f, 0, this.field_73735_i);
        }
    }

    private void drawAspectText(int x, int y, int mx, int my) {
        int var7 = 0;
        int var8 = 0;
        AspectList aspects = Thaumcraft.proxy.getPlayerKnowledge().getAspectsDiscovered(this.username);
        if (aspects != null) {
            int count = 0;
            int drawn = 0;
            for (Aspect aspect : aspects.getAspectsSorted()) {
                if (++count - 1 < this.page * 5 || drawn >= 25) continue;
                int xx = drawn / 5 * 16;
                int yy = drawn % 5 * 16;
                var7 = mx - (x + xx);
                var8 = my - (y + yy);
                if (var7 >= 0 && var8 >= 0 && var7 < 16 && var8 < 16) {
                    UtilsFX.drawCustomTooltip((GuiScreen)this, field_146296_j, this.field_146289_q, Arrays.asList(aspect.getName(), aspect.getLocalizedDescription()), mx, my - 8, 11);
                    if (RESEARCHER_1 && !aspect.isPrimal()) {
                        GL11.glPushMatrix();
                        GL11.glEnable((int)3042);
                        GL11.glBlendFunc((int)770, (int)771);
                        UtilsFX.bindTexture("textures/aspects/_back.png");
                        GL11.glPushMatrix();
                        GL11.glTranslated((double)(mx + 6), (double)(my + 6), (double)0.0);
                        GL11.glScaled((double)1.25, (double)1.25, (double)0.0);
                        UtilsFX.drawTexturedQuadFull(0, 0, 0.0);
                        GL11.glPopMatrix();
                        GL11.glPushMatrix();
                        GL11.glTranslated((double)(mx + 24), (double)(my + 6), (double)0.0);
                        GL11.glScaled((double)1.25, (double)1.25, (double)0.0);
                        UtilsFX.drawTexturedQuadFull(0, 0, 0.0);
                        GL11.glPopMatrix();
                        UtilsFX.drawTag(mx + 26, my + 8, aspect.getComponents()[1], 0.0f, 0, 0.0);
                        UtilsFX.drawTag(mx + 8, my + 8, aspect.getComponents()[0], 0.0f, 0, 0.0);
                        GL11.glDisable((int)3042);
                        GL11.glPopMatrix();
                    }
                    return;
                }
                ++drawn;
            }
        }
        if (this.select1 != null) {
            var7 = mx - (x + 3);
            var8 = my - (y + 99);
            if (var7 >= 0 && var8 >= 0 && var7 < 16 && var8 < 16) {
                UtilsFX.drawCustomTooltip((GuiScreen)this, field_146296_j, this.field_146289_q, Arrays.asList(this.select1.getName(), this.select1.getLocalizedDescription()), mx, my - 8, 11);
                return;
            }
        }
        if (this.select2 != null) {
            var7 = mx - (x + 61);
            var8 = my - (y + 99);
            if (var7 >= 0 && var8 >= 0 && var7 < 16 && var8 < 16) {
                UtilsFX.drawCustomTooltip((GuiScreen)this, field_146296_j, this.field_146289_q, Arrays.asList(this.select2.getName(), this.select2.getLocalizedDescription()), mx, my - 8, 11);
                return;
            }
        }
    }

    private void drawResearchData(int x, int y, int mx, int my) {
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        this.drawSheet(x, y, mx, my);
        GL11.glPopMatrix();
    }

    private void drawHex(HexUtils.Hex hex, int x, int y) {
        GL11.glPushMatrix();
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        GL11.glEnable((int)3042);
        UtilsFX.bindTexture("textures/gui/hex1.png");
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.25f);
        HexUtils.Pixel pix = hex.toPixel(9);
        GL11.glTranslated((double)((double)x + pix.x), (double)((double)y + pix.y), (double)0.0);
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78380_c(240);
        tessellator.func_78369_a(1.0f, 1.0f, 1.0f, 0.25f);
        tessellator.func_78374_a(-8.0, 8.0, (double)this.field_73735_i, 0.0, 1.0);
        tessellator.func_78374_a(8.0, 8.0, (double)this.field_73735_i, 1.0, 1.0);
        tessellator.func_78374_a(8.0, -8.0, (double)this.field_73735_i, 1.0, 0.0);
        tessellator.func_78374_a(-8.0, -8.0, (double)this.field_73735_i, 0.0, 0.0);
        tessellator.func_78381_a();
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glPopMatrix();
    }

    private void drawHexHighlight(HexUtils.Hex hex, int x, int y) {
        GL11.glPushMatrix();
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        UtilsFX.bindTexture("textures/gui/hex2.png");
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        HexUtils.Pixel pix = hex.toPixel(9);
        GL11.glTranslated((double)((double)x + pix.x), (double)((double)y + pix.y), (double)0.0);
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78369_a(1.0f, 1.0f, 1.0f, 1.0f);
        tessellator.func_78374_a(-8.0, 8.0, (double)this.field_73735_i, 0.0, 1.0);
        tessellator.func_78374_a(8.0, 8.0, (double)this.field_73735_i, 1.0, 1.0);
        tessellator.func_78374_a(8.0, -8.0, (double)this.field_73735_i, 1.0, 0.0);
        tessellator.func_78374_a(-8.0, -8.0, (double)this.field_73735_i, 0.0, 0.0);
        tessellator.func_78381_a();
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glPopMatrix();
    }

    private void drawLine(double x, double y, double x2, double y2) {
        int count = FMLClientHandler.instance().getClient().field_71439_g.field_70173_aa;
        float alpha = 0.3f + MathHelper.func_76126_a((float)((float)((double)count + x))) * 0.3f + 0.3f;
        Tessellator var12 = Tessellator.field_78398_a;
        GL11.glPushMatrix();
        GL11.glLineWidth((float)3.0f);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)1);
        var12.func_78371_b(3);
        var12.func_78369_a(0.0f, 0.6f, 0.8f, alpha);
        var12.func_78377_a(x, y, 0.0);
        var12.func_78377_a(x2, y2, 0.0);
        var12.func_78381_a();
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)32826);
        GL11.glEnable((int)3553);
        GL11.glPopMatrix();
    }

    private void drawSheet(int x, int y, int mx, int my) {
        HexUtils.Hex hp;
        this.note = ResearchManager.getData(this.tileEntity.func_70301_a(1));
        if (this.note == null || this.note.key == null || this.note.key.length() == 0) {
            this.runes.clear();
            return;
        }
        UtilsFX.bindTexture("textures/misc/parchment3.png");
        this.func_73729_b(x + 94, y + 8, 0, 0, 150, 150);
        long time = System.currentTimeMillis();
        if (this.lastRuneCheck < time) {
            int l;
            this.lastRuneCheck = time + 250L;
            int k = this.field_146297_k.field_71441_e.field_73012_v.nextInt(120) - 60;
            hp = new HexUtils.Pixel(k, l = this.field_146297_k.field_71441_e.field_73012_v.nextInt(120) - 60).toHex(9);
            if (!this.runes.containsKey(hp.toString()) && !this.note.hexes.containsKey(hp.toString())) {
                this.runes.put(hp.toString(), new Rune(hp.q, hp.r, time, this.lastRuneCheck + 15000L + (long)this.field_146297_k.field_71441_e.field_73012_v.nextInt(10000), this.field_146297_k.field_71441_e.field_73012_v.nextInt(16)));
            }
        }
        if (this.runes.size() > 0) {
            Rune[] rns = this.runes.values().toArray(new Rune[0]);
            for (int a = 0; a < rns.length; ++a) {
                Rune rune = rns[a];
                if (rune.decay < time) {
                    this.runes.remove(rune.q + ":" + rune.r);
                    continue;
                }
                HexUtils.Pixel pix = new HexUtils.Hex(rune.q, rune.r).toPixel(9);
                float progress = (float)(time - rune.start) / (float)(rune.decay - rune.start);
                float alpha = 0.5f;
                if (progress < 0.25f) {
                    alpha = progress * 2.0f;
                } else if (progress > 0.5f) {
                    alpha = 1.0f - progress;
                }
                this.drawRune((double)(x + 169) + pix.x, (double)(y + 83) + pix.y, rune.rune, alpha * 0.66f);
            }
        }
        int mouseX = mx - (x + 169);
        int mouseY = my - (y + 83);
        hp = new HexUtils.Pixel(mouseX, mouseY).toHex(9);
        this.lines.clear();
        this.checked.clear();
        this.highlight.clear();
        for (HexUtils.Hex hex : this.note.hexes.values()) {
            if (this.note.hexEntries.get((Object)hex.toString()).type != 1 || !Thaumcraft.proxy.getPlayerKnowledge().hasDiscoveredAspect(this.username, this.note.hexEntries.get((Object)hex.toString()).aspect)) continue;
            this.checkConnections(hex);
        }
        for (HexUtils.Hex[] con : this.lines.values()) {
            HexUtils.Pixel p1 = con[0].toPixel(9);
            HexUtils.Pixel p2 = con[1].toPixel(9);
            this.drawLine((double)(x + 169) + p1.x, (double)(y + 83) + p1.y, (double)(x + 169) + p2.x, (double)(y + 83) + p2.y);
        }
        UtilsFX.bindTexture("textures/gui/hex1.png");
        GL11.glPushMatrix();
        if (!this.note.isComplete()) {
            for (HexUtils.Hex hex : this.note.hexes.values()) {
                if (this.note.hexEntries.get((Object)hex.toString()).type != 1) {
                    if (this.note.isComplete()) continue;
                    if (hex.equals(hp)) {
                        this.drawHexHighlight(hex, x + 169, y + 83);
                    }
                    this.drawHex(hex, x + 169, y + 83);
                    continue;
                }
                this.drawOrb((double)(x + 161) + hex.toPixel((int)9).x, (double)(y + 75) + hex.toPixel((int)9).y);
            }
        }
        for (HexUtils.Hex hex : this.note.hexes.values()) {
            HexUtils.Pixel pix;
            if (this.note.hexEntries.get((Object)hex.toString()).aspect != null && !Thaumcraft.proxy.getPlayerKnowledge().hasDiscoveredAspect(this.username, this.note.hexEntries.get((Object)hex.toString()).aspect)) {
                pix = hex.toPixel(9);
                UtilsFX.bindTexture("textures/aspects/_unknown.png");
                GL11.glPushMatrix();
                GL11.glColor4f((float)0.0f, (float)0.0f, (float)0.0f, (float)0.5f);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                GL11.glTranslated((double)((double)(x + 161) + pix.x), (double)((double)(y + 75) + pix.y), (double)0.0);
                UtilsFX.drawTexturedQuadFull(0, 0, this.field_73735_i);
                GL11.glDisable((int)3042);
                GL11.glPopMatrix();
                continue;
            }
            if (this.note.hexEntries.get((Object)hex.toString()).type == 1 || this.highlight.contains(hex.toString())) {
                pix = hex.toPixel(9);
                UtilsFX.drawTag((double)(x + 161) + pix.x, (double)(y + 75) + pix.y, this.note.hexEntries.get((Object)hex.toString()).aspect, 0.0f, 0, (double)this.field_73735_i, 771, 1.0f, false);
                continue;
            }
            if (this.note.hexEntries.get((Object)hex.toString()).type != 2) continue;
            pix = hex.toPixel(9);
            UtilsFX.drawTag((double)(x + 161) + pix.x, (double)(y + 75) + pix.y, this.note.hexEntries.get((Object)hex.toString()).aspect, 0.0f, 0, (double)this.field_73735_i, 771, 0.66f, true);
        }
        GL11.glPopMatrix();
    }

    private void checkConnections(HexUtils.Hex hex) {
        this.checked.add(hex.toString());
        for (int a = 0; a < 6; ++a) {
            HexUtils.Hex target = hex.getNeighbour(a);
            if (this.checked.contains(target.toString()) || !this.note.hexEntries.containsKey(target.toString()) || this.note.hexEntries.get((Object)target.toString()).type < 1) continue;
            Aspect aspect1 = this.note.hexEntries.get((Object)hex.toString()).aspect;
            Aspect aspect2 = this.note.hexEntries.get((Object)target.toString()).aspect;
            if (!Thaumcraft.proxy.getPlayerKnowledge().hasDiscoveredAspect(this.username, aspect1) || !Thaumcraft.proxy.getPlayerKnowledge().hasDiscoveredAspect(this.username, aspect2) || (aspect1.isPrimal() || aspect1.getComponents()[0] != aspect2 && aspect1.getComponents()[1] != aspect2) && (aspect2.isPrimal() || aspect2.getComponents()[0] != aspect1 && aspect2.getComponents()[1] != aspect1)) continue;
            String k1 = hex.toString() + ":" + target.toString();
            String k2 = target.toString() + ":" + hex.toString();
            if (!this.lines.containsKey(k1) && !this.lines.containsKey(k2)) {
                this.lines.put(k1, new HexUtils.Hex[]{hex, target});
                this.highlight.add(target.toString());
            }
            this.checkConnections(target);
        }
    }

    private void drawRune(double x, double y, int rune, float alpha) {
        GL11.glPushMatrix();
        UtilsFX.bindTexture("textures/misc/script.png");
        GL11.glColor4f((float)0.0f, (float)0.0f, (float)0.0f, (float)alpha);
        GL11.glTranslated((double)x, (double)y, (double)0.0);
        if (rune < 16) {
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)-1.0f);
        }
        Tessellator tessellator = Tessellator.field_78398_a;
        float var8 = 0.0625f * (float)rune;
        float var9 = var8 + 0.0625f;
        float var10 = 0.0f;
        float var11 = 1.0f;
        tessellator.func_78382_b();
        tessellator.func_78369_a(0.0f, 0.0f, 0.0f, alpha);
        tessellator.func_78374_a(-5.0, 5.0, (double)this.field_73735_i, (double)var9, (double)var11);
        tessellator.func_78374_a(5.0, 5.0, (double)this.field_73735_i, (double)var9, (double)var10);
        tessellator.func_78374_a(5.0, -5.0, (double)this.field_73735_i, (double)var8, (double)var10);
        tessellator.func_78374_a(-5.0, -5.0, (double)this.field_73735_i, (double)var8, (double)var11);
        tessellator.func_78381_a();
        GL11.glPopMatrix();
    }

    protected void func_73864_a(int mx, int my, int par3) {
        AspectList aspects;
        Aspect aspect;
        super.func_73864_a(mx, my, par3);
        if (this.butcount1 > System.nanoTime() || this.butcount2 > System.nanoTime()) {
            return;
        }
        int gx = (this.field_146294_l - this.field_146999_f) / 2;
        int gy = (this.field_146295_m - this.field_147000_g) / 2;
        int var7 = mx - (gx + 35);
        int var8 = my - (gy + 139);
        if (var7 >= 0 && var8 >= 0 && var7 < 32 && var8 < 16 && this.butcount2 < System.nanoTime() && this.select1 != null && this.select2 != null) {
            this.butcount2 = System.nanoTime() + 200000000L;
            this.playButtonClick();
            this.playButtonCombine();
            PacketHandler.INSTANCE.sendToServer((IMessage)new PacketAspectCombinationToServer(this.player, this.tileEntity.field_145851_c, this.tileEntity.field_145848_d, this.tileEntity.field_145849_e, this.select1, this.select2, this.tileEntity.bonusAspects.getAmount(this.select1) > 0, this.tileEntity.bonusAspects.getAmount(this.select2) > 0, true));
            return;
        }
        var7 = mx - (gx + 27);
        var8 = my - (gy + 121);
        if (this.page > 0 && var7 >= 0 && var8 >= 0 && var7 < 24 && var8 < 8) {
            --this.page;
            this.playButtonScroll();
            return;
        }
        var7 = mx - (gx + 51);
        var8 = my - (gy + 121);
        if (this.page < this.lastPage && var7 >= 0 && var8 >= 0 && var7 < 24 && var8 < 8) {
            ++this.page;
            this.playButtonScroll();
            return;
        }
        if (this.select1 != null) {
            var7 = mx - (gx + 11);
            var8 = my - (gy + 137);
            if (var7 >= 0 && var8 >= 0 && var7 < 16 && var8 < 16) {
                this.select1 = null;
                this.playButtonAspect();
                return;
            }
        }
        if (this.select2 != null) {
            var7 = mx - (gx + 71);
            var8 = my - (gy + 137);
            if (var7 >= 0 && var8 >= 0 && var7 < 16 && var8 < 16) {
                this.select2 = null;
                this.playButtonAspect();
                return;
            }
        }
        if (this.note != null) {
            this.checkClickedHex(mx, my, gx, gy);
            if (RESEARCHDUPE && this.note.isComplete()) {
                var7 = mx - (gx + 37);
                var8 = my - (gy + 5);
                if (var7 >= 0 && var8 >= 0 && var7 < 24 && var8 < 24) {
                    this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 5);
                    this.playButtonClick();
                    return;
                }
            }
        }
        if (!(!this.func_146272_n() || !RESEARCHER_2 || (aspect = this.getClickedAspect(mx, my, gx, gy, true)) == null || aspect.isPrimal() || (aspects = Thaumcraft.proxy.getPlayerKnowledge().getAspectsDiscovered(this.username)) == null || aspects.getAmount(aspect.getComponents()[0]) <= 0 && this.tileEntity.bonusAspects.getAmount(aspect.getComponents()[0]) <= 0 || aspects.getAmount(aspect.getComponents()[1]) <= 0 && this.tileEntity.bonusAspects.getAmount(aspect.getComponents()[1]) <= 0)) {
            this.draggedAspect = null;
            this.playButtonCombine();
            PacketHandler.INSTANCE.sendToServer((IMessage)new PacketAspectCombinationToServer(this.player, this.tileEntity.field_145851_c, this.tileEntity.field_145848_d, this.tileEntity.field_145849_e, aspect.getComponents()[0], aspect.getComponents()[1], this.tileEntity.bonusAspects.getAmount(aspect.getComponents()[0]) > 0, this.tileEntity.bonusAspects.getAmount(aspect.getComponents()[1]) > 0, true));
        }
    }

    private void checkClickedHex(int mx, int my, int gx, int gy) {
        int mouseX = mx - (gx + 169);
        int mouseY = my - (gy + 83);
        HexUtils.Hex hp = new HexUtils.Pixel(mouseX, mouseY).toHex(9);
        if (this.note.hexes.containsKey(hp.toString()) && this.note.hexEntries.get((Object)hp.toString()).type == 2) {
            this.playButtonCombine();
            this.playButtonErase();
            PacketHandler.INSTANCE.sendToServer((IMessage)new PacketAspectPlaceToServer(this.player, (byte)hp.q, (byte)hp.r, this.tileEntity.field_145851_c, this.tileEntity.field_145848_d, this.tileEntity.field_145849_e, null));
            return;
        }
    }

    private Aspect getClickedAspect(int mx, int my, int gx, int gy, boolean ignoreZero) {
        AspectList aspects = Thaumcraft.proxy.getPlayerKnowledge().getAspectsDiscovered(this.username);
        if (aspects != null) {
            int count = 0;
            int drawn = 0;
            for (Aspect aspect : aspects.getAspectsSorted()) {
                if (++count - 1 < this.page * 5 || drawn >= 25) continue;
                int xx = drawn / 5 * 16;
                int yy = drawn % 5 * 16;
                int var7 = mx - (gx + xx + 10);
                int var8 = my - (gy + yy + 40);
                if ((ignoreZero || aspects.getAmount(aspect) > 0 || this.tileEntity.bonusAspects.getAmount(aspect) > 0) && var7 >= 0 && var8 >= 0 && var7 < 16 && var8 < 16) {
                    return aspect;
                }
                ++drawn;
            }
        }
        return null;
    }

    private void playButtonClick() {
        this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:cameraclack", 0.4f, 1.0f, false);
    }

    private void playButtonAspect() {
        this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:hhoff", 0.2f, 1.0f + this.field_146297_k.field_71451_h.field_70170_p.field_73012_v.nextFloat() * 0.1f, false);
    }

    private void playButtonCombine() {
        this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:hhon", 0.3f, 1.0f, false);
    }

    private void playButtonWrite() {
        this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:write", 0.2f, 1.0f, false);
    }

    private void playButtonErase() {
        this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:erase", 0.2f, 1.0f + this.field_146297_k.field_71451_h.field_70170_p.field_73012_v.nextFloat() * 0.1f, false);
    }

    private void playButtonScroll() {
        this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:key", 0.3f, 1.0f, false);
    }

    private void drawOrb(double x, double y) {
        int count = FMLClientHandler.instance().getClient().field_71439_g.field_70173_aa;
        float red = 0.7f + MathHelper.func_76126_a((float)((float)(((double)count + x) / 10.0))) * 0.15f + 0.15f;
        float green = 0.7f + MathHelper.func_76126_a((float)((float)(((double)count + x + y) / 11.0))) * 0.15f + 0.15f;
        float blue = 0.7f + MathHelper.func_76126_a((float)((float)(((double)count + y) / 12.0))) * 0.15f + 0.15f;
        Color c = new Color(red, green, blue);
        this.drawOrb(x, y, c.getRGB());
    }

    private void drawOrb(double x, double y, int color) {
        int count = FMLClientHandler.instance().getClient().field_71439_g.field_70173_aa;
        Color c = new Color(color);
        float red = (float)c.getRed() / 255.0f;
        float green = (float)c.getGreen() / 255.0f;
        float blue = (float)c.getBlue() / 255.0f;
        if (Config.colorBlind) {
            red /= 1.8f;
            green /= 1.8f;
            blue /= 1.8f;
        }
        GL11.glPushMatrix();
        UtilsFX.bindTexture(ParticleEngine.particleTexture);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslated((double)x, (double)y, (double)0.0);
        Tessellator tessellator = Tessellator.field_78398_a;
        int part = count % 8;
        float var8 = 0.5f + (float)part / 8.0f;
        float var9 = var8 + 0.0624375f;
        float var10 = 0.5f;
        float var11 = var10 + 0.0624375f;
        tessellator.func_78382_b();
        tessellator.func_78380_c(240);
        tessellator.func_78369_a(red, green, blue, 1.0f);
        tessellator.func_78374_a(0.0, 16.0, (double)this.field_73735_i, (double)var9, (double)var11);
        tessellator.func_78374_a(16.0, 16.0, (double)this.field_73735_i, (double)var9, (double)var10);
        tessellator.func_78374_a(16.0, 0.0, (double)this.field_73735_i, (double)var8, (double)var10);
        tessellator.func_78374_a(0.0, 0.0, (double)this.field_73735_i, (double)var8, (double)var11);
        tessellator.func_78381_a();
        GL11.glPopMatrix();
    }

    class Coord2D {
        int x;
        int y;

        Coord2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private class Rune {
        int q;
        int r;
        long start;
        long decay;
        int rune;

        public Rune(int q, int r, long start, long decay, int rune) {
            this.q = q;
            this.r = r;
            this.start = start;
            this.decay = decay;
            this.rune = rune;
        }
    }
}

