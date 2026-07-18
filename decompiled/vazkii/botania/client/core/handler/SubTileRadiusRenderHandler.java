/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.core.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.subtile.ISubTileContainer;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileEntity;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.common.Botania;
import vazkii.botania.common.item.ItemTwigWand;
import vazkii.botania.common.item.ModItems;

public final class SubTileRadiusRenderHandler {
    @SubscribeEvent
    public void onWorldRenderLast(RenderWorldLastEvent event) {
        TileEntity tile;
        Minecraft mc = Minecraft.func_71410_x();
        MovingObjectPosition pos = mc.field_71476_x;
        if (!Botania.proxy.isClientPlayerWearingMonocle() || pos == null || pos.field_72308_g != null) {
            return;
        }
        int x = pos.field_72311_b;
        int y = pos.field_72312_c;
        int z = pos.field_72309_d;
        ItemStack stackHeld = mc.field_71439_g.func_71045_bC();
        if (stackHeld != null && stackHeld.func_77973_b() == ModItems.twigWand && ItemTwigWand.getBindMode(stackHeld)) {
            ChunkCoordinates coords = ItemTwigWand.getBoundTile(stackHeld);
            if (coords.field_71572_b != -1) {
                x = coords.field_71574_a;
                y = coords.field_71572_b;
                z = coords.field_71573_c;
            }
        }
        if ((tile = mc.field_71441_e.func_147438_o(x, y, z)) == null || !(tile instanceof ISubTileContainer)) {
            return;
        }
        ISubTileContainer container = (ISubTileContainer)tile;
        SubTileEntity subtile = container.getSubTile();
        if (subtile == null) {
            return;
        }
        RadiusDescriptor descriptor = subtile.getRadius();
        if (descriptor == null) {
            return;
        }
        GL11.glPushMatrix();
        GL11.glDisable((int)3553);
        GL11.glPushAttrib((int)2896);
        GL11.glDisable((int)2896);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        Tessellator.renderingWorldRenderer = false;
        if (descriptor.isCircle()) {
            this.renderCircle(descriptor.getSubtileCoords(), descriptor.getCircleRadius());
        } else {
            this.renderRectangle(descriptor.getAABB());
        }
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public void renderRectangle(AxisAlignedBB aabb) {
        GL11.glPushMatrix();
        GL11.glTranslated((double)(aabb.field_72340_a - RenderManager.field_78725_b), (double)(aabb.field_72338_b - RenderManager.field_78726_c), (double)(aabb.field_72339_c - RenderManager.field_78723_d));
        int color = Color.HSBtoRGB((float)(ClientTickHandler.ticksInGame % 200) / 200.0f, 0.6f, 1.0f);
        Color colorRGB = new Color(color);
        GL11.glColor4ub((byte)((byte)colorRGB.getRed()), (byte)((byte)colorRGB.getGreen()), (byte)((byte)colorRGB.getBlue()), (byte)32);
        double f = 0.0625;
        double x = aabb.field_72336_d - aabb.field_72340_a - f;
        double z = aabb.field_72334_f - aabb.field_72339_c - f;
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78377_a(x, f, f);
        tessellator.func_78377_a(f, f, f);
        tessellator.func_78377_a(f, f, z);
        tessellator.func_78377_a(x, f, z);
        tessellator.func_78381_a();
        z += f;
        double f1 = f + f / 4.0;
        GL11.glColor4ub((byte)((byte)colorRGB.getRed()), (byte)((byte)colorRGB.getGreen()), (byte)((byte)colorRGB.getBlue()), (byte)64);
        tessellator.func_78382_b();
        tessellator.func_78377_a(x += f, f1, 0.0);
        tessellator.func_78377_a(0.0, f1, 0.0);
        tessellator.func_78377_a(0.0, f1, z);
        tessellator.func_78377_a(x, f1, z);
        tessellator.func_78381_a();
        GL11.glPopMatrix();
    }

    public void renderCircle(ChunkCoordinates center, double radius) {
        GL11.glPushMatrix();
        double x = (double)center.field_71574_a + 0.5;
        double y = center.field_71572_b;
        double z = (double)center.field_71573_c + 0.5;
        GL11.glTranslated((double)(x - RenderManager.field_78725_b), (double)(y - RenderManager.field_78726_c), (double)(z - RenderManager.field_78723_d));
        int color = Color.HSBtoRGB((float)(ClientTickHandler.ticksInGame % 200) / 200.0f, 0.6f, 1.0f);
        Color colorRGB = new Color(color);
        GL11.glColor4ub((byte)((byte)colorRGB.getRed()), (byte)((byte)colorRGB.getGreen()), (byte)((byte)colorRGB.getBlue()), (byte)32);
        double f = 0.0625;
        int totalAngles = 360;
        int drawAngles = 360;
        int step = totalAngles / drawAngles;
        radius -= f;
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78371_b(6);
        tessellator.func_78377_a(0.0, f, 0.0);
        for (int i = 0; i < totalAngles + 1; i += step) {
            double rad = (double)(totalAngles - i) * Math.PI / 180.0;
            double xp = Math.cos(rad) * radius;
            double zp = Math.sin(rad) * radius;
            tessellator.func_78377_a(xp, f, zp);
        }
        tessellator.func_78377_a(0.0, f, 0.0);
        tessellator.func_78381_a();
        radius += f;
        double f1 = f + f / 4.0;
        GL11.glColor4ub((byte)((byte)colorRGB.getRed()), (byte)((byte)colorRGB.getGreen()), (byte)((byte)colorRGB.getBlue()), (byte)64);
        tessellator.func_78371_b(6);
        tessellator.func_78377_a(0.0, f1, 0.0);
        for (int i = 0; i < totalAngles + 1; i += step) {
            double rad = (double)(totalAngles - i) * Math.PI / 180.0;
            double xp = Math.cos(rad) * radius;
            double zp = Math.sin(rad) * radius;
            tessellator.func_78377_a(xp, f1, zp);
        }
        tessellator.func_78377_a(0.0, f1, 0.0);
        tessellator.func_78381_a();
        GL11.glPopMatrix();
    }
}

