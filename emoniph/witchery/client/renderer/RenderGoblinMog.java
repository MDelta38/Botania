/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.boss.BossStatus
 *  net.minecraft.entity.boss.IBossDisplayData
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.ForgeHooksClient
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  net.minecraftforge.client.MinecraftForgeClient
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.client.model.ModelGoblinMog;
import com.emoniph.witchery.entity.EntityGoblinMog;
import com.google.common.collect.Maps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderGoblinMog
extends RenderLiving {
    public ModelGoblinMog modelBipedMain;
    protected float field_77070_b;
    protected ModelGoblinMog field_82423_g;
    protected ModelGoblinMog field_82425_h;
    private static final Map field_110859_k = Maps.newHashMap();
    public static String[] bipedArmorFilenamePrefix = new String[]{"leather", "chainmail", "iron", "diamond", "gold"};
    private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/mog.png");

    public RenderGoblinMog(ModelGoblinMog par1ModelBiped, float par2) {
        this(par1ModelBiped, par2, 1.0f);
    }

    public RenderGoblinMog(ModelGoblinMog par1ModelBiped, float par2, float par3) {
        super((ModelBase)par1ModelBiped, par2);
        this.modelBipedMain = par1ModelBiped;
        this.field_77070_b = par3;
        this.func_82421_b();
    }

    protected void func_82421_b() {
        this.field_82423_g = new ModelGoblinMog(1.0f);
        this.field_82425_h = new ModelGoblinMog(0.5f);
    }

    protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3) {
        Item item;
        ItemStack itemstack = par1EntityLiving.func_130225_q(3 - par2);
        if (itemstack != null && (item = itemstack.func_77973_b()) instanceof ItemArmor) {
            ItemArmor itemarmor = (ItemArmor)item;
            this.func_110776_a(RenderGoblinMog.getArmorResource((Entity)par1EntityLiving, itemstack, par2, null));
            ModelGoblinMog modelbiped = par2 == 2 ? this.field_82425_h : this.field_82423_g;
            modelbiped.bipedHead.field_78806_j = par2 == 0;
            modelbiped.bipedBody.field_78806_j = par2 == 1 || par2 == 2;
            modelbiped.bipedRightArm.field_78806_j = par2 == 1;
            modelbiped.bipedLeftArm.field_78806_j = par2 == 1;
            modelbiped.bipedRightLeg.field_78806_j = par2 == 2 || par2 == 3;
            modelbiped.bipedLeftLeg.field_78806_j = par2 == 2 || par2 == 3;
            this.func_77042_a(modelbiped);
            modelbiped.field_78095_p = this.field_77045_g.field_78095_p;
            modelbiped.field_78093_q = this.field_77045_g.field_78093_q;
            modelbiped.field_78091_s = this.field_77045_g.field_78091_s;
            int j = itemarmor.func_82814_b(itemstack);
            if (j != -1) {
                float f1 = (float)(j >> 16 & 0xFF) / 255.0f;
                float f2 = (float)(j >> 8 & 0xFF) / 255.0f;
                float f3 = (float)(j & 0xFF) / 255.0f;
                GL11.glColor3f((float)f1, (float)f2, (float)f3);
                if (itemstack.func_77948_v()) {
                    return 31;
                }
                return 16;
            }
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            if (itemstack.func_77948_v()) {
                return 15;
            }
            return 1;
        }
        return -1;
    }

    protected void func_82408_c(EntityLiving par1EntityLivingBase, int par2, float par3) {
        Item item;
        ItemStack itemstack = par1EntityLivingBase.func_130225_q(3 - par2);
        if (itemstack != null && (item = itemstack.func_77973_b()) instanceof ItemArmor) {
            this.func_110776_a(RenderGoblinMog.getArmorResource((Entity)par1EntityLivingBase, itemstack, par2, "overlay"));
            float f1 = 1.0f;
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        }
    }

    public void func_76986_a(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        ItemStack itemstack = par1EntityLiving.func_70694_bm();
        this.func_82420_a(par1EntityLiving, itemstack);
        double d3 = par4 - (double)par1EntityLiving.field_70129_M;
        BossStatus.func_82824_a((IBossDisplayData)((EntityGoblinMog)par1EntityLiving), (boolean)true);
        if (par1EntityLiving.func_70093_af()) {
            d3 -= 0.125;
        }
        super.func_76986_a(par1EntityLiving, par2, d3, par6, par8, par9);
        this.modelBipedMain.aimedBow = false;
        this.field_82425_h.aimedBow = false;
        this.field_82423_g.aimedBow = false;
        this.modelBipedMain.isSneak = false;
        this.field_82425_h.isSneak = false;
        this.field_82423_g.isSneak = false;
        this.modelBipedMain.heldItemRight = 0;
        this.field_82425_h.heldItemRight = 0;
        this.field_82423_g.heldItemRight = 0;
    }

    protected ResourceLocation getEntityTexture(EntityLiving entity) {
        return TEXTURE_URL;
    }

    protected void func_82420_a(EntityLiving par1EntityLiving, ItemStack par2ItemStack) {
        this.modelBipedMain.heldItemRight = par2ItemStack != null ? 1 : 0;
        this.field_82425_h.heldItemRight = this.modelBipedMain.heldItemRight;
        this.field_82423_g.heldItemRight = this.modelBipedMain.heldItemRight;
        this.field_82425_h.isSneak = this.modelBipedMain.isSneak = par1EntityLiving.func_70093_af();
        this.field_82423_g.isSneak = this.modelBipedMain.isSneak;
    }

    protected void renderEquippedItems(EntityLiving par1EntityLiving, float par2) {
        float f1;
        boolean is3D;
        IItemRenderer customRenderer;
        Item item;
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        super.func_77029_c((EntityLivingBase)par1EntityLiving, par2);
        ItemStack itemstack = par1EntityLiving.func_70694_bm();
        ItemStack itemstack1 = par1EntityLiving.func_130225_q(3);
        if (itemstack1 != null) {
            GL11.glPushMatrix();
            this.modelBipedMain.bipedHead.func_78794_c(0.0625f);
            item = itemstack1.func_77973_b();
            customRenderer = MinecraftForgeClient.getItemRenderer((ItemStack)itemstack1, (IItemRenderer.ItemRenderType)IItemRenderer.ItemRenderType.EQUIPPED);
            boolean bl = is3D = customRenderer != null && customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack1, IItemRenderer.ItemRendererHelper.BLOCK_3D);
            if (item instanceof ItemBlock) {
                if (is3D || RenderBlocks.func_147739_a((int)Block.func_149634_a((Item)item).func_149645_b())) {
                    f1 = 0.625f;
                    GL11.glTranslatef((float)0.0f, (float)-0.25f, (float)0.0f);
                    GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glScalef((float)f1, (float)(-f1), (float)(-f1));
                }
                this.field_76990_c.field_78721_f.func_78443_a((EntityLivingBase)par1EntityLiving, itemstack1, 0);
            }
            GL11.glPopMatrix();
        }
        if (itemstack != null && itemstack.func_77973_b() != null) {
            item = itemstack.func_77973_b();
            GL11.glPushMatrix();
            if (this.field_77045_g.field_78091_s) {
                f1 = 0.5f;
                GL11.glTranslatef((float)0.0f, (float)0.625f, (float)0.0f);
                GL11.glRotatef((float)-20.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
                GL11.glScalef((float)f1, (float)f1, (float)f1);
            }
            this.modelBipedMain.bipedRightArm.func_78794_c(0.0625f);
            GL11.glTranslatef((float)-0.0625f, (float)0.4375f, (float)0.0625f);
            customRenderer = MinecraftForgeClient.getItemRenderer((ItemStack)itemstack, (IItemRenderer.ItemRenderType)IItemRenderer.ItemRenderType.EQUIPPED);
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
                this.func_82422_c();
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
                    float f2 = (float)(j >> 16 & 0xFF) / 255.0f;
                    float f3 = (float)(j >> 8 & 0xFF) / 255.0f;
                    float f4 = (float)(j & 0xFF) / 255.0f;
                    GL11.glColor4f((float)f2, (float)f3, (float)f4, (float)1.0f);
                    this.field_76990_c.field_78721_f.func_78443_a((EntityLivingBase)par1EntityLiving, itemstack, i);
                }
            } else {
                int i = itemstack.func_77973_b().func_82790_a(itemstack, 0);
                float f5 = (float)(i >> 16 & 0xFF) / 255.0f;
                float f2 = (float)(i >> 8 & 0xFF) / 255.0f;
                float f3 = (float)(i & 0xFF) / 255.0f;
                GL11.glColor4f((float)f5, (float)f2, (float)f3, (float)1.0f);
                this.field_76990_c.field_78721_f.func_78443_a((EntityLivingBase)par1EntityLiving, itemstack, 0);
            }
            GL11.glPopMatrix();
        }
    }

    protected void func_82422_c() {
        GL11.glTranslatef((float)0.0f, (float)0.1875f, (float)0.0f);
    }

    protected void func_82408_c(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
        this.func_82408_c((EntityLiving)par1EntityLivingBase, par2, par3);
    }

    protected int func_77032_a(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
        return this.shouldRenderPass((EntityLiving)par1EntityLivingBase, par2, par3);
    }

    protected void func_77029_c(EntityLivingBase par1EntityLivingBase, float par2) {
        this.renderEquippedItems((EntityLiving)par1EntityLivingBase, par2);
    }

    public void func_76986_a(EntityLivingBase par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.func_76986_a((EntityLiving)par1Entity, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return this.getEntityTexture((EntityLiving)par1Entity);
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.func_76986_a((EntityLiving)par1Entity, par2, par4, par6, par8, par9);
    }

    public static ResourceLocation getArmorResource(Entity entity, ItemStack stack, int slot, String type) {
        ItemArmor item = (ItemArmor)stack.func_77973_b();
        String s1 = String.format("textures/models/armor/%s_layer_%d%s.png", bipedArmorFilenamePrefix[item.field_77880_c], slot == 2 ? 2 : 1, type == null ? "" : String.format("_%s", type));
        ResourceLocation resourcelocation = (ResourceLocation)field_110859_k.get(s1 = ForgeHooksClient.getArmorTexture((Entity)entity, (ItemStack)stack, (String)s1, (int)slot, (String)type));
        if (resourcelocation == null) {
            resourcelocation = new ResourceLocation(s1);
            field_110859_k.put(s1, resourcelocation);
        }
        return resourcelocation;
    }
}

