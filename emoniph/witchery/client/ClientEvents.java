/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.EventPriority
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiIngame
 *  net.minecraft.client.renderer.ActiveRenderInfo
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.MouseEvent
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Pre
 *  net.minecraftforge.client.event.RenderHandEvent
 *  net.minecraftforge.client.event.RenderLivingEvent$Post
 *  net.minecraftforge.client.event.RenderLivingEvent$Pre
 *  net.minecraftforge.client.event.RenderLivingEvent$Specials$Pre
 *  net.minecraftforge.client.event.RenderPlayerEvent$SetArmorModel
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingJumpEvent
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.util.glu.Project
 */
package com.emoniph.witchery.client;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.brewing.potions.ModelOverlayRenderer;
import com.emoniph.witchery.brewing.potions.PotionResizing;
import com.emoniph.witchery.client.TransformBat;
import com.emoniph.witchery.client.TransformOtherPlayer;
import com.emoniph.witchery.client.TransformWolf;
import com.emoniph.witchery.client.TransformWolfman;
import com.emoniph.witchery.client.model.ModelGrotesque;
import com.emoniph.witchery.client.renderer.RenderOtherPlayer;
import com.emoniph.witchery.client.renderer.RenderVillagerBed;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.common.ExtendedVillager;
import com.emoniph.witchery.common.Shapeshift;
import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
import com.emoniph.witchery.entity.EntityVillageGuard;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.infusion.infusions.InfusionOtherwhere;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.RenderUtil;
import com.emoniph.witchery.util.TransformCreature;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Project;

@SideOnly(value=Side.CLIENT)
public class ClientEvents {
    private static final ResourceLocation BLOODDROP_BG = new ResourceLocation("witchery:textures/gui/bdropfaded.png");
    private static final ResourceLocation BLOODDROP = new ResourceLocation("witchery:textures/gui/bdrop.png");
    private static final ModelGrotesque DEMON_HEAD_MODEL = new ModelGrotesque();
    private static final ResourceLocation TEXTURE = new ResourceLocation("witchery", "textures/entities/Demon.png");
    TransformWolf wolf = new TransformWolf();
    TransformWolfman wolfman = new TransformWolfman();
    TransformBat bat = new TransformBat();
    TransformOtherPlayer otherPlayer = new TransformOtherPlayer();
    RenderVillagerBed renderBed = new RenderVillagerBed();
    private static final ResourceLocation wolfSkin = new ResourceLocation("witchery", "textures/entities/werewolf_man.png");

    @SubscribeEvent
    public void onMouseEvent(MouseEvent event) {
        Minecraft mc = Minecraft.func_71410_x();
        ExtendedPlayer playerEx = ExtendedPlayer.get((EntityPlayer)mc.field_71439_g);
        if (playerEx.isVampire() && event.dwheel != 0) {
            int MAXPOWER = playerEx.getMaxAvailablePowerOrdinal();
            if (mc.field_71439_g.field_71071_by.field_70461_c == 0) {
                int power = playerEx.getSelectedVampirePower().ordinal();
                if (event.dwheel > 0) {
                    if (power == MAXPOWER) {
                        playerEx.setSelectedVampirePower(ExtendedPlayer.VampirePower.NONE, true);
                    } else {
                        playerEx.setSelectedVampirePower(ExtendedPlayer.VampirePower.values()[power + 1], true);
                        event.setCanceled(true);
                    }
                } else if (power > 0) {
                    playerEx.setSelectedVampirePower(ExtendedPlayer.VampirePower.values()[power - 1], true);
                    event.setCanceled(true);
                }
            } else if (mc.field_71439_g.field_71071_by.field_70461_c == 8 && event.dwheel < 0) {
                playerEx.setSelectedVampirePower(ExtendedPlayer.VampirePower.values()[MAXPOWER], true);
            }
        }
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void onRenderGameOverlay(RenderGameOverlayEvent.Pre event) {
        switch (event.type) {
            case HOTBAR: {
                GUIOverlay.INSTANCE.renderHotbar(event);
                break;
            }
            case TEXT: {
                int i;
                EntityPlayer targetPlayer;
                ExtendedPlayer targetPlayerEx;
                MovingObjectPosition movingPosition;
                int j;
                EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
                ExtendedPlayer playerEx = ExtendedPlayer.get((EntityPlayer)player);
                if (!playerEx.isVampire()) break;
                float left = Config.instance().guiOnLeft ? 10.0f : (float)(event.resolution.func_78326_a() - 10);
                float top = (float)event.resolution.func_78328_b() * 0.5f + 16.0f;
                int maxBlood = playerEx.getMaxBloodPower();
                int pscale = 250;
                Minecraft.func_71410_x().field_71446_o.func_110577_a(BLOODDROP_BG);
                for (j = 0; j < maxBlood / 250; ++j) {
                    ClientEvents.drawTexturedRect(left, top - (float)(j * 8), 0.0f, 0.0f, 8.0f, 8.0f, 8.0f, 8.0f);
                }
                Minecraft.func_71410_x().field_71446_o.func_110577_a(BLOODDROP);
                int pblood = playerEx.getBloodPower();
                for (j = 0; j < pblood / 250; ++j) {
                    ClientEvents.drawTexturedRect(left, top - (float)(j * 8), 0.0f, 0.0f, 8.0f, 8.0f, 8.0f, 8.0f);
                }
                if (pblood % 250 > 0) {
                    float remainder = 8.0f * ((float)pblood % 250.0f) / 250.0f;
                    ClientEvents.drawTexturedRect(left, top - (float)(j * 8) + 8.0f - remainder, 0.0f, 8.0f - remainder, 8.0f, remainder, 8.0f, 8.0f);
                }
                if ((movingPosition = InfusionOtherwhere.raytraceEntities((World)Minecraft.func_71410_x().field_71441_e, (EntityPlayer)Minecraft.func_71410_x().field_71439_g, true, 5.0)) == null || movingPosition.field_72308_g == null) break;
                int blood = -1;
                if (movingPosition.field_72308_g instanceof EntityVillager) {
                    EntityVillager villager = (EntityVillager)movingPosition.field_72308_g;
                    ExtendedVillager villagerEx = ExtendedVillager.get(villager);
                    if (villagerEx.isClientSynced()) {
                        blood = villagerEx.getBlood();
                    }
                } else if (movingPosition.field_72308_g instanceof EntityVillageGuard) {
                    EntityVillageGuard guard = (EntityVillageGuard)movingPosition.field_72308_g;
                    blood = guard.getBlood();
                } else if (movingPosition.field_72308_g instanceof EntityPlayer && !(targetPlayerEx = ExtendedPlayer.get(targetPlayer = (EntityPlayer)movingPosition.field_72308_g)).isVampire()) {
                    blood = targetPlayerEx.getHumanBlood();
                }
                if (blood < 0) break;
                int tscale = 25;
                int percent = (int)((float)blood / 500.0f * 100.0f);
                float midX = event.resolution.func_78326_a() / 2;
                float midY = event.resolution.func_78328_b() / 2;
                Minecraft.func_71410_x().field_71446_o.func_110577_a(BLOODDROP_BG);
                for (i = 0; i < 4; ++i) {
                    ClientEvents.drawTexturedRect(midX - 16.0f + (float)(i * 8), midY + 10.0f, 0.0f, 0.0f, 8.0f, 8.0f, 8.0f, 8.0f);
                }
                Minecraft.func_71410_x().field_71446_o.func_110577_a(BLOODDROP);
                for (i = 0; i < percent / 25; ++i) {
                    ClientEvents.drawTexturedRect(midX - 16.0f + (float)(i * 8), midY + 10.0f, 0.0f, 0.0f, 8.0f, 8.0f, 8.0f, 8.0f);
                }
                if (percent % 25 > 0) {
                    float remainder = 8.0f * ((float)percent % 25.0f) / 25.0f;
                    float scale = remainder / 8.0f;
                    float offset = 8.0f - remainder;
                    ClientEvents.drawTexturedRect(midX - 16.0f + (float)(i * 8), midY + 10.0f + offset, 0.0f, offset, 8.0f, remainder, 8.0f, 8.0f);
                }
                if (!Config.instance().hudShowVampireTargetBloodText) break;
                float scale = 0.5f;
                GL11.glScalef((float)scale, (float)scale, (float)scale);
                String text = String.format("%d/%d", blood, 500);
                int width = RenderManager.field_78727_a.func_78716_a().func_78256_a(text);
                RenderManager.field_78727_a.func_78716_a().func_78276_b(text, (int)((float)(event.resolution.func_78326_a() / 2 - width / 4) / scale), (int)((float)(event.resolution.func_78328_b() / 2 + 22) / scale), 0xCC0000);
                GL11.glScalef((float)(1.0f / scale), (float)(1.0f / scale), (float)(1.0f / scale));
                break;
            }
        }
    }

    private static void drawTexturedRect(float p_146110_0_, float p_146110_1_, float p_146110_2_, float p_146110_3_, float p_146110_4_, float p_146110_5_, float p_146110_6_, float p_146110_7_) {
        float f4 = 1.0f / p_146110_6_;
        float f5 = 1.0f / p_146110_7_;
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78374_a((double)p_146110_0_, (double)(p_146110_1_ + p_146110_5_), 0.0, (double)(p_146110_2_ * f4), (double)((p_146110_3_ + p_146110_5_) * f5));
        tessellator.func_78374_a((double)(p_146110_0_ + p_146110_4_), (double)(p_146110_1_ + p_146110_5_), 0.0, (double)((p_146110_2_ + p_146110_4_) * f4), (double)((p_146110_3_ + p_146110_5_) * f5));
        tessellator.func_78374_a((double)(p_146110_0_ + p_146110_4_), (double)p_146110_1_, 0.0, (double)((p_146110_2_ + p_146110_4_) * f4), (double)(p_146110_3_ * f5));
        tessellator.func_78374_a((double)p_146110_0_, (double)p_146110_1_, 0.0, (double)(p_146110_2_ * f4), (double)(p_146110_3_ * f5));
        tessellator.func_78381_a();
    }

    @SubscribeEvent
    public void onSetArmorModel(RenderPlayerEvent.SetArmorModel event) {
        EntityPlayer player = event.entityPlayer;
        if (!player.func_82150_aj() && Infusion.getNBT((Entity)player).func_74764_b("witcheryGrotesque")) {
            GL11.glPushMatrix();
            Minecraft mc = Minecraft.func_71410_x();
            mc.func_110434_K().func_110577_a(TEXTURE);
            float par9 = event.partialRenderTick;
            float f6 = player.field_70127_C + (player.field_70125_A - player.field_70127_C) * par9;
            float f2 = this.interpolateRotation(player.field_70760_ar, player.field_70761_aq, par9);
            float f3 = this.interpolateRotation(player.field_70758_at, player.field_70759_as, par9);
            DEMON_HEAD_MODEL.func_78088_a((Entity)event.entityPlayer, 0.0f, 0.0f, 0.0f, f3 - f2, f6, 0.0625f);
            GL11.glPopMatrix();
        }
    }

    @SubscribeEvent
    public void onLivingJump(LivingEvent.LivingJumpEvent event) {
        if (event.entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)event.entity;
            Shapeshift.INSTANCE.updateJump(player);
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGH)
    public void onPlayerPreRender(RenderLivingEvent.Pre event) {
        if (event.entity instanceof EntityVillager) {
            ExtendedVillager ext = ExtendedVillager.get((EntityVillager)event.entity);
            GL11.glPushMatrix();
            if (ext != null && ext.isSleeping()) {
                GL11.glTranslated((double)event.x, (double)event.y, (double)event.z);
                this.renderBed.render((float)event.x, (float)event.y, (float)event.z, 0);
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslated((double)0.5, (double)-1.25, (double)0.0);
                event.entity.func_70034_d(90.0f);
                GL11.glTranslated((double)(-event.x), (double)(-event.y), (double)(-event.z));
            }
        } else if (event.entity instanceof EntityPlayer) {
            ExtendedPlayer playerEx;
            int creatureType;
            EntityPlayer player = (EntityPlayer)event.entity;
            if (WorldProviderDreamWorld.getPlayerIsGhost(Infusion.getNBT((Entity)player))) {
                RenderUtil.blend(true);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.51f);
            }
            if ((creatureType = (playerEx = ExtendedPlayer.get(player)).getCreatureTypeOrdinal()) > 0 && !(event.renderer instanceof RenderOtherPlayer)) {
                float partialTicks;
                event.setCanceled(true);
                PotionEffect pe = player.func_70660_b(Witchery.Potions.RESIZING);
                if (pe != null) {
                    GL11.glPushMatrix();
                    GL11.glTranslated((double)event.x, (double)event.y, (double)event.z);
                    float scale = PotionResizing.getModifiedScaleFactor((EntityLivingBase)player, pe.func_76458_c());
                    GL11.glScalef((float)scale, (float)scale, (float)scale);
                    GL11.glTranslated((double)(-event.x), (double)(-event.y), (double)(-event.z));
                }
                boolean gui = player.field_70759_as == player.field_70177_z && player.field_70758_at == player.field_70177_z && RenderManager.field_78727_a.field_78735_i == 180.0f && Minecraft.func_71410_x().field_71462_r != null;
                float f = partialTicks = gui ? 0.0f : ModelOverlayRenderer.getRenderPartialTicks();
                if (creatureType == 1) {
                    this.wolf.render(event.entity.field_70170_p, event.entity, event.x, event.y, event.z, event.renderer, partialTicks, gui);
                } else if (creatureType == 2) {
                    this.wolfman.render(event.entity.field_70170_p, event.entity, event.x, event.y, event.z, event.renderer, partialTicks, gui);
                } else if (creatureType == 3) {
                    this.bat.render(event.entity.field_70170_p, event.entity, event.x, event.y, event.z, event.renderer, partialTicks, gui);
                } else if (creatureType == 4 && playerEx.getOtherPlayerSkin() != null && !playerEx.getOtherPlayerSkin().equals("")) {
                    this.otherPlayer.render(event.entity.field_70170_p, event.entity, event.x, event.y, event.z, event.renderer, partialTicks, gui);
                }
                if (pe != null) {
                    GL11.glPopMatrix();
                }
            }
        }
    }

    @SubscribeEvent
    public void onRenderLivingSpecialsPre(RenderLivingEvent.Specials.Pre event) {
        EntityPlayer player;
        ExtendedPlayer playerEx;
        if (!event.isCanceled() && Config.instance().allowNameplateMasquerading && event.entity instanceof EntityPlayer && (playerEx = ExtendedPlayer.get(player = (EntityPlayer)event.entity)).getCreatureType() == TransformCreature.PLAYER) {
            event.setCanceled(true);
            GL11.glAlphaFunc((int)516, (float)0.1f);
            EntityLivingBase p_77033_1_ = event.entity;
            double p_77033_2_ = event.x;
            double p_77033_4_ = event.y;
            double p_77033_6_ = event.z;
            RenderManager renderManager = RenderManager.field_78727_a;
            if (Minecraft.func_71382_s() && p_77033_1_ != renderManager.field_78734_h && !p_77033_1_.func_98034_c((EntityPlayer)Minecraft.func_71410_x().field_71439_g) && p_77033_1_.field_70153_n == null) {
                float f2;
                float f = 1.6f;
                float f1 = 0.016666668f * f;
                double d3 = p_77033_1_.func_70068_e((Entity)renderManager.field_78734_h);
                float f3 = f2 = p_77033_1_.func_70093_af() ? 32.0f : 64.0f;
                if (d3 < (double)(f2 * f2)) {
                    String skin = playerEx.getOtherPlayerSkin();
                    if (skin == null || skin.isEmpty()) {
                        return;
                    }
                    String s = new ChatComponentText(skin).func_150254_d();
                    if (p_77033_1_.func_70093_af()) {
                        FontRenderer fontrenderer = renderManager.func_78716_a();
                        GL11.glPushMatrix();
                        GL11.glTranslatef((float)((float)p_77033_2_ + 0.0f), (float)((float)p_77033_4_ + p_77033_1_.field_70131_O + 0.5f), (float)((float)p_77033_6_));
                        GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
                        GL11.glRotatef((float)(-renderManager.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
                        GL11.glRotatef((float)renderManager.field_78732_j, (float)1.0f, (float)0.0f, (float)0.0f);
                        GL11.glScalef((float)(-f1), (float)(-f1), (float)f1);
                        GL11.glDisable((int)2896);
                        GL11.glTranslatef((float)0.0f, (float)(0.25f / f1), (float)0.0f);
                        GL11.glDepthMask((boolean)false);
                        GL11.glEnable((int)3042);
                        OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
                        Tessellator tessellator = Tessellator.field_78398_a;
                        GL11.glDisable((int)3553);
                        tessellator.func_78382_b();
                        int i = fontrenderer.func_78256_a(s) / 2;
                        tessellator.func_78369_a(0.0f, 0.0f, 0.0f, 0.25f);
                        tessellator.func_78377_a((double)(-i - 1), -1.0, 0.0);
                        tessellator.func_78377_a((double)(-i - 1), 8.0, 0.0);
                        tessellator.func_78377_a((double)(i + 1), 8.0, 0.0);
                        tessellator.func_78377_a((double)(i + 1), -1.0, 0.0);
                        tessellator.func_78381_a();
                        GL11.glEnable((int)3553);
                        GL11.glDepthMask((boolean)true);
                        fontrenderer.func_78276_b(s, -fontrenderer.func_78256_a(s) / 2, 0, 0x20FFFFFF);
                        GL11.glEnable((int)2896);
                        GL11.glDisable((int)3042);
                        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                        GL11.glPopMatrix();
                    } else {
                        this.func_96449_a(p_77033_1_, p_77033_2_, p_77033_4_, p_77033_6_, s, f1, d3);
                    }
                }
            }
        }
    }

    protected void func_96449_a(EntityLivingBase p_96449_1_, double p_96449_2_, double p_96449_4_, double p_96449_6_, String p_96449_8_, float p_96449_9_, double p_96449_10_) {
        if (!p_96449_1_.func_70608_bn()) {
            this.func_147906_a((Entity)p_96449_1_, p_96449_8_, p_96449_2_, p_96449_4_, p_96449_6_, 64);
        }
    }

    protected void func_147906_a(Entity p_147906_1_, String p_147906_2_, double p_147906_3_, double p_147906_5_, double p_147906_7_, int p_147906_9_) {
        RenderManager renderManager = RenderManager.field_78727_a;
        double d3 = p_147906_1_.func_70068_e((Entity)renderManager.field_78734_h);
        if (d3 <= (double)(p_147906_9_ * p_147906_9_)) {
            FontRenderer fontrenderer = renderManager.func_78716_a();
            float f = 1.6f;
            float f1 = 0.016666668f * f;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)p_147906_3_ + 0.0f), (float)((float)p_147906_5_ + p_147906_1_.field_70131_O + 0.5f), (float)((float)p_147906_7_));
            GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(-renderManager.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)renderManager.field_78732_j, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glScalef((float)(-f1), (float)(-f1), (float)f1);
            GL11.glDisable((int)2896);
            GL11.glDepthMask((boolean)false);
            GL11.glDisable((int)2929);
            GL11.glEnable((int)3042);
            OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
            Tessellator tessellator = Tessellator.field_78398_a;
            int b0 = 0;
            if (p_147906_2_.equals("deadmau5")) {
                b0 = -10;
            }
            GL11.glDisable((int)3553);
            tessellator.func_78382_b();
            int j = fontrenderer.func_78256_a(p_147906_2_) / 2;
            tessellator.func_78369_a(0.0f, 0.0f, 0.0f, 0.25f);
            tessellator.func_78377_a((double)(-j - 1), (double)(-1 + b0), 0.0);
            tessellator.func_78377_a((double)(-j - 1), (double)(8 + b0), 0.0);
            tessellator.func_78377_a((double)(j + 1), (double)(8 + b0), 0.0);
            tessellator.func_78377_a((double)(j + 1), (double)(-1 + b0), 0.0);
            tessellator.func_78381_a();
            GL11.glEnable((int)3553);
            fontrenderer.func_78276_b(p_147906_2_, -fontrenderer.func_78256_a(p_147906_2_) / 2, b0, 0x20FFFFFF);
            GL11.glEnable((int)2929);
            GL11.glDepthMask((boolean)true);
            fontrenderer.func_78276_b(p_147906_2_, -fontrenderer.func_78256_a(p_147906_2_) / 2, b0, -1);
            GL11.glEnable((int)2896);
            GL11.glDisable((int)3042);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glPopMatrix();
        }
    }

    @SubscribeEvent
    public void onPlayerPostRender(RenderLivingEvent.Post event) {
        EntityPlayer player;
        if (event.entity instanceof EntityVillager) {
            GL11.glPopMatrix();
        } else if (event.entity instanceof EntityPlayer && WorldProviderDreamWorld.getPlayerIsGhost(Infusion.getNBT((Entity)(player = (EntityPlayer)event.entity)))) {
            RenderUtil.blend(false);
        }
    }

    @SubscribeEvent
    public void onRenderHand(RenderHandEvent event) {
        Minecraft mc = Minecraft.func_71410_x();
        ExtendedPlayer playerEx = ExtendedPlayer.get((EntityPlayer)mc.field_71439_g);
        TransformCreature creatureType = playerEx.getCreatureType();
        if (creatureType != TransformCreature.NONE && (mc.field_71439_g.func_70694_bm() == null || creatureType != TransformCreature.WOLFMAN && creatureType != TransformCreature.PLAYER)) {
            event.setCanceled(true);
            this.renderArm(event.renderPass, event.partialTicks, mc, mc.field_71439_g.func_70694_bm() != null, creatureType, playerEx);
        }
    }

    public void renderArm(int renderPass, float partialTicks, Minecraft mc, boolean hasItem, TransformCreature creatureType, ExtendedPlayer playerEx) {
        GL11.glClear((int)256);
        float farPlaneDistance = mc.field_71474_y.field_151451_c * 16;
        double cameraZoom = 1.0;
        double cameraYaw = 0.0;
        double cameraPitch = 0.0;
        if (mc.field_71460_t.field_78532_q <= 0) {
            GL11.glMatrixMode((int)5889);
            GL11.glLoadIdentity();
            float f1 = 0.07f;
            if (mc.field_71474_y.field_74337_g) {
                GL11.glTranslatef((float)((float)(-(renderPass * 2 - 1)) * f1), (float)0.0f, (float)0.0f);
            }
            if (cameraZoom != 1.0) {
                GL11.glTranslatef((float)((float)cameraYaw), (float)((float)(-cameraPitch)), (float)0.0f);
                GL11.glScaled((double)cameraZoom, (double)cameraZoom, (double)1.0);
            }
            Project.gluPerspective((float)this.getFOVModifier(partialTicks, mc.field_71460_t, mc), (float)((float)mc.field_71443_c / (float)mc.field_71440_d), (float)0.05f, (float)(farPlaneDistance * 2.0f));
            if (mc.field_71442_b.func_78747_a()) {
                float f2 = 0.6666667f;
                GL11.glScalef((float)1.0f, (float)f2, (float)1.0f);
            }
            GL11.glMatrixMode((int)5888);
            GL11.glLoadIdentity();
            if (mc.field_71474_y.field_74337_g) {
                GL11.glTranslatef((float)((float)(renderPass * 2 - 1) * 0.1f), (float)0.0f, (float)0.0f);
            }
            GL11.glPushMatrix();
            this.hurtCameraEffect(partialTicks, mc);
            if (mc.field_71474_y.field_74336_f) {
                this.setupViewBobbing(partialTicks, mc);
            }
            if (!(mc.field_71474_y.field_74320_O != 0 || mc.field_71451_h.func_70608_bn() || mc.field_71474_y.field_74319_N || mc.field_71442_b.func_78747_a())) {
                mc.field_71460_t.func_78463_b((double)partialTicks);
                if (hasItem && (creatureType == TransformCreature.WOLF || creatureType == TransformCreature.BAT)) {
                    if (mc.field_71439_g.func_71052_bv() == 0) {
                        GL11.glTranslatef((float)-0.4f, (float)0.1f, (float)0.0f);
                        GL11.glRotatef((float)-20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    }
                    mc.field_71460_t.field_78516_c.func_78440_a(partialTicks);
                } else if (creatureType == TransformCreature.WOLF || creatureType == TransformCreature.PLAYER || creatureType == TransformCreature.WOLFMAN) {
                    this.renderEmptyHand(mc, partialTicks, creatureType, playerEx);
                }
                mc.field_71460_t.func_78483_a((double)partialTicks);
            }
            GL11.glPopMatrix();
            if (mc.field_71474_y.field_74320_O == 0 && !mc.field_71451_h.func_70608_bn()) {
                mc.field_71460_t.field_78516_c.func_78447_b(partialTicks);
                this.hurtCameraEffect(partialTicks, mc);
            }
            if (mc.field_71474_y.field_74336_f) {
                this.setupViewBobbing(partialTicks, mc);
            }
        }
    }

    private void renderEmptyHand(Minecraft mc, float p_78440_1_, TransformCreature creatureType, ExtendedPlayer playerEx) {
        float f1 = 1.0f;
        EntityClientPlayerMP entityclientplayermp = mc.field_71439_g;
        float f2 = entityclientplayermp.field_70127_C + (entityclientplayermp.field_70125_A - entityclientplayermp.field_70127_C) * p_78440_1_;
        GL11.glPushMatrix();
        GL11.glRotatef((float)f2, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)(entityclientplayermp.field_70126_B + (entityclientplayermp.field_70177_z - entityclientplayermp.field_70126_B) * p_78440_1_), (float)0.0f, (float)1.0f, (float)0.0f);
        RenderHelper.func_74519_b();
        GL11.glPopMatrix();
        EntityClientPlayerMP entityplayersp = entityclientplayermp;
        float f3 = entityplayersp.field_71164_i + (entityplayersp.field_71155_g - entityplayersp.field_71164_i) * p_78440_1_;
        float f4 = entityplayersp.field_71163_h + (entityplayersp.field_71154_f - entityplayersp.field_71163_h) * p_78440_1_;
        GL11.glRotatef((float)((entityclientplayermp.field_70125_A - f3) * 0.1f), (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)((entityclientplayermp.field_70177_z - f4) * 0.1f), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (!entityclientplayermp.func_82150_aj()) {
            GL11.glPushMatrix();
            float f13 = 0.8f;
            float f5 = entityclientplayermp.func_70678_g(p_78440_1_);
            float f6 = MathHelper.func_76126_a((float)(f5 * (float)Math.PI));
            float f7 = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)f5) * (float)Math.PI));
            GL11.glTranslatef((float)(-f7 * 0.3f), (float)(MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)f5) * (float)Math.PI * 2.0f)) * 0.4f), (float)(-f6 * 0.4f));
            GL11.glTranslatef((float)(0.8f * f13), (float)(-0.75f * f13 - (1.0f - f1) * 0.6f), (float)(-0.9f * f13));
            GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glEnable((int)32826);
            f5 = entityclientplayermp.func_70678_g(p_78440_1_);
            f6 = MathHelper.func_76126_a((float)(f5 * f5 * (float)Math.PI));
            f7 = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)f5) * (float)Math.PI));
            GL11.glRotatef((float)(f7 * 70.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(-f6 * 20.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            if (creatureType == TransformCreature.WOLF) {
                float scale = 1.5f;
                GL11.glRotatef((float)30.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)-20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glTranslatef((float)-0.3f, (float)0.1f, (float)-0.5f);
                GL11.glScalef((float)1.0f, (float)1.0f, (float)2.0f);
                mc.func_110434_K().func_110577_a(wolfSkin);
            } else if (creatureType == TransformCreature.WOLFMAN) {
                mc.func_110434_K().func_110577_a(wolfSkin);
            } else if (creatureType == TransformCreature.PLAYER) {
                mc.func_110434_K().func_110577_a(playerEx.getLocationSkin());
            }
            GL11.glTranslatef((float)-1.0f, (float)3.6f, (float)3.5f);
            GL11.glRotatef((float)120.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)200.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glTranslatef((float)5.6f, (float)0.0f, (float)0.0f);
            Render render = RenderManager.field_78727_a.func_78713_a((Entity)mc.field_71439_g);
            RenderPlayer renderplayer = (RenderPlayer)render;
            float f10 = 1.0f;
            GL11.glScalef((float)f10, (float)f10, (float)f10);
            renderplayer.func_82441_a((EntityPlayer)mc.field_71439_g);
            GL11.glPopMatrix();
        }
        GL11.glDisable((int)32826);
        RenderHelper.func_74518_a();
    }

    private void hurtCameraEffect(float p_78482_1_, Minecraft mc) {
        float f2;
        EntityLivingBase entitylivingbase = mc.field_71451_h;
        float f1 = (float)entitylivingbase.field_70737_aN - p_78482_1_;
        if (entitylivingbase.func_110143_aJ() <= 0.0f) {
            f2 = (float)entitylivingbase.field_70725_aQ + p_78482_1_;
            GL11.glRotatef((float)(40.0f - 8000.0f / (f2 + 200.0f)), (float)0.0f, (float)0.0f, (float)1.0f);
        }
        if (f1 >= 0.0f) {
            f1 /= (float)entitylivingbase.field_70738_aO;
            f1 = MathHelper.func_76126_a((float)(f1 * f1 * f1 * f1 * (float)Math.PI));
            f2 = entitylivingbase.field_70739_aP;
            GL11.glRotatef((float)(-f2), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(-f1 * 14.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)f2, (float)0.0f, (float)1.0f, (float)0.0f);
        }
    }

    private void setupViewBobbing(float p_78475_1_, Minecraft mc) {
        if (mc.field_71451_h instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer)mc.field_71451_h;
            float f1 = entityplayer.field_70140_Q - entityplayer.field_70141_P;
            float f2 = -(entityplayer.field_70140_Q + f1 * p_78475_1_);
            float f3 = entityplayer.field_71107_bF + (entityplayer.field_71109_bG - entityplayer.field_71107_bF) * p_78475_1_;
            float f4 = entityplayer.field_70727_aS + (entityplayer.field_70726_aT - entityplayer.field_70727_aS) * p_78475_1_;
            GL11.glTranslatef((float)(MathHelper.func_76126_a((float)(f2 * (float)Math.PI)) * f3 * 0.5f), (float)(-Math.abs(MathHelper.func_76134_b((float)(f2 * (float)Math.PI)) * f3)), (float)0.0f);
            GL11.glRotatef((float)(MathHelper.func_76126_a((float)(f2 * (float)Math.PI)) * f3 * 3.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)(Math.abs(MathHelper.func_76134_b((float)(f2 * (float)Math.PI - 0.2f)) * f3) * 5.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)f4, (float)1.0f, (float)0.0f, (float)0.0f);
        }
    }

    private float getFOVModifier(float partialTicks, EntityRenderer er, Minecraft mc) {
        Block block;
        if (er.field_78532_q > 0) {
            return 90.0f;
        }
        EntityLivingBase entityplayer = mc.field_71451_h;
        float f1 = 70.0f;
        if (entityplayer.func_110143_aJ() <= 0.0f) {
            float f2 = (float)entityplayer.field_70725_aQ + partialTicks;
            f1 /= (1.0f - 500.0f / (f2 + 500.0f)) * 2.0f + 1.0f;
        }
        if ((block = ActiveRenderInfo.func_151460_a((World)mc.field_71441_e, (EntityLivingBase)entityplayer, (float)partialTicks)).func_149688_o() == Material.field_151586_h) {
            f1 = f1 * 60.0f / 70.0f;
        }
        return f1;
    }

    private float interpolateRotation(float par1, float par2, float par3) {
        float f3;
        for (f3 = par2 - par1; f3 < -180.0f; f3 += 360.0f) {
        }
        while (f3 >= 180.0f) {
            f3 -= 360.0f;
        }
        return par1 + par3 * f3;
    }

    public static class GUIOverlay
    extends GuiIngame {
        public static final GUIOverlay INSTANCE = new GUIOverlay();
        private static final ResourceLocation WIDGITS = new ResourceLocation("textures/gui/widgets.png");
        private static final ResourceLocation TEETH = new ResourceLocation("witchery", "textures/items/vteeth.png");
        private static final ResourceLocation EYE = new ResourceLocation("witchery", "textures/items/veye.png");
        private static final ResourceLocation BAT = new ResourceLocation("witchery", "textures/items/vbat.png");
        private static final ResourceLocation RUN = new ResourceLocation("witchery", "textures/items/vspeed.png");
        private static final ResourceLocation FIST = new ResourceLocation("witchery", "textures/items/vfist.png");
        private static final ResourceLocation STORM = new ResourceLocation("witchery", "textures/items/vstorm.png");
        private static final ResourceLocation COFFIN = new ResourceLocation("witchery", "textures/items/vcoffin.png");
        private static final int WHITE = 0xFFFFFF;

        public GUIOverlay() {
            super(Minecraft.func_71410_x());
        }

        public void renderHotbar(RenderGameOverlayEvent.Pre event) {
            ExtendedPlayer playerEx = ExtendedPlayer.get((EntityPlayer)this.field_73839_d.field_71439_g);
            if (playerEx.isVampire()) {
                int width = event.resolution.func_78326_a();
                int height = event.resolution.func_78328_b();
                this.field_73839_d.field_71424_I.func_76320_a("actionBar");
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                this.field_73839_d.field_71446_o.func_110577_a(WIDGITS);
                InventoryPlayer inv = this.field_73839_d.field_71439_g.field_71071_by;
                this.func_73729_b(width / 2 - 91, height - 22, 0, 0, 182, 22);
                int vpower = playerEx.getSelectedVampirePower().ordinal();
                if (vpower != 0) {
                    this.func_73729_b(width / 2 - 91 - 1 + -vpower * 20, height - 22 - 1, 0, 22, 24, 22);
                } else {
                    this.func_73729_b(width / 2 - 91 - 1 + inv.field_70461_c * 20, height - 22 - 1, 0, 22, 24, 22);
                }
                GL11.glDisable((int)3042);
                GL11.glEnable((int)32826);
                RenderHelper.func_74520_c();
                this.field_73735_i += 50.0f;
                int x = width / 2 - 90 + -20 + 2;
                int z = height - 16 - 3;
                if (playerEx.getVampireLevel() >= 1) {
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)770, (int)771);
                    this.field_73839_d.field_71446_o.func_110577_a(TEETH);
                    GUIOverlay.func_146110_a((int)x, (int)z, (float)0.0f, (float)0.0f, (int)16, (int)16, (float)16.0f, (float)16.0f);
                    if (playerEx.getVampireLevel() >= 2) {
                        x = width / 2 - 90 + -40 + 2;
                        this.field_73839_d.field_71446_o.func_110577_a(EYE);
                        GUIOverlay.func_146110_a((int)x, (int)z, (float)0.0f, (float)0.0f, (int)16, (int)16, (float)16.0f, (float)16.0f);
                        if (playerEx.getVampireLevel() >= 4) {
                            x = width / 2 - 90 + -60 + 2;
                            this.field_73839_d.field_71446_o.func_110577_a(RUN);
                            GUIOverlay.func_146110_a((int)x, (int)z, (float)0.0f, (float)0.0f, (int)16, (int)16, (float)16.0f, (float)16.0f);
                            if (playerEx.getVampireLevel() >= 7) {
                                x = width / 2 - 90 + -80 + 2;
                                this.field_73839_d.field_71446_o.func_110577_a(BAT);
                                GUIOverlay.func_146110_a((int)x, (int)z, (float)0.0f, (float)0.0f, (int)16, (int)16, (float)16.0f, (float)16.0f);
                                if (playerEx.getVampireLevel() >= 10) {
                                    x = width / 2 - 90 + -100 + 2;
                                    switch (playerEx.getVampireUltimate()) {
                                        case FARM: {
                                            this.field_73839_d.field_71446_o.func_110577_a(COFFIN);
                                            break;
                                        }
                                        case STORM: {
                                            this.field_73839_d.field_71446_o.func_110577_a(STORM);
                                            break;
                                        }
                                        default: {
                                            this.field_73839_d.field_71446_o.func_110577_a(FIST);
                                        }
                                    }
                                    if (playerEx.getVampireUltimateCharges() == 0 || playerEx.getVampireUltimate() == ExtendedPlayer.VampireUltimate.NONE) {
                                        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.2f);
                                        GUIOverlay.func_146110_a((int)x, (int)z, (float)0.0f, (float)0.0f, (int)16, (int)16, (float)16.0f, (float)16.0f);
                                    } else {
                                        GUIOverlay.func_146110_a((int)x, (int)z, (float)0.0f, (float)0.0f, (int)16, (int)16, (float)16.0f, (float)16.0f);
                                    }
                                }
                            }
                        }
                    }
                    GL11.glDisable((int)3042);
                }
                this.field_73735_i -= 50.0f;
                for (int i = 0; i < 9; ++i) {
                    x = width / 2 - 90 + i * 20 + 2;
                    z = height - 16 - 3;
                    this.func_73832_a(i, x, z, event.partialTicks);
                }
                RenderHelper.func_74518_a();
                GL11.glDisable((int)32826);
                this.field_73839_d.field_71424_I.func_76319_b();
                this.renderToolHightlight(playerEx, width, height);
                event.setCanceled(true);
            }
        }

        protected void renderToolHightlight(ExtendedPlayer playerEx, int width, int height) {
            Minecraft mc = Minecraft.func_71410_x();
            if (mc.field_71474_y.field_92117_D && --playerEx.highlightTicks > 0) {
                String name = "";
                switch (playerEx.getSelectedVampirePower()) {
                    case DRINK: {
                        name = Witchery.resource("witchery.vampirepower.feed");
                        break;
                    }
                    case MESMERIZE: {
                        name = Witchery.resource("witchery.vampirepower.eye");
                        break;
                    }
                    case SPEED: {
                        name = Witchery.resource("witchery.vampirepower.speed");
                        break;
                    }
                    case BAT: {
                        name = Witchery.resource("witchery.vampirepower.bat");
                        break;
                    }
                    case ULTIMATE: {
                        switch (playerEx.getVampireUltimate()) {
                            case NONE: {
                                name = String.format(Witchery.resource("witchery.vampirepower.unone"), playerEx.getVampireUltimateCharges());
                                break;
                            }
                            case FARM: {
                                name = String.format(Witchery.resource("witchery.vampirepower.uteleport"), playerEx.getVampireUltimateCharges());
                                break;
                            }
                            case SWARM: {
                                name = String.format(Witchery.resource("witchery.vampirepower.ubats"), playerEx.getVampireUltimateCharges());
                                break;
                            }
                            case STORM: {
                                name = String.format(Witchery.resource("witchery.vampirepower.ustorm"), playerEx.getVampireUltimateCharges());
                            }
                        }
                        break;
                    }
                }
                if (name.equals("")) {
                    return;
                }
                int opacity = (int)((float)playerEx.highlightTicks * 256.0f / 10.0f);
                if (opacity > 255) {
                    opacity = 255;
                }
                if (opacity > 0) {
                    int y = height - 69;
                    if (!mc.field_71442_b.func_78755_b()) {
                        y += 14;
                    }
                    GL11.glPushMatrix();
                    GL11.glEnable((int)3042);
                    OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
                    FontRenderer font = RenderManager.field_78727_a.func_78716_a();
                    if (font != null) {
                        int x = (width - font.func_78256_a(name)) / 2;
                        font.func_78261_a(name, x, y, 0xFFFFFF | opacity << 24);
                    }
                    GL11.glDisable((int)3042);
                    GL11.glPopMatrix();
                }
            }
        }

        protected void renderExtraInventorySlot(ItemStack itemstack, int p_73832_2_, int p_73832_3_, float p_73832_4_) {
            if (itemstack != null) {
                float f1 = (float)itemstack.field_77992_b - p_73832_4_;
                if (f1 > 0.0f) {
                    GL11.glPushMatrix();
                    float f2 = 1.0f + f1 / 5.0f;
                    GL11.glTranslatef((float)(p_73832_2_ + 8), (float)(p_73832_3_ + 12), (float)0.0f);
                    GL11.glScalef((float)(1.0f / f2), (float)((f2 + 1.0f) / 2.0f), (float)1.0f);
                    GL11.glTranslatef((float)(-(p_73832_2_ + 8)), (float)(-(p_73832_3_ + 12)), (float)0.0f);
                }
                field_73841_b.func_82406_b(this.field_73839_d.field_71466_p, this.field_73839_d.func_110434_K(), itemstack, p_73832_2_, p_73832_3_);
                if (f1 > 0.0f) {
                    GL11.glPopMatrix();
                }
                field_73841_b.func_77021_b(this.field_73839_d.field_71466_p, this.field_73839_d.func_110434_K(), itemstack, p_73832_2_, p_73832_3_);
            }
        }
    }
}

