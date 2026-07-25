/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.fluids.BlockFluidClassic
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXBubble;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;

public class BlockFluidPure
extends BlockFluidClassic {
    public IIcon iconStill;
    public IIcon iconFlow;

    public BlockFluidPure() {
        super(ConfigBlocks.FLUIDPURE, Material.field_151586_h);
        this.func_149647_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.iconStill = ir.func_94245_a("thaumcraft:fluidpure");
        this.iconFlow = ir.func_94245_a("thaumcraft:fluidpure");
        ConfigBlocks.FLUIDPURE.setIcons(this.iconStill, this.iconFlow);
    }

    public IIcon func_149691_a(int par1, int par2) {
        return this.iconStill;
    }

    public void func_149670_a(World world, int x, int y, int z, Entity entity) {
        if (!world.field_72995_K && this.isSourceBlock((IBlockAccess)world, x, y, z) && entity instanceof EntityPlayer && !((EntityPlayer)entity).func_82165_m(Config.potionWarpWardID)) {
            int warp = Thaumcraft.proxy.getPlayerKnowledge().getWarpPerm(((EntityPlayer)entity).func_70005_c_());
            int div = 1;
            if (warp > 0 && (div = (int)Math.sqrt(warp)) < 1) {
                div = 1;
            }
            ((EntityPlayer)entity).func_70690_d(new PotionEffect(Config.potionWarpWardID, Math.min(32000, 200000 / div), 0, true));
            world.func_147468_f(x, y, z);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World world, int x, int y, int z, Random rand) {
        int meta = world.func_72805_g(x, y, z);
        FXBubble fb = new FXBubble(world, (float)x + rand.nextFloat(), (float)y + 0.125f * (float)(8 - meta), (float)z + rand.nextFloat(), 0.0, 0.0, 0.0, 0);
        fb.func_82338_g(0.25f);
        fb.setRGB(1.0f, 1.0f, 1.0f);
        ParticleEngine.instance.addEffect(world, fb);
        if (rand.nextInt(25) == 0) {
            double var21 = (float)x + rand.nextFloat();
            double var22 = (double)y + this.field_149756_F;
            double var23 = (float)z + rand.nextFloat();
            world.func_72980_b(var21, var22, var23, "liquid.lavapop", 0.1f + rand.nextFloat() * 0.1f, 0.9f + rand.nextFloat() * 0.15f, false);
        }
        super.func_149734_b(world, x, y, z, rand);
    }
}

