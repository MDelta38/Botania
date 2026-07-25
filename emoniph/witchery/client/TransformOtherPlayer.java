/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.entity.RendererLivingEntity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  net.minecraftforge.client.MinecraftForgeClient
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client;

import com.emoniph.witchery.client.renderer.RenderOtherPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class TransformOtherPlayer {
    private RenderOtherPlayer proxyRenderer = new RenderOtherPlayer();

    public void syncModelWith(EntityLivingBase entity, boolean frontface) {
    }

    public void render(World worldObj, EntityLivingBase entity, double x, double y, double z, RendererLivingEntity renderer, float partialTicks, boolean frontface) {
        EntityPlayer player = (EntityPlayer)entity;
        this.proxyRenderer.func_76976_a(RenderManager.field_78727_a);
        float f1 = player.field_70126_B + (player.field_70177_z - player.field_70126_B) * partialTicks;
        double d3 = player.field_70129_M;
        this.proxyRenderer.func_76986_a((EntityLivingBase)player, x, y + d3, z, frontface ? 0.0f : f1, partialTicks);
    }

    protected void renderEquippedItems(ItemRenderer itemRenderer, EntityLivingBase p_77029_1_, float p_77029_2_) {
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        ItemStack itemstack = p_77029_1_.func_70694_bm();
        if (itemstack != null && itemstack.func_77973_b() != null) {
            float f1;
            boolean is3D;
            Item item = itemstack.func_77973_b();
            GL11.glPushMatrix();
            GL11.glTranslatef((float)-0.0625f, (float)0.4375f, (float)0.0625f);
            IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer((ItemStack)itemstack, (IItemRenderer.ItemRenderType)IItemRenderer.ItemRenderType.EQUIPPED);
            boolean bl = is3D = customRenderer != null && customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack, IItemRenderer.ItemRendererHelper.BLOCK_3D);
            if (item instanceof ItemBlock && (is3D || RenderBlocks.func_147739_a((int)Block.func_149634_a((Item)item).func_149645_b()))) {
                f1 = 0.5f;
                GL11.glTranslatef((float)0.0f, (float)0.1875f, (float)-0.3125f);
                GL11.glRotatef((float)20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glScalef((float)(-(f1 *= 0.75f)), (float)(-f1), (float)f1);
            } else if (item == Items.field_151031_f) {
                f1 = 0.625f;
                GL11.glTranslatef((float)0.0f, (float)0.125f, (float)0.3125f);
                GL11.glRotatef((float)-20.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glScalef((float)f1, (float)(-f1), (float)f1);
                GL11.glRotatef((float)-100.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            } else if (item.func_77662_d()) {
                f1 = 0.625f;
                if (item.func_77629_n_()) {
                    GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glTranslatef((float)0.0f, (float)-0.125f, (float)0.0f);
                }
                GL11.glScalef((float)f1, (float)(-f1), (float)f1);
                GL11.glRotatef((float)-100.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            } else {
                f1 = 0.375f;
                GL11.glTranslatef((float)0.25f, (float)0.1875f, (float)-0.1875f);
                GL11.glScalef((float)f1, (float)f1, (float)f1);
                GL11.glRotatef((float)60.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            }
            if (itemstack.func_77973_b().func_77623_v()) {
                for (int i = 0; i < itemstack.func_77973_b().getRenderPasses(itemstack.func_77960_j()); ++i) {
                    int j = itemstack.func_77973_b().func_82790_a(itemstack, i);
                    float f5 = (float)(j >> 16 & 0xFF) / 255.0f;
                    float f2 = (float)(j >> 8 & 0xFF) / 255.0f;
                    float f3 = (float)(j & 0xFF) / 255.0f;
                    GL11.glColor4f((float)f5, (float)f2, (float)f3, (float)1.0f);
                    itemRenderer.func_78443_a(p_77029_1_, itemstack, i);
                }
            } else {
                int i = itemstack.func_77973_b().func_82790_a(itemstack, 0);
                float f4 = (float)(i >> 16 & 0xFF) / 255.0f;
                float f5 = (float)(i >> 8 & 0xFF) / 255.0f;
                float f2 = (float)(i & 0xFF) / 255.0f;
                GL11.glColor4f((float)f4, (float)f5, (float)f2, (float)1.0f);
                itemRenderer.func_78443_a(p_77029_1_, itemstack, 0);
            }
            GL11.glPopMatrix();
        }
    }
}

