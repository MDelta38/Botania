/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  net.minecraftforge.client.MinecraftForgeClient
 *  net.minecraftforge.client.model.AdvancedModelLoader
 *  net.minecraftforge.client.model.IModelCustom
 *  net.minecraftforge.fluids.Fluid
 *  net.minecraftforge.fluids.FluidRegistry
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.entity;

import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.entities.ModelGolem;
import thaumcraft.client.renderers.models.entities.ModelGolemAccessories;
import thaumcraft.common.blocks.ItemJarFilled;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.golems.EntityGolemBase;

public class RenderGolemBase
extends RenderLiving {
    ModelBase damage;
    ModelBase accessories;
    private static final ResourceLocation BUCKET = new ResourceLocation("thaumcraft", "textures/models/bucket.obj");
    IIcon icon = null;
    private IModelCustom model;
    private static final ResourceLocation clay = new ResourceLocation("thaumcraft", "textures/models/golem_clay.png");
    private static final ResourceLocation stone = new ResourceLocation("thaumcraft", "textures/models/golem_stone.png");
    private static final ResourceLocation wood = new ResourceLocation("thaumcraft", "textures/models/golem_wood.png");
    private static final ResourceLocation tallow = new ResourceLocation("thaumcraft", "textures/models/golem_tallow.png");
    private static final ResourceLocation iron = new ResourceLocation("thaumcraft", "textures/models/golem_iron.png");
    private static final ResourceLocation straw = new ResourceLocation("thaumcraft", "textures/models/golem_straw.png");
    private static final ResourceLocation flesh = new ResourceLocation("thaumcraft", "textures/models/golem_flesh.png");
    private static final ResourceLocation thaumium = new ResourceLocation("thaumcraft", "textures/models/golem_thaumium.png");

    public RenderGolemBase(ModelBase par1ModelBase) {
        super(par1ModelBase, 0.25f);
        if (par1ModelBase instanceof ModelGolem) {
            ModelGolem mg = new ModelGolem(false);
            mg.pass = 2;
            this.damage = mg;
        }
        this.accessories = new ModelGolemAccessories(0.0f, 30.0f);
        this.model = AdvancedModelLoader.loadModel((ResourceLocation)BUCKET);
    }

    public void render(EntityGolemBase e, double par2, double par4, double par6, float par8, float par9) {
        super.func_76986_a((EntityLiving)e, par2, par4, par6, par8, par9);
    }

    protected int func_77032_a(EntityLivingBase entity, int pass, float par3) {
        if (pass == 0) {
            String deco = ((EntityGolemBase)entity).getGolemDecoration();
            if (((EntityGolemBase)entity).getCore() > -1) {
                GL11.glPushMatrix();
                GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glTranslatef((float)0.0875f, (float)-0.96f, (float)(0.15f + (deco.contains("P") ? 0.03f : 0.0f)));
                GL11.glScaled((double)0.175, (double)0.175, (double)0.175);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                Tessellator tessellator = Tessellator.field_78398_a;
                IIcon icon = ConfigItems.itemGolemCore.func_77617_a((int)((EntityGolemBase)entity).getCore());
                float f1 = icon.func_94212_f();
                float f2 = icon.func_94206_g();
                float f3 = icon.func_94209_e();
                float f4 = icon.func_94210_h();
                this.field_76990_c.field_78724_e.func_110577_a(TextureMap.field_110576_c);
                ItemRenderer.func_78439_a((Tessellator)tessellator, (float)f1, (float)f2, (float)f3, (float)f4, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.2f);
                GL11.glPopMatrix();
            }
            int upgrades = ((EntityGolemBase)entity).upgrades.length;
            float shift = 0.08f;
            GL11.glPushMatrix();
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            for (int a = 0; a < upgrades; ++a) {
                GL11.glPushMatrix();
                GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glTranslatef((float)(-0.05f - shift * (float)(upgrades - 1) / 2.0f + shift * (float)a), (float)-1.106f, (float)0.099f);
                GL11.glScaled((double)0.1, (double)0.1, (double)0.1);
                Tessellator tessellator = Tessellator.field_78398_a;
                IIcon icon = ConfigItems.itemGolemUpgrade.func_77617_a((int)((EntityGolemBase)entity).getUpgrade(a));
                float f1 = icon.func_94212_f();
                float f2 = icon.func_94206_g();
                float f3 = icon.func_94209_e();
                float f4 = icon.func_94210_h();
                this.field_76990_c.field_78724_e.func_110577_a(TextureMap.field_110576_c);
                tessellator.func_78382_b();
                tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
                tessellator.func_78374_a(0.0, 0.0, 0.0, (double)f1, (double)f4);
                tessellator.func_78374_a(1.0, 0.0, 0.0, (double)f3, (double)f4);
                tessellator.func_78374_a(1.0, 1.0, 0.0, (double)f3, (double)f2);
                tessellator.func_78374_a(0.0, 1.0, 0.0, (double)f1, (double)f2);
                tessellator.func_78381_a();
                GL11.glPopMatrix();
            }
            GL11.glDisable((int)3042);
            GL11.glPopMatrix();
        } else {
            if (pass == 1 && (((EntityGolemBase)entity).getGolemDecoration().length() > 0 || ((EntityGolemBase)entity).advanced)) {
                UtilsFX.bindTexture("textures/models/golem_decoration.png");
                this.func_77042_a(this.accessories);
                return 1;
            }
            if (pass == 2 && ((EntityGolemBase)entity).getHealthPercentage() < 1.0f) {
                UtilsFX.bindTexture("textures/models/golem_damage.png");
                this.func_77042_a(this.damage);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)(1.0f - ((EntityGolemBase)entity).getHealthPercentage()));
                return 2;
            }
        }
        return -1;
    }

    protected void renderWithSway(EntityGolemBase e, float par2, float par3, float par4) {
        super.func_77043_a((EntityLivingBase)e, par2, par3, par4);
        if ((double)e.field_70721_aZ >= 0.01) {
            float var5 = 13.0f;
            float var6 = e.field_70754_ba - e.field_70721_aZ * (1.0f - par4) + 6.0f;
            float var7 = (Math.abs(var6 % var5 - var5 * 0.5f) - var5 * 0.25f) / (var5 * 0.25f);
            GL11.glRotatef((float)(6.5f * var7), (float)0.0f, (float)0.0f, (float)1.0f);
        }
    }

    protected void renderCarriedItems(EntityGolemBase e, float par2) {
        ItemStack var3 = e.getCarriedForDisplay();
        if (e.getCore() == 11) {
            GL11.glPushMatrix();
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            float fs = 0.66f;
            this.field_76990_c.field_78724_e.func_110577_a(TextureMap.field_110576_c);
            GL11.glRotatef((float)(5.0f + 90.0f * ((ModelGolem)this.field_77045_g).golemRightArm.field_78795_f / (float)Math.PI), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glTranslatef((float)-0.26875f, (float)1.6f, (float)-0.53f);
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)-1.0f, (float)0.0f);
            GL11.glRotatef((float)30.0f, (float)0.0f, (float)0.0f, (float)-1.0f);
            GL11.glScalef((float)fs, (float)(-fs), (float)fs);
            IIcon ic = Items.field_151112_aM.func_94597_g();
            float f = ic.func_94209_e();
            float f1 = ic.func_94212_f();
            float f2 = ic.func_94206_g();
            float f3 = ic.func_94210_h();
            Tessellator.field_78398_a.func_78369_a(1.0f, 1.0f, 1.0f, 1.0f);
            ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)ic.func_94211_a(), (int)ic.func_94216_b(), (float)0.0625f);
            GL11.glScaled((double)1.0, (double)1.0, (double)1.0);
            GL11.glPopMatrix();
        }
        if (var3 != null && e.field_70725_aQ == 0 && e.getCore() != 5) {
            boolean is3D;
            GL11.glPushMatrix();
            GL11.glScaled((double)0.4, (double)0.4, (double)0.4);
            IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer((ItemStack)var3, (IItemRenderer.ItemRenderType)IItemRenderer.ItemRenderType.EQUIPPED);
            boolean bl = is3D = customRenderer != null && customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, var3, IItemRenderer.ItemRendererHelper.BLOCK_3D);
            if (var3.func_77973_b() instanceof ItemBlock && (is3D || RenderBlocks.func_147739_a((int)Block.func_149634_a((Item)var3.func_77973_b()).func_149645_b()))) {
                GL11.glTranslatef((float)0.0f, (float)2.5f, (float)-1.25f);
                GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            } else if (var3.func_77973_b() instanceof ItemJarFilled) {
                GL11.glTranslatef((float)0.0f, (float)2.5f, (float)-1.0f);
                if (e.getCore() == 6) {
                    double s = 0.5 + (double)Math.min(64, e.getCarryLimit()) / 128.0;
                    GL11.glScaled((double)s, (double)s, (double)s);
                }
                GL11.glTranslatef((float)-0.5f, (float)0.0f, (float)-0.8f);
                GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)335.0f, (float)0.0f, (float)0.0f, (float)-1.0f);
                GL11.glRotatef((float)50.0f, (float)0.0f, (float)-1.0f, (float)0.0f);
            } else {
                GL11.glTranslatef((float)-0.5f, (float)2.5f, (float)-1.25f);
                GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                if (!var3.func_77973_b().func_77623_v()) {
                    GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)335.0f, (float)0.0f, (float)0.0f, (float)-1.0f);
                    GL11.glRotatef((float)50.0f, (float)0.0f, (float)-1.0f, (float)0.0f);
                }
            }
            if (var3.func_77973_b().func_77623_v()) {
                int renderPass = 0;
                do {
                    IIcon icon;
                    if ((icon = var3.func_77973_b().getIcon(var3, renderPass)) == null) continue;
                    Color color = new Color(var3.func_77973_b().func_82790_a(var3, renderPass));
                    GL11.glColor3ub((byte)((byte)color.getRed()), (byte)((byte)color.getGreen()), (byte)((byte)color.getBlue()));
                    float f = icon.func_94209_e();
                    float f1 = icon.func_94212_f();
                    float f2 = icon.func_94206_g();
                    float f3 = icon.func_94210_h();
                    ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.0625f);
                    GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
                } while (++renderPass < var3.func_77973_b().getRenderPasses(var3.func_77960_j()));
            } else {
                int i = var3.func_77973_b().func_82790_a(var3, 0);
                float f7 = (float)(i >> 16 & 0xFF) / 255.0f;
                float f8 = (float)(i >> 8 & 0xFF) / 255.0f;
                float f6 = (float)(i & 0xFF) / 255.0f;
                GL11.glColor4f((float)f7, (float)f8, (float)f6, (float)1.0f);
                this.field_76990_c.field_78721_f.func_78443_a((EntityLivingBase)e, var3, 0);
            }
            GL11.glScaled((double)1.0, (double)1.0, (double)1.0);
            GL11.glPopMatrix();
        } else if (e.getCore() == 5) {
            GL11.glPushMatrix();
            GL11.glScaled((double)0.4, (double)0.4, (double)0.4);
            GL11.glTranslatef((float)0.0f, (float)3.0f, (float)-1.1f);
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            UtilsFX.bindTexture("textures/models/bucket.png");
            this.model.renderPart("Bucket");
            if (e.getCarriedForDisplay() != null) {
                Fluid fluid = FluidRegistry.getFluid((int)Item.func_150891_b((Item)e.getCarriedForDisplay().func_77973_b()));
                float max = Math.max(e.getCarriedForDisplay().func_77960_j(), e.getFluidCarryLimit());
                float fill = (float)e.getCarriedForDisplay().func_77960_j() / max;
                if (fluid != null) {
                    GL11.glTranslatef((float)0.0f, (float)0.0f, (float)(0.2f + 0.8f * fill));
                    GL11.glScaled((double)0.8, (double)0.8, (double)0.8);
                    this.icon = fluid.getIcon();
                    int q = 0xF00000 | fluid.getLuminosity() << 4;
                    int b = Math.max(e.func_70070_b(par2), q);
                    UtilsFX.renderQuadCenteredFromIcon(true, this.icon, 1.0f, 1.0f, 1.0f, 1.0f, b, 771, 1.0f);
                }
            }
            GL11.glPopMatrix();
        }
    }

    protected void func_77029_c(EntityLivingBase par1EntityLiving, float par2) {
        this.renderCarriedItems((EntityGolemBase)par1EntityLiving, par2);
    }

    protected void func_77043_a(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
        this.renderWithSway((EntityGolemBase)par1EntityLiving, par2, par3, par4);
    }

    public void func_76986_a(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
        this.render((EntityGolemBase)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.render((EntityGolemBase)par1Entity, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        switch (((EntityGolemBase)entity).getGolemType()) {
            case STRAW: {
                return straw;
            }
            case WOOD: {
                return wood;
            }
            case CLAY: {
                return clay;
            }
            case STONE: {
                return stone;
            }
            case IRON: {
                return iron;
            }
            case TALLOW: {
                return tallow;
            }
            case FLESH: {
                return flesh;
            }
            case THAUMIUM: {
                return thaumium;
            }
        }
        return AbstractClientPlayer.field_110314_b;
    }
}

