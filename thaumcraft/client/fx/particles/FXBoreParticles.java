/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package thaumcraft.client.fx.particles;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

@SideOnly(value=Side.CLIENT)
public class FXBoreParticles
extends EntityFX {
    private Block blockInstance;
    private Item itemInstance;
    private int metadata;
    private int side;
    private double targetX;
    private double targetY;
    private double targetZ;

    public FXBoreParticles(World par1World, double par2, double par4, double par6, double tx, double ty, double tz, Block par14Block, int par15, int par16) {
        super(par1World, par2, par4, par6, 0.0, 0.0, 0.0);
        this.blockInstance = par14Block;
        this.func_110125_a(par14Block.func_149691_a(par15, par16));
        this.field_70545_g = par14Block.field_149763_I;
        this.field_70551_j = 0.6f;
        this.field_70553_i = 0.6f;
        this.field_70552_h = 0.6f;
        this.field_70544_f = this.field_70146_Z.nextFloat() * 0.3f + 0.4f;
        this.side = par15;
        this.targetX = tx;
        this.targetY = ty;
        this.targetZ = tz;
        double dx = tx - this.field_70165_t;
        double dy = ty - this.field_70163_u;
        double dz = tz - this.field_70161_v;
        int base = (int)(MathHelper.func_76133_a((double)(dx * dx + dy * dy + dz * dz)) * 3.0f);
        if (base < 1) {
            base = 1;
        }
        this.field_70547_e = base / 2 + this.field_70146_Z.nextInt(base);
        float f3 = 0.01f;
        this.field_70159_w = (float)this.field_70170_p.field_73012_v.nextGaussian() * f3;
        this.field_70181_x = (float)this.field_70170_p.field_73012_v.nextGaussian() * f3;
        this.field_70179_y = (float)this.field_70170_p.field_73012_v.nextGaussian() * f3;
        this.field_70545_g = 0.2f;
        this.field_70145_X = false;
        EntityLivingBase renderentity = FMLClientHandler.instance().getClient().field_71451_h;
        int visibleDistance = 64;
        if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j) {
            visibleDistance = 32;
        }
        if (renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > (double)visibleDistance) {
            this.field_70547_e = 0;
        }
    }

    public FXBoreParticles(World par1World, double par2, double par4, double par6, double tx, double ty, double tz, Item item, int par15, int par16) {
        super(par1World, par2, par4, par6, 0.0, 0.0, 0.0);
        this.itemInstance = item;
        this.func_110125_a(item.func_77617_a(par16));
        this.metadata = par16;
        this.field_70545_g = Blocks.field_150431_aC.field_149763_I;
        this.field_70551_j = 0.6f;
        this.field_70553_i = 0.6f;
        this.field_70552_h = 0.6f;
        this.field_70544_f = this.field_70146_Z.nextFloat() * 0.3f + 0.4f;
        this.side = par15;
        this.targetX = tx;
        this.targetY = ty;
        this.targetZ = tz;
        double dx = tx - this.field_70165_t;
        double dy = ty - this.field_70163_u;
        double dz = tz - this.field_70161_v;
        int base = (int)(MathHelper.func_76133_a((double)(dx * dx + dy * dy + dz * dz)) * 3.0f);
        if (base < 1) {
            base = 1;
        }
        this.field_70547_e = base / 2 + this.field_70146_Z.nextInt(base);
        float f3 = 0.01f;
        this.field_70159_w = (float)this.field_70170_p.field_73012_v.nextGaussian() * f3;
        this.field_70181_x = (float)this.field_70170_p.field_73012_v.nextGaussian() * f3;
        this.field_70179_y = (float)this.field_70170_p.field_73012_v.nextGaussian() * f3;
        this.field_70545_g = 0.2f;
        this.field_70145_X = false;
        EntityLivingBase renderentity = FMLClientHandler.instance().getClient().field_71451_h;
        int visibleDistance = 64;
        if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j) {
            visibleDistance = 32;
        }
        if (renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > (double)visibleDistance) {
            this.field_70547_e = 0;
        }
    }

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        if (this.field_70546_d++ >= this.field_70547_e || MathHelper.func_76128_c((double)this.field_70165_t) == MathHelper.func_76128_c((double)this.targetX) && MathHelper.func_76128_c((double)this.field_70163_u) == MathHelper.func_76128_c((double)this.targetY) && MathHelper.func_76128_c((double)this.field_70161_v) == MathHelper.func_76128_c((double)this.targetZ)) {
            this.func_70106_y();
            return;
        }
        if (!this.field_70145_X) {
            this.pushOutOfBlocks(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        }
        this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
        this.field_70159_w *= 0.985;
        this.field_70181_x *= 0.985;
        this.field_70179_y *= 0.985;
        double dx = this.targetX - this.field_70165_t;
        double dy = this.targetY - this.field_70163_u;
        double dz = this.targetZ - this.field_70161_v;
        double d13 = 0.3;
        double d11 = MathHelper.func_76133_a((double)(dx * dx + dy * dy + dz * dz));
        if (d11 < 4.0) {
            this.field_70544_f *= 0.9f;
            d13 = 0.6;
        }
        this.field_70159_w += (dx /= d11) * d13;
        this.field_70181_x += (dy /= d11) * d13;
        this.field_70179_y += (dz /= d11) * d13;
        this.field_70159_w = MathHelper.func_76131_a((float)((float)this.field_70159_w), (float)-0.35f, (float)0.35f);
        this.field_70181_x = MathHelper.func_76131_a((float)((float)this.field_70181_x), (float)-0.35f, (float)0.35f);
        this.field_70179_y = MathHelper.func_76131_a((float)((float)this.field_70179_y), (float)-0.35f, (float)0.35f);
    }

    public FXBoreParticles func_70596_a(int par1, int par2, int par3) {
        if (this.blockInstance != null && this.field_70170_p.func_147439_a(par1, par2, par3) == this.blockInstance) {
            if (this.blockInstance == Blocks.field_150349_c && this.side != 1) {
                return this;
            }
            try {
                int var4 = this.blockInstance.func_149720_d((IBlockAccess)this.field_70170_p, par1, par2, par3);
                this.field_70552_h *= (float)(var4 >> 16 & 0xFF) / 255.0f;
                this.field_70553_i *= (float)(var4 >> 8 & 0xFF) / 255.0f;
                this.field_70551_j *= (float)(var4 & 0xFF) / 255.0f;
            }
            catch (Exception e) {
                // empty catch block
            }
            return this;
        }
        try {
            int var4 = this.itemInstance.func_82790_a(new ItemStack(this.itemInstance, 1, this.metadata), 0);
            this.field_70552_h *= (float)(var4 >> 16 & 0xFF) / 255.0f;
            this.field_70553_i *= (float)(var4 >> 8 & 0xFF) / 255.0f;
            this.field_70551_j *= (float)(var4 & 0xFF) / 255.0f;
        }
        catch (Exception e) {
            // empty catch block
        }
        return this;
    }

    public FXBoreParticles applyRenderColor(int par1) {
        if (this.blockInstance != null) {
            if (this.blockInstance == Blocks.field_150349_c) {
                return this;
            }
            int var2 = this.blockInstance.func_149741_i(par1);
            this.field_70552_h *= (float)(var2 >> 16 & 0xFF) / 255.0f;
            this.field_70553_i *= (float)(var2 >> 8 & 0xFF) / 255.0f;
            this.field_70551_j *= (float)(var2 & 0xFF) / 255.0f;
            return this;
        }
        int var4 = this.itemInstance.func_82790_a(new ItemStack(this.itemInstance, 1, this.metadata), this.metadata);
        this.field_70552_h *= (float)(var4 >> 16 & 0xFF) / 255.0f;
        this.field_70553_i *= (float)(var4 >> 8 & 0xFF) / 255.0f;
        this.field_70551_j *= (float)(var4 & 0xFF) / 255.0f;
        return this;
    }

    public int func_70537_b() {
        return this.blockInstance != null ? 1 : 2;
    }

    public void func_70539_a(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
        float f6 = ((float)this.field_94054_b + this.field_70548_b / 4.0f) / 16.0f;
        float f7 = f6 + 0.015609375f;
        float f8 = ((float)this.field_94055_c + this.field_70549_c / 4.0f) / 16.0f;
        float f9 = f8 + 0.015609375f;
        float f10 = 0.1f * this.field_70544_f;
        if (this.field_70550_a != null) {
            f6 = this.field_70550_a.func_94214_a((double)(this.field_70548_b / 4.0f * 16.0f));
            f7 = this.field_70550_a.func_94214_a((double)((this.field_70548_b + 1.0f) / 4.0f * 16.0f));
            f8 = this.field_70550_a.func_94207_b((double)(this.field_70549_c / 4.0f * 16.0f));
            f9 = this.field_70550_a.func_94207_b((double)((this.field_70549_c + 1.0f) / 4.0f * 16.0f));
        }
        float f11 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)par2 - field_70556_an);
        float f12 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)par2 - field_70554_ao);
        float f13 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)par2 - field_70555_ap);
        float f14 = 1.0f;
        par1Tessellator.func_78386_a(f14 * this.field_70552_h, f14 * this.field_70553_i, f14 * this.field_70551_j);
        par1Tessellator.func_78374_a((double)(f11 - par3 * f10 - par6 * f10), (double)(f12 - par4 * f10), (double)(f13 - par5 * f10 - par7 * f10), (double)f6, (double)f9);
        par1Tessellator.func_78374_a((double)(f11 - par3 * f10 + par6 * f10), (double)(f12 + par4 * f10), (double)(f13 - par5 * f10 + par7 * f10), (double)f6, (double)f8);
        par1Tessellator.func_78374_a((double)(f11 + par3 * f10 + par6 * f10), (double)(f12 + par4 * f10), (double)(f13 + par5 * f10 + par7 * f10), (double)f7, (double)f8);
        par1Tessellator.func_78374_a((double)(f11 + par3 * f10 - par6 * f10), (double)(f12 - par4 * f10), (double)(f13 + par5 * f10 - par7 * f10), (double)f7, (double)f9);
    }

    protected boolean pushOutOfBlocks(double par1, double par3, double par5) {
        int var7 = MathHelper.func_76128_c((double)par1);
        int var8 = MathHelper.func_76128_c((double)par3);
        int var9 = MathHelper.func_76128_c((double)par5);
        double var10 = par1 - (double)var7;
        double var12 = par3 - (double)var8;
        double var14 = par5 - (double)var9;
        if (!this.field_70170_p.func_147437_c(var7, var8, var9) && !this.field_70170_p.func_72953_d(this.field_70121_D)) {
            boolean var16 = !this.field_70170_p.func_147445_c(var7 - 1, var8, var9, true);
            boolean var17 = !this.field_70170_p.func_147445_c(var7 + 1, var8, var9, true);
            boolean var18 = !this.field_70170_p.func_147445_c(var7, var8 - 1, var9, true);
            boolean var19 = !this.field_70170_p.func_147445_c(var7, var8 + 1, var9, true);
            boolean var20 = !this.field_70170_p.func_147445_c(var7, var8, var9 - 1, true);
            boolean var21 = !this.field_70170_p.func_147445_c(var7, var8, var9 + 1, true);
            int var22 = -1;
            double var23 = 9999.0;
            if (var16 && var10 < var23) {
                var23 = var10;
                var22 = 0;
            }
            if (var17 && 1.0 - var10 < var23) {
                var23 = 1.0 - var10;
                var22 = 1;
            }
            if (var18 && var12 < var23) {
                var23 = var12;
                var22 = 2;
            }
            if (var19 && 1.0 - var12 < var23) {
                var23 = 1.0 - var12;
                var22 = 3;
            }
            if (var20 && var14 < var23) {
                var23 = var14;
                var22 = 4;
            }
            if (var21 && 1.0 - var14 < var23) {
                var23 = 1.0 - var14;
                var22 = 5;
            }
            float var25 = this.field_70146_Z.nextFloat() * 0.05f + 0.025f;
            float var26 = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1f;
            if (var22 == 0) {
                this.field_70159_w = -var25;
                this.field_70181_x = this.field_70179_y = (double)var26;
            }
            if (var22 == 1) {
                this.field_70159_w = var25;
                this.field_70181_x = this.field_70179_y = (double)var26;
            }
            if (var22 == 2) {
                this.field_70181_x = -var25;
                this.field_70159_w = this.field_70179_y = (double)var26;
            }
            if (var22 == 3) {
                this.field_70181_x = var25;
                this.field_70159_w = this.field_70179_y = (double)var26;
            }
            if (var22 == 4) {
                this.field_70179_y = -var25;
                this.field_70181_x = this.field_70159_w = (double)var26;
            }
            if (var22 == 5) {
                this.field_70179_y = var25;
                this.field_70181_x = this.field_70159_w = (double)var26;
            }
            return true;
        }
        return false;
    }
}

