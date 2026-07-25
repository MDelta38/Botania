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
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.blocks.ItemJarFilled;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.tiles.TileJarFillable;
import thaumcraft.common.tiles.TileJarFillableVoid;

public class ItemJarFilledRenderer
implements IItemRenderer {
    RenderBlocks rb = new RenderBlocks();

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return item != null && item.func_77973_b() == ConfigItems.itemJarFilled;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return helper != IItemRenderer.ItemRendererHelper.EQUIPPED_BLOCK;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        if (item.func_77973_b() == ConfigItems.itemJarFilled) {
            if (type == IItemRenderer.ItemRenderType.ENTITY) {
                GL11.glTranslatef((float)-0.5f, (float)-0.25f, (float)-0.5f);
            } else if (type == IItemRenderer.ItemRenderType.EQUIPPED && data[1] instanceof EntityPlayer) {
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.5f);
            }
            TileJarFillable tjf = new TileJarFillable();
            if (item.func_77942_o()) {
                String tf;
                AspectList aspects;
                if (item.func_77960_j() == 3) {
                    tjf = new TileJarFillableVoid();
                }
                if ((aspects = ((ItemJarFilled)item.func_77973_b()).getAspects(item)) != null && aspects.size() == 1) {
                    tjf.amount = aspects.getAmount(aspects.getAspects()[0]);
                    tjf.aspect = aspects.getAspects()[0];
                }
                if ((tf = item.field_77990_d.func_74779_i("AspectFilter")) != null) {
                    tjf.aspectFilter = Aspect.getAspect(tf);
                }
            }
            tjf.facing = 5;
            tjf.field_145854_h = ConfigBlocks.blockJar;
            tjf.field_145847_g = 0;
            TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)tjf, 0.0, 0.0, 0.0, 0.0f);
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

