/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.crash.CrashReport
 *  net.minecraft.crash.CrashReportCategory
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ReportedException
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package thaumcraft.client.fx.particles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.common.config.ConfigBlocks;

@SideOnly(value=Side.CLIENT)
public class FXDrop
extends EntityFX {
    int bobTimer;

    public FXDrop(World par1World, double par2, double par4, double par6, float r, float g, float b) {
        super(par1World, par2, par4, par6, 0.0, 0.0, 0.0);
        this.field_70179_y = 0.0;
        this.field_70181_x = 0.0;
        this.field_70159_w = 0.0;
        this.field_70552_h = r;
        this.field_70553_i = g;
        this.field_70551_j = b;
        this.func_70536_a(113);
        this.field_70545_g = 0.06f;
        this.bobTimer = 40;
        this.field_70547_e = (int)(64.0 / (Math.random() * 0.8 + 0.2));
        this.field_70179_y = 0.0;
        this.field_70181_x = 0.0;
        this.field_70159_w = 0.0;
    }

    public int func_70070_b(float par1) {
        return 257;
    }

    public float func_70013_c(float par1) {
        return 1.0f;
    }

    public void func_70071_h_() {
        double d0;
        Material material;
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        this.field_70181_x -= (double)this.field_70545_g;
        if (this.bobTimer-- > 0) {
            this.field_70159_w *= 0.02;
            this.field_70181_x *= 0.02;
            this.field_70179_y *= 0.02;
            this.func_70536_a(113);
        } else {
            this.func_70536_a(112);
        }
        this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
        this.field_70159_w *= (double)0.98f;
        this.field_70181_x *= (double)0.98f;
        this.field_70179_y *= (double)0.98f;
        if (this.field_70547_e-- <= 0) {
            this.func_70106_y();
        }
        if (this.field_70122_E) {
            this.func_70536_a(114);
            this.field_70159_w *= (double)0.7f;
            this.field_70179_y *= (double)0.7f;
        }
        if ((material = this.field_70170_p.func_147439_a(MathHelper.func_76128_c((double)this.field_70165_t), MathHelper.func_76128_c((double)this.field_70163_u), MathHelper.func_76128_c((double)this.field_70161_v)).func_149688_o()) != Material.field_151592_s && (material.func_76224_d() || material.func_76220_a()) && this.field_70163_u < (d0 = (double)((float)(MathHelper.func_76128_c((double)this.field_70163_u) + 1) - BlockLiquid.func_149801_b((int)this.field_70170_p.func_72805_g(MathHelper.func_76128_c((double)this.field_70165_t), MathHelper.func_76128_c((double)this.field_70163_u), MathHelper.func_76128_c((double)this.field_70161_v)))))) {
            this.func_70106_y();
        }
    }

    public void func_70091_d(double par1, double par3, double par5) {
        int x = MathHelper.func_76128_c((double)this.field_70165_t);
        int y = MathHelper.func_76128_c((double)this.field_70163_u);
        int z = MathHelper.func_76128_c((double)this.field_70161_v);
        if (this.field_70145_X || this.field_70170_p.func_147439_a(x, y, z) == ConfigBlocks.blockJar) {
            this.field_70121_D.func_72317_d(par1, par3, par5);
            this.field_70165_t = (this.field_70121_D.field_72340_a + this.field_70121_D.field_72336_d) / 2.0;
            this.field_70163_u = this.field_70121_D.field_72338_b + (double)this.field_70129_M - (double)this.field_70139_V;
            this.field_70161_v = (this.field_70121_D.field_72339_c + this.field_70121_D.field_72334_f) / 2.0;
            x = MathHelper.func_76128_c((double)this.field_70165_t);
            y = MathHelper.func_76128_c((double)this.field_70163_u);
            y = MathHelper.func_76128_c((double)this.field_70163_u);
            if (this.field_70170_p.func_147439_a(x, y + 1, z) == ConfigBlocks.blockJar) {
                this.field_70165_t = this.field_70169_q;
                this.field_70163_u = this.field_70167_r;
                this.field_70161_v = this.field_70166_s;
                this.field_70181_x = 0.0;
                this.field_70122_E = true;
            }
        } else {
            int k;
            double d11;
            double d10;
            double d12;
            int j;
            boolean flag;
            this.field_70170_p.field_72984_F.func_76320_a("move");
            this.field_70139_V *= 0.4f;
            double d3 = this.field_70165_t;
            double d4 = this.field_70163_u;
            double d5 = this.field_70161_v;
            if (this.field_70134_J) {
                this.field_70134_J = false;
                par1 *= 0.25;
                par3 *= (double)0.05f;
                par5 *= 0.25;
                this.field_70159_w = 0.0;
                this.field_70181_x = 0.0;
                this.field_70179_y = 0.0;
            }
            double d6 = par1;
            double d7 = par3;
            double d8 = par5;
            AxisAlignedBB axisalignedbb = this.field_70121_D.func_72329_c();
            boolean bl = flag = this.field_70122_E && this.func_70093_af();
            if (flag) {
                double d9 = 0.05;
                while (par1 != 0.0 && this.field_70170_p.func_72945_a((Entity)this, this.field_70121_D.func_72325_c(par1, -1.0, 0.0)).isEmpty()) {
                    par1 = par1 < d9 && par1 >= -d9 ? 0.0 : (par1 > 0.0 ? (par1 -= d9) : (par1 += d9));
                    d6 = par1;
                }
                while (par5 != 0.0 && this.field_70170_p.func_72945_a((Entity)this, this.field_70121_D.func_72325_c(0.0, -1.0, par5)).isEmpty()) {
                    par5 = par5 < d9 && par5 >= -d9 ? 0.0 : (par5 > 0.0 ? (par5 -= d9) : (par5 += d9));
                    d8 = par5;
                }
                while (par1 != 0.0 && par5 != 0.0 && this.field_70170_p.func_72945_a((Entity)this, this.field_70121_D.func_72325_c(par1, -1.0, par5)).isEmpty()) {
                    par1 = par1 < d9 && par1 >= -d9 ? 0.0 : (par1 > 0.0 ? (par1 -= d9) : (par1 += d9));
                    par5 = par5 < d9 && par5 >= -d9 ? 0.0 : (par5 > 0.0 ? (par5 -= d9) : (par5 += d9));
                    d6 = par1;
                    d8 = par5;
                }
            }
            List list = this.field_70170_p.func_72945_a((Entity)this, this.field_70121_D.func_72321_a(par1, par3, par5));
            for (int i = 0; i < list.size(); ++i) {
                par3 = ((AxisAlignedBB)list.get(i)).func_72323_b(this.field_70121_D, par3);
            }
            this.field_70121_D.func_72317_d(0.0, par3, 0.0);
            if (!this.field_70135_K && d7 != par3) {
                par5 = 0.0;
                par3 = 0.0;
                par1 = 0.0;
            }
            boolean flag1 = this.field_70122_E || d7 != par3 && d7 < 0.0;
            for (j = 0; j < list.size(); ++j) {
                par1 = ((AxisAlignedBB)list.get(j)).func_72316_a(this.field_70121_D, par1);
            }
            this.field_70121_D.func_72317_d(par1, 0.0, 0.0);
            if (!this.field_70135_K && d6 != par1) {
                par5 = 0.0;
                par3 = 0.0;
                par1 = 0.0;
            }
            for (j = 0; j < list.size(); ++j) {
                par5 = ((AxisAlignedBB)list.get(j)).func_72322_c(this.field_70121_D, par5);
            }
            this.field_70121_D.func_72317_d(0.0, 0.0, par5);
            if (!this.field_70135_K && d8 != par5) {
                par5 = 0.0;
                par3 = 0.0;
                par1 = 0.0;
            }
            if (this.field_70138_W > 0.0f && flag1 && (flag || this.field_70139_V < 0.05f) && (d6 != par1 || d8 != par5)) {
                d12 = par1;
                d10 = par3;
                d11 = par5;
                par1 = d6;
                par3 = this.field_70138_W;
                par5 = d8;
                AxisAlignedBB axisalignedbb1 = this.field_70121_D.func_72329_c();
                this.field_70121_D.func_72328_c(axisalignedbb);
                list = this.field_70170_p.func_72945_a((Entity)this, this.field_70121_D.func_72321_a(d6, par3, d8));
                for (k = 0; k < list.size(); ++k) {
                    par3 = ((AxisAlignedBB)list.get(k)).func_72323_b(this.field_70121_D, par3);
                }
                this.field_70121_D.func_72317_d(0.0, par3, 0.0);
                if (!this.field_70135_K && d7 != par3) {
                    par5 = 0.0;
                    par3 = 0.0;
                    par1 = 0.0;
                }
                for (k = 0; k < list.size(); ++k) {
                    par1 = ((AxisAlignedBB)list.get(k)).func_72316_a(this.field_70121_D, par1);
                }
                this.field_70121_D.func_72317_d(par1, 0.0, 0.0);
                if (!this.field_70135_K && d6 != par1) {
                    par5 = 0.0;
                    par3 = 0.0;
                    par1 = 0.0;
                }
                for (k = 0; k < list.size(); ++k) {
                    par5 = ((AxisAlignedBB)list.get(k)).func_72322_c(this.field_70121_D, par5);
                }
                this.field_70121_D.func_72317_d(0.0, 0.0, par5);
                if (!this.field_70135_K && d8 != par5) {
                    par5 = 0.0;
                    par3 = 0.0;
                    par1 = 0.0;
                }
                if (!this.field_70135_K && d7 != par3) {
                    par5 = 0.0;
                    par3 = 0.0;
                    par1 = 0.0;
                } else {
                    par3 = -this.field_70138_W;
                    for (k = 0; k < list.size(); ++k) {
                        par3 = ((AxisAlignedBB)list.get(k)).func_72323_b(this.field_70121_D, par3);
                    }
                    this.field_70121_D.func_72317_d(0.0, par3, 0.0);
                }
                if (d12 * d12 + d11 * d11 >= par1 * par1 + par5 * par5) {
                    par1 = d12;
                    par3 = d10;
                    par5 = d11;
                    this.field_70121_D.func_72328_c(axisalignedbb1);
                }
            }
            this.field_70170_p.field_72984_F.func_76319_b();
            this.field_70170_p.field_72984_F.func_76320_a("rest");
            this.field_70165_t = (this.field_70121_D.field_72340_a + this.field_70121_D.field_72336_d) / 2.0;
            this.field_70163_u = this.field_70121_D.field_72338_b + (double)this.field_70129_M - (double)this.field_70139_V;
            this.field_70161_v = (this.field_70121_D.field_72339_c + this.field_70121_D.field_72334_f) / 2.0;
            this.field_70123_F = d6 != par1 || d8 != par5;
            this.field_70124_G = d7 != par3;
            this.field_70122_E = d7 != par3 && d7 < 0.0;
            this.field_70132_H = this.field_70123_F || this.field_70124_G;
            this.func_70064_a(par3, this.field_70122_E);
            if (d6 != par1) {
                this.field_70159_w = 0.0;
            }
            if (d7 != par3) {
                this.field_70181_x = 0.0;
            }
            if (d8 != par5) {
                this.field_70179_y = 0.0;
            }
            d12 = this.field_70165_t - d3;
            d10 = this.field_70163_u - d4;
            d11 = this.field_70161_v - d5;
            if (this.func_70041_e_() && !flag && this.field_70154_o == null) {
                int k1;
                int i1;
                int l = MathHelper.func_76128_c((double)this.field_70165_t);
                Block j1 = this.field_70170_p.func_147439_a(l, k = MathHelper.func_76128_c((double)(this.field_70163_u - (double)0.2f - (double)this.field_70129_M)), i1 = MathHelper.func_76128_c((double)this.field_70161_v));
                if (j1.isAir((IBlockAccess)this.field_70170_p, l, k, i1) && ((k1 = this.field_70170_p.func_147439_a(l, k - 1, i1).func_149645_b()) == 11 || k1 == 32 || k1 == 21)) {
                    j1 = this.field_70170_p.func_147439_a(l, k - 1, i1);
                }
                if (j1 != Blocks.field_150468_ap) {
                    d10 = 0.0;
                }
                this.field_70140_Q = (float)((double)this.field_70140_Q + (double)MathHelper.func_76133_a((double)(d12 * d12 + d11 * d11)) * 0.6);
                this.field_82151_R = (float)((double)this.field_82151_R + (double)MathHelper.func_76133_a((double)(d12 * d12 + d10 * d10 + d11 * d11)) * 0.6);
            }
            try {
                this.func_145775_I();
            }
            catch (Throwable throwable) {
                CrashReport crashreport = CrashReport.func_85055_a((Throwable)throwable, (String)"Checking entity tile collision");
                CrashReportCategory crashreportcategory = crashreport.func_85058_a("Entity being checked for collision");
                this.func_85029_a(crashreportcategory);
                throw new ReportedException(crashreport);
            }
            this.field_70170_p.field_72984_F.func_76319_b();
        }
    }
}

