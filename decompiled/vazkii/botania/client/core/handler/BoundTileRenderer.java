/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.core.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.awt.Color;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.item.IExtendedWireframeCoordinateListProvider;
import vazkii.botania.api.item.IWireframeCoordinateListProvider;
import vazkii.botania.api.wand.ICoordBoundItem;
import vazkii.botania.api.wand.IWireframeAABBProvider;
import vazkii.botania.client.core.handler.ClientTickHandler;

public final class BoundTileRenderer {
    @SubscribeEvent
    public void onWorldRenderLast(RenderWorldLastEvent event) {
        int invSize;
        ChunkCoordinates coords;
        GL11.glPushMatrix();
        GL11.glPushAttrib((int)2896);
        GL11.glDisable((int)2929);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2896);
        GL11.glEnable((int)3042);
        Tessellator.renderingWorldRenderer = false;
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        ItemStack stack = player.func_71045_bC();
        int color = Color.HSBtoRGB((float)(ClientTickHandler.ticksInGame % 200) / 200.0f, 0.6f, 1.0f);
        if (stack != null && stack.func_77973_b() instanceof ICoordBoundItem && (coords = ((ICoordBoundItem)stack.func_77973_b()).getBinding(stack)) != null) {
            this.renderBlockOutlineAt(coords, color);
        }
        InventoryPlayer mainInv = player.field_71071_by;
        IInventory baublesInv = BotaniaAPI.internalHandler.getBaublesInventory((EntityPlayer)player);
        int size = invSize = mainInv.func_70302_i_();
        if (baublesInv != null) {
            size += baublesInv.func_70302_i_();
        }
        for (int i = 0; i < size; ++i) {
            ChunkCoordinates coords2;
            boolean useBaubles = i >= invSize;
            Object inv = useBaubles ? baublesInv : mainInv;
            ItemStack stackInSlot = inv.func_70301_a(i - (useBaubles ? invSize : 0));
            if (stackInSlot == null || !(stackInSlot.func_77973_b() instanceof IWireframeCoordinateListProvider)) continue;
            IWireframeCoordinateListProvider provider = (IWireframeCoordinateListProvider)stackInSlot.func_77973_b();
            List<ChunkCoordinates> coordsList = provider.getWireframesToDraw((EntityPlayer)player, stackInSlot);
            if (coordsList != null) {
                for (ChunkCoordinates coords3 : coordsList) {
                    this.renderBlockOutlineAt(coords3, color);
                }
            }
            if (!(stackInSlot.func_77973_b() instanceof IExtendedWireframeCoordinateListProvider) || (coords2 = ((IExtendedWireframeCoordinateListProvider)stackInSlot.func_77973_b()).getSourceWireframe((EntityPlayer)player, stackInSlot)) == null || coords2.field_71572_b <= -1) continue;
            this.renderBlockOutlineAt(coords2, color, 5.0f);
        }
        GL11.glEnable((int)2929);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    private void renderBlockOutlineAt(ChunkCoordinates pos, int color) {
        this.renderBlockOutlineAt(pos, color, 1.0f);
    }

    private void renderBlockOutlineAt(ChunkCoordinates pos, int color, float thickness) {
        AxisAlignedBB axis;
        GL11.glPushMatrix();
        GL11.glTranslated((double)((double)pos.field_71574_a - RenderManager.field_78725_b), (double)((double)pos.field_71572_b - RenderManager.field_78726_c), (double)((double)pos.field_71573_c - RenderManager.field_78723_d + 1.0));
        Color colorRGB = new Color(color);
        GL11.glColor4ub((byte)((byte)colorRGB.getRed()), (byte)((byte)colorRGB.getGreen()), (byte)((byte)colorRGB.getBlue()), (byte)-1);
        WorldClient world = Minecraft.func_71410_x().field_71441_e;
        Block block = world.func_147439_a(pos.field_71574_a, pos.field_71572_b, pos.field_71573_c);
        if (block != null && (axis = block instanceof IWireframeAABBProvider ? ((IWireframeAABBProvider)block).getWireframeAABB((World)world, pos.field_71574_a, pos.field_71572_b, pos.field_71573_c) : block.func_149633_g((World)world, pos.field_71574_a, pos.field_71572_b, pos.field_71573_c)) != null) {
            axis.field_72340_a -= (double)pos.field_71574_a;
            axis.field_72336_d -= (double)pos.field_71574_a;
            axis.field_72338_b -= (double)pos.field_71572_b;
            axis.field_72337_e -= (double)pos.field_71572_b;
            axis.field_72339_c -= (double)(pos.field_71573_c + 1);
            axis.field_72334_f -= (double)(pos.field_71573_c + 1);
            GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glLineWidth((float)thickness);
            this.renderBlockOutline(axis);
            GL11.glLineWidth((float)(thickness + 3.0f));
            GL11.glColor4ub((byte)((byte)colorRGB.getRed()), (byte)((byte)colorRGB.getGreen()), (byte)((byte)colorRGB.getBlue()), (byte)64);
            this.renderBlockOutline(axis);
        }
        GL11.glPopMatrix();
    }

    private void renderBlockOutline(AxisAlignedBB aabb) {
        Tessellator tessellator = Tessellator.field_78398_a;
        double ix = aabb.field_72340_a;
        double iy = aabb.field_72338_b;
        double iz = aabb.field_72339_c;
        double ax = aabb.field_72336_d;
        double ay = aabb.field_72337_e;
        double az = aabb.field_72334_f;
        tessellator.func_78371_b(1);
        tessellator.func_78377_a(ix, iy, iz);
        tessellator.func_78377_a(ix, ay, iz);
        tessellator.func_78377_a(ix, ay, iz);
        tessellator.func_78377_a(ax, ay, iz);
        tessellator.func_78377_a(ax, ay, iz);
        tessellator.func_78377_a(ax, iy, iz);
        tessellator.func_78377_a(ax, iy, iz);
        tessellator.func_78377_a(ix, iy, iz);
        tessellator.func_78377_a(ix, iy, az);
        tessellator.func_78377_a(ix, ay, az);
        tessellator.func_78377_a(ix, iy, az);
        tessellator.func_78377_a(ax, iy, az);
        tessellator.func_78377_a(ax, iy, az);
        tessellator.func_78377_a(ax, ay, az);
        tessellator.func_78377_a(ix, ay, az);
        tessellator.func_78377_a(ax, ay, az);
        tessellator.func_78377_a(ix, iy, iz);
        tessellator.func_78377_a(ix, iy, az);
        tessellator.func_78377_a(ix, ay, iz);
        tessellator.func_78377_a(ix, ay, az);
        tessellator.func_78377_a(ax, iy, iz);
        tessellator.func_78377_a(ax, iy, az);
        tessellator.func_78377_a(ax, ay, iz);
        tessellator.func_78377_a(ax, ay, az);
        tessellator.func_78381_a();
    }
}

