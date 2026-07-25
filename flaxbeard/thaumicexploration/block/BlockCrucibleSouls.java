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
 */
package flaxbeard.thaumicexploration.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import flaxbeard.thaumicexploration.ThaumicExploration;
import flaxbeard.thaumicexploration.tile.TileEntityCrucibleSouls;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCrucibleSouls
extends BlockContainer {
    public static IIcon middleSide;
    public static IIcon topSide;
    public static IIcon topTop;
    public static IIcon bottomTop;
    public static IIcon bottomBottom;
    public static IIcon topBottom;
    public IIcon texture;
    public IIcon[] IIcon = new IIcon[16];

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.IIcon[0] = ir.func_94245_a("thaumicexploration:metalbase");
        for (int a = 1; a <= 7; ++a) {
            this.IIcon[a] = ir.func_94245_a("thaumicExploration:crucible" + a);
        }
        this.IIcon[9] = ir.func_94245_a("thaumicExploration:blankTexture");
    }

    public IIcon func_149673_e(IBlockAccess iblockaccess, int i, int j, int k, int side) {
        TileEntityCrucibleSouls te = (TileEntityCrucibleSouls)iblockaccess.func_147438_o(i, j, k);
        if (side == 1) {
            return this.IIcon[1];
        }
        if (side == 0) {
            return this.IIcon[2];
        }
        return this.IIcon[9];
    }

    public BlockCrucibleSouls(int par1) {
        super(Material.field_151573_f);
        this.func_149711_c(3.0f);
        this.func_149752_b(17.0f);
        this.func_149672_a(Block.field_149777_j);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIIcon(int par1, int par2) {
        return this.IIcon[3];
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return ThaumicExploration.crucibleSoulsRenderID;
    }

    public TileEntity func_149915_a(World var1, int var2) {
        return new TileEntityCrucibleSouls();
    }
}

