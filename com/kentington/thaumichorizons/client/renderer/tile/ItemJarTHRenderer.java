/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.UtilsFX
 *  thaumcraft.common.config.ConfigBlocks
 */
package com.kentington.thaumichorizons.client.renderer.tile;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileSoulJar;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.config.ConfigBlocks;

public class ItemJarTHRenderer
implements IItemRenderer {
    static String tx3 = "textures/misc/soul.png";

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return helper != IItemRenderer.ItemRendererHelper.EQUIPPED_BLOCK;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        if (item.func_77973_b() == Item.func_150898_a((Block)ThaumicHorizons.blockJar)) {
            float short1 = 240.0f;
            float short2 = 240.0f;
            GL11.glTranslated((double)0.5, (double)0.0, (double)0.5);
            if (type == IItemRenderer.ItemRenderType.ENTITY) {
                GL11.glTranslatef((float)-0.5f, (float)-0.25f, (float)-0.5f);
            } else if (type == IItemRenderer.ItemRenderType.EQUIPPED && data[1] instanceof EntityPlayer) {
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.5f);
            }
            if (item.func_77942_o() && item.field_77990_d.func_74767_n("isSoul")) {
                long nt = System.nanoTime();
                UtilsFX.bindTexture((String)"thaumichorizons", (String)tx3);
                GL11.glEnable((int)3042);
                GL11.glAlphaFunc((int)516, (float)0.003921569f);
                GL11.glDisable((int)2929);
                GL11.glDisable((int)2884);
                GL11.glPushMatrix();
                GL11.glTranslated((double)0.0, (double)0.4, (double)0.0);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                UtilsFX.renderAnimatedQuad((float)0.3f, (float)0.9f, (int)16, (int)((int)(nt / 40000000L % 16L)), (float)0.0f, (int)0xFFFFFF);
                GL11.glDisable((int)3042);
                GL11.glPopMatrix();
                GL11.glEnable((int)2884);
                GL11.glEnable((int)2929);
                GL11.glDisable((int)3042);
            } else if (item.func_77942_o()) {
                TileSoulJar th = new TileSoulJar();
                GL11.glPushMatrix();
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)-0.5f, (float)0.0f, (float)-0.5f);
                EntityClientPlayerMP viewer = Minecraft.func_71410_x().field_71439_g;
                th.entity = EntityList.func_75615_a((NBTTagCompound)item.func_77978_p(), (World)viewer.field_70170_p);
                TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)th, 0.0, 0.0, 0.0, 0.0f);
                GL11.glBlendFunc((int)770, (int)771);
                Minecraft.func_71410_x().field_71460_t.func_78483_a(0.0);
                GL11.glPopMatrix();
            }
            GL11.glPushMatrix();
            GL11.glTranslatef((float)0.0f, (float)0.5f, (float)0.0f);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110575_b);
            RenderBlocks rb = (RenderBlocks)data[0];
            rb.field_147844_c = true;
            rb.func_147800_a(ConfigBlocks.blockJar, 0, 1.0f);
            GL11.glPopMatrix();
            GL11.glDisable((int)3042);
        }
    }
}

