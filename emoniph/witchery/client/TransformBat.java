/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.entity.RenderBat
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.entity.RendererLivingEntity
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntityBat
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  net.minecraftforge.client.MinecraftForgeClient
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client;

import com.emoniph.witchery.entity.EntityBroom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderBat;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class TransformBat {
    private EntityBat proxyEntity;
    private RenderBat proxyRenderer = new RenderBat();

    public EntityLivingBase getModel() {
        return this.proxyEntity;
    }

    public void syncModelWith(EntityLivingBase entity, boolean frontface) {
        if (this.proxyEntity == null) {
            this.proxyEntity = new EntityBat(entity.field_70170_p);
        } else if (this.proxyEntity.field_70170_p != entity.field_70170_p) {
            this.proxyEntity.func_70029_a(entity.field_70170_p);
        }
        this.proxyEntity.func_70107_b(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
        this.proxyEntity.field_70142_S = entity.field_70142_S;
        this.proxyEntity.field_70137_T = entity.field_70137_T;
        this.proxyEntity.field_70136_U = entity.field_70136_U;
        this.proxyEntity.field_70159_w = entity.field_70159_w;
        this.proxyEntity.field_70181_x = entity.field_70181_x;
        this.proxyEntity.field_70179_y = entity.field_70179_y;
        this.proxyEntity.field_70701_bs = entity.field_70701_bs;
        this.proxyEntity.field_70702_br = entity.field_70702_br;
        this.proxyEntity.field_70122_E = entity.field_70122_E;
        this.proxyEntity.field_70169_q = entity.field_70169_q;
        this.proxyEntity.field_70167_r = entity.field_70167_r;
        this.proxyEntity.field_70166_s = entity.field_70166_s;
        this.proxyEntity.field_70125_A = entity.field_70125_A;
        this.proxyEntity.field_70177_z = entity.field_70177_z;
        this.proxyEntity.field_70759_as = entity.field_70759_as;
        this.proxyEntity.field_70127_C = entity.field_70127_C;
        this.proxyEntity.field_70126_B = entity.field_70126_B;
        this.proxyEntity.field_70758_at = entity.field_70758_at;
        this.proxyEntity.field_70754_ba = entity.field_70754_ba;
        this.proxyEntity.field_70721_aZ = entity.field_70721_aZ;
        this.proxyEntity.field_70722_aY = entity.field_70722_aY;
        this.proxyEntity.field_82175_bq = entity.field_82175_bq;
        this.proxyEntity.field_70733_aJ = entity.field_70733_aJ;
        this.proxyEntity.field_70732_aI = entity.field_70732_aI;
        this.proxyEntity.field_70761_aq = frontface ? 0.0f : entity.field_70761_aq;
        this.proxyEntity.field_70760_ar = frontface ? 0.0f : entity.field_70760_ar;
        this.proxyEntity.field_70173_aa = entity.field_70173_aa;
        this.proxyEntity.field_70128_L = false;
        this.proxyEntity.field_70160_al = entity.field_70160_al;
        this.proxyEntity.func_82236_f(false);
        this.proxyEntity.field_70129_M = 0.0f;
        this.proxyEntity.func_70095_a(entity.func_70093_af());
        this.proxyEntity.func_70031_b(entity.func_70051_ag());
        this.proxyEntity.func_82142_c(entity.func_82150_aj());
    }

    public void render(World worldObj, EntityLivingBase entity, double x, double y, double z, RendererLivingEntity renderer, float partialTicks, boolean frontface) {
        this.syncModelWith(entity, frontface);
        this.proxyRenderer.func_76976_a(RenderManager.field_78727_a);
        float f1 = this.proxyEntity.field_70126_B + (this.proxyEntity.field_70177_z - this.proxyEntity.field_70126_B) * partialTicks;
        double d3 = -((double)this.proxyEntity.field_70129_M);
        if (this.proxyEntity.func_70093_af() && !(entity instanceof EntityPlayerSP)) {
            d3 -= 0.125;
        }
        if (entity.func_70115_ae()) {
            Entity ridden = entity.field_70154_o;
            d3 += ridden.func_70042_X() + (entity.field_70154_o instanceof EntityBroom ? (double)ridden.field_70131_O - 0.2 : 0.0);
        }
        float f2 = 1.0f;
        GL11.glColor3f((float)f2, (float)f2, (float)f2);
        this.proxyRenderer.func_76986_a(this.proxyEntity, x, y + d3, z, frontface ? 0.0f : f1, partialTicks);
        Vec3 vec = this.proxyEntity.func_70040_Z();
        vec.func_72442_b(90.0f);
        GL11.glPushMatrix();
        GL11.glScalef((float)0.8f, (float)0.8f, (float)0.8f);
        this.proxyEntity.field_70173_aa += 2;
        this.proxyRenderer.func_76986_a(this.proxyEntity, x + vec.field_72450_a * 0.75, y + d3 - 0.6, z + vec.field_72449_c * 0.75, frontface ? 0.0f : f1, partialTicks);
        this.proxyEntity.field_70173_aa += 5;
        vec.func_72442_b(-180.0f);
        this.proxyRenderer.func_76986_a(this.proxyEntity, x + vec.field_72450_a * 0.75, y + d3 - 0.6, z + vec.field_72449_c * 0.75, frontface ? 0.0f : f1, partialTicks);
        GL11.glPopMatrix();
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

