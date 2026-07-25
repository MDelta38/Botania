/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.client.renderers.tile.ItemNodeRenderer;
import thaumcraft.common.blocks.ItemJarNode;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.tiles.TileJar;
import thaumcraft.common.tiles.TileJarNode;

public class ItemJarNodeRenderer
implements IItemRenderer {
    RenderBlocks rb = new RenderBlocks();

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return item != null && item.func_77973_b() == ConfigItems.itemJarNode;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return helper != IItemRenderer.ItemRendererHelper.EQUIPPED_BLOCK;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        if (item.func_77973_b() == ConfigItems.itemJarNode) {
            AspectList aspects;
            if (type == IItemRenderer.ItemRenderType.ENTITY) {
                GL11.glTranslatef((float)-0.5f, (float)-0.25f, (float)-0.5f);
            } else if (type == IItemRenderer.ItemRenderType.EQUIPPED && data[1] instanceof EntityPlayer) {
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.5f);
            }
            TileJarNode tjf = new TileJarNode();
            if (item.func_77942_o() && (aspects = ((ItemJarNode)item.func_77973_b()).getAspects(item)) != null) {
                tjf.setAspects(aspects);
                tjf.setNodeType(((ItemJarNode)item.func_77973_b()).getNodeType(item));
                tjf.setNodeModifier(((ItemJarNode)item.func_77973_b()).getNodeModifier(item));
            }
            tjf.field_145854_h = ConfigBlocks.blockJar;
            tjf.field_145847_g = 2;
            GL11.glPushMatrix();
            TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileJar(), 0.0, 0.0, 0.0, 0.0f);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslated((double)0.5, (double)0.4, (double)0.5);
            ItemNodeRenderer.renderItemNode(tjf);
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            ItemNodeRenderer.renderItemNode(tjf);
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            ItemNodeRenderer.renderItemNode(tjf);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110575_b);
            this.rb.field_147844_c = true;
            this.rb.func_147800_a(ConfigBlocks.blockJar, item.func_77960_j(), 1.0f);
            GL11.glPopMatrix();
            GL11.glEnable((int)32826);
        }
    }
}

