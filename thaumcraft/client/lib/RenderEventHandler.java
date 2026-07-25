/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.shader.ShaderGroup
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityNote
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.DrawBlockHighlightEvent
 *  net.minecraftforge.client.event.EntityViewRenderEvent$RenderFogEvent
 *  net.minecraftforge.client.event.RenderGameOverlayEvent
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Pre
 *  net.minecraftforge.client.event.RenderPlayerEvent$Specials$Pre
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 *  net.minecraftforge.oredict.OreDictionary
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.lib;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityNote;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.oredict.OreDictionary;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.IArchitect;
import thaumcraft.api.IGoggles;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.research.ScanResult;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.client.lib.REHNotifyHandler;
import thaumcraft.client.lib.REHWandHandler;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.tile.TileNodeRenderer;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.entities.golems.ItemGolemBell;
import thaumcraft.common.entities.golems.ItemGolemPlacer;
import thaumcraft.common.entities.monster.mods.ChampionModifier;
import thaumcraft.common.items.armor.ItemFortressArmor;
import thaumcraft.common.items.armor.ItemVoidRobeArmor;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.misc.PacketNote;
import thaumcraft.common.lib.research.ScanManager;
import thaumcraft.common.lib.utils.EntityUtils;
import thaumcraft.common.tiles.TileSensor;
import thaumcraft.common.tiles.TileWandPedestal;
import truetyper.FontLoader;
import truetyper.TrueTypeFont;

public class RenderEventHandler {
    TrueTypeFont font = null;
    public static List blockTags = new ArrayList();
    int q = 0;
    public static float tagscale = 0.0f;
    public long scanCount = 0L;
    public int scanX = 0;
    public int scanY = 0;
    public int scanZ = 0;
    int[][][] scannedBlocks = new int[17][17][17];
    @SideOnly(value=Side.CLIENT)
    public REHWandHandler wandHandler;
    @SideOnly(value=Side.CLIENT)
    public REHNotifyHandler notifyHandler;
    public static boolean resetShaders = false;
    private static int oldDisplayWidth = 0;
    private static int oldDisplayHeight = 0;
    public static HashMap<Integer, ShaderGroup> shaderGroups = new HashMap();
    public static boolean fogFiddled = false;
    public static float fogTarget = 0.0f;
    public static int fogDuration = 0;
    public static float prevVignetteBrightness = 0.0f;
    public static float targetBrightness = 1.0f;
    protected static final ResourceLocation vignetteTexPath = new ResourceLocation("thaumcraft", "textures/misc/vignette.png");

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent event) {
        if (this.font == null) {
            this.font = FontLoader.loadSystemFont("Arial", 12.0f, true);
        }
        Minecraft mc = Minecraft.func_71410_x();
        long time = System.nanoTime() / 1000000L;
        if (this.wandHandler == null) {
            this.wandHandler = new REHWandHandler();
        }
        if (this.notifyHandler == null) {
            this.notifyHandler = new REHNotifyHandler();
        }
        if (event.type == RenderGameOverlayEvent.ElementType.TEXT) {
            this.notifyHandler.handleNotifications(mc, time, event);
            this.wandHandler.handleFociRadial(mc, time, event);
        }
        if (event.type == RenderGameOverlayEvent.ElementType.PORTAL) {
            this.renderVignette(targetBrightness, event.resolution.func_78327_c(), event.resolution.func_78324_d());
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void renderShaders(RenderGameOverlayEvent.Pre event) {
        if (Config.shaders && event.type == RenderGameOverlayEvent.ElementType.ALL) {
            Minecraft mc = Minecraft.func_71410_x();
            long time = System.nanoTime() / 1000000L;
            if (OpenGlHelper.field_148824_g) {
                if (shaderGroups.size() > 0) {
                    this.updateShaderFrameBuffers(mc);
                    GL11.glMatrixMode((int)5890);
                    GL11.glLoadIdentity();
                    for (ShaderGroup sg : shaderGroups.values()) {
                        GL11.glPushMatrix();
                        try {
                            sg.func_148018_a(event.partialTicks);
                        }
                        catch (Exception e) {
                            // empty catch block
                        }
                        GL11.glPopMatrix();
                    }
                    mc.func_147110_a().func_147610_a(true);
                }
            }
        }
    }

    private void updateShaderFrameBuffers(Minecraft mc) {
        if (resetShaders || mc.field_71443_c != oldDisplayWidth || oldDisplayHeight != mc.field_71440_d) {
            for (ShaderGroup sg : shaderGroups.values()) {
                sg.func_148026_a(mc.field_71443_c, mc.field_71440_d);
            }
            oldDisplayWidth = mc.field_71443_c;
            oldDisplayHeight = mc.field_71440_d;
            resetShaders = false;
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void blockHighlight(DrawBlockHighlightEvent event) {
        int ticks = event.player.field_70173_aa;
        MovingObjectPosition target = event.target;
        if (blockTags.size() > 0) {
            int x = (Integer)blockTags.get(0);
            int y = (Integer)blockTags.get(1);
            int z = (Integer)blockTags.get(2);
            AspectList ot = (AspectList)blockTags.get(3);
            ForgeDirection dir = ForgeDirection.getOrientation((int)((Integer)blockTags.get(4)));
            if (x == target.field_72311_b && y == target.field_72312_c && z == target.field_72309_d) {
                if (tagscale < 0.5f) {
                    tagscale += 0.031f - tagscale / 10.0f;
                }
                this.drawTagsOnContainer((float)target.field_72311_b + (float)dir.offsetX / 2.0f, (float)target.field_72312_c + (float)dir.offsetY / 2.0f, (float)target.field_72309_d + (float)dir.offsetZ / 2.0f, ot, 220, dir, event.partialTicks);
            }
        }
        if (event.player.field_71071_by.func_70440_f(3) != null && event.player.field_71071_by.func_70440_f(3).func_77973_b() instanceof IGoggles && ((IGoggles)event.player.field_71071_by.func_70440_f(3).func_77973_b()).showIngamePopups(event.player.field_71071_by.func_70440_f(3), (EntityLivingBase)event.player)) {
            boolean spaceAbove = event.player.field_70170_p.func_147437_c(target.field_72311_b, target.field_72312_c + 1, target.field_72309_d);
            TileEntity te = event.player.field_70170_p.func_147438_o(target.field_72311_b, target.field_72312_c, target.field_72309_d);
            if (te != null) {
                int note = -1;
                if (te instanceof TileEntityNote) {
                    note = ((TileEntityNote)te).field_145879_a;
                } else if (te instanceof TileSensor) {
                    note = ((TileSensor)te).note;
                } else if (te instanceof IAspectContainer && ((IAspectContainer)te).getAspects() != null && ((IAspectContainer)te).getAspects().size() > 0) {
                    float shift = 0.0f;
                    if (te instanceof TileWandPedestal) {
                        shift = 0.6f;
                    }
                    if (tagscale < 0.3f) {
                        tagscale += 0.031f - tagscale / 10.0f;
                    }
                    this.drawTagsOnContainer(target.field_72311_b, (float)target.field_72312_c + (spaceAbove ? 0.4f : 0.0f) + shift, target.field_72309_d, ((IAspectContainer)te).getAspects(), 220, spaceAbove ? ForgeDirection.UP : ForgeDirection.getOrientation((int)event.target.field_72310_e), event.partialTicks);
                }
                if (note >= 0) {
                    if (ticks % 5 == 0) {
                        PacketHandler.INSTANCE.sendToServer((IMessage)new PacketNote(target.field_72311_b, target.field_72312_c, target.field_72309_d, event.player.field_70170_p.field_73011_w.field_76574_g));
                    }
                    this.drawTextInAir(target.field_72311_b, target.field_72312_c + 1, target.field_72309_d, event.partialTicks, "Note: " + note);
                }
            }
        }
        if (this.wandHandler == null) {
            this.wandHandler = new REHWandHandler();
        }
        if (target.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && event.player.func_70694_bm() != null && event.player.func_70694_bm().func_77973_b() instanceof IArchitect && !(event.player.func_70694_bm().func_77973_b() instanceof ItemFocusBasic) && this.wandHandler.handleArchitectOverlay(event.player.func_70694_bm(), event, ticks, target)) {
            event.setCanceled(true);
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void renderLast(RenderWorldLastEvent event) {
        if (tagscale > 0.0f) {
            tagscale -= 0.005f;
        }
        float partialTicks = event.partialTicks;
        Minecraft mc = Minecraft.func_71410_x();
        if (Minecraft.func_71410_x().field_71451_h instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().field_71451_h;
            long time = System.currentTimeMillis();
            if (player.field_71071_by.func_70448_g() != null && (player.field_71071_by.func_70448_g().func_77973_b() instanceof ItemGolemPlacer || player.field_71071_by.func_70448_g().func_77973_b() instanceof ItemGolemBell)) {
                this.renderMarkedBlocks(event, partialTicks, player, time);
            }
            if (this.scanCount > time) {
                this.showScannedBlocks(partialTicks, player, time);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void fogDensityEvent(EntityViewRenderEvent.RenderFogEvent event) {
        if (fogFiddled && fogTarget > 0.0f) {
            GL11.glFogi((int)2917, (int)2048);
            GL11.glFogf((int)2914, (float)fogTarget);
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void renderPlayerSpecialsEvent(RenderPlayerEvent.Specials.Pre event) {
        if (event.entityPlayer != null && event.entityPlayer.field_71071_by.field_70460_b[2] != null && (event.entityPlayer.field_71071_by.field_70460_b[2].func_77973_b() instanceof ItemFortressArmor || event.entityPlayer.field_71071_by.field_70460_b[2].func_77973_b() instanceof ItemVoidRobeArmor)) {
            event.renderCape = false;
        }
    }

    public void drawTagsOnContainer(double x, double y, double z, AspectList tags, int bright, ForgeDirection dir, float partialTicks) {
        if (Minecraft.func_71410_x().field_71451_h instanceof EntityPlayer && tags != null && tags.size() > 0) {
            EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().field_71451_h;
            double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * (double)partialTicks;
            double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * (double)partialTicks;
            double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * (double)partialTicks;
            boolean e = false;
            int rowsize = 5;
            int current = 0;
            float shifty = 0.0f;
            int left = tags.size();
            for (Aspect tag : tags.getAspects()) {
                int div = Math.min(left, rowsize);
                if (current >= rowsize) {
                    current = 0;
                    shifty -= tagscale * 1.05f;
                    if ((left -= rowsize) < rowsize) {
                        div = left % rowsize;
                    }
                }
                float shift = ((float)current - (float)div / 2.0f + 0.5f) * tagscale * 4.0f;
                shift *= tagscale;
                Color color = new Color(tag.getColor());
                GL11.glPushMatrix();
                GL11.glDisable((int)2929);
                GL11.glTranslated((double)(-iPX + x + 0.5 + (double)(tagscale * 2.0f * (float)dir.offsetX)), (double)(-iPY + y - (double)shifty + 0.5 + (double)(tagscale * 2.0f * (float)dir.offsetY)), (double)(-iPZ + z + 0.5 + (double)(tagscale * 2.0f * (float)dir.offsetZ)));
                float xd = (float)(iPX - (x + 0.5));
                float zd = (float)(iPZ - (z + 0.5));
                float rotYaw = (float)(Math.atan2(xd, zd) * 180.0 / Math.PI);
                GL11.glRotatef((float)(rotYaw + 180.0f), (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslated((double)shift, (double)0.0, (double)0.0);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glScalef((float)tagscale, (float)tagscale, (float)tagscale);
                if (!Thaumcraft.proxy.playerKnowledge.hasDiscoveredAspect(player.func_70005_c_(), tag)) {
                    UtilsFX.renderQuadCenteredFromTexture("textures/aspects/_unknown.png", 1.0f, (float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, bright, 771, 0.75f);
                    color = new Color(0xAAAAAA);
                } else {
                    UtilsFX.renderQuadCenteredFromTexture(tag.getImage(), 1.0f, (float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, bright, 771, 0.75f);
                }
                if (tags.getAmount(tag) >= 0) {
                    String am = "" + tags.getAmount(tag);
                    GL11.glScalef((float)0.04f, (float)0.04f, (float)0.04f);
                    GL11.glTranslated((double)0.0, (double)6.0, (double)-0.1);
                    int sw = Minecraft.func_71410_x().field_71466_p.func_78256_a(am);
                    GL11.glEnable((int)3042);
                    Minecraft.func_71410_x().field_71466_p.func_78276_b(am, 14 - sw, 1, 0x111111);
                    GL11.glTranslated((double)0.0, (double)0.0, (double)-0.1);
                    Minecraft.func_71410_x().field_71466_p.func_78276_b(am, 13 - sw, 0, 0xFFFFFF);
                }
                GL11.glEnable((int)2929);
                GL11.glPopMatrix();
                ++current;
            }
        }
    }

    public void drawTextInAir(double x, double y, double z, float partialTicks, String text) {
        if (Minecraft.func_71410_x().field_71451_h instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().field_71451_h;
            double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * (double)partialTicks;
            double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * (double)partialTicks;
            double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * (double)partialTicks;
            GL11.glPushMatrix();
            GL11.glTranslated((double)(-iPX + x + 0.5), (double)(-iPY + y + 0.5), (double)(-iPZ + z + 0.5));
            float xd = (float)(iPX - (x + 0.5));
            float zd = (float)(iPZ - (z + 0.5));
            float rotYaw = (float)(Math.atan2(xd, zd) * 180.0 / Math.PI);
            GL11.glRotatef((float)(rotYaw + 180.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glScalef((float)0.02f, (float)0.02f, (float)0.02f);
            int sw = Minecraft.func_71410_x().field_71466_p.func_78256_a(text);
            GL11.glEnable((int)3042);
            Minecraft.func_71410_x().field_71466_p.func_78276_b(text, 1 - sw / 2, 1, 0x111111);
            GL11.glTranslated((double)0.0, (double)0.0, (double)-0.1);
            Minecraft.func_71410_x().field_71466_p.func_78276_b(text, -sw / 2, 0, 0xFFFFFF);
            GL11.glPopMatrix();
        }
    }

    public void startScan(Entity player, int x, int y, int z, long time, int range) {
        this.scannedBlocks = new int[17][17][17];
        this.scanX = x;
        this.scanY = y;
        this.scanZ = z;
        this.scanCount = time;
        for (int xx = -range; xx <= range; ++xx) {
            for (int yy = -range; yy <= range; ++yy) {
                for (int zz = -range; zz <= range; ++zz) {
                    int value = -1;
                    Block bi = player.field_70170_p.func_147439_a(x + xx, y + yy, z + zz);
                    if (bi != Blocks.field_150350_a && bi != Blocks.field_150357_h) {
                        if (bi.func_149688_o() == Material.field_151587_i) {
                            value = -10;
                        } else if (bi.func_149688_o() == Material.field_151586_h) {
                            value = -5;
                        } else {
                            int md = bi.func_149643_k(player.field_70170_p, x + xx, y + yy, z + zz);
                            int[] od = OreDictionary.getOreIDs((ItemStack)new ItemStack(bi, 1, md));
                            boolean ore = false;
                            if (od != null && od.length > 0) {
                                for (int id : od) {
                                    if (OreDictionary.getOreName((int)id) == null || !OreDictionary.getOreName((int)id).toUpperCase().contains("ORE")) continue;
                                    ore = true;
                                    value = 0;
                                    break;
                                }
                            }
                            if (ore) {
                                try {
                                    ScanResult scan = new ScanResult(1, Block.func_149682_b((Block)bi), md, null, "");
                                    value = ScanManager.getScanAspects(scan, player.field_70170_p).visSize();
                                }
                                catch (Exception e) {
                                    try {
                                        ScanResult scan = new ScanResult(1, Item.func_150891_b((Item)bi.func_149694_d(player.field_70170_p, x + xx, y + yy, z + zz)), bi.func_149643_k(player.field_70170_p, x + xx, y + yy, z + zz), null, "");
                                        value = ScanManager.getScanAspects(scan, player.field_70170_p).visSize();
                                    }
                                    catch (Exception e2) {
                                        // empty catch block
                                    }
                                }
                            }
                        }
                    }
                    this.scannedBlocks[xx + 8][yy + 8][zz + 8] = value;
                }
            }
        }
    }

    public void showScannedBlocks(float partialTicks, EntityPlayer player, long time) {
        Minecraft mc = Minecraft.func_71410_x();
        long dif = this.scanCount - time;
        GL11.glPushMatrix();
        GL11.glDepthMask((boolean)false);
        GL11.glDisable((int)2929);
        for (int xx = -8; xx <= 8; ++xx) {
            for (int yy = -8; yy <= 8; ++yy) {
                for (int zz = -8; zz <= 8; ++zz) {
                    int value = this.scannedBlocks[xx + 8][yy + 8][zz + 8];
                    float alpha = 1.0f;
                    if (dif > 4750L) {
                        alpha = 1.0f - (float)(dif - 4750L) / 5.0f;
                    }
                    if (dif < 1500L) {
                        alpha = (float)dif / 1500.0f;
                    }
                    float dist = 1.0f - (float)(xx * xx + yy * yy + zz * zz) / 64.0f;
                    alpha *= dist;
                    if (value == -5) {
                        this.drawSpecialBlockoverlay(this.scanX + xx, this.scanY + yy, this.scanZ + zz, partialTicks, 3986684, alpha);
                        continue;
                    }
                    if (value == -10) {
                        this.drawSpecialBlockoverlay(this.scanX + xx, this.scanY + yy, this.scanZ + zz, partialTicks, 16734721, alpha);
                        continue;
                    }
                    if (value < 0) continue;
                    GL11.glPushMatrix();
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)770, (int)1);
                    GL11.glAlphaFunc((int)516, (float)0.003921569f);
                    GL11.glDisable((int)2884);
                    UtilsFX.bindTexture(TileNodeRenderer.nodetex);
                    this.drawPickScannedObject(this.scanX + xx, this.scanY + yy, this.scanZ + zz, partialTicks, alpha, (int)(time / 50L % 32L), (float)value / 7.0f);
                    GL11.glAlphaFunc((int)516, (float)0.1f);
                    GL11.glDisable((int)3042);
                    GL11.glEnable((int)2884);
                    GL11.glPopMatrix();
                }
            }
        }
        GL11.glEnable((int)2929);
        GL11.glDepthMask((boolean)true);
        GL11.glPopMatrix();
    }

    @SideOnly(value=Side.CLIENT)
    public void drawPickScannedObject(double x, double y, double z, float partialTicks, float alpha, int cframe, float size) {
        GL11.glPushMatrix();
        UtilsFX.renderFacingStrip(x + 0.5, y + 0.5, z + 0.5, 0.0f, 0.2f * size, alpha, 32, 0, cframe, partialTicks, 0xAAAA11);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        UtilsFX.renderFacingStrip(x + 0.5, y + 0.5, z + 0.5, 0.0f, 0.5f * size, alpha, 32, 0, cframe, partialTicks, 0xAA1122);
        GL11.glPopMatrix();
    }

    public void drawSpecialBlockoverlay(double x, double y, double z, float partialTicks, int color, float alpha) {
        float r = 1.0f;
        float g = 1.0f;
        float b = 1.0f;
        EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().field_71451_h;
        double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * (double)partialTicks;
        double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * (double)partialTicks;
        double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * (double)partialTicks;
        float time = System.nanoTime() / 30000000L;
        Color cc = new Color(color);
        r = (float)cc.getRed() / 255.0f;
        g = (float)cc.getGreen() / 255.0f;
        b = (float)cc.getBlue() / 255.0f;
        for (int side = 0; side < 6; ++side) {
            GL11.glPushMatrix();
            ForgeDirection dir = ForgeDirection.getOrientation((int)side);
            GL11.glTranslated((double)(-iPX + x + 0.5), (double)(-iPY + y + 0.5), (double)(-iPZ + z + 0.5));
            GL11.glRotatef((float)90.0f, (float)(-dir.offsetY), (float)dir.offsetX, (float)(-dir.offsetZ));
            if (dir.offsetZ < 0) {
                GL11.glTranslated((double)0.0, (double)0.0, (double)0.5);
            } else {
                GL11.glTranslated((double)0.0, (double)0.0, (double)-0.5);
            }
            GL11.glRotatef((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            UtilsFX.renderQuadCenteredFromTexture("textures/blocks/wardedglass.png", 1.0f, r, g, b, 200, 1, alpha);
            GL11.glPopMatrix();
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void renderMarkedBlocks(RenderWorldLastEvent event, float partialTicks, EntityPlayer player, long time) {
        Minecraft mc = Minecraft.func_71410_x();
        if (player.field_71071_by.func_70448_g().func_77942_o() && player.field_71071_by.func_70448_g().field_77990_d.func_74764_b("markers")) {
            Entity golem = null;
            ChunkCoordinates cc = null;
            int face = -1;
            if (player.field_71071_by.func_70448_g().func_77973_b() instanceof ItemGolemBell) {
                cc = ItemGolemBell.getGolemHomeCoords(player.field_71071_by.func_70448_g());
                face = ItemGolemBell.getGolemHomeFace(player.field_71071_by.func_70448_g());
                int gid = ItemGolemBell.getGolemId(player.field_71071_by.func_70448_g());
                if (gid > -1) {
                    golem = player.field_70170_p.func_73045_a(gid);
                }
                if (golem == null || !(golem instanceof EntityGolemBase)) {
                    return;
                }
            }
            GL11.glPushMatrix();
            GL11.glAlphaFunc((int)516, (float)0.003921569f);
            if (golem != null && cc != null && face > -1 && player.func_70092_e((double)cc.field_71574_a, (double)cc.field_71572_b, (double)cc.field_71573_c) < 4096.0) {
                GL11.glPushMatrix();
                this.drawGolemHomeOverlay(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c, face, partialTicks);
                GL11.glPopMatrix();
            }
            NBTTagList tl = player.field_71071_by.func_70448_g().field_77990_d.func_150295_c("markers", 10);
            for (int q = 0; q < tl.func_74745_c(); ++q) {
                NBTTagCompound nbttagcompound1 = tl.func_150305_b(q);
                double x = nbttagcompound1.func_74762_e("x");
                double y = nbttagcompound1.func_74762_e("y");
                double z = nbttagcompound1.func_74762_e("z");
                int ox = nbttagcompound1.func_74762_e("x");
                int oy = nbttagcompound1.func_74762_e("y");
                int oz = nbttagcompound1.func_74762_e("z");
                int dim = nbttagcompound1.func_74762_e("dim");
                byte s = nbttagcompound1.func_74771_c("side");
                byte c = nbttagcompound1.func_74771_c("color");
                x += (double)ForgeDirection.getOrientation((int)s).offsetX;
                y += (double)ForgeDirection.getOrientation((int)s).offsetY;
                z += (double)ForgeDirection.getOrientation((int)s).offsetZ;
                if (dim != player.field_70170_p.field_73011_w.field_76574_g || !(player.func_70092_e(x, y, z) < 4096.0)) continue;
                GL11.glPushMatrix();
                this.drawMarkerOverlay(x, y, z, s, partialTicks, c);
                GL11.glPopMatrix();
                if (player.field_70170_p.func_147437_c(ox, oy, oz)) {
                    GL11.glPushMatrix();
                    for (int a = 0; a < 6; ++a) {
                        this.drawAirBlockoverlay(ox + ForgeDirection.getOrientation((int)a).offsetX, oy + ForgeDirection.getOrientation((int)a).offsetY, oz + ForgeDirection.getOrientation((int)a).offsetZ, a, partialTicks, c);
                    }
                    GL11.glPopMatrix();
                }
                if (golem == null || Config.golemLinkQuality <= 3) continue;
                GL11.glPushMatrix();
                this.drawMarkerLine(x -= (double)ForgeDirection.getOrientation((int)s).offsetX * 0.5, y -= (double)ForgeDirection.getOrientation((int)s).offsetY * 0.5, z -= (double)ForgeDirection.getOrientation((int)s).offsetZ * 0.5, s, partialTicks, c, golem);
                GL11.glPopMatrix();
            }
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glAlphaFunc((int)516, (float)0.1f);
            GL11.glPopMatrix();
        }
    }

    public void drawAirBlockoverlay(double x, double y, double z, int side, float partialTicks, int color) {
        float r = 1.0f;
        float g = 1.0f;
        float b = 1.0f;
        EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().field_71451_h;
        double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * (double)partialTicks;
        double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * (double)partialTicks;
        double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * (double)partialTicks;
        float time = System.nanoTime() / 30000000L;
        if (color == -1) {
            r = MathHelper.func_76126_a((float)(time % 32767.0f / 12.0f + (float)side)) * 0.2f + 0.8f;
            g = MathHelper.func_76126_a((float)(time % 32767.0f / 14.0f + (float)side)) * 0.2f + 0.8f;
            b = MathHelper.func_76126_a((float)(time % 32767.0f / 16.0f + (float)side)) * 0.2f + 0.8f;
        } else {
            Color cc = new Color(UtilsFX.colors[color]);
            r = (float)cc.getRed() / 255.0f;
            g = (float)cc.getGreen() / 255.0f;
            b = (float)cc.getBlue() / 255.0f;
        }
        GL11.glPushMatrix();
        GL11.glDepthMask((boolean)false);
        GL11.glDisable((int)2884);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        ForgeDirection dir = ForgeDirection.getOrientation((int)side);
        GL11.glTranslated((double)(-iPX + x + 0.5 - (double)((float)dir.offsetX * 0.01f)), (double)(-iPY + y + 0.5 - (double)((float)dir.offsetY * 0.01f)), (double)(-iPZ + z + 0.5 - (double)((float)dir.offsetZ * 0.01f)));
        GL11.glRotatef((float)90.0f, (float)(-dir.offsetY), (float)dir.offsetX, (float)(-dir.offsetZ));
        GL11.glPushMatrix();
        if (dir.offsetZ < 0) {
            GL11.glTranslated((double)0.0, (double)0.0, (double)0.5);
        } else {
            GL11.glTranslated((double)0.0, (double)0.0, (double)-0.5);
        }
        GL11.glRotatef((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glScalef((float)0.98f, (float)0.98f, (float)0.98f);
        UtilsFX.renderQuadCenteredFromTexture("textures/blocks/empty.png", 1.0f, r, g, b, 200, 1, 1.0f);
        GL11.glPopMatrix();
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2884);
        GL11.glDepthMask((boolean)true);
        GL11.glPopMatrix();
    }

    public void drawMarkerOverlay(double x, double y, double z, int side, float partialTicks, int color) {
        float r = 1.0f;
        float g = 1.0f;
        float b = 1.0f;
        EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().field_71451_h;
        double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * (double)partialTicks;
        double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * (double)partialTicks;
        double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * (double)partialTicks;
        float time = System.nanoTime() / 30000000L;
        if (color == -1) {
            r = MathHelper.func_76126_a((float)(time % 32767.0f / 12.0f + (float)side)) * 0.2f + 0.8f;
            g = MathHelper.func_76126_a((float)(time % 32767.0f / 14.0f + (float)side)) * 0.2f + 0.8f;
            b = MathHelper.func_76126_a((float)(time % 32767.0f / 16.0f + (float)side)) * 0.2f + 0.8f;
        } else {
            Color cc = new Color(UtilsFX.colors[color]);
            r = (float)cc.getRed() / 255.0f;
            g = (float)cc.getGreen() / 255.0f;
            b = (float)cc.getBlue() / 255.0f;
        }
        GL11.glPushMatrix();
        GL11.glDepthMask((boolean)false);
        GL11.glDisable((int)2884);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        ForgeDirection dir = ForgeDirection.getOrientation((int)side);
        GL11.glTranslated((double)(-iPX + x + 0.5 + (double)((float)dir.offsetX * 0.01f)), (double)(-iPY + y + 0.5 + (double)((float)dir.offsetY * 0.01f)), (double)(-iPZ + z + 0.5 + (double)((float)dir.offsetZ * 0.01f)));
        GL11.glRotatef((float)90.0f, (float)(-dir.offsetY), (float)dir.offsetX, (float)(-dir.offsetZ));
        GL11.glPushMatrix();
        if (dir.offsetZ < 0) {
            GL11.glTranslated((double)0.0, (double)0.0, (double)0.5);
        } else {
            GL11.glTranslated((double)0.0, (double)0.0, (double)-0.5);
        }
        GL11.glRotatef((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glScalef((float)0.4f, (float)0.4f, (float)0.4f);
        UtilsFX.renderQuadCenteredFromTexture("textures/misc/mark.png", 1.0f, r, g, b, 200, 1, 1.0f);
        GL11.glPopMatrix();
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2884);
        GL11.glDepthMask((boolean)true);
        GL11.glPopMatrix();
    }

    public void drawGolemHomeOverlay(double x, double y, double z, int side, float partialTicks) {
        float r = 1.0f;
        float g = 1.0f;
        float b = 1.0f;
        EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().field_71451_h;
        double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * (double)partialTicks;
        double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * (double)partialTicks;
        double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * (double)partialTicks;
        float time = System.nanoTime() / 30000000L;
        r = MathHelper.func_76126_a((float)(time % 32767.0f / 12.0f + (float)side)) * 0.2f + 0.8f;
        g = MathHelper.func_76126_a((float)(time % 32767.0f / 14.0f + (float)side)) * 0.2f + 0.8f;
        b = MathHelper.func_76126_a((float)(time % 32767.0f / 16.0f + (float)side)) * 0.2f + 0.8f;
        GL11.glPushMatrix();
        GL11.glDepthMask((boolean)false);
        GL11.glDisable((int)2884);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        ForgeDirection dir = ForgeDirection.getOrientation((int)side);
        GL11.glTranslated((double)(-iPX + x + 0.5 + (double)((float)dir.offsetX * 0.01f)), (double)(-iPY + y + 0.5 + (double)((float)dir.offsetY * 0.01f)), (double)(-iPZ + z + 0.5 + (double)((float)dir.offsetZ * 0.01f)));
        GL11.glRotatef((float)90.0f, (float)(-dir.offsetY), (float)dir.offsetX, (float)(-dir.offsetZ));
        GL11.glPushMatrix();
        if (dir.offsetZ < 0) {
            GL11.glTranslated((double)0.0, (double)0.0, (double)0.5);
        } else {
            GL11.glTranslated((double)0.0, (double)0.0, (double)-0.5);
        }
        GL11.glRotatef((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glScalef((float)0.65f, (float)0.65f, (float)0.65f);
        UtilsFX.renderQuadCenteredFromTexture("textures/misc/home.png", 1.0f, r, g, b, 200, 1, 1.0f);
        GL11.glPopMatrix();
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2884);
        GL11.glDepthMask((boolean)true);
        GL11.glPopMatrix();
    }

    public void drawMarkerLine(double x, double y, double z, int side, float partialTicks, int color, Entity cc) {
        EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().field_71451_h;
        double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * (double)partialTicks;
        double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * (double)partialTicks;
        double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * (double)partialTicks;
        double ePX = cc.field_70169_q + (cc.field_70165_t - cc.field_70169_q) * (double)partialTicks;
        double ePY = cc.field_70167_r + (cc.field_70163_u - cc.field_70167_r) * (double)partialTicks;
        double ePZ = cc.field_70166_s + (cc.field_70161_v - cc.field_70166_s) * (double)partialTicks;
        GL11.glTranslated((double)(-iPX + ePX), (double)(-iPY + ePY + (double)cc.field_70131_O), (double)(-iPZ + ePZ));
        float r = 1.0f;
        float g = 1.0f;
        float b = 1.0f;
        float time = System.nanoTime() / 30000000L;
        if (color > -1) {
            Color co = new Color(UtilsFX.colors[color]);
            r = (float)co.getRed() / 255.0f;
            g = (float)co.getGreen() / 255.0f;
            b = (float)co.getBlue() / 255.0f;
        }
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        Tessellator tessellator = Tessellator.field_78398_a;
        double ds1x = ePX;
        double ds1y = ePY + (double)cc.field_70131_O;
        double ds1z = ePZ;
        double dd1x = x + 0.5 + (double)ForgeDirection.getOrientation((int)side).offsetX * 0.5;
        double dd1y = y + 0.5 + (double)ForgeDirection.getOrientation((int)side).offsetY * 0.5;
        double dd1z = z + 0.5 + (double)ForgeDirection.getOrientation((int)side).offsetZ * 0.5;
        double dc1x = (float)(dd1x - ds1x);
        double dc1y = (float)(dd1y - ds1y);
        double dc1z = (float)(dd1z - ds1z);
        double ds2x = x + 0.5;
        double ds2y = y + 0.5;
        double ds2z = z + 0.5;
        double dc22x = (float)(ds2x - ds1x);
        double dc22y = (float)(ds2y - ds1y);
        double dc22z = (float)(ds2z - ds1z);
        UtilsFX.bindTexture("textures/misc/script.png");
        GL11.glDisable((int)2884);
        tessellator.func_78371_b(5);
        float f4 = 0.0f;
        double dx2 = 0.0;
        double dy2 = 0.0;
        double dz2 = 0.0;
        double d3 = x - ePX;
        double d4 = y - ePY;
        double d5 = z - ePZ;
        float dist = MathHelper.func_76133_a((double)(d3 * d3 + d4 * d4 + d5 * d5));
        float blocks = Math.round(dist);
        float length = blocks * (float)Config.golemLinkQuality;
        float f9 = 0.0f;
        float f10 = 1.0f;
        boolean count = false;
        int i = 0;
        while ((float)i <= length) {
            float f2 = (float)i / length;
            float f2a = (float)i * 1.5f / length;
            f2a = Math.min(0.75f, f2a);
            float f3 = 1.0f - Math.abs((float)i - length / 2.0f) / (length / 2.0f);
            f4 = 0.0f;
            if (color == -1) {
                r = MathHelper.func_76126_a((float)(time % 32767.0f / 12.0f + (float)side + (float)i)) * 0.2f + 0.8f;
                g = MathHelper.func_76126_a((float)(time % 32767.0f / 14.0f + (float)side + (float)i)) * 0.2f + 0.8f;
                b = MathHelper.func_76126_a((float)(time % 32767.0f / 16.0f + (float)side + (float)i)) * 0.2f + 0.8f;
            }
            double dx = dc1x + (double)(MathHelper.func_76126_a((float)((float)(((double)(side * 20) + z % 16.0 + (double)(dist * (1.0f - f2) * (float)Config.golemLinkQuality) - (double)(time % 32767.0f / 5.0f)) / 4.0))) * 0.5f * f3);
            double dy = dc1y + (double)(MathHelper.func_76126_a((float)((float)(((double)(side * 20) + x % 16.0 + (double)(dist * (1.0f - f2) * (float)Config.golemLinkQuality) - (double)(time % 32767.0f / 5.0f)) / 3.0))) * 0.5f * f3);
            double dz = dc1z + (double)(MathHelper.func_76126_a((float)((float)(((double)(side * 20) + y % 16.0 + (double)(dist * (1.0f - f2) * (float)Config.golemLinkQuality) - (double)(time % 32767.0f / 5.0f)) / 2.0))) * 0.5f * f3);
            if ((float)i > length - (float)(Config.golemLinkQuality / 2)) {
                dx2 = dc22x + (double)(MathHelper.func_76126_a((float)((float)(((double)(side * 20) + z % 16.0 + (double)(dist * (1.0f - f2) * (float)Config.golemLinkQuality) - (double)(time % 32767.0f / 5.0f)) / 4.0))) * 0.5f * f3);
                dy2 = dc22y + (double)(MathHelper.func_76126_a((float)((float)(((double)(side * 20) + x % 16.0 + (double)(dist * (1.0f - f2) * (float)Config.golemLinkQuality) - (double)(time % 32767.0f / 5.0f)) / 3.0))) * 0.5f * f3);
                dz2 = dc22z + (double)(MathHelper.func_76126_a((float)((float)(((double)(side * 20) + y % 16.0 + (double)(dist * (1.0f - f2) * (float)Config.golemLinkQuality) - (double)(time % 32767.0f / 5.0f)) / 2.0))) * 0.5f * f3);
                f3 = (length - (float)i) / ((float)Config.golemLinkQuality / 2.0f);
                f4 = 1.0f - f3;
                dx = dx * (double)f3 + dx2 * (double)f4;
                dy = dy * (double)f3 + dy2 * (double)f4;
                dz = dz * (double)f3 + dz2 * (double)f4;
            }
            tessellator.func_78369_a(r, g, b, f2a * (1.0f - f4));
            float f13 = (1.0f - f2) * dist - time * 0.005f;
            tessellator.func_78374_a(dx * (double)f2, dy * (double)f2 - 0.05, dz * (double)f2, (double)f13, (double)f10);
            tessellator.func_78374_a(dx * (double)f2, dy * (double)f2 + 0.05, dz * (double)f2, (double)f13, (double)f9);
            ++i;
        }
        tessellator.func_78381_a();
        GL11.glEnable((int)2884);
        GL11.glDisable((int)3042);
        GL11.glDepthMask((boolean)true);
    }

    protected void renderVignette(float brightness, double sw, double sh) {
        int k = (int)sw;
        int l = (int)sh;
        brightness = 1.0f - brightness;
        if ((prevVignetteBrightness = (float)((double)prevVignetteBrightness + (double)(brightness - prevVignetteBrightness) * 0.01)) > 0.0f) {
            float b = prevVignetteBrightness * (1.0f + MathHelper.func_76126_a((float)((float)Minecraft.func_71410_x().field_71439_g.field_70173_aa / 2.0f)) * 0.1f);
            GL11.glPushMatrix();
            GL11.glClear((int)256);
            GL11.glMatrixMode((int)5889);
            GL11.glLoadIdentity();
            GL11.glOrtho((double)0.0, (double)sw, (double)sh, (double)0.0, (double)1000.0, (double)3000.0);
            Minecraft.func_71410_x().func_110434_K().func_110577_a(vignetteTexPath);
            GL11.glMatrixMode((int)5888);
            GL11.glLoadIdentity();
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-2000.0f);
            GL11.glDisable((int)2929);
            GL11.glDepthMask((boolean)false);
            OpenGlHelper.func_148821_a((int)0, (int)769, (int)1, (int)0);
            GL11.glColor4f((float)b, (float)b, (float)b, (float)1.0f);
            Tessellator tessellator = Tessellator.field_78398_a;
            tessellator.func_78382_b();
            tessellator.func_78374_a(0.0, (double)l, -90.0, 0.0, 1.0);
            tessellator.func_78374_a((double)k, (double)l, -90.0, 1.0, 1.0);
            tessellator.func_78374_a((double)k, 0.0, -90.0, 1.0, 0.0);
            tessellator.func_78374_a(0.0, 0.0, -90.0, 0.0, 0.0);
            tessellator.func_78381_a();
            GL11.glDepthMask((boolean)true);
            GL11.glEnable((int)2929);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
            GL11.glPopMatrix();
        }
    }

    @SubscribeEvent
    public void livingTick(LivingEvent.LivingUpdateEvent event) {
        EntityMob mob;
        int t;
        if (event.entity.field_70170_p.field_72995_K && event.entity instanceof EntityMob && !event.entity.field_70128_L && (t = (int)(mob = (EntityMob)event.entity).func_110148_a(EntityUtils.CHAMPION_MOD).func_111126_e()) >= 0) {
            ChampionModifier.mods[t].effect.showFX((EntityLivingBase)mob);
        }
    }
}

