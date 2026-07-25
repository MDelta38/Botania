/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$RenderTickEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.client.renderer.InventoryEffectRenderer
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.brewing.potions.PotionResizing;
import com.emoniph.witchery.client.RenderEntityViewer;
import com.emoniph.witchery.client.renderer.RenderInfusionEnergyBar;
import com.emoniph.witchery.infusion.InfusedBrewEffect;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.infusion.infusions.creature.CreaturePower;
import com.emoniph.witchery.infusion.infusions.creature.CreaturePowerSpeed;
import com.emoniph.witchery.infusion.infusions.symbols.EffectRegistry;
import com.emoniph.witchery.infusion.infusions.symbols.StrokeSet;
import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffect;
import com.emoniph.witchery.integration.ModHookMorph;
import com.emoniph.witchery.item.ItemBrewBag;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.EntitySizeInfo;
import com.emoniph.witchery.util.KeyBindHelper;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.RenderUtil;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.Field;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class PlayerRender {
    protected static RenderInfusionEnergyBar infusionEnergyBar;
    protected static RenderInfusionEnergyBar creatureEnergyBar;
    private boolean remoteViewingActive = true;
    public static long ticksSinceActive;
    public static boolean moveCameraActive;
    public static int moveCameraToEntityID;
    private static final ResourceLocation RADIAL_LOCATION;
    EntityRenderer prevRender;
    RenderEntityViewer renderer;
    int currentBeastForm = 0;
    private static final ResourceLocation BARK_TEXTURES;
    private static RenderItem drawItems;
    private int lastY = 0;
    private static final int[] glyphOffsetX;
    private static final int[] glyphOffsetY;
    private static final ResourceLocation TEXTURE_GRID;
    private Field fieldAccess = null;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        ItemStack belt;
        EntityClientPlayerMP player;
        if (event.phase == TickEvent.Phase.START) {
            PotionEffect shrunk;
            EntityClientPlayerMP player2 = Minecraft.func_71410_x().field_71439_g;
            Minecraft mc = Minecraft.func_71410_x();
            if (player2 == null || mc.field_71462_r != null) return;
            if (mc.func_71386_F() - ticksSinceActive > 3000L) {
                moveCameraActive = false;
            }
            this.remoteViewingActive = moveCameraActive;
            if (this.remoteViewingActive) {
                for (Object entity : player2.field_70170_p.field_72996_f) {
                    if (((Entity)entity).func_145782_y() != moveCameraToEntityID || !(entity instanceof EntityLivingBase)) continue;
                    EntityLivingBase living = (EntityLivingBase)entity;
                    if (living.field_70128_L) return;
                    Minecraft.func_71410_x().field_71451_h = living;
                    return;
                }
                return;
            }
            EntitySizeInfo size = new EntitySizeInfo((EntityLivingBase)player2);
            PotionEffect potionEffect = shrunk = Witchery.Potions.RESIZING != null ? player2.func_70660_b(Witchery.Potions.RESIZING) : null;
            if (!(shrunk == null && size.isDefault || ModHookMorph.isMorphed((EntityPlayer)player2, true))) {
                if (this.renderer == null) {
                    this.renderer = new RenderEntityViewer(mc);
                }
                if (mc.field_71474_y.field_74320_O == 0) {
                    if (mc.field_71460_t != this.renderer) {
                        this.prevRender = mc.field_71460_t;
                        mc.field_71460_t = this.renderer;
                    }
                } else if (this.prevRender != null) {
                    mc.field_71460_t = this.prevRender;
                }
                float normalSize = 1.8f;
                float scale = size.defaultHeight / 1.8f * (shrunk != null ? PotionResizing.getScaleFactor(shrunk.func_76458_c()) : 1.0f);
                if (scale < 1.0f) {
                    float requiredSize = 1.8f * (1.0f - scale);
                    float currentSize = this.renderer.getOffset();
                    if (currentSize < requiredSize) {
                        currentSize = Math.min(currentSize + 0.01f, requiredSize);
                    } else if (currentSize > requiredSize) {
                        currentSize = Math.min(currentSize - 0.01f, requiredSize);
                    }
                    this.renderer.setOffset(currentSize);
                    return;
                } else {
                    float requiredSize = -(1.8f * scale - 1.8f);
                    float currentSize = this.renderer.getOffset();
                    if (currentSize > requiredSize) {
                        currentSize = Math.max(currentSize - 0.01f, requiredSize);
                    }
                    this.renderer.setOffset(currentSize);
                }
                return;
            } else {
                if (this.prevRender == null || mc.field_71460_t == this.prevRender) return;
                if (this.renderer != null) {
                    this.renderer.setOffset(0.0f);
                }
                mc.field_71460_t = this.prevRender;
            }
            return;
        }
        if (event.phase != TickEvent.Phase.END || (player = Minecraft.func_71410_x().field_71439_g) == null || Minecraft.func_71410_x().field_71462_r != null) return;
        Minecraft mc = Minecraft.func_71410_x();
        if (this.remoteViewingActive) {
            Minecraft.func_71410_x().field_71451_h = player;
        }
        ScaledResolution screen = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
        int maxEnergy = Infusion.getMaxEnergy((EntityPlayer)player);
        if (maxEnergy > 0) {
            if (infusionEnergyBar == null) {
                infusionEnergyBar = new RenderInfusionEnergyBar(true);
            }
            double left = Config.instance().guiOnLeft ? 20.0 : (double)(screen.func_78326_a() - 20);
            double top = (double)screen.func_78328_b() / 2.0 - 16.0;
            Infusion infusion = Infusion.Registry.instance().get((EntityPlayer)player);
            infusionEnergyBar.draw(left, top, (double)Infusion.getCurrentEnergy((EntityPlayer)player) / (double)maxEnergy, (EntityPlayer)player, infusion.infusionID);
        }
        int powerID = CreaturePower.getCreaturePowerID((EntityPlayer)player);
        int charges = CreaturePower.getCreaturePowerCharges((EntityPlayer)player);
        if (powerID > 0) {
            if (creatureEnergyBar == null) {
                creatureEnergyBar = new RenderInfusionEnergyBar(false);
            }
            double left = Config.instance().guiOnLeft ? 30.0 : (double)(screen.func_78326_a() - 30);
            double top = (double)screen.func_78328_b() / 2.0 - 16.0;
            creatureEnergyBar.draw(left, top, charges, (EntityPlayer)player, powerID);
        }
        if ((belt = player.func_71124_b(2)) != null && belt.func_77973_b() == Witchery.Items.BARK_BELT) {
            int beltCharges = Math.min(Witchery.Items.BARK_BELT.getChargeLevel(belt), Witchery.Items.BARK_BELT.getMaxChargeLevel((EntityLivingBase)player));
            this.drawBarkBeltCharges(player, beltCharges, screen);
        }
        this.drawInfusedBrews(player, screen);
        ItemStack stack = player.func_71011_bu();
        if (stack != null && stack.func_77973_b() == Witchery.Items.MYSTIC_BRANCH) {
            byte[] strokes = player.getEntityData().func_74770_j("Strokes");
            mc.func_110434_K().func_110577_a(TEXTURE_GRID);
            GL11.glPushMatrix();
            int iconOffset = 0;
            if (Config.instance().branchIconSet == 1) {
                iconOffset = 64;
            }
            try {
                int x = screen.func_78326_a() / 2 - 8;
                int y = screen.func_78328_b() / 2 - 8;
                int DELAY = 8;
                this.lastY = this.lastY == 120 ? 0 : this.lastY + 1;
                int tempIndex = this.lastY / 8;
                int imageIndex = tempIndex > 7 ? 15 - tempIndex : tempIndex;
                for (int i = 0; i < strokes.length; ++i) {
                    PlayerRender.drawTexturedModalRect(x += glyphOffsetX[strokes[i]] * 16, y += glyphOffsetY[strokes[i]] * 16, strokes[i] * 16 + iconOffset, imageIndex * 16, 16, 16);
                }
                SymbolEffect effect = EffectRegistry.instance().getEffect(strokes);
                if (effect == null) return;
                String text = effect.getLocalizedName();
                int tx = screen.func_78326_a() / 2 - (int)(PlayerRender.getStringWidth(text) / 2.0);
                int ty = screen.func_78328_b() / 2 + 20;
                PlayerRender.drawString(text, tx, ty, 0xFFFFFF);
                return;
            }
            finally {
                GL11.glPopMatrix();
            }
        } else {
            if (stack == null || stack.func_77973_b() != Witchery.Items.BREW_BAG || player.func_70093_af()) return;
            ItemBrewBag.InventoryBrewBag inv = new ItemBrewBag.InventoryBrewBag((EntityPlayer)player);
            byte[] strokes = player.getEntityData().func_74770_j("Strokes");
            GL11.glPushMatrix();
            try {
                int x = screen.func_78326_a() / 2 - 8;
                int y = screen.func_78328_b() / 2 - 8;
                if (strokes.length == 0) {
                    mc.func_110434_K().func_110577_a(RADIAL_LOCATION);
                    GL11.glPushMatrix();
                    float scale = 0.33333334f;
                    GL11.glTranslatef((float)(x - 42 + 5), (float)(y - 42 + 5), (float)0.0f);
                    GL11.glScalef((float)scale, (float)scale, (float)scale);
                    int color = ItemBrewBag.getColor(stack);
                    float red = (float)(color >>> 16 & 0xFF) / 256.0f;
                    float green = (float)(color >>> 8 & 0xFF) / 256.0f;
                    float blue = (float)(color & 0xFF) / 256.0f;
                    GL11.glColor4f((float)red, (float)green, (float)blue, (float)1.0f);
                    PlayerRender.drawTexturedModalRect(8, 8, 0, 0, 256, 256);
                    GL11.glPopMatrix();
                }
                this.drawBrewInSlot(inv, 0, strokes, x + 0, y - 32, 0, -11, 1);
                this.drawBrewInSlot(inv, 1, strokes, x + 24, y - 24, 23, 6, 0);
                this.drawBrewInSlot(inv, 2, strokes, x + 32, y - 0, 23, 6, 0);
                this.drawBrewInSlot(inv, 3, strokes, x + 24, y + 24, 23, 6, 0);
                this.drawBrewInSlot(inv, 4, strokes, x + 0, y + 32, 0, 19, 1);
                this.drawBrewInSlot(inv, 5, strokes, x - 24, y + 24, -5, 6, 2);
                this.drawBrewInSlot(inv, 6, strokes, x - 32, y - 0, -5, 6, 2);
                this.drawBrewInSlot(inv, 7, strokes, x - 24, y - 24, -5, 6, 2);
                return;
            }
            finally {
                GL11.glPopMatrix();
            }
        }
    }

    private void drawInfusedBrews(EntityClientPlayerMP player, ScaledResolution screen) {
        String remainingTime;
        NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)player);
        InfusedBrewEffect effect = InfusedBrewEffect.getActiveBrew(nbtPlayer);
        if (effect != null && (remainingTime = InfusedBrewEffect.getMinutesRemaining(player.field_70170_p, nbtPlayer, effect)) != null && !remainingTime.isEmpty()) {
            Minecraft mc = Minecraft.func_71410_x();
            mc.func_110434_K().func_110577_a(BARK_TEXTURES);
            int tx = screen.func_78326_a() / 2 - 91;
            int screenHeight = screen.func_78328_b();
            int top = screen.func_78328_b() / 2 + 26;
            int screenMid = screenHeight / 2;
            int left = Config.instance().guiOnLeft ? 17 : screen.func_78326_a() - 23;
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            int ICON_WIDTH = 16;
            int ICON_HEIGHT = 16;
            PlayerRender.drawTexturedModalRect(left, top, effect.imageMapX, effect.imageMapY, 16, 16);
            double width = PlayerRender.getStringWidth(remainingTime) / 2.0;
            PlayerRender.drawString(remainingTime, (double)(left + 8) - width, top + 10, -285212673);
        }
    }

    private void drawBarkBeltCharges(EntityClientPlayerMP player, int beltCharges, ScaledResolution screen) {
        if (beltCharges > 0 && !player.field_71075_bZ.field_75098_d) {
            Minecraft mc = Minecraft.func_71410_x();
            mc.func_110434_K().func_110577_a(BARK_TEXTURES);
            int tx = screen.func_78326_a() / 2 - 91;
            int par2 = screen.func_78328_b();
            int ty = par2 / 2;
            IAttributeInstance attributeinstance = mc.field_71439_g.func_110148_a(SharedMonsterAttributes.field_111267_a);
            int i2 = par2 - 39;
            float f = (float)attributeinstance.func_111126_e();
            float f1 = mc.field_71439_g.func_110139_bj();
            int j2 = MathHelper.func_76123_f((float)((f + f1) / 2.0f / 10.0f));
            int k2 = Math.max(10 - (j2 - 2), 3);
            int l2 = Witchery.modHooks.isTinkersPresent ? i2 - 10 : i2 - (j2 - 1) * k2 - 10;
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            boolean iconOffsetX = false;
            int ICON_WIDTH = 8;
            int ICON_HEIGHT = 8;
            int iconOffsetY = 248;
            for (int i = 0; i < beltCharges; ++i) {
                PlayerRender.drawTexturedModalRect(tx + i * 8, l2, 0, 248, 8, 8);
            }
        }
    }

    private void drawBrewInSlot(ItemBrewBag.InventoryBrewBag inv, int slot, byte[] strokes, int x, int y, int fx, int fy, int align) {
        ItemStack brew = inv.func_70301_a(slot);
        if (brew != null && (strokes.length == 0 || strokes[0] == StrokeSet.Stroke.INDEX_TO_STROKE[slot])) {
            PlayerRender.drawItem(x, y, brew);
            String s = brew.func_82833_r();
            if (s != null) {
                s.trim();
                double fontX = x + fx;
                double fontY = y + fy;
                if (align != 0) {
                    double width = PlayerRender.getStringWidth(s);
                    if (align == 1) {
                        fontX -= width / 2.0;
                    } else if (align == 2) {
                        fontX -= width;
                    }
                }
                PlayerRender.drawString(s, fontX, fontY, 0x77FFFFFF);
            }
        }
    }

    private static FontRenderer getFontRenderer(ItemStack stack) {
        FontRenderer f;
        if (stack != null && stack.func_77973_b() != null && (f = stack.func_77973_b().getFontRenderer(stack)) != null) {
            return f;
        }
        return Minecraft.func_71410_x().field_71466_p;
    }

    private static void drawItem(int i, int j, ItemStack itemstack) {
        PlayerRender.drawItem(i, j, itemstack, PlayerRender.getFontRenderer(itemstack));
    }

    private static void drawItem(int i, int j, ItemStack itemstack, FontRenderer fontRenderer) {
        Minecraft mc = Minecraft.func_71410_x();
        GL11.glEnable((int)2896);
        GL11.glEnable((int)2929);
        PlayerRender.drawItems.field_77023_b += 100.0f;
        try {
            drawItems.func_82406_b(fontRenderer, mc.field_71446_o, itemstack, i, j);
            drawItems.func_77021_b(fontRenderer, mc.field_71446_o, itemstack, i, j);
        }
        catch (Exception e) {
            // empty catch block
        }
        PlayerRender.drawItems.field_77023_b -= 100.0f;
        GL11.glDisable((int)2896);
        GL11.glDisable((int)2929);
    }

    public static void drawString(String s, double x, double y, int color) {
        RenderHelper.func_74518_a();
        RenderUtil.blend(true);
        RenderUtil.render2d(true);
        Minecraft.func_71410_x().field_71466_p.func_78261_a(s, (int)x, (int)y, color);
        RenderUtil.render2d(false);
        RenderUtil.blend(false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static double getStringWidth(String s) {
        double val;
        GL11.glPushAttrib((int)262144);
        try {
            val = Minecraft.func_71410_x().field_71466_p.func_78256_a(s);
        }
        finally {
            GL11.glPopAttrib();
        }
        return val;
    }

    private static void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6) {
        double zLevel = 0.0;
        float f = 0.00390625f;
        float f1 = 0.00390625f;
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78374_a((double)(par1 + 0), (double)(par2 + par6), zLevel, (double)((float)(par3 + 0) * f), (double)((float)(par4 + par6) * f1));
        tessellator.func_78374_a((double)(par1 + par5), (double)(par2 + par6), zLevel, (double)((float)(par3 + par5) * f), (double)((float)(par4 + par6) * f1));
        tessellator.func_78374_a((double)(par1 + par5), (double)(par2 + 0), zLevel, (double)((float)(par3 + par5) * f), (double)((float)(par4 + 0) * f1));
        tessellator.func_78374_a((double)(par1 + 0), (double)(par2 + 0), zLevel, (double)((float)(par3 + 0) * f), (double)((float)(par4 + 0) * f1));
        tessellator.func_78381_a();
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            Minecraft minecraft = Minecraft.func_71410_x();
            EntityClientPlayerMP player = minecraft.field_71439_g;
            if (player != null) {
                int z;
                int y;
                int x;
                PotionEffect effect;
                int sinkingCurseLevel;
                CreaturePower power;
                boolean allowSpeedUp = true;
                int creaturePowerID = CreaturePower.getCreaturePowerID((EntityPlayer)player);
                if (creaturePowerID > 0 && (power = CreaturePower.Registry.instance().get(creaturePowerID)) != null) {
                    power.onUpdate(player.field_70170_p, (EntityPlayer)player);
                    boolean bl = allowSpeedUp = !(power instanceof CreaturePowerSpeed);
                }
                if (player.func_70694_bm() != null && player.func_70694_bm().func_77973_b() != null && allowSpeedUp && (player.func_70694_bm().func_77973_b() == Witchery.Items.MYSTIC_BRANCH || player.func_70694_bm().func_77973_b() == Witchery.Items.BREW_BAG) && player.func_71039_bw()) {
                    boolean canGo;
                    boolean bl = canGo = Math.abs(player.field_70159_w) <= 0.1 && Math.abs(player.field_70179_y) <= 0.1;
                    if (player.field_70170_p.func_147439_a(MathHelper.func_76128_c((double)player.field_70165_t), MathHelper.func_76128_c((double)player.field_70163_u) - 2, MathHelper.func_76128_c((double)player.field_70161_v)) != Blocks.field_150432_aD) {
                        if (player.field_70122_E) {
                            if (!player.func_70090_H()) {
                                if (canGo) {
                                    player.field_70159_w *= 1.6500000476837158;
                                    player.field_70179_y *= 1.6500000476837158;
                                }
                            } else if (canGo) {
                                player.field_70159_w *= (double)1.1f;
                                player.field_70179_y *= (double)1.1f;
                            }
                        }
                    } else if (canGo) {
                        player.field_70159_w *= (double)1.1f;
                        player.field_70179_y *= (double)1.1f;
                    }
                }
                if ((sinkingCurseLevel = Infusion.getSinkingCurseLevel((EntityPlayer)player)) > 0 && player.func_70090_H()) {
                    if (player.field_70181_x < -0.03 && !player.field_70122_E) {
                        player.field_70181_x *= 1.5 + Math.min(0.05 * (double)(sinkingCurseLevel - 1), 0.2);
                    } else if (!player.field_70122_E && player.func_70055_a(Material.field_151586_h) && player.field_70181_x > 0.0) {
                        // empty if block
                    }
                } else if (sinkingCurseLevel > 0) {
                    if (!player.field_71075_bZ.field_75098_d && player.field_71075_bZ.field_75101_c && player.field_71075_bZ.field_75100_b) {
                        player.field_70181_x = -0.2f;
                    }
                } else if (player.func_70644_a(Potion.field_76421_d) && !player.field_71075_bZ.field_75098_d && player.field_71075_bZ.field_75101_c && player.field_71075_bZ.field_75100_b && (effect = player.func_70660_b(Potion.field_76421_d)) != null && effect.func_76458_c() > 4) {
                    player.field_70181_x = -0.2f;
                }
                if (sinkingCurseLevel == 0 && BlockUtil.getBlockMaterial((EntityPlayer)player).func_76224_d() && player.func_82169_q(0) != null && player.func_82169_q(0).func_77973_b() == Witchery.Items.DEATH_FEET && player.field_70181_x < 0.0) {
                    player.field_70181_x += 0.1;
                }
                if (player.field_70122_E && KeyBindHelper.isKeyBindDown(minecraft.field_71474_y.field_74314_A) && player.field_70170_p.func_147439_a(x = MathHelper.func_76128_c((double)player.field_70165_t), (y = MathHelper.func_76128_c((double)player.field_70163_u)) - 1, z = MathHelper.func_76128_c((double)player.field_70161_v)) == Witchery.Blocks.LEAPING_LILY) {
                    player.func_85030_a("random.bowhit", 1.0f, 0.4f / ((float)player.field_70170_p.field_73012_v.nextDouble() * 0.4f + 0.8f));
                }
            }
        } else if (event.phase == TickEvent.Phase.END) {
            Minecraft minecraft = Minecraft.func_71410_x();
            EntityClientPlayerMP player = minecraft.field_71439_g;
            if (player != null && minecraft.field_71462_r != null && minecraft.field_71462_r instanceof InventoryEffectRenderer && player.field_71093_bK == Config.instance().dimensionDreamID && !player.field_71075_bZ.field_75098_d) {
                block35: {
                    if (this.fieldAccess == null) {
                        try {
                            Field[] fields = GuiScreen.class.getDeclaredFields();
                            if (fields.length <= 3) break block35;
                            if (fields[3].getType() == List.class) {
                                Field field = fields[3];
                                field.setAccessible(true);
                                this.fieldAccess = field;
                                break block35;
                            }
                            for (Field field : fields) {
                                if (field.getType() != List.class) continue;
                                field.setAccessible(true);
                                this.fieldAccess = field;
                                break;
                            }
                        }
                        catch (Exception e) {
                            Log.instance().debug(String.format("Exception occurred setting player gui. %s", e.toString()));
                        }
                    }
                }
                if (this.fieldAccess != null) {
                    try {
                        List list = (List)this.fieldAccess.get(minecraft.field_71462_r);
                        if (list.size() > 0) {
                            list.clear();
                        }
                    }
                    catch (IllegalAccessException e) {
                        Log.instance().warning(e, "Exception occurred setting player gui screen");
                    }
                }
            }
        }
    }

    static {
        ticksSinceActive = 0L;
        moveCameraActive = false;
        moveCameraToEntityID = 0;
        RADIAL_LOCATION = new ResourceLocation("witchery", "textures/gui/radial.png");
        BARK_TEXTURES = new ResourceLocation("witchery", "textures/gui/creatures.png");
        drawItems = new RenderItem();
        glyphOffsetX = new int[]{0, 0, 1, -1, 1, -1, -1, 1};
        glyphOffsetY = new int[]{-1, 1, 0, 0, -1, 1, -1, 1};
        TEXTURE_GRID = new ResourceLocation("witchery", "textures/gui/grid.png");
    }
}

