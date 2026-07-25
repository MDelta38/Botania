/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockGrassper;
import com.emoniph.witchery.client.model.ModelGrassper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderGrassper
extends TileEntitySpecialRenderer {
    final ModelGrassper model = new ModelGrassper();
    private RenderItem renderItems = new RenderItem(){

        public byte getMiniItemCountForItemStack(ItemStack stack) {
            return 1;
        }

        public byte getMiniBlockCountForItemStack(ItemStack stack) {
            return 1;
        }

        public boolean shouldBob() {
            return false;
        }

        public boolean shouldSpreadItems() {
            return false;
        }
    };
    private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/blocks/grassper.png");

    public RenderGrassper() {
        this.renderItems.func_76976_a(RenderManager.field_78727_a);
    }

    public void func_147500_a(TileEntity tileEntity, double d, double d1, double d2, float f) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)d), (float)((float)d1), (float)((float)d2));
        BlockGrassper.TileEntityGrassper tileEntityYour = (BlockGrassper.TileEntityGrassper)tileEntity;
        this.renderGrassper(tileEntityYour, tileEntity.func_145831_w(), tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e, Witchery.Blocks.GRASSPER);
        GL11.glPopMatrix();
    }

    public void renderGrassper(BlockGrassper.TileEntityGrassper te, World world, int x, int y, int z, Block block) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
        this.func_147499_a(TEXTURE_URL);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glTranslatef((float)0.0f, (float)-1.0f, (float)0.0f);
        if (world != null) {
            int meta = world.func_72805_g(x, y, z);
            float rotation = 0.0f;
            switch (meta) {
                case 2: {
                    rotation = 0.0f;
                    break;
                }
                case 3: {
                    rotation = 180.0f;
                    break;
                }
                case 4: {
                    rotation = 270.0f;
                    break;
                }
                case 5: {
                    rotation = 90.0f;
                }
            }
            GL11.glRotatef((float)rotation, (float)0.0f, (float)1.0f, (float)0.0f);
        }
        this.model.func_78088_a(null, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f);
        GL11.glPopMatrix();
        if (world != null) {
            ItemStack newStack = null;
            float rotational = (float)Minecraft.func_71386_F() / 3000.0f * 300.0f;
            EntityItem ei = new EntityItem(world);
            ei.field_70290_d = 0.0f;
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glPushMatrix();
            GL11.glEnable((int)32826);
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.0f);
            OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)170.0f, (float)170.0f);
            GL11.glTranslatef((float)0.0f, (float)0.6f, (float)0.0f);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glScalef((float)0.75f, (float)0.75f, (float)0.75f);
            float yShift = -0.15f;
            float zShift = 0.65f;
            float xShift = 0.65f;
            boolean fancy = Witchery.proxy.getGraphicsLevel();
            for (int i = 0; i < te.func_70302_i_() && i <= 46; ++i) {
                if (te.func_70301_a(i) == null) continue;
                newStack = te.func_70301_a(i).func_77946_l();
                newStack.field_77994_a = 1;
                ei.func_92058_a(newStack);
                GL11.glPushMatrix();
                GL11.glTranslatef((float)xShift, (float)yShift, (float)zShift);
                if (fancy) {
                    GL11.glRotatef((float)(rotational / 2.0f), (float)0.0f, (float)1.0f, (float)0.0f);
                }
                GL11.glPushMatrix();
                this.renderItems.func_76986_a(ei, 0.0, 0.0, 0.0, 0.0f, 0.0f);
                GL11.glPopMatrix();
                GL11.glPopMatrix();
            }
            GL11.glDisable((int)32826);
            GL11.glPopMatrix();
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
    }
}

