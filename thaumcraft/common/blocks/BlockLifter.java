/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileLifter;

public class BlockLifter
extends BlockContainer {
    private Random random = new Random();
    public IIcon iconTop;
    public IIcon iconBottom;
    public IIcon iconSide;
    public IIcon iconGlow;

    public BlockLifter() {
        super(Material.field_151575_d);
        this.func_149711_c(2.5f);
        this.func_149752_b(15.0f);
        this.func_149672_a(field_149766_f);
        this.func_149647_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.iconTop = ir.func_94245_a("thaumcraft:liftertop");
        this.iconBottom = ir.func_94245_a("thaumcraft:arcaneearbottom");
        this.iconSide = ir.func_94245_a("thaumcraft:lifterside");
        this.iconGlow = ir.func_94245_a("thaumcraft:animatedglow");
    }

    public IIcon func_149691_a(int par1, int par2) {
        if (par1 == 0) {
            return this.iconBottom;
        }
        if (par1 == 1) {
            return this.iconTop;
        }
        return this.iconSide;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return ConfigBlocks.blockLifterRI;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World w, int i, int j, int k, Random r) {
        TileEntity te = w.func_147438_o(i, j, k);
        if (te != null && te instanceof TileLifter && !((TileLifter)te).gettingPower() && ((TileLifter)te).rangeAbove > 0) {
            Thaumcraft.proxy.sparkle((float)i + 0.2f + r.nextFloat() * 0.6f, j + 1, (float)k + 0.2f + r.nextFloat() * 0.6f, 1.0f, 3, -0.3f);
        }
    }

    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return side != ForgeDirection.UP && side != ForgeDirection.DOWN;
    }

    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
        return side > 1;
    }

    public void func_149726_b(World world, int x, int y, int z) {
        this.updateLifterStack(world, x, y, z);
        super.func_149726_b(world, x, y, z);
    }

    public void func_149749_a(World world, int x, int y, int z, Block par5, int par6) {
        this.updateLifterStack(world, x, y, z);
        super.func_149749_a(world, x, y, z, par5, par6);
    }

    public void func_149695_a(World world, int x, int y, int z, Block par5) {
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null && te instanceof TileLifter && ((TileLifter)te).gettingPower() != ((TileLifter)te).lastPowerState) {
            this.updateLifterStack(world, x, y, z);
        }
        super.func_149695_a(world, x, y, z, par5);
    }

    private void updateLifterStack(World worldObj, int xCoord, int yCoord, int zCoord) {
        int count = 1;
        while (worldObj.func_147439_a(xCoord, yCoord - count, zCoord) == this) {
            TileEntity te = worldObj.func_147438_o(xCoord, yCoord - count, zCoord);
            if (te != null && te instanceof TileLifter) {
                ((TileLifter)te).requiresUpdate = true;
            }
            ++count;
        }
    }

    public TileEntity func_149915_a(World var1, int md) {
        return new TileLifter();
    }
}

