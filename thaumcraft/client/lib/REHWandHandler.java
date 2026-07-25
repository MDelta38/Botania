/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaublesApi
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.Facing
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.DrawBlockHighlightEvent
 *  net.minecraftforge.client.event.RenderGameOverlayEvent
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.Display
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.lib;

import baubles.api.BaublesApi;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.BlockCoordinates;
import thaumcraft.api.IArchitect;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.blocks.BlockCosmeticOpaque;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.wands.ItemFocusPouch;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.events.KeyHandler;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.misc.PacketFocusChangeToServer;

public class REHWandHandler {
    static float radialHudScale = 0.0f;
    TreeMap<String, Integer> foci = new TreeMap();
    HashMap<String, ItemStack> fociItem = new HashMap();
    HashMap<String, Boolean> fociHover = new HashMap();
    HashMap<String, Float> fociScale = new HashMap();
    long lastTime = 0L;
    boolean lastState = false;
    RenderBlocks renderBlocks = new RenderBlocks();
    int lastArcHash = 0;
    ArrayList<BlockCoordinates> architectBlocks = new ArrayList();
    String tex = "textures/misc/architect_arrows.png";

    @SideOnly(value=Side.CLIENT)
    public void handleFociRadial(Minecraft mc, long time, RenderGameOverlayEvent event) {
        if (KeyHandler.radialActive || radialHudScale > 0.0f) {
            long timeDiff = System.currentTimeMillis() - KeyHandler.lastPressF;
            if (KeyHandler.radialActive) {
                if (mc.field_71462_r != null) {
                    KeyHandler.radialActive = false;
                    KeyHandler.radialLock = true;
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
                        if (baubles.func_70301_a(a) == null || !(baubles.func_70301_a(a).func_77973_b() instanceof ItemFocusPouch)) continue;
                        ++pouchcount;
                        item = baubles.func_70301_a(a);
                        inv = ((ItemFocusPouch)item.func_77973_b()).getInventory(item);
                        for (q = 0; q < inv.length; ++q) {
                            item = inv[q];
                            if (item == null || !(item.func_77973_b() instanceof ItemFocusBasic)) continue;
                            this.foci.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), q + pouchcount * 1000);
                            this.fociItem.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), item.func_77946_l());
                            this.fociScale.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), Float.valueOf(1.0f));
                            this.fociHover.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), false);
                        }
                    }
                    for (a = 0; a < 36; ++a) {
                        item = mc.field_71439_g.field_71071_by.field_70462_a[a];
                        if (item != null && item.func_77973_b() instanceof ItemFocusBasic) {
                            this.foci.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), a);
                            this.fociItem.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), item.func_77946_l());
                            this.fociScale.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), Float.valueOf(1.0f));
                            this.fociHover.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), false);
                        }
                        if (item == null || !(item.func_77973_b() instanceof ItemFocusPouch)) continue;
                        ++pouchcount;
                        inv = ((ItemFocusPouch)item.func_77973_b()).getInventory(item);
                        for (q = 0; q < inv.length; ++q) {
                            item = inv[q];
                            if (item == null || !(item.func_77973_b() instanceof ItemFocusBasic)) continue;
                            this.foci.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), q + pouchcount * 1000);
                            this.fociItem.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), item.func_77946_l());
                            this.fociScale.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), Float.valueOf(1.0f));
                            this.fociHover.put(((ItemFocusBasic)item.func_77973_b()).getSortingHelper(item), false);
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
                        if (!KeyHandler.radialActive && !KeyHandler.radialLock) {
                            PacketHandler.INSTANCE.sendToServer((IMessage)new PacketFocusChangeToServer((EntityPlayer)mc.field_71439_g, key));
                            KeyHandler.radialLock = true;
                        }
                        if (!(this.fociScale.get(key).floatValue() < 1.3f)) continue;
                        this.fociScale.put(key, Float.valueOf(this.fociScale.get(key).floatValue() + 0.025f));
                        continue;
                    }
                    if (!(this.fociScale.get(key).floatValue() > 1.0f)) continue;
                    this.fociScale.put(key, Float.valueOf(this.fociScale.get(key).floatValue() - 0.025f));
                }
                if (!KeyHandler.radialActive) {
                    radialHudScale -= 0.05f;
                } else if (KeyHandler.radialActive && radialHudScale < 1.0f) {
                    radialHudScale += 0.05f;
                }
                if (radialHudScale > 1.0f) {
                    radialHudScale = 1.0f;
                }
                if (radialHudScale < 0.0f) {
                    radialHudScale = 0.0f;
                    KeyHandler.radialLock = false;
                }
                this.lastTime = time + 5L;
                this.lastState = KeyHandler.radialActive;
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    private void renderFocusRadialHUD(double sw, double sh, long time, float partialTicks) {
        RenderItem ri = new RenderItem();
        Minecraft mc = Minecraft.func_71410_x();
        if (mc.field_71439_g.func_71045_bC() == null || !(mc.field_71439_g.func_71045_bC().func_77973_b() instanceof ItemWandCasting)) {
            return;
        }
        ItemWandCasting wand = (ItemWandCasting)mc.field_71439_g.func_71045_bC().func_77973_b();
        ItemFocusBasic focus = wand.getFocus(mc.field_71439_g.func_71045_bC());
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
        UtilsFX.bindTexture("textures/misc/radial.png");
        GL11.glPushMatrix();
        GL11.glRotatef((float)(partialTicks + (float)(mc.field_71439_g.field_70173_aa % 720) / 2.0f), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        UtilsFX.renderQuadCenteredFromTexture(width * 2.75f * radialHudScale, 0.5f, 0.5f, 0.5f, 200, 771, 0.5f);
        GL11.glDisable((int)3042);
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glPopMatrix();
        UtilsFX.bindTexture("textures/misc/radial2.png");
        GL11.glPushMatrix();
        GL11.glRotatef((float)(-(partialTicks + (float)(mc.field_71439_g.field_70173_aa % 720) / 2.0f)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        UtilsFX.renderQuadCenteredFromTexture(width * 2.55f * radialHudScale, 0.5f, 0.5f, 0.5f, 200, 771, 0.5f);
        GL11.glDisable((int)3042);
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glPopMatrix();
        if (focus != null) {
            GL11.glPushMatrix();
            GL11.glEnable((int)32826);
            RenderHelper.func_74520_c();
            ItemStack item = wand.getFocusItem(mc.field_71439_g.func_71045_bC()).func_77946_l();
            item.field_77990_d = null;
            ri.func_77015_a(mc.field_71466_p, mc.field_71446_o, item, -8, -8);
            RenderHelper.func_74518_a();
            GL11.glDisable((int)32826);
            GL11.glPopMatrix();
            int mx = (int)((double)i - sw / 2.0);
            int my = (int)((double)j - sh / 2.0);
            if (mx >= -10 && mx <= 10 && my >= -10 && my <= 10) {
                tt = wand.getFocusItem(mc.field_71439_g.func_71045_bC());
            }
        }
        GL11.glScaled((double)radialHudScale, (double)radialHudScale, (double)radialHudScale);
        float currentRot = -90.0f * radialHudScale;
        float pieSlice = 360.0f / (float)this.fociItem.size();
        String key = this.foci.firstKey();
        for (int a = 0; a < this.fociItem.size(); ++a) {
            double xx = MathHelper.func_76134_b((float)(currentRot / 180.0f * (float)Math.PI)) * width;
            double yy = MathHelper.func_76126_a((float)(currentRot / 180.0f * (float)Math.PI)) * width;
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
            if (!KeyHandler.radialLock && KeyHandler.radialActive) {
                int mx = (int)((double)i - sw / 2.0 - xx);
                int my = (int)((double)j - sh / 2.0 - yy);
                if (mx >= -10 && mx <= 10 && my >= -10 && my <= 10) {
                    this.fociHover.put(key, true);
                    tt = this.fociItem.get(key);
                    if (k == 0) {
                        KeyHandler.radialActive = false;
                        KeyHandler.radialLock = true;
                        PacketHandler.INSTANCE.sendToServer((IMessage)new PacketFocusChangeToServer((EntityPlayer)mc.field_71439_g, key));
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
            UtilsFX.drawCustomTooltip(mc.field_71462_r, ri, mc.field_71466_p, tt.func_82840_a((EntityPlayer)mc.field_71439_g, mc.field_71474_y.field_82882_x), -4, 20, 11);
        }
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2929);
        GL11.glDisable((int)3042);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
    }

    @SideOnly(value=Side.CLIENT)
    public boolean handleArchitectOverlay(ItemStack stack, DrawBlockHighlightEvent event, int playerticks, MovingObjectPosition target) {
        Minecraft mc = Minecraft.func_71410_x();
        IArchitect af = (IArchitect)stack.func_77973_b();
        String h = target.field_72311_b + "" + target.field_72312_c + "" + target.field_72309_d + "" + target.field_72310_e + "" + playerticks / 5;
        int hc = h.hashCode();
        if (hc != this.lastArcHash) {
            this.lastArcHash = hc;
            this.architectBlocks = af.getArchitectBlocks(stack, (World)mc.field_71441_e, target.field_72311_b, target.field_72312_c, target.field_72309_d, target.field_72310_e, event.player);
        }
        if (this.architectBlocks == null || this.architectBlocks.size() == 0) {
            return false;
        }
        this.drawArchitectAxis(target.field_72311_b, target.field_72312_c, target.field_72309_d, event.partialTicks, af.showAxis(stack, (World)mc.field_71441_e, event.player, target.field_72310_e, IArchitect.EnumAxis.X), af.showAxis(stack, (World)mc.field_71441_e, event.player, target.field_72310_e, IArchitect.EnumAxis.Y), af.showAxis(stack, (World)mc.field_71441_e, event.player, target.field_72310_e, IArchitect.EnumAxis.Z));
        for (BlockCoordinates cc : this.architectBlocks) {
            this.drawOverlayBlock(cc.x, cc.y, cc.z, playerticks, mc, event.partialTicks);
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        return true;
    }

    private boolean isConnectedBlock(World world, int x, int y, int z) {
        return this.architectBlocks.contains(new BlockCoordinates(x, y, z));
    }

    @SideOnly(value=Side.CLIENT)
    private IIcon getIconOnSide(World world, int x, int y, int z, int side, int ticks) {
        IIcon iIcon;
        boolean[] bitMatrix = new boolean[8];
        if (side == 0 || side == 1) {
            bitMatrix[0] = this.isConnectedBlock(world, x - 1, y, z - 1);
            bitMatrix[1] = this.isConnectedBlock(world, x, y, z - 1);
            bitMatrix[2] = this.isConnectedBlock(world, x + 1, y, z - 1);
            bitMatrix[3] = this.isConnectedBlock(world, x - 1, y, z);
            bitMatrix[4] = this.isConnectedBlock(world, x + 1, y, z);
            bitMatrix[5] = this.isConnectedBlock(world, x - 1, y, z + 1);
            bitMatrix[6] = this.isConnectedBlock(world, x, y, z + 1);
            bitMatrix[7] = this.isConnectedBlock(world, x + 1, y, z + 1);
        }
        if (side == 2 || side == 3) {
            bitMatrix[0] = this.isConnectedBlock(world, x + (side == 2 ? 1 : -1), y + 1, z);
            bitMatrix[1] = this.isConnectedBlock(world, x, y + 1, z);
            bitMatrix[2] = this.isConnectedBlock(world, x + (side == 3 ? 1 : -1), y + 1, z);
            bitMatrix[3] = this.isConnectedBlock(world, x + (side == 2 ? 1 : -1), y, z);
            bitMatrix[4] = this.isConnectedBlock(world, x + (side == 3 ? 1 : -1), y, z);
            bitMatrix[5] = this.isConnectedBlock(world, x + (side == 2 ? 1 : -1), y - 1, z);
            bitMatrix[6] = this.isConnectedBlock(world, x, y - 1, z);
            bitMatrix[7] = this.isConnectedBlock(world, x + (side == 3 ? 1 : -1), y - 1, z);
        }
        if (side == 4 || side == 5) {
            bitMatrix[0] = this.isConnectedBlock(world, x, y + 1, z + (side == 5 ? 1 : -1));
            bitMatrix[1] = this.isConnectedBlock(world, x, y + 1, z);
            bitMatrix[2] = this.isConnectedBlock(world, x, y + 1, z + (side == 4 ? 1 : -1));
            bitMatrix[3] = this.isConnectedBlock(world, x, y, z + (side == 5 ? 1 : -1));
            bitMatrix[4] = this.isConnectedBlock(world, x, y, z + (side == 4 ? 1 : -1));
            bitMatrix[5] = this.isConnectedBlock(world, x, y - 1, z + (side == 5 ? 1 : -1));
            bitMatrix[6] = this.isConnectedBlock(world, x, y - 1, z);
            bitMatrix[7] = this.isConnectedBlock(world, x, y - 1, z + (side == 4 ? 1 : -1));
        }
        int idBuilder = 0;
        for (int i = 0; i <= 7; ++i) {
            idBuilder += bitMatrix[i] ? (i == 0 ? 1 : (i == 1 ? 2 : (i == 2 ? 4 : (i == 3 ? 8 : (i == 4 ? 16 : (i == 5 ? 32 : (i == 6 ? 64 : 128))))))) : 0;
        }
        if (idBuilder > 255 || idBuilder < 0) {
            BlockCosmeticOpaque cfr_ignored_0 = (BlockCosmeticOpaque)ConfigBlocks.blockCosmeticOpaque;
            iIcon = BlockCosmeticOpaque.wardedGlassIcon[0];
        } else {
            BlockCosmeticOpaque cfr_ignored_1 = (BlockCosmeticOpaque)ConfigBlocks.blockCosmeticOpaque;
            iIcon = BlockCosmeticOpaque.wardedGlassIcon[UtilsFX.connectedTextureRefByID[idBuilder]];
        }
        return iIcon;
    }

    private boolean shouldSideBeRendered(int x, int y, int z, int side) {
        return !this.architectBlocks.contains(new BlockCoordinates(x - Facing.field_71586_b[side], y - Facing.field_71587_c[side], z - Facing.field_71585_d[side]));
    }

    @SideOnly(value=Side.CLIENT)
    public void drawOverlayBlock(int x, int y, int z, int ticks, Minecraft mc, float partialTicks) {
        GL11.glPushMatrix();
        GL11.glDepthMask((boolean)false);
        GL11.glDisable((int)2929);
        GL11.glDisable((int)2884);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        EntityPlayer player = (EntityPlayer)mc.field_71451_h;
        double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * (double)partialTicks;
        double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * (double)partialTicks;
        double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * (double)partialTicks;
        GL11.glTranslated((double)(-iPX + (double)x + 0.5), (double)(-iPY + (double)y), (double)(-iPZ + (double)z + 0.5));
        GL11.glDisable((int)2896);
        Tessellator t = Tessellator.field_78398_a;
        this.renderBlocks.func_147782_a((double)-0.001f, (double)-0.001f, (double)-0.001f, (double)1.001f, (double)1.001f, (double)1.001f);
        float r = MathHelper.func_76126_a((float)((float)ticks / 2.0f + (float)x)) * 0.2f + 0.3f;
        float g = MathHelper.func_76126_a((float)((float)ticks / 3.0f + (float)y)) * 0.2f + 0.3f;
        float b = MathHelper.func_76126_a((float)((float)ticks / 4.0f + (float)z)) * 0.2f + 0.8f;
        GL11.glColor4f((float)r, (float)g, (float)b, (float)0.2f);
        t.func_78382_b();
        t.func_78380_c(200);
        mc.field_71446_o.func_110577_a(TextureMap.field_110575_b);
        GL11.glTexEnvi((int)8960, (int)8704, (int)260);
        if (this.shouldSideBeRendered(x, y, z, 1)) {
            this.renderBlocks.func_147768_a(ConfigBlocks.blockJar, -0.5, 0.0, -0.5, this.getIconOnSide((World)mc.field_71441_e, x, y, z, 0, ticks));
        }
        if (this.shouldSideBeRendered(x, y, z, 0)) {
            this.renderBlocks.func_147806_b(ConfigBlocks.blockJar, -0.5, 0.0, -0.5, this.getIconOnSide((World)mc.field_71441_e, x, y, z, 1, ticks));
        }
        if (this.shouldSideBeRendered(x, y, z, 3)) {
            this.renderBlocks.func_147761_c(ConfigBlocks.blockJar, -0.5, 0.0, -0.5, this.getIconOnSide((World)mc.field_71441_e, x, y, z, 2, ticks));
        }
        if (this.shouldSideBeRendered(x, y, z, 2)) {
            this.renderBlocks.func_147734_d(ConfigBlocks.blockJar, -0.5, 0.0, -0.5, this.getIconOnSide((World)mc.field_71441_e, x, y, z, 3, ticks));
        }
        if (this.shouldSideBeRendered(x, y, z, 5)) {
            this.renderBlocks.func_147798_e(ConfigBlocks.blockJar, -0.5, 0.0, -0.5, this.getIconOnSide((World)mc.field_71441_e, x, y, z, 4, ticks));
        }
        if (this.shouldSideBeRendered(x, y, z, 4)) {
            this.renderBlocks.func_147764_f(ConfigBlocks.blockJar, -0.5, 0.0, -0.5, this.getIconOnSide((World)mc.field_71441_e, x, y, z, 5, ticks));
        }
        t.func_78381_a();
        GL11.glTexEnvi((int)8960, (int)8704, (int)8448);
        GL11.glEnable((int)2896);
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glDisable((int)3042);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)2884);
        GL11.glEnable((int)2929);
        GL11.glDepthMask((boolean)true);
        GL11.glPopMatrix();
    }

    @SideOnly(value=Side.CLIENT)
    public void drawArchitectAxis(double x, double y, double z, float partialTicks, boolean dx, boolean dy, boolean dz) {
        if (!(dx || dy || dz)) {
            return;
        }
        EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().field_71451_h;
        double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * (double)partialTicks;
        double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * (double)partialTicks;
        double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * (double)partialTicks;
        float r = MathHelper.func_76126_a((float)((float)((double)((float)player.field_70173_aa / 4.0f) + x))) * 0.2f + 0.3f;
        float g = MathHelper.func_76126_a((float)((float)((double)((float)player.field_70173_aa / 3.0f) + y))) * 0.2f + 0.3f;
        float b = MathHelper.func_76126_a((float)((float)((double)((float)player.field_70173_aa / 2.0f) + z))) * 0.2f + 0.8f;
        GL11.glPushMatrix();
        GL11.glDepthMask((boolean)false);
        GL11.glDisable((int)2929);
        GL11.glDisable((int)2884);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        GL11.glTranslated((double)(-iPX + x + 0.5), (double)(-iPY + y + 0.5), (double)(-iPZ + z + 0.5));
        GL11.glPushMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.33f);
        GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        if (dx) {
            GL11.glPushMatrix();
            UtilsFX.renderQuadCenteredFromTexture(this.tex, 1.0f, r, g, b, 200, 1, 1.0f);
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            UtilsFX.renderQuadCenteredFromTexture(this.tex, 1.0f, r, g, b, 200, 1, 1.0f);
            GL11.glPopMatrix();
        }
        if (dz) {
            GL11.glPushMatrix();
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            UtilsFX.renderQuadCenteredFromTexture(this.tex, 1.0f, r, g, b, 200, 1, 1.0f);
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            UtilsFX.renderQuadCenteredFromTexture(this.tex, 1.0f, r, g, b, 200, 1, 1.0f);
            GL11.glPopMatrix();
        }
        if (dy) {
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            UtilsFX.renderQuadCenteredFromTexture(this.tex, 1.0f, r, g, b, 200, 1, 1.0f);
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            UtilsFX.renderQuadCenteredFromTexture(this.tex, 1.0f, r, g, b, 200, 1, 1.0f);
        }
        GL11.glPopMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2884);
        GL11.glEnable((int)2929);
        GL11.glDepthMask((boolean)true);
        GL11.glPopMatrix();
    }
}

