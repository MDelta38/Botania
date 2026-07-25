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
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidFinite;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXBubble;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.entities.monster.EntityThaumicSlime;
import thaumcraft.common.lib.CustomSoundType;
import thaumcraft.common.lib.utils.Utils;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;

public class BlockFluxGoo
extends BlockFluidFinite {
    public IIcon iconStill;
    public IIcon iconFlow;

    public BlockFluxGoo() {
        super(ConfigBlocks.FLUXGOO, Config.fluxGoomaterial);
        this.func_149672_a(new CustomSoundType("gore", 1.0f, 1.0f));
        this.func_149647_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.iconStill = ir.func_94245_a("thaumcraft:fluxgoo");
        this.iconFlow = ir.func_94245_a("thaumcraft:fluxgoo");
        ConfigBlocks.FLUXGOO.setIcons(this.iconStill, this.iconFlow);
    }

    public IIcon func_149691_a(int par1, int par2) {
        return this.iconStill;
    }

    public int getQuanta() {
        return this.quantaPerBlock;
    }

    public void func_149670_a(World world, int x, int y, int z, Entity entity) {
        int md = world.func_72805_g(x, y, z);
        if (entity instanceof EntityThaumicSlime) {
            EntityThaumicSlime slime = (EntityThaumicSlime)entity;
            if (slime.getSlimeSize() < md && world.field_73012_v.nextBoolean()) {
                slime.setSlimeSize(slime.getSlimeSize() + 1);
                if (md > 1) {
                    world.func_72921_c(x, y, z, md - 1, 3);
                } else {
                    world.func_147468_f(x, y, z);
                }
            }
        } else {
            entity.field_70159_w *= (double)(1.0f - this.getQuantaPercentage((IBlockAccess)world, x, y, z));
            entity.field_70179_y *= (double)(1.0f - this.getQuantaPercentage((IBlockAccess)world, x, y, z));
            if (entity instanceof EntityLivingBase) {
                PotionEffect pe = new PotionEffect(Config.potionVisExhaustID, 600, md / 3, true);
                pe.getCurativeItems().clear();
                ((EntityLivingBase)entity).func_70690_d(pe);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World world, int x, int y, int z, Random rand) {
        int meta = world.func_72805_g(x, y, z);
        if (rand.nextInt(50 - Thaumcraft.proxy.particleCount(10)) <= meta) {
            FXBubble fb = new FXBubble(world, (float)x + rand.nextFloat(), (float)y + 0.125f * (float)meta, (float)z + rand.nextFloat(), 0.0, 0.0, 0.0, 0);
            fb.func_82338_g(0.25f);
            ParticleEngine.instance.addEffect(world, fb);
        }
        super.func_149734_b(world, x, y, z, rand);
    }

    public void func_149674_a(World world, int x, int y, int z, Random rand) {
        super.func_149674_a(world, x, y, z, rand);
        int meta = world.func_72805_g(x, y, z);
        if (meta >= 2 && meta < 6 && world.func_147437_c(x, y + 1, z) && rand.nextInt(25) == 0) {
            world.func_147468_f(x, y, z);
            EntityThaumicSlime slime = new EntityThaumicSlime(world);
            slime.func_70012_b((float)x + 0.5f, y, (float)z + 0.5f, 0.0f, 0.0f);
            slime.setSlimeSize(1);
            world.func_72838_d((Entity)slime);
            world.func_72956_a((Entity)slime, "thaumcraft:gore", 1.0f, 1.0f);
        } else if (meta >= 6 && world.func_147437_c(x, y + 1, z)) {
            if (rand.nextInt(25) == 0) {
                world.func_147468_f(x, y, z);
                EntityThaumicSlime slime = new EntityThaumicSlime(world);
                slime.func_70012_b((float)x + 0.5f, y, (float)z + 0.5f, 0.0f, 0.0f);
                slime.setSlimeSize(2);
                world.func_72838_d((Entity)slime);
                world.func_72956_a((Entity)slime, "thaumcraft:gore", 1.0f, 1.0f);
            } else if (Config.taintFromFlux && rand.nextInt(50) == 0) {
                Utils.setBiomeAt(world, x, z, ThaumcraftWorldGenerator.biomeTaint);
                world.func_147465_d(x, y, z, ConfigBlocks.blockTaintFibres, 0, 3);
                world.func_147452_c(x, y, z, ConfigBlocks.blockTaintFibres, 1, 0);
            }
        } else if (rand.nextInt(30) == 0) {
            if (meta == 0) {
                world.func_147468_f(x, y, z);
            } else {
                world.func_72921_c(x, y, z, meta - 1, 3);
                if (rand.nextBoolean() && world.func_147437_c(x, y + 1, z)) {
                    world.func_147465_d(x, y + 1, z, ConfigBlocks.blockFluxGas, 0, 3);
                }
            }
        }
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

    static {
        defaultDisplacements.put(ConfigBlocks.blockTaintFibres, true);
    }
}

