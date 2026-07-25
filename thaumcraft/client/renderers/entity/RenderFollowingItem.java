/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 */
package thaumcraft.client.renderers.entity;

import java.util.Random;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.common.entities.EntitySpecialItem;

public class RenderFollowingItem
extends Render {
    private RenderBlocks renderBlocks = new RenderBlocks();
    private Random random = new Random();
    public boolean field_77024_a = true;
    public float zLevel = 0.0f;

    public RenderFollowingItem() {
        this.field_76989_e = 0.15f;
        this.field_76987_f = 0.75f;
    }

    public void doRenderItem(EntitySpecialItem par1EntityItem, double par2, double par4, double par6, float par8, float pticks) {
        this.random.setSeed(187L);
        RenderItem ri = new RenderItem();
        ri.func_76976_a(RenderManager.field_78727_a);
        ItemStack var10 = par1EntityItem.func_92059_d();
        if (var10 != null) {
            EntityItem ei = new EntityItem(par1EntityItem.field_70170_p, par1EntityItem.field_70165_t, par1EntityItem.field_70163_u, par1EntityItem.field_70161_v, var10);
            ei.field_70292_b = par1EntityItem.field_70292_b;
            ei.field_70290_d = par1EntityItem.field_70290_d;
            ri.func_76986_a(ei, par2, par4, par6, par8, pticks);
        }
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderItem((EntitySpecialItem)par1Entity, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return AbstractClientPlayer.field_110314_b;
    }
}

