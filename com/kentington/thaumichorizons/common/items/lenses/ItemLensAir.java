/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.Vec3
 *  org.lwjgl.opengl.GL11
 */
package com.kentington.thaumichorizons.common.items.lenses;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.items.lenses.ILens;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class ItemLensAir
extends Item
implements ILens {
    ResourceLocation tex = new ResourceLocation("thaumichorizons", "textures/fx/ripple.png");
    ResourceLocation tex2 = new ResourceLocation("thaumichorizons", "textures/fx/vortex.png");
    IIcon icon;

    public ItemLensAir() {
        this.func_77637_a(ThaumicHorizons.tabTH);
    }

    @Override
    public String lensName() {
        return "LensAir";
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void handleRender(Minecraft mc, float partialTicks) {
        if (mc.field_71474_y.field_74320_O > 0) {
            return;
        }
        EntityClientPlayerMP player = mc.field_71439_g;
        List critters = player.field_70170_p.func_72839_b((Entity)mc.field_71439_g, AxisAlignedBB.func_72330_a((double)(player.field_70165_t - 24.0), (double)(player.field_70163_u - 24.0), (double)(player.field_70161_v - 24.0), (double)(player.field_70165_t + 24.0), (double)(player.field_70163_u + 24.0), (double)(player.field_70161_v + 24.0)));
        for (Entity ent : critters) {
            if (!(ent instanceof EntityLivingBase)) continue;
            Vec3 look = player.func_70040_Z();
            if (look.field_72448_b == -1.0) {
                look.field_72450_a = -0.1 * Math.sin(Math.toRadians(player.field_70177_z));
                look.field_72448_b = -0.999;
                look.field_72449_c = 0.1 * Math.cos(Math.toRadians(player.field_70177_z));
            } else if (look.field_72448_b == 1.0) {
                look.field_72450_a = -0.1 * Math.sin(Math.toRadians(player.field_70177_z));
                look.field_72448_b = 0.999;
                look.field_72449_c = 0.1 * Math.cos(Math.toRadians(player.field_70177_z));
            }
            Vec3 playerPos = player.func_70666_h(partialTicks);
            playerPos.field_72448_b += (double)(player.field_70129_M - player.field_70131_O + player.func_70047_e());
            Vec3 entityPos = ((EntityLivingBase)ent).func_70666_h(partialTicks);
            entityPos.field_72448_b += (double)(ent.field_70129_M + ent.field_70131_O / 2.0f);
            Vec3 pos = entityPos.func_72444_a(playerPos);
            double scale = pos.func_72433_c();
            double dot = pos.func_72430_b(look);
            if (dot >= 0.0) continue;
            Vec3 proj = Vec3.func_72443_a((double)(look.field_72450_a * dot), (double)(look.field_72448_b * dot), (double)(look.field_72449_c * dot));
            Vec3 rej = Vec3.func_72443_a((double)(pos.field_72450_a - proj.field_72450_a), (double)(pos.field_72448_b - proj.field_72448_b), (double)(pos.field_72449_c - proj.field_72449_c));
            Vec3 right = look.func_72431_c(Vec3.func_72443_a((double)1.0E-4, (double)-1.0, (double)1.0E-4));
            right = right.func_72432_b();
            Vec3 up = right.func_72431_c(look);
            up = up.func_72432_b();
            double vert = rej.func_72430_b(up);
            double horiz = rej.func_72430_b(right);
            ScaledResolution var5 = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
            int var6 = var5.func_78326_a();
            int var7 = var5.func_78328_b();
            float minScreen = Math.min(var6, var7);
            double hScale = proj.func_72433_c() * Math.tan(Math.toRadians(mc.field_71474_y.field_74334_X) / 2.0) * 2.0;
            horiz /= hScale;
            vert = vert / hScale / (double)var7 * (double)var6;
            GL11.glPushMatrix();
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glAlphaFunc((int)518, (float)0.005f);
            float minEnt = Math.min(ent.field_70130_N, ent.field_70131_O);
            double size = (double)minEnt * ((double)minScreen / scale);
            double xCenter = (double)var6 * (1.0 + horiz) / 2.0;
            double yCenter = (double)var7 * (1.0 - vert) / 2.0;
            Tessellator t = Tessellator.field_78398_a;
            if (((EntityLivingBase)ent).func_70668_bt() != EnumCreatureAttribute.UNDEAD) {
                FMLClientHandler.instance().getClient().field_71446_o.func_110577_a(this.tex);
                float sizeOffset = ent.field_70173_aa % 16;
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)(0.3f - (float)scale / 80.0f));
                int numRipples = (int)(48.0 / scale + 1.0);
                if (numRipples > 4) {
                    numRipples = 4;
                }
                for (int i = 0; i < numRipples; ++i) {
                    double ripSize = size * (double)(((float)(i * 16 / (numRipples + 1)) + sizeOffset) % 16.0f) / 12.0;
                    t.func_78382_b();
                    t.func_78374_a(xCenter - ripSize, yCenter + ripSize, 1.0, 0.0, 1.0);
                    t.func_78374_a(xCenter + ripSize, yCenter + ripSize, 1.0, 1.0, 1.0);
                    t.func_78374_a(xCenter + ripSize, yCenter - ripSize, 1.0, 1.0, 0.0);
                    t.func_78374_a(xCenter - ripSize, yCenter - ripSize, 1.0, 0.0, 0.0);
                    t.func_78381_a();
                }
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            } else {
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)(0.1f - (float)scale / 240.0f));
                size *= 2.0;
                FMLClientHandler.instance().getClient().field_71446_o.func_110577_a(this.tex2);
                double angle = Math.toRadians(ent.field_70173_aa % 360);
                double sin = Math.sin(angle);
                double cos = Math.cos(angle);
                GL11.glPushMatrix();
                t.func_78382_b();
                t.func_78374_a(xCenter - size * sin, yCenter + size * cos, 1.0, 0.0, 0.0);
                t.func_78374_a(xCenter + size * cos, yCenter + size * sin, 1.0, 1.0, 0.0);
                t.func_78374_a(xCenter + size * sin, yCenter - size * cos, 1.0, 1.0, 1.0);
                t.func_78374_a(xCenter - size * cos, yCenter - size * sin, 1.0, 0.0, 1.0);
                t.func_78381_a();
                GL11.glPopMatrix();
                double sin2 = Math.sin(-angle);
                double cos2 = Math.cos(-angle);
                GL11.glPushMatrix();
                t.func_78382_b();
                t.func_78374_a(xCenter - size * sin2 / 2.0, yCenter + size * cos2 / 2.0, 1.0, 0.0, 0.0);
                t.func_78374_a(xCenter + size * cos2 / 2.0, yCenter + size * sin2 / 2.0, 1.0, 1.0, 0.0);
                t.func_78374_a(xCenter + size * sin2 / 2.0, yCenter - size * cos2 / 2.0, 1.0, 1.0, 1.0);
                t.func_78374_a(xCenter - size * cos2 / 2.0, yCenter - size * sin2 / 2.0, 1.0, 0.0, 1.0);
                t.func_78381_a();
                GL11.glPopMatrix();
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            }
            GL11.glDisable((int)3042);
            GL11.glPopMatrix();
        }
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return "item.LensAir";
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumichorizons:lensair");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    @Override
    public void handleRemoval(EntityPlayer p) {
    }
}

