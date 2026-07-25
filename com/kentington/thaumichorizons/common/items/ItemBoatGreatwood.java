/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemBoat
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.items;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.entities.EntityBoatGreatwood;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBoat;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemBoatGreatwood
extends ItemBoat {
    @SideOnly(value=Side.CLIENT)
    public IIcon icon;

    public ItemBoatGreatwood() {
        this.field_77777_bU = 1;
        this.func_77637_a(ThaumicHorizons.tabTH);
    }

    public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
        int i;
        float f8;
        float f6;
        double d3;
        float f5;
        float f = 1.0f;
        float f1 = p_77659_3_.field_70127_C + (p_77659_3_.field_70125_A - p_77659_3_.field_70127_C) * f;
        float f2 = p_77659_3_.field_70126_B + (p_77659_3_.field_70177_z - p_77659_3_.field_70126_B) * f;
        double d0 = p_77659_3_.field_70169_q + (p_77659_3_.field_70165_t - p_77659_3_.field_70169_q) * (double)f;
        double d1 = p_77659_3_.field_70167_r + (p_77659_3_.field_70163_u - p_77659_3_.field_70167_r) * (double)f + 1.62 - (double)p_77659_3_.field_70129_M;
        double d2 = p_77659_3_.field_70166_s + (p_77659_3_.field_70161_v - p_77659_3_.field_70166_s) * (double)f;
        Vec3 vec3 = Vec3.func_72443_a((double)d0, (double)d1, (double)d2);
        float f3 = MathHelper.func_76134_b((float)(-f2 * ((float)Math.PI / 180) - (float)Math.PI));
        float f4 = MathHelper.func_76126_a((float)(-f2 * ((float)Math.PI / 180) - (float)Math.PI));
        float f7 = f4 * (f5 = -MathHelper.func_76134_b((float)(-f1 * ((float)Math.PI / 180))));
        Vec3 vec31 = vec3.func_72441_c((double)f7 * (d3 = 5.0), (double)(f6 = MathHelper.func_76126_a((float)(-f1 * ((float)Math.PI / 180)))) * d3, (double)(f8 = f3 * f5) * d3);
        MovingObjectPosition movingobjectposition = p_77659_2_.func_72901_a(vec3, vec31, true);
        if (movingobjectposition == null) {
            return p_77659_1_;
        }
        Vec3 vec32 = p_77659_3_.func_70676_i(f);
        boolean flag = false;
        float f9 = 1.0f;
        List list = p_77659_2_.func_72839_b((Entity)p_77659_3_, p_77659_3_.field_70121_D.func_72321_a(vec32.field_72450_a * d3, vec32.field_72448_b * d3, vec32.field_72449_c * d3).func_72314_b((double)f9, (double)f9, (double)f9));
        for (i = 0; i < list.size(); ++i) {
            float f10;
            AxisAlignedBB axisalignedbb;
            Entity entity = (Entity)list.get(i);
            if (!entity.func_70067_L() || !(axisalignedbb = entity.field_70121_D.func_72314_b((double)(f10 = entity.func_70111_Y()), (double)f10, (double)f10)).func_72318_a(vec3)) continue;
            flag = true;
        }
        if (flag) {
            return p_77659_1_;
        }
        if (movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
            i = movingobjectposition.field_72311_b;
            int j = movingobjectposition.field_72312_c;
            int k = movingobjectposition.field_72309_d;
            if (p_77659_2_.func_147439_a(i, j, k) == Blocks.field_150431_aC) {
                --j;
            }
            EntityBoatGreatwood entityboat = new EntityBoatGreatwood(p_77659_2_, (float)i + 0.5f, (float)j + 1.0f, (float)k + 0.5f);
            entityboat.field_70177_z = ((MathHelper.func_76128_c((double)((double)(p_77659_3_.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3) - 1) * 90;
            if (!p_77659_2_.func_72945_a((Entity)entityboat, entityboat.field_70121_D.func_72314_b(-0.1, -0.1, -0.1)).isEmpty()) {
                return p_77659_1_;
            }
            if (!p_77659_2_.field_72995_K) {
                p_77659_2_.func_72838_d((Entity)entityboat);
            }
            if (!p_77659_3_.field_71075_bZ.field_75098_d) {
                --p_77659_1_.field_77994_a;
            }
        }
        return p_77659_1_;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumichorizons:boatgreatwood");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return "item.boatGreatwood";
    }
}

