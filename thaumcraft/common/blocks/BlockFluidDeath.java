/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  net.minecraftforge.fluids.BlockFluidFinite
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidFinite;
import thaumcraft.api.damagesource.DamageSourceThaumcraft;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXSlimyBubble;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;

public class BlockFluidDeath
extends BlockFluidFinite {
    public IIcon iconStill;
    public IIcon iconFlow;

    public BlockFluidDeath() {
        super(ConfigBlocks.FLUIDDEATH, Material.field_151586_h);
        this.func_149647_a(Thaumcraft.tabTC);
        this.setQuantaPerBlock(4);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.iconStill = ir.func_94245_a("thaumcraft:fluiddeath");
        this.iconFlow = ir.func_94245_a("thaumcraft:fluiddeath");
        ConfigBlocks.FLUIDDEATH.setIcons(this.iconStill, this.iconFlow);
    }

    public IIcon func_149691_a(int par1, int par2) {
        return this.iconStill;
    }

    public void func_149670_a(World world, int x, int y, int z, Entity entity) {
        if (!world.field_72995_K && entity instanceof EntityLivingBase) {
            entity.func_70097_a(DamageSourceThaumcraft.dissolve, (float)(world.func_72805_g(x, y, z) + 1));
        }
    }

    public int getQuanta() {
        return this.quantaPerBlock;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World world, int x, int y, int z, Random rand) {
        int meta = world.func_72805_g(x, y, z);
        float h = rand.nextFloat() * 0.075f;
        FXSlimyBubble ef = new FXSlimyBubble(world, (float)x + rand.nextFloat(), (float)y + 0.1f + 0.225f * (float)meta, (float)z + rand.nextFloat(), 0.075f + h);
        ef.func_82338_g(0.8f);
        ef.func_70538_b(0.3f - rand.nextFloat() * 0.1f, 0.0f, 0.4f + rand.nextFloat() * 0.1f);
        ParticleEngine.instance.addEffect(world, ef);
        if (rand.nextInt(50) == 0) {
            double var21 = (float)x + rand.nextFloat();
            double var22 = (double)y + this.field_149756_F;
            double var23 = (float)z + rand.nextFloat();
            world.func_72980_b(var21, var22, var23, "liquid.lavapop", 0.1f + rand.nextFloat() * 0.1f, 0.9f + rand.nextFloat() * 0.15f, false);
        }
        super.func_149734_b(world, x, y, z, rand);
    }
}

