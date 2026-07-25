/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  net.minecraftforge.client.MinecraftForgeClient
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.models.entities.ModelPech;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.monster.EntityPech;

@SideOnly(value=Side.CLIENT)
public class RenderPech
extends RenderLiving {
    protected ModelPech modelMain;
    protected ModelPech modelOverlay;
    private static final ResourceLocation[] skin = new ResourceLocation[]{new ResourceLocation("thaumcraft", "textures/models/pech_forage.png"), new ResourceLocation("thaumcraft", "textures/models/pech_thaum.png"), new ResourceLocation("thaumcraft", "textures/models/pech_stalker.png")};

    public RenderPech(ModelPech par1ModelBiped, float par2) {
        super((ModelBase)par1ModelBiped, par2);
        this.modelMain = par1ModelBiped;
        this.modelOverlay = new ModelPech();
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return skin[((EntityPech)entity).getPechType()];
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
        float f2 = 1.0f;
        GL11.glColor3f((float)f2, (float)f2, (float)f2);
        ItemStack itemstack = par1EntityLiving.func_70694_bm();
        this.func_82420_a(par1EntityLiving, itemstack);
        double d3 = par4 - (double)par1EntityLiving.field_70129_M;
        if (par1EntityLiving.func_70093_af()) {
            d3 -= 0.125;
        }
        super.func_76986_a(par1EntityLiving, par2, d3, par6, par8, par9);
    }

    protected ResourceLocation func_110856_a(EntityLiving par1EntityLiving) {
        return null;
    }

    protected void func_82420_a(EntityLiving par1EntityLiving, ItemStack par2ItemStack) {
    }

    protected void func_130005_c(EntityLiving par1EntityLiving, float par2) {
        float f1 = 1.0f;
        GL11.glColor3f((float)f1, (float)f1, (float)f1);
        super.func_77029_c((EntityLivingBase)par1EntityLiving, par2);
        ItemStack itemstack = par1EntityLiving.func_70694_bm();
        if (itemstack != null) {
            boolean is3D;
            float f2;
            GL11.glPushMatrix();
            if (this.field_77045_g.field_78091_s) {
                f2 = 0.5f;
                GL11.glTranslatef((float)0.0f, (float)0.625f, (float)0.0f);
                GL11.glRotatef((float)-20.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
                GL11.glScalef((float)f2, (float)f2, (float)f2);
            }
            this.modelMain.RightArm.func_78794_c(0.0625f);
            GL11.glTranslatef((float)-0.0625f, (float)0.3375f, (float)0.0625f);
            IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer((ItemStack)itemstack, (IItemRenderer.ItemRenderType)IItemRenderer.ItemRenderType.EQUIPPED);
            boolean bl = is3D = customRenderer != null && customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack, IItemRenderer.ItemRendererHelper.BLOCK_3D);
            if (itemstack.func_77973_b() instanceof ItemBlock && (is3D || RenderBlocks.func_147739_a((int)Block.func_149634_a((Item)itemstack.func_77973_b()).func_149645_b()))) {
                f2 = 0.5f;
                GL11.glTranslatef((float)0.0f, (float)0.1875f, (float)-0.3125f);
                GL11.glRotatef((float)20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glScalef((float)(-(f2 *= 0.75f)), (float)(-f2), (float)f2);
            } else if (itemstack.func_77973_b() == Items.field_151031_f) {
                f2 = 0.625f;
                GL11.glTranslatef((float)0.0f, (float)0.125f, (float)0.3125f);
                GL11.glRotatef((float)-20.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glScalef((float)f2, (float)(-f2), (float)f2);
                GL11.glRotatef((float)-100.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            } else if (itemstack.func_77973_b().func_77662_d()) {
                f2 = 0.625f;
                if (itemstack.func_77973_b() == ConfigItems.itemWandCasting) {
                    GL11.glTranslatef((float)0.0f, (float)-0.125f, (float)0.0f);
                }
                if (itemstack.func_77973_b().func_77629_n_()) {
                    GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glTranslatef((float)0.0f, (float)-0.125f, (float)0.0f);
                }
                this.func_82422_c();
                GL11.glScalef((float)f2, (float)(-f2), (float)f2);
                GL11.glRotatef((float)-100.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            } else {
                f2 = 0.375f;
                GL11.glTranslatef((float)0.25f, (float)0.1875f, (float)-0.1875f);
                GL11.glScalef((float)f2, (float)f2, (float)f2);
                GL11.glRotatef((float)60.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            }
            this.field_76990_c.field_78721_f.func_78443_a((EntityLivingBase)par1EntityLiving, itemstack, 0);
            if (itemstack.func_77973_b().func_77623_v()) {
                for (int x = 1; x < itemstack.func_77973_b().getRenderPasses(itemstack.func_77960_j()); ++x) {
                    this.field_76990_c.field_78721_f.func_78443_a((EntityLivingBase)par1EntityLiving, itemstack, x);
                }
            }
            GL11.glPopMatrix();
        }
    }

    protected void func_82422_c() {
        GL11.glTranslatef((float)0.0f, (float)0.1875f, (float)0.0f);
    }

    protected void func_77029_c(EntityLivingBase par1EntityLivingBase, float par2) {
        this.func_130005_c((EntityLiving)par1EntityLivingBase, par2);
    }

    public void renderPlayer(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderLiving((EntityLiving)par1EntityLivingBase, par2, par4, par6, par8, par9);
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderLiving((EntityLiving)par1Entity, par2, par4, par6, par8, par9);
    }
}

