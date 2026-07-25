/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  org.lwjgl.opengl.GL11
 */
package thaumic.tinkerer.client.core.handler.kami;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.item.kami.ItemPlacementMirror;

public final class PlacementMirrorPredictionRenderer {
    RenderBlocks blockRender = new RenderBlocks();

    @SubscribeEvent
    public void onWorldRenderLast(RenderWorldLastEvent event) {
        WorldClient world = Minecraft.func_71410_x().field_71441_e;
        List playerEntities = world.field_73010_i;
        for (EntityPlayer player : playerEntities) {
            ItemStack currentStack = player.func_71045_bC();
            if (currentStack == null || currentStack.func_77973_b() != ThaumicTinkerer.registry.getFirstItemFromClass(ItemPlacementMirror.class) || ItemPlacementMirror.getBlock(currentStack) == Blocks.field_150350_a) continue;
            this.renderPlayerLook(player, currentStack);
        }
    }

    private void renderPlayerLook(EntityPlayer player, ItemStack stack) {
        ChunkCoordinates[] coords = ItemPlacementMirror.getBlocksToPlace(stack, player);
        if (ItemPlacementMirror.hasBlocks(stack, player, coords)) {
            ItemStack block = new ItemStack(ItemPlacementMirror.getBlock(stack), 1, ItemPlacementMirror.getBlockMeta(stack));
            ChunkCoordinates lastCoords = new ChunkCoordinates(0, 0, 0);
            GL11.glPushMatrix();
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            for (ChunkCoordinates coord : coords) {
                this.renderBlockAt(block, coord, lastCoords);
                lastCoords = coord;
            }
            GL11.glPopMatrix();
        }
    }

    private void renderBlockAt(ItemStack block, ChunkCoordinates pos, ChunkCoordinates last) {
        if (block.func_77973_b() == null) {
            return;
        }
        GL11.glPushMatrix();
        GL11.glTranslated((double)((double)pos.field_71574_a + 0.5 - RenderManager.field_78725_b), (double)((double)pos.field_71572_b + 0.5 - RenderManager.field_78726_c), (double)((double)pos.field_71573_c + 0.5 - RenderManager.field_78723_d));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.6f);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110575_b);
        this.blockRender.field_147844_c = false;
        this.blockRender.func_147800_a(Block.func_149634_a((Item)block.func_77973_b()), block.func_77960_j(), 1.0f);
        GL11.glPopMatrix();
    }
}

