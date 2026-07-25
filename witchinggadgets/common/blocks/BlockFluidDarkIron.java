/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraftforge.fluids.BlockFluidFinite
 *  net.minecraftforge.fluids.Fluid
 */
package witchinggadgets.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;

public class BlockFluidDarkIron
extends BlockFluidFinite {
    public BlockFluidDarkIron(Fluid fluid) {
        super(fluid, Material.field_151587_i);
    }

    public void func_149651_a(IIconRegister par1IconRegister) {
        this.field_149761_L = par1IconRegister.func_94245_a("witchinggadgets:darkIronFluid");
    }
}

