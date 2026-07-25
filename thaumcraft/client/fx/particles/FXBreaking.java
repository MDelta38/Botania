/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.world.World
 */
package thaumcraft.client.fx.particles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

@SideOnly(value=Side.CLIENT)
public class FXBreaking
extends EntityFX {
    public void setParticleMaxAge(int particleMaxAge) {
        this.field_70547_e = particleMaxAge;
    }

    public FXBreaking(World par1World, double par2, double par4, double par6, Item par8Item) {
        super(par1World, par2, par4, par6, 0.0, 0.0, 0.0);
        this.func_110125_a(par8Item.func_77617_a(0));
        this.field_70551_j = 1.0f;
        this.field_70553_i = 1.0f;
        this.field_70552_h = 1.0f;
        this.field_70545_g = Blocks.field_150431_aC.field_149763_I;
        this.field_70544_f /= 2.0f;
    }

    public FXBreaking(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, Item par14Item) {
        this(par1World, par2, par4, par6, par14Item);
        this.field_70159_w *= (double)0.1f;
        this.field_70181_x *= (double)0.1f;
        this.field_70179_y *= (double)0.1f;
        this.field_70159_w += par8;
        this.field_70181_x += par10;
        this.field_70179_y += par12;
    }

    public int func_70537_b() {
        return 2;
    }

    public void func_70539_a(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
        float f6 = ((float)this.field_94054_b + this.field_70548_b / 4.0f) / 16.0f;
        float f7 = f6 + 0.015609375f;
        float f8 = ((float)this.field_94055_c + this.field_70549_c / 4.0f) / 16.0f;
        float f9 = f8 + 0.015609375f;
        float f10 = 0.1f * this.field_70544_f;
        float fade = 1.0f - (float)this.field_70546_d / (float)this.field_70547_e;
        f10 *= fade;
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
        par1Tessellator.func_78369_a(f14 * this.field_70552_h, f14 * this.field_70553_i, f14 * this.field_70551_j, this.field_82339_as * fade);
        par1Tessellator.func_78374_a((double)(f11 - par3 * f10 - par6 * f10), (double)(f12 - par4 * f10), (double)(f13 - par5 * f10 - par7 * f10), (double)f6, (double)f9);
        par1Tessellator.func_78374_a((double)(f11 - par3 * f10 + par6 * f10), (double)(f12 + par4 * f10), (double)(f13 - par5 * f10 + par7 * f10), (double)f6, (double)f8);
        par1Tessellator.func_78374_a((double)(f11 + par3 * f10 + par6 * f10), (double)(f12 + par4 * f10), (double)(f13 + par5 * f10 + par7 * f10), (double)f7, (double)f8);
        par1Tessellator.func_78374_a((double)(f11 + par3 * f10 - par6 * f10), (double)(f12 - par4 * f10), (double)(f13 + par5 * f10 - par7 * f10), (double)f7, (double)f9);
    }
}

