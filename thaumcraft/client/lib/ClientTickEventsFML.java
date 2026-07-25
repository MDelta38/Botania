/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$PlayerTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$RenderTickEvent
 *  cpw.mods.fml.common.registry.GameData
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.shader.ShaderGroup
 *  net.minecraft.client.util.JsonException
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.lib;

import com.google.common.collect.Maps;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.util.JsonException;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.gui.GuiResearchPopup;
import thaumcraft.client.gui.GuiResearchRecipe;
import thaumcraft.client.gui.MappingThread;
import thaumcraft.client.lib.RenderEventHandler;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.blocks.ItemJarFilled;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.relics.ItemSanityChecker;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.items.wands.WandManager;
import thaumcraft.common.items.wands.foci.ItemFocusTrade;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
import thaumcraft.common.lib.events.EssentiaHandler;
import thaumcraft.common.lib.research.ScanManager;
import thaumcraft.common.tiles.TileInfusionMatrix;

public class ClientTickEventsFML {
    public static GuiResearchPopup researchPopup = null;
    public int tickCount = 0;
    int prevWorld;
    boolean checkedDate = false;
    final ResourceLocation HUD = new ResourceLocation("thaumcraft", "textures/gui/hud.png");
    RenderItem ri = new RenderItem();
    DecimalFormat myFormatter = new DecimalFormat("#######.##");
    DecimalFormat myFormatter2 = new DecimalFormat("#######.#");
    HashMap<Integer, AspectList> oldvals = new HashMap();
    long nextsync = 0L;
    boolean startThread = false;
    public static int warpVignette = 0;
    private static final int SHADER_DESAT = 0;
    private static final int SHADER_BLUR = 1;
    private static final int SHADER_HUNGER = 2;
    private static final int SHADER_SUNSCORNED = 3;
    ResourceLocation[] shader_resources = new ResourceLocation[]{new ResourceLocation("shaders/post/desaturatetc.json"), new ResourceLocation("shaders/post/blurtc.json"), new ResourceLocation("shaders/post/hunger.json"), new ResourceLocation("shaders/post/sunscorned.json")};
    ItemStack lastItem = null;
    int lastCount = 0;

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event) {
        if (event.side == Side.SERVER) {
            return;
        }
        if (event.phase == TickEvent.Phase.START) {
            if (!this.startThread && GuiResearchRecipe.cache.size() <= 0) {
                HashMap idMappings = Maps.newHashMap();
                GameData.getBlockRegistry().serializeInto((Map)idMappings);
                GameData.getItemRegistry().serializeInto((Map)idMappings);
                Thread t = new Thread(new MappingThread(idMappings));
                t.start();
                this.startThread = true;
            }
            Minecraft mc = Minecraft.func_71410_x();
            if (event.player.func_71011_bu() != null && event.player.func_71011_bu().func_77973_b() instanceof ItemWandCasting) {
                event.player.func_71008_a(event.player.field_71071_by.func_70448_g(), event.player.func_71052_bv());
            }
            try {
                if (event.player.func_145782_y() == mc.field_71439_g.func_145782_y()) {
                    this.checkShaders(event, mc);
                    if (warpVignette > 0) {
                        --warpVignette;
                        RenderEventHandler.targetBrightness = 0.0f;
                    } else {
                        RenderEventHandler.targetBrightness = 1.0f;
                    }
                    if (RenderEventHandler.fogFiddled) {
                        if (RenderEventHandler.fogDuration < 100) {
                            RenderEventHandler.fogTarget = 0.1f * ((float)RenderEventHandler.fogDuration / 100.0f);
                        } else if (RenderEventHandler.fogTarget < 0.1f) {
                            RenderEventHandler.fogTarget += 0.001f;
                        }
                        if (--RenderEventHandler.fogDuration < 0) {
                            RenderEventHandler.fogFiddled = false;
                        }
                    }
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    private void checkShaders(TickEvent.PlayerTickEvent event, Minecraft mc) {
        if (event.player.func_82165_m(Config.potionDeathGazeID)) {
            warpVignette = 10;
            if (!RenderEventHandler.shaderGroups.containsKey(0)) {
                try {
                    this.setShader(new ShaderGroup(mc.func_110434_K(), mc.func_110442_L(), mc.func_147110_a(), this.shader_resources[0]), 0);
                }
                catch (JsonException e) {}
            }
        } else if (RenderEventHandler.shaderGroups.containsKey(0)) {
            this.deactivateShader(0);
        }
        if (event.player.func_82165_m(Config.potionBlurredID)) {
            if (!RenderEventHandler.shaderGroups.containsKey(1)) {
                try {
                    this.setShader(new ShaderGroup(mc.func_110434_K(), mc.func_110442_L(), mc.func_147110_a(), this.shader_resources[1]), 1);
                }
                catch (JsonException e) {}
            }
        } else if (RenderEventHandler.shaderGroups.containsKey(1)) {
            this.deactivateShader(1);
        }
        if (event.player.func_82165_m(Config.potionUnHungerID)) {
            if (!RenderEventHandler.shaderGroups.containsKey(2)) {
                try {
                    this.setShader(new ShaderGroup(mc.func_110434_K(), mc.func_110442_L(), mc.func_147110_a(), this.shader_resources[2]), 2);
                }
                catch (JsonException e) {}
            }
        } else if (RenderEventHandler.shaderGroups.containsKey(2)) {
            this.deactivateShader(2);
        }
        if (event.player.func_82165_m(Config.potionSunScornedID)) {
            if (!RenderEventHandler.shaderGroups.containsKey(3)) {
                try {
                    this.setShader(new ShaderGroup(mc.func_110434_K(), mc.func_110442_L(), mc.func_147110_a(), this.shader_resources[3]), 3);
                }
                catch (JsonException jsonException) {}
            }
        } else if (RenderEventHandler.shaderGroups.containsKey(3)) {
            this.deactivateShader(3);
        }
    }

    void setShader(ShaderGroup target, int shaderId) {
        if (OpenGlHelper.field_148824_g) {
            Minecraft mc = Minecraft.func_71410_x();
            if (RenderEventHandler.shaderGroups.containsKey(shaderId)) {
                RenderEventHandler.shaderGroups.get(shaderId).func_148021_a();
                RenderEventHandler.shaderGroups.remove(shaderId);
            }
            try {
                if (target == null) {
                    this.deactivateShader(shaderId);
                } else {
                    RenderEventHandler.resetShaders = true;
                    RenderEventHandler.shaderGroups.put(shaderId, target);
                }
            }
            catch (Exception ioexception) {
                RenderEventHandler.shaderGroups.remove(shaderId);
            }
        }
    }

    public void deactivateShader(int shaderId) {
        if (RenderEventHandler.shaderGroups.containsKey(shaderId)) {
            RenderEventHandler.shaderGroups.get(shaderId).func_148021_a();
        }
        RenderEventHandler.shaderGroups.remove(shaderId);
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void clientWorldTick(TickEvent.ClientTickEvent event) {
        if (event.side == Side.SERVER) {
            return;
        }
        Minecraft mc = FMLClientHandler.instance().getClient();
        WorldClient world = mc.field_71441_e;
        if (event.phase == TickEvent.Phase.START) {
            ++this.tickCount;
            for (String fxk : EssentiaHandler.sourceFX.keySet().toArray(new String[0])) {
                EssentiaHandler.EssentiaSourceFX fx = EssentiaHandler.sourceFX.get(fxk);
                if (fx.ticks <= 0) {
                    EssentiaHandler.sourceFX.remove(fxk);
                    continue;
                }
                if (world == null) continue;
                int mod = 0;
                TileEntity tile = world.func_147438_o(fx.start.field_71574_a, fx.start.field_71572_b, fx.start.field_71573_c);
                if (tile != null && tile instanceof TileInfusionMatrix) {
                    mod = -1;
                }
                if (fx.ticks > 5) {
                    Thaumcraft.proxy.essentiaTrailFx((World)world, fx.end.field_71574_a, fx.end.field_71572_b, fx.end.field_71573_c, fx.start.field_71574_a, fx.start.field_71572_b + mod, fx.start.field_71573_c, this.tickCount, fx.color, 1.0f);
                } else {
                    float scale = (float)(fx.ticks * fx.ticks) / 25.0f;
                    Thaumcraft.proxy.essentiaTrailFx((World)world, fx.end.field_71574_a, fx.end.field_71572_b, fx.end.field_71573_c, fx.start.field_71574_a, fx.start.field_71572_b + mod, fx.start.field_71573_c, this.tickCount - (5 - fx.ticks), fx.color, scale);
                }
                --fx.ticks;
                EssentiaHandler.sourceFX.put(fxk, fx);
            }
        } else if (mc.field_71441_e != null && !this.checkedDate) {
            this.checkedDate = true;
            Calendar calendar = mc.field_71441_e.func_83015_S();
            if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31) {
                Thaumcraft.isHalloween = true;
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void renderTick(TickEvent.RenderTickEvent event) {
        block13: {
            long time;
            EntityPlayer player;
            Minecraft mc;
            block14: {
                GuiScreen gui;
                block16: {
                    block15: {
                        mc = FMLClientHandler.instance().getClient();
                        WorldClient world = mc.field_71441_e;
                        if (event.phase == TickEvent.Phase.START || !(Minecraft.func_71410_x().field_71451_h instanceof EntityPlayer)) break block13;
                        player = (EntityPlayer)Minecraft.func_71410_x().field_71451_h;
                        time = System.currentTimeMillis();
                        if (researchPopup == null) {
                            researchPopup = new GuiResearchPopup(mc);
                        }
                        researchPopup.updateResearchWindow();
                        gui = mc.field_71462_r;
                        if (!(gui instanceof GuiContainer)) break block14;
                        if (!gui.func_146272_n()) break block15;
                        if (!Config.showTags) break block16;
                    }
                    if (gui.func_146272_n() || !Config.showTags) break block14;
                }
                if (!Mouse.isGrabbed()) {
                    this.renderAspectsInGui((GuiContainer)gui, player);
                }
            }
            if (player != null && mc.field_71415_G) {
                if (mc.func_71382_s()) {
                    if (player.field_71071_by.func_70440_f(2) != null && player.field_71071_by.func_70440_f(2).func_77973_b() == ConfigItems.itemHoverHarness) {
                        this.renderHoverHUD(event.renderTickTime, player, time, player.field_71071_by.func_70440_f(2));
                    }
                    if (!player.field_71075_bZ.field_75098_d && Thaumcraft.instance.runicEventHandler.runicCharge.containsKey(player.func_145782_y()) && Thaumcraft.instance.runicEventHandler.runicCharge.get(player.func_145782_y()) > 0 && Thaumcraft.instance.runicEventHandler.runicInfo.containsKey(player.func_145782_y())) {
                        this.renderRunicArmorBar(event.renderTickTime, player, time);
                    }
                    if (player.field_71071_by.func_70448_g() != null) {
                        if (player.field_71071_by.func_70448_g().func_77973_b() instanceof ItemWandCasting) {
                            this.renderCastingWandHud(Float.valueOf(event.renderTickTime), player, time, player.field_71071_by.func_70448_g());
                        } else if (player.field_71071_by.func_70448_g().func_77973_b() instanceof ItemSanityChecker) {
                            this.renderSanityHud(Float.valueOf(event.renderTickTime), player, time);
                        }
                    }
                }
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    private void renderSanityHud(Float partialTicks, EntityPlayer player, long time) {
        Minecraft mc = Minecraft.func_71410_x();
        GL11.glPushMatrix();
        ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x(), mc.field_71443_c, mc.field_71440_d);
        GL11.glClear((int)256);
        GL11.glMatrixMode((int)5889);
        GL11.glLoadIdentity();
        GL11.glOrtho((double)0.0, (double)sr.func_78327_c(), (double)sr.func_78324_d(), (double)0.0, (double)1000.0, (double)3000.0);
        GL11.glMatrixMode((int)5888);
        GL11.glLoadIdentity();
        int k = sr.func_78326_a();
        int l = sr.func_78328_b();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-2000.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        mc.field_71446_o.func_110577_a(this.HUD);
        GL11.glPushMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        UtilsFX.drawTexturedQuad(1, 1, 152, 0, 20, 76, -90.0);
        GL11.glPopMatrix();
        float tw = Thaumcraft.proxy.getPlayerKnowledge().getWarpTotal(player.func_70005_c_());
        int p = Thaumcraft.proxy.getPlayerKnowledge().getWarpPerm(player.func_70005_c_());
        int s = Thaumcraft.proxy.getPlayerKnowledge().getWarpSticky(player.func_70005_c_());
        int t = Thaumcraft.proxy.getPlayerKnowledge().getWarpTemp(player.func_70005_c_());
        float mod = 1.0f;
        if (tw > 100.0f) {
            mod = 100.0f / tw;
            tw = 100.0f;
        }
        int gap = (int)((100.0f - tw) / 100.0f * 48.0f);
        int wt = (int)((float)t / 100.0f * 48.0f * mod);
        int ws = (int)((float)s / 100.0f * 48.0f * mod);
        if (t > 0) {
            GL11.glPushMatrix();
            GL11.glColor4f((float)1.0f, (float)0.5f, (float)1.0f, (float)1.0f);
            UtilsFX.drawTexturedQuad(7, 21 + gap, 200, gap, 8, wt + gap, -90.0);
            GL11.glPopMatrix();
        }
        if (s > 0) {
            GL11.glPushMatrix();
            GL11.glColor4f((float)0.75f, (float)0.0f, (float)0.75f, (float)1.0f);
            UtilsFX.drawTexturedQuad(7, 21 + wt + gap, 200, wt + gap, 8, wt + ws + gap, -90.0);
            GL11.glPopMatrix();
        }
        if (p > 0) {
            GL11.glPushMatrix();
            GL11.glColor4f((float)0.5f, (float)0.0f, (float)0.5f, (float)1.0f);
            UtilsFX.drawTexturedQuad(7, 21 + wt + ws + gap, 200, wt + ws + gap, 8, 48, -90.0);
            GL11.glPopMatrix();
        }
        GL11.glPushMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        UtilsFX.drawTexturedQuad(1, 1, 176, 0, 20, 76, -90.0);
        GL11.glPopMatrix();
        if (tw >= 100.0f) {
            GL11.glPushMatrix();
            UtilsFX.drawTexturedQuad(1, 1, 216, 0, 20, 16, -90.0);
            GL11.glPopMatrix();
        }
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    @SideOnly(value=Side.CLIENT)
    private void renderCastingWandHud(Float partialTicks, EntityPlayer player, long time, ItemStack wandstack) {
        Minecraft mc = Minecraft.func_71410_x();
        ItemWandCasting wand = (ItemWandCasting)wandstack.func_77973_b();
        if (this.oldvals.get(player.field_71071_by.field_70461_c) == null) {
            this.oldvals.put(player.field_71071_by.field_70461_c, wand.getAllVis(wandstack));
        } else if (this.nextsync <= time) {
            this.oldvals.put(player.field_71071_by.field_70461_c, wand.getAllVis(wandstack));
            this.nextsync = time + 1000L;
        }
        GL11.glPushMatrix();
        ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x(), mc.field_71443_c, mc.field_71440_d);
        GL11.glClear((int)256);
        GL11.glMatrixMode((int)5889);
        GL11.glLoadIdentity();
        GL11.glOrtho((double)0.0, (double)sr.func_78327_c(), (double)sr.func_78324_d(), (double)0.0, (double)1000.0, (double)3000.0);
        GL11.glMatrixMode((int)5888);
        GL11.glLoadIdentity();
        int k = sr.func_78326_a();
        int l = sr.func_78328_b();
        int dailLocation = Config.dialBottom ? l - 32 : 0;
        GL11.glTranslatef((float)0.0f, (float)dailLocation, (float)-2000.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        mc.field_71446_o.func_110577_a(this.HUD);
        GL11.glPushMatrix();
        GL11.glScaled((double)0.5, (double)0.5, (double)0.5);
        UtilsFX.drawTexturedQuad(0, 0, 0, 0, 64, 64, -90.0);
        GL11.glPopMatrix();
        GL11.glTranslatef((float)16.0f, (float)16.0f, (float)0.0f);
        int max = wand.getMaxVis(wandstack);
        ItemFocusBasic focus = wand.getFocus(wandstack);
        ItemStack focusStack = wand.getFocusItem(wandstack);
        int count = 0;
        AspectList aspects = wand.getAllVis(wandstack);
        for (Aspect aspect : aspects.getAspects()) {
            int amt = aspects.getAmount(aspect);
            GL11.glPushMatrix();
            if (!Config.dialBottom) {
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            }
            GL11.glRotatef((float)(-15 + count * 24), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glTranslatef((float)0.0f, (float)-32.0f, (float)0.0f);
            GL11.glScaled((double)0.5, (double)0.5, (double)0.5);
            int loc = (int)(30.0f * (float)amt / (float)max);
            GL11.glPushMatrix();
            Color ac = new Color(aspect.getColor());
            GL11.glColor4f((float)((float)ac.getRed() / 255.0f), (float)((float)ac.getGreen() / 255.0f), (float)((float)ac.getBlue() / 255.0f), (float)0.8f);
            UtilsFX.drawTexturedQuad(-4, 35 - loc, 104, 0, 8, loc, -90.0);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            UtilsFX.drawTexturedQuad(-8, -3, 72, 0, 16, 42, -90.0);
            GL11.glPopMatrix();
            int sh = 0;
            if (focus != null && focus.getVisCost(focusStack).getAmount(aspect) > 0) {
                GL11.glPushMatrix();
                UtilsFX.drawTexturedQuad(-4, -8, 136, 0, 8, 8, -90.0);
                sh = 8;
                GL11.glPopMatrix();
            }
            if (this.oldvals.get(player.field_71071_by.field_70461_c).getAmount(aspect) > amt) {
                GL11.glPushMatrix();
                UtilsFX.drawTexturedQuad(-4, -8 - sh, 128, 0, 8, 8, -90.0);
                GL11.glPopMatrix();
            } else if (this.oldvals.get(player.field_71071_by.field_70461_c).getAmount(aspect) < amt) {
                GL11.glPushMatrix();
                UtilsFX.drawTexturedQuad(-4, -8 - sh, 120, 0, 8, 8, -90.0);
                GL11.glPopMatrix();
            }
            if (player.func_70093_af()) {
                GL11.glPushMatrix();
                GL11.glRotatef((float)-90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                String msg = amt / 100 + "";
                mc.field_71456_v.func_73731_b(mc.field_71466_p, msg, -32, -4, 0xFFFFFF);
                GL11.glPopMatrix();
                if (focus != null && focus.getVisCost(focusStack).getAmount(aspect) > 0) {
                    float mod = wand.getConsumptionModifier(wandstack, player, aspect, false);
                    GL11.glPushMatrix();
                    GL11.glRotatef((float)-90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    msg = this.myFormatter.format((float)focus.getVisCost(focusStack).getAmount(aspect) * mod / 100.0f);
                    mc.field_71456_v.func_73731_b(mc.field_71466_p, msg, 8, -4, 0xFFFFFF);
                    GL11.glPopMatrix();
                }
                mc.field_71446_o.func_110577_a(this.HUD);
            }
            GL11.glPopMatrix();
            ++count;
        }
        if (focus != null) {
            ItemFocusTrade wt;
            ItemStack picked = null;
            if (focus instanceof ItemFocusTrade && (picked = (wt = (ItemFocusTrade)focus).getPickedBlock(player.field_71071_by.func_70448_g())) != null) {
                this.renderWandTradeHud(partialTicks.floatValue(), player, time, picked);
            }
            if (picked == null) {
                GL11.glPushMatrix();
                GL11.glTranslatef((float)-24.0f, (float)-24.0f, (float)90.0f);
                GL11.glEnable((int)2896);
                this.ri.func_82406_b(mc.field_71466_p, mc.field_71446_o, wand.getFocusItem(wandstack), 16, 16);
                GL11.glDisable((int)2896);
                GL11.glPopMatrix();
                float f = WandManager.getCooldown((EntityLivingBase)player);
                if (f > 0.0f) {
                    GL11.glPushMatrix();
                    GL11.glTranslatef((float)0.0f, (float)0.0f, (float)150.0f);
                    GL11.glScaled((double)0.5, (double)0.5, (double)0.5);
                    String secs = this.myFormatter2.format(f) + "s";
                    int w = mc.field_71466_p.func_78256_a(secs) / 2;
                    mc.field_71456_v.func_73731_b(mc.field_71466_p, secs, -w, -4, 0xFFFFFF);
                    GL11.glPopMatrix();
                }
            }
        }
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    @SideOnly(value=Side.CLIENT)
    public void renderRunicArmorBar(float partialTicks, EntityPlayer player, long time) {
        Minecraft mc = Minecraft.func_71410_x();
        float total = Thaumcraft.instance.runicEventHandler.runicInfo.get(player.func_145782_y())[0].intValue();
        float current = Thaumcraft.instance.runicEventHandler.runicCharge.get(player.func_145782_y()).intValue();
        GL11.glPushMatrix();
        ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x(), mc.field_71443_c, mc.field_71440_d);
        GL11.glClear((int)256);
        GL11.glMatrixMode((int)5889);
        GL11.glLoadIdentity();
        GL11.glOrtho((double)0.0, (double)sr.func_78327_c(), (double)sr.func_78324_d(), (double)0.0, (double)1000.0, (double)3000.0);
        GL11.glMatrixMode((int)5888);
        GL11.glLoadIdentity();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-2000.0f);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)3008);
        int k = sr.func_78326_a();
        int l = sr.func_78328_b();
        GL11.glTranslatef((float)(k / 2 - 91), (float)(l - 39), (float)0.0f);
        mc.field_71446_o.func_110577_a(ParticleEngine.particleTexture);
        float fill = current / total;
        int a = 0;
        while ((float)a < fill * 10.0f) {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            UtilsFX.drawTexturedQuad(a * 8, 0, 160, 16, 9, 9, -90.0);
            GL11.glPushMatrix();
            GL11.glScaled((double)0.5, (double)0.5, (double)0.5);
            GL11.glColor4f((float)1.0f, (float)0.75f, (float)0.24f, (float)(MathHelper.func_76126_a((float)((float)player.field_70173_aa / 4.0f + (float)a)) * 0.4f + 0.6f));
            UtilsFX.drawTexturedQuad(a * 16, 0, a * 16, 96, 16, 16, -90.0);
            GL11.glPopMatrix();
            ++a;
        }
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2929);
        GL11.glEnable((int)3008);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
    }

    @SideOnly(value=Side.CLIENT)
    public void renderHoverHUD(float partialTicks, EntityPlayer player, long time, ItemStack armor) {
        AspectList aspects;
        ItemStack jar;
        Minecraft mc = Minecraft.func_71410_x();
        GL11.glPushMatrix();
        ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x(), mc.field_71443_c, mc.field_71440_d);
        GL11.glClear((int)256);
        GL11.glMatrixMode((int)5889);
        GL11.glLoadIdentity();
        GL11.glOrtho((double)0.0, (double)sr.func_78327_c(), (double)sr.func_78324_d(), (double)0.0, (double)1000.0, (double)3000.0);
        GL11.glMatrixMode((int)5888);
        GL11.glLoadIdentity();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-2000.0f);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)3008);
        int k = sr.func_78326_a();
        int l = sr.func_78328_b();
        short fuel = 0;
        if (armor.func_77942_o() && armor.field_77990_d.func_74764_b("jar") && (jar = ItemStack.func_77949_a((NBTTagCompound)armor.field_77990_d.func_74775_l("jar"))) != null && jar.func_77973_b() instanceof ItemJarFilled && jar.func_77942_o() && (aspects = ((ItemJarFilled)jar.func_77973_b()).getAspects(jar)) != null && aspects.size() > 0) {
            fuel = (short)aspects.getAmount(Aspect.ENERGY);
        }
        int level = Math.round((float)fuel / 64.0f * 48.0f);
        mc.field_71446_o.func_110577_a(ParticleEngine.particleTexture);
        GL11.glColor4f((float)0.0f, (float)1.0f, (float)0.75f, (float)1.0f);
        UtilsFX.drawTexturedQuad(6, l / 2 + 24 - level, 224, 48 - level, 8, level, -91.0);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        UtilsFX.drawTexturedQuad(5, l / 2 - 28, 240, 0, 10, 56, -90.0);
        if (armor.func_77942_o() && armor.field_77990_d.func_74764_b("hover") && armor.field_77990_d.func_74771_c("hover") == 1) {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.66f);
            UtilsFX.drawTexturedQuad(2, l / 2 - 43, 16 * ((int)(mc.func_71386_F() % 700L) / 50), 32, 16, 16, -90.0);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
        try {
            ResourceLocation resourcelocation = mc.field_71446_o.func_130087_a(armor.func_94608_d());
            mc.field_71446_o.func_110577_a(resourcelocation);
            IIcon object = armor.func_77954_c();
            if (object == null) {
                object = ((TextureMap)Minecraft.func_71410_x().func_110434_K().func_110581_b(resourcelocation)).func_110572_b("missingno");
            }
            int i1 = armor.func_77973_b().func_82790_a(armor, 0);
            float f2 = (float)(i1 >> 16 & 0xFF) / 255.0f;
            float f = (float)(i1 >> 8 & 0xFF) / 255.0f;
            float f1 = (float)(i1 & 0xFF) / 255.0f;
            GL11.glColor4f((float)f2, (float)f, (float)f1, (float)1.0f);
            this.ri.func_94149_a(2, l / 2 - 43, object, 16, 16);
        }
        catch (Exception e) {
            // empty catch block
        }
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2929);
        GL11.glEnable((int)3008);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
    }

    @SideOnly(value=Side.CLIENT)
    public void renderWandTradeHud(float partialTicks, EntityPlayer player, long time, ItemStack picked) {
        Minecraft mc = Minecraft.func_71410_x();
        int amount = this.lastCount;
        if (player.field_71071_by.field_70459_e || !picked.func_77969_a(this.lastItem)) {
            amount = 0;
            for (ItemStack is : player.field_71071_by.field_70462_a) {
                if (is == null || !is.func_77969_a(picked)) continue;
                amount += is.field_77994_a;
            }
            this.lastItem = picked;
            player.field_71071_by.field_70459_e = false;
        }
        this.lastCount = amount;
        GL11.glPushMatrix();
        RenderHelper.func_74520_c();
        GL11.glDisable((int)2896);
        GL11.glEnable((int)32826);
        GL11.glEnable((int)2903);
        GL11.glEnable((int)2896);
        try {
            this.ri.func_82406_b(mc.field_71466_p, mc.field_71446_o, picked, -8, -8);
        }
        catch (Exception e) {
            // empty catch block
        }
        GL11.glDisable((int)2896);
        GL11.glPushMatrix();
        String am = "" + amount;
        int sw = mc.field_71466_p.func_78256_a(am);
        GL11.glTranslatef((float)0.0f, (float)(-mc.field_71466_p.field_78288_b), (float)500.0f);
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        for (int a = -1; a <= 1; ++a) {
            for (int b = -1; b <= 1; ++b) {
                if (a != 0 && b != 0 || a == 0 && b == 0) continue;
                mc.field_71466_p.func_78276_b(am, a + 16 - sw, b + 24, 0);
            }
        }
        mc.field_71466_p.func_78276_b(am, 16 - sw, 24, 0xFFFFFF);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    public void renderAspectsInGui(GuiContainer gui, EntityPlayer player) {
        Minecraft mc = FMLClientHandler.instance().getClient();
        ScaledResolution var13 = new ScaledResolution(Minecraft.func_71410_x(), mc.field_71443_c, mc.field_71440_d);
        int var14 = var13.func_78326_a();
        int var15 = var13.func_78328_b();
        int var16 = Mouse.getX() * var14 / mc.field_71443_c;
        int var17 = var15 - Mouse.getY() * var15 / mc.field_71440_d - 1;
        GL11.glPushMatrix();
        GL11.glPushAttrib((int)1048575);
        GL11.glDisable((int)2896);
        for (int var20 = 0; var20 < gui.field_147002_h.field_75151_b.size(); ++var20) {
            int guiTop;
            int guiLeft;
            Slot var23;
            int xs = UtilsFX.getGuiXSize(gui);
            int ys = UtilsFX.getGuiYSize(gui);
            int shift = 0;
            int shift2 = 0;
            int shiftx = -8;
            int shifty = -8;
            if (Thaumcraft.instance.aspectShift) {
                shiftx -= 8;
                shifty -= 8;
            }
            if (!this.isMouseOverSlot(var23 = (Slot)gui.field_147002_h.field_75151_b.get(var20), var16, var17, guiLeft = shift + (gui.field_146294_l - xs - shift2) / 2, guiTop = (gui.field_146295_m - ys) / 2) || var23.func_75211_c() == null) continue;
            int h = ScanManager.generateItemHash(var23.func_75211_c().func_77973_b(), var23.func_75211_c().func_77960_j());
            List list = Thaumcraft.proxy.getScannedObjects().get(player.func_70005_c_());
            if (list == null || !list.contains("@" + h) && !list.contains("#" + h)) continue;
            AspectList tags = ThaumcraftCraftingManager.getObjectTags(var23.func_75211_c());
            tags = ThaumcraftCraftingManager.getBonusTags(var23.func_75211_c(), tags);
            if (tags == null) continue;
            int x = var16 + 17;
            int y = var17 + 7 - 33;
            GL11.glDisable((int)2929);
            int index = 0;
            if (tags.size() > 0) {
                for (Aspect tag : tags.getAspectsSortedAmount()) {
                    if (tag == null) continue;
                    x = var16 + 17 + index * 18;
                    y = var17 + 7 - 33;
                    UtilsFX.bindTexture("textures/aspects/_back.png");
                    GL11.glPushMatrix();
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)770, (int)771);
                    GL11.glTranslated((double)(x + shiftx - 2), (double)(y + shifty - 2), (double)0.0);
                    GL11.glScaled((double)1.25, (double)1.25, (double)0.0);
                    UtilsFX.drawTexturedQuadFull(0, 0, UtilsFX.getGuiZLevel((Gui)gui));
                    GL11.glDisable((int)3042);
                    GL11.glPopMatrix();
                    if (Thaumcraft.proxy.playerKnowledge.hasDiscoveredAspect(player.func_70005_c_(), tag)) {
                        UtilsFX.drawTag(x + shiftx, y + shifty, tag, tags.getAmount(tag), 0, UtilsFX.getGuiZLevel((Gui)gui));
                    } else {
                        UtilsFX.bindTexture("textures/aspects/_unknown.png");
                        GL11.glPushMatrix();
                        GL11.glEnable((int)3042);
                        GL11.glBlendFunc((int)770, (int)771);
                        GL11.glTranslated((double)(x + shiftx), (double)(y + shifty), (double)0.0);
                        UtilsFX.drawTexturedQuadFull(0, 0, UtilsFX.getGuiZLevel((Gui)gui));
                        GL11.glDisable((int)3042);
                        GL11.glPopMatrix();
                    }
                    ++index;
                }
            }
            GL11.glEnable((int)2929);
        }
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    private boolean isMouseOverSlot(Slot par1Slot, int par2, int par3, int par4, int par5) {
        int var4 = par4;
        int var5 = par5;
        return (par2 -= var4) >= par1Slot.field_75223_e - 1 && par2 < par1Slot.field_75223_e + 16 + 1 && (par3 -= var5) >= par1Slot.field_75221_f - 1 && par3 < par1Slot.field_75221_f + 16 + 1;
    }
}

