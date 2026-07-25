/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.INode;
import thaumcraft.api.nodes.NodeType;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.tile.TileNodeRenderer;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileNode;

public class ItemNodeRenderer
implements IItemRenderer {
    AspectList aspects = new AspectList().add(Aspect.AIR, 40).add(Aspect.FIRE, 40).add(Aspect.EARTH, 40).add(Aspect.WATER, 40);

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return item != null && item.func_77973_b() == Item.func_150898_a((Block)ConfigBlocks.blockAiry) && (item.func_77960_j() == 0 || item.func_77960_j() == 5);
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return helper != IItemRenderer.ItemRendererHelper.EQUIPPED_BLOCK;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        if (type == IItemRenderer.ItemRenderType.ENTITY) {
            GL11.glTranslatef((float)-0.5f, (float)-0.25f, (float)-0.5f);
        } else if (type == IItemRenderer.ItemRenderType.EQUIPPED && data[1] instanceof EntityPlayer) {
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.5f);
        }
        TileNode tjf = new TileNode();
        tjf.setAspects(this.aspects);
        tjf.setNodeType(NodeType.NORMAL);
        tjf.field_145854_h = ConfigBlocks.blockAiry;
        tjf.field_145847_g = 0;
        GL11.glPushMatrix();
        GL11.glTranslated((double)0.5, (double)0.5, (double)0.5);
        GL11.glScaled((double)2.0, (double)2.0, (double)2.0);
        ItemNodeRenderer.renderItemNode(tjf);
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        ItemNodeRenderer.renderItemNode(tjf);
        GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        ItemNodeRenderer.renderItemNode(tjf);
        GL11.glPopMatrix();
        GL11.glEnable((int)32826);
    }

    public static void renderItemNode(INode node) {
        if (node.getAspects().size() > 0) {
            EntityLivingBase viewer = Minecraft.func_71410_x().field_71451_h;
            float alpha = 0.5f;
            if (node.getNodeModifier() != null) {
                switch (node.getNodeModifier()) {
                    case BRIGHT: {
                        alpha *= 1.5f;
                        break;
                    }
                    case PALE: {
                        alpha *= 0.66f;
                        break;
                    }
                    case FADING: {
                        alpha *= MathHelper.func_76126_a((float)((float)viewer.field_70173_aa / 3.0f)) * 0.25f + 0.33f;
                    }
                }
            }
            GL11.glPushMatrix();
            GL11.glAlphaFunc((int)516, (float)0.003921569f);
            GL11.glDepthMask((boolean)false);
            GL11.glDisable((int)2884);
            long nt = System.nanoTime();
            long time = nt / 5000000L;
            float bscale = 0.25f;
            GL11.glPushMatrix();
            float rad = (float)Math.PI * 2;
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
            UtilsFX.bindTexture(TileNodeRenderer.nodetex);
            int frames = 32;
            int i = (int)((nt / 40000000L + 1L) % (long)frames);
            int count = 0;
            float scale = 0.0f;
            float average = 0.0f;
            for (Aspect aspect : node.getAspects().getAspects()) {
                if (aspect.getBlend() == 771) {
                    alpha = (float)((double)alpha * 1.5);
                }
                average += (float)node.getAspects().getAmount(aspect);
                GL11.glPushMatrix();
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)aspect.getBlend());
                scale = MathHelper.func_76126_a((float)((float)viewer.field_70173_aa / (14.0f - (float)count))) * bscale + bscale * 2.0f;
                scale = 0.2f + scale * ((float)node.getAspects().getAmount(aspect) / 50.0f);
                UtilsFX.renderAnimatedQuadStrip(scale, alpha / (float)node.getAspects().size(), frames, 0, i, 0.0f, aspect.getColor());
                GL11.glDisable((int)3042);
                GL11.glPopMatrix();
                ++count;
                if (aspect.getBlend() != 771) continue;
                alpha = (float)((double)alpha / 1.5);
            }
            GL11.glPushMatrix();
            GL11.glEnable((int)3042);
            i = (int)((nt / 40000000L + 1L) % (long)frames);
            scale = 0.1f + (average /= (float)node.getAspects().size()) / 150.0f;
            int strip = 1;
            switch (node.getNodeType()) {
                case NORMAL: {
                    GL11.glBlendFunc((int)770, (int)1);
                    break;
                }
                case UNSTABLE: {
                    GL11.glBlendFunc((int)770, (int)1);
                    strip = 6;
                    break;
                }
                case DARK: {
                    GL11.glBlendFunc((int)770, (int)771);
                    strip = 2;
                    break;
                }
                case TAINTED: {
                    GL11.glBlendFunc((int)770, (int)771);
                    strip = 5;
                    break;
                }
                case PURE: {
                    GL11.glBlendFunc((int)770, (int)1);
                    strip = 4;
                    break;
                }
                case HUNGRY: {
                    scale *= 0.75f;
                    GL11.glBlendFunc((int)770, (int)1);
                    strip = 3;
                }
            }
            GL11.glColor4f((float)1.0f, (float)0.0f, (float)1.0f, (float)alpha);
            UtilsFX.renderAnimatedQuadStrip(scale, alpha, frames, strip, i, 0.0f, 0xFFFFFF);
            GL11.glDisable((int)3042);
            GL11.glPopMatrix();
            GL11.glPopMatrix();
            GL11.glEnable((int)2884);
            GL11.glDepthMask((boolean)true);
            GL11.glAlphaFunc((int)516, (float)0.1f);
            GL11.glPopMatrix();
        }
    }
}

