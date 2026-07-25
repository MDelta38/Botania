/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.ObfuscationReflectionHelper
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.FurnaceRecipes
 *  net.minecraft.item.crafting.ShapedRecipes
 *  net.minecraft.item.crafting.ShapelessRecipes
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.oredict.ShapedOreRecipe
 *  net.minecraftforge.oredict.ShapelessOreRecipe
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.gui;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.client.gui.GuiResearchBrowser;
import thaumcraft.client.lib.TCFontRenderer;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
import thaumcraft.common.lib.utils.InventoryUtils;

@SideOnly(value=Side.CLIENT)
public class GuiResearchRecipe
extends GuiScreen {
    protected static RenderItem itemRenderer = new RenderItem();
    public static LinkedList<Object[]> history = new LinkedList();
    protected int paneWidth = 256;
    protected int paneHeight = 181;
    protected double guiMapX;
    protected double guiMapY;
    protected int mouseX = 0;
    protected int mouseY = 0;
    private GuiButton button;
    private ResearchItem research;
    private ResearchPage[] pages = null;
    private int page = 0;
    private int maxPages = 0;
    TCFontRenderer fr = null;
    HashMap<Aspect, ArrayList<ItemStack>> aspectItems = new HashMap();
    public static ConcurrentHashMap<Integer, ItemStack> cache = new ConcurrentHashMap();
    String tex1 = "textures/gui/gui_researchbook.png";
    String tex2 = "textures/gui/gui_researchbook_overlay.png";
    private Object[] tooltip = null;
    private long lastCycle = 0L;
    ArrayList<List> reference = new ArrayList();
    private int cycle = -1;

    public static synchronized void putToCache(int key, ItemStack stack) {
        cache.put(key, stack);
    }

    public static synchronized ItemStack getFromCache(int key) {
        return cache.get(key);
    }

    public GuiResearchRecipe(ResearchItem research, int page, double x, double y) {
        this.research = research;
        this.guiMapX = x;
        this.guiMapY = y;
        this.field_146297_k = Minecraft.func_71410_x();
        this.pages = research.getPages();
        List<ResearchPage> p1 = Arrays.asList(this.pages);
        ArrayList<ResearchPage> p2 = new ArrayList<ResearchPage>();
        for (ResearchPage pp : p1) {
            if (pp != null && pp.type == ResearchPage.PageType.TEXT_CONCEALED && !ThaumcraftApiHelper.isResearchComplete(this.field_146297_k.field_71439_g.func_70005_c_(), pp.research)) continue;
            p2.add(pp);
        }
        this.pages = p2.toArray(new ResearchPage[0]);
        if (research.key.equals("ASPECTS")) {
            AspectList aspectsKnownSorted = Thaumcraft.proxy.getPlayerKnowledge().getAspectsDiscovered(Minecraft.func_71410_x().field_71439_g.func_70005_c_());
            List list = Thaumcraft.proxy.getScannedObjects().get(Minecraft.func_71410_x().field_71439_g.func_70005_c_());
            if (list != null && list.size() > 0) {
                for (String s : list) {
                    try {
                        String s2 = s.substring(1);
                        ItemStack is = GuiResearchRecipe.getFromCache(Integer.parseInt(s2));
                        if (is == null) continue;
                        AspectList tags = ThaumcraftCraftingManager.getObjectTags(is);
                        if ((tags = ThaumcraftCraftingManager.getBonusTags(is, tags)) == null || tags.size() <= 0) continue;
                        for (Aspect a : tags.getAspects()) {
                            ArrayList<Object> items = this.aspectItems.get(a);
                            if (items == null) {
                                items = new ArrayList();
                            }
                            ItemStack is2 = is.func_77946_l();
                            is2.field_77994_a = tags.getAmount(a);
                            items.add(is2);
                            this.aspectItems.put(a, items);
                        }
                    }
                    catch (NumberFormatException e) {
                    }
                }
            }
            ArrayList<ResearchPage> tpl = new ArrayList<ResearchPage>();
            for (ResearchPage p : research.getPages()) {
                tpl.add(p);
            }
            AspectList tal = new AspectList();
            if (aspectsKnownSorted != null) {
                int count = 0;
                for (Aspect aspect : aspectsKnownSorted.getAspectsSorted()) {
                    if (count <= 4) {
                        ++count;
                        tal.add(aspect, aspectsKnownSorted.getAmount(aspect));
                    }
                    if (count != 4) continue;
                    count = 0;
                    tpl.add(new ResearchPage(tal.copy()));
                    tal = new AspectList();
                }
                if (count > 0) {
                    tpl.add(new ResearchPage(tal));
                }
            }
            this.pages = tpl.toArray(this.pages);
        }
        this.maxPages = this.pages.length;
        this.fr = new TCFontRenderer(this.field_146297_k.field_71474_y, TCFontRenderer.FONT_NORMAL, this.field_146297_k.field_71446_o, true);
        if (page % 2 == 1) {
            --page;
        }
        this.page = page;
    }

    public void func_73866_w_() {
    }

    protected void func_146284_a(GuiButton par1GuiButton) {
        super.func_146284_a(par1GuiButton);
    }

    protected void func_73869_a(char par1, int par2) {
        if (par2 == this.field_146297_k.field_71474_y.field_151445_Q.func_151463_i() || par2 == 1) {
            history.clear();
            this.field_146297_k.func_147108_a((GuiScreen)new GuiResearchBrowser(this.guiMapX, this.guiMapY));
        } else {
            super.func_73869_a(par1, par2);
        }
    }

    public void func_146281_b() {
        super.func_146281_b();
    }

    public void func_73863_a(int par1, int par2, float par3) {
        this.func_146276_q_();
        this.genResearchBackground(par1, par2, par3);
        int sw = (this.field_146294_l - this.paneWidth) / 2;
        int sh = (this.field_146295_m - this.paneHeight) / 2;
        if (!history.isEmpty()) {
            int mx = par1 - (sw + 118);
            int my = par2 - (sh + 189);
            if (mx >= 0 && my >= 0 && mx < 20 && my < 12) {
                this.field_146289_q.func_78261_a(StatCollector.func_74838_a((String)"recipe.return"), par1, par2, 0xFFFFFF);
            }
        }
    }

    protected void genResearchBackground(int par1, int par2, float par3) {
        int sw = (this.field_146294_l - this.paneWidth) / 2;
        int sh = (this.field_146295_m - this.paneHeight) / 2;
        float var10 = ((float)this.field_146294_l - (float)this.paneWidth * 1.3f) / 2.0f;
        float var11 = ((float)this.field_146295_m - (float)this.paneHeight * 1.3f) / 2.0f;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        UtilsFX.bindTexture(this.tex1);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)var10, (float)var11, (float)0.0f);
        GL11.glEnable((int)3042);
        GL11.glScalef((float)1.3f, (float)1.3f, (float)1.0f);
        this.func_73729_b(0, 0, 0, 0, this.paneWidth, this.paneHeight);
        GL11.glPopMatrix();
        this.reference.clear();
        this.tooltip = null;
        int current = 0;
        for (int a = 0; a < this.pages.length; ++a) {
            if ((current == this.page || current == this.page + 1) && current < this.maxPages) {
                this.drawPage(this.pages[a], current % 2, sw, sh, par1, par2);
            }
            if (++current > this.page + 1) break;
        }
        if (this.tooltip != null) {
            UtilsFX.drawCustomTooltip(this, itemRenderer, this.field_146289_q, (List)this.tooltip[0], (Integer)this.tooltip[1], (Integer)this.tooltip[2], (Integer)this.tooltip[3]);
        }
        UtilsFX.bindTexture(this.tex1);
        int mx1 = par1 - (sw + 261);
        int my1 = par2 - (sh + 189);
        int mx2 = par1 - (sw - 17);
        int my2 = par2 - (sh + 189);
        float bob = MathHelper.func_76126_a((float)((float)this.field_146297_k.field_71439_g.field_70173_aa / 3.0f)) * 0.2f + 0.1f;
        if (!history.isEmpty()) {
            GL11.glEnable((int)3042);
            this.drawTexturedModalRectScaled(sw + 118, sh + 189, 38, 202, 20, 12, bob);
        }
        if (this.page > 0) {
            GL11.glEnable((int)3042);
            this.drawTexturedModalRectScaled(sw - 16, sh + 190, 0, 184, 12, 8, bob);
        }
        if (this.page < this.maxPages - 2) {
            GL11.glEnable((int)3042);
            this.drawTexturedModalRectScaled(sw + 262, sh + 190, 12, 184, 12, 8, bob);
        }
    }

    public void drawCustomTooltip(GuiScreen gui, RenderItem itemRenderer, FontRenderer fr, List var4, int par2, int par3, int subTipColor) {
        this.tooltip = new Object[]{var4, par2, par3, subTipColor};
    }

    private void drawPage(ResearchPage pageParm, int side, int x, int y, int mx, int my) {
        GL11.glPushAttrib((int)1048575);
        if (this.lastCycle < System.currentTimeMillis()) {
            ++this.cycle;
            this.lastCycle = System.currentTimeMillis() + 1000L;
        }
        if (this.page == 0 && side == 0) {
            this.func_73729_b(x + 4, y - 13, 24, 184, 96, 4);
            this.func_73729_b(x + 4, y + 4, 24, 184, 96, 4);
            int offset = this.field_146289_q.func_78256_a(this.research.getName());
            if (offset <= 130) {
                this.field_146289_q.func_78276_b(this.research.getName(), x + 52 - offset / 2, y - 6, 0x303030);
            } else {
                float vv = 130.0f / (float)offset;
                GL11.glPushMatrix();
                GL11.glTranslatef((float)((float)(x + 52) - (float)(offset / 2) * vv), (float)((float)y - 6.0f * vv), (float)0.0f);
                GL11.glScalef((float)vv, (float)vv, (float)vv);
                this.field_146289_q.func_78276_b(this.research.getName(), 0, 0, 0x303030);
                GL11.glPopMatrix();
            }
            y += 25;
        }
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        if (pageParm.type == ResearchPage.PageType.TEXT || pageParm.type == ResearchPage.PageType.TEXT_CONCEALED) {
            this.drawTextPage(side, x, y - 10, pageParm.getTranslatedText());
        } else if (pageParm.type == ResearchPage.PageType.ASPECTS) {
            this.drawAspectPage(side, x - 8, y - 8, mx, my, pageParm.aspects);
        } else if (pageParm.type == ResearchPage.PageType.CRUCIBLE_CRAFTING) {
            this.drawCruciblePage(side, x - 4, y - 8, mx, my, pageParm);
        } else if (pageParm.type == ResearchPage.PageType.NORMAL_CRAFTING) {
            this.drawCraftingPage(side, x - 4, y - 8, mx, my, pageParm);
        } else if (pageParm.type == ResearchPage.PageType.ARCANE_CRAFTING) {
            this.drawArcaneCraftingPage(side, x - 4, y - 8, mx, my, pageParm);
        } else if (pageParm.type == ResearchPage.PageType.COMPOUND_CRAFTING) {
            this.drawCompoundCraftingPage(side, x - 4, y - 8, mx, my, pageParm);
        } else if (pageParm.type == ResearchPage.PageType.INFUSION_CRAFTING) {
            this.drawInfusionPage(side, x - 4, y - 8, mx, my, pageParm);
        } else if (pageParm.type == ResearchPage.PageType.INFUSION_ENCHANTMENT) {
            this.drawInfusionEnchantingPage(side, x - 4, y - 8, mx, my, pageParm);
        } else if (pageParm.type == ResearchPage.PageType.SMELTING) {
            this.drawSmeltingPage(side, x - 4, y - 8, mx, my, pageParm);
        }
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glPopAttrib();
    }

    private void drawCompoundCraftingPage(int side, int x, int y, int mx, int my, ResearchPage page) {
        List r = (List)page.recipe;
        if (r != null) {
            int py;
            int px;
            int k;
            int j;
            AspectList aspects = (AspectList)r.get(0);
            int dx = (Integer)r.get(1);
            int dy = (Integer)r.get(2);
            int dz = (Integer)r.get(3);
            int xoff = 64 - (dx * 16 + dz * 16) / 2;
            int yoff = -dy * 25;
            List items = (List)r.get(4);
            GL11.glPushMatrix();
            int start = side * 152;
            String text = StatCollector.func_74838_a((String)"recipe.type.construct");
            int offset = this.field_146289_q.func_78256_a(text);
            this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y, 0x505050);
            int mposx = mx;
            int mposy = my;
            if (aspects != null && aspects.size() > 0) {
                int count = 0;
                for (Aspect tag : aspects.getAspectsSortedAmount()) {
                    UtilsFX.drawTag(x + start + 14 + 18 * count + (5 - aspects.size()) * 8, y + 182, tag, (float)aspects.getAmount(tag), 0, 0.0, 771, 1.0f, false);
                    ++count;
                }
                count = 0;
                for (Aspect tag : aspects.getAspectsSortedAmount()) {
                    int tx = x + start + 14 + 18 * count + (5 - aspects.size()) * 8;
                    int ty = y + 182;
                    if (mposx >= tx && mposy >= ty && mposx < tx + 16 && mposy < ty + 16) {
                        this.drawCustomTooltip(this, itemRenderer, this.field_146289_q, Arrays.asList(tag.getName(), tag.getLocalizedDescription()), mx, my - 8, 11);
                    }
                    ++count;
                }
            }
            UtilsFX.bindTexture(this.tex2);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            RenderHelper.func_74520_c();
            GL11.glDisable((int)2896);
            if (aspects != null && aspects.size() > 0) {
                GL11.glPushMatrix();
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.4f);
                GL11.glEnable((int)3042);
                GL11.glTranslatef((float)(x + start), (float)(y + 174), (float)0.0f);
                GL11.glScalef((float)2.0f, (float)2.0f, (float)1.0f);
                this.func_73729_b(0, 0, 68, 76, 12, 12);
                GL11.glPopMatrix();
            }
            GL11.glPushMatrix();
            float sz = 0.0f;
            if (dy > 3) {
                sz = (float)(dy - 3) * 0.2f;
                GL11.glTranslatef((float)((float)(x + start) + (float)xoff * (1.0f + sz)), (float)((float)(y + 108) + (float)yoff * (1.0f - sz)), (float)0.0f);
                GL11.glScalef((float)(1.0f - sz), (float)(1.0f - sz), (float)(1.0f - sz));
            } else {
                GL11.glTranslatef((float)(x + start + xoff), (float)(y + 108 + yoff), (float)0.0f);
            }
            GL11.glPushMatrix();
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.5f);
            GL11.glEnable((int)3042);
            GL11.glTranslatef((float)(-8 - xoff), (float)(-119 + Math.max(3 - dx, 3 - dz) * 8 + dx * 4 + dz * 4 + dy * 50), (float)0.0f);
            GL11.glScalef((float)2.0f, (float)2.0f, (float)1.0f);
            this.func_73729_b(0, 0, 0, 72, 64, 44);
            GL11.glPopMatrix();
            int count = 0;
            for (j = 0; j < dy; ++j) {
                for (k = dz - 1; k >= 0; --k) {
                    for (int i = dx - 1; i >= 0; --i) {
                        px = i * 16 + k * 16;
                        py = -i * 8 + k * 8 + j * 50;
                        GL11.glPushMatrix();
                        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                        RenderHelper.func_74520_c();
                        GL11.glEnable((int)2884);
                        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)(60 - j * 10));
                        if (items.get(count) != null) {
                            itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(items.get(count)), px, py);
                            itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(items.get(count)).func_77946_l().func_77979_a(1), px, py);
                        }
                        RenderHelper.func_74518_a();
                        GL11.glPopMatrix();
                        ++count;
                    }
                }
            }
            GL11.glEnable((int)2896);
            GL11.glPopMatrix();
            count = 0;
            for (j = 0; j < dy; ++j) {
                for (k = dz - 1; k >= 0; --k) {
                    for (int i = dx - 1; i >= 0; --i) {
                        px = (int)((float)(x + start) + (float)xoff * (1.0f + sz) + (float)(i * 16) * (1.0f - sz) + (float)(k * 16) * (1.0f - sz));
                        py = (int)((float)(y + 108) + (float)yoff * (1.0f - sz) - (float)(i * 8) * (1.0f - sz) + (float)(k * 8) * (1.0f - sz) + (float)(j * 50) * (1.0f - sz));
                        if (items.get(count) != null && mposx >= px && mposy >= py && (float)mposx < (float)px + 16.0f * (1.0f - sz) && (float)mposy < (float)py + 16.0f * (1.0f - sz)) {
                            List addtext = InventoryUtils.cycleItemStack(items.get(count)).func_82840_a((EntityPlayer)this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x);
                            Object[] ref = this.findRecipeReference(InventoryUtils.cycleItemStack(items.get(count)));
                            if (ref != null && !((String)ref[0]).equals(this.research.key)) {
                                addtext.add("\u00a78\u00a7o" + StatCollector.func_74838_a((String)"recipe.clickthrough"));
                                this.reference.add(Arrays.asList(mx, my, (String)ref[0], (Integer)ref[1]));
                            }
                            this.drawCustomTooltip(this, itemRenderer, this.field_146289_q, addtext, mx, my, 11);
                        }
                        ++count;
                    }
                }
            }
            GL11.glPopMatrix();
        }
    }

    private void drawAspectPage(int side, int x, int y, int mx, int my, AspectList aspects) {
        if (aspects != null && aspects.size() > 0) {
            int ty;
            int tx;
            GL11.glPushMatrix();
            int start = side * 152;
            int mposx = mx;
            int mposy = my;
            int count = 0;
            for (Aspect aspect : aspects.getAspectsSorted()) {
                if (aspect.getImage() != null) {
                    GL11.glPushMatrix();
                    tx = x + start;
                    ty = y + count * 50;
                    if (mposx >= tx && mposy >= ty && mposx < tx + 40 && mposy < ty + 40) {
                        UtilsFX.bindTexture("textures/aspects/_back.png");
                        GL11.glPushMatrix();
                        GL11.glEnable((int)3042);
                        GL11.glBlendFunc((int)770, (int)771);
                        GL11.glTranslated((double)(x + start - 5), (double)(y + count * 50 - 5), (double)0.0);
                        GL11.glScaled((double)2.5, (double)2.5, (double)0.0);
                        UtilsFX.drawTexturedQuadFull(0, 0, this.field_73735_i);
                        GL11.glDisable((int)3042);
                        GL11.glPopMatrix();
                    }
                    GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
                    UtilsFX.drawTag((x + start) / 2, (y + count * 50) / 2, aspect, aspects.getAmount(aspect), 0, this.field_73735_i);
                    GL11.glPopMatrix();
                    String text = aspect.getName();
                    int offset = this.fr.getStringWidth(text) / 2;
                    this.fr.drawString(text, x + start + 16 - offset, y + 33 + count * 50, 0x505050);
                    if (aspect.getComponents() != null) {
                        GL11.glPushMatrix();
                        GL11.glScalef((float)1.5f, (float)1.5f, (float)1.5f);
                        UtilsFX.drawTag((int)((float)(x + start + 54) / 1.5f), (int)((float)(y + 4 + count * 50) / 1.5f), aspect.getComponents()[0], 0.0f, 0, this.field_73735_i);
                        UtilsFX.drawTag((int)((float)(x + start + 96) / 1.5f), (int)((float)(y + 4 + count * 50) / 1.5f), aspect.getComponents()[1], 0.0f, 0, this.field_73735_i);
                        GL11.glPopMatrix();
                        text = aspect.getComponents()[0].getName();
                        offset = this.fr.getStringWidth(text) / 2;
                        this.fr.drawString("\u00a7o" + text, x + start + 16 - offset + 50, y + 30 + count * 50, 0x505050);
                        text = aspect.getComponents()[1].getName();
                        offset = this.fr.getStringWidth(text) / 2;
                        this.fr.drawString("\u00a7o" + text, x + start + 16 - offset + 92, y + 30 + count * 50, 0x505050);
                        this.field_146289_q.func_78276_b("=", x + start + 7 + 32, y + 12 + count * 50, 0x999999);
                        this.field_146289_q.func_78276_b("+", x + start + 4 + 79, y + 12 + count * 50, 0x999999);
                    } else {
                        this.fr.drawString(StatCollector.func_74838_a((String)"tc.aspect.primal"), x + start + 48, y + 12 + count * 50, 0x444444);
                    }
                }
                ++count;
            }
            count = 0;
            for (Aspect aspect : aspects.getAspectsSorted()) {
                ArrayList<ItemStack> items;
                tx = x + start;
                ty = y + count * 50;
                if (mposx >= tx && mposy >= ty && mposx < tx + 40 && mposy < ty + 40 && (items = this.aspectItems.get(aspect)) != null && items.size() > 0) {
                    int xcount = 0;
                    int ycount = 0;
                    for (ItemStack item : items) {
                        GL11.glPushMatrix();
                        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                        RenderHelper.func_74520_c();
                        GL11.glEnable((int)2884);
                        itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(item), mposx + 8 + xcount * 17, 17 * ycount + (mposy - (4 + items.size() / 8 * 8)));
                        itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(item), mposx + 8 + xcount * 17, 17 * ycount + (mposy - (4 + items.size() / 8 * 8)));
                        RenderHelper.func_74518_a();
                        GL11.glPopMatrix();
                        if (++xcount < 8) continue;
                        xcount = 0;
                        ++ycount;
                    }
                    GL11.glEnable((int)2896);
                }
                ++count;
            }
            GL11.glPopMatrix();
        }
    }

    private void drawArcaneCraftingPage(int side, int x, int y, int mx, int my, ResearchPage pageParm) {
        IArcaneRecipe recipe = null;
        Object tr = null;
        if (pageParm.recipe instanceof Object[]) {
            try {
                tr = ((Object[])pageParm.recipe)[this.cycle];
            }
            catch (Exception e) {
                this.cycle = 0;
                tr = ((Object[])pageParm.recipe)[this.cycle];
            }
        } else {
            tr = pageParm.recipe;
        }
        if (tr instanceof ShapedArcaneRecipe) {
            recipe = (ShapedArcaneRecipe)tr;
        } else if (tr instanceof ShapelessArcaneRecipe) {
            recipe = (ShapelessArcaneRecipe)tr;
        }
        if (recipe == null) {
            return;
        }
        GL11.glPushMatrix();
        int start = side * 152;
        UtilsFX.bindTexture(this.tex2);
        GL11.glPushMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3042);
        GL11.glTranslatef((float)(x + start), (float)y, (float)0.0f);
        GL11.glScalef((float)2.0f, (float)2.0f, (float)1.0f);
        this.func_73729_b(2, 27, 112, 15, 52, 52);
        this.func_73729_b(20, 7, 20, 3, 16, 16);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.4f);
        GL11.glEnable((int)3042);
        GL11.glTranslatef((float)(x + start), (float)(y + 164), (float)0.0f);
        GL11.glScalef((float)2.0f, (float)2.0f, (float)1.0f);
        this.func_73729_b(0, 0, 68, 76, 12, 12);
        GL11.glPopMatrix();
        int mposx = mx;
        int mposy = my;
        AspectList tags = recipe.getAspects();
        if (tags != null && tags.size() > 0) {
            int count = 0;
            for (Aspect tag : tags.getAspectsSortedAmount()) {
                UtilsFX.drawTag(x + start + 14 + 18 * count + (5 - tags.size()) * 8, y + 172, tag, tags.getAmount(tag), 0, 0.0, 771, 1.0f);
                ++count;
            }
            count = 0;
            for (Aspect tag : tags.getAspectsSortedAmount()) {
                int tx = x + start + 14 + 18 * count + (5 - tags.size()) * 8;
                int ty = y + 172;
                if (mposx >= tx && mposy >= ty && mposx < tx + 16 && mposy < ty + 16) {
                    this.drawCustomTooltip(this, itemRenderer, this.field_146289_q, Arrays.asList(tag.getName(), tag.getLocalizedDescription()), mx, my - 8, 11);
                }
                ++count;
            }
        }
        GL11.glPushMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslated((double)0.0, (double)0.0, (double)100.0);
        RenderHelper.func_74520_c();
        GL11.glEnable((int)2884);
        itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(recipe.getRecipeOutput()), x + 48 + start, y + 22);
        itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(recipe.getRecipeOutput()), x + 48 + start, y + 22);
        RenderHelper.func_74518_a();
        GL11.glEnable((int)2896);
        GL11.glPopMatrix();
        if (mposx >= x + 48 + start && mposy >= y + 27 && mposx < x + 48 + start + 16 && mposy < y + 27 + 16) {
            this.drawCustomTooltip(this, itemRenderer, this.field_146289_q, InventoryUtils.cycleItemStack(recipe.getRecipeOutput()).func_82840_a((EntityPlayer)this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x), mx, my, 11);
        }
        String text = StatCollector.func_74838_a((String)"recipe.type.arcane");
        int offset = this.field_146289_q.func_78256_a(text);
        this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y, 0x505050);
        if (recipe != null && recipe instanceof ShapedArcaneRecipe) {
            int j;
            int i;
            int rw = ((ShapedArcaneRecipe)recipe).width;
            int rh = ((ShapedArcaneRecipe)recipe).height;
            Object[] items = ((ShapedArcaneRecipe)recipe).getInput();
            for (i = 0; i < rw && i < 3; ++i) {
                for (j = 0; j < rh && j < 3; ++j) {
                    if (items[i + j * rw] == null) continue;
                    GL11.glPushMatrix();
                    GL11.glTranslated((double)0.0, (double)0.0, (double)100.0);
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    RenderHelper.func_74520_c();
                    GL11.glEnable((int)2884);
                    itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(items[i + j * rw]), x + start + 16 + i * 32, y + 66 + j * 32);
                    itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(items[i + j * rw]).func_77946_l().func_77979_a(1), x + start + 16 + i * 32, y + 66 + j * 32);
                    RenderHelper.func_74518_a();
                    GL11.glEnable((int)2896);
                    GL11.glPopMatrix();
                }
            }
            for (i = 0; i < rw && i < 3; ++i) {
                for (j = 0; j < rh && j < 3; ++j) {
                    if (items[i + j * rw] == null || mposx < x + 16 + start + i * 32 || mposy < y + 66 + j * 32 || mposx >= x + 16 + start + i * 32 + 16 || mposy >= y + 66 + j * 32 + 16) continue;
                    List addtext = InventoryUtils.cycleItemStack(items[i + j * rw]).func_82840_a((EntityPlayer)this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x);
                    Object[] ref = this.findRecipeReference(InventoryUtils.cycleItemStack(items[i + j * rw]));
                    if (ref != null && !((String)ref[0]).equals(this.research.key)) {
                        addtext.add("\u00a78\u00a7o" + StatCollector.func_74838_a((String)"recipe.clickthrough"));
                        this.reference.add(Arrays.asList(mx, my, (String)ref[0], (Integer)ref[1]));
                    }
                    this.drawCustomTooltip(this, itemRenderer, this.field_146289_q, addtext, mx, my, 11);
                }
            }
        }
        if (recipe != null && recipe instanceof ShapelessArcaneRecipe) {
            int i;
            ArrayList items = ((ShapelessArcaneRecipe)recipe).getInput();
            for (i = 0; i < items.size() && i < 9; ++i) {
                if (items.get(i) == null) continue;
                GL11.glPushMatrix();
                GL11.glTranslated((double)0.0, (double)0.0, (double)100.0);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                RenderHelper.func_74520_c();
                GL11.glEnable((int)2884);
                itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(items.get(i)), x + start + 16 + i % 3 * 32, y + 66 + i / 3 * 32);
                itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(items.get(i)), x + start + 16 + i % 3 * 32, y + 66 + i / 3 * 32);
                RenderHelper.func_74518_a();
                GL11.glEnable((int)2896);
                GL11.glPopMatrix();
            }
            for (i = 0; i < items.size() && i < 9; ++i) {
                if (items.get(i) == null || mposx < x + 16 + start + i % 3 * 32 || mposy < y + 66 + i / 3 * 32 || mposx >= x + 16 + start + i % 3 * 32 + 16 || mposy >= y + 66 + i / 3 * 32 + 16) continue;
                List addtext = InventoryUtils.cycleItemStack(items.get(i)).func_82840_a((EntityPlayer)this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x);
                Object[] ref = this.findRecipeReference(InventoryUtils.cycleItemStack(items.get(i)));
                if (ref != null && !((String)ref[0]).equals(this.research.key)) {
                    addtext.add("\u00a78\u00a7o" + StatCollector.func_74838_a((String)"recipe.clickthrough"));
                    this.reference.add(Arrays.asList(mx, my, (String)ref[0], (Integer)ref[1]));
                }
                this.drawCustomTooltip(this, itemRenderer, this.field_146289_q, addtext, mx, my, 11);
            }
        }
        GL11.glPopMatrix();
    }

    private void drawCraftingPage(int side, int x, int y, int mx, int my, ResearchPage pageParm) {
        int offset;
        String text;
        ShapedRecipes recipe = null;
        Object tr = null;
        if (pageParm.recipe instanceof Object[]) {
            try {
                tr = ((Object[])pageParm.recipe)[this.cycle];
            }
            catch (Exception e) {
                this.cycle = 0;
                tr = ((Object[])pageParm.recipe)[this.cycle];
            }
        } else {
            tr = pageParm.recipe;
        }
        if (tr instanceof ShapedRecipes) {
            recipe = (ShapedRecipes)tr;
        } else if (tr instanceof ShapelessRecipes) {
            recipe = (ShapelessRecipes)tr;
        } else if (tr instanceof ShapedOreRecipe) {
            recipe = (ShapedOreRecipe)tr;
        } else if (tr instanceof ShapelessOreRecipe) {
            recipe = (ShapelessOreRecipe)tr;
        }
        if (recipe == null) {
            return;
        }
        GL11.glPushMatrix();
        int start = side * 152;
        UtilsFX.bindTexture(this.tex2);
        GL11.glPushMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3042);
        GL11.glTranslatef((float)(x + start), (float)y, (float)0.0f);
        GL11.glScalef((float)2.0f, (float)2.0f, (float)1.0f);
        this.func_73729_b(2, 32, 60, 15, 52, 52);
        this.func_73729_b(20, 12, 20, 3, 16, 16);
        GL11.glPopMatrix();
        int mposx = mx;
        int mposy = my;
        GL11.glPushMatrix();
        GL11.glTranslated((double)0.0, (double)0.0, (double)100.0);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderHelper.func_74520_c();
        GL11.glEnable((int)2884);
        itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(recipe.func_77571_b()), x + 48 + start, y + 32);
        itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(recipe.func_77571_b()), x + 48 + start, y + 32);
        RenderHelper.func_74518_a();
        GL11.glEnable((int)2896);
        GL11.glPopMatrix();
        if (mposx >= x + 48 + start && mposy >= y + 32 && mposx < x + 48 + start + 16 && mposy < y + 32 + 16) {
            this.drawCustomTooltip(this, itemRenderer, this.field_146289_q, InventoryUtils.cycleItemStack(recipe.func_77571_b()).func_82840_a((EntityPlayer)this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x), mx, my, 11);
        }
        if (recipe != null && (recipe instanceof ShapedRecipes || recipe instanceof ShapedOreRecipe)) {
            int j;
            int i;
            text = StatCollector.func_74838_a((String)"recipe.type.workbench");
            offset = this.field_146289_q.func_78256_a(text);
            this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y, 0x505050);
            int rw = 0;
            int rh = 0;
            Object[] items = null;
            if (recipe instanceof ShapedRecipes) {
                rw = recipe.field_77576_b;
                rh = recipe.field_77577_c;
                items = recipe.field_77574_d;
            } else {
                rw = (Integer)ObfuscationReflectionHelper.getPrivateValue(ShapedOreRecipe.class, (Object)((ShapedOreRecipe)recipe), (String[])new String[]{"width"});
                rh = (Integer)ObfuscationReflectionHelper.getPrivateValue(ShapedOreRecipe.class, (Object)((ShapedOreRecipe)recipe), (String[])new String[]{"height"});
                items = ((ShapedOreRecipe)recipe).getInput();
            }
            for (i = 0; i < rw && i < 3; ++i) {
                for (j = 0; j < rh && j < 3; ++j) {
                    if (items[i + j * rw] == null) continue;
                    GL11.glPushMatrix();
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    RenderHelper.func_74520_c();
                    GL11.glEnable((int)2884);
                    GL11.glTranslated((double)0.0, (double)0.0, (double)100.0);
                    itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(items[i + j * rw]), x + start + 16 + i * 32, y + 76 + j * 32);
                    itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(items[i + j * rw]).func_77946_l().func_77979_a(1), x + start + 16 + i * 32, y + 76 + j * 32);
                    RenderHelper.func_74518_a();
                    GL11.glEnable((int)2896);
                    GL11.glPopMatrix();
                }
            }
            for (i = 0; i < rw && i < 3; ++i) {
                for (j = 0; j < rh && j < 3; ++j) {
                    if (items[i + j * rw] == null || mposx < x + 16 + start + i * 32 || mposy < y + 76 + j * 32 || mposx >= x + 16 + start + i * 32 + 16 || mposy >= y + 76 + j * 32 + 16) continue;
                    List addtext = InventoryUtils.cycleItemStack(items[i + j * rw]).func_82840_a((EntityPlayer)this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x);
                    Object[] ref = this.findRecipeReference(InventoryUtils.cycleItemStack(items[i + j * rw]));
                    if (ref != null && !((String)ref[0]).equals(this.research.key)) {
                        addtext.add("\u00a78\u00a7o" + StatCollector.func_74838_a((String)"recipe.clickthrough"));
                        this.reference.add(Arrays.asList(mx, my, (String)ref[0], (Integer)ref[1]));
                    }
                    this.drawCustomTooltip(this, itemRenderer, this.field_146289_q, addtext, mx, my, 11);
                }
            }
        }
        if (recipe != null && (recipe instanceof ShapelessRecipes || recipe instanceof ShapelessOreRecipe)) {
            int i;
            text = StatCollector.func_74838_a((String)"recipe.type.workbenchshapeless");
            offset = this.field_146289_q.func_78256_a(text);
            this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y, 0x505050);
            List items = null;
            items = recipe instanceof ShapelessRecipes ? ((ShapelessRecipes)recipe).field_77579_b : ((ShapelessOreRecipe)recipe).getInput();
            for (i = 0; i < items.size() && i < 9; ++i) {
                if (items.get(i) == null) continue;
                GL11.glPushMatrix();
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                RenderHelper.func_74520_c();
                GL11.glEnable((int)2884);
                GL11.glTranslated((double)0.0, (double)0.0, (double)100.0);
                itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(items.get(i)), x + start + 16 + i % 3 * 32, y + 76 + i / 3 * 32);
                itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(items.get(i)).func_77946_l().func_77979_a(1), x + start + 16 + i % 3 * 32, y + 76 + i / 3 * 32);
                RenderHelper.func_74518_a();
                GL11.glEnable((int)2896);
                GL11.glPopMatrix();
            }
            for (i = 0; i < items.size() && i < 9; ++i) {
                if (items.get(i) == null || mposx < x + 16 + start + i % 3 * 32 || mposy < y + 76 + i / 3 * 32 || mposx >= x + 16 + start + i % 3 * 32 + 16 || mposy >= y + 76 + i / 3 * 32 + 16) continue;
                List addtext = InventoryUtils.cycleItemStack(items.get(i)).func_82840_a((EntityPlayer)this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x);
                Object[] ref = this.findRecipeReference(InventoryUtils.cycleItemStack(items.get(i)));
                if (ref != null && !((String)ref[0]).equals(this.research.key)) {
                    addtext.add("\u00a78\u00a7o" + StatCollector.func_74838_a((String)"recipe.clickthrough"));
                    this.reference.add(Arrays.asList(mx, my, (String)ref[0], (Integer)ref[1]));
                }
                this.drawCustomTooltip(this, itemRenderer, this.field_146289_q, addtext, mx, my, 11);
            }
        }
        GL11.glPopMatrix();
    }

    private void drawCruciblePage(int side, int x, int y, int mx, int my, ResearchPage pageParm) {
        CrucibleRecipe rc = null;
        Object tr = null;
        if (pageParm.recipe instanceof Object[]) {
            try {
                tr = ((Object[])pageParm.recipe)[this.cycle];
            }
            catch (Exception e) {
                this.cycle = 0;
                tr = ((Object[])pageParm.recipe)[this.cycle];
            }
        } else {
            tr = pageParm.recipe;
        }
        if (tr instanceof CrucibleRecipe) {
            rc = (CrucibleRecipe)tr;
        }
        if (rc != null) {
            int vy;
            int vx;
            int m;
            GL11.glPushMatrix();
            int start = side * 152;
            String text = StatCollector.func_74838_a((String)"recipe.type.crucible");
            int offset = this.field_146289_q.func_78256_a(text);
            this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y, 0x505050);
            UtilsFX.bindTexture(this.tex2);
            GL11.glPushMatrix();
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glEnable((int)3042);
            GL11.glTranslatef((float)(x + start), (float)(y + 28), (float)0.0f);
            GL11.glScalef((float)2.0f, (float)2.0f, (float)1.0f);
            this.func_73729_b(0, 0, 0, 3, 56, 17);
            GL11.glTranslatef((float)0.0f, (float)32.0f, (float)0.0f);
            this.func_73729_b(0, 0, 0, 20, 56, 48);
            GL11.glTranslatef((float)21.0f, (float)-8.0f, (float)0.0f);
            this.func_73729_b(0, 0, 100, 84, 11, 13);
            GL11.glPopMatrix();
            int mposx = mx;
            int mposy = my;
            int total = 0;
            int rows = (rc.aspects.size() - 1) / 3;
            int shift = (3 - rc.aspects.size() % 3) * 10;
            int sx = x + start + 28;
            int sy = y + 96 + 32 - 10 * rows;
            for (Aspect tag : rc.aspects.getAspectsSorted()) {
                m = 0;
                if (total / 3 >= rows && (rows > 1 || rc.aspects.size() < 3)) {
                    m = 1;
                }
                vx = sx + total % 3 * 20 + shift * m;
                vy = sy + total / 3 * 20;
                UtilsFX.drawTag(vx, vy, tag, rc.aspects.getAmount(tag), 0, this.field_73735_i);
                ++total;
            }
            GL11.glPushMatrix();
            GL11.glTranslated((double)0.0, (double)0.0, (double)100.0);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            RenderHelper.func_74520_c();
            GL11.glEnable((int)2884);
            itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, rc.getRecipeOutput(), x + 48 + start, y + 36);
            itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, rc.getRecipeOutput(), x + 48 + start, y + 36);
            RenderHelper.func_74518_a();
            GL11.glEnable((int)2896);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslated((double)0.0, (double)0.0, (double)100.0);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            RenderHelper.func_74520_c();
            GL11.glEnable((int)2884);
            itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(rc.catalyst), x + 26 + start, y + 72);
            itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(rc.catalyst).func_77946_l().func_77979_a(1), x + 26 + start, y + 72);
            RenderHelper.func_74518_a();
            GL11.glEnable((int)2896);
            GL11.glPopMatrix();
            if (mposx >= x + 48 + start && mposy >= y + 36 && mposx < x + 48 + start + 16 && mposy < y + 36 + 16) {
                this.drawCustomTooltip(this, itemRenderer, this.field_146289_q, rc.getRecipeOutput().func_82840_a((EntityPlayer)this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x), mx, my, 11);
            }
            if (mposx >= x + 26 + start && mposy >= y + 72 && mposx < x + 26 + start + 16 && mposy < y + 72 + 16) {
                List addtext = InventoryUtils.cycleItemStack(rc.catalyst).func_82840_a((EntityPlayer)this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x);
                Object[] ref = this.findRecipeReference(InventoryUtils.cycleItemStack(rc.catalyst));
                if (ref != null && !((String)ref[0]).equals(this.research.key)) {
                    addtext.add("\u00a78\u00a7o" + StatCollector.func_74838_a((String)"recipe.clickthrough"));
                    this.reference.add(Arrays.asList(mx, my, (String)ref[0], (Integer)ref[1]));
                }
                this.drawCustomTooltip(this, itemRenderer, this.field_146289_q, addtext, mx, my, 11);
            }
            total = 0;
            for (Aspect tag : rc.aspects.getAspectsSorted()) {
                m = 0;
                if (total / 3 >= rows && (rows > 1 || rc.aspects.size() < 3)) {
                    m = 1;
                }
                vx = sx + total % 3 * 20 + shift * m;
                vy = sy + total / 3 * 20;
                if (mposx >= vx && mposy >= vy && mposx < vx + 16 && mposy < vy + 16) {
                    this.drawCustomTooltip(this, itemRenderer, this.field_146289_q, Arrays.asList(tag.getName(), tag.getLocalizedDescription()), mx, my, 11);
                }
                ++total;
            }
            GL11.glPopMatrix();
        }
    }

    private void drawSmeltingPage(int side, int x, int y, int mx, int my, ResearchPage pageParm) {
        ItemStack in = (ItemStack)pageParm.recipe;
        ItemStack out = null;
        if (in != null) {
            out = FurnaceRecipes.func_77602_a().func_151395_a(in);
        }
        if (in != null && out != null) {
            GL11.glPushMatrix();
            int start = side * 152;
            String text = StatCollector.func_74838_a((String)"recipe.type.smelting");
            int offset = this.field_146289_q.func_78256_a(text);
            this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y, 0x505050);
            UtilsFX.bindTexture(this.tex2);
            GL11.glPushMatrix();
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glEnable((int)3042);
            GL11.glTranslatef((float)(x + start), (float)(y + 28), (float)0.0f);
            GL11.glScalef((float)2.0f, (float)2.0f, (float)1.0f);
            this.func_73729_b(0, 0, 0, 192, 56, 64);
            GL11.glPopMatrix();
            int mposx = mx;
            int mposy = my;
            GL11.glPushMatrix();
            GL11.glTranslated((double)0.0, (double)0.0, (double)100.0);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            RenderHelper.func_74520_c();
            GL11.glEnable((int)2884);
            itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, in, x + 48 + start, y + 64);
            itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, in, x + 48 + start, y + 64);
            RenderHelper.func_74518_a();
            GL11.glEnable((int)2896);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslated((double)0.0, (double)0.0, (double)100.0);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            RenderHelper.func_74520_c();
            GL11.glEnable((int)2884);
            itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, out, x + 48 + start, y + 144);
            itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, out, x + 48 + start, y + 144);
            RenderHelper.func_74518_a();
            GL11.glEnable((int)2896);
            GL11.glPopMatrix();
            if (mposx >= x + 48 + start && mposy >= y + 64 && mposx < x + 48 + start + 16 && mposy < y + 64 + 16) {
                List addtext = in.func_82840_a((EntityPlayer)this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x);
                Object[] ref = this.findRecipeReference(in);
                if (ref != null && !((String)ref[0]).equals(this.research.key)) {
                    addtext.add("\u00a78\u00a7o" + StatCollector.func_74838_a((String)"recipe.clickthrough"));
                    this.reference.add(Arrays.asList(mx, my, (String)ref[0], (Integer)ref[1]));
                }
                this.drawCustomTooltip(this, itemRenderer, this.field_146289_q, addtext, mx, my, 11);
            }
            if (mposx >= x + 48 + start && mposy >= y + 144 && mposx < x + 48 + start + 16 && mposy < y + 144 + 16) {
                this.drawCustomTooltip(this, itemRenderer, this.field_146289_q, out.func_82840_a((EntityPlayer)this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x), mx, my, 11);
            }
            GL11.glPopMatrix();
        }
    }

    /*
     * WARNING - void declaration
     */
    private void drawInfusionPage(int side, int x, int y, int mx, int my, ResearchPage pageParm) {
        InfusionRecipe ri;
        Object tr = null;
        if (pageParm.recipe instanceof Object[]) {
            try {
                tr = ((Object[])pageParm.recipe)[this.cycle];
            }
            catch (Exception e) {
                this.cycle = 0;
                tr = ((Object[])pageParm.recipe)[this.cycle];
            }
        } else {
            tr = pageParm.recipe;
        }
        if ((ri = (InfusionRecipe)tr) != null) {
            int vy;
            int vx;
            void var25_33;
            GL11.glPushMatrix();
            int start = side * 152;
            String text = StatCollector.func_74838_a((String)"recipe.type.infusion");
            int offset = this.field_146289_q.func_78256_a(text);
            this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y, 0x505050);
            int inst = Math.min(5, ri.getInstability() / 2);
            text = StatCollector.func_74838_a((String)"tc.inst") + " " + StatCollector.func_74838_a((String)("tc.inst." + inst));
            offset = this.field_146289_q.func_78256_a(text);
            this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y + 194, 0x505050);
            UtilsFX.bindTexture(this.tex2);
            GL11.glPushMatrix();
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glEnable((int)3042);
            GL11.glTranslatef((float)(x + start), (float)(y + 20), (float)0.0f);
            GL11.glScalef((float)2.0f, (float)2.0f, (float)1.0f);
            this.func_73729_b(0, 0, 0, 3, 56, 17);
            GL11.glTranslatef((float)0.0f, (float)19.0f, (float)0.0f);
            this.func_73729_b(0, 0, 200, 77, 60, 44);
            GL11.glPopMatrix();
            int mposx = mx;
            int mposy = my;
            int total = 0;
            int rows = (ri.getAspects().size() - 1) / 5;
            int shift = (5 - ri.getAspects().size() % 5) * 10;
            int sx = x + start + 8;
            int sy = y + 164 - 10 * rows;
            for (Aspect tag : ri.getAspects().getAspectsSorted()) {
                int m = 0;
                if (total / 5 >= rows && (rows > 1 || ri.getAspects().size() < 5)) {
                    m = 1;
                }
                int n = sx + total % 5 * 20 + shift * m;
                int vy2 = sy + total / 5 * 20;
                UtilsFX.drawTag(n, vy2, tag, ri.getAspects().getAmount(tag), 0, this.field_73735_i);
                ++total;
            }
            ItemStack idisp = null;
            if (ri.getRecipeOutput() instanceof ItemStack) {
                idisp = InventoryUtils.cycleItemStack((ItemStack)ri.getRecipeOutput());
            } else {
                idisp = InventoryUtils.cycleItemStack(ri.getRecipeInput()).func_77946_l();
                Object[] obj = (Object[])ri.getRecipeOutput();
                NBTBase tag = (NBTBase)obj[1];
                idisp.func_77983_a((String)obj[0], tag);
            }
            GL11.glPushMatrix();
            GL11.glTranslated((double)0.0, (double)0.0, (double)100.0);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            RenderHelper.func_74520_c();
            GL11.glEnable((int)2884);
            itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, idisp, x + 48 + start, y + 28);
            itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, idisp, x + 48 + start, y + 28);
            RenderHelper.func_74518_a();
            GL11.glEnable((int)2896);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslated((double)0.0, (double)0.0, (double)100.0);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            RenderHelper.func_74520_c();
            GL11.glEnable((int)2884);
            itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(ri.getRecipeInput()), x + 48 + start, y + 94);
            itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(ri.getRecipeInput()).func_77946_l().func_77979_a(1), x + 48 + start, y + 94);
            RenderHelper.func_74518_a();
            GL11.glEnable((int)2896);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslated((double)0.0, (double)0.0, (double)100.0);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            RenderHelper.func_74520_c();
            GL11.glDisable((int)2896);
            GL11.glEnable((int)2884);
            int le = ri.getComponents().length;
            ArrayList<Coord2D> coords = new ArrayList<Coord2D>();
            float pieSlice = 360 / le;
            float currentRot = -90.0f;
            boolean bl = false;
            while (var25_33 < le) {
                int xx = (int)(MathHelper.func_76134_b((float)(currentRot / 180.0f * (float)Math.PI)) * 40.0f) - 8;
                int yy = (int)(MathHelper.func_76126_a((float)(currentRot / 180.0f * (float)Math.PI)) * 40.0f) - 8;
                currentRot += pieSlice;
                coords.add(new Coord2D(xx, yy));
                ++var25_33;
            }
            total = 0;
            sx = x + 56 + start;
            sy = y + 102;
            ItemStack[] itemStackArray = ri.getComponents();
            int len$ = itemStackArray.length;
            for (int i$ = 0; i$ < len$; ++i$) {
                ItemStack itemStack = itemStackArray[i$];
                RenderHelper.func_74520_c();
                vx = sx + ((Coord2D)coords.get((int)total)).x;
                vy = sy + ((Coord2D)coords.get((int)total)).y;
                itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(itemStack), vx, vy);
                itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(itemStack).func_77946_l().func_77979_a(1), vx, vy);
                RenderHelper.func_74518_a();
                ++total;
            }
            GL11.glEnable((int)2896);
            GL11.glPopMatrix();
            if (mposx >= x + 48 + start && mposy >= y + 28 && mposx < x + 48 + start + 16 && mposy < y + 28 + 16) {
                this.drawCustomTooltip(this, itemRenderer, this.field_146289_q, idisp.func_82840_a((EntityPlayer)this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x), mx, my, 11);
            }
            if (mposx >= x + 48 + start && mposy >= y + 94 && mposx < x + 48 + start + 16 && mposy < y + 94 + 16) {
                List list = InventoryUtils.cycleItemStack(ri.getRecipeInput()).func_82840_a((EntityPlayer)this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x);
                Object[] ref = this.findRecipeReference(InventoryUtils.cycleItemStack(ri.getRecipeInput()));
                if (ref != null && !((String)ref[0]).equals(this.research.key)) {
                    list.add("\u00a78\u00a7o" + StatCollector.func_74838_a((String)"recipe.clickthrough"));
                    this.reference.add(Arrays.asList(mx, my, (String)ref[0], (Integer)ref[1]));
                }
                this.drawCustomTooltip(this, itemRenderer, this.field_146289_q, list, mx, my, 11);
            }
            total = 0;
            sx = x + 56 + start;
            sy = y + 102;
            for (ItemStack itemStack : ri.getComponents()) {
                vx = sx + ((Coord2D)coords.get((int)total)).x;
                vy = sy + ((Coord2D)coords.get((int)total)).y;
                if (mposx >= vx && mposy >= vy && mposx < vx + 16 && mposy < vy + 16) {
                    List addtext = InventoryUtils.cycleItemStack(itemStack).func_82840_a((EntityPlayer)this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x);
                    Object[] ref = this.findRecipeReference(InventoryUtils.cycleItemStack(itemStack));
                    if (ref != null && !((String)ref[0]).equals(this.research.key)) {
                        addtext.add("\u00a78\u00a7o" + StatCollector.func_74838_a((String)"recipe.clickthrough"));
                        this.reference.add(Arrays.asList(mx, my, (String)ref[0], (Integer)ref[1]));
                    }
                    this.drawCustomTooltip(this, itemRenderer, this.field_146289_q, addtext, mx, my, 11);
                }
                ++total;
            }
            total = 0;
            rows = (ri.getAspects().size() - 1) / 5;
            shift = (5 - ri.getAspects().size() % 5) * 10;
            sx = x + start + 8;
            sy = y + 164 - 10 * rows;
            for (Aspect aspect : ri.getAspects().getAspectsSorted()) {
                int m = 0;
                if (total / 5 >= rows && (rows > 1 || ri.getAspects().size() < 5)) {
                    m = 1;
                }
                int vx3 = sx + total % 5 * 20 + shift * m;
                int vy3 = sy + total / 5 * 20;
                if (mposx >= vx3 && mposy >= vy3 && mposx < vx3 + 16 && mposy < vy3 + 16) {
                    this.drawCustomTooltip(this, itemRenderer, this.field_146289_q, Arrays.asList(aspect.getName(), aspect.getLocalizedDescription()), mx, my, 11);
                }
                ++total;
            }
            GL11.glPopMatrix();
        }
    }

    /*
     * WARNING - void declaration
     */
    private void drawInfusionEnchantingPage(int side, int x, int y, int mx, int my, ResearchPage pageParm) {
        Object tr = pageParm.recipe;
        InfusionEnchantmentRecipe ri = (InfusionEnchantmentRecipe)tr;
        if (ri != null) {
            int vy;
            int vx;
            void var26_32;
            GL11.glPushMatrix();
            int start = side * 152;
            int level = (int)(1L + System.currentTimeMillis() / 1000L % (long)ri.enchantment.func_77325_b());
            String text = StatCollector.func_74838_a((String)"recipe.type.infusionenchantment");
            int offset = this.field_146289_q.func_78256_a(text);
            this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y, 0x505050);
            int inst = Math.min(5, ri.instability / 2);
            text = StatCollector.func_74838_a((String)"tc.inst") + " " + StatCollector.func_74838_a((String)("tc.inst." + inst));
            offset = this.field_146289_q.func_78256_a(text);
            this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y + 194, 0x505050);
            text = ri.enchantment.func_77316_c(level);
            offset = this.field_146289_q.func_78256_a(text);
            this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y + 24, 7360656);
            int xp = ri.recipeXP * level;
            text = xp + " levels";
            offset = this.field_146289_q.func_78256_a(text);
            this.field_146289_q.func_78276_b(text, x + start + 56 - offset / 2, y + 40, 0x508850);
            UtilsFX.bindTexture(this.tex2);
            GL11.glPushMatrix();
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glEnable((int)3042);
            GL11.glTranslatef((float)(x + start), (float)(y + 20), (float)0.0f);
            GL11.glScalef((float)2.0f, (float)2.0f, (float)1.0f);
            GL11.glTranslatef((float)0.0f, (float)19.0f, (float)0.0f);
            this.func_73729_b(0, 0, 200, 77, 60, 44);
            GL11.glPopMatrix();
            int mposx = mx;
            int mposy = my;
            int total = 0;
            int rows = (ri.aspects.size() - 1) / 5;
            int shift = (5 - ri.aspects.size() % 5) * 10;
            int sx = x + start + 8;
            int sy = y + 164 - 10 * rows;
            for (Aspect tag : ri.aspects.getAspectsSorted()) {
                int n = 0;
                if (total / 5 >= rows && (rows > 1 || ri.aspects.size() < 5)) {
                    n = 1;
                }
                int vx2 = sx + total % 5 * 20 + shift * n;
                int vy2 = sy + total / 5 * 20;
                UtilsFX.drawTag(vx2, vy2, tag, ri.aspects.getAmount(tag) * level, 0, this.field_73735_i);
                ++total;
            }
            GL11.glPushMatrix();
            GL11.glTranslated((double)0.0, (double)0.0, (double)100.0);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            RenderHelper.func_74520_c();
            GL11.glDisable((int)2896);
            GL11.glEnable((int)2884);
            int le = ri.components.length;
            ArrayList<Coord2D> coords = new ArrayList<Coord2D>();
            float pieSlice = 360 / le;
            float currentRot = -90.0f;
            boolean bl = false;
            while (var26_32 < le) {
                int xx = (int)(MathHelper.func_76134_b((float)(currentRot / 180.0f * (float)Math.PI)) * 40.0f) - 8;
                int yy = (int)(MathHelper.func_76126_a((float)(currentRot / 180.0f * (float)Math.PI)) * 40.0f) - 8;
                currentRot += pieSlice;
                coords.add(new Coord2D(xx, yy));
                ++var26_32;
            }
            total = 0;
            sx = x + 56 + start;
            sy = y + 102;
            for (ItemStack itemStack : ri.components) {
                RenderHelper.func_74520_c();
                vx = sx + ((Coord2D)coords.get((int)total)).x;
                vy = sy + ((Coord2D)coords.get((int)total)).y;
                itemRenderer.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(itemStack), vx, vy);
                itemRenderer.func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, InventoryUtils.cycleItemStack(itemStack).func_77946_l().func_77979_a(1), vx, vy);
                ++total;
                RenderHelper.func_74518_a();
            }
            GL11.glEnable((int)2896);
            GL11.glPopMatrix();
            total = 0;
            sx = x + 56 + start;
            sy = y + 102;
            for (ItemStack itemStack : ri.components) {
                vx = sx + ((Coord2D)coords.get((int)total)).x;
                vy = sy + ((Coord2D)coords.get((int)total)).y;
                if (mposx >= vx && mposy >= vy && mposx < vx + 16 && mposy < vy + 16) {
                    List addtext = InventoryUtils.cycleItemStack(itemStack).func_82840_a((EntityPlayer)this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x);
                    Object[] ref = this.findRecipeReference(InventoryUtils.cycleItemStack(itemStack));
                    if (ref != null && !((String)ref[0]).equals(this.research.key)) {
                        addtext.add("\u00a78\u00a7o" + StatCollector.func_74838_a((String)"recipe.clickthrough"));
                        this.reference.add(Arrays.asList(mx, my, (String)ref[0], (Integer)ref[1]));
                    }
                    this.drawCustomTooltip(this, itemRenderer, this.field_146289_q, addtext, mx, my, 11);
                }
                ++total;
            }
            total = 0;
            rows = (ri.aspects.size() - 1) / 5;
            shift = (5 - ri.aspects.size() % 5) * 10;
            sx = x + start + 8;
            sy = y + 164 - 10 * rows;
            for (Aspect aspect : ri.aspects.getAspectsSorted()) {
                int m = 0;
                if (total / 5 >= rows && (rows > 1 || ri.aspects.size() < 5)) {
                    m = 1;
                }
                int vx3 = sx + total % 5 * 20 + shift * m;
                int vy3 = sy + total / 5 * 20;
                if (mposx >= vx3 && mposy >= vy3 && mposx < vx3 + 16 && mposy < vy3 + 16) {
                    this.drawCustomTooltip(this, itemRenderer, this.field_146289_q, Arrays.asList(aspect.getName(), aspect.getLocalizedDescription()), mx, my, 11);
                }
                ++total;
            }
            GL11.glPopMatrix();
        }
    }

    private void drawTextPage(int side, int x, int y, String text) {
        GL11.glPushMatrix();
        RenderHelper.func_74520_c();
        GL11.glEnable((int)3042);
        this.fr.drawSplitString(text, x - 15 + side * 152, y, 139, 0, (Gui)this);
        GL11.glPopMatrix();
    }

    protected void func_73864_a(int par1, int par2, int par3) {
        int var4 = (this.field_146294_l - this.paneWidth) / 2;
        int var5 = (this.field_146295_m - this.paneHeight) / 2;
        int mx = par1 - (var4 + 261);
        int my = par2 - (var5 + 189);
        if (this.page < this.maxPages - 2 && mx >= 0 && my >= 0 && mx < 14 && my < 10) {
            this.page += 2;
            this.lastCycle = 0L;
            this.cycle = -1;
            Minecraft.func_71410_x().field_71441_e.func_72980_b(Minecraft.func_71410_x().field_71439_g.field_70165_t, Minecraft.func_71410_x().field_71439_g.field_70163_u, Minecraft.func_71410_x().field_71439_g.field_70161_v, "thaumcraft:page", 0.66f, 1.0f, false);
        }
        mx = par1 - (var4 - 17);
        my = par2 - (var5 + 189);
        if (this.page >= 2 && mx >= 0 && my >= 0 && mx < 14 && my < 10) {
            this.page -= 2;
            this.lastCycle = 0L;
            this.cycle = -1;
            Minecraft.func_71410_x().field_71441_e.func_72980_b(Minecraft.func_71410_x().field_71439_g.field_70165_t, Minecraft.func_71410_x().field_71439_g.field_70163_u, Minecraft.func_71410_x().field_71439_g.field_70161_v, "thaumcraft:page", 0.66f, 1.0f, false);
        }
        if (!history.isEmpty()) {
            mx = par1 - (var4 + 118);
            my = par2 - (var5 + 189);
            if (mx >= 0 && my >= 0 && mx < 20 && my < 12) {
                Minecraft.func_71410_x().field_71441_e.func_72980_b(Minecraft.func_71410_x().field_71439_g.field_70165_t, Minecraft.func_71410_x().field_71439_g.field_70163_u, Minecraft.func_71410_x().field_71439_g.field_70161_v, "thaumcraft:page", 0.66f, 1.0f, false);
                Object[] o = history.pop();
                this.field_146297_k.func_147108_a((GuiScreen)new GuiResearchRecipe(ResearchCategories.getResearch((String)o[0]), (Integer)o[1], this.guiMapX, this.guiMapY));
            }
        }
        if (this.reference.size() > 0) {
            for (List coords : this.reference) {
                if (par1 < (Integer)coords.get(0) || par2 < (Integer)coords.get(1) || par1 >= (Integer)coords.get(0) + 16 || par2 >= (Integer)coords.get(1) + 16) continue;
                Minecraft.func_71410_x().field_71441_e.func_72980_b(Minecraft.func_71410_x().field_71439_g.field_70165_t, Minecraft.func_71410_x().field_71439_g.field_70163_u, Minecraft.func_71410_x().field_71439_g.field_70161_v, "thaumcraft:page", 0.66f, 1.0f, false);
                history.push(new Object[]{this.research.key, this.page});
                this.field_146297_k.func_147108_a((GuiScreen)new GuiResearchRecipe(ResearchCategories.getResearch((String)coords.get(2)), (Integer)coords.get(3), this.guiMapX, this.guiMapY));
            }
        }
        super.func_73864_a(par1, par2, par3);
    }

    public boolean func_73868_f() {
        return false;
    }

    public Object[] findRecipeReference(ItemStack item) {
        return ThaumcraftApi.getCraftingRecipeKey((EntityPlayer)this.field_146297_k.field_71439_g, item);
    }

    public void drawTexturedModalRectScaled(int par1, int par2, int par3, int par4, int par5, int par6, float scale) {
        GL11.glPushMatrix();
        float var7 = 0.00390625f;
        float var8 = 0.00390625f;
        Tessellator var9 = Tessellator.field_78398_a;
        GL11.glTranslatef((float)((float)par1 + (float)par5 / 2.0f), (float)((float)par2 + (float)par6 / 2.0f), (float)0.0f);
        GL11.glScalef((float)(1.0f + scale), (float)(1.0f + scale), (float)1.0f);
        var9.func_78382_b();
        var9.func_78374_a((double)((float)(-par5) / 2.0f), (double)((float)par6 / 2.0f), (double)this.field_73735_i, (double)((float)(par3 + 0) * var7), (double)((float)(par4 + par6) * var8));
        var9.func_78374_a((double)((float)par5 / 2.0f), (double)((float)par6 / 2.0f), (double)this.field_73735_i, (double)((float)(par3 + par5) * var7), (double)((float)(par4 + par6) * var8));
        var9.func_78374_a((double)((float)par5 / 2.0f), (double)((float)(-par6) / 2.0f), (double)this.field_73735_i, (double)((float)(par3 + par5) * var7), (double)((float)(par4 + 0) * var8));
        var9.func_78374_a((double)((float)(-par5) / 2.0f), (double)((float)(-par6) / 2.0f), (double)this.field_73735_i, (double)((float)(par3 + 0) * var7), (double)((float)(par4 + 0) * var8));
        var9.func_78381_a();
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
}

