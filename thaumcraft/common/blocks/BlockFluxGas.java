/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.fluids.BlockFluidFinite
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidFinite;
import thaumcraft.api.entities.ITaintedMob;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;

public class BlockFluxGas
extends BlockFluidFinite {
    public IIcon iconStill;
    public IIcon iconFlow;

    public BlockFluxGas() {
        super(ConfigBlocks.FLUXGAS, Config.fluxGoomaterial);
        this.func_149647_a(Thaumcraft.tabTC);
        this.densityDir = 1;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.iconStill = ir.func_94245_a("thaumcraft:fluxgas");
        this.iconFlow = ir.func_94245_a("thaumcraft:fluxgas");
        ConfigBlocks.FLUXGAS.setIcons(this.iconStill, this.iconFlow);
    }

    public int getDensityDir() {
        return this.densityDir;
    }

    public int func_149645_b() {
        return ConfigBlocks.blockFluxGasRI;
    }

    public void setDensityDir(int densityDir) {
        this.densityDir = densityDir;
    }

    public IIcon func_149691_a(int par1, int par2) {
        return this.iconStill;
    }

    public int getQuanta() {
        return this.quantaPerBlock;
    }

    public void func_149670_a(World world, int x, int y, int z, Entity entity) {
        int md = world.func_72805_g(x, y, z);
        if (!(world.field_73012_v.nextInt(10) != 0 || !(entity instanceof EntityLivingBase) || entity instanceof ITaintedMob || ((EntityLivingBase)entity).func_70662_br() || ((EntityLivingBase)entity).func_82165_m(Config.potionVisExhaustID) || ((EntityLivingBase)entity).func_82165_m(Potion.field_76431_k.field_76415_H))) {
            if (world.field_73012_v.nextBoolean()) {
                PotionEffect pe = new PotionEffect(Config.potionVisExhaustID, 1200, md / 3, true);
                pe.getCurativeItems().clear();
                ((EntityLivingBase)entity).func_70690_d(pe);
            } else {
                ((EntityLivingBase)entity).func_70690_d(new PotionEffect(Potion.field_76431_k.field_76415_H, 80 + md * 20, 0, false));
            }
            if (md > 0) {
                world.func_72921_c(x, y, z, md - 1, 3);
            } else {
                world.func_147468_f(x, y, z);
            }
        }
    }

    public void func_149674_a(World world, int x, int y, int z, Random rand) {
        super.func_149674_a(world, x, y, z, rand);
    }

    public boolean isReplaceable(IBlockAccess world, int x, int y, int z) {
        int meta = world.func_72805_g(x, y, z);
        return meta < 2;
    }

    public boolean isInsideOfMaterial(World worldObj, Entity entity) {
        int k;
        int j;
        double d0 = entity.field_70163_u + (double)entity.func_70047_e();
        int i = MathHelper.func_76128_c((double)entity.field_70165_t);
        Block l = worldObj.func_147439_a(i, j = MathHelper.func_76141_d((float)MathHelper.func_76128_c((double)d0)), k = MathHelper.func_76128_c((double)entity.field_70161_v));
        if (l.func_149688_o() == this.field_149764_J) {
            float f = this.getQuantaPercentage((IBlockAccess)worldObj, i, j, k) - 0.11111111f;
            float f1 = (float)(j + 1) - f;
            return d0 < (double)f1;
        }
        return false;
    }
}

