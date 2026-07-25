/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.container.ContainerFocalManipulator;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.tiles.TileFocalManipulator;

@SideOnly(value=Side.CLIENT)
public class GuiFocalManipulator
extends GuiContainer {
    private TileFocalManipulator table;
    private float xSize_lo;
    private float ySize_lo;
    int selected = -1;
    int rank = 0;
    long time;
    long nextSparkle = 0L;
    DecimalFormat myFormatter = new DecimalFormat("#######.#");
    ArrayList<FocusUpgradeType> possibleUpgrades = new ArrayList();
    ArrayList<FocusUpgradeType> upgrades = new ArrayList();
    AspectList aspects = new AspectList();
    HashMap<Long, Sparkle> sparkles = new HashMap();

    public GuiFocalManipulator(InventoryPlayer par1InventoryPlayer, TileFocalManipulator table) {
        super((Container)new ContainerFocalManipulator(par1InventoryPlayer, table));
        this.table = table;
        this.field_146999_f = 192;
        this.field_147000_g = 233;
        if (table.size > 0) {
            this.gatherInfo();
            this.selected = table.upgrade;
        }
    }

    public void func_73863_a(int par1, int par2, float par3) {
        ArrayList<String> list;
        FocusUpgradeType u;
        int a;
        super.func_73863_a(par1, par2, par3);
        this.xSize_lo = par1;
        this.ySize_lo = par2;
        int baseX = this.field_147003_i;
        int baseY = this.field_147009_r;
        int mposx = 0;
        int mposy = 0;
        if (this.rank > 0) {
            for (a = 0; a < this.possibleUpgrades.size(); ++a) {
                mposx = par1 - (baseX + 48 + a * 16);
                mposy = par2 - (baseY + 104);
                if (mposx < 0 || mposy < 0 || mposx >= 16 || mposy >= 16) continue;
                u = this.possibleUpgrades.get(a);
                list = new ArrayList<String>();
                list.add(EnumChatFormatting.DARK_PURPLE + "" + EnumChatFormatting.UNDERLINE + u.getLocalizedName());
                list.add(u.getLocalizedText());
                this.drawHoveringTextFixed(list, baseX + this.field_146999_f - 36, baseY + 24, this.field_146289_q, this.field_146294_l - (baseX + this.field_146999_f - 16));
            }
        }
        if (this.selected >= 0) {
            mposx = par1 - (baseX + 48);
            mposy = par2 - (baseY + 48);
            if (mposx >= 0 && mposy >= 0 && mposx < 36 && mposy < 36) {
                ArrayList<String> list2 = new ArrayList<String>();
                list2.add(StatCollector.func_74838_a((String)"wandtable.text1"));
                this.drawHoveringText(list2, par1, par2, this.field_146289_q);
            }
            mposx = par1 - (baseX + 108);
            mposy = par2 - (baseY + 58);
            if (mposx >= 0 && mposy >= 0 && mposx < 36 && mposy < 16) {
                ArrayList<String> list3 = new ArrayList<String>();
                list3.add(StatCollector.func_74838_a((String)"wandtable.text2"));
                this.drawHoveringText(list3, par1, par2, this.field_146289_q);
            }
            if (this.table.size == 0 && this.rank * 8 <= this.field_146297_k.field_71439_g.field_71068_ca) {
                mposx = par1 - (baseX + 48);
                mposy = par2 - (baseY + 88);
                if (mposx >= 0 && mposy >= 0 && mposx < 96 && mposy < 8) {
                    ArrayList<String> list4 = new ArrayList<String>();
                    list4.add(StatCollector.func_74838_a((String)"wandtable.text3"));
                    this.drawHoveringText(list4, par1, par2, this.field_146289_q);
                }
            }
        }
        for (a = 0; a < this.upgrades.size(); ++a) {
            mposx = par1 - (baseX + 56 + a * 16);
            mposy = par2 - (baseY + 32);
            if (mposx < 0 || mposy < 0 || mposx >= 16 || mposy >= 16) continue;
            u = this.upgrades.get(a);
            list = new ArrayList();
            list.add(EnumChatFormatting.DARK_PURPLE + "" + EnumChatFormatting.UNDERLINE + u.getLocalizedName());
            list.add(u.getLocalizedText());
            this.drawHoveringTextFixed(list, baseX + this.field_146999_f - 36, baseY + 24, this.field_146289_q, this.field_146294_l - (baseX + this.field_146999_f - 16));
        }
    }

    protected void func_146976_a(float par1, int par2, int par3) {
        int a;
        this.time = System.currentTimeMillis();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        UtilsFX.bindTexture("textures/gui/gui_wandtable.png");
        int k = (this.field_146294_l - this.field_146999_f) / 2;
        int l = (this.field_146295_m - this.field_147000_g) / 2;
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        this.func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
        if (this.table.func_70301_a(0) == null || this.table.rank < 0 || this.table.reset) {
            this.rank = 0;
            this.selected = -1;
            this.possibleUpgrades.clear();
            this.upgrades.clear();
            this.aspects = new AspectList();
            this.table.reset = false;
            this.table.rank = 0;
        }
        if (this.rank > 0) {
            for (a = 0; a < this.possibleUpgrades.size(); ++a) {
                FocusUpgradeType u = this.possibleUpgrades.get(a);
                if (this.selected != u.id) continue;
                this.func_73729_b(k + 48 + a * 16, l + 104, 200, 0, 16, 16);
            }
        }
        if (this.rank > 0 && this.selected >= 0 && this.table.func_70301_a(0) != null) {
            int xp = this.rank * 8;
            if (this.table.size == 0 && xp <= this.field_146297_k.field_71439_g.field_71068_ca) {
                this.func_73729_b(k + 48, l + 88, 8, 240, 96, 8);
            }
            this.func_73729_b(k + 108, l + 59, 200, 16, 16, 16);
            int start = 0;
            if (this.table.aspects.size() > 0) {
                for (Aspect aspect : this.table.aspects.getAspectsSorted()) {
                    if (aspect == null || this.table.aspects.getAmount(aspect) == 0) continue;
                    int size = (int)((float)this.table.aspects.getAmount(aspect) / (float)this.table.size * 96.0f);
                    Color c = new Color(aspect.getColor());
                    GL11.glColor4f((float)((float)c.getRed() / 255.0f), (float)((float)c.getGreen() / 255.0f), (float)((float)c.getBlue() / 255.0f), (float)0.9f);
                    this.func_73729_b(k + 48 + start, l + 88, 112 + start, 240, size, 8);
                    start += size;
                    if (this.table.func_145831_w().field_73012_v.nextInt(66) != 0) continue;
                    float x = 48 + start;
                    float y = 92.0f;
                    float xx = ((float)(46 + this.rank * 16) - x) / 9.0f;
                    float yy = (38.0f - y) / 9.0f;
                    this.sparkles.put(this.time, new Sparkle(x, y, xx, yy, (float)c.getRed() / 255.0f, (float)c.getGreen() / 255.0f, (float)c.getBlue() / 255.0f));
                }
            }
            this.field_146289_q.func_78261_a("" + xp, k + 125, l + 64, xp > this.field_146297_k.field_71439_g.field_71068_ca ? 16151160 : 10092429);
            AspectList al = this.aspects;
            if (this.table.size > 0) {
                al = this.table.aspects;
            }
            int q = 0;
            for (Aspect a2 : al.getAspectsSorted()) {
                if (a2 == null) continue;
                GL11.glPushMatrix();
                GL11.glTranslated((double)(k + 49), (double)((double)(l + 68) - (double)al.size() * 2.5), (double)0.0);
                GL11.glScaled((double)0.5, (double)0.5, (double)0.5);
                this.field_146289_q.func_78261_a(a2.getName(), 0, q * 10, a2.getColor());
                String s = this.myFormatter.format((float)al.getAmount(a2) / 100.0f);
                this.field_146289_q.func_78261_a(s, 48, q * 10, a2.getColor());
                GL11.glPopMatrix();
                ++q;
            }
        }
        if (this.rank > 0) {
            if (this.nextSparkle < this.time) {
                this.nextSparkle = this.time + (long)(this.table.size > 0 ? 10 : 500) + (long)this.table.func_145831_w().field_73012_v.nextInt(200);
                this.sparkles.put(this.time, new Sparkle(42 + this.rank * 16 + this.table.func_145831_w().field_73012_v.nextInt(12), 34 + this.table.func_145831_w().field_73012_v.nextInt(12), 0.0f, 0.0f, 0.5f + this.table.func_145831_w().field_73012_v.nextFloat() * 0.4f, 1.0f - this.table.func_145831_w().field_73012_v.nextFloat() * 0.4f, 1.0f - this.table.func_145831_w().field_73012_v.nextFloat() * 0.4f));
            }
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            for (a = 0; a < this.possibleUpgrades.size(); ++a) {
                FocusUpgradeType u = this.possibleUpgrades.get(a);
                GL11.glPushMatrix();
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                this.field_146297_k.field_71446_o.func_110577_a(u.icon);
                UtilsFX.drawTexturedQuadFull(k + 48 + a * 16, l + 104, this.field_73735_i);
                GL11.glPopMatrix();
            }
        } else if (this.rank == 0 && this.table.func_70301_a(0) != null) {
            try {
                this.gatherInfo();
            }
            catch (Exception e) {
                // empty catch block
            }
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        for (int a3 = 0; a3 < this.upgrades.size(); ++a3) {
            FocusUpgradeType u = this.upgrades.get(a3);
            GL11.glPushMatrix();
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            this.field_146297_k.field_71446_o.func_110577_a(u.icon);
            UtilsFX.drawTexturedQuadFull(k + 56 + a3 * 16, l + 32, this.field_73735_i);
            GL11.glPopMatrix();
        }
        GL11.glDisable((int)3042);
    }

    private void gatherInfo() {
        this.possibleUpgrades.clear();
        this.upgrades.clear();
        this.aspects = new AspectList();
        ItemFocusBasic focus = (ItemFocusBasic)this.table.func_70301_a(0).func_77973_b();
        short[] s = focus.getAppliedUpgrades(this.table.func_70301_a(0));
        this.rank = 1;
        int fu = 0;
        while (this.rank <= 5 && s[this.rank - 1] != -1) {
            this.upgrades.add(FocusUpgradeType.types[s[this.rank - 1]]);
            ++fu;
            ++this.rank;
        }
        if (fu == 5) {
            this.rank = -1;
        } else {
            FocusUpgradeType[] ut = focus.getPossibleUpgradesByRank(this.table.func_70301_a(0), this.rank);
            if (ut == null) {
                return;
            }
            for (int a = 0; a < ut.length; ++a) {
                if (!focus.canApplyUpgrade(this.table.func_70301_a(0), (EntityPlayer)Minecraft.func_71410_x().field_71439_g, ut[a], this.rank)) continue;
                this.possibleUpgrades.add(ut[a]);
            }
        }
        if (this.table.size > 0) {
            this.selected = this.table.upgrade;
        }
    }

    protected void func_146979_b(int p_146979_1_, int p_146979_2_) {
        Long[] keys;
        UtilsFX.bindTexture(ParticleEngine.particleTexture);
        for (Long key : keys = this.sparkles.keySet().toArray(new Long[0])) {
            Sparkle s = this.sparkles.get(key);
            this.drawSparkle(s.x, s.y, s.frame, s.r, s.g, s.b);
            if (s.nextframe < this.time) {
                ++s.frame;
                s.nextframe = this.time + 50L;
                s.x += s.mx;
                s.y += s.my;
            }
            if (s.frame == 9) {
                this.sparkles.remove(key);
                continue;
            }
            this.sparkles.put(key, s);
        }
    }

    protected void func_73864_a(int mx, int my, int par3) {
        super.func_73864_a(mx, my, par3);
        int gx = (this.field_146294_l - this.field_146999_f) / 2;
        int gy = (this.field_146295_m - this.field_147000_g) / 2;
        int var7 = mx - (gx + 48);
        int var8 = my - (gy + 88);
        if (this.table.size == 0 && this.selected >= 0 && this.rank * 8 <= this.field_146297_k.field_71439_g.field_71068_ca && var7 >= 0 && var8 >= 0 && var7 < 96 && var8 < 8) {
            this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, this.selected);
            this.playButtonClick();
            return;
        }
        if (this.table.size == 0) {
            for (int a = 0; a < this.possibleUpgrades.size(); ++a) {
                FocusUpgradeType u = this.possibleUpgrades.get(a);
                var7 = mx - (gx + 48 + a * 16);
                var8 = my - (gy + 104);
                if (var7 < 0 || var8 < 0 || var7 >= 16 || var8 >= 16) continue;
                this.aspects = new AspectList();
                if (this.selected == u.id) {
                    this.selected = -1;
                } else {
                    this.selected = u.id;
                    int amt = 200;
                    for (int q = 1; q < this.rank; ++q) {
                        amt *= 2;
                    }
                    AspectList tal = new AspectList();
                    for (Aspect as : FocusUpgradeType.types[this.selected].aspects.getAspects()) {
                        tal.add(as, amt);
                    }
                    this.aspects = ResearchManager.reduceToPrimals(tal);
                }
                this.playButtonClick();
                return;
            }
        }
    }

    private void playButtonClick() {
        this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:cameraclack", 0.4f, 1.0f, false);
    }

    private void drawSparkle(double x, double y, int frame, float r, float g, float b) {
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        GL11.glColor4f((float)r, (float)g, (float)b, (float)0.9f);
        GL11.glTranslated((double)x, (double)y, (double)200.0);
        Tessellator tessellator = Tessellator.field_78398_a;
        float var8 = (float)frame / 16.0f;
        float var9 = var8 + 0.0624375f;
        float var10 = 0.4375f;
        float var11 = var10 + 0.0624375f;
        tessellator.func_78382_b();
        tessellator.func_78380_c(220);
        tessellator.func_78369_a(r, g, b, 0.9f);
        tessellator.func_78374_a(-4.0, 4.0, (double)this.field_73735_i, (double)var9, (double)var11);
        tessellator.func_78374_a(4.0, 4.0, (double)this.field_73735_i, (double)var9, (double)var10);
        tessellator.func_78374_a(4.0, -4.0, (double)this.field_73735_i, (double)var8, (double)var10);
        tessellator.func_78374_a(-4.0, -4.0, (double)this.field_73735_i, (double)var8, (double)var11);
        tessellator.func_78381_a();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    protected void drawHoveringTextFixed(List listin, int x, int y, FontRenderer font, int width) {
        if (!listin.isEmpty()) {
            ArrayList<String> list = new ArrayList<String>();
            for (Object o : listin) {
                String s = (String)o;
                s = this.trimStringNewline(s);
                List list2 = font.func_78271_c(s, width);
                for (String s1 : list2) {
                    list.add(s1);
                }
            }
            GL11.glDisable((int)32826);
            RenderHelper.func_74518_a();
            GL11.glDisable((int)2896);
            GL11.glDisable((int)2929);
            int k = 0;
            for (String s : list) {
                int l = font.func_78256_a(s);
                if (l <= k) continue;
                k = l;
            }
            int j2 = x + 12;
            int k2 = y - 12;
            int i1 = 8;
            if (list.size() > 1) {
                i1 += 2 + (list.size() - 1) * 10;
            }
            this.field_73735_i = 300.0f;
            GuiFocalManipulator.field_146296_j.field_77023_b = 300.0f;
            int j1 = -267386864;
            this.func_73733_a(j2 - 3, k2 - 4, j2 + k + 3, k2 - 3, j1, j1);
            this.func_73733_a(j2 - 3, k2 + i1 + 3, j2 + k + 3, k2 + i1 + 4, j1, j1);
            this.func_73733_a(j2 - 3, k2 - 3, j2 + k + 3, k2 + i1 + 3, j1, j1);
            this.func_73733_a(j2 - 4, k2 - 3, j2 - 3, k2 + i1 + 3, j1, j1);
            this.func_73733_a(j2 + k + 3, k2 - 3, j2 + k + 4, k2 + i1 + 3, j1, j1);
            int k1 = 0x505000FF;
            int l1 = (k1 & 0xFEFEFE) >> 1 | k1 & 0xFF000000;
            this.func_73733_a(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + i1 + 3 - 1, k1, l1);
            this.func_73733_a(j2 + k + 2, k2 - 3 + 1, j2 + k + 3, k2 + i1 + 3 - 1, k1, l1);
            this.func_73733_a(j2 - 3, k2 - 3, j2 + k + 3, k2 - 3 + 1, k1, k1);
            this.func_73733_a(j2 - 3, k2 + i1 + 2, j2 + k + 3, k2 + i1 + 3, l1, l1);
            for (int i2 = 0; i2 < list.size(); ++i2) {
                String s1 = (String)list.get(i2);
                font.func_78261_a(s1, j2, k2, -1);
                if (i2 == 0) {
                    k2 += 2;
                }
                k2 += 10;
            }
            this.field_73735_i = 0.0f;
            GuiFocalManipulator.field_146296_j.field_77023_b = 0.0f;
            GL11.glEnable((int)2896);
            GL11.glEnable((int)2929);
            RenderHelper.func_74519_b();
            GL11.glEnable((int)32826);
        }
    }

    private String trimStringNewline(String p_78273_1_) {
        while (p_78273_1_ != null && p_78273_1_.endsWith("\n")) {
            p_78273_1_ = p_78273_1_.substring(0, p_78273_1_.length() - 1);
        }
        return p_78273_1_;
    }

    private class Sparkle {
        float x;
        float y;
        float mx;
        float my;
        float r;
        float g;
        float b;
        long nextframe;
        int frame;

        public Sparkle(float x, float y, float mx, float my, float r, float g, float b) {
            this.x = x;
            this.y = y;
            this.mx = mx;
            this.my = my;
            this.frame = 0;
            this.r = r;
            this.g = g;
            this.b = b;
            this.nextframe = System.currentTimeMillis() + 50L;
        }
    }
}

