/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.model.AdvancedModelLoader
 *  net.minecraftforge.client.model.IModelCustom
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.models.gear;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashMap;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.fx.bolt.FXLightningBolt;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.items.armor.ItemHoverHarness;
import thaumcraft.common.lib.utils.BlockUtils;

@SideOnly(value=Side.CLIENT)
public class ModelHoverHarness
extends ModelBiped {
    HashMap<Integer, Long> timingShock = new HashMap();
    private static final ResourceLocation HARNESS = new ResourceLocation("thaumcraft", "textures/models/hoverharness.obj");
    private IModelCustom modelBack;

    public ModelHoverHarness() {
        this.field_78115_e = new ModelRenderer((ModelBase)this, 16, 16);
        this.field_78115_e.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 12, 4, 0.6f);
        this.modelBack = AdvancedModelLoader.loadModel((ResourceLocation)HARNESS);
    }

    public void func_78088_a(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        GL11.glPushMatrix();
        GL11.glPushMatrix();
        if (entity != null && entity.func_70093_af()) {
            GL11.glRotatef((float)28.64789f, (float)1.0f, (float)0.0f, (float)0.0f);
        }
        this.field_78115_e.func_78785_a(par7);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glDisable((int)2896);
        GL11.glScalef((float)0.1f, (float)0.1f, (float)0.1f);
        GL11.glRotatef((float)90.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
        if (entity != null && entity.func_70093_af()) {
            GL11.glRotatef((float)28.64789f, (float)1.0f, (float)0.0f, (float)0.0f);
        }
        GL11.glTranslatef((float)0.0f, (float)0.33f, (float)-3.7f);
        FMLClientHandler.instance().getClient().field_71446_o.func_110577_a(new ResourceLocation("thaumcraft", "textures/models/hoverharness2.png"));
        this.modelBack.renderAll();
        GL11.glEnable((int)2896);
        GL11.glPopMatrix();
        if (entity != null && entity instanceof EntityPlayer && !GL11.glIsEnabled((int)3042) && GL11.glGetInteger((int)2976) == 5888 && ((EntityPlayer)entity).field_71071_by.func_70440_f(2).func_77942_o() && ((EntityPlayer)entity).field_71071_by.func_70440_f((int)2).field_77990_d.func_74764_b("hover") && ((EntityPlayer)entity).field_71071_by.func_70440_f((int)2).field_77990_d.func_74771_c("hover") == 1) {
            long currenttime = System.currentTimeMillis();
            long timeShock = 0L;
            if (this.timingShock.get(entity.func_145782_y()) != null) {
                timeShock = this.timingShock.get(entity.func_145782_y());
            }
            GL11.glPushMatrix();
            float mod = 0.0f;
            if (entity.func_70093_af()) {
                GL11.glRotatef((float)28.64789f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glTranslatef((float)0.0f, (float)0.075f, (float)-0.05f);
                mod = 0.075f;
            }
            GL11.glTranslatef((float)0.0f, (float)0.2f, (float)0.55f);
            GL11.glPushMatrix();
            UtilsFX.renderQuadCenteredFromIcon(false, ((ItemHoverHarness)((EntityPlayer)entity).field_71071_by.func_70440_f((int)2).func_77973_b()).iconLightningRing, 2.5f, 1.0f, 1.0f, 1.0f, 230, 1, 1.0f);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.03f);
            UtilsFX.renderQuadCenteredFromIcon(false, ((ItemHoverHarness)((EntityPlayer)entity).field_71071_by.func_70440_f((int)2).func_77973_b()).iconLightningRing, 1.5f, 1.0f, 0.5f, 1.0f, 230, 1, 1.0f);
            GL11.glPopMatrix();
            GL11.glPopMatrix();
            if (timeShock < currenttime) {
                timeShock = currenttime + 50L + (long)entity.field_70170_p.field_73012_v.nextInt(50);
                this.timingShock.put(entity.func_145782_y(), timeShock);
                MovingObjectPosition mop = BlockUtils.getTargetBlock(entity.field_70170_p, entity.field_70165_t, entity.field_70163_u - (double)0.45f - (double)mod, entity.field_70161_v, ((EntityPlayer)entity).field_70761_aq - 90.0f - (float)entity.field_70170_p.field_73012_v.nextInt(180), -80 + entity.field_70170_p.field_73012_v.nextInt(160), false, 6.0);
                if (mop != null) {
                    double px = mop.field_72307_f.field_72450_a;
                    double py = mop.field_72307_f.field_72448_b;
                    double pz = mop.field_72307_f.field_72449_c;
                    FXLightningBolt bolt = new FXLightningBolt(entity.field_70170_p, entity.field_70165_t - (double)(MathHelper.func_76134_b((float)((((EntityPlayer)entity).field_70761_aq + 90.0f) / 180.0f * 3.141593f)) * 0.5f), entity.field_70163_u - (double)0.45f - (double)mod, entity.field_70161_v - (double)(MathHelper.func_76126_a((float)((((EntityPlayer)entity).field_70761_aq + 90.0f) / 180.0f * 3.141593f)) * 0.5f), px, py, pz, entity.field_70170_p.field_73012_v.nextLong(), 1, 2.0f, 3);
                    bolt.defaultFractal();
                    bolt.setType(6);
                    bolt.setWidth(0.015f);
                    bolt.finalizeBolt();
                }
            }
        }
        GL11.glPopMatrix();
    }
}

