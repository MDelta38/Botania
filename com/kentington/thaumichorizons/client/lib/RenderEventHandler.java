/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaublesApi
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$RenderTickEvent
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.C07PacketPlayerDigging
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.MathHelper
 *  net.minecraftforge.client.event.DrawBlockHighlightEvent
 *  net.minecraftforge.client.event.RenderBlockOverlayEvent
 *  net.minecraftforge.client.event.RenderBlockOverlayEvent$OverlayType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.Display
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.UtilsFX
 *  thaumcraft.common.Thaumcraft
 */
package com.kentington.thaumichorizons.client.lib;

import baubles.api.BaublesApi;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.entities.EntityBoatThaumium;
import com.kentington.thaumichorizons.common.items.lenses.ILens;
import com.kentington.thaumichorizons.common.items.lenses.ItemLensCase;
import com.kentington.thaumichorizons.common.items.lenses.LensManager;
import com.kentington.thaumichorizons.common.lib.PacketHandler;
import com.kentington.thaumichorizons.common.lib.PacketLensChangeToServer;
import com.kentington.thaumichorizons.common.lib.THKeyHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.nodes.IRevealer;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.Thaumcraft;

public class RenderEventHandler {
    static float radialHudScale = 0.0f;
    TreeMap<String, Integer> foci = new TreeMap();
    HashMap<String, ItemStack> fociItem = new HashMap();
    HashMap<String, Boolean> fociHover = new HashMap();
    HashMap<String, Float> fociScale = new HashMap();
    long lastTime = 0L;
    boolean lastState = false;
    float breakProgress = 0.0f;
    public int cacheX = Integer.MAX_VALUE;
    public int cacheY = Integer.MAX_VALUE;
    public int cacheZ = Integer.MAX_VALUE;
    int evanescentStage = 0;
    Block[][] tempBlock = new Block[3][3];
    int[][] tempMD = new int[3][3];
    public ForgeDirection tempDir;
    public ArrayList<EntityLivingBase> thingsThatSparkle = new ArrayList();

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent event) {
        Minecraft mc = Minecraft.func_71410_x();
        long time = System.nanoTime() / 1000000L;
        if (event.type == RenderGameOverlayEvent.ElementType.TEXT) {
            ILens theLens;
            this.handleFociRadial(mc, time, event);
            ItemStack goggles = mc.field_71439_g.field_71071_by.func_70440_f(3);
            if (LensManager.nightVisionOffTime > 0L && (goggles == null || !(goggles.func_77973_b() instanceof IRevealer) || goggles.field_77990_d == null) && mc.field_71439_g.func_70660_b(Potion.field_76439_r) != null && mc.field_71439_g.func_70660_b(Potion.field_76439_r).func_82720_e()) {
                mc.field_71439_g.func_82170_o(Potion.field_76439_r.field_76415_H);
                LensManager.nightVisionOffTime = 0L;
            }
            if (goggles != null && goggles.func_77973_b() instanceof IRevealer && goggles.field_77990_d != null && goggles.field_77990_d.func_74779_i("Lens") != null && !goggles.field_77990_d.func_74779_i("Lens").equals("") && (theLens = (ILens)LensManager.getLens(goggles.field_77990_d.func_74779_i("Lens"))) != null) {
                theLens.handleRender(mc, event.partialTicks);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void handleFociRadial(Minecraft mc, long time, RenderGameOverlayEvent event) {
        if (THKeyHandler.radialActive || radialHudScale > 0.0f) {
            long timeDiff = System.currentTimeMillis() - THKeyHandler.lastPressV;
            if (THKeyHandler.radialActive) {
                if (mc.field_71462_r != null) {
                    THKeyHandler.radialActive = false;
                    THKeyHandler.radialLock = true;
                    mc.func_71381_h();
                    mc.func_71364_i();
                    return;
                }
                if (radialHudScale == 0.0f) {
                    int q;
                    ItemStack[] inv;
                    int a;
                    this.foci.clear();
                    this.fociItem.clear();
                    this.fociHover.clear();
                    this.fociScale.clear();
                    int pouchcount = 0;
                    ItemStack item = null;
                    IInventory baubles = BaublesApi.getBaubles((EntityPlayer)mc.field_71439_g);
                    for (a = 0; a < 4; ++a) {
                        if (baubles.func_70301_a(a) == null || !(baubles.func_70301_a(a).func_77973_b() instanceof ItemLensCase)) continue;
                        ++pouchcount;
                        item = baubles.func_70301_a(a);
                        inv = ((ItemLensCase)item.func_77973_b()).getInventory(item);
                        for (q = 0; q < inv.length; ++q) {
                            item = inv[q];
                            if (item == null || !(item.func_77973_b() instanceof ILens)) continue;
                            this.foci.put(((ILens)item.func_77973_b()).lensName(), q + pouchcount * 1000);
                            this.fociItem.put(((ILens)item.func_77973_b()).lensName(), item.func_77946_l());
                            this.fociScale.put(((ILens)item.func_77973_b()).lensName(), Float.valueOf(1.0f));
                            this.fociHover.put(((ILens)item.func_77973_b()).lensName(), false);
                        }
                    }
                    for (a = 0; a < 36; ++a) {
                        item = mc.field_71439_g.field_71071_by.field_70462_a[a];
                        if (item != null && item.func_77973_b() instanceof ILens) {
                            this.foci.put(((ILens)item.func_77973_b()).lensName(), a);
                            this.fociItem.put(((ILens)item.func_77973_b()).lensName(), item.func_77946_l());
                            this.fociScale.put(((ILens)item.func_77973_b()).lensName(), Float.valueOf(1.0f));
                            this.fociHover.put(((ILens)item.func_77973_b()).lensName(), false);
                        }
                        if (item == null || !(item.func_77973_b() instanceof ItemLensCase)) continue;
                        ++pouchcount;
                        inv = ((ItemLensCase)item.func_77973_b()).getInventory(item);
                        for (q = 0; q < inv.length; ++q) {
                            item = inv[q];
                            if (item == null || !(item.func_77973_b() instanceof ILens)) continue;
                            this.foci.put(((ILens)item.func_77973_b()).lensName(), q + pouchcount * 1000);
                            this.fociItem.put(((ILens)item.func_77973_b()).lensName(), item.func_77946_l());
                            this.fociScale.put(((ILens)item.func_77973_b()).lensName(), Float.valueOf(1.0f));
                            this.fociHover.put(((ILens)item.func_77973_b()).lensName(), false);
                        }
                    }
                    if (this.foci.size() > 0 && mc.field_71415_G) {
                        mc.field_71415_G = false;
                        mc.field_71417_B.func_74373_b();
                    }
                }
            } else if (mc.field_71462_r == null && this.lastState) {
                if (Display.isActive() && !mc.field_71415_G) {
                    mc.field_71415_G = true;
                    mc.field_71417_B.func_74372_a();
                }
                this.lastState = false;
            }
            this.renderFocusRadialHUD(event.resolution.func_78327_c(), event.resolution.func_78324_d(), time, event.partialTicks);
            if (time > this.lastTime) {
                for (String key : this.fociHover.keySet()) {
                    if (this.fociHover.get(key).booleanValue()) {
                        if (!THKeyHandler.radialActive && !THKeyHandler.radialLock) {
                            PacketHandler.INSTANCE.sendToServer((IMessage)new PacketLensChangeToServer((EntityPlayer)mc.field_71439_g, key));
                            THKeyHandler.radialLock = true;
                        }
                        if (!(this.fociScale.get(key).floatValue() < 1.3f)) continue;
                        this.fociScale.put(key, Float.valueOf(this.fociScale.get(key).floatValue() + 0.025f));
                        continue;
                    }
                    if (!(this.fociScale.get(key).floatValue() > 1.0f)) continue;
                    this.fociScale.put(key, Float.valueOf(this.fociScale.get(key).floatValue() - 0.025f));
                }
                if (!THKeyHandler.radialActive) {
                    radialHudScale -= 0.05f;
                } else if (THKeyHandler.radialActive && radialHudScale < 1.0f) {
                    radialHudScale += 0.05f;
                }
                if (radialHudScale > 1.0f) {
                    radialHudScale = 1.0f;
                }
                if (radialHudScale < 0.0f) {
                    radialHudScale = 0.0f;
                    THKeyHandler.radialLock = false;
                }
                this.lastTime = time + 5L;
                this.lastState = THKeyHandler.radialActive;
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    private void renderFocusRadialHUD(double sw, double sh, long time, float partialTicks) {
        RenderItem ri = new RenderItem();
        Minecraft mc = Minecraft.func_71410_x();
        if (mc.field_71439_g.field_71071_by.func_70440_f(3) == null || !(mc.field_71439_g.field_71071_by.func_70440_f(3).func_77973_b() instanceof IRevealer)) {
            return;
        }
        ItemStack goggles = mc.field_71439_g.field_71071_by.func_70440_f(3);
        ILens lens = null;
        if (goggles.field_77990_d != null) {
            lens = (ILens)LensManager.getLens(goggles.field_77990_d.func_74779_i("Lens"));
        }
        int i = (int)((double)Mouse.getEventX() * sw / (double)mc.field_71443_c);
        int j = (int)(sh - (double)Mouse.getEventY() * sh / (double)mc.field_71440_d - 1.0);
        int k = Mouse.getEventButton();
        if (this.fociItem.size() == 0) {
            return;
        }
        GL11.glPushMatrix();
        GL11.glClear((int)256);
        GL11.glMatrixMode((int)5889);
        GL11.glLoadIdentity();
        GL11.glOrtho((double)0.0, (double)sw, (double)sh, (double)0.0, (double)1000.0, (double)3000.0);
        GL11.glMatrixMode((int)5888);
        GL11.glLoadIdentity();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-2000.0f);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        GL11.glPushMatrix();
        GL11.glTranslated((double)(sw / 2.0), (double)(sh / 2.0), (double)0.0);
        ItemStack tt = null;
        float width = 16.0f + (float)this.fociItem.size() * 2.5f;
        UtilsFX.bindTexture((String)"textures/misc/radial.png");
        GL11.glPushMatrix();
        GL11.glRotatef((float)(partialTicks + (float)(mc.field_71439_g.field_70173_aa % 720) / 2.0f), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        UtilsFX.renderQuadCenteredFromTexture((float)(width * 2.75f * radialHudScale), (float)0.5f, (float)0.5f, (float)0.5f, (int)200, (int)771, (float)0.5f);
        GL11.glDisable((int)3042);
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glPopMatrix();
        UtilsFX.bindTexture((String)"textures/misc/radial2.png");
        GL11.glPushMatrix();
        GL11.glRotatef((float)(-(partialTicks + (float)(mc.field_71439_g.field_70173_aa % 720) / 2.0f)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        UtilsFX.renderQuadCenteredFromTexture((float)(width * 2.55f * radialHudScale), (float)0.5f, (float)0.5f, (float)0.5f, (int)200, (int)771, (float)0.5f);
        GL11.glDisable((int)3042);
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glPopMatrix();
        if (lens != null) {
            GL11.glPushMatrix();
            GL11.glEnable((int)32826);
            RenderHelper.func_74520_c();
            ItemStack item = new ItemStack((Item)lens);
            item.field_77990_d = null;
            ri.func_77015_a(mc.field_71466_p, mc.field_71446_o, item, -8, -8);
            RenderHelper.func_74518_a();
            GL11.glDisable((int)32826);
            GL11.glPopMatrix();
            int mx = (int)((double)i - sw / 2.0);
            int my = (int)((double)j - sh / 2.0);
            if (mx >= -10 && mx <= 10 && my >= -10 && my <= 10) {
                tt = new ItemStack((Item)lens);
            }
        }
        GL11.glScaled((double)radialHudScale, (double)radialHudScale, (double)radialHudScale);
        float currentRot = -90.0f * radialHudScale;
        float pieSlice = 360.0f / (float)this.fociItem.size();
        String key = this.foci.firstKey();
        for (int a = 0; a < this.fociItem.size(); ++a) {
            double xx = MathHelper.func_76134_b((float)(currentRot / 180.0f * 3.141593f)) * width;
            double yy = MathHelper.func_76126_a((float)(currentRot / 180.0f * 3.141593f)) * width;
            currentRot += pieSlice;
            GL11.glPushMatrix();
            GL11.glTranslated((double)xx, (double)yy, (double)100.0);
            GL11.glScalef((float)this.fociScale.get(key).floatValue(), (float)this.fociScale.get(key).floatValue(), (float)this.fociScale.get(key).floatValue());
            GL11.glEnable((int)32826);
            RenderHelper.func_74520_c();
            ItemStack item = this.fociItem.get(key).func_77946_l();
            item.field_77990_d = null;
            ri.func_77015_a(mc.field_71466_p, mc.field_71446_o, item, -8, -8);
            RenderHelper.func_74518_a();
            GL11.glDisable((int)32826);
            GL11.glPopMatrix();
            if (!THKeyHandler.radialLock && THKeyHandler.radialActive) {
                int mx = (int)((double)i - sw / 2.0 - xx);
                int my = (int)((double)j - sh / 2.0 - yy);
                if (mx >= -10 && mx <= 10 && my >= -10 && my <= 10) {
                    this.fociHover.put(key, true);
                    tt = this.fociItem.get(key);
                    if (k == 0) {
                        THKeyHandler.radialActive = false;
                        THKeyHandler.radialLock = true;
                        PacketHandler.INSTANCE.sendToServer((IMessage)new PacketLensChangeToServer((EntityPlayer)mc.field_71439_g, key));
                        break;
                    }
                } else {
                    this.fociHover.put(key, false);
                }
            }
            key = this.foci.higherKey(key);
        }
        GL11.glPopMatrix();
        if (tt != null) {
            UtilsFX.drawCustomTooltip((GuiScreen)mc.field_71462_r, (RenderItem)ri, (FontRenderer)mc.field_71466_p, (List)tt.func_82840_a((EntityPlayer)mc.field_71439_g, mc.field_71474_y.field_82882_x), (int)-4, (int)20, (int)11);
        }
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2929);
        GL11.glDisable((int)3042);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void blockHighlight(DrawBlockHighlightEvent event) {
        Minecraft mc = Minecraft.func_71410_x();
        ItemStack goggles = mc.field_71439_g.field_71071_by.func_70440_f(3);
        if (goggles != null && goggles.func_77973_b() instanceof IRevealer && goggles.field_77990_d != null) {
            if (goggles.field_77990_d.func_74779_i("Lens") != null && !goggles.field_77990_d.func_74779_i("Lens").equals("")) {
                ILens theLens = (ILens)LensManager.getLens(goggles.field_77990_d.func_74779_i("Lens"));
                if (theLens == ThaumicHorizons.itemLensEarth) {
                    if (this.cacheX != event.target.field_72311_b || this.cacheY != event.target.field_72312_c || this.cacheZ != event.target.field_72309_d || this.tempDir != ForgeDirection.getOrientation((int)event.target.field_72310_e)) {
                        this.resetBlocks((EntityPlayer)mc.field_71439_g);
                    }
                    if (event.player.field_70170_p.func_147439_a(event.target.field_72311_b, event.target.field_72312_c, event.target.field_72309_d).func_149688_o() != Material.field_151579_a) {
                        this.cacheX = event.target.field_72311_b;
                        this.cacheY = event.target.field_72312_c;
                        this.cacheZ = event.target.field_72309_d;
                        this.tempDir = ForgeDirection.getOrientation((int)event.target.field_72310_e);
                    } else {
                        this.resetBlocks((EntityPlayer)mc.field_71439_g);
                    }
                } else {
                    this.resetBlocks((EntityPlayer)mc.field_71439_g);
                }
            } else {
                this.resetBlocks((EntityPlayer)mc.field_71439_g);
            }
        } else if (this.evanescentStage != 0) {
            this.resetBlocks((EntityPlayer)mc.field_71439_g);
        }
    }

    void setBlocksEvanescent(EntityPlayer p) {
        if (p.field_82175_bq) {
            this.breakProgress += p.field_70170_p.func_147439_a(this.cacheX, this.cacheY, this.cacheZ).func_149737_a(p, p.field_70170_p, this.cacheX, this.cacheY, this.cacheZ);
            if (this.breakProgress > 1.0f) {
                Minecraft.func_71410_x().func_147114_u().func_147297_a((Packet)new C07PacketPlayerDigging(2, p.func_145782_y(), this.cacheX, this.cacheY, this.cacheZ));
                Minecraft.func_71410_x().field_71442_b.func_78751_a(p.func_145782_y(), this.cacheX, this.cacheY, this.cacheZ);
                this.breakProgress = 0.0f;
                return;
            }
        }
        for (int i = -1; i < 2; ++i) {
            for (int j = -1; j < 2; ++j) {
                if (this.tempDir == ForgeDirection.UP || this.tempDir == ForgeDirection.DOWN) {
                    if (p.field_70170_p.func_147438_o(this.cacheX + i, this.cacheY, this.cacheZ + j) == null && p.field_70170_p.func_147439_a(this.cacheX + i, this.cacheY, this.cacheZ + j).func_149750_m() == 0 && p.field_70170_p.func_147439_a(this.cacheX + i, this.cacheY, this.cacheZ + j).func_149678_a(p.field_70170_p.func_72805_g(this.cacheX + i, this.cacheY, this.cacheZ + j), false)) {
                        if (p.field_70170_p.func_147439_a(this.cacheX + i, this.cacheY, this.cacheZ + j) != ThaumicHorizons.blockEvanescent) {
                            this.tempBlock[i + 1][j + 1] = p.field_70170_p.func_147439_a(this.cacheX + i, this.cacheY, this.cacheZ + j);
                            this.tempMD[i + 1][j + 1] = p.field_70170_p.func_72805_g(this.cacheX + i, this.cacheY, this.cacheZ + j);
                        }
                        if (this.tempBlock[i + 1][j + 1].func_149688_o() == Material.field_151579_a) continue;
                        p.field_70170_p.func_147449_b(this.cacheX + i, this.cacheY, this.cacheZ + j, ThaumicHorizons.blockEvanescent);
                        continue;
                    }
                    if (p.field_70170_p.func_147439_a(this.cacheX + i, this.cacheY, this.cacheZ + j) == ThaumicHorizons.blockEvanescent) continue;
                    this.tempBlock[i + 1][j + 1] = null;
                    continue;
                }
                if (this.tempDir == ForgeDirection.NORTH || this.tempDir == ForgeDirection.SOUTH) {
                    if (p.field_70170_p.func_147438_o(this.cacheX + i, this.cacheY + j, this.cacheZ) == null && p.field_70170_p.func_147439_a(this.cacheX + i, this.cacheY + j, this.cacheZ).func_149750_m() == 0 && p.field_70170_p.func_147439_a(this.cacheX + i, this.cacheY + j, this.cacheZ).func_149678_a(p.field_70170_p.func_72805_g(this.cacheX + i, this.cacheY + j, this.cacheZ), false)) {
                        if (p.field_70170_p.func_147439_a(this.cacheX + i, this.cacheY + j, this.cacheZ) != ThaumicHorizons.blockEvanescent) {
                            this.tempBlock[i + 1][j + 1] = p.field_70170_p.func_147439_a(this.cacheX + i, this.cacheY + j, this.cacheZ);
                            this.tempMD[i + 1][j + 1] = p.field_70170_p.func_72805_g(this.cacheX + i, this.cacheY + j, this.cacheZ);
                        }
                        if (this.tempBlock[i + 1][j + 1].func_149688_o() == Material.field_151579_a) continue;
                        p.field_70170_p.func_147449_b(this.cacheX + i, this.cacheY + j, this.cacheZ, ThaumicHorizons.blockEvanescent);
                        continue;
                    }
                    if (p.field_70170_p.func_147439_a(this.cacheX + i, this.cacheY + j, this.cacheZ) == ThaumicHorizons.blockEvanescent) continue;
                    this.tempBlock[i + 1][j + 1] = null;
                    continue;
                }
                if (p.field_70170_p.func_147438_o(this.cacheX, this.cacheY + j, this.cacheZ + i) == null && p.field_70170_p.func_147439_a(this.cacheX, this.cacheY + j, this.cacheZ + i).func_149750_m() == 0 && p.field_70170_p.func_147439_a(this.cacheX, this.cacheY + j, this.cacheZ + i).func_149678_a(p.field_70170_p.func_72805_g(this.cacheX, this.cacheY + j, this.cacheZ + i), false)) {
                    if (p.field_70170_p.func_147439_a(this.cacheX, this.cacheY + j, this.cacheZ + i) != ThaumicHorizons.blockEvanescent) {
                        this.tempBlock[i + 1][j + 1] = p.field_70170_p.func_147439_a(this.cacheX, this.cacheY + j, this.cacheZ + i);
                        this.tempMD[i + 1][j + 1] = p.field_70170_p.func_72805_g(this.cacheX, this.cacheY + j, this.cacheZ + i);
                    }
                    if (this.tempBlock[i + 1][j + 1].func_149688_o() == Material.field_151579_a) continue;
                    p.field_70170_p.func_147449_b(this.cacheX, this.cacheY + j, this.cacheZ + i, ThaumicHorizons.blockEvanescent);
                    continue;
                }
                if (p.field_70170_p.func_147439_a(this.cacheX, this.cacheY + j, this.cacheZ + i) == ThaumicHorizons.blockEvanescent) continue;
                this.tempBlock[i + 1][j + 1] = null;
            }
        }
        this.evanescentStage = 2;
    }

    public void resetBlocks(EntityPlayer p) {
        this.breakProgress = 0.0f;
        for (int i = -1; i < 2; ++i) {
            for (int j = -1; j < 2; ++j) {
                if (this.tempDir == ForgeDirection.UP || this.tempDir == ForgeDirection.DOWN) {
                    if (this.tempBlock[i + 1][j + 1] == null || p.field_70170_p.func_147439_a(this.cacheX + i, this.cacheY, this.cacheZ + j) != ThaumicHorizons.blockEvanescent) continue;
                    p.field_70170_p.func_147465_d(this.cacheX + i, this.cacheY, this.cacheZ + j, this.tempBlock[i + 1][j + 1], this.tempMD[i + 1][j + 1], 4);
                    continue;
                }
                if (this.tempDir == ForgeDirection.NORTH || this.tempDir == ForgeDirection.SOUTH) {
                    if (this.tempBlock[i + 1][j + 1] == null || p.field_70170_p.func_147439_a(this.cacheX + i, this.cacheY + j, this.cacheZ) != ThaumicHorizons.blockEvanescent) continue;
                    p.field_70170_p.func_147465_d(this.cacheX + i, this.cacheY + j, this.cacheZ, this.tempBlock[i + 1][j + 1], this.tempMD[i + 1][j + 1], 4);
                    continue;
                }
                if (this.tempBlock[i + 1][j + 1] == null || p.field_70170_p.func_147439_a(this.cacheX, this.cacheY + j, this.cacheZ + i) != ThaumicHorizons.blockEvanescent) continue;
                p.field_70170_p.func_147465_d(this.cacheX, this.cacheY + j, this.cacheZ + i, this.tempBlock[i + 1][j + 1], this.tempMD[i + 1][j + 1], 4);
            }
        }
        this.tempBlock = new Block[3][3];
        this.tempMD = new int[3][3];
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void renderTick(TickEvent.RenderTickEvent event) {
        ILens theLens;
        Minecraft mc = Minecraft.func_71410_x();
        if (mc.field_71439_g == null) {
            return;
        }
        ItemStack goggles = mc.field_71439_g.field_71071_by.func_70440_f(3);
        if (goggles != null && goggles.func_77973_b() instanceof IRevealer && goggles.field_77990_d != null && goggles.field_77990_d.func_74779_i("Lens") != null && !goggles.field_77990_d.func_74779_i("Lens").equals("") && (theLens = (ILens)LensManager.getLens(goggles.field_77990_d.func_74779_i("Lens"))) == ThaumicHorizons.itemLensEarth) {
            this.setBlocksEvanescent((EntityPlayer)mc.field_71439_g);
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void renderLast(RenderWorldLastEvent event) {
        for (EntityLivingBase entity : this.thingsThatSparkle) {
            if (!(Minecraft.func_71410_x().field_71439_g.func_70068_e((Entity)entity) < 32.0) || !(entity.field_70170_p.field_73012_v.nextFloat() > 0.95f)) continue;
            float angle = (float)((double)(entity.field_70170_p.field_73012_v.nextFloat() * 2.0f) * Math.PI);
            Thaumcraft.proxy.sparkle((float)entity.field_70165_t + entity.field_70130_N * (float)Math.cos(angle), (float)entity.field_70163_u + entity.field_70131_O * (entity.field_70170_p.field_73012_v.nextFloat() - 0.1f) * 1.2f, (float)entity.field_70161_v + entity.field_70130_N * (float)Math.sin(angle), 2.0f, 7, 0.0f);
        }
        if (this.evanescentStage == 2) {
            float temp = this.breakProgress;
            this.resetBlocks((EntityPlayer)Minecraft.func_71410_x().field_71439_g);
            this.breakProgress = temp;
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void clearWater(RenderBlockOverlayEvent event) {
        ILens theLens;
        Minecraft mc = Minecraft.func_71410_x();
        ItemStack goggles = mc.field_71439_g.field_71071_by.func_70440_f(3);
        if (goggles != null && goggles.func_77973_b() instanceof IRevealer && goggles.field_77990_d != null && goggles.field_77990_d.func_74779_i("Lens") != null && !goggles.field_77990_d.func_74779_i("Lens").equals("") && (theLens = (ILens)LensManager.getLens(goggles.field_77990_d.func_74779_i("Lens"))) == ThaumicHorizons.itemLensWater) {
            event.setCanceled(true);
        }
        if (event.overlayType == RenderBlockOverlayEvent.OverlayType.FIRE && mc.field_71439_g.field_70154_o != null && mc.field_71439_g.field_70154_o instanceof EntityBoatThaumium) {
            event.setCanceled(true);
        }
    }
}

