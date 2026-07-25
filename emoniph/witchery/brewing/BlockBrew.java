/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.fluids.BlockFluidClassic
 *  net.minecraftforge.fluids.Fluid
 */
package com.emoniph.witchery.brewing;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.util.BlockUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockBrew
extends BlockFluidClassic {
    @SideOnly(value=Side.CLIENT)
    protected IIcon[] icons;

    public BlockBrew(Fluid fluid) {
        super(fluid, Material.field_151586_h);
        this.quantaPerBlock = 2;
    }

    public Block func_149663_c(String blockName) {
        BlockUtil.registerBlock((Block)this, blockName);
        return super.func_149663_c(blockName);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        return side != 0 && side != 1 ? this.icons[1] : this.icons[0];
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister iconRegister) {
        this.icons = new IIcon[]{iconRegister.func_94245_a(this.func_149641_N() + "_still"), iconRegister.func_94245_a(this.func_149641_N() + "_flow")};
        Witchery.Fluids.BREW.setIcons(this.icons[0], this.icons[1]);
    }

    public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
        if (world.func_147439_a(x, y, z).func_149688_o().func_76224_d()) {
            return false;
        }
        return super.canDisplace(world, x, y, z);
    }

    public boolean displaceIfPossible(World world, int x, int y, int z) {
        if (world.func_147439_a(x, y, z).func_149688_o().func_76224_d()) {
            return false;
        }
        return super.displaceIfPossible(world, x, y, z);
    }
}

