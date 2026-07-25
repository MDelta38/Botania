/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaublesApi
 *  codechicken.lib.packet.PacketCustom
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$PlayerLoggedInEvent
 *  cpw.mods.fml.common.network.internal.FMLNetworkHandler
 *  cpw.mods.fml.common.network.internal.FMLProxyPacket
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.ByteBufOutputStream
 *  io.netty.buffer.Unpooled
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAICreeperSwell
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAITasks$EntityAITaskEntry
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.monster.EntityEnderman
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.Packet
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Tuple
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.event.entity.EntityJoinWorldEvent
 *  net.minecraftforge.event.entity.living.EnderTeleportEvent
 *  net.minecraftforge.event.entity.living.LivingAttackEvent
 *  net.minecraftforge.event.entity.living.LivingDropsEvent
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent
 *  net.minecraftforge.event.world.WorldEvent$Load
 *  org.apache.commons.lang3.tuple.MutablePair
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.damagesource.DamageSourceThaumcraft
 *  thaumcraft.api.entities.ITaintedMob
 *  thaumcraft.api.wands.WandRod
 *  thaumcraft.client.lib.UtilsFX
 *  thaumcraft.common.config.Config
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.entities.golems.ItemGolemBell
 *  thaumcraft.common.entities.golems.ItemGolemPlacer
 *  thaumcraft.common.items.wands.ItemWandCasting
 *  thaumcraft.common.tiles.TileJarFillable
 */
package flaxbeard.thaumicexploration.event;

import baubles.api.BaublesApi;
import codechicken.lib.packet.PacketCustom;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import flaxbeard.thaumicexploration.ThaumicExploration;
import flaxbeard.thaumicexploration.ai.EntityAICreeperDummy;
import flaxbeard.thaumicexploration.ai.EntityAINearestAttackablePureTarget;
import flaxbeard.thaumicexploration.data.BoundJarNetworkManager;
import flaxbeard.thaumicexploration.data.TXWorldData;
import flaxbeard.thaumicexploration.entity.EntityTaintacleMinion;
import flaxbeard.thaumicexploration.event.DamageSourceTX;
import flaxbeard.thaumicexploration.tile.TileEntityAutoSorter;
import flaxbeard.thaumicexploration.tile.TileEntityBoundChest;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAICreeperSwell;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Tuple;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.WorldEvent;
import org.apache.commons.lang3.tuple.MutablePair;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.damagesource.DamageSourceThaumcraft;
import thaumcraft.api.entities.ITaintedMob;
import thaumcraft.api.wands.WandRod;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.golems.ItemGolemBell;
import thaumcraft.common.entities.golems.ItemGolemPlacer;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.tiles.TileJarFillable;

public class TXEventHandler {
    private HashMap<String, ArrayList<EntityItem>> lastKilled = new HashMap();

    @SubscribeEvent
    public void handleWorldLoad(WorldEvent.Load event) {
        TXWorldData.get(event.world);
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void renderLast(RenderWorldLastEvent event) {
        float partialTicks = event.partialTicks;
        Minecraft mc = Minecraft.func_71410_x();
        if (Minecraft.func_71410_x().field_71451_h instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().field_71451_h;
            long time = System.currentTimeMillis();
            if (player.field_71071_by.func_70448_g() != null && (player.field_71071_by.func_70448_g().func_77973_b() instanceof ItemGolemPlacer || player.field_71071_by.func_70448_g().func_77973_b() instanceof ItemGolemBell)) {
                this.renderMarkedBlocks(event, partialTicks, player, time);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void renderMarkedBlocks(RenderWorldLastEvent event, float partialTicks, EntityPlayer player, long time) {
        Minecraft mc = Minecraft.func_71410_x();
        if (player.field_71071_by.func_70448_g().func_77942_o() && !player.field_71071_by.func_70448_g().field_77990_d.func_74764_b("golemid")) {
            ItemStack item;
            Object golem = null;
            ChunkCoordinates cc = null;
            int face = -1;
            if (player.field_71071_by.func_70448_g().func_77973_b() instanceof ItemGolemBell) {
                item = player.field_71071_by.func_70448_g();
                cc = new ChunkCoordinates(item.field_77990_d.func_74762_e("brainx"), item.field_77990_d.func_74762_e("brainy"), item.field_77990_d.func_74762_e("brainz"));
                if (player.field_70170_p.func_147438_o(item.field_77990_d.func_74762_e("brainx"), item.field_77990_d.func_74762_e("brainy"), item.field_77990_d.func_74762_e("brainz")) == null || !(player.field_70170_p.func_147438_o(item.field_77990_d.func_74762_e("brainx"), item.field_77990_d.func_74762_e("brainy"), item.field_77990_d.func_74762_e("brainz")) instanceof TileEntityAutoSorter)) {
                    return;
                }
            }
            GL11.glPushMatrix();
            GL11.glAlphaFunc((int)516, (float)0.003921569f);
            if (cc != null && player.func_70092_e((double)cc.field_71574_a, (double)cc.field_71572_b, (double)cc.field_71573_c) < 4096.0) {
                for (int i = 0; i < 6; ++i) {
                    GL11.glPushMatrix();
                    this.drawGolemHomeOverlay(cc.field_71574_a + ForgeDirection.getOrientation((int)i).offsetX, cc.field_71572_b + ForgeDirection.getOrientation((int)i).offsetY, cc.field_71573_c + ForgeDirection.getOrientation((int)i).offsetZ, i, partialTicks);
                    GL11.glPopMatrix();
                }
            }
            item = player.field_71071_by.func_70448_g();
            TileEntityAutoSorter sorter = (TileEntityAutoSorter)player.field_70170_p.func_147438_o(item.field_77990_d.func_74762_e("brainx"), item.field_77990_d.func_74762_e("brainy"), item.field_77990_d.func_74762_e("brainz"));
            if (sorter != null) {
                for (MutablePair chest : sorter.chests) {
                    ChunkCoordinates coord = (ChunkCoordinates)chest.left;
                    int s = (Integer)chest.right;
                    double x = coord.field_71574_a;
                    double y = coord.field_71572_b;
                    double z = coord.field_71573_c;
                    int ox = coord.field_71574_a;
                    int oy = coord.field_71572_b;
                    int oz = coord.field_71573_c;
                    if (!(player.func_70092_e(x += (double)ForgeDirection.getOrientation((int)s).offsetX, y += (double)ForgeDirection.getOrientation((int)s).offsetY, z += (double)ForgeDirection.getOrientation((int)s).offsetZ) < 4096.0)) continue;
                    GL11.glPushMatrix();
                    this.drawMarkerOverlay(x, y, z, s, partialTicks, 0);
                    GL11.glPopMatrix();
                    if (player.field_70170_p.func_147437_c(ox, oy, oz)) {
                        GL11.glPushMatrix();
                        this.drawAirBlockoverlay(ox + ForgeDirection.getOrientation((int)s).offsetX, oy + ForgeDirection.getOrientation((int)s).offsetY, oz + ForgeDirection.getOrientation((int)s).offsetZ, s, partialTicks, 0);
                        GL11.glPopMatrix();
                    }
                    if (Config.golemLinkQuality <= 3) continue;
                    GL11.glPushMatrix();
                    this.drawMarkerLine(x -= (double)ForgeDirection.getOrientation((int)s).offsetX * 0.5, y -= (double)ForgeDirection.getOrientation((int)s).offsetY * 0.5, z -= (double)ForgeDirection.getOrientation((int)s).offsetZ * 0.5, s, partialTicks, 0, sorter);
                    GL11.glPopMatrix();
                }
            }
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glAlphaFunc((int)516, (float)0.1f);
            GL11.glPopMatrix();
        }
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
        UtilsFX.renderQuadCenteredFromTexture((String)"textures/misc/mark.png", (float)1.0f, (float)r, (float)g, (float)b, (int)200, (int)1, (float)1.0f);
        GL11.glPopMatrix();
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2884);
        GL11.glDepthMask((boolean)true);
        GL11.glPopMatrix();
    }

    public void drawMarkerLine(double x, double y, double z, int side, float partialTicks, int color, TileEntityAutoSorter sorter) {
        EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().field_71451_h;
        double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * (double)partialTicks;
        double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * (double)partialTicks;
        double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * (double)partialTicks;
        double ePX = (float)sorter.field_145851_c * partialTicks;
        double ePY = (float)sorter.field_145848_d * partialTicks;
        double ePZ = (float)sorter.field_145849_e * partialTicks;
        GL11.glTranslated((double)(-iPX + ePX), (double)(-iPY + ePY + 0.5), (double)(-iPZ + ePZ));
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
        double ds1y = ePY + 0.5;
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
        UtilsFX.bindTexture((String)"textures/misc/script.png");
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
        UtilsFX.renderQuadCenteredFromTexture((String)"textures/blocks/empty.png", (float)1.0f, (float)r, (float)g, (float)b, (int)200, (int)1, (float)1.0f);
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
        UtilsFX.renderQuadCenteredFromTexture((String)"textures/misc/home.png", (float)1.0f, (float)r, (float)g, (float)b, (int)200, (int)1, (float)1.0f);
        GL11.glPopMatrix();
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2884);
        GL11.glDepthMask((boolean)true);
        GL11.glPopMatrix();
    }

    @SubscribeEvent
    public void handleTaintSpawns(EntityJoinWorldEvent event) {
        if (event.entity instanceof EntityPlayer) {
            // empty if block
        }
        if (event.entity instanceof ITaintedMob) {
            EntityLiving mob = (EntityLiving)event.entity;
            ArrayList<EntityAITasks.EntityAITaskEntry> tasksToRemove = new ArrayList<EntityAITasks.EntityAITaskEntry>();
            for (Object e : mob.field_70715_bh.field_75782_a) {
                EntityAITasks.EntityAITaskEntry entry2 = (EntityAITasks.EntityAITaskEntry)e;
                if (!(entry2.field_75733_a instanceof EntityAINearestAttackableTarget)) continue;
                tasksToRemove.add((EntityAITasks.EntityAITaskEntry)e);
            }
            for (EntityAITasks.EntityAITaskEntry entityAITaskEntry : tasksToRemove) {
                mob.field_70715_bh.func_85156_a(entityAITaskEntry.field_75733_a);
            }
            mob.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAINearestAttackablePureTarget((EntityCreature)mob, EntityPlayer.class, 0, true));
        }
    }

    @SubscribeEvent
    public void handleMobDrop(LivingDropsEvent event) {
        if (event.source == DamageSourceTX.soulCrucible) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void handleTeleport(EnderTeleportEvent event) {
        if ((event.entityLiving instanceof EntityEnderman || event.entityLiving instanceof EntityPlayer) && event.entityLiving.func_70644_a(ThaumicExploration.potionBinding)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void stopCreeperExplosions(LivingEvent.LivingUpdateEvent event) {
        block4: {
            int size;
            EntityCreeper creeper;
            block3: {
                ItemStack heldItem;
                int nightVision;
                if (!(event.entityLiving.func_71124_b(4) == null || (nightVision = EnchantmentHelper.func_77506_a((int)ThaumicExploration.enchantmentNightVision.field_77352_x, (ItemStack)(heldItem = event.entityLiving.func_71124_b(4)))) <= 0 || event.entityLiving.func_82165_m(Potion.field_76439_r.field_76415_H) && event.entityLiving.func_70660_b(Potion.field_76439_r).func_76459_b() >= 202)) {
                    event.entityLiving.func_70690_d(new PotionEffect(Potion.field_76439_r.field_76415_H, 202, 1));
                }
                if (!(event.entityLiving instanceof EntityCreeper) || !event.entityLiving.func_70644_a(ThaumicExploration.potionBinding)) break block3;
                creeper = (EntityCreeper)event.entityLiving;
                size = creeper.field_70714_bg.field_75782_a.size();
                for (int i = 0; i < size; ++i) {
                    EntityAITasks.EntityAITaskEntry entry = (EntityAITasks.EntityAITaskEntry)creeper.field_70714_bg.field_75782_a.get(i);
                    if (!(entry.field_75733_a instanceof EntityAICreeperSwell)) continue;
                    entry.field_75733_a = new EntityAICreeperDummy(creeper);
                    creeper.field_70714_bg.field_75782_a.set(i, entry);
                }
                break block4;
            }
            if (!(event.entityLiving instanceof EntityCreeper)) break block4;
            creeper = (EntityCreeper)event.entityLiving;
            size = creeper.field_70714_bg.field_75782_a.size();
            for (int i = 0; i < size; ++i) {
                EntityAITasks.EntityAITaskEntry entry = (EntityAITasks.EntityAITaskEntry)creeper.field_70714_bg.field_75782_a.get(i);
                if (!(entry.field_75733_a instanceof EntityAICreeperDummy)) continue;
                entry.field_75733_a = new EntityAICreeperSwell(creeper);
                creeper.field_70714_bg.field_75782_a.set(i, entry);
                creeper.func_70829_a(1);
            }
        }
    }

    @SubscribeEvent
    public void handleEnchantmentAttack(LivingAttackEvent event) {
        int binding;
        ItemStack heldItem;
        EntityLivingBase attacker;
        if ((event.entityLiving instanceof EntityEnderman || event.entityLiving instanceof EntityCreeper || event.entityLiving instanceof EntityPlayer) && event.source.func_76364_f() instanceof EntityLivingBase) {
            attacker = (EntityLivingBase)event.source.func_76364_f();
            heldItem = attacker.func_70694_bm();
            if (heldItem == null) {
                return;
            }
            binding = EnchantmentHelper.func_77506_a((int)ThaumicExploration.enchantmentBinding.field_77352_x, (ItemStack)heldItem);
            if (binding > 1) {
                event.entityLiving.func_70690_d(new PotionEffect(ThaumicExploration.potionBinding.field_76415_H, 50, 1));
            }
        }
        if (event.source.func_76364_f() instanceof EntityLivingBase) {
            attacker = (EntityLivingBase)event.source.func_76364_f();
            heldItem = attacker.func_70694_bm();
            if (heldItem == null) {
                return;
            }
            binding = EnchantmentHelper.func_77506_a((int)ThaumicExploration.enchantmentBinding.field_77352_x, (ItemStack)heldItem);
            if (binding > 0) {
                event.entityLiving.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 50, 1));
            }
        }
        if (event.source.func_76364_f() instanceof EntityLivingBase) {
            attacker = (EntityLivingBase)event.source.func_76364_f();
            heldItem = attacker.func_70694_bm();
            if (heldItem == null) {
                return;
            }
            int disarm = EnchantmentHelper.func_77506_a((int)ThaumicExploration.enchantmentDisarm.field_77352_x, (ItemStack)heldItem);
            if (disarm > 0 && !(event.entityLiving instanceof EntityPlayer) && event.entityLiving.func_70694_bm() != null && !event.entityLiving.field_70170_p.field_72995_K && event.entityLiving.field_70170_p.field_73012_v.nextInt(10 - 2 * disarm) == 0) {
                ItemStack itemstack = event.entityLiving.func_70694_bm();
                event.entityLiving.func_70062_b(0, null);
                World world = event.entityLiving.field_70170_p;
                double x = event.entityLiving.field_70165_t;
                double y = event.entityLiving.field_70163_u;
                double z = event.entityLiving.field_70161_v;
                float f = world.field_73012_v.nextFloat() * 0.8f + 0.1f;
                float f1 = world.field_73012_v.nextFloat() * 0.8f + 0.1f;
                float f2 = world.field_73012_v.nextFloat();
                int k1 = world.field_73012_v.nextInt(21) + 10;
                k1 = itemstack.field_77994_a;
                EntityItem entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.func_77973_b(), k1, itemstack.func_77960_j()));
                float f3 = 0.05f;
                entityitem.field_70159_w = (float)world.field_73012_v.nextGaussian() * f3;
                entityitem.field_70181_x = (float)world.field_73012_v.nextGaussian() * f3 + 0.2f;
                entityitem.field_70179_y = (float)world.field_73012_v.nextGaussian() * f3;
                if (itemstack.func_77942_o()) {
                    entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b());
                }
                world.func_72838_d((Entity)entityitem);
            }
        }
        if (event.source.func_76364_f() instanceof EntityLivingBase) {
            ItemWandCasting wand;
            WandRod test;
            attacker = (EntityLivingBase)event.source.func_76364_f();
            heldItem = attacker.func_70694_bm();
            if (heldItem == null) {
                return;
            }
            if (heldItem.func_77973_b() instanceof ItemWandCasting && (test = (wand = (ItemWandCasting)heldItem.func_77973_b()).getRod(heldItem)) == ThaumicExploration.STAFF_ROD_NECRO) {
                event.entityLiving.func_70690_d(new PotionEffect(Potion.field_82731_v.field_76415_H, 100));
            }
        }
    }

    private ArrayList<ChunkCoordinates> safeBlocks(EntityPlayer player) {
        ArrayList<ChunkCoordinates> safeSpots = new ArrayList<ChunkCoordinates>();
        for (int x = -2; x < 3; ++x) {
            for (int z = -2; z < 3; ++z) {
                if (!player.field_70170_p.func_147437_c((int)player.field_70165_t + x, (int)player.field_70163_u, (int)player.field_70161_v + z) || player.field_70170_p.func_147437_c((int)player.field_70165_t + x, (int)player.field_70163_u - 1, (int)player.field_70161_v + z) || !player.field_70170_p.isSideSolid((int)player.field_70165_t + x, (int)player.field_70163_u - 1, (int)player.field_70161_v + z, ForgeDirection.UP)) continue;
                safeSpots.add(new ChunkCoordinates((int)player.field_70165_t + x, (int)player.field_70163_u, (int)player.field_70161_v + z));
            }
        }
        return safeSpots;
    }

    private boolean hasBauble(Item item, IInventory inventory) {
        for (int i = 0; i < inventory.func_70302_i_(); ++i) {
            if (inventory.func_70301_a(i) == null || inventory.func_70301_a(i).func_77973_b() != item) continue;
            return true;
        }
        return false;
    }

    @SubscribeEvent
    public void handleTaint(LivingHurtEvent event) {
        EntityPlayer player;
        if (event.entityLiving instanceof EntityPlayer) {
            ArrayList<ChunkCoordinates> safeSpots;
            player = (EntityPlayer)event.entityLiving;
            if (player.field_70122_E && this.hasBauble(ThaumicExploration.tentacleRing, BaublesApi.getBaubles((EntityPlayer)player)) && (safeSpots = this.safeBlocks(player)).size() > 0) {
                int rand = player.field_70170_p.field_73012_v.nextInt(safeSpots.size());
                EntityTaintacleMinion minion = new EntityTaintacleMinion(player.field_70170_p);
                minion.func_70107_b((float)safeSpots.get((int)rand).field_71574_a + 0.5f, safeSpots.get((int)rand).field_71572_b, (float)safeSpots.get((int)rand).field_71573_c + 0.5f);
                player.field_70170_p.func_72838_d((Entity)minion);
                minion.playSpawnSound();
            }
        }
        if (event.entityLiving.field_70170_p.field_73012_v.nextInt(4) < 3) {
            if (event.source.field_76373_n == "mob" && event.source.func_76364_f() instanceof ITaintedMob && event.entityLiving instanceof EntityPlayer) {
                player = (EntityPlayer)event.entityLiving;
                for (int i = 0; i < 10; ++i) {
                    if (player.field_71071_by.func_70301_a(i) == null || player.field_71071_by.func_70301_a(i).func_77973_b() != ThaumicExploration.charmNoTaint) continue;
                    event.setCanceled(true);
                    break;
                }
            }
            if ((event.source == DamageSourceThaumcraft.taint || event.source == DamageSourceThaumcraft.tentacle || event.source == DamageSourceThaumcraft.swarm) && event.entityLiving instanceof EntityPlayer) {
                player = (EntityPlayer)event.entityLiving;
                for (int i = 0; i < 10; ++i) {
                    if (player.field_71071_by.func_70301_a(i) == null || player.field_71071_by.func_70301_a(i).func_77973_b() != ThaumicExploration.charmNoTaint) continue;
                    event.setCanceled(true);
                    break;
                }
            }
        }
    }

    @SubscribeEvent
    public void handleItemUse(PlayerInteractEvent event) {
        int type = 0;
        if (event.entityPlayer.field_70170_p.func_72899_e(event.x, event.y, event.z)) {
            if (event.entityPlayer.func_71045_bC() != null && event.entityPlayer.func_71045_bC().func_77973_b() == ConfigItems.itemGolemBell) {
                ItemStack stack = event.entityPlayer.func_71045_bC();
                if (event.entityPlayer.field_70170_p.func_147439_a(event.x, event.y, event.z) == ThaumicExploration.autoSorter) {
                    if (stack.func_77942_o()) {
                        stack.func_77978_p().func_82580_o("golemid");
                        stack.func_77978_p().func_82580_o("markers");
                        stack.func_77978_p().func_82580_o("golemhomex");
                        stack.func_77978_p().func_82580_o("golemhomey");
                        stack.func_77978_p().func_82580_o("golemhomez");
                        stack.func_77978_p().func_82580_o("golemhomeface");
                        stack.func_77978_p().func_74768_a("brainx", event.x);
                        stack.func_77978_p().func_74768_a("brainy", event.y);
                        stack.func_77978_p().func_74768_a("brainz", event.z);
                        stack.func_77978_p().func_74768_a("brain", event.z);
                    }
                    if (event.entityPlayer.field_70170_p.field_72995_K && event.entityPlayer != null) {
                        event.entityPlayer.func_71038_i();
                    }
                }
                if (!stack.func_77942_o()) {
                    stack.func_77982_d(new NBTTagCompound());
                }
                if (!(event.entityPlayer.field_70170_p.func_147438_o(event.x, event.y, event.z) == null || stack.func_77942_o() && stack.func_77978_p().func_74764_b("golemid") || !stack.func_77978_p().func_74764_b("brainx"))) {
                    ChunkCoordinates cc = new ChunkCoordinates(event.x, event.y, event.z);
                    if (event.entityPlayer.field_70170_p.func_147438_o(event.x, event.y, event.z) instanceof IInventory) {
                        TileEntityAutoSorter sorter = (TileEntityAutoSorter)event.entityPlayer.field_70170_p.func_147438_o(stack.func_77978_p().func_74762_e("brainx"), stack.func_77978_p().func_74762_e("brainy"), stack.func_77978_p().func_74762_e("brainz"));
                        if (sorter.chests.contains(MutablePair.of((Object)cc, (Object)event.face))) {
                            sorter.chests.remove(MutablePair.of((Object)cc, (Object)event.face));
                        } else {
                            if (!event.entityPlayer.field_70170_p.field_72995_K) {
                                FMLNetworkHandler.openGui((EntityPlayer)event.entityPlayer, (Object)ThaumicExploration.instance, (int)(2 + event.face), (World)event.entityPlayer.field_70170_p, (int)event.x, (int)event.y, (int)event.z);
                            }
                            sorter.chests.add(MutablePair.of((Object)cc, (Object)event.face));
                        }
                    }
                }
            }
            if (event.entityPlayer.field_70170_p.func_147439_a(event.x, event.y, event.z) == Blocks.field_150486_ae) {
                if (event.entityPlayer.field_71071_by.func_70448_g() != null) {
                    if (event.entityPlayer.field_71071_by.func_70448_g().func_77973_b() == ThaumicExploration.chestSeal) {
                        type = 1;
                    } else if (event.entityPlayer.field_71071_by.func_70448_g().func_77973_b() == ThaumicExploration.chestSealLinked) {
                        type = 2;
                    }
                }
            } else if (event.entityPlayer.field_70170_p.func_147439_a(event.x, event.y, event.z) == ThaumicExploration.boundChest) {
                World world = event.entityPlayer.field_70170_p;
                if (event.entityPlayer.field_71071_by.func_70448_g() != null && event.entityPlayer.field_71071_by.func_70448_g().func_77973_b() == ThaumicExploration.chestSeal) {
                    int color = ((TileEntityBoundChest)world.func_147438_o((int)event.x, (int)event.y, (int)event.z)).clientColor;
                    type = 3;
                    if (15 - event.entityPlayer.field_71071_by.func_70448_g().func_77960_j() == color) {
                        int nextID = ((TileEntityBoundChest)world.func_147438_o((int)event.x, (int)event.y, (int)event.z)).id;
                        ItemStack linkedSeal = new ItemStack(ThaumicExploration.chestSealLinked, 1, event.entityPlayer.field_71071_by.func_70448_g().func_77960_j());
                        NBTTagCompound tag = new NBTTagCompound();
                        tag.func_74768_a("ID", nextID);
                        tag.func_74768_a("x", event.x);
                        tag.func_74768_a("y", event.y);
                        tag.func_74768_a("z", event.z);
                        tag.func_74768_a("dim", world.field_73011_w.field_76574_g);
                        linkedSeal.func_77982_d(tag);
                        event.entityPlayer.field_71071_by.func_70441_a(linkedSeal);
                        if (!event.entityPlayer.field_71075_bZ.field_75098_d) {
                            event.entityPlayer.field_71071_by.func_70298_a(event.entityPlayer.field_71071_by.field_70461_c, 1);
                        }
                    }
                    event.setCanceled(true);
                }
            }
        }
        if (event.entityPlayer.field_70170_p.func_72899_e(event.x, event.y, event.z) && event.entityPlayer.field_70170_p.func_147439_a(event.x, event.y, event.z) == ConfigBlocks.blockJar && event.entityPlayer.field_70170_p.func_72805_g(event.x, event.y, event.z) == 0 && event.entityPlayer.field_71071_by.func_70448_g() != null && ((TileJarFillable)event.entityPlayer.field_70170_p.func_147438_o((int)event.x, (int)event.y, (int)event.z)).aspectFilter == null && ((TileJarFillable)event.entityPlayer.field_70170_p.func_147438_o((int)event.x, (int)event.y, (int)event.z)).amount == 0) {
            if (event.entityPlayer.field_71071_by.func_70448_g().func_77973_b() == ThaumicExploration.jarSeal) {
                type = 4;
            } else if (event.entityPlayer.field_71071_by.func_70448_g().func_77973_b() == ThaumicExploration.jarSealLinked) {
                type = 5;
            }
        }
        if (event.entityPlayer.field_70170_p.field_72995_K && type > 0) {
            ByteBuf buf = Unpooled.buffer();
            ByteBufOutputStream out = new ByteBufOutputStream(buf);
            try {
                out.writeByte(1);
                out.writeInt(event.entityPlayer.field_70170_p.field_73011_w.field_76574_g);
                out.writeInt(event.x);
                out.writeInt(event.y);
                out.writeInt(event.z);
                out.writeByte(type);
                out.writeInt(event.entityPlayer.func_145782_y());
                FMLProxyPacket packet = new FMLProxyPacket(buf, "tExploration");
                ThaumicExploration.channel.sendToServer(packet);
                out.close();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @SubscribeEvent
    public void playerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        for (Map.Entry<String, AspectList> entry : BoundJarNetworkManager.getData().networks.entrySet()) {
            PacketCustom.sendToPlayer((Packet)BoundJarNetworkManager.getPacket(new Tuple((Object)entry.getKey(), (Object)entry.getValue())), (EntityPlayer)event.player);
        }
    }
}

