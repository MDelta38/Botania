/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.blocks.BlockPlacedItem;
import com.emoniph.witchery.client.renderer.RenderItem3d;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class RenderPlacedItem
extends TileEntitySpecialRenderer {
    private final RenderItem3d renderItems = new RenderItem3d(true){

        public byte getMiniItemCountForItemStack(ItemStack stack) {
            return 1;
        }

        public byte getMiniBlockCountForItemStack(ItemStack stack) {
            return 1;
        }

        @Override
        public boolean shouldBob() {
            return false;
        }

        @Override
        public boolean shouldSpreadItems() {
            return false;
        }
    };

    public RenderPlacedItem() {
        this.renderItems.func_76976_a(RenderManager.field_78727_a);
    }

    public void func_147500_a(TileEntity tileEntity, double d0, double d1, double d2, float f) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)d0), (float)((float)d1), (float)((float)d2));
        this.renderPlacedItem((BlockPlacedItem.TileEntityPlacedItem)tileEntity, tileEntity.func_145831_w(), tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e);
        GL11.glPopMatrix();
    }

    public void renderPlacedItem(BlockPlacedItem.TileEntityPlacedItem te, World world, int x, int y, int z) {
        if (world != null) {
            EntityItem ei = new EntityItem(world);
            ei.field_70290_d = 0.0f;
            if (te != null && te.getStack() != null) {
                ei.func_92058_a(te.getStack().func_77946_l());
            } else {
                ei.func_92058_a(new ItemStack(Items.field_151103_aS));
            }
            GL11.glTranslatef((float)0.5f, (float)0.05f, (float)0.5f);
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
                        rotation = 90.0f;
                        break;
                    }
                    case 5: {
                        rotation = 270.0f;
                    }
                }
                GL11.glRotatef((float)rotation, (float)0.0f, (float)1.0f, (float)0.0f);
            }
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glTranslatef((float)0.0f, (float)-0.1f, (float)0.0f);
            this.renderItems.doRender(ei, 0.0, 0.0, 0.0, 0.0f, 0.0f);
        }
    }
}

