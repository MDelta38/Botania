/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$Action
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.core.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.lexicon.multiblock.IMultiblockRenderHook;
import vazkii.botania.api.lexicon.multiblock.Multiblock;
import vazkii.botania.api.lexicon.multiblock.MultiblockSet;
import vazkii.botania.api.lexicon.multiblock.component.MultiblockComponent;
import vazkii.botania.client.core.handler.MultiblockBlockAccess;

public final class MultiblockRenderHandler {
    public static boolean rendering = false;
    private static MultiblockBlockAccess blockAccess = new MultiblockBlockAccess();
    private static RenderBlocks blockRender = RenderBlocks.getInstance();
    public static MultiblockSet currentMultiblock;
    public static ChunkCoordinates anchor;
    public static int angle;
    public static int dimension;

    public static void setMultiblock(MultiblockSet set) {
        currentMultiblock = set;
        anchor = null;
        angle = 0;
        Minecraft mc = Minecraft.func_71410_x();
        if (mc.field_71441_e != null) {
            dimension = mc.field_71441_e.field_73011_w.field_76574_g;
        }
    }

    @SubscribeEvent
    public void onWorldRenderLast(RenderWorldLastEvent event) {
        Minecraft mc = Minecraft.func_71410_x();
        if (!(mc.field_71439_g == null || mc.field_71476_x == null || mc.field_71439_g.func_70093_af() && anchor == null)) {
            mc.field_71439_g.func_71045_bC();
            this.renderPlayerLook((EntityPlayer)mc.field_71439_g, mc.field_71476_x);
        }
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (currentMultiblock != null && anchor == null && event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && event.entityPlayer == Minecraft.func_71410_x().field_71439_g) {
            anchor = new ChunkCoordinates(event.x, event.y, event.z);
            angle = MathHelper.func_76128_c((double)((double)event.entityPlayer.field_70177_z * 4.0 / 360.0 + 0.5)) & 3;
            event.setCanceled(true);
        }
    }

    private void renderPlayerLook(EntityPlayer player, MovingObjectPosition src) {
        if (currentMultiblock != null && dimension == player.field_70170_p.field_73011_w.field_76574_g) {
            int anchorX = anchor != null ? MultiblockRenderHandler.anchor.field_71574_a : src.field_72311_b;
            int anchorY = anchor != null ? MultiblockRenderHandler.anchor.field_71572_b : src.field_72312_c;
            int anchorZ = anchor != null ? MultiblockRenderHandler.anchor.field_71573_c : src.field_72309_d;
            GL11.glPushMatrix();
            GL11.glPushAttrib((int)2896);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glDisable((int)2896);
            rendering = true;
            Multiblock mb = anchor != null ? currentMultiblock.getForIndex(angle) : currentMultiblock.getForEntity((Entity)player);
            boolean didAny = false;
            blockAccess.update((IBlockAccess)player.field_70170_p, mb, anchorX, anchorY, anchorZ);
            for (MultiblockComponent comp : mb.getComponents()) {
                if (!this.renderComponentInWorld(player.field_70170_p, mb, comp, anchorX, anchorY, anchorZ)) continue;
                didAny = true;
            }
            rendering = false;
            GL11.glPopAttrib();
            GL11.glPopMatrix();
            if (!didAny) {
                MultiblockRenderHandler.setMultiblock(null);
                player.func_146105_b(new ChatComponentTranslation("botaniamisc.structureComplete", new Object[0]).func_150255_a(new ChatStyle().func_150238_a(EnumChatFormatting.GREEN)));
            }
        }
    }

    private boolean renderComponentInWorld(World world, Multiblock mb, MultiblockComponent comp, int anchorX, int anchorY, int anchorZ) {
        ChunkCoordinates pos = comp.getRelativePosition();
        int x = pos.field_71574_a + anchorX;
        int y = pos.field_71572_b + anchorY;
        int z = pos.field_71573_c + anchorZ;
        if (anchor != null && comp.matches(world, x, y, z)) {
            return false;
        }
        GL11.glPushMatrix();
        GL11.glTranslated((double)(-RenderManager.field_78725_b), (double)(-RenderManager.field_78726_c), (double)(-RenderManager.field_78723_d));
        GL11.glDisable((int)2929);
        MultiblockRenderHandler.doRenderComponent(mb, comp, x, y, z, 0.4f);
        GL11.glPopMatrix();
        return true;
    }

    public static void renderMultiblockOnPage(Multiblock mb) {
        GL11.glTranslated((double)-0.5, (double)-0.5, (double)-0.5);
        blockAccess.update(null, mb, mb.offX, mb.offY, mb.offZ);
        for (MultiblockComponent comp : mb.getComponents()) {
            ChunkCoordinates pos = comp.getRelativePosition();
            MultiblockRenderHandler.doRenderComponent(mb, comp, pos.field_71574_a + mb.offX, pos.field_71572_b + mb.offY, pos.field_71573_c + mb.offZ, 1.0f);
        }
    }

    private static void doRenderComponent(Multiblock mb, MultiblockComponent comp, int x, int y, int z, float alpha) {
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        Block block = comp.getBlock();
        int meta = comp.getMeta();
        Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110575_b);
        MultiblockRenderHandler.blockRender.field_147844_c = false;
        if (block == null) {
            return;
        }
        if (IMultiblockRenderHook.renderHooks.containsKey(block)) {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
            IMultiblockRenderHook renderHook = IMultiblockRenderHook.renderHooks.get(block);
            if (renderHook.needsTranslate(block)) {
                GL11.glTranslated((double)((double)x + 0.5), (double)((double)y + 0.5), (double)((double)z + 0.5));
            }
            renderHook.renderBlockForMultiblock(blockAccess, mb, block, comp.getMeta(), blockRender, comp, alpha);
        } else if (comp.shouldDoFancyRender()) {
            int color = block.func_149720_d((IBlockAccess)blockAccess, x, y, z);
            float red = (float)(color >> 16 & 0xFF) / 255.0f;
            float green = (float)(color >> 8 & 0xFF) / 255.0f;
            float blue = (float)(color & 0xFF) / 255.0f;
            GL11.glColor4f((float)red, (float)green, (float)blue, (float)alpha);
            IBlockAccess oldBlockAccess = MultiblockRenderHandler.blockRender.field_147845_a;
            MultiblockRenderHandler.blockRender.field_147845_a = blockAccess;
            Tessellator tessellator = Tessellator.field_78398_a;
            MultiblockRenderHandler.blockRender.field_147837_f = true;
            tessellator.func_78382_b();
            tessellator.func_78383_c();
            try {
                blockRender.func_147805_b(block, x, y, z);
            }
            catch (Exception e) {
                comp.doFancyRender = false;
            }
            tessellator.func_78381_a();
            MultiblockRenderHandler.blockRender.field_147837_f = false;
            MultiblockRenderHandler.blockRender.field_147845_a = oldBlockAccess;
        } else {
            int color = block.func_149741_i(meta);
            float red = (float)(color >> 16 & 0xFF) / 255.0f;
            float green = (float)(color >> 8 & 0xFF) / 255.0f;
            float blue = (float)(color & 0xFF) / 255.0f;
            GL11.glColor4f((float)red, (float)green, (float)blue, (float)alpha);
            GL11.glTranslated((double)((double)x + 0.5), (double)((double)y + 0.5), (double)((double)z + 0.5));
            blockRender.func_147800_a(block, meta, 1.0f);
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)2929);
        GL11.glPopMatrix();
    }
}

